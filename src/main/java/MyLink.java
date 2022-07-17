import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.IntConsumer;

public class MyLink<E extends Number> implements ILink<E>{
    private ListElement<E> head;
    private ListElement<E> last;
    private int size;

    private static class ListElement<E> {
        ListElement<E> prev;
        ListElement<E> next;
        E data;
        public ListElement(ListElement<E> prev, ListElement<E> next, E data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }

    private ListElement<E> getListElement(int index) {
        ListElement<E> temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public boolean add(E data) {
        ListElement<E> newElement = new ListElement<>(this.last, null, data);
        if (head == null) {
            this.head = newElement;
        } else {
            this.last.next = newElement;
        }
        this.last = newElement;
        this.size++;
        return true;
    }

    @Override
    public boolean add(int index, E data) {
        if (index < 0 || index > size) {
            return false;
        }

        if ((index == 0 || index == size) && head == null) {
            add(data);
        } else if (index == size) {
            add(data);
        } else if (index == 0) {
            ListElement<E> tempNext = getListElement(index);
            ListElement<E> tempPrev = this.head;
            ListElement<E> newElement = new ListElement<>(tempPrev, tempNext, data);
            this.head = newElement;
            tempNext.prev = newElement;
            this.size++;
        } else {
            ListElement<E> tempNext = getListElement(index);
            ListElement<E> tempPrev = getListElement(index - 1);
            ListElement<E> newElement = new ListElement<>(tempPrev, tempNext, data);
            tempPrev.next = newElement;
            tempNext.prev = newElement;
            this.size++;
        }
        return true;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size || head == null) {
            return false;
        }
        if (size == 1) {
            this.head = null;
            this.last = null;
            size--;
        } else if (index == size - 1) {
            ListElement<E> temp = this.last.prev;
            temp.next = null;
            this.last = temp;
            this.size--;
        } else if (index == 0) {
            ListElement<E> temp = this.head.next;
            temp.prev = null;
            this.head = temp;
            this.size--;
        } else {
            ListElement<E> temp = getListElement(index);
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            size--;

        }
        return true;
    }

    @Override
    public boolean set(int index, E newData) {
        if (index < 0 || index >= size) {
            return false;
        }
        ListElement<E> temp = getListElement(index);
        temp.data = newData;
        return true;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getListElement(index).data;
    }

    @Override
    public void clear() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    //TODO
    @Override
    public E[] toArray() {
        E[] array = (E[]) new Number[size];
        ListElement<E> temp = this.head;
        for (int i = 0; i < size; i++) {
            array[i] = temp.data;
            temp = temp.next;
        }
        return array;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printLink() {
        System.out.println(Arrays.toString(toArray()));
    }

    @Override
    public void quickSort() {
        qSort(0, size-1);
    }

    private void qSort(int low, int high) {
        if (low < high) {
            int p = partition(low, high);
            qSort(low, p);
            qSort(p + 1, high);
        }
    }

    private int partition(int low, int high) {
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (!less(high, j)) {
                i++;
                swap(i, j);
            }
        }
        swap(i+1, high);
        return i;
    }

    private boolean less(int i, int j) {
        return get(i).doubleValue() < get(j).doubleValue();
    }

    private void swap(int i, int j) {
        E temp = get(i);
        set(i, get(j));
        set(j, temp);
    }

}

