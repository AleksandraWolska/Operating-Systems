import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
ramki konkretnych oprocesow są zamieszczone w danych klasach
 */

public class Algorytmy {

    int FRAME_SIZE = 1000;
    int iloscStron = 100;
    int iloscProcesow = 100;                //ilosc procesow
    int interwal;
    ArrayList<Proces> tablicaProcesow;
    ArrayList<Strona> ramka = new ArrayList<Strona>();



        Random rand = new Random();
    public Algorytmy(int FRAME_SIZE, int iloscStron, int iloscProcesow, int interwal) {
        this.FRAME_SIZE = FRAME_SIZE;
        this.iloscStron = iloscStron;
        this.iloscProcesow = iloscProcesow;
        this.interwal = interwal;
        tablicaProcesow = new ArrayList<Proces>();


        for (int j = 0; j < iloscProcesow; j++){            //każdy proces

            ArrayList<Strona> str =  new ArrayList<Strona>();       //tworze liste potrzebnych stron

            int ilosc = rand.nextInt(iloscStron);
            for (int k = 0; k < ilosc; k++) {
                str.add(new Strona(rand.nextInt(iloscProcesow)));
            }

                tablicaProcesow.add(new Proces(str, FRAME_SIZE, 0));
        }

    }




    int ROWNY() {
        ArrayList<Proces> procesyROWNY = new ArrayList<Proces>();
        for (Proces p : tablicaProcesow) {
            procesyROWNY.add(p);
        }

        int sumaWymian = 0;

        int wielkoscRamkiProcesu = FRAME_SIZE/(procesyROWNY.size());                        //wielkos ramki = ilość dostepnych ramek / ilość procesów
        for (Proces p : procesyROWNY) {
            sumaWymian += LRU(p.procesy, wielkoscRamkiProcesu);
        }
        return sumaWymian;
    }




    int PROPORCJONALNY() {
        ArrayList<Proces> procesyPROPORCJONALNY = new ArrayList<Proces>();
        for (Proces p : tablicaProcesow) {
            procesyPROPORCJONALNY.add(p);
        }

        int sumaWymian = 0;


        int wielkoscRamkiProcesu =0;                                //wielkos ramki = strony danego procesu / strony wszystkich procesow * ilosc ramek
        for (Proces p : procesyPROPORCJONALNY) {
            wielkoscRamkiProcesu = p.procesy.size()* FRAME_SIZE/iloscStron ;
            if (wielkoscRamkiProcesu < 1) {
                wielkoscRamkiProcesu = 1;
            }
            sumaWymian += LRU(p.procesy, wielkoscRamkiProcesu);
        }
        return sumaWymian;
    }


    int STEROWANIE() {
        ArrayList<Proces> procesySTEROWANIE = new ArrayList<Proces>();
        for (Proces p : tablicaProcesow) {
            procesySTEROWANIE.add(p);
        }
        int sumaWymian = 0;
        int obecneWymiany = 0;
        int wielkoscRamkiProcesu = FRAME_SIZE/(procesySTEROWANIE.size());           //poczatkowo proporcjaoalny
        int pozyczone = 0;

        for (Proces p : procesySTEROWANIE) {
            p.setRamkaProcesu(wielkoscRamkiProcesu);
            obecneWymiany = LRU(p.procesy, p.ramkaProcesu);
            if (obecneWymiany >= 0.5 * wielkoscRamkiProcesu) {                  //jesli suma wymian jest za wysoka (przekracza 50% rozmairu ramki
                pozyczone += (int )(0.01 * FRAME_SIZE) ;
                if (pozyczone >= (int )(0.01 * FRAME_SIZE)) {
                    p.ramkaProcesu = p.ramkaProcesu + pozyczone ;
                }
                obecneWymiany = LRU(p.procesy, p.ramkaProcesu);
            } else if ( obecneWymiany <= 0.05 * wielkoscRamkiProcesu) {         //za niska (mniejsza niz 5% wielkosci ramki
                pozyczone += (int )(0.01 * FRAME_SIZE) ;
                if (pozyczone <= (int )(0.01 * FRAME_SIZE)) {
                    p.ramkaProcesu = p.ramkaProcesu - pozyczone ;
                    pozyczone =- (int )(0.01 * FRAME_SIZE);
                }
                p.ramkaProcesu = p.ramkaProcesu - (int )(0.01 * FRAME_SIZE) ;
                obecneWymiany = LRU(p.procesy, p.ramkaProcesu);
            }
            sumaWymian += obecneWymiany;
        }

    return sumaWymian;
    }

















    public int LRU(ArrayList<Strona> strony, int rozmiarRamki) {                                      //tutaj jako miarę dawności użycia ustalamy odniesienie, które inkrementujemy co kolejną strone
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


            if (ramka.size() < rozmiarRamki) {            //jeśli ramka ma wolne miejsca
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

        ramka.clear();
        return wymiany;
    }


}
