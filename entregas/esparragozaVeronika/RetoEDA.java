import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class RetoEDA {
    private static Baraja baraja;

    public static void main(String[] args) {
        baraja = new Baraja();
        ArrayList<Carta> conjuntoCartas = new ArrayList<>();
        System.out.println("Baraja inicial: ");
        Carta carta;
        do {
            carta = baraja.sacar();
            carta.voltear();
            carta.mostrar();
            conjuntoCartas.add(carta);
        } while (!baraja.vacia());

        System.out.println();
        new RetoEDA().imprimirMenu(conjuntoCartas);
    }

    private static void bubbleSort(ArrayList<Carta> cartas, Comparator<Carta> comparator) {
        boolean sorted = false;
        int passIndex = 0;
        while (!sorted) {
            sorted = true;
            for (int j = 0; j < cartas.size() - 1 - passIndex; j++) {
                Carta cartaActual = cartas.get(j);
                Carta siguienteCarta = cartas.get(j + 1);
                if (comparator.compare(cartaActual, siguienteCarta) > 0) {
                    cartas.set(j, siguienteCarta);
                    cartas.set(j + 1, cartaActual);
                    sorted = false;
                }
            }
            passIndex++;
        }
    }

    private void imprimirMenu(ArrayList<Carta> conjuntoCartas) {
        System.out.println("1. Ordenar por Número");
        System.out.println("2. Ordenar por Palo");
        System.out.println("3. Salir");
        opcionesMenu(conjuntoCartas);
    }

    private void opcionesMenu(ArrayList<Carta> conjuntoCartas) {
        int opcion;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("-> ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    ordenarPorNumero(conjuntoCartas);
                    mostrarBaraja(conjuntoCartas);
                    break;
                case 2:
                    ordenarPorPalo(conjuntoCartas);
                    mostrarBaraja(conjuntoCartas);
                    break;
                case 3:
                    System.out.println("Adiós");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 3);
    }

    private static void ordenarPorNumero(ArrayList<Carta> conjuntoCartas) {
        bubbleSort(conjuntoCartas, Comparator.comparing(Carta::getNumero).thenComparing(Carta::getPalo));
    }

    private static void ordenarPorPalo(ArrayList<Carta> conjuntoCartas) {
        bubbleSort(conjuntoCartas, Comparator.comparing(Carta::getPalo).thenComparing(Carta::getNumero));
    }

    private static void mostrarBaraja(ArrayList<Carta> conjuntoCartas) {
        for (Carta carta : conjuntoCartas) {
            carta.mostrar();
        }
        System.out.println();
    }
}
