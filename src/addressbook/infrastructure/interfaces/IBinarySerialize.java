/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.interfaces;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Jeka
 */
public interface IBinarySerialize {

    /**
     * Deserialize object data from stream.
     *
     * @param stream - the input stream what we read byte data from
     * @return - true if method was successful,else false
     */
    public boolean Read(InputStream stream);

    /**
     * Serialize object data to stream
     *
     * @param stream -the output stream what we write byte data to
     * @return - true if method was successful,else false
     */
    public boolean Write(OutputStream stream);
}
