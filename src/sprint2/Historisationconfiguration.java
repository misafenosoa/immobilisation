package sprint2;

import java.sql.Date;

import orm.ORM;

public class Historisationconfiguration extends ORM<Historisationconfiguration>{

    Integer historisationconfigurationid;
    String bienacquisid;
    String naturefille;
    Date datehistorisationdebut ;
    Date datehistorisationfin;
    Double quantite ;
    public Integer getHistorisationconfigurationid() {
        return historisationconfigurationid;
    }
    public void setHistorisationconfigurationid(Integer historisationconfigurationid) {
        this.historisationconfigurationid = historisationconfigurationid;
    }
    public String getBienacquisid() {
        return bienacquisid;
    }
    public void setBienacquisid(String bienacquisid) {
        this.bienacquisid = bienacquisid;
    }
    public String getNaturefille() {
        return naturefille;
    }
    public void setNaturefille(String naturefille) {
        this.naturefille = naturefille;
    }
    public Date getDatehistorisationdebut() {
        return datehistorisationdebut;
    }
    public void setDatehistorisationdebut(Date datehistorisationdebut) {
        this.datehistorisationdebut = datehistorisationdebut;
    }
    public Date getDatehistorisationfin() {
        return datehistorisationfin;
    }
    public void setDatehistorisationfin(Date datehistorisationfin) {
        this.datehistorisationfin = datehistorisationfin;
    }
    public Double getQuantite() {
        return quantite;
    }
    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }
}
