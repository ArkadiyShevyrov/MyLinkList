import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) {
        MyLink<Integer> myLink = new MyLink<>();
        myLink.add(0, 1);
        myLink.add(-1);
        myLink.add(2,42);
        myLink.add(5);
        myLink.add(4);
        myLink.printLink();
        System.out.println(myLink.size());
        myLink.set(0, 69);
        System.out.println(myLink.get(0));
        myLink.remove(0);
        myLink.printLink();
        myLink.quickSort();
        myLink.printLink();
        myLink.clear();
        myLink.printLink();
    }
}
