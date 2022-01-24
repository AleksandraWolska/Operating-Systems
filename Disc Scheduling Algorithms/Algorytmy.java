package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Algorytmy {

    static final int MAX = 200;


    public int FCFS(ArrayList<Zgloszenie> lista, int start) {

        ArrayList<Zgloszenie> listaFCFS = new ArrayList<Zgloszenie>();
        for (Zgloszenie z : lista) {
            listaFCFS.add(new Zgloszenie(z));
        }


        int calDroga = 0;
        int odleglosc;
        int pozycja;

        for (int i = 0; i < listaFCFS.size(); i++) {
            pozycja = listaFCFS.get(i).getCylinder();
            odleglosc = Math.abs(pozycja - start);
            calDroga += odleglosc;
            start = pozycja;
        }
        return calDroga;
    }
    //=========================================================================================

    public int SSTF(ArrayList<Zgloszenie> lista, int start) {

        ArrayList<Zgloszenie> listaSSTF = new ArrayList<Zgloszenie>();
        for (Zgloszenie z : lista) {
            listaSSTF.add(new Zgloszenie(z));
        }

        int calDroga = 0;
        int rozmiar = listaSSTF.size();


        while (rozmiar >= 0) {
            Zgloszenie.porownajOdlegloscWObieStrony(start, listaSSTF);
            for (Zgloszenie z : listaSSTF) {
                if (!z.getZakonczone()) {
                    z.setZakonczone(true);
                    calDroga += Math.abs(start - z.getCylinder());
                    start = z.getCylinder();
                    break;
                }
            }
            rozmiar--;
        }
        return calDroga;

    }

    public void wyswietlListę(ArrayList<Zgloszenie> lista) {
        for (Zgloszenie z: lista ) {
            System.out.println(z.getCylinder());
        }
    }

    public int SCAN(ArrayList<Zgloszenie> lista, int start) {

        ArrayList<Zgloszenie> listaSCAN = new ArrayList<Zgloszenie>();
        for (Zgloszenie z : lista) {
            listaSCAN.add(new Zgloszenie(z));
        }

        int calDroga = 0;
        int kierunek = 1;


          while (!sprawdzZrobione(listaSCAN)) {                                         //dopoki wszyskie nie są zrobione

                Zgloszenie.porownajOdlegloscWTyl(start, listaSCAN);                     //idzie w lewo

                while (kierunek == 1) {
                    for (Zgloszenie z : listaSCAN) {

                        if (z.getCylinder() >= start) {                                  //jeśli cylinder bedzie po prawej stronie od obecnego
                            calDroga += Math.abs(start - 0);
                            kierunek = 2;
                            start = 0;
                            break;                                                      //wyjscie z iteracji i pętli
                        } else {
                            if (!z.getZakonczone()) {                                   //jesli jest po drodze i nie jest zakonczone
                                z.setZakonczone(true);
                                calDroga += Math.abs(start - z.getCylinder());
                                start = z.getCylinder();
                            }
                        }
                    }
                }

                Zgloszenie.porownajOdlegloscWPrzod(start, listaSCAN);                   //sortujemy w prawo

                while (kierunek == 2) {

                    for (Zgloszenie z : listaSCAN) {
                        if (z.getCylinder() == listaSCAN.get(listaSCAN.size() - 1).getCylinder()) {     //jesli dojdziemy do ostatniego elementu
                            calDroga += Math.abs(start - z.getCylinder());
                            if (!sprawdzZrobione(listaSCAN)) {                                          //jesli lista nie zostala wykonana, doliczamy drogę do końca
                                calDroga += Math.abs(z.cylinder - MAX);
                            }
                            z.setZakonczone(true);
                            kierunek = 1;
                            start = MAX;
                            break;
                        } else {
                            if (!z.getZakonczone()) {
                                z.setZakonczone(true);
                                calDroga += Math.abs(start - z.getCylinder());
                                start = z.getCylinder();

                            }
                        }

                    }

                }
            }

        return calDroga;

    }

    public int CSCAN(ArrayList<Zgloszenie> lista, int start) {

        ArrayList<Zgloszenie> listaCSCAN = new ArrayList<Zgloszenie>();
        for (Zgloszenie z : lista) {
            listaCSCAN.add(new Zgloszenie(z));
        }

        int calDroga = 0;

        while (!sprawdzZrobione(listaCSCAN)) {                          //dopóki wszystkie nie zostaną wykonane

            Zgloszenie.porownajOdlegloscWPrzod(start, listaCSCAN);      //najbliższe w przód
                for (Zgloszenie z : listaCSCAN) {

                    if (z.getCylinder() < start) {                      //jeśli kolejny cyliner jest mniejszy od obecnego (odwrócenie)
                        calDroga += Math.abs(start - MAX) + MAX;        //dojeżdża do końca, doliczamy droge na poczatek
                        start = 0;
                        break;
                    } else {
                        if (!z.getZakonczone()) {
                            z.setZakonczone(true);
                            calDroga += Math.abs(start - z.getCylinder());
                            start = z.getCylinder();
                        }
                    }



                }
        }

        return calDroga;

    }


    public int EDF(ArrayList<Zgloszenie> lista, int start) {

        ArrayList<Zgloszenie> listaEDF = new ArrayList<Zgloszenie>();
        for (Zgloszenie z : lista) {
            listaEDF.add(new Zgloszenie(z));
        }

        int calDroga = 0;
        Collections.sort(listaEDF, Zgloszenie.deadlineComparator);




        for (Zgloszenie z : listaEDF) {
            if (Math.abs(z.cylinder - start) <= (z.deadline) - calDroga) {        //jeśli droga glowicy jest dłuższa niż deadline, ignorujemy zgłoszenie
                calDroga += Math.abs(start - z.cylinder);
                start = z.cylinder;
                z.setZakonczone(true);
            }
        }


        System.out.println("Niewykonane żądania w EDF: " + sprawdzNiewykonane(listaEDF));
        return calDroga;

    }


    public int EDSCAN(ArrayList<Zgloszenie> lista, int start) {

        ArrayList<Zgloszenie> listaEDSCAN = new ArrayList<Zgloszenie>();
        for (Zgloszenie z : lista) {
            listaEDSCAN.add(new Zgloszenie(z));
        }

        int calDroga = 0;
        Collections.sort(listaEDSCAN, Zgloszenie.deadlineComparator);
        int counterNiewykonanych = 0;



        for (Zgloszenie z : listaEDSCAN) {
            if (!z.zakonczone &&Math.abs(z.cylinder - start) <= (z.deadline) - calDroga) {        //jeśli droga do zgloszenia jest dłuzsza niz deadline, ignorujemy zloszenie
                for (Zgloszenie k : listaEDSCAN) {
                    if (!k.zakonczone && ((k.cylinder > start && k.cylinder < z.cylinder) || (k.cylinder < start && k.cylinder > z.cylinder))) {    //jesli jest po drodze głowicy i nie zostało wykonane, to wykoujemy
                        k.setZakonczone(true);
                    }
                }
                calDroga += Math.abs(start - z.cylinder);
                start = z.cylinder;
                z.setZakonczone(true);
            }
        }


        System.out.println("Niewykonane żądania w ED-SCAN: " + sprawdzNiewykonane(listaEDSCAN));
        return calDroga;

    }









    boolean sprawdzZrobione (ArrayList<Zgloszenie> lista) {
        boolean flag = true;
        for (Zgloszenie z : lista) {

            if (!z.getZakonczone()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    int sprawdzNiewykonane (ArrayList<Zgloszenie> lista) {
        int counterNiewykonane = 0;
        for (Zgloszenie z : lista) {
            if (!z.zakonczone) {
                counterNiewykonane ++;
            }
        }
        return counterNiewykonane;
    }





}
