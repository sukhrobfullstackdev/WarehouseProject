package uz.warehouse.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.Measurement;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> getMeasurementsService() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementByIdService(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.orElseGet(Measurement::new);
    }

    public Message addMeasurementService(Measurement measurement) {
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (!existsByName) {
            measurementRepository.save(measurement);
            return new Message(true, "The new measure of products has been successfully added to our system!");
        } else {
            return new Message(false, "This kind of measurement already exists in our system!");
        }
    }

    public Message editMeasurementService(Integer id, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            Measurement editingMeasurement = optionalMeasurement.get();
            editingMeasurement.setName(measurement.getName());
            editingMeasurement.setActive(measurement.isActive());
            measurementRepository.save(editingMeasurement);
            return new Message(true, "This type of measurement has been successfully edited!");
        } else {
            return new Message(false, "This type of measurement has not been found to edit!");
        }
    }

    public Message deleteMeasurementService(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            Measurement measurement = optionalMeasurement.get();
            measurementRepository.delete(measurement);
            return new Message(true, "The measurement has been successfully deleted!");
        } else {
            return new Message(false, "The measurement has not been found to delete!");
        }
    }
}
