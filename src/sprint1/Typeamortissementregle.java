package sprint1;

import orm.ORM;

public class Typeamortissementregle extends ORM<Typeamortissementregle>{
    Integer typeamortissementregle ;
    String typeamortissementid;
    Integer debut; 
    Integer fin;
    Double coefficient ;
    
    public Integer getTypeamortissementregle() {
        return typeamortissementregle;
    }
    public void setTypeamortissementregle(Integer typeamortissementregle) {
        this.typeamortissementregle = typeamortissementregle;
    }
    public String getTypeamortissementid() {
        return typeamortissementid;
    }
    public void setTypeamortissementid(String typeamortissementid) {
        this.typeamortissementid = typeamortissementid;
    }
    public Integer getDebut() {
        return debut;
    }
    public void setDebut(Integer debut) {
        this.debut = debut;
    }
    public Integer getFin() {
        return fin;
    }
    public void setFin(Integer fin) {
        this.fin = fin;
    }
    public Double getCoefficient() {
        return coefficient;
    }
    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }
    
}
