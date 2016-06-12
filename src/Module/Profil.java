package Module;

import Model.CodageType;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by falcon on 5/23/16.
 */
public class Profil implements Serializable {
    private String email;
    //private Map<Integer, Integer> mapCourCompletion;

    private Map<CodageType, Integer> bonnesReponses;
    private Map<CodageType, Integer> mauvaisesReponses;

    //element de gameification
    private int experience;

    public Profil(String email) {
        this.email = email;
        //mapCourCompletion = new HashMap<>();
        bonnesReponses = new HashMap<>();
        mauvaisesReponses = new HashMap<>();

        for(CodageType c : CodageType.values()) {
            bonnesReponses.put(c, 0);
            mauvaisesReponses.put(c, 0);
        }
    }

    public void addBonneReponse(CodageType c) {
        //Finalement c'est pas des references les integer :/
        /*Integer integer = bonnesReponses.get(c);
        integer++;*/
        bonnesReponses.put(c, bonnesReponses.get(c) + 1);

        experience += 100;
    }

    public void addMauvaiseReponse(CodageType c) {
        /*Integer integer = mauvaisesReponses.get(c);
        integer++;*/
        mauvaisesReponses.put(c, mauvaisesReponses.get(c) + 1);

        experience += 15;
    }

    public float getRatioBonneMauvaiseReponse(CodageType c) {
        Integer br = bonnesReponses.get(c);
        Integer mr = mauvaisesReponses.get(c);

        if(mr.equals(0))
            return -1;
        return br / mr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<CodageType, Integer> getMauvaisesReponses() {
        return mauvaisesReponses;
    }

    public void setMauvaisesReponses(Map<CodageType, Integer> mauvaisesReponses) {
        this.mauvaisesReponses = mauvaisesReponses;
    }

    public Map<CodageType, Integer> getBonnesReponses() {
        return bonnesReponses;
    }

    public void setBonnesReponses(Map<CodageType, Integer> bonnesReponses) {
        this.bonnesReponses = bonnesReponses;
    }

    /*public Map<Integer, Integer> getMapCourCompletion() {
        return mapCourCompletion;
    }*/

    /*public void setMapCourCompletion(Map<Integer, Integer> mapCourCompletion) {
        this.mapCourCompletion = mapCourCompletion;
    }*/

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void save(String filename) {
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream file = new FileOutputStream(filename);
            oos = new ObjectOutputStream(file);
            oos.writeObject(this);
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static Profil load(String filename) {
        ObjectInputStream ois = null;
        Profil profil = null;

        try {
            final FileInputStream file = new FileInputStream(filename);
            ois = new ObjectInputStream(file);
            profil = (Profil) ois.readObject();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return profil;
    }
}
