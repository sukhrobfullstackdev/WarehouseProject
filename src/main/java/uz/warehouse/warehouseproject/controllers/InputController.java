package uz.warehouse.warehouseproject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.Input;
import uz.warehouse.warehouseproject.payload.InputDto;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.service.InputService;

@RestController
@RequestMapping(value = "/input")
public class InputController {
    final InputService inputService;

    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @GetMapping
    public Page<Input> getInputsController(@RequestParam int page) {
        return inputService.getInputsService(page);
    }

    @GetMapping(value = "/{id}")
    public Input getInputController(@PathVariable Integer id) {
        return inputService.getInputService(id);
    }

    @PostMapping
    public Message addInputController(@RequestBody InputDto inputDto) {
        return inputService.addInputService(inputDto);
    }

    @PutMapping(value = "/{id}")
    public Message editInputController(@RequestBody InputDto inputDto, @PathVariable Integer id) {
        return inputService.editInputService(id, inputDto);
    }

    @DeleteMapping(value = "/{id}")
    public Message deleteInputController(@PathVariable Integer id) {
        return inputService.deleteInputService(id);
    }
}
