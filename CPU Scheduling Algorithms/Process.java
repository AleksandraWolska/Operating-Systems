import java.util.Comparator;

public class Process {

    private int numerProcesu;
    private int dlugoscFazy;
    private int momentWejscia;
    private int czasPozostały;
    private int czasOczekiwania;

    public Process(int numerProcesu, int dlugoscFazy, int momentWejscia, int czasPozostały, int czasOczekiwania) {
        this.numerProcesu = numerProcesu;
        this.dlugoscFazy = dlugoscFazy;
        this.momentWejscia = momentWejscia;
        this.czasPozostały = czasPozostały;
        this.czasOczekiwania = 0;
    }


    public int getNumerProcesu() {
        return numerProcesu;
    }

    public void setNumerProcesu(int numerProcesu) {
        this.numerProcesu = numerProcesu;
    }

    public int getDlugoscFazy() {
        return dlugoscFazy;
    }

    public void setDlugoscFazy(int dlugoscFazy) {
        this.dlugoscFazy = dlugoscFazy;
    }

    public int getMomentWejscia() {
        return momentWejscia;
    }

    public void setMomentWejscia(int momentWejscia) {
        this.momentWejscia = momentWejscia;
    }

    public int getCzasPozostały() {
        return czasPozostały;
    }

    public void setCzasPozostały(int czasPozostały) {
        this.czasPozostały = czasPozostały;
    }

    public int getCzasOczekiwania() {
        return czasOczekiwania;
    }

    public void setCzasOczekiwania(int czasOczekiwania) {
        this.czasOczekiwania = czasOczekiwania;
    }


    //===================================================================

    public static class Comparators {
        public static Comparator<Process> ComparatorMomentWejscia = new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.momentWejscia - o2.momentWejscia;
            }
        };
                public static Comparator<Process> ComparatorDlugoscFazy = new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.dlugoscFazy - o2.dlugoscFazy;
            }
        };
                public static Comparator<Process> ComparatorCzasPozostały = new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.czasPozostały - o2.czasPozostały;
            }
        };
                public static Comparator<Process> ComparatornumerProcesu = new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.numerProcesu - o2.numerProcesu;
            }
        };
                public static Comparator<Process> ComparatorPriorytetWejscie = new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {

                int res = o1.numerProcesu - o2.numerProcesu;
                if (res != 0) {
                    return res;
                } else{
                    return o1.momentWejscia - o2.momentWejscia;
                }
            }
        };


    }
    public String toString(){
        return "Numer procesu: " + numerProcesu + "   Moment wejscia: " + momentWejscia + "   dlugosc fazy: " + dlugoscFazy + " Czas pozostały: "+ czasPozostały + " czasOczekiwania: " + czasOczekiwania;
    }
}
