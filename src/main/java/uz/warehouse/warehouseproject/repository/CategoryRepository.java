package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
