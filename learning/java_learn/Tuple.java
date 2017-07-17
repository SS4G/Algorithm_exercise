package AlgorithmTraining.learning.java_learn;

import java.io.Serializable;

/**
 * Created by BUPT_SS4G on 2017/6/5.
 *
 */
class AbstractTuple<A, B>  {
    protected final A a;
    protected final B b;
    protected AbstractTuple(A a, B b) {
        this.a = a;
        this.b = b;
    }
}

class Tuple<A, B> extends AbstractTuple<A, B> implements Serializable{
    public Tuple(A a, B b) {
        super(a, b);
    }

    @Override
    public String toString() {
        return "("+this.a+","+this.b+")";
    }

    public boolean equals(Tuple<A, B> y) {
        return this.a.equals(y.a) && this.b.equals(y.b);
    }
}

class ThreeTuple<A, B, C> extends Tuple<A, B> implements Serializable{
    private final C c;
    ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }
    public String toString() {
        return "(" + this.a + "," + this.b + "," + this.c + ")";
    }
    public boolean equals(ThreeTuple<A, B, C> y) {
        return this.a.equals(y.a) && this.b.equals(y.b) && this.c.equals(y.c);
    }
}

class TupleTest{
    public static void main(String[] args) {
        Tuple<String, Integer> t0 = new Tuple<>("String0", 1);
        Tuple<String, Integer> t1 = new Tuple<>("String1", 1);
        System.out.println(t0.equals(t1));
        ThreeTuple<String, Integer, Integer> tri0 = new ThreeTuple<>("A", 1, 1);
        ThreeTuple<String, Integer, Integer> tri1 = new ThreeTuple<>("A", 1, 1);
        System.out.println(tri0);
        System.out.println(tri1);
        System.out.println(tri0.equals(tri1));
    }
}