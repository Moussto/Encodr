package Model;

import Tools.XMLTools;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by falcon on 6/9/16.
 */
public class QCMManager {
    private static String saveFilename = "qcm.xml";
    public static QCMContainer container = new QCMContainer();



    public QCMManager() {}

    private static void loadQuestions() {
        try {
            container.qcm = (HashMap<List<CodageType>, List<QCM>>) XMLTools.decodeFromFile(saveFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveQuestions() {
        try {
            XMLTools.encodeToFile(container, saveFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<QCM> getQCMAbout(List<CodageType> codageType) {
        if(container.qcm == null) {
            loadQuestions();
        }
        return container.qcm.get(codageType);
    }

    public static void setQcm(HashMap<List<CodageType>, List<QCM>> qcm) {
        container.setQcm(qcm);
    }
}

