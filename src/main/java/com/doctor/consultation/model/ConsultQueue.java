package com.doctor.consultation.model;

import java.util.Arrays;

public class ConsultQueue {
    private int maxSize;
    private int currentSize;
    private int[] heap;

    public ConsultQueue(int capacity) {
        this.maxSize = capacity;
        this.currentSize = 0;
        this.heap = new int[this.maxSize];
    }

    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    public boolean isFull() {
        return this.currentSize == this.maxSize;
    }

    public void enqueuePatient(int age) {
        if (this.isFull()) {
            System.out.println("ConsultQueue is full. Unable to enqueue patient");
        } else {
            this.heap[this.currentSize] = age;
            this.heapifyUp(this.currentSize);
            ++this.currentSize;
        }
    }

    public int getSize() {
        return this.currentSize;
    }

    public int dequeuePatient() {
        if (this.isEmpty()) {
            System.out.println("ConsultQueue is empty. Unable to dequeue.");
            return -1;
        } else {
            int root = this.heap[0];
            this.heap[0] = this.heap[this.currentSize - 1];
            --this.currentSize;
            this.heapifyDown(0);
            return root;
        }
    }

    public int peek() {
        if (this.currentSize == 0) {
            throw new IllegalStateException("Queue is empty.");
        } else {
            return this.heap[0];
        }
    }

    private void heapifyUp(int index) {
        for(int parent = (index - 1) / 2; index > 0 && this.heap[index] > this.heap[parent]; parent = (parent - 1) / 2) {
            this.swap(index, parent);
            index = parent;
        }

    }

    private void heapifyDown(int index) {
        int largest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        if (leftChild < this.currentSize && this.heap[leftChild] > this.heap[index]) {
            largest = leftChild;
        }

        if (rightChild < this.currentSize && this.heap[rightChild] > this.heap[largest]) {
            largest = rightChild;
        }

        if (largest != index) {
            this.swap(index, largest);
            this.heapifyDown(largest);
        }

    }

    private void swap(int i, int j) {
        int temp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = temp;
    }

    public int[] getHeap() {
        return Arrays.copyOf(this.heap, this.currentSize);
    }
}
