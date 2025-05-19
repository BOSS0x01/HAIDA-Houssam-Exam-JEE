package haida.houssam.backendexam.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("PROFESSIONNEL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProfessionnel extends Credit {

    private String motif;
    private String raisonSociale;
}