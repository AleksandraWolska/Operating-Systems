package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    static ArrayList<Zgloszenie> lista;
    public static void main(String[] args) {
       lista = new ArrayList<Zgloszenie>();
        Algorytmy alg = new Algorytmy();
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            lista.add(new Zgloszenie(0, rand.nextInt(200), rand.nextInt(500)+1));
        }



//        lista.add(new Zgloszenie(0, 98));
//        lista.add(new Zgloszenie(0, 183));
//        lista.add(new Zgloszenie(0, 37));
//        lista.add(new Zgloszenie(0, 6));
//        lista.add(new Zgloszenie(0, 14));
//        lista.add(new Zgloszenie(0, 124));
//        lista.add(new Zgloszenie(0, 65));
//        lista.add(new Zgloszenie(0, 67));


        System.out.println("FCFS: " + alg.FCFS(lista, 53));
        System.out.println("SSTF: " + alg.SSTF(lista, 53));
        System.out.println("SCAN: " + alg.SCAN(lista, 53));
        System.out.println("CSCAN: " + alg.CSCAN(lista, 53));
        System.out.println("EDF: " + alg.EDF(lista, 53));
        System.out.println("ED-SCAN: " + alg.EDSCAN(lista, 53));

    }
}
