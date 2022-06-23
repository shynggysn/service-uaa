package kz.ne.railways.tezcustoms.service.model.preliminary_information;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Expeditor extends Base {

    private Personal personal;

    public Expeditor(String name, String shortName, String сountryCode, String countryName, String index, Address address, Personal personal) {
        super(name, shortName, сountryCode, countryName, index, address);
        this.personal = personal;
    }
}
