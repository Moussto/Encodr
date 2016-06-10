package sample;

import Model.CodageType;
import Model.QCM;
import Model.QCMManager;
import Tools.XMLTools;
import jdk.nashorn.internal.objects.NativeArray;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by falcon on 6/9/16.
 */
public class QCMSerializationTest {
    public static void main(String[] args) {
        ArrayList<CodageType> sujet1 = new ArrayList<>();
        ArrayList<CodageType> sujet2 = new ArrayList<>();
        sujet1.add(CodageType.MANCHESTER);


        ArrayList<String> reponses1 = new ArrayList<>();
        ArrayList<String> reponses2 = new ArrayList<>();
        ArrayList<String> reponses3 = new ArrayList<>();

        reponses1.add("42");
        reponses1.add("Obiwan Kenobi");
        reponses1.add("De la ville");
        reponses1.add("De Monsieur Manchester");
        reponses1.add("de Man chez Stère");
        reponses1.add("je n'ai plus d'idée...");

        int index = 2;

        QCM qcm = new QCM(sujet1, "D'où vient le nom du codage Manchester?", reponses1, index);
        ArrayList<QCM> qcmList = new ArrayList<>();
        qcmList.add(qcm);

        reponses2.add("reponse 1");
        reponses2.add("reponse 2");
        reponses2.add("reponse 3");
        reponses2.add("reponse 4");
        reponses2.add("reponse 5");


        qcm = new QCM(sujet1, "Deuxieme Question.", reponses2, 0);
        qcmList.add(qcm);

        sujet2.add(CodageType.NRZ);
        sujet2.add(CodageType.NRZI);

        reponses3.clear();
        reponses3.add("reponse 1");
        reponses3.add("reponse 2");
        reponses3.add("reponse 3");
        reponses3.add("reponse 4");
        reponses3.add("reponse 5");

        qcm = new QCM(sujet2, "Troisieme Question.", reponses3, 4);
        ArrayList<QCM> qcmList2 = new ArrayList<>();
        qcmList2.add(qcm);

        HashMap<List<CodageType>, List<QCM>> mapQCM = new HashMap<>();
        mapQCM.put(sujet1, qcmList);
        mapQCM.put(sujet2, qcmList2);

       // QCMManager.init();
        QCMManager.setQcm(mapQCM);
        QCMManager.saveQuestions();
    }
}
