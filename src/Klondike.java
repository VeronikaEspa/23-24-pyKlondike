import java.util.Scanner;

class Klondike {

    private Baraja baraja;
    private Palo[] palos;
    private Descarte descarte;
    private Columna[] columnas;

    private final int NUM_PALOS = 4;
    private final int NUM_COLUMNAS = 7;

    public Klondike() {
        baraja = new Baraja();
        descarte = new Descarte();
        palos = new Palo[NUM_PALOS];
        for (int palo = 0; palo < NUM_PALOS; palo++) {
            palos[palo] = new Palo();
        }
        columnas = new Columna[NUM_COLUMNAS];
        for (int columna = 0; columna < NUM_COLUMNAS; columna++) {
            columnas[columna] = new Columna(baraja, columna + 1);
        }
    }

    public void jugar() {
        boolean estaJugando = true;
        do {
            Menu menu = new Menu();
            menu.mostrar();
            mostrarTapete();
            int opcion = menu.getOpcion();
            switch (opcion) {
                case 1:
                    baraja.moverA(descarte);
                    break;
                case 2:
                    descarte.moverA(this.escogerPalo("A"));
                    break;
                case 3:
                    descarte.moverA(this.escogerColumna("A"));
                    break;
                case 4:
                    this.escogerPalo("De").moverA(this.escogerColumna("A"));
                    break;
                case 5:
                    this.escogerColumna("De").moverA(this.escogerPalo("A"));
                    break;
                case 6:
                    this.escogerColumna("De").moverA(this.escogerColumna("A"));
                    break;
                case 7:
                    this.escogerColumna("De").voltear();
                    break;
                case 8:
                    descarte.voltear(baraja);
                    break;
                case 9:
                    estaJugando = !estaJugando;
                    break;
            }
        } while (estaJugando);
    }

    private Palo escogerPalo(String prefijo) {
        System.out.println(prefijo + " que palo? (1-4)");
        int palo = new Scanner(System.in).nextInt();
        return palos[palo - 1];
    }

    private Columna escogerColumna(String prefijo) {
        System.out.println(prefijo + " que columna? (1-7)");
        int columna = new Scanner(System.in).nextInt();
        return columnas[columna - 1];
    }

    private void mostrarTapete() {
        baraja.mostrar();
        descarte.mostrar();
        for (int palo = 0; palo < 4; palo++) {
            palos[palo].mostrar();
        }
        for (int columna = 0; columna < 7; columna++) {
            columnas[columna].mostrar();
        }
    }

    public static void main(String[] args) {
        new Klondike().jugar();
    }
}