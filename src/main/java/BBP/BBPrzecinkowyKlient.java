package BBP;

public class BBPrzecinkowyKlient {
    private int NrRachunku;
    private String imie;
    private String Nazwisko;
    private String pesel;

    public BBPrzecinkowyKlient(int nrRachunku, String imie, String nazwisko, String pesel) {
        NrRachunku = nrRachunku;
        this.imie = imie;
        Nazwisko = nazwisko;
        this.pesel = pesel;
    }

    public String getPesel() {
        return pesel;
    }
    public boolean compare(String pesel2){
        return pesel2.equals(pesel);
    }
}
