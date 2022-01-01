package BBZ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PrzelewFormat2 {
    private String identyfikator;
    private String rodzaj;
    private String nrTel;
    private int kwota;

    public PrzelewFormat2(String ident, String rodzaj, String nrTel, int kwota) {
        identyfikator = ident;
        this.rodzaj = rodzaj;
        this.nrTel = nrTel;
        this.kwota = kwota;
    }

    public String getIdentyfikator() {
        return identyfikator;
    }

    public String getNrTel() {
        return nrTel;
    }

    public int getKwota() {
        return kwota;
    }

    public String getRodzaj() {
        return rodzaj;
    }
}
