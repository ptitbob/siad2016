package fr.univ.blois.insee.ws.bean.mapper;

import fr.univ.blois.insee.model.Address;
import fr.univ.blois.insee.ws.bean.AddressDto;

/**
 * @author François Robert
 */
public interface AddressMapper extends CityMapper {

  default AddressDto getAddressDto(Address address) {
    AddressDto addressDto = new AddressDto(address.getId(), address.getFloor(), address.getLine1(), address.getLine2());
    addressDto.setCity(getCityDtoWithoutZipCode(address.getCity()));
    addressDto.setZipCode(address.getZipCode() == null ? "-----" : address.getZipCode().getZipCode());
    return addressDto;
  }

}
