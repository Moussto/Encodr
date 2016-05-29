/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author mouss
 */
public class Cour {
    int id;
    String name;
    String description;
    ArrayList<Integer> requirements;
    ArrayList<String> tags;
    // Conteneur de pages de cours
    
   

    public Cour(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
}
