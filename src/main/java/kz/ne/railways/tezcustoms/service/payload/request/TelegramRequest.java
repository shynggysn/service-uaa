package kz.ne.railways.tezcustoms.service.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TelegramRequest {

    @NotBlank
    private Long applicationNumber;

    @NotBlank
    private Long telegramNumber;

    @NotBlank
    private String expCode;

    @NotBlank
    private String lsCode;

}
