package magis.stockchallenge.service;

import magis.stockchallenge.domain.DrinkType;
import magis.stockchallenge.domain.Sector;
import magis.stockchallenge.gateway.model.response.SectorAvailableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl {
    
    @Autowired
    SectorServiceImpl sectorService;
    
    @Autowired
    DrinkTypeServiceImpl drinkTypeService;
    
    public List<SectorAvailableResponse> checkStorageForVolumeAndDrinkType(String drinkTypeName, Integer volume) {

        DrinkType drinkType = drinkTypeService.validateAndRetrieveDrinkType(drinkTypeName);

        List<Sector> filteredSectors = getEmptySectorsAndSameDrinkType(drinkTypeName, volume);
        List<Sector> sectorsWithVolumeAvailable = setAvailableVolumeForEmptySectors(filteredSectors, drinkType);
        List<SectorAvailableResponse> filteredSectorsAvailable = mapToSectorAvailableResponse(sectorsWithVolumeAvailable, drinkType);
        filteredSectorsAvailable = getSectorWithCapacityOnly(volume, filteredSectorsAvailable);

        return filteredSectorsAvailable;
        
    }

    private List<Sector> getEmptySectorsAndSameDrinkType(String drinkTypeName, Integer volume) {
        List<Sector> filteredSectors = sectorService.getAllSectors()
                .stream()
                .filter(sector -> {
                    if (sector.getDrinkType() != null) {
                        return sector.getDrinkType().getName().equalsIgnoreCase(drinkTypeName);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
        return filteredSectors;
    }

    private List<Sector> setAvailableVolumeForEmptySectors(List<Sector> filteredSectors, DrinkType drinkType) {
        return filteredSectors
                .stream()
                .map(sector -> {
                    if (sector.getDrinkType() == null) {
                        sector.setAvailableVolume(drinkType.getVolume());
                    }
                    return sector;
                })
                .collect(Collectors.toList());
    }

    private List<SectorAvailableResponse> mapToSectorAvailableResponse(List<Sector> sectorsWithVolume, DrinkType drinkType) {
        return sectorsWithVolume
                .stream()
                .map(sector -> new SectorAvailableResponse(
                        sector.getId().intValue(),
                        sector.getName(),
                        sector.getAvailableVolume()))
                .collect(Collectors.toList());
    }

    private List<SectorAvailableResponse> getSectorWithCapacityOnly(Integer volume, List<SectorAvailableResponse> filteredSectorsAvailable) {
        filteredSectorsAvailable = filteredSectorsAvailable
                .stream()
                .filter(response -> response.getAvailableVolume() >= volume)
                .collect(Collectors.toList());
        return filteredSectorsAvailable;
    }
}
