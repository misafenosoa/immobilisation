package test.sprint1;

import sprint1.BienAcquis;
import sprint1.Nature;
import sprint1.TableauAmortissement;

public class Sprint1 {
    public static void main(String[] args) throws Exception {
        // lister les natures

        // System.out.println(Nature.getOptions());

        // lister les bien acquis

        // System.out.println(BienAcquis.getOptions());

        // afficher le tableau d amortissement par bien acquis

        System.out.println(TableauAmortissement.getTableauAmortissementComplet("5"));
    }
}
