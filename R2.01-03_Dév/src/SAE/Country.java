package SAE;

public enum Country {
    ITA("Italie"), 
    FRA("France"),
    ESP("Espagne"),
    ALL("Allemagne");

    String cty;

    private Country(String cty){
        this.cty=cty;
    }

    public String getCountry(){
        return this.cty; 
    }
}