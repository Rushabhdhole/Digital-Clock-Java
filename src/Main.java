import java.util.ArrayList;
import java.util.Collections;

public class PowerOfTwoMaxHeap {
    private final int childrenCount;
    private final ArrayList<Integer> heap;

    public PowerOfTwoMaxHeap(int power) {
        this.childrenCount = (int) Math.pow(2, power);
        this.heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        Collections.swap(heap, 0, heap.size() - 1);
        int max = heap.remove(heap.size() - 1);
        heapifyDown(0);
        return max;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / childrenCount;
        while (index > 0 && heap.get(index) > heap.get(parentIndex)) {
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / childrenCount;
        }
    }

    private void heapifyDown(int index) {
        int maxIndex = index;
        for (int i = 1; i <= childrenCount; i++) {
            int childIndex = childrenCount * index + i;
            if (childIndex < heap.size() && heap.get(childIndex) > heap.get(maxIndex)) {
                maxIndex = childIndex;
            }
        }

        if (maxIndex != index) {
            Collections.swap(heap, index, maxIndex);
            heapifyDown(maxIndex);
        }
    }

    public static void main(String[] args) {
        // Test with a small power value
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(1); // Binary Heap
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(6);
        heap.insert(1);
        heap.insert(8);
        heap.insert(9);

        System.out.println(heap.popMax()); // Should print 20
        System.out.println(heap.popMax()); // Should print 10

        // Test with a larger power value
        PowerOfTwoMaxHeap largeHeap = new PowerOfTwoMaxHeap(3); // 8-ary Heap
        for (int i = 0; i < 20; i++) {
            largeHeap.insert(i);
        }

        System.out.println(largeHeap.popMax()); // Should print 19
        System.out.println(largeHeap.popMax()); // Should print 18
    }
}
