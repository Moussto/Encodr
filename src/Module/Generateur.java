/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.util.ArrayList;

/**
 * @author mouss
 */
public class Generateur {
    
    /* Pour generer un exo a besoin de :
        type de l'exo (enum)
        connaissance a tester 
    */
    
    /*Liste de fonction decodage en codage */
    
    /* Liste des codages et notion : 
        Nrz
        Nrz-i
        Manchester
        Miller
        Rz
    */


    // de la forme up-down-middle-up ?
    // Same stay like nefore


    public Generateur() {
    }

    public static String ArraytoString(ArrayList<String> array) {
        String output = "";
        for (String s : array) {
            output += s + "-";
        }
        output = output.substring(0, output.length() - 1); // remove du dernier - pour la propreté
        return output;
    }

    public static String inverse(String in) {
        if (in.equals("UP")) return "DOWN";
        if (in.equals("DOWN")) return "UP";
        return "DOWN"; //pas bien
    }

    // Non Return To ZEro
    //0-> negative | 1 -> positive
    public static String NRZ(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            if ((Character.toString(input.charAt(i))).equals("1")) {
                output += "UP-"; // down ?
            } else {
                output += "DOWN-";
            }
        }
        output = output.substring(0, output.length() - 1);
        return output;
    }

    // Non return to zero inversed
    // 0-> stay same voltage | 1-> reverse voltage
    public static String NRZi(String input) {
        ArrayList<String> out = new ArrayList<>();
        out.add("UP");
        for (int i = 0; i < input.length(); i++) {
            if ((Character.toString(input.charAt(i))).equals("0")) {
                String before = out.get(out.size() - 1);
                out.add(before);
            } else {
                String before = out.get(out.size() - 1);
                out.add(inverse(before));
            }
        }
        String output = ArraytoString(out);
        return output;
    }

    // BiPolar
    // 0-> 0 | 1-> alternate up/down
    public static String BiPol(String input) {
        ArrayList<String> out = new ArrayList<>();
        String OnePreviousState = "DOWN";
        for (int i = 0; i < input.length(); i++) {
            if ((Character.toString(input.charAt(i))).equals("0")) {
                out.add("MIDDLE");
            } else {
                out.add(inverse(OnePreviousState));
                OnePreviousState = inverse(OnePreviousState);
            }
        }
        String output = ArraytoString(out);
        return output;
    }

    // /!\ DOUBLE CLOCK TIME sux
    public static String Manchester(String input) {
        ArrayList<String> out = new ArrayList<>();
        out.add("UP");
        for (int i = 0; i < input.length(); i++) {
            if ((Character.toString(input.charAt(i))).equals("1")) {
                String before = out.get(out.size() - 1);
                if (before.equals("UP")) {
                    out.add("DOWN");
                } else {
                    out.add("UP");
                }
                out.add("UP");
            } else {
                String before = out.get(out.size() - 1);
                if (before.equals("DOWN")) {
                    out.add("UP");
                } else {
                    out.add("DOWN");
                }
                out.add("DOWN");
            }
        }
        String output = ArraytoString(out);
        return output;
    }

    public static String EncodeWith(String input, String technique) {
        if (technique.equals("NRZ")) return NRZ(input);
        if (technique.equals("NRZI")) return NRZi(input);
        if (technique.equals("BIPOLAR")) return BiPol(input);
        if (technique.equals("MANCHESTER")) return Manchester(input);
        return NRZ(input);
    }

}


