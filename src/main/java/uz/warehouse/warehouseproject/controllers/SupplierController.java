package uz.warehouse.warehouseproject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.Supplier;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.service.SupplierService;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {
    final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public Page<Supplier> getSuppliersController(@RequestParam int page) {
        return supplierService.getSuppliersService(page);
    }

    @GetMapping(value = "/{id}")
    public Supplier getSupplierController(@PathVariable Integer id) {
        return supplierService.getSupplierService(id);
    }

    @PostMapping
    public Message addSupplierController(@RequestBody Supplier supplier) {
        return supplierService.addSupplierService(supplier);
    }

    @PutMapping(value = "/{id}")
    public Message editSupplierController(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return supplierService.editSupplierService(id, supplier);
    }

    @DeleteMapping(value = "/{id}")
    public Message deleteSupplierController(@PathVariable Integer id) {
        return supplierService.deleteSupplierService(id);
    }
}
