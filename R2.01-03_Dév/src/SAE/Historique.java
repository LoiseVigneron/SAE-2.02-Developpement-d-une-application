package SAE;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/* classe permettant de conserver les données d'anciens séjours */
public class Historique implements Serializable{
    /* L'identification d'un séjour se base sur la date de début de séjour */
    private ArrayList<Voyage> anciensVoyages;

/* Constructeurs d'un historique */
    public Historique(ArrayList<Voyage> voyages){
        this.anciensVoyages=voyages; 
    }

    public Historique(){
        this(new ArrayList<Voyage>());
    }
/* Methodes getters des attributs */

    public ArrayList<Voyage> getAllVoyages(){
        return this.anciensVoyages; 
    }

    public Voyage getVoyage(LocalDate date){
        for(Voyage v : this.anciensVoyages){
            if(v.getDATE()==date){
                return v;
            }
        }
        return null; 
    }

    @Override
    public String toString(){
        return this.anciensVoyages.toString();
    }

/* Equals entre deux objets */
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null) return false; 
        if(this.getClass()!=obj.getClass()) return false; 
        Historique other = (Historique) obj;
        if(this.anciensVoyages!=other.anciensVoyages) return false; 
        return true;
    }

    public void ajouterVoyage(Voyage voyage) {
    this.anciensVoyages.add(voyage);
}

}
//TO DO LIST :
// Il faut la réactualiser selon le nouveau UML - Loïse 