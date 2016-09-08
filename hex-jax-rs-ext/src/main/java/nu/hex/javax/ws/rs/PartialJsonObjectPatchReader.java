package nu.hex.javax.ws.rs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 * Created 2016-sep-08
 *
 * Code from: https://craftsmen.nl/index.php/patching-jax-rs-3/
 *
 * @author hl
 */
@Provider
public class PartialJsonObjectPatchReader implements MessageBodyReader<ObjectPatch> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public boolean isReadable(Class<?> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType) {
        return ObjectPatch.class == type && MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType);
    }

    @Override
    public ObjectPatch readFrom(Class<ObjectPatch> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException, WebApplicationException {
        JsonNode patch = OBJECT_MAPPER.readTree(entityStream);
        return new PartialJsonObjectPatch(OBJECT_MAPPER, patch);
    }
}
