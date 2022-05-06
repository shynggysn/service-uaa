package kz.ne.railways.tezcustoms.service.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EcpDataRequest {
    @NotBlank
    private Long documentNumber;

    @NotBlank
    private String documentType;
}
