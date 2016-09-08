package nu.hex.javax.ws.rs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.IOException;
import nu.hex.javax.ws.rs.exception.ObjectPatchException;

/**
 * Created 2016-sep-08
 *
 * Code from: https://craftsmen.nl/index.php/patching-jax-rs-3/
 *
 * @author hl
 */
public class PartialJsonObjectPatch implements ObjectPatch {

    private final ObjectMapper objectMapper;
    private final JsonNode patch;

    public PartialJsonObjectPatch(ObjectMapper objectMapper, JsonNode patch) {
        this.objectMapper = objectMapper;
        this.patch = patch;
    }

    @Override
    public <T> T apply(T target) throws ObjectPatchException {
        ObjectReader reader = objectMapper.readerForUpdating(target);
        try {
            return reader.readValue(patch);
        } catch (IOException e) {
            throw new ObjectPatchException(e);
        }
    }
}
