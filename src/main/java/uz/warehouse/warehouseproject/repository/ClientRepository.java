package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    boolean existsByNameAndPhoneNumber(String name, String phoneNumber);
}
