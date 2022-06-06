package uz.warehouse.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.Output;
import uz.warehouse.warehouseproject.entity.OutputProduct;
import uz.warehouse.warehouseproject.entity.Product;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.payload.OutputProductDto;
import uz.warehouse.warehouseproject.repository.OutputProductRepository;
import uz.warehouse.warehouseproject.repository.OutputRepository;
import uz.warehouse.warehouseproject.repository.ProductRepository;

import java.util.Optional;

@Service
public class OutputProductService {
    final OutputProductRepository outputProductRepository;
    final ProductRepository productRepository;
    final OutputRepository outputRepository;

    public OutputProductService(OutputProductRepository outputProductRepository, ProductRepository productRepository, OutputRepository outputRepository) {
        this.outputProductRepository = outputProductRepository;
        this.productRepository = productRepository;
        this.outputRepository = outputRepository;
    }

    public Page<OutputProduct> getOutputProductsService(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return outputProductRepository.findAll(pageable);
    }

    public OutputProduct getOutputProductService(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElseGet(OutputProduct::new);
    }

    public Message addOutputProductService(OutputProductDto outputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalProduct.isPresent()) {
            return new Message(false, "The product that you want to assign to this output product has not been found!");
        } else if (!optionalOutput.isPresent()) {
            return new Message(false, "The output that you want to assign to this output product has not been found!");
        } else {
            OutputProduct outputProduct = new OutputProduct(optionalProduct.get(), outputProductDto.getAmount(), outputProductDto.getPrice(), optionalOutput.get());
            outputProductRepository.save(outputProduct);
            return new Message(true, "The output's product has been successfully added!");
        }
    }

    public Message editOutputProductService(Integer id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutputProduct.isPresent()) {
            return new Message(false, "The output's product that you want to edit has not been found!");
        } else if (!optionalProduct.isPresent()) {
            return new Message(false, "The product that you want to assign to this output has not been found!");
        } else if (!optionalOutput.isPresent()) {
            return new Message(false, "The output that you want to assign to this output product has not been found!");
        } else {
            OutputProduct outputProduct = optionalOutputProduct.get();
            outputProduct.setOutput(optionalOutput.get());
            outputProduct.setProduct(optionalProduct.get());
            outputProduct.setAmount(outputProductDto.getAmount());
            outputProduct.setPrice(outputProductDto.getPrice());
            outputProductRepository.save(outputProduct);
            return new Message(true, "The output product has been successfully edited!");
        }
    }

    public Message deleteOutputProductService(Integer id) {
        Optional<OutputProduct> outputProductOptional = outputProductRepository.findById(id);
        if (outputProductOptional.isPresent()) {
            outputProductRepository.delete(outputProductOptional.get());
            return new Message(true, "The output product has been successfully deleted!");
        } else {
            return new Message(false, "The output product that you want to delete has not been found!");
        }
    }
}
