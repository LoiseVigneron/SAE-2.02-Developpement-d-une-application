package SAE;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VoyageTest {
    static Student frenchPerson1;
    static Student frenchPerson2; 
    static Student frenchPerson3;
    static Student frenchPerson4;
    static Student frenchPerson5;
    static Student frenchPerson6; 
    static Student frenchPerson7;
    static Student frenchPerson8;
    static Student germanPerson;
    static Student espPerson;
    static Student person1, person2, person3; 


    @BeforeClass
    public static void setup(){
        Map<Critere, String> criteria1 = new HashMap<>();
        criteria1.put(Critere.HOBBIES, "basket, , cinema");

        Map<Critere, String> criteria2 = new HashMap<>();
        criteria2.put(Critere.HOBBIES, "foot, jeux-vidéos, cinema, ");

        Map<Critere, String> criteria3 = new HashMap<>();
        criteria3.put(Critere.HOBBIES, "volley, lecture");

        Map<Critere, String> criteria4 = new HashMap<>();
        criteria4.put(Critere.HOBBIES, "foot, cinema");

        frenchPerson1 = new Student("Durand", "Alice", LocalDate.of(2007, 3, 12), "F", Country.FRA, criteria1);
        frenchPerson2 = new Student("Rossi", "Luca", LocalDate.of(2007, 5, 10), "M", Country.FRA, criteria2);
        frenchPerson3 = new Student("Lopez", "Maria", LocalDate.of(2007, 10, 15), "F", Country.FRA, criteria3);
        frenchPerson4 = new Student("Dupont", "Sam", LocalDate.of(2008, 12, 21), "O", Country.ALL, new HashMap<>());
        germanPerson = new Student("Schmidt", "Anna", LocalDate.of(2007, 7, 20), "F", Country.ALL, criteria4);
        espPerson = new Student("Martinez", "Roberto", LocalDate.now(), "M", Country.ESP, criteria3);
        frenchPerson5 = new Student("Olad", "Marie", LocalDate.of(2007, 3, 12), "F", Country.FRA, criteria1);
        frenchPerson6 = new Student("Poli", "Thomas", LocalDate.of(2007, 5, 10), "M", Country.FRA, criteria2);
        frenchPerson7 = new Student("Dutra", "Carla", LocalDate.of(2007, 10, 15), "F", Country.FRA, criteria3);
        frenchPerson8 = new Student("Molia", "Paul", LocalDate.of(2008, 12, 21), "O", Country.ALL, new HashMap<>());

        Map<Critere, String> criteres5 = new HashMap<>();
            criteres5.put(Critere.GUEST_ANIMAL_ALLERGY, "true");
            criteres5.put(Critere.HOST_HAS_ANIMAL, "false");
            criteres5.put(Critere.GUEST_FOOD_CONSTRAINT, "normal");
            criteres5.put(Critere.HOST_FOOD, "normal");
            criteres5.put(Critere.HOBBIES, "foot, cinema, lecture");
            criteres5.put(Critere.PAIR_GENDER, "F");
            criteres5.put(Critere.HISTORY, "true");

        Map<Critere, String> criteres6 = new HashMap<>();
            criteres6.put(Critere.GUEST_ANIMAL_ALLERGY, "false");
            criteres6.put(Critere.HOST_HAS_ANIMAL, "true");
            criteres6.put(Critere.GUEST_FOOD_CONSTRAINT, "vege");
            criteres6.put(Critere.HOST_FOOD, "vege");
            criteres6.put(Critere.HOBBIES, "volley, cinema, lecture");
            criteres6.put(Critere.PAIR_GENDER, "F");
            criteres6.put(Critere.HISTORY, "false");

        Map<Critere, String> criteres7 = new HashMap<>();
            criteres7.put(Critere.GUEST_ANIMAL_ALLERGY, "false");
            criteres7.put(Critere.HOST_HAS_ANIMAL, "true");
            criteres7.put(Critere.GUEST_FOOD_CONSTRAINT, "normal");
            criteres7.put(Critere.HOST_FOOD, "normal");
            criteres7.put(Critere.HOBBIES, "volley, cinema, lecture");
            criteres7.put(Critere.PAIR_GENDER, "F");
            criteres7.put(Critere.HISTORY, "false");


            person1 = new Student("Dubois", "Lucie", LocalDate.of(2006, 7, 12), "F", Country.FRA, criteres5);
            person2 = new Student("Loli", "Mathieu", LocalDate.of(2005, 8, 02), "M", Country.ESP, criteres6);
            person3= new Student ("Durand", "Jeanne", LocalDate.of(2005, 8, 17), "F", Country.FRA, criteres7);

    }

    /* Mettre en public la méthode nbHobbiesCommuns */
    @Test
    public void testNbHobbiesCommuns() {
        assertEquals(1, Voyage.nbHobbiesCommuns(frenchPerson1, germanPerson));
        assertEquals(2, Voyage.nbHobbiesCommuns(frenchPerson2, germanPerson));
        assertEquals(2, Voyage.nbHobbiesCommuns(frenchPerson3, espPerson));
        assertEquals(0, Voyage.nbHobbiesCommuns(null, germanPerson));
        assertEquals(0, Voyage.nbHobbiesCommuns(frenchPerson2, null));
        assertEquals(0, Voyage.nbHobbiesCommuns(frenchPerson4, espPerson));
    }

    @Test
    public void testFrenchCompatibility(){
        Voyage voyage= new Voyage(LocalDate.now(), null, null);

        boolean rep = voyage.frenchCompatibility(frenchPerson1, frenchPerson2);
        assertTrue(rep);

        rep = voyage.frenchCompatibility(frenchPerson1, frenchPerson3);
        assertFalse(rep);

        rep = voyage.frenchCompatibility(frenchPerson1, germanPerson);
        assertTrue(rep);

        rep = voyage.frenchCompatibility(espPerson, frenchPerson1);
        assertFalse(rep);

        rep =voyage.frenchCompatibility(espPerson, germanPerson); 
        assertFalse(rep);
    }

    @Test
    public void testAffectation(){
        ArrayList<Student> list1 = new ArrayList<>();
        list1.add(frenchPerson1);
        list1.add(frenchPerson2);
        list1.add(frenchPerson3);
        list1.add(frenchPerson4);
        ArrayList<Student> list2 = new ArrayList<>();
        list2.add(frenchPerson8);
        list2.add(frenchPerson7);
        list2.add(frenchPerson6);
        list2.add(frenchPerson5);
        Groupe groupeHost = new Groupe("france", list1 ,Country.FRA);
        Groupe groupeVisit = new Groupe("france", list2 ,Country.FRA);
        Voyage voyage = new Voyage(LocalDate.now(), groupeHost, groupeVisit);
        voyage.affectationCalcul();
        System.out.println(voyage.getAffectations());
    }

    @Test
    public void testCriteresCompatibility(){
        // tests à faire car modif de la fonction (ajout des allergies animal + alimentaire)
        // reprendre person1, 2, 3 + en créer d'autres avec des régimes et critères animal différents
        // (normalement guest et host food doivent être identique pour une même personne, sinon c'est pas cohérent)
        // Compter le nombre de points que ça doit faire pour vérifier 
        // faire des assertEquals(int, voyage.criteres...(personne, personne))
        // /!\ il faut instancier un voyage, sinon la fonction ne fonctionnera pas ! 
        // Loïse
    }

    @Test
    public void testPointCommun(){
        System.out.println(Voyage.getPointsCommuns(person1,person2));
        System.out.println(Voyage.getPointsCommuns(person2,person1));
        System.out.println(Voyage.getPointsCommuns(person1,person3));
        System.out.println(Voyage.getPointsCommuns(person3,person1));
        System.out.println(Voyage.getPointsCommuns(person2,person3));
        System.out.println(Voyage.getPointsCommuns(person3,person2));
    }
    @Test
    public void testPointIncompatible(){
        System.out.println(Voyage.getPointsIncompatibles(person2,person1));
        System.out.println(Voyage.getPointsIncompatibles(person1,person2));
        System.out.println(Voyage.getPointsIncompatibles(person1,person3));
        System.out.println(Voyage.getPointsIncompatibles(person3,person1));
        System.out.println(Voyage.getPointsIncompatibles(person2,person3));
        System.out.println(Voyage.getPointsIncompatibles(person3,person2));
    }
}
    
