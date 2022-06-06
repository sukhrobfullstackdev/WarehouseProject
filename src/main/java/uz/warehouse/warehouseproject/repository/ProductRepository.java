package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByNameAndCategoryId(String name, Integer category_id);
    @Query(value = "select max(code) from product",nativeQuery = true)
    Integer getMaxId();
}
