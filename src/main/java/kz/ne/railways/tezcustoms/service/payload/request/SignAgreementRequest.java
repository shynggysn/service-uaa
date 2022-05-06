package kz.ne.railways.tezcustoms.service.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignAgreementRequest {

    @NotBlank
    private Long applicationId;

    @NotBlank
    private Long gu12Number;
}
