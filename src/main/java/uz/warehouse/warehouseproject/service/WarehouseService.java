package uz.warehouse.warehouseproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.Warehouse;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class WarehouseService {
    final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Page<Warehouse> getWarehousesService(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return warehouseRepository.findAll(pageable);
    }

    public Warehouse getWarehouseService(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElseGet(() -> new Warehouse());
    }

    public Message addWarehouseService(Warehouse warehouse) {
        boolean exists = warehouseRepository.existsByName(warehouse.getName());
        if (!exists) {
            warehouseRepository.save(warehouse);
            return new Message(true, "The warehouse has been successfully added!");
        } else {
            return new Message(false, "The warehouse named " + warehouse.getName() + " is already exists in our system!");
        }
    }

    public Message editWarehouseService(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()) {
            Warehouse editingWarehouse = optionalWarehouse.get();
            editingWarehouse.setName(warehouse.getName());
            editingWarehouse.setActive(warehouse.isActive());
            warehouseRepository.save(editingWarehouse);
            return new Message(true, "The warehouse has been successfully edited!");
        } else {
            return new Message(false, "The warehouse you want to edit has not been found!");
        }
    }

    public Message deleteWarehouseService(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()) {
            warehouseRepository.delete(optionalWarehouse.get());
            return new Message(true,"The warehouse has been successfully deleted!");
        } else {
            return new Message(false, "The warehouse you want to delete has not been found!");
        }
    }
}
