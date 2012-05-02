/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.convertors;

/**
 *
 * @author Jeka
 */
public class ConverterException extends Exception{
    /**
     * An exception that appears during the execution of the conversion
     * @param s - message
     */
    public ConverterException(String s){
        super(s+"\n"+ConverterException.class.getName()+" Converter Exception");
    }
}
