package uz.warehouse.warehouseproject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.InputProduct;
import uz.warehouse.warehouseproject.payload.InputProductDto;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.service.InputProductService;

@RestController
@RequestMapping(value = "/inputProduct")
public class InputProductController {
    final InputProductService inputProductService;

    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @GetMapping
    public Page<InputProduct> getInputProductsController(@RequestParam int page) {
        return inputProductService.getInputProductsService(page);
    }

    @GetMapping(value = "/{id}")
    public InputProduct getInputProductController(@PathVariable Integer id) {
        return inputProductService.getInputProductService(id);
    }

    @PostMapping
    public Message addInputProductController(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.addInputProductService(inputProductDto); // bug
    }

    @PutMapping(value = "/{id}")
    public Message editInputProductController(@RequestBody InputProductDto inputProductDto, @PathVariable Integer id) {
        return inputProductService.editInputProductService(id, inputProductDto);
    }
    @DeleteMapping(value = "/{id}")
    public Message deleteInputProductController(@PathVariable Integer id) {
        return inputProductService.deleteInputProductService(id);
    }
}
