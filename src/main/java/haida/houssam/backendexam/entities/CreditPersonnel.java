package haida.houssam.backendexam.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("PERSONNEL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditPersonnel extends Credit {

    private String motif;
}