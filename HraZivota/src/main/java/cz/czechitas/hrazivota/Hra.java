package cz.czechitas.hrazivota;

import java.util.*;

public class Hra {

    /**
     * An integer to set the lenght and the width of arrays of booleans aktualniGenerace and budouciGenerace
     */
    private int rozmerHraciPlochy;

    /**
     * An array of booleans which representatives if cells in current generation are alive or dead
     */
    private boolean aktualniGenerace[][];

    /**
     * An array of booleans which representatives if cells in next generation are alive or dead
     */
    private boolean budouciGenerace[][];

    /**
     * Randomly generated integer from 1 to rozmerHraciPlochy
     */
    private int nahodneCisloOd1doRozmeruHraciPlochy;

    /**
     * An area of arrays aktualniGenerace and budouciGenerace
     */
    private int velikostHraciPlochy;

    /**
     * The count of live neighborhood
     */
    private int pocetZivychSousednichBunek;

    /**
     * The count of generation to show
     */
    private int pocetGeneraci;

    /**
     * The method sets lenght and width of arrays aktualniGenerace and budouciGenerace. The method also
     * calculates velikostHraciPlochy
     * @param newValue The value is read from user
     */
    public void setRozmerHraciPlochy(int newValue) {
        if (newValue <= 0) {
            throw new IllegalArgumentException("Rozměr hrací plochy musí být celé číslo větší než 0.");
        } else {
            rozmerHraciPlochy = newValue;
            aktualniGenerace = new boolean[rozmerHraciPlochy][rozmerHraciPlochy];
            budouciGenerace = new boolean[rozmerHraciPlochy][rozmerHraciPlochy];
            velikostHraciPlochy = rozmerHraciPlochy * rozmerHraciPlochy;
        }
    }

    /**
     * The method gets count of generation
     * @return pocetGeneraci
     */
    public int getPocetGeneraci() {
        return pocetGeneraci;
    }

    /**
     * The method sets lenght and width of arrays aktualniGenerace and budouciGenerace
     * @param newValue The value is read from user
     */
    public void setPocetGeneraci(int newValue) {
        if (newValue <= 0) {
            throw new IllegalArgumentException("Počet generací musí být celé číslo větší než 0.");
        } else {
            pocetGeneraci = newValue;
        }
    }

    /**
     * The method shows the current array values
     */
    public void vykresliAktualniGeneraci() {
        System.out.println();
        for (int i = 0; i < rozmerHraciPlochy; i++) {
            for (int j = 0; j < rozmerHraciPlochy; j++) {
                System.out.print(aktualniGenerace[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * The method shows the current array values
     */
    public void vykresliBudouciGeneraci() {
        System.out.println();
        for (int i = 0; i < rozmerHraciPlochy; i++) {
            for (int j = 0; j < rozmerHraciPlochy; j++) {
                System.out.print(budouciGenerace[i][j] + " ");
            }
            System.out.println();
        }
    }
    //TODO sloučit obě předcházející metody do jedné s parametrem (pro 2D pole se mi to zatím nepovedlo)

    /**
     * The method generates random iteger from 1 to rozmerHraciPlochy
     * @return random integer
     */
    public int generujNahodneCisloOd1doRozmeruHraciPlochy() {
        Random generatorNahodnychCisel = new Random();
        int nahodneCislo = generatorNahodnychCisel.nextInt(rozmerHraciPlochy);
        nahodneCisloOd1doRozmeruHraciPlochy = nahodneCislo;
        return nahodneCisloOd1doRozmeruHraciPlochy;
    }

    /**
     * The method randomly generate first generation of live cells and saves it itno the arrays
     * aktualniGenerace i budouciGenerace
     */
    public void vygenerujPocatecniStavZivychBunek() {
        int pocetOpakovani = (int) (velikostHraciPlochy * 0.4);
        for (int i = 0; i < pocetOpakovani; i++) {
            int a = generujNahodneCisloOd1doRozmeruHraciPlochy();
            int b = generujNahodneCisloOd1doRozmeruHraciPlochy();
            if (aktualniGenerace[a][b] == true) {
                i--;
            } else
                aktualniGenerace[a][b] = true;
            budouciGenerace[a][b] = true;
        }
        vykresliAktualniGeneraci();
    }

    /**
     * The method makes a change from the current generation of cells to the next generation and the new
     * state shows
     */
    public void provedTah() {
        pocetZivychSousednichBunek = 0;
        aktualizujAktualniGeneraci();
        for (int i = 0; i < rozmerHraciPlochy; i++) {
            for (int j = 0; j < rozmerHraciPlochy; j++) {

                try {
                    //testuje okolí každé buňky a sebe sama a počítá počet živých sousedních buněk
                    for (int a = i - 1; a <= i + 1; a++) {
                        if ((a >= 0) && (a < rozmerHraciPlochy)) {
                            for (int b = j - 1; b <= j + 1; b++) {
                                if ((b >= 0) && (b < rozmerHraciPlochy)) {
                                    if (aktualniGenerace[a][b] == true) {
                                        pocetZivychSousednichBunek++;
                                    }
                                }
                            }
                        }
                    }

                    //pokud je aktuální buňka živá, je potřeba ji odečíst, protože není soused
                    if (aktualniGenerace[i][j] == true) {
                        pocetZivychSousednichBunek--;
                    }

                    //podmínky zániku a zrodu buněk v pořáadí v jakém jsou vypsána pravidla do konzole
                    if ((aktualniGenerace[i][j] == true) && (pocetZivychSousednichBunek < 2)) {
                        budouciGenerace[i][j] = false;

                    } else if (((aktualniGenerace[i][j] == true) && (pocetZivychSousednichBunek == 2)) ||
                            ((aktualniGenerace[i][j] == true) && (pocetZivychSousednichBunek == 3))) {
                        budouciGenerace[i][j] = true;

                    } else if ((aktualniGenerace[i][j] == true) && (pocetZivychSousednichBunek > 3)) {
                        budouciGenerace[i][j] = false;

                    } else if ((aktualniGenerace[i][j] == false) && (pocetZivychSousednichBunek == 3)) {
                        budouciGenerace[i][j] = true;

                    } else {
                        budouciGenerace[i][j] = false;
                    }

                } catch (RuntimeException e) {

                }

                pocetZivychSousednichBunek = 0;
            }
        }
        vykresliBudouciGeneraci();
    }

    /**
     * The method copies the array representing the next generation into the array representing
     * the current generation
     * @return aktualniGenerace
     */
    public boolean[][] aktualizujAktualniGeneraci() {
        for (int i = 0; i < budouciGenerace.length; i++) {
            aktualniGenerace[i] = budouciGenerace[i].clone();
        }
        return aktualniGenerace;
    }

}




