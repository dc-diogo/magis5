package magis.stockchallenge.gateway.controller;

import jakarta.validation.Valid;
import magis.stockchallenge.domain.Sector;
import magis.stockchallenge.gateway.model.request.SectorRequest;
import magis.stockchallenge.gateway.model.response.SectorAvailableResponse;
import magis.stockchallenge.gateway.repository.SectorRepository;
import magis.stockchallenge.service.SectorServiceImpl;
import magis.stockchallenge.service.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sectors")
public class SectorController {

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private SectorServiceImpl sectorServiceImpl;

    @Autowired
    private StorageServiceImpl storageServiceImpl;

    @GetMapping
    public ResponseEntity<List<Sector>> getAllSectors() {
        List<Sector> sectors = sectorRepository.findAll();
        return ResponseEntity.ok(sectors);
    }

    @GetMapping(value = "/check-storage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SectorAvailableResponse>> getAvailableVolume(
            @RequestParam(value = "drinkTypeName") String drinkTypeName,
            @RequestParam(value = "volume") Integer volume) {

        if (drinkTypeName == null || drinkTypeName.isEmpty() || volume == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'drinkTypeName' and 'volume' must be provided");
        }

        List<SectorAvailableResponse> sectors = storageServiceImpl.checkStorageForVolumeAndDrinkType(drinkTypeName, volume);

        return ResponseEntity.ok(sectors);
    }
}