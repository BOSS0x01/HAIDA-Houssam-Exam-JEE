package haida.houssam.backendexam.services;

import haida.houssam.backendexam.dtos.CreditDTO;
import haida.houssam.backendexam.dtos.CreditImmobilierDTO;
import haida.houssam.backendexam.dtos.CreditPersonnelDTO;
import haida.houssam.backendexam.dtos.CreditProfessionnelDTO;

import java.util.List;

public interface CreditService {
    List<CreditDTO> getAllCredits();

    CreditDTO createCredit(CreditDTO creditDTO);

    CreditDTO getCreditById(Long id);

    CreditDTO updateCredit(Long id, CreditDTO creditDTO);

    void deleteCredit(Long id);
}