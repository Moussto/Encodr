package Module;

import java.io.*;
import java.util.HashMap;

/**
 * Created by falcon on 6/9/16.
 */

public class ProfileManager implements Serializable {
    private static String saveFilename = "profiles.prf";
    private static HashMap<String, Profil> profiles = load(saveFilename);

    public static HashMap<String, Profil> getProfiles() {
        return profiles;
    }

    public static Profil getProfile(String login) {
        if(profiles == null) {
            profiles = new HashMap<>();
            return null;
        }
        return profiles.get(login);
    }

    public static void addProfile(Profil profil) {
        if(profiles == null) {
            profiles = new HashMap<>();
        }
        profiles.put(profil.getEmail(), profil);
    }

    public static void save(String filename) {
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream file = new FileOutputStream(filename);
            oos = new ObjectOutputStream(file);
            oos.writeObject(profiles);
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

    private static HashMap<String, Profil> load(String filename) {
        ObjectInputStream ois = null;
        profiles = null;

        try {
            final FileInputStream file = new FileInputStream(filename);
            ois = new ObjectInputStream(file);
            profiles = (HashMap<String, Profil>) ois.readObject();
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
        return profiles;
    }
}
