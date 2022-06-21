package kz.ne.railways.tezcustoms.service.model.preliminary_information;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Expeditor extends Base {

    private Personal personal;

    public Expeditor(@NotBlank String name, @NotBlank String shortName, @NotBlank String сountryCode, String countryName, String index, @NotBlank Address address, Personal personal) {
        super(name, shortName, сountryCode, countryName, index, address);
        this.personal = personal;
    }
}
