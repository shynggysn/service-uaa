package kz.ne.railways.tezcustoms.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import kz.ne.railways.tezcustoms.service.constants.errors.Errors;
import kz.ne.railways.tezcustoms.service.exception.FLCException;
import kz.ne.railways.tezcustoms.service.payload.response.*;
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
    public ResponseEntity<?> getStationList(@RequestParam("query") String query) throws FLCException {
        List<SimpleResponse> stationList = dictionaryBean.getStationList(query);
        if (stationList != null)
            return ResponseEntity.ok(stationList);
        throw new FLCException(Errors.STATION_NOT_FOUND);
    }

    @Operation(summary = "Get countries list")
    @GetMapping("/country")
    public ResponseEntity<?> getCountryList(@RequestParam("query") String query) throws FLCException {
        List<SimpleResponse> countryList = dictionaryBean.getCountryList(query);
        if (countryList != null)
            return ResponseEntity.ok(countryList);
        throw new FLCException(Errors.COUNTRY_NOT_FOUND);
    }

    @Operation(summary = "Get customs list")
    @GetMapping("/custom")
    public ResponseEntity<?> getCustomList(@RequestParam("query") String query) throws FLCException {
        List<SimpleResponse> customList = dictionaryBean.getCustomList(query);
        if (customList != null)
            return ResponseEntity.ok(customList);
        throw new FLCException(Errors.CUSTOM_ORGAN_NOT_FOUND);
    }

    @Operation(summary = "Get transit direction code")
    @GetMapping("/transitCode")
    public ResponseEntity<?> getTransitCode() throws FLCException {
        List<SimpleResponse> customList = dictionaryBean.getTransitDirectionCodes();
        if (customList != null)
            return ResponseEntity.ok(customList);
        throw new FLCException(Errors.TRANSIT_CODE_NOT_FOUND);
    }

}
