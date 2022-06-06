package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    boolean existsByName(String name);
}
