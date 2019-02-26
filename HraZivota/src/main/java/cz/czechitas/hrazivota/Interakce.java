package cz.czechitas.hrazivota;

public class Interakce {

    public String zobrazUvodniInformace() {
        return "Dobrý den, vítejte ve Hře života. V této hře vznikají a zanikají buňky. Zrod i zánik probíhá " +
                "podle přesně daných pravidel.\n";
    }

    public String zobrazPravidla() {
        return  "Pravidla hry jsou následující:" +
                "\nKaždá živá buňka s méně než dvěma živými sousedy zemře." +
                "\nKaždá živá buňka se dvěma nebo třemi živými sousedy zůstává žít." +
                "\nKaždá živá buňka s více než třemi živými sousedy zemře." +
                "\nKaždá mrtvá buňka s právě třemi živými sousedy oživne.\n";
    }

    public String polozDotazNaVelikostHraciPlochy() {
        return "Zadejte velikost hracího pole:";
    }

    public String polozDotazNaPocetGeneraci() {
        return "\nKolik následujících generací chcete vidět?";
    }

}
