package kz.ne.railways.tezcustoms.service.model.preliminary_information;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Base {

    private String name;

    @Schema(description = "Краткое наименование")
    @NotBlank
    private String shortName;

    @Schema(description = "Код страны")
    @NotBlank
    private String сountryCode;

    @Schema(description = "Страна")
    private String countryName;

    @Schema(description = "Почтовый индекс")
    private String index = "000000";

    @NotBlank
    private Address address;
}
