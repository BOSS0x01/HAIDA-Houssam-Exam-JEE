package haida.houssam.backendexam.services;

import haida.houssam.backendexam.dtos.*;
import haida.houssam.backendexam.entities.*;
import haida.houssam.backendexam.enums.StatutCredit;
import haida.houssam.backendexam.mappers.DtoMapper;
import haida.houssam.backendexam.repositories.ClientRepository;
import haida.houssam.backendexam.repositories.CreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final DtoMapper dtoMapper;

    public CreditServiceImpl(CreditRepository creditRepository, ClientRepository clientRepository, DtoMapper dtoMapper) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public CreditDTO createCredit(CreditDTO creditDTO) {
        if (creditDTO.getClientId() == null) {
            throw new IllegalArgumentException("ClientId obligatoire");
        }
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client non trouvé"));

        Credit credit;
        if (creditDTO instanceof CreditPersonnelDTO) {
            credit = dtoMapper.toCreditPersonnel((CreditPersonnelDTO) creditDTO, client);
        } else if (creditDTO instanceof CreditImmobilierDTO) {
            credit = dtoMapper.toCreditImmobilier((CreditImmobilierDTO) creditDTO, client);
        } else if (creditDTO instanceof CreditProfessionnelDTO) {
            credit = dtoMapper.toCreditProfessionnel((CreditProfessionnelDTO) creditDTO, client);
        } else {

            throw  new RuntimeException("Credit type not supported");
        }

        credit.setStatut(StatutCredit.EN_COURS); // statut par défaut

        Credit saved = creditRepository.save(credit);
        return dtoMapper.toCreditDTO(saved);
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        return creditRepository.findById(id)
                .map(dtoMapper::toCreditDTO)
                .orElse(null);
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll().stream()
                .map(dtoMapper::toCreditDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO updateCredit(Long id, CreditDTO creditDTO) {
        return creditRepository.findById(id).map(existingCredit -> {
            Credit updated = dtoMapper.updateCreditFromDTO(existingCredit, creditDTO);
            updated = creditRepository.save(updated);
            return dtoMapper.toCreditDTO(updated);
        }).orElse(null);
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }
}