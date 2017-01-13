package fr.univ.blois.insee.ws.rs.provider;

import fr.univ.blois.insee.model.Region;
import fr.univ.blois.insee.ws.bean.RegionDetailDto;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author François Robert
 */
@Provider
@Produces(SiadMediaType.SIAD_FORMATTED)
public class FormattedProviderForRegion implements MessageBodyWriter<RegionDetailDto> {

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return RegionDetailDto.class.isAssignableFrom(type);
  }

  @Override
  public long getSize(RegionDetailDto region, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return 0;
  }

  @Override
  public void writeTo(RegionDetailDto region, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
    StringJoiner stringJoiner = new StringJoiner(", ", "Région: ", "\n");
    stringJoiner.add(region.getInseeId()).add(region.getName());
    try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(entityStream)) {
      outputStreamWriter.write(stringJoiner.toString());
    }
  }
}
