package kz.ne.railways.tezcustoms.service.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordRequest {
    @NotBlank
    private String key;

    @NotBlank
    private String password;
}
