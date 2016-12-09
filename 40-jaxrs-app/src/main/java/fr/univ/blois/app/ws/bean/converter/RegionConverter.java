package fr.univ.blois.app.ws.bean.converter;

import fr.univ.blois.app.model.Region;
import fr.univ.blois.app.ws.bean.RegionDto;

/**
 * @author François Robert
 */
public class RegionConverter implements EntityDtoConverter<Region, RegionDto> {

  @Override
  public Region getEntity(RegionDto dto) {
    return null;
  }

  @Override
  public RegionDto getDto(Region entity) {
    return new RegionDto(entity.getId(), entity.getInseeId(), entity.getName());
  }

}
