package uz.warehouse.warehouseproject.controllers;

import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.Measurement;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping(value = "/measurement")
public class MeasurementController {
    final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    public List<Measurement> getMeasurementsController() {
        return measurementService.getMeasurementsService();
    }

    @GetMapping(value = "/{id}")
    public Measurement getMeasurementByIdController(@PathVariable Integer id) {
        return measurementService.getMeasurementByIdService(id);
    }

    @PostMapping
    public Message addMeasurementController(@RequestBody Measurement measurement) {
        return measurementService.addMeasurementService(measurement);
    }

    @PutMapping(value = "/{id}")
    public Message editMeasurementController(@RequestBody Measurement measurement, @PathVariable Integer id) {
        return measurementService.editMeasurementService(id, measurement); // isActive stays still active!
    }

    @DeleteMapping(value = "/{id}")
    public Message deleteMeasurementController(@PathVariable Integer id) {
        return measurementService.deleteMeasurementService(id);
    }
}
