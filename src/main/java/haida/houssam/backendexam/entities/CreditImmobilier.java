package haida.houssam.backendexam.entities;

import haida.houssam.backendexam.enums.TypeBien;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditImmobilier extends Credit {

    private TypeBien typeBien;
}