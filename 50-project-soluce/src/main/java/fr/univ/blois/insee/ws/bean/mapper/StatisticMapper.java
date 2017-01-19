package fr.univ.blois.insee.ws.bean.mapper;

import fr.univ.blois.insee.ws.bean.StatisticDto;

import java.net.URI;

/**
 * @author François Robert
 */
public interface StatisticMapper {

  default StatisticDto fillStatisticWith(String target, URI targetURI, Long countIn) {
    StatisticDto statisticDto = new StatisticDto();
    statisticDto.setTarget(target);
    statisticDto.setTargetUrl(targetURI);
    statisticDto.setPersonCountIn(countIn);
    return statisticDto;
  }


}
