package kz.ne.railways.tezcustoms.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import kz.ne.railways.tezcustoms.service.constants.errors.Errors;
import kz.ne.railways.tezcustoms.service.entity.TnVed;
import kz.ne.railways.tezcustoms.service.exception.FLCException;
import kz.ne.railways.tezcustoms.service.payload.response.*;
import kz.ne.railways.tezcustoms.service.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @Operation(summary = "Get stations list")
    @GetMapping("/station")
    public ResponseEntity<?> getStationList(@RequestParam("query") String query) throws FLCException {
        List<SimpleResponse> stationList = dictionaryService.getStationList(query);
        if (stationList != null)
            return ResponseEntity.ok(stationList);
        throw new FLCException(Errors.STATION_NOT_FOUND);
    }

    @Operation(summary = "Get countries list")
    @GetMapping("/country")
    public ResponseEntity<?> getCountryList(@RequestParam("query") String query) throws FLCException {
        List<SimpleResponse> countryList = dictionaryService.getCountryList(query);
        if (countryList != null)
            return ResponseEntity.ok(countryList);
        throw new FLCException(Errors.COUNTRY_NOT_FOUND);
    }

    @Operation(summary = "Get customs list")
    @GetMapping("/custom")
    public ResponseEntity<?> getCustomList(@RequestParam("query") String query) throws FLCException {
        List<SimpleResponse> customList = dictionaryService.getCustomList(query);
        if (customList != null)
            return ResponseEntity.ok(customList);
        throw new FLCException(Errors.CUSTOM_ORGAN_NOT_FOUND);
    }

    @Operation(summary = "Get transit direction code")
    @GetMapping("/transitCode")
    public ResponseEntity<?> getTransitCode() throws FLCException {
        List<SimpleResponse> customList = dictionaryService.getTransitDirectionCodes();
        if (customList != null)
            return ResponseEntity.ok(customList);
        throw new FLCException(Errors.TRANSIT_CODE_NOT_FOUND);
    }

    @Operation(summary = "Get level below goods by parent id")
    @GetMapping("/tnved")
    public ResponseEntity<List<TnVed>> getTnVedCodes(@RequestParam(required = false) Long parentId){
        return ResponseEntity.ok(dictionaryService.getTnVedCodes(parentId));
    }

    @Operation(summary = "Search goods by tnved codes or text")
    @GetMapping("/tnved-search")
    public ResponseEntity<List<TnVed>> searchTnved(@RequestParam(required = false) String query){
        return ResponseEntity.ok(dictionaryService.getSearchResults(query));
    }

    @Operation(summary = "Returns a tnved tree for particular good")
    @GetMapping("/tnved/{id}/tree")
    public ResponseEntity<List<TnVed>> getTree(@PathVariable Long id){
        return ResponseEntity.ok(dictionaryService.getTree(id));
    }
}
