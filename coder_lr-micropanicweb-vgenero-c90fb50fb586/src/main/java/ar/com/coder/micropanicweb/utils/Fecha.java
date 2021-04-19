/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author lgaray
 */
public class Fecha {

    public static String validarFecha(String fecha) {
        String[] f = fecha.split("-");
        if (f.length != 3) {
            return "false";
        }
        int year = Integer.parseUnsignedInt(f[2]);// año
        int month = Integer.parseUnsignedInt(f[1]);// mes [1,...,12]
        int dayOfMonth = Integer.parseUnsignedInt(f[0]);// día [1,...,31]

        if (year < 1900) {
            return "false";
        }
        try {
            LocalDate today = LocalDate.of(year, month, dayOfMonth);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return formatter.format(today);
        } catch (Exception e) {
//throw new IllegalArgumentException("Fecha Invalida");
            return "false";
        }
    }

    public static Date getFecha(String fecha) {
        String f = Fecha.validarFecha(fecha);
        if (f != "false") {
            return null;
        } else {
            String[] date = f.split("/");
            int anio = Integer.parseUnsignedInt(date[2]);// año
            int mes = Integer.parseUnsignedInt(date[1]);// mes [1,...,12]
            int dia = Integer.parseUnsignedInt(date[0]);// día [1,...,31]
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, dia);
            c.set(Calendar.MONTH, mes);
            c.set(Calendar.YEAR, anio);
            return c.getTime();
        }
    }

    public static ArrayList getMeses() {
        ArrayList<Mes> meses = new ArrayList();
        meses.add(new Mes(1, "January", "Enero"));
        meses.add(new Mes(2, "february", "Febrero"));
        meses.add(new Mes(3, "March", "marzo"));
        meses.add(new Mes(4, "April", "abril"));
        meses.add(new Mes(5, "may", "mayo"));
        meses.add(new Mes(6, "June", "junio"));
        meses.add(new Mes(7, "July", "julio"));
        meses.add(new Mes(8, "August", "agosto"));
        meses.add(new Mes(9, "September", "septiembre"));
        meses.add(new Mes(10, "October", "octubre"));
        meses.add(new Mes(11, "November", "noviembre"));
        meses.add(new Mes(12, "December", "diciembre"));
        return meses;
    }
    public static void completarMeses(String inicio,String fin){
      //  Profilo resultado = null;
    //return resultado;
    }
}
