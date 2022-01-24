import java.util.ArrayList;
import java.util.Random;

public class Main {

        static ArrayList <Process> procesyMain;

    public static void main(String[] args) {
        Main pr = new Main();
        pr.procesyMain = new ArrayList<Process>();
        wczytajProcesy();
        analizuj();
    }

    static void wczytajProcesy(){
        procesyMain.add(new Process(
                    1,
                    1,
                    10,
                    1,
                    0

            ));
        procesyMain.add(new Process(
                2,
                4,
                2,
                4,
                0

        ));
        procesyMain.add(new Process(
                3,
                88,
                7,
                88,
                0

        ));
        procesyMain.add(new Process(
                4,
                400,
                3,
                400,
                0

        ));
    }

//    static void wczytajProcesy(){
//        int dlFaz = 0;
//        int momWej = 0;
//
//        for (int i = 0; i < 30; i++) {
//            dlFaz = (int)(Math.random() * (20 - 0 + 1) + 0);
//            momWej = (int)(Math.random() * (50 - 0 + 1) + 0);
//
//            procesyMain.add(new Process(
//                    i,
//                    momWej,
//                    dlFaz,
//                    0,
//                    0
//
//            ));
//        }
//
//    }


    static void analizuj(){
        Procesy algProces = new Procesy();
        System.out.println(algProces.FCFS(procesyMain));
        System.out.println(algProces.SFJ(procesyMain));
        System.out.println(algProces.SRT(procesyMain));
        System.out.println(algProces.RR(5, procesyMain));

    }
}
