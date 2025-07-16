package webdriver.helperstatic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import webdriver.globalstatic.DriverStatic;

public class JavaStatic extends DriverStatic {


    public void compareDoubleValues(double actualValue, double expectedValue) {
        System.out.println("The Actual Value is: " + actualValue);
        System.out.println("The Expected Value is: " + expectedValue);
        if (actualValue == expectedValue) {
            System.out.println("The expected Total Change value is equal to the actual Total Change value.");
        } else {
            System.out.println("ERROR: The expected Total Change value is not equal to the actual Total Change value.");
        }
    }

    public double convertStringToDoubleWithTwoDecimals(String string){
        double myDouble = Double.parseDouble(string);
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        if(myDouble >= 0){
            nf.setRoundingMode(RoundingMode.FLOOR);
        } else {
            nf.setRoundingMode(RoundingMode.CEILING);
        }
        String formattedString = nf.format(myDouble);
        double formattedDouble = Double.parseDouble(formattedString);
        return formattedDouble;
    }

    public double roundDoubleToTwoDecimals(double dValue){
        double formattedDecimalValue = (double)Math.round(dValue * 100000d)/100000d;
        return formattedDecimalValue;
    }

    public double roundDouble(Double vDouble, int significantDigits) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(significantDigits);
        if (vDouble >= 0) {
            nf.setRoundingMode(RoundingMode.FLOOR);
        } else {
            nf.setRoundingMode(RoundingMode.CEILING);
        }
        String formattedString = nf.format(vDouble);
        double formattedDouble = Double.parseDouble(formattedString);
        return formattedDouble;
    }

    public double truncateDouble(Double startDouble, int significantDigits) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(significantDigits);
        String formattedString = nf.format(startDouble);
        double formattedDouble = Double.parseDouble(formattedString);
        return formattedDouble;
    }

    public String getNumbersFromStringWithRegex(String string) throws InterruptedException {
        Pattern p = Pattern.compile("\\d+"); //regex pattern
        Matcher m = p.matcher(string);    //string to search
        String numbers = null;
        while(m.find()) {
            numbers = m.group();
        }
        return numbers;
    }


    /** Returns a random number up to, but not including, the user set upperLimit. Starts with and includes the value 1. */
    public static int javaGetRandomNumber(int upperLimit, boolean printout) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(upperLimit);
        if(randomNumber == 0){
            randomNumber = 1;
        }
        if(printout){
            System.out.println("Generated random number: " + randomNumber);
        }
        return randomNumber;
    }

    public static double getRandomDoubleAndSetDecimalPlaces(double min, double max, int decimalPlaces, boolean printout) {
        double d = (Math.random() * ((max - min) + 1)) + min;
        BigDecimal bd = new BigDecimal(d).setScale(decimalPlaces, RoundingMode.DOWN);
        double rounded = bd.doubleValue();
        if (printout) {
            System.out.println("Original random value: " + d);
            System.out.println("Original value - after setting decimal places and rounding down:" + rounded);
        }
        return rounded;
    }

    public static String getRandomStringAndSetDecimalPlaces(double min, double max, int decimalPlaces, boolean printout) {
        String random = Double.toString(getRandomDoubleAndSetDecimalPlaces(min, max, decimalPlaces, printout));
        return random;
    }

    public static String getRandomStringWithNoDecimalPlaces(double min, double max, int decimalPlaces, boolean printout) {
        DecimalFormat df = new DecimalFormat("#");
        Double dbl = getRandomDoubleAndSetDecimalPlaces(min, max, decimalPlaces, printout);
        String random = df.format(dbl);
        return random;
    }



    public static double getRandomDoubleAndSetDecimalPlaces(double min, double max, int decimalPlaces, String fieldLabel, boolean printout) {
        double d = (Math.random() * ((max - min) + 1)) + min;
        BigDecimal bd = new BigDecimal(d).setScale(decimalPlaces, RoundingMode.DOWN);
        double rounded = bd.doubleValue();
        if (printout) {
            System.out.println("Initial generated random value for " + fieldLabel + ": " + d);
            System.out.println("Final random value for " + fieldLabel + " after setting decimal places and rounding down: " + rounded);
        }
        return rounded;
    }

    public static String getRandomStringAndSetDecimalPlaces(double min, double max, int decimalPlaces, String fieldLabel, boolean printout) {
        String random = Double.toString(getRandomDoubleAndSetDecimalPlaces(min, max, decimalPlaces, fieldLabel, printout));
        return random;
    }

    public static String getRandomStringWithNoDecimalPlaces(double min, double max, int decimalPlaces, String fieldLabel, boolean printout) {
        DecimalFormat df = new DecimalFormat("#");
        Double dbl = getRandomDoubleAndSetDecimalPlaces(min, max, decimalPlaces, fieldLabel, printout);
        String random = df.format(dbl);
        return random;
    }

    public String javaGetCurrentDate(String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        return dtf.format(now);
    }
}
