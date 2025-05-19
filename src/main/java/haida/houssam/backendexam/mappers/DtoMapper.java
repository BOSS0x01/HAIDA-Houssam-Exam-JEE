package haida.houssam.backendexam.mappers;

import haida.houssam.backendexam.dtos.*;
import haida.houssam.backendexam.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoMapper {


    public ClientDTO toClientDTO(Client client) {

        ClientDTO dto = new ClientDTO();
        BeanUtils.copyProperties(client, dto);

        if (client.getCredits() != null) {
            dto.setCredits(client.getCredits().stream()
                    .map(this::toCreditDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public Client toClient(ClientDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        BeanUtils.copyProperties(dto, client);
        return client;
    }


    public CreditDTO toCreditDTO(Credit credit) {
        if (credit == null) return null;

        if (credit instanceof CreditPersonnel) {
            return toCreditPersonnelDTO((CreditPersonnel) credit);
        } else if (credit instanceof CreditImmobilier) {
            return toCreditImmobilierDTO((CreditImmobilier) credit);
        } else if (credit instanceof CreditProfessionnel) {
            return toCreditProfessionnelDTO((CreditProfessionnel) credit);
        } else {
            CreditDTO dto = new CreditDTO();
            BeanUtils.copyProperties(credit, dto);

            if (credit.getClient() != null)
                dto.setClientId(credit.getClient().getId());

            if (credit.getRemboursements() != null) {
                dto.setRemboursements(
                        credit.getRemboursements().stream()
                                .map(this::toRemboursementDTO)
                                .collect(Collectors.toList())
                );
            }
            return dto;
        }
    }

    public Credit updateCreditFromDTO(Credit existingCredit, CreditDTO creditDTO) {
        if (existingCredit == null || creditDTO == null) {
            return existingCredit;
        }

        existingCredit.setDateAcceptation(creditDTO.getDateAcceptation());
        existingCredit.setMontant(creditDTO.getMontant());
        existingCredit.setDureeRemboursementMois(creditDTO.getDureeRemboursementMois());
        existingCredit.setStatut(creditDTO.getStatut());
        existingCredit.setTauxInteret(creditDTO.getTauxInteret());

        if (existingCredit instanceof CreditPersonnel && creditDTO instanceof CreditPersonnelDTO) {
            ((CreditPersonnel) existingCredit).setMotif(((CreditPersonnelDTO) creditDTO).getMotif());
        } else if (existingCredit instanceof CreditImmobilier && creditDTO instanceof CreditImmobilierDTO) {
            ((CreditImmobilier) existingCredit).setTypeBien(((CreditImmobilierDTO) creditDTO).getTypeBien());
        } else if (existingCredit instanceof CreditProfessionnel && creditDTO instanceof CreditProfessionnelDTO) {
            ((CreditProfessionnel) existingCredit).setMotif(((CreditProfessionnelDTO) creditDTO).getMotif());
            ((CreditProfessionnel) existingCredit).setRaisonSociale(((CreditProfessionnelDTO) creditDTO).getRaisonSociale());
        }

        return existingCredit;
    }


    public CreditPersonnelDTO toCreditPersonnelDTO(CreditPersonnel credit) {
        if (credit == null) return null;

        CreditPersonnelDTO dto = new CreditPersonnelDTO();
        BeanUtils.copyProperties(credit, dto);

        if (credit.getClient() != null)
            dto.setClientId(credit.getClient().getId());

        return dto;
    }

    public CreditImmobilierDTO toCreditImmobilierDTO(CreditImmobilier credit) {
        if (credit == null) return null;

        CreditImmobilierDTO dto = new CreditImmobilierDTO();
        BeanUtils.copyProperties(credit, dto);

        if (credit.getClient() != null)
            dto.setClientId(credit.getClient().getId());

        return dto;
    }

    public CreditProfessionnelDTO toCreditProfessionnelDTO(CreditProfessionnel credit) {
        if (credit == null) return null;

        CreditProfessionnelDTO dto = new CreditProfessionnelDTO();
        BeanUtils.copyProperties(credit, dto);

        if (credit.getClient() != null)
            dto.setClientId(credit.getClient().getId());

        return dto;
    }


    public CreditPersonnel toCreditPersonnel(CreditPersonnelDTO dto, Client client) {
        if (dto == null) return null;

        CreditPersonnel credit = new CreditPersonnel();
        BeanUtils.copyProperties(dto, credit);
        credit.setClient(client);
        return credit;
    }

    public CreditImmobilier toCreditImmobilier(CreditImmobilierDTO dto, Client client) {
        if (dto == null) return null;

        CreditImmobilier credit = new CreditImmobilier();
        BeanUtils.copyProperties(dto, credit);
        credit.setClient(client);
        return credit;
    }

    public CreditProfessionnel toCreditProfessionnel(CreditProfessionnelDTO dto, Client client) {
        if (dto == null) return null;

        CreditProfessionnel credit = new CreditProfessionnel();
        BeanUtils.copyProperties(dto, credit);
        credit.setClient(client);
        return credit;
    }


    public RemboursementDTO toRemboursementDTO(Remboursement remboursement) {
        if (remboursement == null) return null;

        RemboursementDTO dto = new RemboursementDTO();
        BeanUtils.copyProperties(remboursement, dto);

        if (remboursement.getCredit() != null)
            dto.setCreditId(remboursement.getCredit().getId());

        return dto;
    }

    public Remboursement toRemboursement(RemboursementDTO dto, Credit credit) {
        if (dto == null) return null;

        Remboursement remboursement = new Remboursement();
        BeanUtils.copyProperties(dto, remboursement);
        remboursement.setCredit(credit);

        return remboursement;
    }
}