package BBP;

public class PrzelewBBP {
    private String rodzaj;
    private String pesel;
    private String tytuł;
    private int kwota;

    public PrzelewBBP(String rodzaj, String pesel, String tytuł, int kwota) {
        this.rodzaj = rodzaj;
        this.pesel = pesel;
        this.tytuł = tytuł;
        this.kwota = kwota;
    }

    public String getPesel() {
        return pesel;
    }

    public int getKwota() {
        return kwota;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public String getTytuł() {
        return tytuł;
    }
}
