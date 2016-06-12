package Module;

import sun.nio.ch.FileKey;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by falcon on 6/9/16.
 */

public class ProfileManager implements Serializable {
    private static String saveFilename = "./Ressources/profiles.prf";
    private static HashMap<String, Profil> profiles = load(saveFilename);
    private static Profil currentProfile = null;

    public static HashMap<String, Profil> getProfiles() {
        return profiles;
    }

    private static Profil createAndAddNewProfile(String login)
    {
        Profil profil = new Profil(login);
        profiles.put(login, profil);
        return profil;
    }

    public static Profil getCurrentProfile() {
        return currentProfile;
    }

    public static void setCurrentProfile(Profil currentProfile) {
        ProfileManager.currentProfile = currentProfile;
    }

    public static Profil getProfile(String login) {
        if(profiles == null) {
            profiles = new HashMap<>();
            return createAndAddNewProfile(login);
        }
        Profil p = profiles.get(login);
        if(p != null)
            return p;
        return createAndAddNewProfile(login);
    }

    public static void addProfile(Profil profil) {
        if(profiles == null) {
            profiles = new HashMap<>();
        }
        profiles.put(profil.getEmail(), profil);
    }

    public static void save() {

        ObjectOutputStream oos = null;

        try {

            final FileOutputStream file = new FileOutputStream(saveFilename);
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
         //   final InputStream file = ProfileManager.class.getResourceAsStream("./Ressources/profiles.prf");
            final FileInputStream file = new FileInputStream(saveFilename);
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
