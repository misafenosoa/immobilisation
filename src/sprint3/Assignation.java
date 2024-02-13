package sprint3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

import orm.ORM;

public class Assignation extends ORM<Assignation>{
    Integer assignationid ;
    String bienacquis;
    String utilisateur;
    Date datedebut ;
    Date datefin;

    public static void insert(String bien ,String user2 ,String dateDebut , String dateFin) throws Exception{
        String url = "jdbc:postgresql://localhost:5432/immobilisation";
        String user = "postgres";
        String password = "post";
        Connection connection = DriverManager.getConnection(url, user, password);
        try{
            Assignation s = new Assignation();
            s.setBienacquis(bien);
            s.setUtilisateur(user2);
            s.setDatedebut(Date.valueOf(dateDebut));
            s.setDatefin(Date.valueOf(dateFin));
            s.insert(connection, false);
        }finally{
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }



    public Integer getAssignationid() {
        return assignationid;
    }
    public void setAssignationid(Integer assignationid) {
        this.assignationid = assignationid;
    }
    public String getBienacquis() {
        return bienacquis;
    }
    public void setBienacquis(String bienacquis) {
        this.bienacquis = bienacquis;
    }
    public String getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }
    public Date getDatedebut() {
        return datedebut;
    }
    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }
    public Date getDatefin() {
        return datefin;
    }
    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }
}
