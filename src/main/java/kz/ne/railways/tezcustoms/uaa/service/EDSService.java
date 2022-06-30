package kz.ne.railways.tezcustoms.uaa.service;

import kz.ne.railways.tezcustoms.common.model.EDSResponse;
import kz.ne.railways.tezcustoms.common.payload.request.EDSRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "eds-service", url = "http://localhost:8083")
public interface EDSService {

    @PostMapping(path = "/api/validate", consumes = "application/json")
    EDSResponse edsValidation(@RequestBody EDSRequest request);

}
