package yazdaniscodelab.prtestproject;

/**
 * Created by Yazdani on 4/11/2018.
 */

public class Data {

    String userName;
    String pass;
    String eMAIL;
    String id;

    public Data(String userName, String pass, String eMAIL, String id) {
        this.userName = userName;
        this.pass = pass;
        this.eMAIL = eMAIL;
        this.id = id;
    }

    public Data(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String geteMAIL() {
        return eMAIL;
    }

    public void seteMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
