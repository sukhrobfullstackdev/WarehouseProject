package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.Supplier;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    boolean existsByNameAndPhoneNumber(String name, String phoneNumber);
}
