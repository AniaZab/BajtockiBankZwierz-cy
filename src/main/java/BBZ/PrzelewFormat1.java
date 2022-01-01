package BBZ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PrzelewFormat1 {
    private String identyfikator;
    private String rodzaj;
    private String imię;
    private String nazwisko;
    private String pesel;
    private int kwota;
    private String imieW;
    private String nazwiskoW;
    private String tytuł;

    public PrzelewFormat1(String ident,String rodzaj, String imię, String nazwisko, String pesel, int kwota, String imieW, String nazwiskoW, String tytuł) {
        identyfikator = ident;
        this.rodzaj = rodzaj;
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.kwota = kwota;
        this.imieW = imieW;
        this.nazwiskoW = nazwiskoW;
        this.tytuł = tytuł;
    }

    public String getIdentyfikator() {
        return identyfikator;
    }

    public String getImię() {
        return imię;
    }

    public String getNazwisko() {
        return nazwisko;
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

    public String getImieW() {
        return imieW;
    }

    public String getNazwiskoW() {
        return nazwiskoW;
    }
}
