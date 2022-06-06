package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.Input;
@Repository
public interface InputRepository extends JpaRepository<Input,Integer> {
    @Query(value = "select max(code) from input",nativeQuery = true)
    String findMaxCode();
}
