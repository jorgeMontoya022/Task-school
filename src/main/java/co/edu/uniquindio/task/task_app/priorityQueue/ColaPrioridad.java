package co.edu.uniquindio.task.task_app.priorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ColaPrioridad<T> {

    private ArrayList<T> heap;
    private Comparator<T> comparator;

    public ColaPrioridad(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public void add(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public T remove() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }
        T result = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return result;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (comparator.compare(heap.get(index), heap.get(parent)) >= 0) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < size && comparator.compare(heap.get(leftChild), heap.get(smallest)) < 0) {
                smallest = leftChild;
            }
            if (rightChild < size && comparator.compare(heap.get(rightChild), heap.get(smallest)) < 0) {
                smallest = rightChild;
            }
            if (smallest == index) {
                break;
            }
            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
}
