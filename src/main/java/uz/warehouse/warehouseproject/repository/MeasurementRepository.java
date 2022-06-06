package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.Measurement;
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    boolean existsByName(String name);
}
