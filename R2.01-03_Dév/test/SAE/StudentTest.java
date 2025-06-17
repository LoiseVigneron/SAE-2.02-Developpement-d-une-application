package SAE;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StudentTest {
    static Student person1, person2, person3, person4, person5; 

    @BeforeAll
    public static void setup(){
        Map<Critere, String> criteres1 = new HashMap<>();
            criteres1.put(Critere.GUEST_ANIMAL_ALLERGY, "true");
            criteres1.put(Critere.HOST_HAS_ANIMAL, "false");
            criteres1.put(Critere.GUEST_FOOD_CONSTRAINT, "normal");
            criteres1.put(Critere.HOST_FOOD, "normal");
            criteres1.put(Critere.HOBBIES, "foot, cinema, lecture");
            criteres1.put(Critere.PAIR_GENDER, "F");
            criteres1.put(Critere.HISTORY, "true");

        Map<Critere, String> criteres2 = new HashMap<>();
            criteres2.put(Critere.GUEST_ANIMAL_ALLERGY, "false");
            criteres2.put(Critere.HOST_HAS_ANIMAL, "true");
            criteres2.put(Critere.GUEST_FOOD_CONSTRAINT, "normal");
            criteres2.put(Critere.HOST_FOOD, "normal");
            criteres2.put(Critere.HOBBIES, "volley, cinema, lecture");
            criteres2.put(Critere.PAIR_GENDER, "F");
            criteres2.put(Critere.HISTORY, "false");

        Map<Critere, String> criteres3 = new HashMap<>();
            criteres3.put(Critere.GUEST_ANIMAL_ALLERGY, "yes");
            criteres3.put(Critere.HOST_HAS_ANIMAL, "yes");
            criteres3.put(Critere.GUEST_FOOD_CONSTRAINT, "normal");
            criteres3.put(Critere.HOST_FOOD, "normal");
            criteres3.put(Critere.HOBBIES, "volley, cinema, lecture");
            criteres3.put(Critere.PAIR_GENDER, "F");
            criteres3.put(Critere.HISTORY, "false");

            Map<Critere, String> criteres4 = new HashMap<>();
            criteres4.put(Critere.GUEST_ANIMAL_ALLERGY, "yes");
            criteres4.put(Critere.HOST_HAS_ANIMAL, "no");
            criteres4.put(Critere.GUEST_FOOD_CONSTRAINT, "normal");
            criteres4.put(Critere.HOST_FOOD, "no nuts");
            criteres4.put(Critere.HOBBIES, "volley, cinema, lecture");
            criteres4.put(Critere.PAIR_GENDER, "F");
            criteres4.put(Critere.HISTORY, "false");

             Map<Critere, String> criteres5 = new HashMap<>();
            criteres5.put(Critere.GUEST_ANIMAL_ALLERGY, "yes");
            criteres5.put(Critere.HOST_HAS_ANIMAL, "yes");
            criteres5.put(Critere.GUEST_FOOD_CONSTRAINT, "normal");
            criteres5.put(Critere.HOST_FOOD, "no nuts");
            criteres5.put(Critere.HOBBIES, "volley, cinema, lecture");
            criteres5.put(Critere.PAIR_GENDER, "F");
            criteres5.put(Critere.HISTORY, "false");

            person1 = new Student("Dubois", "Lucie", LocalDate.of(2006, 7, 12), "F", Country.FRA, criteres1);
            person2 = new Student("Loli", "Mathieu", LocalDate.of(2005, 8, 02), "M", Country.ESP, criteres2);
            person3= new Student ("Durand", "Jeanne", LocalDate.of(2005, 8, 17), "F", Country.FRA, criteres3);
            person4 = new Student("Smith", "Peter", LocalDate.of(2005, 5, 24), "M", Country.ITA, criteres4);
            person5 = new Student("Calvez", "Max", LocalDate.of(2006, 1, 22), "O", Country.ESP, criteres5);
    }

    @Test
    public void testStudent(){
        assertFalse(person1.equals(person2));
        assertTrue(person1.equals(person1));
        assertTrue(person1.ancientPair());
        assertFalse(person2.ancientPair());
        assertFalse(person1.petOwner());
        assertTrue(person2.petOwner());
        assertTrue(person1.allergyAnimal());
        assertFalse(person2.allergyAnimal());

    }

    @Test
    public void testIsValid(){
        assertTrue(person1.isValid());
        assertTrue(person2.isValid());
        assertFalse(person3.isValid());
        assertFalse(person4.isValid());
        assertFalse(person5.isValid());
    }
}