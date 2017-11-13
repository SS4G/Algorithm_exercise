package AlgorithmTraining.exercise.leetcode.java_src;

/**
 * Created by BUPT_SS4G on 2017/5/10.
 */
public class TwoTuple<A,B> {
private final A first;
private final B second;
public TwoTuple(A a, B b) {
        this.first = a;
        this.second = b;
        }
public A get0th() {
    return first;
}
public B get1st() {
    return second;
}
}