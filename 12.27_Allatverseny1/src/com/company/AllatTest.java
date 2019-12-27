package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AllatTest {

    private final int aktEv = 2018;
    private final int korhatar = 10;
    private final String nev = "Bodri";
    private final int szulEv = 2015;
    private final int szepsegPont = 7;
    private final int viselkedesPont = 4;

    private Allat allat;

    @BeforeEach
    void setUp() {
        Allat.setAktualisEv(aktEv);
        Allat.setKorHatar(korhatar);

        allat = new Allat(nev, szulEv);
    }

    @Test
    public void teszt() {
        //feltételezzük, hogy az állat életkorát jól számoltuk - 3 féle képpen is lehet:
        assertTrue(allat.eletKor() == Allat.getAktualisEv() - allat.getSzulEv());
        assertEquals(allat.eletKor(), Allat.getAktualisEv() - allat.getSzulEv());
        assertEquals(allat.eletKor(),3);

        //feltételezük, hogy a verseny előtt még nincs pontszáma:
        assertEquals(allat.getPontSzam(), 0);
        allat.pontotKap(szepsegPont, viselkedesPont);

        //feltételezzük, hogy a verseny után már nem nulla a pontszám - bár ez most hamis feltételezés:
        assertFalse(allat.getPontSzam() == 0);
        assertEquals(allat.getPontSzam(), 61);

        //ellenőrizzük, hogy az életkor a korhatár fölött van-e
        allat = new Allat(nev, Allat.getAktualisEv() - Allat.getKorHatar() -1);
        //vagy így: new Allat(nev, 2007);
        assertEquals(allat.eletKor(), 11);

        //ellenőrizzük, hogy ilyen szépség- és viselkedéspont esetén valóban nulla lesz-e a pont:
        assertEquals(allat.getPontSzam(), 0);
    }
}