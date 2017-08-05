package AlgorithmTraining.G55Utils.Java;

/**
 *
 * Created by BUPT_SS4G on 2017/7/15.
 */

public class Tuple implements Comparable<Tuple>{
    public final Object[] items;
    public final int length;
    Tuple(Object ...items) {
        this.items = items; // initliaze just once
        this.length = items.length;
    }
    @Override
    public int compareTo(Tuple otherTuple) {
        int otherLength = otherTuple.length;
        int i = -1;
        int minLength = Math.min(otherLength, length);
        do {
            i++;
            // todo should be checked by reflect
        } while (i < minLength && 0 == ((Comparable)(otherTuple.items[i])).compareTo(items[i]));
        if (i == minLength) { // exit loop above because of length
            if (otherTuple.length > length)
                return -1;
            else if (otherTuple.length == length)
                return 0;
            else
                return 1;
        }
        else { // exit loop above because of value
            if (((Comparable)(otherTuple.items[i])).compareTo(items[i]) > 0)
                return -1;
            else
                return 1;
        }
    }
    
    public boolean equals(Tuple t0) {
        return this.compareTo(t0) == 0;
    }
    
    // todo: add hashable method
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Object o: items) {
            sb.append(o.toString());
            sb.append(',');
        }
        sb.append(')');
        return sb.toString();
    }
}
class Tuple_Test {
    public static void main(String[] args) {
        Tuple t0 = new Tuple(1, 2, 3);
        Tuple t1 = new Tuple(1, 2, 3, 5);
        System.out.println(t0);
        System.out.println(t1);
        System.out.println(t0.compareTo(t1));
    }
}
