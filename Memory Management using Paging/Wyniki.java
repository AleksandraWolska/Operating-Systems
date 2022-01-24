public class Wyniki {

    public Wyniki() {
        Algorytmy alg = new Algorytmy(10000, 2000, 1000, 4);
        System.out.println("Dla 1000 procesów, 10000 ramek (10 na proces) i 2000 roznych stron: ");
        System.out.print("Przydział równy: ");
        System.out.println(alg.ROWNY());;
        System.out.print("Przydział proporcjonalny: ");
        System.out.println(alg.PROPORCJONALNY());
        System.out.print("Przydział sterowanie częstością: ");
        System.out.println(alg.STEROWANIE());
    }
}
