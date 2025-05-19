package haida.houssam.backendexam;

import haida.houssam.backendexam.dtos.*;
import haida.houssam.backendexam.enums.*;
import haida.houssam.backendexam.services.ClientService;
import haida.houssam.backendexam.services.CreditService;
import haida.houssam.backendexam.services.RemboursementService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }



    @Bean
    CommandLineRunner testAllEntities(ClientService clientService,
                                      CreditService creditService,
                                      RemboursementService remboursementService) {
        return args -> {

            Stream.of("Wafae", "Houssam", "Imran").forEach(nom -> {
                // Create client
                ClientDTO client = new ClientDTO();
                client.setNom(nom);
                client.setEmail(nom.toLowerCase() + "@gmail.com");
                ClientDTO savedClient = clientService.createClient(client);
                System.out.println("Client créé: " + savedClient.getNom());

                // 1. Crédit Personnel
                CreditPersonnelDTO creditPersonnel = new CreditPersonnelDTO();
                creditPersonnel.setClientId(savedClient.getId());
                creditPersonnel.setDateDemande(LocalDate.now());
                creditPersonnel.setDateAcceptation(LocalDate.now().plusDays(1));
                creditPersonnel.setMontant(15000.0);
                creditPersonnel.setDureeRemboursementMois(36);
                creditPersonnel.setTauxInteret(4.2);
                creditPersonnel.setStatut(StatutCredit.ACCEPTE);
                creditPersonnel.setMotif("Travaux maison");
                CreditDTO savedPersonnel = creditService.createCredit(creditPersonnel);

                // 2. Crédit Immobilier
                CreditImmobilierDTO creditImmobilier = new CreditImmobilierDTO();
                creditImmobilier.setClientId(savedClient.getId());
                creditImmobilier.setDateDemande(LocalDate.now());
                creditImmobilier.setDateAcceptation(LocalDate.now().plusDays(2));
                creditImmobilier.setMontant(120000.0);
                creditImmobilier.setDureeRemboursementMois(240);
                creditImmobilier.setTauxInteret(2.8);
                creditImmobilier.setStatut(StatutCredit.ACCEPTE);
                creditImmobilier.setTypeBien(TypeBien.APPARTEMENT);
                CreditDTO savedImmobilier = creditService.createCredit(creditImmobilier);

                // 3. Crédit Professionnel
                CreditProfessionnelDTO creditPro = new CreditProfessionnelDTO();
                creditPro.setClientId(savedClient.getId());
                creditPro.setDateDemande(LocalDate.now());
                creditPro.setDateAcceptation(LocalDate.now().plusDays(3));
                creditPro.setMontant(50000.0);
                creditPro.setDureeRemboursementMois(60);
                creditPro.setTauxInteret(3.9);
                creditPro.setStatut(StatutCredit.ACCEPTE);
                creditPro.setMotif("Achat équipements");
                creditPro.setRaisonSociale("Société " + nom.toUpperCase());
                CreditDTO savedPro = creditService.createCredit(creditPro);

                // Remboursements pour chaque crédit
                List<CreditDTO> allCredits = List.of(savedPersonnel, savedImmobilier, savedPro);
                for (CreditDTO credit : allCredits) {
                    for (int i = 1; i <= 2; i++) {
                        RemboursementDTO remboursement = new RemboursementDTO();
                        remboursement.setCreditId(credit.getId());
                        remboursement.setDate(LocalDate.now().plusMonths(i));
                        remboursement.setMontant(1000.0 + Math.random() * 500);
                        remboursement.setType(i % 2 == 0 ? TypeRemboursement.MENSUALITE : TypeRemboursement.REMBOURSEMENT_ANTICIPE);
                        remboursementService.createRemboursement(remboursement);
                    }
                }

            });

        };
    }
}
