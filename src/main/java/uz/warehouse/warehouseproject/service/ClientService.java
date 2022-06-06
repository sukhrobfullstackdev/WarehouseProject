package uz.warehouse.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.warehouse.warehouseproject.entity.Client;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {
    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> getClientsService(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return clientRepository.findAll(pageable);
    }

    public Client getClientService(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElseGet(Client::new);
    }

    public Message addClientService(Client client) {
        boolean exists = clientRepository.existsByNameAndPhoneNumber(client.getName(), client.getPhoneNumber());
        if (exists) {
            return new Message(false, "This client is already exists in our system!");
        } else {
            clientRepository.save(client);
            return new Message(true, "The client has been successfully saved!");
        }
    }

    public Message editClientService(Client client, Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client editingClient = optionalClient.get();
            editingClient.setName(client.getName());
            editingClient.setPhoneNumber(client.getPhoneNumber());
            clientRepository.save(editingClient);
            return new Message(true, "The client has been successfully edited!");
        } else {
            return new Message(false, "The client you want to edit has not been found in our system!");
        }
    }

    public Message deleteClientService(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            clientRepository.delete(optionalClient.get());
            return new Message(true, "The client has been successfully deleted!");
        } else {
            return new Message(false, "The client you want to delete has not been found!");
        }
    }
}
