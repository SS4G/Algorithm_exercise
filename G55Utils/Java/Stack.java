package AlgorithmTraining.G55Utils.Java;

import java.util.LinkedList;

/**
 * Created by BUPT_SS4G on 2017/6/5.
 */
public class Stack<E> {
    private LinkedList<E> storage = new LinkedList<E>();
    public void push(E e) {storage.addFirst(e);}
    public E peek() {return storage.getFirst();}
    public boolean isEmpty() {return storage.isEmpty();}
    public E pop() {return storage.removeFirst();}
    public String toString() {return storage.toString();}
}
