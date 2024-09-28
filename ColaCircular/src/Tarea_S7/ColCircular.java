package Tarea_S7;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ColCircular {
    private int[] cola;  // Arreglo para almacenar los elementos
    private int capacidad;  // Tamaño máximo de la cola
    private int frente;  // Índice del frente de la cola
    private int finalCola;  // Índice del final de la cola
    private int size;  // Tamaño actual de la cola

    // Constructor para inicializar la cola con un tamaño N
    public ColCircular(int N) {
        capacidad = N;
        cola = new int[capacidad];
        frente = 0;
        finalCola = -1;
        size = 0;
    }
    // Getters para acceder a los atributos
    public int getCapacidad() {
        return capacidad;
    }
    public int getTamanoActual() {
        return size;
    }
    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return size == 0;
    }

    // Método para verificar si la cola está llena
    public boolean estaLlena() {
        return size == capacidad;
    }

    // Método para agregar un elemento a la cola
    public void encolar(int elemento) {
        if (estaLlena()) {
            throw new IllegalStateException("Cola llena. No se puede agregar el elemento.");
        }
        // Incrementa el finalCola de forma circular
        finalCola = (finalCola + 1) % capacidad;
        cola[finalCola] = elemento;
        size++;
        System.out.println("Elemento encolado: " + elemento);
    }

    // Método para remover un elemento de la cola
    public int desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("Cola vacía. No se puede remover elementos.");
        }
        int elemento = cola[frente];
        frente = (frente + 1) % capacidad;
        size--;
        return elemento;
    }

    // Método para ver el primer elemento sin eliminarlo
    public int verFrente() {
        if (estaVacia()) {
            throw new NoSuchElementException("Cola vacía. No hay elementos para ver.");
        }
        return cola[frente];
    }

    // Método para ver el tamaño actual de la cola
    public int tamanoActual() {
        return size;
    }

    // Mostrar el estado de la cola
    public void mostrarCola() {
        if (estaVacia()) {
            throw new NoSuchElementException("Cola vacía. No hay elementos para mostrar.");
        }
        System.out.print("Cola: ");
        for (int i = 0; i < size; i++) {
            System.out.print(cola[(frente + i) % capacidad] + " ");
        }
        System.out.println();
    }
    //Metodo principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el tamaño de la cola al usuario
        System.out.print("Ingrese el tamaño de la cola (N): ");
        int N = scanner.nextInt();

        // Crear la cola con el tamaño especificado
        ColCircular cola = new ColCircular(N);

        boolean continuar = true;
        while (continuar) {
            String Menu = """
                          ++++Opciones++++
                          1. Encolar un elemento
                          2. Desencolar un elemento
                          3. Mostrar la cola
                          4. Ver el primer elemento
                          5. Salir
                          Seleccione una opción: """;
            System.out.println(Menu);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Encolar elementos
                    System.out.print("Ingrese el elemento a encolar: ");
                    int elemento = scanner.nextInt();
                    cola.encolar(elemento);
                    System.out.println("Elemento encolado: " + elemento);
                    break;
                case 2:
                    // Desencolar un elemento
                    int desencolado = cola.desencolar();
                    System.out.println("Elemento desencolado: " + desencolado);
                    break;
                case 3:
                    // Mostrar la cola
                    cola.mostrarCola();
                    break;
                case 4:
                    // Ver el primer elemento sin desencolar
                    int frente = cola.verFrente();
                    System.out.println("Primer elemento en la cola: " + frente);
                    break;
                case 5:
                    // Salir del programa
                    continuar = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
        scanner.close();
    }
}
