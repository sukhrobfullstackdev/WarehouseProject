package uz.warehouse.warehouseproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.Client;
import uz.warehouse.warehouseproject.entity.Currency;
import uz.warehouse.warehouseproject.entity.Output;
import uz.warehouse.warehouseproject.entity.Warehouse;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.payload.OutputDto;
import uz.warehouse.warehouseproject.repository.ClientRepository;
import uz.warehouse.warehouseproject.repository.CurrencyRepository;
import uz.warehouse.warehouseproject.repository.OutputRepository;
import uz.warehouse.warehouseproject.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class OutputService {
    final OutputRepository outputRepository;
    final WarehouseRepository warehouseRepository;
    final CurrencyRepository currencyRepository;
    final ClientRepository clientRepository;

    public OutputService(OutputRepository outputRepository, WarehouseRepository warehouseRepository, CurrencyRepository currencyRepository, ClientRepository clientRepository) {
        this.outputRepository = outputRepository;
        this.warehouseRepository = warehouseRepository;
        this.currencyRepository = currencyRepository;
        this.clientRepository = clientRepository;
    }

    public Page<Output> getOutputsService(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return outputRepository.findAll(pageable);
    }

    public Output getOutputService(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElseGet(Output::new);
    }

    public Message addOutputService(OutputDto outputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalWarehouse.isEmpty()) {
            return new Message(false, "The warehouse which you you want to assign an output has not been found!");
        } else if (optionalCurrency.isEmpty()) {
            return new Message(false, "The currency which you you want to assign an output has not been found!");
        } else if (optionalClient.isEmpty()) {
            return new Message(false, "The client which you you want to assign an output has not been found!");
        } else {
            long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            String maxCode = outputRepository.getMaxCode();
            if (maxCode == null) {
                maxCode = "0";
            }
            int maxCodeIn = Integer.parseInt(maxCode) + 1;
            Output output = new Output(timestamp, optionalWarehouse.get(), optionalCurrency.get(), outputDto.getFacture_number(), Integer.toString(maxCodeIn), optionalClient.get());
            outputRepository.save(output);
            return new Message(true, "The output has been successfully added!");
        }
    }

    public Message editOutputService(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalOutput.isEmpty()) {
            return new Message(false, "The output that you want to edit has not been found!");
        } else if (optionalWarehouse.isEmpty()) {
            return new Message(false, "The warehouse that you want to assign to this output has not been found!");
        } else if (optionalCurrency.isEmpty()) {
            return new Message(false, "The currency that you want to assign to this output has not been found!");
        } else if (optionalClient.isEmpty()) {
            return new Message(false, "The client that you want to assign to this output has not been found!");
        } else {
            Output output = optionalOutput.get();
            output.setClient(optionalClient.get());
            output.setWarehouse(optionalWarehouse.get());
            output.setCurrency(optionalCurrency.get());
            output.setFacture_number(outputDto.getFacture_number());
            outputRepository.save(output);
            return new Message(true, "The output has been successfully edited!");
        }
    }

    public Message deleteOutputService(Integer id) {
        Optional<Output> byId = outputRepository.findById(id);
        if (byId.isPresent()) {
            outputRepository.delete(byId.get());
            return new Message(true, "The output has been successfully deleted!");
        } else {
            return new Message(true, "The output that you want to delete has not been found!");
        }
    }
}
