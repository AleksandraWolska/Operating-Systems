import java.util.ArrayList;
import java.util.Collections;

public class Proces {

    ArrayList<Strona> procesy;
    int ramkaProcesu;
    int wymianyProcesu;
    int RAMKA_SIZE;
    ArrayList<Strona> ramka = new ArrayList<Strona>();

    public Proces(ArrayList<Strona> proces, int ramkaProcesu, int wymianyProcesu) {
        this.procesy = proces;
        this.ramkaProcesu = ramkaProcesu;
        this.wymianyProcesu = wymianyProcesu;
    }



    int wymiany = 0;
    public int LRU(ArrayList<Strona> strony) {                                      //tutaj jako miarę dawności użycia ustalamy odniesienie, które inkrementujemy co kolejną strone
        ArrayList<Strona> stronyLRU = new ArrayList<Strona>();
        for (Strona s : strony) {
            stronyLRU.add(s);
        }

        int wymiany = 0;
        Strona obecna;
        boolean flag = false;

        for (int i = 0; i < stronyLRU.size(); i++) {
            flag = false;
            // System.out.println(wyswietlRamke(ramka));
            obecna = stronyLRU.get(i);


            if (ramka.size() < RAMKA_SIZE) {            //jeśli ramka ma wolne miejsca
                for (Strona s : ramka) {
                    if (s.numer == obecna.numer) {
                        s.setOdniesienie(0);                        //jesli użyto, czas od ostatniego uzycie ustawiamy na 0
                        flag = true;
                    } else {
                        s.setOdniesienie(s.odniesienie +1);         //nieuzyto kolejny raz
                    }
                }
                if (!flag) {                                        //jesli nie bylo tej ramkki, to dodajemy

                    wymiany++;
                    obecna.setOdniesienie(0);
                    ramka.add(obecna);
                }

            } else {                                    //jesli nie ma wolnych miejsc

                for (Strona s : ramka) {
                    if (s.numer == obecna.numer) {
                        s.setOdniesienie(0);
                        flag = true;
                    } else {
                        s.setOdniesienie(s.odniesienie + 1);        //nieuzyto kolejny raz
                    }
                }
                Collections.sort(ramka, Strona.odniesienieComparator);          //sortuje od najwiekszego odniesienie (najdawniejszego
                if(!flag){

                    obecna.setOdniesienie(0);
                    ramka.set(0, obecna);                                      //usuwamy stronę z najmniejszą liczbą odniesień
                    //dodajemy obecną strone
                    wymiany++;

                }


            }
        }
        setWymianyProcesu(wymianyProcesu + wymiany);
        //ramka.clear();
        return wymiany;
    }


    public ArrayList getProces() {
        return procesy;
    }

    public void setProces(ArrayList proces) {
        this.procesy = proces;
    }

    public int getRamkaProcesu() {
        return ramkaProcesu;
    }

    public void setRamkaProcesu(int ramkaProcesu) {
        this.ramkaProcesu = ramkaProcesu;
    }

    public int getWymianyProcesu() {
        return wymianyProcesu;
    }

    public void setWymianyProcesu(int wymianyProcesu) {
        this.wymianyProcesu = wymianyProcesu;
    }

    public int getRAMKA_SIZE() {
        return RAMKA_SIZE;
    }

    public void setRAMKA_SIZE(int RAMKA_SIZE) {
        this.RAMKA_SIZE = RAMKA_SIZE;
    }

    public ArrayList<Strona> getRamka() {
        return ramka;
    }

    public void setRamka(ArrayList<Strona> ramka) {
        this.ramka = ramka;
    }

    public int getWymiany() {
        return wymiany;
    }

    public void setWymiany(int wymiany) {
        this.wymiany = wymiany;
    }
}
