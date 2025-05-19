package haida.houssam.backendexam.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("PROFESSIONNEL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProfessionnel extends Credit {

    private String motif;
    private String raisonSociale;
}