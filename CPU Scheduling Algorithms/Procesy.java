import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Procesy {

    //
    public double FCFS(ArrayList<Process> procesy) {
        ArrayList<Process> procesyFCFS = new ArrayList<Process>(procesy.size());


        for (int i = 0; i < procesy.size(); i++) {

            procesyFCFS.add(new Process(
                    procesy.get(i).getNumerProcesu(),
                    procesy.get(i).getDlugoscFazy(),
                    procesy.get(i).getMomentWejscia(),
                    procesy.get(i).getCzasPozostały(),
                    procesy.get(i).getCzasOczekiwania()
                    ));
        }


        Collections.sort(procesyFCFS, Process.Comparators.ComparatorMomentWejscia);
        double czasCalkowityOczekiwania = 0;
        int czasZakonczeniaCalkowity = procesyFCFS.get(0).getDlugoscFazy();


        for (int i = 1; i < procesyFCFS.size(); i++) {
            //jeśli proces jest pierwszym procesem
            if (czasZakonczeniaCalkowity <= procesyFCFS.get(i).getMomentWejscia()) {
                czasZakonczeniaCalkowity = procesyFCFS.get(i).getMomentWejscia() + procesyFCFS.get(i).getDlugoscFazy();

            } else {
                //jesli proces jest kolejnym
                czasCalkowityOczekiwania += (czasZakonczeniaCalkowity - procesyFCFS.get(i).getMomentWejscia());
                czasZakonczeniaCalkowity += (procesyFCFS.get(i)).getDlugoscFazy();
            }
        }
        return czasCalkowityOczekiwania / procesy.size();
    }

    //======== SFJ bez wywlaszczenia========================

    public double SFJ (ArrayList<Process> procesy) {

        int momentObecny = 0;
        double calkowityCzasOczekiwania = 0;
        ArrayList<Process> procesySJF = new ArrayList<Process>();
        ArrayList<Process> kolejka = new ArrayList<Process>();

        for (int i = 0; i < procesy.size(); i++) {

            //zapisuje do listy procesów
            procesySJF.add(new Process(
                    procesy.get(i).getNumerProcesu(),
                    procesy.get(i).getDlugoscFazy(),
                    procesy.get(i).getMomentWejscia(),
                    procesy.get(i).getCzasPozostały(),
                    procesy.get(i).getCzasOczekiwania()
            ));
        };

            Collections.sort(procesySJF, Process.Comparators.ComparatorMomentWejscia);

            do {
                for (int i = 0; i < procesySJF.size(); i++) {

                    if(momentObecny == procesySJF.get(i).getMomentWejscia()) {

                        kolejka.add(new Process(
                                0,
                                0,
                                0,
                                procesySJF.get(i).getCzasPozostały(),
                                0));
                    }
                }

                if (kolejka.size() == 0) {
                    //Collections.sort(kolejka, Process.Comparators.ComparatorCzasPozostały);
                } else if (kolejka.get(0).getCzasPozostały() == 0) {
                    calkowityCzasOczekiwania = calkowityCzasOczekiwania + (kolejka.get(0)).getCzasOczekiwania();
                    kolejka.remove(0);
                    Collections.sort(kolejka, Process.Comparators.ComparatorCzasPozostały);

                }
                momentObecny++;

                if (kolejka.size() != 0) {
                    (kolejka.get(0)).setCzasPozostały((kolejka.get(0)).getCzasPozostały() - 1);
                    for (int j = 1; j < kolejka.size(); j++) {
                        kolejka.get(j).setCzasOczekiwania(kolejka.get(j).getCzasOczekiwania() + 1);
                    }

                }
            } while (momentObecny != 1000);
        return calkowityCzasOczekiwania / procesySJF.size();
            }




    public double SRT(ArrayList<Process> procesy) {

        int momentObecny = 0;
        double calkowityCzasOczekiwania = 0;
        int counter = 0;
        ArrayList<Process> procesySRT = new ArrayList<Process>();
        ArrayList<Process> kolejka = new ArrayList<Process>();
        for (int i = 0; i < procesy.size(); i++) {
            procesySRT.add(new Process(
                    procesy.get(i).getNumerProcesu(),
                    procesy.get(i).getMomentWejscia(),
                    procesy.get(i).getDlugoscFazy(),
                    procesy.get(i).getDlugoscFazy(),
                    0));
        }
        Collections.sort(procesySRT, Process.Comparators.ComparatorMomentWejscia);

        do {
            for (int i = 0; i < procesySRT.size(); i++) {
                if (momentObecny == (procesySRT.get(i)).getMomentWejscia()) {
                    if (kolejka.size() != 0 && ((kolejka.get(0)).getCzasPozostały() < (procesySRT.get(i)).getCzasPozostały())) {
                        counter++;
                    }

                    kolejka.add(new Process(0, 0, 0, (procesySRT.get(i)).getCzasPozostały(), 0));
                    Collections.sort(kolejka, Process.Comparators.ComparatorCzasPozostały);

                }
            }

            momentObecny++;

            if (kolejka.size() != 0) {
                //zmienjszenie czasu pozostałego zgodnie z "zegarem"
                (kolejka.get(0)).setCzasPozostały((kolejka.get(0)).getCzasPozostały() - 1);


                for (int j = 1; j < kolejka.size(); j++) {
                    (kolejka.get(j)).setCzasOczekiwania((kolejka.get(j)).getCzasOczekiwania() + 1);
                }
                if ((kolejka.get(0)).getCzasPozostały() == 0) {
                    calkowityCzasOczekiwania += (kolejka.get(0)).getCzasOczekiwania();
                    kolejka.remove(0);
                }

            }
        }
        while (momentObecny != 100000);
        // System.out.println("Ilosc wywlaszczen: " + licznikWywlaszczen / 2);
        return calkowityCzasOczekiwania / procesySRT.size();

            }


    public double RR(int k, ArrayList<Process> procesy)
    {
        int kwant = 0;
        int momentObecny = 0;
        double calkowityCzasOczekiwania = 0;
        ArrayList<Process> procesyRR = new ArrayList<Process>();
        ArrayList<Process> kolejka = new ArrayList<Process>();
        for (int i = 0; i < procesy.size(); i++) {
            procesyRR.add(new Process(
                    procesy.get(i).getNumerProcesu(),
                    procesy.get(i).getMomentWejscia(),
                    procesy.get(i).getDlugoscFazy(),
                    procesy.get(i).getDlugoscFazy(),
                    0));
        }
        Collections.sort(procesyRR, Process.Comparators.ComparatorMomentWejscia);

        do {


            for (int i = 0; i < procesyRR.size(); i++) {
                if (momentObecny == (procesyRR.get(i)).getMomentWejscia()) {
                    kolejka.add(new Process(
                            0,
                            procesyRR.get(i).getMomentWejscia(),
                            0,
                            procesyRR.get(i).getCzasPozostały(),
                            0));
                }
            }



            if (kwant <= 0 && kolejka.size() !=0) {


                if ((kolejka.get(0)).getCzasPozostały() == 0) {
                    // System.out.println("kwant zerowy");
                    calkowityCzasOczekiwania = calkowityCzasOczekiwania + (kolejka.get(0)).getCzasOczekiwania();
                    kolejka.remove(0);
                    Collections.sort(kolejka, Process.Comparators.ComparatorPriorytetWejscie);


                    if (kolejka.size() != 0)
                    {
                        kolejka.get(0).setNumerProcesu((kolejka.get(0)).getNumerProcesu() + 1);
                        if (kolejka.get(0).getCzasPozostały() >= k) {
                            kwant = k;
                        } else {
                            //  System.out.println("mniejszy kwant");
                            kwant = kolejka.get(0).getCzasPozostały();
                        } }



                } else {

                    if (kolejka.size() == 1) {
                        (kolejka.get(0)).setNumerProcesu((kolejka.get(0)).getNumerProcesu() + 1);

                    } else {
                        kolejka.add(kolejka.get(0));
                        kolejka.remove(0);
                        Collections.sort(kolejka, Process.Comparators.ComparatorPriorytetWejscie);
                        kolejka.get(0).setNumerProcesu((kolejka.get(0)).getNumerProcesu() + 1);
                    }



                    if (kolejka.get(0).getCzasPozostały() >= k) {
                        kwant = k;
                    } else {
                        //  System.out.println("mniejszy kwant");
                        kwant = (kolejka.get(0)).getCzasPozostały();
                    }
                }
            }



            if (kolejka.size() != 0) {
                // System.out.println(kolejka);
                kolejka.get(0).setCzasPozostały((kolejka.get(0)).getCzasPozostały() - 1);
                for (int j = 1; j < kolejka.size(); j++) {
                    (kolejka.get(j)).setCzasOczekiwania((kolejka.get(j)).getCzasOczekiwania() + 1);
                }

            }

            momentObecny++;
            kwant--;

        }
        while (momentObecny != 100000);
        return calkowityCzasOczekiwania / procesyRR.size();

    }









    }





