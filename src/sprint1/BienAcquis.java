package sprint1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

import orm.ORM;

public class BienAcquis extends ORM<BienAcquis> {

    String bienacquisid;
    Date dateacquis;
    Integer serie;
    String villeacquis;
    String depot;
    String natureid;
    String description;
    String idtypeamortissement;
    Integer anneeamorti;
    Double achat ;

    public static void insert(String dateacquis, String serie, String villeacquis, String depot, String natureid,
            String description, String idtypeamortissement, String anneeamorti ,String achat) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/Immobilisation";
        String user = "postgres";
        String password = "post";
        Connection connection = null;

        BienAcquis bienacquis = new BienAcquis();
        connection = DriverManager.getConnection(url, user, password);
        bienacquis.setDateacquis(Date.valueOf(dateacquis));
        bienacquis.setSerie(Integer.parseInt(serie));
        bienacquis.setVilleacquis(villeacquis);
        bienacquis.setDepot(depot);
        bienacquis.setNatureid(natureid);
        bienacquis.setDescription(description);
        bienacquis.setIdtypeamortissement(idtypeamortissement);
        bienacquis.setAnneeamorti(Integer.parseInt(anneeamorti));
        bienacquis.setAchat(Double.parseDouble(achat));
        bienacquis.insert(connection, false);

    }

    public Double getTauxLineaireAmortissementLineaire(){
        return 100. / anneeamorti ;
    }

    public Double geTauxLineaireAmortissement(Double anneeRestante){
        return 100. / anneeRestante ;
    }

    public String getBienacquisid() {
        return bienacquisid;
    }

    public void setBienacquisid(String bienacquisid) {
        this.bienacquisid = bienacquisid;
    }

    public Date getDateacquis() {
        return dateacquis;
    }

    public void setDateacquis(Date dateacquis) {
        this.dateacquis = dateacquis;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public String getVilleacquis() {
        return villeacquis;
    }

    public void setVilleacquis(String villeacquis) {
        this.villeacquis = villeacquis;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getNatureid() {
        return natureid;
    }

    public void setNatureid(String natureid) {
        this.natureid = natureid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdtypeamortissement() {
        return idtypeamortissement;
    }

    public void setIdtypeamortissement(String idtypeamortissement) {
        this.idtypeamortissement = idtypeamortissement;
    }

    public Integer getAnneeamorti() {
        return anneeamorti;
    }

    public void setAnneeamorti(Integer anneeamorti) {
        this.anneeamorti = anneeamorti;
    }

    public Double getAchat() {
        return achat;
    }

    public void setAchat(Double achat) {
        this.achat = achat;
    }

    public Double getCoefficientDegressif() throws Exception{
        String url = "jdbc:postgresql://localhost:5432/Immobilisation";
        String user = "postgres";
        String password = "post";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            Typeamortissementregle t = new Typeamortissementregle();
            return t.selectWhere(connection, false, "debut<="+getAnneeamorti()+" and fin>"+getAnneeamorti())[0].getCoefficient();

        } finally{
            if (!connection.isClosed()) {
                connection.close();
            }
        }

    }



}
