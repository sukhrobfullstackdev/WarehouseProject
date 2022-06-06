package uz.warehouse.warehouseproject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.Product;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.payload.ProductDto;
import uz.warehouse.warehouseproject.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<Product> getProductsController(@RequestParam int page) {
        return productService.getProductsService(page);
    }

    @GetMapping(value = "/{id}")
    public Product getProductController(@PathVariable Integer id) {
        return productService.getProductService(id);
    }

    @PostMapping
    public Message addProductController(@RequestBody ProductDto productDto) {
        return productService.addProductService(productDto);
    }

    @PutMapping(value = "/{id}")
    public Message editProductController(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        return productService.editProductService(id, productDto);
    }
    @DeleteMapping(value = "/{id}")
    public Message deleteProductController(@PathVariable Integer id){
        return productService.deleteProductService(id);
    }
}
