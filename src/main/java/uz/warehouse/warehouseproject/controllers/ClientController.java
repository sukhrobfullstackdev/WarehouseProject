package uz.warehouse.warehouseproject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.warehouse.warehouseproject.entity.Client;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public Page<Client> getClientsController(@RequestParam int page) {
        return clientService.getClientsService(page);
    }

    @GetMapping(value = "/{id}")
    public Client getClientController(@PathVariable Integer id) {
        return clientService.getClientService(id);
    }

    @PostMapping
    public Message addClientController(@RequestBody Client client) {
        return clientService.addClientService(client);
    }

    @PutMapping(value = "/{id}")
    public Message editClientController(@RequestBody Client client, @PathVariable Integer id) {
        return clientService.editClientService(client, id);
    }

    @DeleteMapping(value = "/{id}")
    public Message deleteClientController(@PathVariable Integer id) {
        return clientService.deleteClientService(id);
    }
}
