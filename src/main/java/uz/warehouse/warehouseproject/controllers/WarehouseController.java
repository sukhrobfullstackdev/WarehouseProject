package uz.warehouse.warehouseproject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.Warehouse;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.service.WarehouseService;

@RestController
@RequestMapping(value = "/warehouse")
public class WarehouseController {
    final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public Page<Warehouse> getWarehousesController(@RequestParam int page) {
        return warehouseService.getWarehousesService(page);
    }

    @GetMapping(value = "/{id}")
    public Warehouse getWarehouseController(@PathVariable Integer id) {
        return warehouseService.getWarehouseService(id);
    }

    @PostMapping
    public Message addWarehouseController(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouseService(warehouse);
    }

    @PutMapping(value = "/{id}")
    public Message editWarehouseController(@RequestBody Warehouse warehouse, @PathVariable Integer id) {
        return warehouseService.editWarehouseService(id, warehouse);
    }
    @DeleteMapping(value = "/{id}")
    public Message deleteWarehouseController(@PathVariable Integer id){
        return warehouseService.deleteWarehouseService(id);
    }
}
