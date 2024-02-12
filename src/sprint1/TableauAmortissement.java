package sprint1;

import java.util.ArrayList;

public class TableauAmortissement {



    Integer annee ;
    Double base ;
    Double tauxDegressif ;
    Double tauxLineaire ;
    Double annuite ;
    Double valeurNetteComptable ;

    
    public static String getHTML(BienAcquis bienAcquis){
        TableauAmortissement [] lst = getLineaires(bienAcquis);
        // TODO misa

        return null;
    }

    public static TableauAmortissement[] getLineaires(BienAcquis bienAcquis){
        ArrayList<TableauAmortissement> lst = new ArrayList<>();
        int loop= bienAcquis.getAnneeamorti() ;
        TableauAmortissement v = new TableauAmortissement();
        v.setAnnee(1);
        v.setBase(bienAcquis.getAchat());
        v.setTauxLineaire(bienAcquis.getTauxLineaireAmortissementLineaire());
        v.setAnnuite(v.getBase()*v.getTauxLineaire()/100);
        v.setValeurNetteComptable(v.getBase()-v.getAnnuite());
        lst.add(v);
        Double tauxLinear = v.getTauxLineaire();
        for (int i = 2; i <= loop; i++) {
            lst.add(getLineaire(i, lst.get(lst.size()-1).getBase(),tauxLinear ));
        }
        return lst.toArray(new TableauAmortissement[lst.size()]);
    }

    public static TableauAmortissement getLineaire(Integer annee ,Double base , Double tauxLineaire){
        TableauAmortissement t= new TableauAmortissement();
        t.setAnnee(annee);
        t.setAnnuite(base*tauxLineaire);
        t.setBase(base);
        t.setTauxLineaire(tauxLineaire);
        return t ;
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
        v.setAnnuite(v.getBase()*tauxApplique);
        v.setValeurNetteComptable(v.getBase() - v.getAnnuite());

        Double anneerestante = bienAcquis.getAnneeamorti()-1.;

        lst.add(v);
        for (int i = 2; i < loop; i++) {
            TableauAmortissement v2 = new TableauAmortissement();
            v2.setAnnee(i);
            v2.setBase(v.getValeurNetteComptable());
            v2.setTauxLineaire(100./anneerestante);
            v2.setTauxDegressif(tauxDegressif);
            tauxApplique= Double.max(v2.getTauxDegressif(), v2.getTauxLineaire());
            v2.setAnnuite(v2.getBase()*tauxApplique);
            v2.setValeurNetteComptable(v2.getBase() - v2.getAnnuite());
            anneerestante-=1;
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
}