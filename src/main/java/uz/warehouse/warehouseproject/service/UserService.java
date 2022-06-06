package uz.warehouse.warehouseproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.User;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getUsersService(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return userRepository.findAll(pageable);
    }

    public User getUserService(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(User::new);
    }

    public Message addUserService(User user) {
        boolean exists = userRepository.existsByFirstNameAndLastNameAndPhoneNumber(user.getFirstName(), user.getLastName(), user.getPhoneNumber());
        if (!exists) {
            String maxVal = userRepository.findMaxCode();
            if (Objects.equals(maxVal, null)) {
                maxVal = "0";
            }
             String maxCode = Integer.toString(Integer.parseInt(maxVal) + 1);
            userRepository.save(new User(user.getFirstName(), user.getLastName(), user.getPhoneNumber(), maxCode, user.getPassword(), user.isActive()));
            return new Message(true, "The user has been successfully added!");
        } else {
            return new Message(false, "This user is already exists!");
        }
    }

    public Message editUserService(Integer id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User editingUser = optionalUser.get();
            editingUser.setFirstName(user.getFirstName());
            editingUser.setLastName(user.getLastName());
            editingUser.setActive(user.isActive());
            editingUser.setPassword(user.getPassword());
            editingUser.setPassword(user.getPhoneNumber());
            userRepository.save(editingUser);
            return new Message(true, "The user has been edited successfully!");
        } else {
            return new Message(false, "The user you want to edit has not been found!");
        }
    }

    public Message deleteUserService(Integer id) {
        boolean exists = userRepository.existsById(id);
        if (exists) {
            userRepository.deleteById(id);
            return new Message(true, "The user has been successfully deleted!");
        } else {
            return new Message(false, "The user you want to delete has not been found!");
        }
    }
}
