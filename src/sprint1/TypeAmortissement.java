package sprint1;

import java.sql.Connection;
import java.sql.DriverManager;

import orm.ORM;

public class TypeAmortissement extends ORM<TypeAmortissement> {
    String typeAmortissementId;

    public static String getOptions() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/immobilisation";
        String user = "postgres";
        String password = "post";
        Connection connection = null;

        connection = DriverManager.getConnection(url, user, password);
        TypeAmortissement[] materiels = new TypeAmortissement().select(connection, false);

        StringBuilder options = new StringBuilder();
        options .append("<select class="+'"'+"form-control"+'"'+ "id="+'"'+"exampleSelectGender"+'"'+" name="+'"'+"typeamortissement"+'"'+">");

        for (TypeAmortissement materiel : materiels) {
            options
                    .append("<option value=\"")
                    .append(materiel.getTypeAmortissementId())
                    .append("\">")
                    .append(materiel.getTypeAmortissementId())
                    .append("</option>") ;
        }
        options.append("</select>");

        return options.toString();

    }

    public String getTypeAmortissementId() {
        return typeAmortissementId;
    }

    public void setTypeAmortissementId(String typeAmortissementId) {
        this.typeAmortissementId = typeAmortissementId;
    }
}
