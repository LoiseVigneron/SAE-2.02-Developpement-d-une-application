package SAE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* Classe qui va permettre l'importation des données basées sur les formulaires des élèves */
public class Gestion {
    private static final String PATH = System.getProperty("user.dir")+File.separator+"R2.01-03_Dév"+File.separator+"res"+File.separator; 
    private static final String SOURCE = "import.csv";
    private static final String EXPORT = "export.csv";
    private static final String SERIAL_PATH = "historique.json"; 

    /* Méthode static permettant l'importation des données des formulaires */
    public static ArrayList<String> importData(){
        ArrayList<String> tableImport = new ArrayList<String>();
        try(Scanner scan = new Scanner(new File(PATH+SOURCE))){
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                tableImport.add(line);
            }
        }catch(FileNotFoundException fnfe){
            System.err.println("Fichier introuvable : "+fnfe);
        }catch(Exception e){
            System.err.println("Une produite s'est produite : "+e);
        }
        return tableImport; 
    }

    public static ArrayList<Student> createStudents(ArrayList<String> tableImport){
        ArrayList<Student> studentsList= new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            for (int i=1;i<tableImport.size();i++) {
                String[] values=tableImport.get(i).split(";",-1);

                /*Récupération des informations de l'élèves (hors critères)*/
                String name=values[1].trim();
                String forename=values[0].trim(); 
                Country nationality=Country.valueOf(values[2].trim().toUpperCase().substring(0, 3));
                LocalDate date;
                if(values[3].equals(" ")){
                    date=null;
                }else{
                    date=LocalDate.parse(values[3].trim(), formatter);
                }
                String gender=values[9].trim();

                Map<Critere, String> criteres= new HashMap<>(); 
                criteres.put(Critere.GUEST_ANIMAL_ALLERGY, values[4].trim());
                criteres.put(Critere.HOST_HAS_ANIMAL,values[5].trim());
                criteres.put(Critere.GUEST_FOOD_CONSTRAINT,values[6].trim());
                criteres.put(Critere.HOST_FOOD,values[7].trim());
                criteres.put(Critere.HOBBIES,values[8].trim());
                criteres.put(Critere.PAIR_GENDER,values[10].trim());
                criteres.put(Critere.HISTORY,values[11].trim());


                studentsList.add(new Student(name, forename, date, gender, nationality, criteres));
            }
        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println("Index en dehors des bornes pour StudentList : "+ie);
        }catch (IllegalArgumentException ae){
            System.err.println("Erreur dans la lecture des données : "+ae);
        }catch (Exception e){
            System.err.println("Une erreur s'est produite : "+e);
        }
        
        return studentsList; 
    }

    /* Affichage d'une ArrayList */
    public static <T> void displayArrayList(ArrayList<T> liste){
            for(T t : liste){
                System.out.println(t);
            }
    }
    //Jsp si ça va nous servir, mais au cas où - Loïse 

    public static Groupe createGroup(ArrayList<Student> studentsList, Country pays){
        ArrayList<Student> liste = new ArrayList<>(); 
        for(Student s : studentsList){
            if(s.getCountry()==pays && s.isValid()){
                liste.add(s);
            }
        }

        Groupe groupe=new Groupe(pays.getCountry(), liste, pays);
        return groupe; 
    }

    public static void exportData(ArrayList<Affectation> affectations){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH+EXPORT))){
            writer.write("HOSTS;GUESTS");
            writer.newLine();
            for (Affectation a : affectations){
                writer.write(a.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'exportation des données : "+ e);
        }
    }

    public static void exportHistorique(Historique h){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH+SERIAL_PATH))) {
            oos.writeObject(h);
        }catch (IOException ioe){
            System.err.println("Erreur pendant la sauvegarde : "+ioe);
        }
    }

    public static Historique importHistorique(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH+SERIAL_PATH))){
            return (Historique) ois.readObject(); 
        } catch(IOException ioe){
            System.err.println("Erreur pendant l'importation des données : "+ioe);
        }catch(ClassNotFoundException ce){
            System.err.println("Aucune correspondance trouvé : "+ce);
        }
        return null; 
    }

    public static void main(String[] args) {
        ArrayList<String> tableImport = Gestion.importData(); 
        // Gestion.displayArrayList(tableImport);
        ArrayList<Student> studentsList= Gestion.createStudents(tableImport); 
        // Gestion.displayArrayList(studentsList);
        Groupe france = Gestion.createGroup(studentsList, Country.FRA); 
        Groupe italie = Gestion.createGroup(studentsList, Country.ITA);

        Groupe allemagne = Gestion.createGroup(studentsList, Country.ALL);
        Gestion.displayArrayList(allemagne.getStudentsList());
        
        Groupe espagne = Gestion.createGroup(studentsList, Country.ESP);
        Gestion.displayArrayList(espagne.getStudentsList());

        // System.out.println("-------------");
        // System.out.println(france.toStringAll());
        // System.out.println("====");
        // System.out.println(italie.toStringAll());
        
        Voyage voyage= new Voyage(LocalDate.now(), allemagne, espagne);
        voyage.affectationCalcul();
        
        System.out.println("---------------");
        System.out.println(voyage.getAffectations().toString());
        Gestion.exportData(voyage.getAffectations());

        
        Voyage voyage2 = new Voyage(LocalDate.now(), italie, italie);

        ArrayList<Voyage> listeVoyages = new ArrayList<>(); 
        listeVoyages.add(voyage);
        listeVoyages.add(voyage2); 

        Historique h = new Historique(listeVoyages); 
        System.out.println(h.toString());
        Gestion.exportHistorique(h); 
        System.out.println("====");
        Historique h2 = Gestion.importHistorique();
        System.out.println(h2.toString());
    }
}




