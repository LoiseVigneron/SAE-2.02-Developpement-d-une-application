package SAE;

import java.io.Serializable;
import java.util.ArrayList;

public class Groupe implements Serializable{
    /* Attributs */
    private String label;
    private ArrayList <Student> studentsList;
    private Country pays;
    
    /*Constructeurs */
    public Groupe(String label, ArrayList<Student> studentsList, Country pays){
        this.label = label;
        this.studentsList = studentsList;
        this.pays = pays;
    }

    public Groupe(String label, Country pays){
        this(label, new ArrayList<Student>(), pays);
    }

    /* Getters */
    public String getPays(){
        return this.pays.getCountry();
    }

    public String getLabel(){
        return this.label;
    }

    public ArrayList<Student> getStudentsList(){
        return this.studentsList; 
    }

    public void addStudent(Student s){
        if(s.getCountry()==this.pays){
            this.studentsList.add(s);
        }
    }
    // on le laisse en void ou on fait un boolean ? - Loïse

    @Override
    public String toString(){
        return this.label+"("+this.pays.getCountry()+") : "+this.studentsList.size(); 
    }

    
    public String toStringAll(){
        return this.label+"("+this.pays.getCountry()+") : "+this.studentsList.toString();
    }
}