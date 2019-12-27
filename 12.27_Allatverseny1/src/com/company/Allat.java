package com.company;

public class Allat implements Comparable<Allat> {

    private String nev;
    private int szulEv;

    private int pontSzam;
    private static int aktualisEv;
    private static int korHatar;

    private int szepsegPont;
    private int viselkedesPont;

    //konstruktor
    public Allat(String nev, int szulEv) {
        this.nev = nev;
        this.szulEv = szulEv;
        this.pontSzam = pontSzam;
    }

    //visszaadja a kiszámított életkort
    public int eletKor() {
        return aktualisEv - szulEv;
    }

    //metódus, kiszámolja a kapott pontot
    public void pontotKap(int szepsegPont, int viselkedesPont) {
        this.szepsegPont = szepsegPont;
        this.viselkedesPont = viselkedesPont;
    }

    public int pontSzam(){
        int kor = this.eletKor();
        if (kor > Allat.korHatar) return 0;
        return (Allat.korHatar - kor) * szepsegPont + kor * viselkedesPont;
    }

    //kiíró függvény
    @Override
    public String toString() {
        return nev + ", " + this.eletKor() + " éves";
    }

    //setter getter (setterek nem kellenek)
    public String getNev() {
        return nev;
    }
    public int getSzulEv() {
        return szulEv;
    }
    public int getPontSzam() {
        return pontSzam;
    }

    //az aktuális év és korhatár minden példányra egyformán érvényes, ezért osztályszinten adjuk meg, és statikus lesz.
    public static int getAktualisEv() {
        return aktualisEv;
    }
    public static void setAktualisEv(int aktualisEv) {
        Allat.aktualisEv = aktualisEv;
    }
    public static int getKorHatar() {
        return korHatar;
    }
    public static void setKorHatar(int korHatar) {
        Allat.korHatar = korHatar;
    }

    //összehasonlítja az állatokat pontszám szerint, és sorbarakja
    @Override
    public int compareTo(Allat o) {
        return o.pontSzam - this.pontSzam;
    }
}
