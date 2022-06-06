package uz.warehouse.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.Supplier;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;
@Service
public class SupplierService {
    final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Page<Supplier> getSuppliersService(int page) {
        Pageable pageable = PageRequest.of(page,10);
        return supplierRepository.findAll(pageable);
    }

    public Supplier getSupplierService(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElseGet(Supplier::new);
    }

    public Message addSupplierService(Supplier supplier) {
        boolean exists = supplierRepository.existsByNameAndPhoneNumber(supplier.getName(), supplier.getPhoneNumber());
        if (exists) {
            return new Message(false, "This supplier is already exists in our system!");
        } else {
            supplierRepository.save(new Supplier(supplier.getName(), supplier.isActive(), supplier.getPhoneNumber()));
            return new Message(true, "The supplier has been successfully added!");
        }
    }

    public Message editSupplierService(Integer id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier editingSupplier = optionalSupplier.get();
            editingSupplier.setName(supplier.getName());
            editingSupplier.setPhoneNumber(supplier.getPhoneNumber());
            editingSupplier.setActive(supplier.isActive());
            supplierRepository.save(editingSupplier);
            return new Message(true, "The supplier has been successfully edited!");
        } else {
            return new Message(false, "The supplier you want edit has not been found to edit!");
        }
    }

    public Message deleteSupplierService(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            supplierRepository.delete(optionalSupplier.get());
            return new Message(true, "The supplier has been successfully deleted!");
        } else {
            return new Message(true, "The supplier you want to delete has not been found!");
        }
    }
}
