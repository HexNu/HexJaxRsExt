package nu.hex.javax.ws.rs;

import nu.hex.javax.ws.rs.exception.ObjectPatchException;

/**
 * Created 2016-sep-08
 *
 * Code from: https://craftsmen.nl/index.php/patching-jax-rs-3/
 *
 * @author hl
 */
public interface ObjectPatch {

    <T> T apply(T target) throws ObjectPatchException;
}
