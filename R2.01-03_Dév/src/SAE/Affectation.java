package SAE;

import java.io.Serializable;

public class Affectation implements Serializable {
/* Attributs */
    private Student host; 
    private Student guest;  

/* Constructeur */
    public Affectation(Student host, Student guest){
        this.host = host ; 
        this.guest = guest; 
        // est-ce qu'on rajoute une clé ID ? - Loïse
    }

/* Getters */
    public Student getHost(){
        return this.host;
    }

    public Student getGuest(){
        return this.guest; 
    }

/* Setters */
    public void setHost(Student host){
        this.host=host;
    }

    public void setGuest(Student guest){
        this.guest=guest ;
    }

/* toString() utilisé ppur l'exportation des données en csv : */
    @Override
    public String toString() {
        return this.host.getName()+" "+this.host.getForename()+";"+this.guest.getName()+" "+this.guest.getForename();
    }
    //voir si on rajoute un private int score, pour afficher le score d'affectation dans les résultats - Loïse
}