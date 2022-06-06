package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.InputProduct;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
}
