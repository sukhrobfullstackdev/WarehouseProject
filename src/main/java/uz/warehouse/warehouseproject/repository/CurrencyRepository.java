package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.Currency;
@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    boolean existsByName(String name);
}
