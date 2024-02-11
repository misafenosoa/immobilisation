package sprint1;

import java.sql.Connection;
import java.sql.DriverManager;

import orm.ORM;

public class Nature extends ORM<Nature> {
    String natureId;
    String nature;
    String compteCode;

    public static String getOptions() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/immobilisation";
        String user = "postgres";
        String password = "post";
        Connection connection = null;

        connection = DriverManager.getConnection(url, user, password);
        Nature[] materiels = new Nature().select(connection, false);

        StringBuilder options = new StringBuilder();
        for (Nature materiel : materiels) {
            options.append(
                    "<select class=" + '"' + "form-control" + '"' + "id=" + '"' + "exampleSelectGender" + '"' + ">")
                    .append("<option value=\"")
                    .append(materiel.getNatureId())
                    .append("\">")
                    .append(materiel.getNature())
                    .append("</option>")
                    .append("</select>");
        }

        return options.toString();

    }

    public String getNatureId() {
        return natureId;
    }

    public void setNatureId(String natureId) {
        this.natureId = natureId;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getCompteCode() {
        return compteCode;
    }

    public void setCompteCode(String compteCode) {
        this.compteCode = compteCode;
    }
}