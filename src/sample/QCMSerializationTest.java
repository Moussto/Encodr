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

    static HashMap<List<CodageType>, List<QCM>> mapQCM = new HashMap<>();
    public static void main(String[] args) {

        NrzQCM();
        NrzIQCM();
        BipolarQCM();

        QCMManager.setQcm(mapQCM);
        QCMManager.saveQuestions();
    }

    public static void NrzQCM(){
        ArrayList<QCM> qcmList = new ArrayList<>();
        ArrayList<CodageType> sujetnrz = new ArrayList<>();
        sujetnrz.add(CodageType.NRZ);
        ArrayList<String> reponses1 = new ArrayList<>();
        reponses1.add("Avec un voltage negatif");
        reponses1.add("En inversant la polarité du dernier 1 ");
        reponses1.add("Avec un voltage positif");
        reponses1.add("Avec un Signal a 0V");
        reponses1.add("En forcant le front montant du signal");
        QCM qcm = new QCM(sujetnrz, "Comment code-t-on un bit 1 en NRZ ?", reponses1, 2);
        qcmList.add(qcm);

        ArrayList<String> reponses2 = new ArrayList<>();


        reponses2.add("Avec un voltage positif");
        reponses2.add("Avec un voltage negatif");
        reponses2.add("En gardant le meme signal que le bit précédent ");
        reponses2.add("Avec un signal a 0V");
        reponses2.add("En forcant le front montant du signal");
        qcm = new QCM(sujetnrz, "Comment code-t-on un bit 0 en NRZ ?", reponses2, 3);
        qcmList.add(qcm);

        ArrayList<String> reponses3 = new ArrayList<>();

        reponses3.add("Compliqué a implementer");
        reponses3.add("Une perte de signal equivaut a une suite de bit 0");
        reponses3.add("Synchronisation tres couteuse");
        qcm = new QCM(sujetnrz, "Quel est le plus gros inconvenient de du codage NRZ ?", reponses3, 1);
        qcmList.add(qcm);

        mapQCM.put(sujetnrz, qcmList);

    }

    public static void NrzIQCM(){
        ArrayList<QCM> qcmListnrzi = new ArrayList<>();
        ArrayList<CodageType> sujetnrzI = new ArrayList<>();
        sujetnrzI.add(CodageType.NRZI);

        ArrayList<String> NRZIQ1R = new ArrayList<>();
        NRZIQ1R.add("Avec un voltage positif");
        NRZIQ1R.add("Avec un voltage negatif");
        NRZIQ1R.add("En gardant le meme signal que le bit précédent ");
        NRZIQ1R.add("En forcant la transition du signal");
        NRZIQ1R.add("Avec un signal a 0V");
        QCM qcm = new QCM(sujetnrzI, "Comment code-t-on un bit 1 en NRZI ?", NRZIQ1R, 3);
        qcmListnrzi.add(qcm);

        ArrayList<String> NRZIQ2R = new ArrayList<>();
        NRZIQ2R.add("Avec un voltage positif");
        NRZIQ2R.add("Avec un voltage negatif");
        NRZIQ2R.add("En gardant le meme signal que le bit précédent ");
        NRZIQ2R.add("En forcant la transition du signal");
        NRZIQ2R.add("Avec un signal a 1V");
        qcm = new QCM(sujetnrzI, "Comment code-t-on un bit 0 en NRZI ?", NRZIQ2R, 2);
        qcmListnrzi.add(qcm);

        ArrayList<String> NRZIQ3R = new ArrayList<>();
        NRZIQ3R.add("Bonne utilisation de la bande passante");
        NRZIQ3R.add("Bonne gestion de l'horloge");
        NRZIQ3R.add("Pas de probleme lorsqu'il y a une longue sequence de 0 ");
        NRZIQ3R.add("cout de synchronisation faible");
        qcm = new QCM(sujetnrzI, "Quel est l'avantage du codage NRZi ?", NRZIQ3R, 0);
        qcmListnrzi.add(qcm);

        ArrayList<String> NRZIQ4R = new ArrayList<>();
        NRZIQ4R.add("Mauvaise utilisation de la bande passante");
        NRZIQ4R.add("Mauvaise gestion de l'horloge");
        NRZIQ4R.add("Pas de transition lorsqu'il y a une longue sequence de 0 ");
        NRZIQ4R.add("Complexité de mise en oeuvre");
        qcm = new QCM(sujetnrzI, "Quel est l'inconvenient du codage NRZ ?", NRZIQ4R, 2);
        qcmListnrzi.add(qcm);
        mapQCM.put(sujetnrzI, qcmListnrzi);

    }

    public static void BipolarQCM(){
        ArrayList<QCM> qcmbipolar = new ArrayList<>();
        ArrayList<CodageType> sujetbipol = new ArrayList<>();
        sujetbipol.add(CodageType.BIPOLAR);

        ArrayList<String> BIPOLIQ1R = new ArrayList<>();
        BIPOLIQ1R.add("Avec un voltage positif");
        BIPOLIQ1R.add("Avec un voltage negatif");
        BIPOLIQ1R.add("En inversant la polarité du dernier 1 ");
        BIPOLIQ1R.add("En gardant le meme signal que le bit 0 ");
        BIPOLIQ1R.add("En forcant la transition du signal vers le bas si ce n'est pas le cas");
        QCM qcm = new QCM(sujetbipol, "Comment code-t-on un bit 1 en Bipolaire ?", BIPOLIQ1R, 2);
        qcmbipolar.add(qcm);

        ArrayList<String> BIPOLIQ2R = new ArrayList<>();
        BIPOLIQ2R.add("Avec un voltage positif");
        BIPOLIQ2R.add("En gardant le meme signal que le bit précédent ");
        BIPOLIQ2R.add("En forcant la transition du signal vers le haut");
        BIPOLIQ2R.add("Avec un signal a 0V");
        BIPOLIQ2R.add("Avec un voltage negatif");
        qcm = new QCM(sujetbipol, "Comment code-t-on un bit 0 en Bipolaire ?", BIPOLIQ2R, 3);
        qcmbipolar.add(qcm);

        mapQCM.put(sujetbipol, qcmbipolar);

    }
}
