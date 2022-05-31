package kz.ne.railways.tezcustoms.service.payload.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomResponse {

    private String customId;
    private String customCode;
    private String customName;
}
