package codingdojo.romannumerals;

/*
* The Romans were a clever bunch. They conquered most of Europe and ruled it for hundreds of years.
* They invented concrete and straight roads and even bikinis [1]. One thing they never discovered though
* was the number zero. This made writing and dating extensive histories of their exploits slightly more
* challenging, but the system of numbers they came up with is still in use today.
*
* For example the BBC uses Roman numerals to date their programmes.

* The Romans wrote numbers using letters : I, V, X, L, C, D, M.
* (notice these letters have lots of straight lines and are hence easy to hack into stone tablets)
*
* */

/*
* Assumptions
* 1. Input cannot be more than 3000
* 2. Only work with upper-case letters 3 = III and not iii
* 3.
* */

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class IntegerToRoman {

    Map<Integer, String> RomanConverter = new TreeMap<>(Collections.reverseOrder());

    public IntegerToRoman() {
        RomanConverter.put(1, "I");
        RomanConverter.put(4, "IV");
        RomanConverter.put(5, "V");
        RomanConverter.put(9, "IX");
        RomanConverter.put(10, "X");
        RomanConverter.put(40, "XL");
        RomanConverter.put(50, "L");
        RomanConverter.put(90, "XC");
        RomanConverter.put(100, "C");
        RomanConverter.put(400, "CD");
        RomanConverter.put(500, "D");
        RomanConverter.put(900, "CM");
        RomanConverter.put(1000, "M");
    }

    public String convert(int i) {
        //TODO: reflect, improve this. try reverse.
        if (i<0) {
            throw new NumberFormatException("No negatives allowed");
        }

        if (i>3000) {
            throw new NumberFormatException("No negatives allowed");
        }

        int quot;
        int number = i;
        String answer = "";
        if (i == 0) {
            return "nulla";
        }
       for (int intValue:RomanConverter.keySet())
       {
           quot = number/intValue;

           while (quot > 0)
           {
               answer += RomanConverter.get(intValue);
               number = number - intValue;
               quot = number/intValue;
           }
       }
        return answer;
    }
}
