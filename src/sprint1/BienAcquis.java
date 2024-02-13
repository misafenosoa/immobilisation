package sprint1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

import orm.ORM;
import sprint2.Bienconfigurationpardefaut;
import sprint2.Historisationconfiguration;

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
    Date debutUtilisation;
    Integer etatgeneral;

    public static void insert(String dateacquis, String serie, String villeacquis, String depot, String natureid,
        String description, String idtypeamortissement, String anneeamorti ,String achat) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/Immobilisation";
        String user = "postgres";
        String password = "post";
        Connection connection = DriverManager.getConnection(url, user, password);

        try{
            BienAcquis bienacquis = new BienAcquis();
            bienacquis.setDateacquis(Date.valueOf(dateacquis));
            bienacquis.setSerie(Integer.parseInt(serie));
            bienacquis.setVilleacquis(villeacquis);
            bienacquis.setDepot(depot);
            bienacquis.setNatureid(natureid);
            bienacquis.setDescription(description);
            bienacquis.setIdtypeamortissement(idtypeamortissement);
            bienacquis.setAnneeamorti(Integer.parseInt(anneeamorti));
            bienacquis.setAchat(Double.parseDouble(achat));
            String codeCompta = new Nature().selectWhere(connection, true, "natureId='"+natureid+"'")[0].getCompteCode();
            bienacquis.setBienacquisid(natureid+"/"+((int)Math.random()) +"/"+dateacquis+"/"+codeCompta+"/"+villeacquis+"/"+depot);
            bienacquis.insert(connection, true);
    
    
            Bienconfigurationpardefaut[] bienconfigurationpardefauts = new Bienconfigurationpardefaut().selectWhere(connection, true, "natureidmere='"+bienacquis.getNatureid()+"'");
    
            for (Bienconfigurationpardefaut bienconfigurationpardefaut : bienconfigurationpardefauts) {
                Historisationconfiguration s = new Historisationconfiguration();
    
                s.setBienacquisid(bienacquis.getBienacquisid());
                s.setNaturefille(bienconfigurationpardefaut.getNaturefille());
                s.setQuantite(bienconfigurationpardefaut.getQuantite());
    
                s.insert(connection, true);
            }
            connection.commit();
        }
        catch(Exception e){
            connection.rollback();
        }
        finally{
            connection.close();
        }
    }

    public static String getOptions() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/immobilisation";
        String user = "postgres";
        String password = "post";
        Connection connection = null;

        connection = DriverManager.getConnection(url, user, password);
        BienAcquis[] materiels = new BienAcquis().select(connection, false);

        StringBuilder options = new StringBuilder();
        for (BienAcquis materiel : materiels) {
            options.append(
                    "<select class=" + '"' + "form-control" + '"' + "id=" + '"' + "exampleSelectGender" + '"' + ">")
                    .append("<option value=\"")
                    .append(materiel.getBienacquisid())
                    .append("\">")
                    .append(materiel.getNatureid())
                    .append("</option>")
                    .append("</select>");
        }

        return options.toString();

    }

    public Double getMoisUtilisation(){
        return 13- Double.parseDouble(dateacquis.toString().split("-")[1]) ;
    }

    public Double getTauxLineaireAmortissementLineaire(){
        return 100. / anneeamorti /100.;
    }

    public Double geTauxLineaireAmortissement(Double anneeRestante){
        return 100. / anneeRestante /100.;
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
        String url = "jdbc:postgresql://localhost:5432/immobilisation";
        String user = "postgres";
        String password = "post";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            Typeamortissementregle t = new Typeamortissementregle();
            return t.selectWhere(connection, false, "debut<="+getAnneeamorti()+" and fin>="+getAnneeamorti())[0].getCoefficient();

        } finally{
            if (!connection.isClosed()) {
                connection.close();
            }
        }

    }

    public Date getDebutUtilisation() {
        return debutUtilisation;
    }

    public void setDebutUtilisation(Date debutUtilisation) {
        this.debutUtilisation = debutUtilisation;
    }

    public Integer getEtatgeneral() {
        return etatgeneral;
    }

    public void setEtatgeneral(Integer etatgeneral) {
        this.etatgeneral = etatgeneral;
    }



}
