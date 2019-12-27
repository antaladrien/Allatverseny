package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final String adatfajl = "c:\\Users\\user\\IdeaProjects\\allatok.txt";
    private final int aktualisEv = Calendar.getInstance().get(Calendar.YEAR);
    private final int korHatar = 10;
    private final int szepsegPonthatar = 11;
    private final int viselkedesPonthatar = 11;

    private List<Allat> allatok = new ArrayList<>();

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        statikusAdatok();
        adatBevitel();
        resztvevok();
        verseny();
        eredmenyek("\nA verseny eredménye:");
        legjobbak();
        pontszamSzerintRendezve();
        eredmenyek("\nPontszám szerint rendezve:");
    }

    private void statikusAdatok() {
        Allat.setAktualisEv(aktualisEv);
        Allat.setKorHatar(korHatar);
    }

    //adatok beolvasása:
    private void adatBevitel() {
        try {
            Scanner sc = new Scanner(new File(adatfajl));
            String[] adatok;
            String sor;
            while (sc.hasNextLine()) {
                sor = sc.nextLine();
                if (!sor.isEmpty()) {
                    adatok = sor.split(";");
                    allatok.add(new Allat(adatok[0], Integer.parseInt(adatok[1])));
                }
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //kiíratjuk a beolvasott adatokat:
    private void resztvevok() {
        System.out.println("\nA verseny résztvevői:");
        for (Allat allat : allatok) {
            System.out.println(allat);
        }
    }

    //most véletlenül generáljuk a pontokat:
    private void verseny() {
        Random rand = new Random();
        int szepseg, viselkedes;
        for (Allat allat : allatok) {
            szepseg = rand.nextInt(szepsegPonthatar);
            viselkedes = rand.nextInt(viselkedesPonthatar);
            allat.pontotKap(szepseg, viselkedes);
        }
    }

    //a szűri pontozása után kiíratjuk az eredményt:
    private void eredmenyek(String cim) {
        System.out.println(cim);
        for (Allat allat : allatok) {
            System.out.println(String.format("%s %d pont", allat.getNev(), allat.pontSzam()));
        }
    }

    //mindig gondolni kell a holtversenyre, ezért kell többes szám:
    private void legjobbak() {
        int max = allatok.get(0).pontSzam();
        for (Allat allat : allatok) {
            if (allat.pontSzam() > max) {
                max = allat.pontSzam();
            }
        }

        System.out.printf("\nA legjobb(ak) %d pontszámmal:\n", max);
        for (Allat allat : allatok) {
            if (allat.pontSzam() == max) {
                System.out.println(allat.getNev());
            }
        }
    }

    //rendezést követően írjuk ki az eredményt:
    private void pontszamSzerintRendezve() {
        Collections.sort(allatok);
    }
}