package haida.houssam.backendexam.dtos;

import haida.houssam.backendexam.enums.TypeBien;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreditImmobilierDTO extends CreditDTO {
    private TypeBien typeBien; // Maison, Appartement, Local Commercial
}