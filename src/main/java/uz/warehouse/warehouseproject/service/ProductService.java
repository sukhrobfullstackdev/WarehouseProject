package uz.warehouse.warehouseproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import uz.warehouse.warehouseproject.entity.Attachment;
import uz.warehouse.warehouseproject.entity.Category;
import uz.warehouse.warehouseproject.entity.Measurement;
import uz.warehouse.warehouseproject.entity.Product;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.payload.ProductDto;
import uz.warehouse.warehouseproject.repository.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;
    final MeasurementRepository measurementRepository;
    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;

    public ProductService(AttachmentContentRepository attachmentContentRepository, ProductRepository productRepository, CategoryRepository categoryRepository, MeasurementRepository measurementRepository, AttachmentRepository attachmentRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.measurementRepository = measurementRepository;
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    public Page<Product> getProductsService(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return productRepository.findAll(pageable);
    }

    public Product getProductService(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseGet(Product::new);
    }

    public Message addProductService(ProductDto productDto) {
        if (!Objects.equals(productDto.getName(), "") && productDto.getCategoryId() != null) {
            boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
            if (!exists) {
                Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
                Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
                Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
                if (optionalCategory.isEmpty()) {
                    return new Message(false, "This kind of category has not been found to add a new product to it!");
                } else if (optionalMeasurement.isEmpty()) {
                    return new Message(false, "This kind of measurement has not been found to assign a new product to it!");
                } else if (optionalAttachment.isEmpty()) {
                    return new Message(false, "This kind of photo has not been found to assign a new product to it!");
                } else {
                    Product product = new Product();
                    product.setName(productDto.getName());
                    int code;
                    Object codeResult = productRepository.getMaxId();
                    if (codeResult == null) {
                        code = 1;
                    } else {
                        code = productRepository.getMaxId() + 1;
                    }
                    product.setCode(Integer.toString(code));
                    product.setCategory(optionalCategory.get());
                    product.setMeasure(optionalMeasurement.get());
                    product.setAttachment(optionalAttachment.get());
                    productRepository.save(product);
                    return new Message(true, "The product has been successfully added!");
                }
            } else {
                return new Message(false, "The product named (" + productDto.getName() + ") is already exists in the category (" + productDto.getCategoryId() + ")!");
            }
        } else {
            return new Message(false, "The name of product or category is undefined!");
        }
    }

    public Message editProductService(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
            if (optionalCategory.isEmpty()) {
                return new Message(false, "You have not assigned a category to the product!");
            } else if (optionalMeasurement.isEmpty()) {
                return new Message(false, "You have not assigned a measurement to the product!");
            } else if (optionalAttachment.isEmpty()) {
                return new Message(false, "You have not assigned a photo to the product!");
            } else {
                Category category = optionalCategory.get();
                Measurement measurement = optionalMeasurement.get();
                Attachment attachment = optionalAttachment.get();
                Product product = optionalProduct.get();
                product.setName(productDto.getName());
                product.setMeasure(measurement);
                product.setAttachment(attachment);
                product.setCategory(category);
                productRepository.save(product);
                return new Message(true, "The product has been successfully edited!");
            }
        } else {
            return new Message(false, "The product you want to edit has not been found in our system!");
        }
    }

    public Message deleteProductService(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Attachment attachment = product.getAttachment();
            productRepository.delete(product);
            attachmentContentRepository.deleteById(attachment.getId());
            attachmentRepository.delete(attachment);
            return new Message(true, "The product has been successfully deleted!");
        } else {
            return new Message(false, "The product which you want to delete has not been found!");
        }
    }
}
