package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.OutputProduct;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
}
