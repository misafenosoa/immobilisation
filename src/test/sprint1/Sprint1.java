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

        // System.out.println(TableauAmortissement.getTableauAmortissementComplet("5"));

        // insertion 

        BienAcquis.insert("2022-11-28", "tana", "entrepot4", "ordinateur", "bbl", "degressif", "5", "3500" ,"10" ,"2022-11-28");
    }
}


// 6            | 2022-11-28 |     2 | tana        | entrepot4 | ordinateur | blabla                                                               | degressif           |           5 |  3500 |          10 |
// 5            | 2022-05-02 |     1 | tana        | entrepot2 | clavier    | rétroéclairage LED, touches silencieuses pour une frappe confortable | lineaire            |           5 |  4000 |          10 | 2019-04-01