package com.restaurant.grandmasfood.mapper.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Formatters {

    private static final DecimalFormat DECIMAL_FORMAT;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DECIMAL_FORMAT =  new DecimalFormat("#.##", symbols);
    }

    public static double decimalFormat(double num){
        return Double.parseDouble(DECIMAL_FORMAT.format(num));
    }


}
