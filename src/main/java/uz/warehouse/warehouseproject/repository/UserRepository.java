package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByFirstNameAndLastNameAndPhoneNumber(String firstName, String lastName, String phoneNumber);

    @Query(value = "select max(code) from users", nativeQuery = true)
    String findMaxCode();
    boolean existsById(Integer id);
}
