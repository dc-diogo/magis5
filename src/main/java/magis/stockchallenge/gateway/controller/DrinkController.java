package magis.stockchallenge.gateway.controller;

import jakarta.validation.Valid;
import magis.stockchallenge.exception.DrinkTypeMismatchException;
import magis.stockchallenge.exception.DrinkVolumeExceedException;
import magis.stockchallenge.exception.InsuficientSectorCapacityException;
import magis.stockchallenge.exception.InvalidSectorIdException;
import magis.stockchallenge.gateway.model.request.DrinkRequest;
import magis.stockchallenge.gateway.model.response.DrinkHistoryResponse;
import magis.stockchallenge.gateway.model.response.DrinkHistorySimpleResponse;
import magis.stockchallenge.gateway.model.response.DrinkTypeTotalVolumeResponse;
import magis.stockchallenge.service.DrinkHistoryServiceImpl;
import magis.stockchallenge.service.DrinkInsertServiceImpl;
import magis.stockchallenge.service.DrinkSellServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drink")
public class DrinkController {

    @Autowired
    DrinkHistoryServiceImpl drinkHistoryServiceImpl;
    @Autowired
    private DrinkSellServiceImpl drinkSellServiceImpl;
    @Autowired
    private DrinkInsertServiceImpl drinkInsertServiceImpl;

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DrinkHistorySimpleResponse> insertDrink(@RequestBody DrinkRequest request) throws DrinkVolumeExceedException, InvalidSectorIdException, DrinkTypeMismatchException, InsuficientSectorCapacityException {

        DrinkHistorySimpleResponse drinkHistorySimpleResponse = drinkInsertServiceImpl.insertDrink(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(drinkHistorySimpleResponse);}

    @GetMapping(value = "/find-drinks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DrinkHistorySimpleResponse>> findDrinks(
            @RequestParam(value = "volume", required = false) Integer volume,
            @RequestParam(value = "sectorId", required = false) Integer sectorId,
            @RequestParam(value = "drinkType", required = false) String drinkType) {

        List<DrinkHistorySimpleResponse> drinks = drinkHistoryServiceImpl.getDrinksByCriteria(volume, sectorId, drinkType);
        return ResponseEntity.ok(drinks);
    }

    @GetMapping("/volume")
    public ResponseEntity<DrinkTypeTotalVolumeResponse> getTotalVolume(@RequestParam String drinkTypeName) {
        if (drinkTypeName == null || drinkTypeName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'drinkTypeName' must be provided");
        }
        DrinkTypeTotalVolumeResponse totalVolume = drinkHistoryServiceImpl.getTotalVolume(drinkTypeName);
        return ResponseEntity.ok(totalVolume);
    }

    @PostMapping(value = "/sell", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sellDrink(@RequestBody @Valid DrinkRequest request, BindingResult bindingResult) throws DrinkVolumeExceedException, InvalidSectorIdException, DrinkTypeMismatchException, InsuficientSectorCapacityException {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        drinkSellServiceImpl.sellDrink(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DrinkHistoryResponse>> getDrinkHistory(
            @RequestParam(value = "drinkTypeName", required = false) String drinkTypeName,
            @RequestParam(value = "sectorId", required = false) Integer sectorId) {

        if (drinkTypeName == null && sectorId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least one search criteria (drinkTypeName or sectorId) must be provided");
        }

        List<DrinkHistoryResponse> histories = drinkHistoryServiceImpl.getDrinkUpdateHistory(drinkTypeName, sectorId);
        return ResponseEntity.ok(histories);
    }
}
