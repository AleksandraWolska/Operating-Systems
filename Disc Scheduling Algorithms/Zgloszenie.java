package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Zgloszenie {

    int przybycie;
    int cylinder;
    int deadline;
    boolean zakonczone = false;

    public Zgloszenie(int przybycie, int cylinder) {
        this.przybycie = przybycie;
        this.cylinder = cylinder;
        this.zakonczone = false;
    }

    public Zgloszenie(int przybycie, int cylinder, int deadline) {
        this.przybycie = przybycie;
        this.cylinder = cylinder;
        this.deadline = deadline;
        this.zakonczone = false;
    }

    public Zgloszenie(Zgloszenie z) {
        this.przybycie = z.przybycie;
        this.cylinder = z.cylinder;
        this.deadline = z.deadline;
        this.zakonczone = false;
    }


    // komparatory
    public static void porownajOdlegloscWObieStrony (final int obecnaPozycja, ArrayList a) {

        Comparator<Zgloszenie> najblizszaOdlegloscComparator= new Comparator<Zgloszenie>() {
            public int compare(Zgloszenie o1, Zgloszenie o2) {
                return Math.abs(o1.cylinder - obecnaPozycja) - Math.abs(o2.cylinder - obecnaPozycja);
            }
        };
        Collections.sort(a, najblizszaOdlegloscComparator );
    }



    public static void porownajOdlegloscWPrzod(final int obecnaPozycja, ArrayList a) {

        Comparator<Zgloszenie> najblizszaOdlegloscJednostronnaComparator = new Comparator<Zgloszenie>() {

            public int compare(Zgloszenie o1, Zgloszenie o2) {

                if (o1.cylinder - obecnaPozycja > 0) {
                    if (o2.cylinder - obecnaPozycja > 0) {
                        return (o1.cylinder - obecnaPozycja) - (o2.cylinder - obecnaPozycja);
                    }
                    return -1;
                } else if (o1.cylinder - obecnaPozycja > 0) {
                    return 1;
                } else
                    return 0;
            }

        };
        Collections.sort(a, najblizszaOdlegloscJednostronnaComparator);

    }
    public static void porownajOdlegloscWTyl(final int obecnaPozycja, ArrayList a) {


        Comparator<Zgloszenie> closestTaskComparator1 = new Comparator<Zgloszenie>()
        {

            public int compare(Zgloszenie o1, Zgloszenie o2) {


                if (o1.cylinder - obecnaPozycja < 0) {
                    if (o2.cylinder - obecnaPozycja < 0) {

                        return Math.abs(o1.cylinder - obecnaPozycja) - Math.abs(o2.cylinder - obecnaPozycja);
                    }
                    return -1;
                } else if (o1.cylinder - obecnaPozycja < 0) {
                    return 1;
                } else
                    return 0;
            }


        };
        Collections.sort(a, closestTaskComparator1);

    }



    public static Comparator<Zgloszenie> deadlineComparator = new Comparator<Zgloszenie>() {
        @Override
        public int compare(Zgloszenie o1, Zgloszenie o2) {
            return o1.deadline + o1.przybycie - o2.deadline + o1.przybycie;
        }
    };


    public static Comparator<Zgloszenie> deadlineArrivalComparator = new Comparator<Zgloszenie>() {
        @Override
        public int compare(Zgloszenie o1, Zgloszenie o2) {
            int result =  o1.deadline - o2.deadline;
            if(result != 0)
                return  result;
            else
            {
                return o1.przybycie - o2.przybycie;
            }
        }
    };


    public String toString()
    {
        return przybycie + " " + cylinder + " " + deadline;
    }














    public int getPrzybycie() {
        return przybycie;
    }

    public void setPrzybycie(int przybycie) {
        this.przybycie = przybycie;
    }

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public boolean getZakonczone() {
        return zakonczone;
    }

    public void setZakonczone(boolean zakonczone) {
        this.zakonczone = zakonczone;
    }
}
