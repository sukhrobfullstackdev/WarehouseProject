package uz.warehouse.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.Currency;
import uz.warehouse.warehouseproject.entity.Input;
import uz.warehouse.warehouseproject.entity.Supplier;
import uz.warehouse.warehouseproject.entity.Warehouse;
import uz.warehouse.warehouseproject.payload.InputDto;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.CurrencyRepository;
import uz.warehouse.warehouseproject.repository.InputRepository;
import uz.warehouse.warehouseproject.repository.SupplierRepository;
import uz.warehouse.warehouseproject.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class InputService {
    final InputRepository inputRepository;
    final WarehouseRepository warehouseRepository;
    final SupplierRepository supplierRepository;
    final CurrencyRepository currencyRepository;

    public InputService(InputRepository inputRepository, WarehouseRepository warehouseRepository, SupplierRepository supplierRepository, CurrencyRepository currencyRepository) {
        this.inputRepository = inputRepository;
        this.warehouseRepository = warehouseRepository;
        this.supplierRepository = supplierRepository;
        this.currencyRepository = currencyRepository;
    }

    public Page<Input> getInputsService(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return inputRepository.findAll(pageable);
    }

    public Input getInputService(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElseGet(Input::new);
    }

    public Message addInputService(InputDto inputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalWarehouse.isEmpty()) {
            return new Message(false, "The warehouse that you want to assign a new input has not been found!");
        } else if (optionalSupplier.isEmpty()) {
            return new Message(false, "The supplier that you want to assign a new input has not been found!");
        } else if (optionalCurrency.isEmpty()) {
            return new Message(false, "The currency that you want to assign a new input has not been found!");
        } else {
            String maxVal = inputRepository.findMaxCode();
            if (maxVal == null) {
                maxVal = "0";
            }
            String code = String.valueOf((Integer.parseInt(maxVal) + 1));
            long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            Input input = new Input(timestamp,optionalWarehouse.get(), optionalSupplier.get(), optionalCurrency.get(), inputDto.getFacture_number(), code);
            inputRepository.save(input);
            return new Message(true, "The input has been successfully added!");
        }
    }

    public Message editInputService(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalInput.isEmpty()) {
            return new Message(false, "The input that you want to edit has not been found!");
        } else if (optionalWarehouse.isEmpty()) {
            return new Message(false, "The warehouse that you want to assign to this input has not been found!");
        } else if (optionalSupplier.isEmpty()) {
            return new Message(false, "The supplier that you want to assign to this input has not been found!");
        } else if (optionalCurrency.isEmpty()) {
            return new Message(false, "The currency that you want to assign to this input has not been found!");
        } else {
            long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            Input input = optionalInput.get();
            input.setWarehouse(optionalWarehouse.get());
            input.setCurrency(optionalCurrency.get());
            input.setSupplier(optionalSupplier.get());
            input.setFacture_number(inputDto.getFacture_number());
            input.setDate(timestamp);
            inputRepository.save(input);
            return new Message(true, "The input has been successfully edited!");
        }
    }

    public Message deleteInputService(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {
            inputRepository.delete(optionalInput.get());
            return new Message(true, "The input has been successfully deleted!");
        } else {
            return new Message(false, "The input that you want to delete has not been found!");
        }
    }
}
