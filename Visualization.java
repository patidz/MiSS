/**
 * Rysowanie lasu i wiru
 */
public class Visualization {
    private int[] customColorMap;

    void paintVortex(int[] X, int[] Y, int[] sMatrix, int[] uMatrix, int[] vMatrix){}

    /**
     * rysuje zniszczenia w lesie
     * @param X
     * @param Y
     * @param treeMatrix
     */
    void paintForest2D(int[] X, int[] Y, int[] treeMatrix){}
}

/*
Po wprowadzeniu przez uzytkownika danych oraz kliknieciu przycisku 'start symulacji' tworzone sa obiekty klas
Simulation, HWIND i Rankine. Nastepnie w obiekcie simulation ustawiane sa referencje do utworzonych klas poprzez
metody initializeForest() i initializeVortex() oraz tworzone sa instancje klas TreeData i VortexData. Po
skonczeniu tych czynnosci nastepuje wywolanie funkcji doSimulation() i oddanie sterowania do obiektu simulation.
Wywolywana jest metoda generateTreeMatrix(), ktora tworzy poczatkowa macierz lasu. Nastepnie obliczana jest macierz
wiatru calculateWindSpeed() w obiekcie vortexData, ktora z kolei wywoluje metode calculateWind() i przesuwa srodek
tornada za pomoca calculateNewCenter() w obiekcie rankine. OBiekt simulation pobiera z obiektu vortexData macierze
predkosci i macierze wektorow, a nastepnie korzystajac z tych danych wywoluje metode calculateTreeForce() w obiekcie
hwind. Tak obliczone macierze wyswietlane sa w GUI a pomoca paintVortex() i paintForest2D() w klasie Visualization.
Obiekt simulation sprawdza czy srodek tornada nie wyszedl poza widoczny obszar siatki za pomoca metody
origOutOfBound() w obiekcie vortexData lub czy nie przekroczono limitu licznika petli ustawionego na wartosc 100.
Jesli ktorych z tych warunkow zostal spelniony to symulacja zostaje zakonczona
 */

