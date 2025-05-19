package haida.houssam.backendexam.dtos;

import haida.houssam.backendexam.enums.StatutCredit;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreditDTO {
    private Long id;
    private LocalDate dateDemande;
    private StatutCredit statut;
    private LocalDate dateAcceptation;
    private double montant;
    private int dureeRemboursementMois;
    private double tauxInteret;
    private Long clientId;
    private List<RemboursementDTO> remboursements;
}