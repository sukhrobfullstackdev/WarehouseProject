package uz.warehouse.warehouseproject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.User;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<User> getUsersController(@RequestParam int page) {
        return userService.getUsersService(page);
    }

    @GetMapping(value = "/{id}")
    public User getUserController(@PathVariable Integer id) {
        return userService.getUserService(id);
    }

    @PostMapping
    public Message addUserController(@RequestBody User user) {
        return userService.addUserService(user);
    }

    @PutMapping(value = "/{id}")
    public Message editUserController(@RequestBody User user, @PathVariable Integer id) {
        return userService.editUserService(id, user);
    }
    @DeleteMapping(value = "/{id}")
    public Message deleteUserController(@PathVariable Integer id) {
        return userService.deleteUserService(id);
    }
}
