package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.Output;
@Repository
public interface OutputRepository extends JpaRepository<Output,Integer> {
    @Query(value = "select max(code) from output",nativeQuery = true)
    String getMaxCode();
}
