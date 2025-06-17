package SAE;


public enum Critere {
    GUEST_ANIMAL_ALLERGY('B'),
    HOST_HAS_ANIMAL('B'),
    GUEST_FOOD_CONSTRAINT('T'),
    HOST_FOOD('T'),
    HOBBIES('T'),
    PAIR_GENDER('T'),
    HISTORY('T');
/* B = boolean
 * T = String
 * N = numeric 
 */
    private char type; 

    private Critere(char type){
        this.type=type; 
    }

    public char getType() {
        return this.type;
    }
}
