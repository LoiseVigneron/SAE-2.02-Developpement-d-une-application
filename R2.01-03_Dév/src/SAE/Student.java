package SAE;
import java.io.Serializable;
//a voir pour modifier le nom du package - Loïse
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/*Class Student qui identifie chaque étudiant */
public class Student implements Serializable{
 /*Attributs reprenant : n° d'identifiant, nom, prénom, date de naissance et statut hôte ou non*/
    private final int ID; 
    private String name; 
    private String forename; 
    private LocalDate birthday;
    private String gender; //'M', 'F' ou 'O' 
    private Country country; 
    // private boolean host; // Je le "retire" car pour moi ce n'est plus pertinent (nous avons désormais la classe Groupe) - Loïse
    private Map<Critere,String> criteres; 
/* Attribut statique permettant l'attribution d'un identifiant unique automatiquement */
    private static int cpt = 1; 

/*Constructeurs d'un élève*/
    public Student(String name, String forename, LocalDate birth, String gender, Country country){
        this.ID=cpt++; 
        this.name=name;
        this.forename=forename; 
        this.birthday=birth; 
        this.gender=gender;
        this.country=country;
        // this.host=host; //=> Attention il faut le remettre dans les paramètres de la signature si jamais on l'utilise
        this.criteres=new HashMap<>(); 
    }

    public Student(String name, String forename, LocalDate birth, String gender, Country country, Map<Critere, String> criteres){
        this(name,forename,birth,gender,country);
        this.criteres=criteres;
    }

/* Getter de différents attributs de la classe */
    public String getName(){
        return this.name; 
    }
    public String getForename(){
        return this.forename; 
    }
    public LocalDate getBirthday(){
        return this.birthday; 
    }
    public int getID(){
        return this.ID; 
    }

    public String getGender(){
        return this.gender; 
    }

    public Country getCountry(){
        return this.country;
    }

    public String getStringCountry(){
        return this.country.getCountry(); 
    }

// /* Retourne un boolean qui permet de savoir si la personne est hôte (true) ou visiteur (false) */
//     public boolean isHost(){
//         return this.host; 
//     }


/* Ajout dans la HashMap les critères et leurs valeurs associés */
    public void addCritere(Critere key, String value){
        this.criteres.put(key,value);
    }
//Voir pour ajouter plusieurs critères en même temps - Loïse 


/* Retourne un boolean indiquant si l'élève a un animal(true) ou s'il n'en possède pas (false) */
    public boolean petOwner(){
        if(this.criteres.containsKey(Critere.HOST_HAS_ANIMAL)){
            String valeur = this.criteres.get(Critere.HOST_HAS_ANIMAL).trim();
            if(valeur.equalsIgnoreCase("true") || valeur.equalsIgnoreCase("yes")){
                return true;
            }else{
                return false;
            }
        }
        return false; 
    }

/* Boolean indiquant si la personne est allergique (true) ou non (false) aux animaux */
    public boolean allergyAnimal(){
        if(this.criteres.containsKey(Critere.GUEST_ANIMAL_ALLERGY)){
            String valeur = this.criteres.get(Critere.GUEST_ANIMAL_ALLERGY).trim();
            if(valeur.equalsIgnoreCase("true") || valeur.equalsIgnoreCase("yes")){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

/* Indication de l'élève s'il souhaite être avec la même personne que le séjour précédent */
    public boolean ancientPair(){
        if(this.criteres.containsKey(Critere.HISTORY)){
            String valeur = this.criteres.get(Critere.HISTORY).trim();
            if(valeur.equalsIgnoreCase("true") || valeur.equalsIgnoreCase("yes")){
                return true;
            }else{
                return false;
            }
        }
        return false; 
        //  return "true".equals(this.criteres.get(Critere.HISTORY));
    }

/* Retourne les régimes alimentaire */
    public String hostFood(){
        if(this.criteres.containsKey(Critere.HOST_FOOD)){
            return this.criteres.get(Critere.HOST_FOOD);
        }
        return "normal";
    }
    public String guestFoodConstraint(){
        if(this.criteres.containsKey(Critere.GUEST_FOOD_CONSTRAINT)){
            return this.criteres.get(Critere.GUEST_FOOD_CONSTRAINT);
        }
        return "normal";
    }

/* Liste des loisirs de l'élève */
    public String hobbiesList(){
        if(this.criteres.containsKey(Critere.HOBBIES)){
            return this.criteres.get(Critere.HOBBIES);
        }
        return "";
    }

/* Choix du genre pour la consitution d'un duo */
    public String genderPreference(){
        if(this.criteres.containsKey(Critere.PAIR_GENDER)){
            return this.criteres.get(Critere.PAIR_GENDER);
        }
        return "x";
    }
// Est-ce qu'on considère que la HashMap sera toujours valide et on évite de vérifier à chaque fois que les clés sont comprises dedans ? - Loïse
//Voir si pas moyen à l'avenir d'éviter la répétition de code (car les méthodes sur les critères sont très similaires) - Loïse

    public boolean isValid(){
        if(this.petOwner() && this.allergyAnimal())return false; 
        if(!this.guestFoodConstraint().trim().equalsIgnoreCase(this.hostFood())) return false;
        return true; 
    }

/* Méthode equals de comparaison d'objet */
    @Override
    public boolean equals(Object obj){
        if(this==obj)return true;
        if(obj==null)return false;
        if(this.getClass()!=obj.getClass())return false;
        Student other=(Student) obj; 
        if(this.ID!=other.ID) return false;
        if(this.name==null){
            if(other.name!=null)return false;
        }else if(!this.name.equals(other.name))return false;
        if(this.forename==null){
            if(other.forename!=null)return false;
        }else if(!this.forename.equals(other.forename))return false;
        if(this.birthday==null){
            if(other.birthday!=null)return false;
        }else if(!this.birthday.equals(other.birthday))return false;
        return true;
    }
//Du coup on garde tout ou juste l'ID ? - Loïse 

/*String contenant les attributs importants de la classe */
    @Override
    public String toString(){
        return this.ID+"= "+this.forename+" "+this.name+" ("+this.birthday+", "+this.gender+") "+this.country.getCountry(); 
    }
}