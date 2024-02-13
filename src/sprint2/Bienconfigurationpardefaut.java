package sprint2;

import orm.ORM;

public class Bienconfigurationpardefaut extends ORM<Bienconfigurationpardefaut>{
    Integer bienconfigurationpardefaut;
    String natureidmere ;
    String naturefille ;
    Double quantite ;





    public Integer getBienconfigurationpardefaut() {
        return bienconfigurationpardefaut;
    }
    public void setBienconfigurationpardefaut(Integer bienconfigurationpardefaut) {
        this.bienconfigurationpardefaut = bienconfigurationpardefaut;
    }
    public String getNatureidmere() {
        return natureidmere;
    }
    public void setNatureidmere(String natureidmere) {
        this.natureidmere = natureidmere;
    }
    public String getNaturefille() {
        return naturefille;
    }
    public void setNaturefille(String naturefille) {
        this.naturefille = naturefille;
    }
    public Double getQuantite() {
        return quantite;
    }
    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }
}
