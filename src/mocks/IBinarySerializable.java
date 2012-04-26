/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mocks;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Jeka
 */
public interface IBinarySerializable {
    public Object read(OutputStream stream);
    public boolean write(InputStream stream);
}
