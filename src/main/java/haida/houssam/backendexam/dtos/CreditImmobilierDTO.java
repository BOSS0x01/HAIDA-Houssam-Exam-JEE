package haida.houssam.backendexam.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreditImmobilierDTO extends CreditDTO {
    private String typeBien; // Maison, Appartement, Local Commercial
}