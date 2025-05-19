package haida.houssam.backendexam;

import haida.houssam.backendexam.dtos.ClientDTO;
import haida.houssam.backendexam.dtos.CreditDTO;
import haida.houssam.backendexam.dtos.CreditImmobilierDTO;
import haida.houssam.backendexam.dtos.RemboursementDTO;
import haida.houssam.backendexam.enums.*;
import haida.houssam.backendexam.services.ClientService;
import haida.houssam.backendexam.services.CreditService;
import haida.houssam.backendexam.services.RemboursementService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }



    @Bean
    CommandLineRunner commandLineRunner(ClientService clientService,
                                        CreditService creditService,
                                        RemboursementService remboursementService) {
        return args -> {

            Stream.of("Hassan", "Karima", "Jamal").forEach(nom -> {
                ClientDTO client = new ClientDTO();
                client.setNom(nom);
                client.setEmail(nom.toLowerCase() + "@gmail.com");
                ClientDTO savedClient = clientService.createClient(client);

                CreditImmobilierDTO credit = new CreditImmobilierDTO();
                credit.setClientId(savedClient.getId());
                credit.setDateDemande(LocalDate.now());
                credit.setDateAcceptation(LocalDate.now().plusDays(1));
                credit.setMontant(100_000.0 + Math.random() * 50_000);
                credit.setDureeRemboursementMois(180);
                credit.setTauxInteret(3.5);
                credit.setStatut(StatutCredit.ACCEPTE);
                credit.setTypeBien(TypeBien.MAISON);

                CreditDTO savedCredit = creditService.createCredit(credit);

                for (int i = 1; i <= 3; i++) {
                    RemboursementDTO r = new RemboursementDTO();
                    r.setCreditId(savedCredit.getId());
                    r.setDate(LocalDate.now().plusMonths(i));
                    r.setMontant(2000.0 + Math.random() * 1000);
                    r.setType(TypeRemboursement.MENSUALITE);
                    remboursementService.createRemboursement(r);
                }
            });

        };
    }
}
