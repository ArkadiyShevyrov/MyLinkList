import java.util.concurrent.Callable;
import java.util.function.IntConsumer;

interface ILink<E extends Number> {

    boolean add(E data);

    boolean add(int index, E data);

    boolean remove(int index);

    boolean set(int index, E newData);

    E get(int index);

    void clear();

    E[] toArray();

    int size();

    void printLink();

    void quickSort();
}