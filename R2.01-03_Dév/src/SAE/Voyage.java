package SAE;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;

public class Voyage implements Serializable {
/*Attributs */
    private final LocalDate DATE;
    private Groupe paysHost;
    private Groupe paysGuest; 
    private ArrayList<Affectation> affectations; 

/* Constructeur */
    public Voyage(LocalDate date, Groupe paysHost, Groupe paysGuest){
        this.DATE = date; 
        this.paysHost=paysHost;
        this.paysGuest=paysGuest; 
        this.affectations=new ArrayList<>();
    }

/* Getters */
    public Groupe getPaysHost(){
        return this.paysHost;
    }

    public Groupe getPaysGuest(){
        return this.paysGuest;
    }

    public LocalDate getDATE(){
        return this.DATE; 
    }

    public ArrayList<Affectation> getAffectations(){
        return this.affectations; 
    }

/* Méthodes */
    public static int nbHobbiesCommuns(Student h, Student g){
        try {
            if (h==null || g == null)return 0;
            if(h.hobbiesList().equals("") || g.hobbiesList().equals("")) return 0; 
            String[] hobbiesHost = h.hobbiesList().split(","); //.split(",") ==> divise la chaine par rapport au séparateur
            String[] hobbiesGuest = g.hobbiesList().split(",");
            int count=0;
            for(int i=0;i<hobbiesHost.length;i++){
                        for(int j=0;j<hobbiesGuest.length;j++){
                            if(hobbiesHost[i].trim().equalsIgnoreCase(hobbiesGuest[j].trim())){ //.trim() ==> supprime les espaces au début et à la fin d'une chaine 
                                count++;                                                        //.equalsIgnoreCase ==> c'est dans le nom, ça ignore la case 
                            }
                        }
                    }
            return count; 
        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println("Indice en dehors des bornes : "+ie);
        }
        return 0;
            
    }

    public boolean frenchCompatibility(Student h, Student g){
        try{ 
            if(h.getCountry()==Country.FRA || g.getCountry()==Country.FRA){
                int count = nbHobbiesCommuns(h,g);
                if(count>=1) return true;
            }
        }catch(NullPointerException e){//class Fraction
            System.out.println("Erreur lors du calcul : "+e);
        }catch(ArrayIndexOutOfBoundsException ae){
            System.out.println("Erreur, valeur en dehors des bornes : "+ae);
        }catch(Exception ex){
            System.out.println("Erreur exception : "+ex);
        }
        return false; 
    }

    public int criteresCompatibility(Student h, Student g){
        int result = 0;
        if(Period.between(h.getBirthday(), g.getBirthday()).getMonths()>=18){
            result++;
        }

        if(!h.getGender().equalsIgnoreCase(g.genderPreference())){
            result++;
        }else if(!g.getGender().equalsIgnoreCase(h.genderPreference())){
            result++;
        }
        if(h.petOwner() && g.allergyAnimal()){
            result+=50;
        }
        if(!g.guestFoodConstraint().equalsIgnoreCase("normal")){
            if(!g.guestFoodConstraint().equalsIgnoreCase(h.hostFood())){
                result+=50; 
            }
        }
            result=result-nbHobbiesCommuns(h, g);
        return result;
    }


    public void affectationCalcul(){
        ArrayList<Affectation> result= new ArrayList<>(); 

        ArrayList<Student> hosts = paysHost.getStudentsList(); 
        ArrayList<Student> guests = paysGuest.getStudentsList();
        int taille=Math.min(hosts.size(), guests.size());
        int[][] scores = new int[taille][taille];

        /* Création de la matrice pour le cacul */
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                Student hote = hosts.get(i);
                Student invite = guests.get(j);
                if(hote.getCountry()==Country.FRA || invite.getCountry()==Country.FRA){
                    boolean okFrance = frenchCompatibility(hote, invite);
                    if(okFrance){
                       scores[i][j]=criteresCompatibility(hote, invite); 
                    }else{
                        scores[i][j]=9999; //A voir pour changer
                    }
                }else{
                    scores[i][j]=criteresCompatibility(hote, invite);
                }
            }
        }

        /* Algorithme hongrois */
        int[] matchs = new int[taille];
        Arrays.fill(matchs, -1);
        //l'indice correspond à l'host, et le contenu correspond à l'indice du guest (dans l'arraylist guests)
        //Tout est initialisé à -1 (car pas possible d'avoir un indice qui vaut -1)
        boolean[] guestUsed = new boolean[taille];
        //indique si le guest est déjà placé ou non 

        for(int i=0;i<taille;i++){
            int scoreMin = Integer.MAX_VALUE; //permet de sécurisé le résultat 
            int bestGuest=-1; //represente l'indice du guest 
            for(int j=0;j<taille;j++){
                if(!guestUsed[j] && scores[i][j]<scoreMin){
                    scoreMin=scores[i][j]; 
                    bestGuest=j;
                }
            }
            //on vient de sélectionner le meilleur guest 

            //Maintenant on vérifie qu'il est bien réél et on le passe dans les tableau de matching
            if(bestGuest!=-1){
                matchs[i]=bestGuest;
                guestUsed[bestGuest]=true;
            }
        }

        //Création ensuite des affectations : 
        for(int i=0;i<taille;i++){
            int guestIndex = matchs[i];
            if(guestIndex !=-1){
                Student hote = hosts.get(i);
                Student invite = guests.get(guestIndex);
                result.add(new Affectation(hote, invite));
            }
        }

        this.affectations=result;
    }

    public void ajoutBinome(Affectation a){
        if(!this.affectations.contains(a)){
            this.affectations.add(a); 
        }        
    }
    //Methode pour retourner une liste des points communs

    public static ArrayList<String> getPointsCommuns(Student h, Student g){
        ArrayList<String> pc = new ArrayList<>();
        //if (h==null || g == null)return null;
            //if(h.hobbiesList().equals("") || g.hobbiesList().equals("")) return null; 
            String[] hobbiesHost = h.hobbiesList().split(","); //.split(",") ==> divise la chaine par rapport au séparateur
            String[] hobbiesGuest = g.hobbiesList().split(",");
            for(int i=0;i<hobbiesHost.length;i++){
                for(int j=0;j<hobbiesGuest.length;j++){
                    if(hobbiesHost[i].trim().equalsIgnoreCase(hobbiesGuest[j].trim())){ //.trim() ==> supprime les espaces au début et à la fin d'une chaine 
                        pc.add(hobbiesHost[i].trim());                            //.equalsIgnoreCase ==> c'est dans le nom, ça ignore la case
                    }
                }
            }
            if(Period.between(h.getBirthday(), g.getBirthday()).getMonths()<18){
            pc.add("Ecart d'âge");
        }if(h.getGender().equalsIgnoreCase(g.genderPreference())){
            if(g.getGender().equalsIgnoreCase(h.genderPreference())){
                pc.add("Pair gender");
            }
        }
        return pc;
    }

    //Methode pour retourner une liste des points imcompatibles

    public static ArrayList<String> getPointsIncompatibles(Student h, Student g){
        ArrayList<String> PI = new ArrayList<>();
        if(h.petOwner() == true && g.allergyAnimal() == true){
            PI.add("Allergie Animal");
        }
        if(!h.hostFood().equalsIgnoreCase( g.guestFoodConstraint())){
            PI.add("Allergie Nourriture");
        }
        if(Period.between(h.getBirthday(), g.getBirthday()).getMonths()>=18){
            PI.add("Ecart d'age");
        }
        if(!h.getGender().equalsIgnoreCase(g.genderPreference())){
            PI.add("Pair gender");
        }else if(!g.getGender().equalsIgnoreCase(h.genderPreference())){
            PI.add("Pair gender");
        }
        return PI;
    }

    @Override
    public String toString() {
        return this.DATE+"("+this.paysHost.getLabel()+","+this.paysGuest.getLabel()+")";  
    }

}

//TO DO LIST : 
//fonction Affectation qui calcule les meilleures affectations et renvoie un tableau d'Affectation=>vérifier s'il fonctionne - Loïse 
