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
public interface IBinarySerializable {

    /**
     *
     * @param stream
     * @return
     */
    public Object Read(InputStream stream);

    /**
     *
     * @param stream
     * @return
     */
    public boolean Write(OutputStream stream);
}
