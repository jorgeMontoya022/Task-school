package co.edu.uniquindio.task.task_app.priorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clase que implementa una Cola de Prioridad (Priority Queue) usando un heap
 * binario.
 * Permite agregar elementos con prioridad, eliminarlos y consultar su tamaño.
 * 
 * @param <T> Tipo de datos que manejará la cola de prioridad.
 */
public class ColaPrioridad<T> {

    /** Lista que representa el heap (montón) */
    private ArrayList<T> heap;

    /** Comparador que define la prioridad de los elementos */
    private Comparator<T> comparator;

    /**
     * Constructor que recibe un comparador para establecer la prioridad.
     * 
     * @param comparator Comparador que define la prioridad de los elementos
     */
    public ColaPrioridad(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * Agrega un nuevo elemento a la cola de prioridad y reordena el heap hacia
     * arriba.
     * 
     * @param element Elemento a agregar
     */
    public void add(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    /**
     * Elimina y retorna el elemento con mayor prioridad (el que esté más arriba en
     * el heap).
     * 
     * @return Elemento con mayor prioridad
     * @throws NoSuchElementException si la cola está vacía
     */
    public T remove() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }

        T result = heap.get(0); // Elemento con mayor prioridad
        T last = heap.remove(heap.size() - 1); // Último elemento del heap

        // Si aún hay elementos, reemplazar la raíz y reordenar hacia abajo
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return result;
    }


      /**
     * Elimina y retorna el elemento con mayor prioridad, o null si la cola está vacía.
     * Este método es similar a remove() pero no lanza excepción si la cola está vacía.
     * 
     * @return Elemento con mayor prioridad o null si la cola está vacía
     */
    public T poll() {
        if (heap.isEmpty()) {
            return null;
        }
        return remove();
    }

    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Retorna el número de elementos en la cola de prioridad.
     * 
     * @return Tamaño de la cola
     */
    public int size() {
        return heap.size();
    }

    /**
     * Reordena el heap hacia arriba desde un índice dado para mantener la propiedad
     * del heap.
     * 
     * @param index Índice desde donde se debe hacer el reordenamiento
     */
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            // Si el elemento actual tiene mayor prioridad, termina
            if (comparator.compare(heap.get(index), heap.get(parent)) >= 0) {
                break;
            }

            swap(index, parent); // Intercambia con el padre
            index = parent; // Sube al padre
        }
    }

    /**
     * Reordena el heap hacia abajo desde un índice dado para mantener la propiedad
     * del heap.
     * 
     * @param index Índice desde donde se debe hacer el reordenamiento
     */
    private void heapifyDown(int index) {
        int size = heap.size();

        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            // Compara con el hijo izquierdo
            if (leftChild < size && comparator.compare(heap.get(leftChild), heap.get(smallest)) < 0) {
                smallest = leftChild;
            }

            // Compara con el hijo derecho
            if (rightChild < size && comparator.compare(heap.get(rightChild), heap.get(smallest)) < 0) {
                smallest = rightChild;
            }

            // Si ya está en orden, se rompe el ciclo
            if (smallest == index) {
                break;
            }

            swap(index, smallest); // Intercambia con el hijo de menor valor
            index = smallest; // Baja al nuevo índice
        }
    }

    /**
     * Intercambia dos elementos en el heap.
     * 
     * @param i Índice del primer elemento
     * @param j Índice del segundo elemento
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

        /**
     * Convierte la cola de prioridad a una lista sin modificar la cola original.
     * 
     * @return Lista con los elementos de la cola de prioridad ordenados por prioridad
     */
    public List<T> toList() {
        List<T> resultado = new ArrayList<>();
        
        // Si la cola está vacía, retornar lista vacía
        if (isEmpty()) {
            return resultado;
        }
        
        // Crear una copia de la cola
        ColaPrioridad<T> colaTemp = new ColaPrioridad<>(this.comparator);
        
        // Extraer todos los elementos
        T elemento;
        while ((elemento = this.poll()) != null) {
            resultado.add(elemento);
            colaTemp.add(elemento);
        }
        
        // Restaurar la cola original
        while ((elemento = colaTemp.poll()) != null) {
            this.add(elemento);
        }
        
        return resultado;
    }
}
