package uz.warehouse.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.Currency;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.CurrencyRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CurrencyService {
    final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getCurrenciesService() {
        return currencyRepository.findAll();
    }

    public Currency getCurrencyService(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.orElseGet(Currency::new);
    }

    public Message addCurrencyService(Currency currency) {
        if (!Objects.equals(currency.getName(), "")) {
            if (!currencyRepository.existsByName(currency.getName())) {
                currencyRepository.save(new Currency(currency.getName(), currency.isActive()));
                return new Message(true, "The currency has been successfully added!");
            } else {
                return new Message(false, "This currency is already exists in our system!");
            }
        } else {
            return new Message(false, "Please, enter the currency name!");
        }
    }

    public Message editCurrencyService(Currency currency, Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            Currency editingCurrecy = optionalCurrency.get();
            editingCurrecy.setName(currency.getName());
            editingCurrecy.setActive(currency.isActive());
            currencyRepository.save(editingCurrecy);
            return new Message(true, "The currency has been successfully edited!");
        } else {
            return new Message(false, "The currency you want to edit has not found in our system!");
        }
    }

    public Message deleteCurrencyService(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            currencyRepository.delete(optionalCurrency.get());
            return new Message(true, "The currency has been successfully deleted!");
        } else {
            return new Message(false, "The currency which you want to delete has not been found!");
        }
    }
}
