import BBP.BankPrzecinkowy;
import BBZ.BankZwierzęcy;

public class Main {
    private static final String PATH = ".//Dane//";
   // private static final String PATH = "C://Users//aniaz//Documents//studia//semestr5//podstawy_Java//BajtockiBankZwierzęcy//Dane//";

    public static void main(String[] args) {
        BankPrzecinkowy p = new BankPrzecinkowy(PATH + "BDKlientówBBP.txt", PATH + "przelewyBBP.txt");
        BankZwierzęcy z = new BankZwierzęcy(PATH + "bdKlientówBBZ.txt", PATH + "przelewyBBZ_f1.txt", PATH + "przelewyBBZ_f2.txt");
        z.generujRaport(PATH + "raportZ.txt");
        p.generujRaport(PATH + "raportP.txt");
    }
}
