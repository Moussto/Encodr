package Module;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by falcon on 5/23/16.
 */
public class Profil implements Serializable {
    private String email;
    private Map<Integer, Integer> mapCourCompletion;

    //element de gameification
    private int experience;

    Profil(String email) {
        this.email = email;
        mapCourCompletion = new HashMap<>();
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
