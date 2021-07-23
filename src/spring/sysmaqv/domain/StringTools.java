/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mi PC, Marco Antonio Quenta Velasco
 */
public class StringTools {

    public enum FormatoFechas {
        dd_MM_yyyy_guion,
        dd_MM_yyyy_slash,
        yyyyMMddhhmmss,
        dd_MM_yyyy_hhmmss
        
    }

    static String FormatearFecha(Date fecha, FormatoFechas formatoFechas) {
        String formatedo = "";
        switch (formatoFechas) {
            case dd_MM_yyyy_guion:
                formatedo = new SimpleDateFormat("dd'-'MM'-'yyyy").format(fecha);
                break;
            case dd_MM_yyyy_slash:
                formatedo = new SimpleDateFormat("dd'/'MM'/'yyyy").format(fecha);
                break;
                case yyyyMMddhhmmss:
                formatedo = new SimpleDateFormat("yyyyMMddhhmmss").format(fecha);
                break;
                    case dd_MM_yyyy_hhmmss:
                formatedo = new SimpleDateFormat("dd'-'MM'-'yyyy hh:mm:ss").format(fecha);
                break;
            default:
                formatedo = fecha.toString();
                break;
                
        }
        return formatedo;
    }
    /*   public String FormatearFecha(Date fecha,FormatoFechas formato) {
     String nuevo = new String();
     //SimpleDateFormat formato = new SimpleDateFormat("dd'-'MM'-'yyyy");
     //nuevo = formato.format(fecha);
     return nuevo;
     }*/
}
