package BBZ;

import java.util.Objects;

public class BBZwierzęcyKlient {
    private String kodPocztowy;
    private String ulubioneZwierze;
    private String nickZwierzęcia;
    private String nrTel;
    private String imie;
    private String Nazwisko;
    private String pesel;
    private int NrRachunku;

    public BBZwierzęcyKlient(String kodPocztowy, String ulubioneZwierze, String nickZwierzęcia, String nrTel, String imie, String nazwisko, String pesel, int nrRachunku) {
        this.kodPocztowy = kodPocztowy;
        this.ulubioneZwierze = ulubioneZwierze;
        this.nickZwierzęcia = nickZwierzęcia;
        this.nrTel = nrTel;
        this.imie = imie;
        Nazwisko = nazwisko;
        this.pesel = pesel;
        NrRachunku = nrRachunku;
    }
    public boolean compare(String imie2, String nazwisko2, String pesel2)
    {
        return imie.equals(imie2) && nazwisko2.equals(Nazwisko) && pesel2.equals(pesel);
    }
    public boolean compareTel(String tel){
        return nrTel.equals(tel);
    }
}
