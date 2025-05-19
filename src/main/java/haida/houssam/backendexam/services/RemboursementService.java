package haida.houssam.backendexam.services;

import haida.houssam.backendexam.dtos.RemboursementDTO;

import java.util.List;

public interface RemboursementService {
    RemboursementDTO createRemboursement(RemboursementDTO remboursementDTO);
    RemboursementDTO getRemboursementById(Long id);

    List<RemboursementDTO> getRemboursementsByCredit(Long creditId);

    List<RemboursementDTO> getAllRemboursements();
    void deleteRemboursement(Long id);
}