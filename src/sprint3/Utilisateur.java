package sprint3;

import java.sql.Connection;
import java.sql.DriverManager;

import orm.ORM;

public class Utilisateur extends ORM<Utilisateur>{
    String utilisateur;
    Integer utilisateurgrade;

        public static String getOptions() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/immobilisation";
        String user = "postgres";
        String password = "post";
        Connection connection = null;

        connection = DriverManager.getConnection(url, user, password);
        Utilisateur[] materiels = new Utilisateur().select(connection, false);
        StringBuilder options = new StringBuilder();
        options.append(
                "<select class=" + '"' + "form-control" + '"' + "id=" + '"' + "exampleSelectGender" + '"' + " name='utilisateur'>") ;

        for (Utilisateur materiel : materiels) {
            options
                    .append("<option value=\"")
                    .append(materiel.getUtilisateur())
                    .append("\">")
                    .append(materiel.getUtilisateur())
                    .append("</option>") ;
                }
            options.append("</select>");

        return options.toString();
    }



    public String getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }
    public Integer getUtilisateurgrade() {
        return utilisateurgrade;
    }
    public void setUtilisateurgrade(Integer utilisateurgrade) {
        this.utilisateurgrade = utilisateurgrade;
    } 

}
