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
    public Object read(InputStream stream);
    public boolean write(OutputStream stream);
}
