package haida.houssam.backendexam.services;

import haida.houssam.backendexam.dtos.*;
import haida.houssam.backendexam.entities.*;
import haida.houssam.backendexam.mappers.DtoMapper;
import haida.houssam.backendexam.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final DtoMapper dtoMapper;

    public ClientServiceImpl(ClientRepository clientRepository, DtoMapper dtoMapper) {
        this.clientRepository = clientRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = dtoMapper.toClient(clientDTO);
        Client saved = clientRepository.save(client);
        return dtoMapper.toClientDTO(saved);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(dtoMapper::toClientDTO)
                .orElse(null);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(dtoMapper::toClientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        return clientRepository.findById(id).map(existingClient -> {
            existingClient.setNom(clientDTO.getNom());
            existingClient.setEmail(clientDTO.getEmail());
            Client updated = clientRepository.save(existingClient);
            return dtoMapper.toClientDTO(updated);
        }).orElse(null);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}