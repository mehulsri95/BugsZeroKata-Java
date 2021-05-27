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

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {

    Map<Integer, String> RomanConverter = new HashMap<>();
    Integer[] values = new Integer[5];

    public IntegerToRoman() {
        RomanConverter.put(1, "I");
        RomanConverter.put(4, "IV");
        RomanConverter.put(5, "V");
        RomanConverter.put(9, "IX");
        RomanConverter.put(10, "X");

        values[0] = 10;
        values[1] = 9;
        values[2] = 5;
        values[3] = 4;
        values[4] = 1;
    }

    public String convert(int i) {
        //TODO: Refactor this to be more elegant
        int quot = 0, remain = 0, number = i;
        String answer = "";
        if (i == 0) {
            return "nulla";
        }
       for (int j = 0; j < values.length; j++)
       {
           quot = number/values[j];

           while (quot > 0)
           {
               answer += RomanConverter.get(values[j]);
               number = number - values[j];
               quot = number/values[j];
           }
       }
        return answer;
    }
}
