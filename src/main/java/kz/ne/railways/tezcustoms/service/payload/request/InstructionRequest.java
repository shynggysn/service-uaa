package kz.ne.railways.tezcustoms.service.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class InstructionRequest {

    @NotBlank
    private Long applicationNumber;

    @NotBlank
    private Long instructionNumber;

    @NotBlank
    private String cargoSender;

    @NotBlank
    private String volume;

}
