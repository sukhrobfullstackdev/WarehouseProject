package uz.warehouse.warehouseproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.Currency;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.CurrencyRepository;
import uz.warehouse.warehouseproject.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {
    final CurrencyService currencyService;

    public CurrencyController(CurrencyRepository currencyRepository, CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<Currency> getCurrenciesController() {
        return currencyService.getCurrenciesService();
    }

    @GetMapping(value = "/{id}")
    public Currency getCurrencyController(@PathVariable Integer id) {
        return currencyService.getCurrencyService(id);
    }

    @PostMapping
    public Message addCurrencyController(@RequestBody Currency currency) {
        return currencyService.addCurrencyService(currency);
    }

    @PutMapping(value = "/{id}")
    public Message editCurrencyController(@RequestBody Currency currency, @PathVariable Integer id) {
        return currencyService.editCurrencyService(currency, id);
    }

    @DeleteMapping(value = "/{id}")
    public Message deleteCurrencyController(@PathVariable Integer id) {
        return currencyService.deleteCurrencyService(id);
    }
}
