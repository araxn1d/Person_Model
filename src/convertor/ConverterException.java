/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convertor;

/**
 *
 * @author Jeka
 */
public class ConverterException extends Exception{
    public ConverterException(String s){
        super(s+"\n"+ConverterException.class.getName()+" Converter Exception");
    }
}
