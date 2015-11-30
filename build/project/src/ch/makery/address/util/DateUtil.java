package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

	 /** El patrón de fecha que se utiliza para la conversión. Cambie como desee. */
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    /** La fecha formateador. */
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern(DATE_PATTERN);
    

    /*
      Devuelve la fecha dada como una cadena bien formateado. La definido anteriormente
      {@ Link DateUtil # DATE_PATTERN} se utiliza.
      
      Fechaparam la fecha que se devuelve como una cadena
     return String formateado
     **/ 
    
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    
    /*
    * Convierte una cadena en el formato de la definida {@ link DateUtil # DATE_PATTERN} 
    * A un {@ link LocalDate} objeto.
    * 
    * Devuelve null si la cadena no pudo convertir.
    * 
    *param DateString la fecha como cadena
    *return El objeto fecha o nulo si no se podría convertir
    */
    
    
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /*
    * Comprueba la cadena si se trata de una fecha válida.
    * 
    *param DateString
    *return True si la cadena es una fecha válida
    *
    */ 
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }
}
