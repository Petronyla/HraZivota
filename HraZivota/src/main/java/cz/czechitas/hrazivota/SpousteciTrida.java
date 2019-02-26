package cz.czechitas.hrazivota;

import java.util.*;

public class SpousteciTrida {

    public static void main(String[] args) {
        Interakce text = new Interakce();
        Hra novaHra = new Hra();

        System.out.println(text.zobrazUvodniInformace());
        System.out.println(text.zobrazPravidla());
        System.out.println(text.polozDotazNaVelikostHraciPlochy());
        Scanner sc = new Scanner(System.in);
        novaHra.setRozmerHraciPlochy(sc.nextInt());

        novaHra.vygenerujPocatecniStavZivychBunek();
        System.out.println(text.polozDotazNaPocetGeneraci());
        novaHra.setPocetGeneraci(sc.nextInt());

        for (int i = 0; i < novaHra.getPocetGeneraci(); i++) {
            novaHra.provedTah();
        }
    }

}
