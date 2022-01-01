package BBP;

import BBZ.BBZwierzęcyKlient;
import BBZ.PrzelewFormat1;
import BBZ.PrzelewFormat2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankPrzecinkowy {
    private String pathKlienci;
    private String pathPrzelewy;
    private int iloscOdrzuconychTranzakcji = 0;
    private int iloscZaksięgowanychTranzakcji = 0;
    private int sumaZaksięgowanychKwot = 0;
    private ArrayList<BBPrzecinkowyKlient> klienci = new ArrayList<>();
    private ArrayList<PrzelewBBP> przelewy = new ArrayList<>();

    public BankPrzecinkowy(String p1, String p2) {
        pathKlienci = p1;
        pathPrzelewy = p2;
        readKlienci();
        readPrzelewy();
    }
    private void readKlienci() {
        try {
            Scanner scanner = new Scanner(new File(pathKlienci));
            scanner.useDelimiter("(\\n)|,|(\\r)");

            while (scanner.hasNext()) {
                try {

                    int nrRachunku = Integer.parseInt(scanner.next());
                    String imie = scanner.next();
                    String nazwisko = scanner.next();
                    String pesel = scanner.next();
                    if(Integer.toString(nrRachunku).length()==9 && pesel.length()==11)
                        klienci.add(new BBPrzecinkowyKlient(nrRachunku, imie, nazwisko, pesel));
                }
                catch (Exception e){
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        }
    }
    private void readPrzelewy() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(pathPrzelewy));
            for (String line : allLines) {
                String[] przelew = line.split(",");
                try {
                    PrzelewBBP p = new PrzelewBBP(przelew[0], przelew[1], przelew[2], Integer.parseInt(przelew[3]));
                    przelewy.add(p);
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
    public boolean sprawdzPrzelew(PrzelewBBP przelew){
        if(przelew.getPesel().length() == 11 && przelew.getKwota()>0 && czyJestKlientP(przelew.getPesel())&&
                przelew.getRodzaj().length()<=10 && przelew.getTytuł().length()<=20){
            try{
                Long.parseLong(przelew.getPesel());
                return true;
            }
            catch(Exception e){
            }
        }
        return false;
    }
    private boolean czyJestKlientP(String pesel){
        for(BBPrzecinkowyKlient klient: klienci){
            //if(klient.compare(pesel))
            if(klient.compare(pesel)){
                return true;
            }
        }
        return false;
    }
    public void przejdźWszystkiePrzelewy(){
        for(PrzelewBBP p: przelewy){
            if(sprawdzPrzelew(p)){
                iloscZaksięgowanychTranzakcji++;
                sumaZaksięgowanychKwot+=p.getKwota();
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
