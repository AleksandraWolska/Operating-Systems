import java.util.Comparator;

public class Strona {

    int numer;
    int odniesienie;
    int proces;

    public Strona(int numer, int odniesienie, int proces) {
        this.numer = numer;
        this.odniesienie = odniesienie;
        this.proces = proces;
    }

    public Strona(Strona s) {
        this.numer = s.numer;
        this.odniesienie = s.odniesienie;
        this.proces = s.proces;
    }

    public Strona(int numer, int proces) {
        this.numer = numer;
        this.proces = proces;
        this.odniesienie = 0;
    }
    public Strona(int numer) {
        this.numer = numer;
        this.proces = proces;
        this.odniesienie = 0;
    }


    public static Comparator<Strona> odniesienieComparator = new Comparator<Strona>() {
        @Override
        public int compare(Strona o1, Strona o2) {
            return o2.odniesienie - o1.odniesienie;
        }
    };



    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public int getOdniesienie() {
        return odniesienie;
    }

    public void setOdniesienie(int odniesienie) {
        this.odniesienie = odniesienie;
    }

    public int getProces() {
        return proces;
    }

    public void setProces(int proces) {
        this.proces = proces;
    }
}
