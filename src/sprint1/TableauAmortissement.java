package sprint1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class TableauAmortissement {

    Integer annee;
    Double base;
    Double tauxDegressif;
    Double tauxLineaire;
    Double montantdesamortissementscumules;
    Double annuite;
    Double valeurNetteComptable;

    public static String getTableauAmortissementComplet(String bienAcquisId) throws Exception{
        String url = "jdbc:postgresql://localhost:5432/immobilisation";
        String user = "postgres";
        String password = "post";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, password);
            BienAcquis b= new BienAcquis().selectWhere(connection, false, "bienacquisid='"+bienAcquisId.trim()+"'")[0];
            if (b.getIdtypeamortissement().equalsIgnoreCase("lineaire")) {
                return getHTMLLineaire(b);
            }else{
                return getHTMLDegressif(b);
            }
        }finally{
            try {
                connection.close();
            } catch (Exception e) {}
        }
    }

    public static String getHTMLLineaire(BienAcquis bienAcquis) throws Exception {

            TableauAmortissement[] lst = getLineaires(bienAcquis);
            // Génération du tableau HTML
            StringBuilder htmlTable = new StringBuilder();
            htmlTable.append("<div class=\"table-responsive\">");
            htmlTable.append("<table class=\"table table-hover\">");
            htmlTable.append("<thead>");
            htmlTable.append("<tr>");
                htmlTable.append("<th>Annee</th>");
                htmlTable.append("<th>Base</th>");
                htmlTable.append("<th>Montant des amortissments cumules</th>");
                htmlTable.append("<th>Annuite</th>");
                htmlTable.append("<th>Valeur Nette Comptable</th>");
            htmlTable.append("</tr>");
            htmlTable.append("</thead>");
            htmlTable.append("<tbody>");
    
            for (TableauAmortissement amortissement : lst) {
                htmlTable.append("<tr>");
                htmlTable.append("<td>").append(amortissement.getAnnee()).append("</td>");
                htmlTable.append("<td>").append(amortissement.getBase()).append("</td>");
                htmlTable.append("<td>").append(amortissement.getMontantdesamortissementscumules()).append("</td>");
                htmlTable.append("<td>").append(amortissement.getAnnuite()).append("</td>");
                htmlTable.append("<td>").append(amortissement.getValeurNetteComptable()).append("</td>");
                // Ajoutez ici d'autres colonnes en fonction de vos besoins
                htmlTable.append("</tr>");
            }
    
            htmlTable.append("</tbody>");
            htmlTable.append("</table>");
            htmlTable.append("</div>");
    
            // Affichage du tableau HTML généré
            return (htmlTable.toString());
    
        


    }

    public static String getHTMLDegressif(BienAcquis bienAcquis) throws Exception {
        
        TableauAmortissement[] lst = getDegressif(bienAcquis);
        // Génération du tableau HTML
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<div class=\"table-responsive\">");
        htmlTable.append("<table class=\"table table-hover\">");
        htmlTable.append("<thead>");
        htmlTable.append("<tr>");
        htmlTable.append("<th>Annee</th>");
        htmlTable.append("<th>Base</th>");
        htmlTable.append("<th>Taux Degressif</th>");
        htmlTable.append("<th>Taux Lineaire</th>");
        htmlTable.append("<th>Annuite</th>");
        htmlTable.append("<th>Valeur Nette Comptable</th>");
        htmlTable.append("</tr>");
        htmlTable.append("</thead>");
        htmlTable.append("<tbody>");

        for (TableauAmortissement amortissement : lst) {
            htmlTable.append("<tr>");
            htmlTable.append("<td>").append(amortissement.getAnnee()).append("</td>");
            htmlTable.append("<td>").append(amortissement.getBase()).append("</td>");
            htmlTable.append("<td>").append(amortissement.getTauxDegressif()).append("</td>");
            htmlTable.append("<td>").append(amortissement.getTauxLineaire()).append("</td>");
            htmlTable.append("<td>").append(amortissement.getAnnuite()).append("</td>");
            htmlTable.append("<td>").append(amortissement.getValeurNetteComptable()).append("</td>");
            // Ajoutez ici d'autres colonnes en fonction de vos besoins
            htmlTable.append("</tr>");
        }

        htmlTable.append("</tbody>");
        htmlTable.append("</table>");
        htmlTable.append("</div>");

        // Affichage du tableau HTML généré
        return (htmlTable.toString());

    }



    public static TableauAmortissement[] getLineaires(BienAcquis bienAcquis) {


        ArrayList<TableauAmortissement> lst = new ArrayList<>();
        int loop = bienAcquis.getAnneeamorti();
        Double basebe = bienAcquis.getAchat();
        TableauAmortissement v = new TableauAmortissement();
        v.setAnnee(1);
        v.setBase(basebe);
        v.setTauxLineaire(bienAcquis.getTauxLineaireAmortissementLineaire());
        v.setAnnuite(basebe * v.getTauxLineaire() * (12 -Double.parseDouble(bienAcquis.getDebutUtilisation().toString().split("-")[1])+1) *30/360. );
        v.setValeurNetteComptable(v.getBase() - v.getAnnuite());
        v.setMontantdesamortissementscumules(v.getAnnuite());
        lst.add(v);
        Double tauxLinear = v.getTauxLineaire();
        for (int i = 2; i <= loop; i++) {
            lst.add(getLineaire(i, basebe, tauxLinear ,lst.get(lst.size() - 1).getMontantdesamortissementscumules()));
        }
        TableauAmortissement v2 = new TableauAmortissement();
        v2.setAnnee(loop);
        v2.setBase(basebe);
        v2.setAnnuite(basebe * tauxLinear * (12 -Double.parseDouble(bienAcquis.getDebutUtilisation().toString().split("-")[1])+1) *30/360. );
        v2.setTauxLineaire(tauxLinear);
        v2.setMontantdesamortissementscumules(v2.getAnnuite()+lst.get(lst.size() - 1).getMontantdesamortissementscumules());
        v2.setValeurNetteComptable(basebe - v2.getMontantdesamortissementscumules());
        lst.add(v2);
        
        return lst.toArray(new TableauAmortissement[lst.size()]);
    }

    public static TableauAmortissement getLineaire(Integer annee, Double base, Double tauxLineaire ,Double cumule) {
        TableauAmortissement t = new TableauAmortissement();
        t.setAnnee(annee);
        t.setAnnuite(base * tauxLineaire);
        t.setBase(base);
        t.setTauxLineaire(tauxLineaire);
        t.setMontantdesamortissementscumules(cumule+t.getAnnuite());
        t.setValeurNetteComptable(t.getBase()-t.getMontantdesamortissementscumules());
        return t;
    }


    public static TableauAmortissement[] getDegressif(BienAcquis bienAcquis) throws Exception{
        ArrayList<TableauAmortissement> lst = new ArrayList<>();
        int loop= bienAcquis.getAnneeamorti() ;
        Double tauxDegressif = bienAcquis.getCoefficientDegressif()*1/bienAcquis.getAnneeamorti();

        TableauAmortissement v = new TableauAmortissement();
        v.setAnnee(1);
        v.setBase(bienAcquis.getAchat());
        v.setTauxLineaire(bienAcquis.getTauxLineaireAmortissementLineaire());   
        v.setTauxDegressif(tauxDegressif);
        Double tauxApplique= Double.max(v.getTauxDegressif(), v.getTauxLineaire());
        v.setAnnuite(v.getBase()*tauxApplique*bienAcquis.getMoisUtilisation()/12);
        v.setValeurNetteComptable(v.getBase() - v.getAnnuite());

        Double anneerestante = bienAcquis.getAnneeamorti()-1.;

        lst.add(v);
        for (int i = 2; i <= loop; i++) {
            TableauAmortissement v2 = new TableauAmortissement();
            v2.setAnnee(i);
            v2.setBase(lst.get(lst.size()-1).getValeurNetteComptable());
            v2.setTauxLineaire(100./anneerestante/100.);
            v2.setTauxDegressif(tauxDegressif);
            tauxApplique= Double.max(v2.getTauxDegressif(), v2.getTauxLineaire());
            v2.setAnnuite(v2.getBase()*tauxApplique);
            v2.setValeurNetteComptable(v2.getBase() - v2.getAnnuite());
            anneerestante-=1;
            lst.add(v2);
        }
        return lst.toArray(new TableauAmortissement[lst.size()]);
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public Double getTauxDegressif() {
        return tauxDegressif;
    }

    public void setTauxDegressif(Double tauxDegressif) {
        this.tauxDegressif = tauxDegressif;
    }

    public Double getTauxLineaire() {
        return tauxLineaire;
    }

    public void setTauxLineaire(Double tauxLineaire) {
        this.tauxLineaire = tauxLineaire;
    }

    public Double getAnnuite() {
        return annuite;
    }

    public void setAnnuite(Double annuite) {
        this.annuite = annuite;
    }

    public Double getValeurNetteComptable() {
        return valeurNetteComptable;
    }

    public void setValeurNetteComptable(Double valeurNetteComptable) {
        this.valeurNetteComptable = valeurNetteComptable;
    }

    public Double getMontantdesamortissementscumules() {
        return montantdesamortissementscumules;
    }

    public void setMontantdesamortissementscumules(Double montantdesamortissementscumules) {
        this.montantdesamortissementscumules = montantdesamortissementscumules;
    }
}