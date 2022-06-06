package uz.warehouse.warehouseproject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.OutputProduct;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.payload.OutputProductDto;
import uz.warehouse.warehouseproject.service.OutputProductService;

@RestController
@RequestMapping(value = "/outputProduct")
public class OutputProductController {
    final OutputProductService outputProductService;

    public OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }

    @GetMapping
    public Page<OutputProduct> getOutputProductsController(@RequestParam int page) {
        return outputProductService.getOutputProductsService(page);
    }

    @GetMapping(value = "/{id}")
    public OutputProduct getOutputProductController(@PathVariable Integer id) {
        return outputProductService.getOutputProductService(id);
    }

    @PostMapping
    public Message addOutputProductController(@RequestBody OutputProductDto outputProductDto) {
        return outputProductService.addOutputProductService(outputProductDto);
    }

    @PutMapping(value = "/{id}")
    public Message addOutputProductController(@RequestBody OutputProductDto outputProductDto, @PathVariable Integer id) {
        return outputProductService.editOutputProductService(id, outputProductDto);
    }
    @DeleteMapping(value = "/{id}")
    public Message deleteOutputProductController(@PathVariable Integer id){
        return outputProductService.deleteOutputProductService(id);
    }
}
