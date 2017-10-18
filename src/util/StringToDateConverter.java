/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rodrigo
 */
public class StringToDateConverter extends org.jdesktop.beansbinding.Converter<String,Date> {

    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public Date convertForward(String value) {
        try
        {
            return df.parse(value);
        }
        catch(ParseException e)
        {
            System.out.printf("Data invalida\n");
            return null;
        }
    }

    @Override
    public String convertReverse(Date value) {
        return df.format(value);
    }
    
}
