package haida.houssam.backendexam.services;

import haida.houssam.backendexam.dtos.RemboursementDTO;
import haida.houssam.backendexam.entities.Credit;
import haida.houssam.backendexam.entities.Remboursement;
import haida.houssam.backendexam.mappers.DtoMapper;
import haida.houssam.backendexam.repositories.CreditRepository;
import haida.houssam.backendexam.repositories.RemboursementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemboursementServiceImpl implements RemboursementService {

    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;
    private final DtoMapper dtoMapper;

    public RemboursementServiceImpl(RemboursementRepository remboursementRepository,
                                    CreditRepository creditRepository,
                                    DtoMapper dtoMapper) {
        this.remboursementRepository = remboursementRepository;
        this.creditRepository = creditRepository;
        this.dtoMapper = dtoMapper;
    }

//    @Override
//    public RemboursementDTO createRemboursement(RemboursementDTO dto) {
//        Credit credit = creditRepository.findById(dto.getCreditId())
//                .orElseThrow(() -> new IllegalArgumentException("Crédit non trouvé"));
//
//        Remboursement remboursement = dtoMapper.toRemboursement(dto);
//        remboursement.setCredit(credit);
//
//        Remboursement saved = remboursementRepository.save(remboursement);
//        return dtoMapper.toRemboursementDTO(saved);
//    }

    @Override
    public RemboursementDTO getRemboursementById(Long id) {
        return remboursementRepository.findById(id)
                .map(dtoMapper::toRemboursementDTO)
                .orElse(null);
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCredit(Long creditId) {
        return remboursementRepository.findByCreditId(creditId).stream()
                .map(dtoMapper::toRemboursementDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementRepository.findAll().stream()
                .map(dtoMapper::toRemboursementDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRemboursement(Long id) {
        remboursementRepository.deleteById(id);
    }
}