package com.doctor.consultation.model;

import java.util.Arrays;

public class ConsultQueue {
    private int[] heap;
    private int size;

    public ConsultQueue(int maxSize) {
        heap = new int[maxSize];
        size = 0;
    }

    public void enqueuePatient(int patientId) {
        heap[size] = patientId;
        siftUp(size);
        size++;
    }
    public int getSize() {
        return size;
    }

    public int dequeuePatient() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return root;
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }

        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void siftUp(int index) {
        int parent = (index - 1) / 2;

        while (index > 0 && heap[index] < heap[parent]) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void siftDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        if (leftChild < size && heap[leftChild] < heap[smallest]) {
            smallest = leftChild;
        }

        if (rightChild < size && heap[rightChild] < heap[smallest]) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            siftDown(smallest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public int[] getHeap() {
        return Arrays.copyOf(heap, size);
    }


}

