/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo
 */
public class DateToStringConverter extends org.jdesktop.beansbinding.Converter<Date,String>{

    private SimpleDateFormat df;
    
    public DateToStringConverter()
    {
        df = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    @Override
    public String convertForward(Date value) {
        return df.format(value);
    }

    @Override
    public Date convertReverse(String value) {
        try
        {
            return df.parse(value);
        }catch(ParseException e)
        {
            JOptionPane.showMessageDialog(null, "Formato de data inválido");
            return null;
        }
    }
    
}
