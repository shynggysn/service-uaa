package kz.ne.railways.tezcustoms.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import kz.ne.railways.tezcustoms.service.payload.response.CountryResponse;
import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;
import kz.ne.railways.tezcustoms.service.payload.response.StationResponse;
import kz.ne.railways.tezcustoms.service.service.bean.DictionaryBeanLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    DictionaryBeanLocal dictionaryBean;

    @Operation(summary = "Get stations list")
    @GetMapping("/station")
    public ResponseEntity<?> getStationList(@RequestParam("query") String query) {
        List<StationResponse> stationList = dictionaryBean.getStationList(query);
        if (stationList != null)
            return ResponseEntity.ok(stationList);
        return ResponseEntity.badRequest().body(new MessageResponse("could not find any station"));
    }

    @Operation(summary = "Get countries list")
    @GetMapping("/country")
    public ResponseEntity<?> getCountryList(@RequestParam("query") String query) {
        List<CountryResponse> countryList = dictionaryBean.getCountryList(query);
        if (countryList != null)
            return ResponseEntity.ok(countryList);
        return ResponseEntity.badRequest().body(new MessageResponse("could not find any country"));
    }
}
