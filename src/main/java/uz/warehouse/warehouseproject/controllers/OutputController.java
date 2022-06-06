package uz.warehouse.warehouseproject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.Output;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.payload.OutputDto;
import uz.warehouse.warehouseproject.service.OutputService;

@RestController
@RequestMapping(value = "/output")
public class OutputController {
    final OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @GetMapping
    public Page<Output> getOutputsController(@RequestParam int page) {
        return outputService.getOutputsService(page);
    }

    @GetMapping(value = "/{id}")
    public Output getOutputController(@PathVariable Integer id) {
        return outputService.getOutputService(id);
    }

    @PostMapping
    public Message addOutputController(@RequestBody OutputDto outputDto) {
        return outputService.addOutputService(outputDto);
    }

    @PutMapping(value = "/{id}")
    public Message editOutputController(@RequestBody OutputDto outputDto, @PathVariable Integer id) {
        return outputService.editOutputService(id, outputDto);
    }
    @DeleteMapping(value = "/{id}")
    public Message deleteOutputController(@PathVariable Integer id) {
        return outputService.deleteOutputService(id);
    }
}
