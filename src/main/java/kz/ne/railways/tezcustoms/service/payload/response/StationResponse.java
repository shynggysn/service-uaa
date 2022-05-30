package kz.ne.railways.tezcustoms.service.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StationResponse {

    private String stationId;
    private String stationNumber;
    private String stationName;

}
