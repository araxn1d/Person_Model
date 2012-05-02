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
     * 
     * @param s 
     */
    public ConverterException(String s){
        super(s+"\n"+ConverterException.class.getName()+" Converter Exception");
    }
}
