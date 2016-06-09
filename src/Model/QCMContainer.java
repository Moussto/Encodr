package Model;

import java.util.HashMap;
import java.util.List;

public class QCMContainer {
    public HashMap<List<CodageType>, List<QCM>> qcm = null;


    public QCMContainer() {}

    public HashMap<List<CodageType>, List<QCM>> getQcm() {
        return qcm;
    }

    public void setQcm(HashMap<List<CodageType>, List<QCM>> qcm) {
        this.qcm = qcm;
    }

}
