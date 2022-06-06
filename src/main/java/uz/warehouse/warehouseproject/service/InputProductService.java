package uz.warehouse.warehouseproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.Input;
import uz.warehouse.warehouseproject.entity.InputProduct;
import uz.warehouse.warehouseproject.entity.Product;
import uz.warehouse.warehouseproject.payload.InputProductDto;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.InputProductRepository;
import uz.warehouse.warehouseproject.repository.InputRepository;
import uz.warehouse.warehouseproject.repository.ProductRepository;

import java.util.Optional;

@Service
public class InputProductService {
    final InputProductRepository inputProductRepository;
    final ProductRepository productRepository;
    final InputRepository inputRepository;

    public InputProductService(InputProductRepository inputProductRepository, ProductRepository productRepository, InputRepository inputRepository) {
        this.inputProductRepository = inputProductRepository;
        this.productRepository = productRepository;
        this.inputRepository = inputRepository;
    }

    public Page<InputProduct> getInputProductsService(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return inputProductRepository.findAll(pageable);
    }

    public InputProduct getInputProductService(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElseGet(InputProduct::new);
    }

    public Message addInputProductService(InputProductDto inputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalProduct.isEmpty()) {
            return new Message(false, "The product has not been found to assign to this input product!");
        } else if (optionalInput.isEmpty()) {
            return new Message(false, "The input has not been found to assign to this input product!");
        } else {
            InputProduct inputProduct = new InputProduct(optionalProduct.get(), inputProductDto.getAmount(), inputProductDto.getPrice(), inputProductDto.getExpireDate(), optionalInput.get());
            inputProductRepository.save(inputProduct);
            return new Message(true, "The product input has been successfully added!");
        }
    }

    public Message editInputProductService(Integer id, InputProductDto inputProductDto) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInputProduct.isEmpty()) {
            return new Message(false, "The input product that you edit has not been found!");
        } else if (optionalProduct.isEmpty()) {
            return new Message(false, "The product that you want to assign to this input product has not been found!");
        } else if (optionalInput.isEmpty()) {
            return new Message(false, "The input that you want to assign to this input product has not been found!");
        } else {
            InputProduct inputProduct = optionalInputProduct.get();
            Product product = optionalProduct.get();
            Input input = optionalInput.get();
            inputProduct.setInput(input);
            inputProduct.setProduct(product);
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpireDate(inputProductDto.getExpireDate());
            inputProductRepository.save(inputProduct);
            return new Message(true, "The input product has been successfully edited!");
        }
    }

    public Message deleteInputProductService(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()) {
            inputProductRepository.delete(optionalInputProduct.get());
            return new Message(true, "The input product has been successfully deleted!");
        } else {
            return new Message(false, "The input product that you want to delete has not been found!");
        }
    }
}
