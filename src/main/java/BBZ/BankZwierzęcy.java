package BBZ;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BankZwierzęcy {
    private String pathKlienci;
    private String pathPrzelewy1;
    private String pathPrzelewy2;
    private int iloscOdrzuconychTranzakcji = 0;
    private int iloscZaksięgowanychTranzakcji = 0;
    private int sumaZaksięgowanychKwot = 0;
    private ArrayList<BBZwierzęcyKlient> klienci = new ArrayList<>();
    private ArrayList<PrzelewFormat1> przelewy1 = new ArrayList<>();
    private ArrayList<PrzelewFormat2> przelewy2 = new ArrayList<>();

    public BankZwierzęcy(String p1, String p2, String p3) {
        pathKlienci = p1;
        pathPrzelewy1 = p2;
        pathPrzelewy2= p3;
        readKlienci();
        readPrzelewy1();
        readPrzelewy2();
    }

    private void readKlienci() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(pathKlienci));
            for (String line : allLines) {
                String[] klient = line.split("\\|");
                try {
                    if(klient[6].length()==11 && klient[7].length()==9){
                        BBZwierzęcyKlient k = new BBZwierzęcyKlient(klient[0], klient[1], klient[2], klient[3],
                               klient[4], klient[5],klient[6], Integer.parseInt(klient[7]));
                        klienci.add(k);
                    }
                }
                catch(Exception e){
                    System.out.println("Ten klient jest niepoprawnie zapisany!!! \n"+line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readPrzelewy1() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(pathPrzelewy1));
            for (String line : allLines) {
                String[] przelew = line.substring(25).split(" ");
                try {
                    PrzelewFormat1 p = new PrzelewFormat1(line.substring(0, 15), line.substring(15, 25), przelew[0], przelew[1], przelew[2], Integer.parseInt(przelew[3]), przelew[4], przelew[5], przelew[6]);
                    przelewy1.add(p);
                }
                catch(Exception e){
                    iloscOdrzuconychTranzakcji++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readPrzelewy2() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(pathPrzelewy2));
            for (String line : allLines) {
                String[] przelew = line.substring(25).split(" ");
                try{
                    PrzelewFormat2 p = new PrzelewFormat2(line.substring(0,15), line.substring(15,25), przelew[0],Integer.parseInt(przelew[1]));
                    przelewy2.add(p);
                }
                catch(Exception e){
                    iloscOdrzuconychTranzakcji++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean sprawdzPrzelew1(PrzelewFormat1 przelew){
        if(przelew.getIdentyfikator().equals("KLASYCZNY      ") && przelew.getRodzaj().length()==10 &&
                czyJestKlientP1(przelew.getImię(),przelew.getNazwisko(),przelew.getPesel()) &&
                przelew.getImieW().length()<=20 && przelew.getNazwiskoW().length()<=20 &&
                przelew.getKwota()>0)
        {
            try{
                Long.parseLong(przelew.getPesel());
                return true;
            }
            catch(Exception e){
            }
        }
        return false;
    }
    private boolean czyJestKlientP1(String imie2, String nazwisko2, String pesel2){
        for(BBZwierzęcyKlient klient: klienci){
            if(klient.compare(imie2, nazwisko2, pesel2))
                return true;
        }
        return false;
    }
    public boolean sprawdzPrzelew2(PrzelewFormat2 przelew){
        if(przelew.getIdentyfikator().equals("TELEFONICZNY   ") && przelew.getRodzaj().length()==10 &&
                czyJestKlientP2(przelew.getNrTel()) && przelew.getKwota()>0)
            return true;
        else
            return false;
    }
    private boolean czyJestKlientP2(String tel){
        for(BBZwierzęcyKlient klient: klienci){
            if(klient.compareTel(tel))
                return true;
        }
        return false;
    }
    public void przejdźWszystkiePrzelewy(){
        for(PrzelewFormat1 p1: przelewy1){
            if(sprawdzPrzelew1(p1)){
                iloscZaksięgowanychTranzakcji++;
                sumaZaksięgowanychKwot+=p1.getKwota();
            }
            else{
                iloscOdrzuconychTranzakcji++;
            }
        }
        for(PrzelewFormat2 p2: przelewy2){
            if(sprawdzPrzelew2(p2)){
                iloscZaksięgowanychTranzakcji++;
                sumaZaksięgowanychKwot+=p2.getKwota();
            }
            else{
                iloscOdrzuconychTranzakcji++;
            }
        }
    }
    public void generujRaport(String path){
        try {
            FileWriter fileWriter = new FileWriter(path);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            przejdźWszystkiePrzelewy();
            printWriter.println("Liczbę transakcji zaksięgowanych: "+iloscZaksięgowanychTranzakcji);
            printWriter.println("Liczbę transakcji odrzuconych: "+iloscOdrzuconychTranzakcji);
            printWriter.println("Sumę zaksięgowanych kwot: "+sumaZaksięgowanychKwot);
            printWriter.close();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
