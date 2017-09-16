package AlgorithmTraining.exercise.toOffer;

/**
 * Created by G5501 on 2017/9/13.

 */
import java.util.*;
class Solution051 {
    /*
    class Element implements Comparable<Element> {
        int cnt;
        Character c;
        int insertOrder;
        Element(Character c, int order) {
            this.c = c;
            insertOrder = order;
        }
        @Override
        public int compareTo(Element o) {
            if (this.cnt > o.cnt) {
                return 1;
            }
            else if (this.cnt == o.cnt) {
                if (insertOrder > o.insertOrder)
                    return 1;
                else if (insertOrder == o.insertOrder)
                    return 0;
                else
                    return -1;
            }
            else {
                return -1;
            }
        }
    }*/
    //Insert one char from stringstream
    Map<Character, Integer> streamMap = new HashMap<>();
    PriorityQueue<Character> fifo = new PriorityQueue<>();
    //int insertOrder;
    public void Insert(char ch)
    {
        if (streamMap.containsKey(ch)) {
            streamMap.put(ch, 2);
        }
        else {
            streamMap.put(ch, 1);
            fifo.offer(ch);
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        Character ch = null;
        while (fifo.size() > 0 && streamMap.get(ch = fifo.peek()) != 1) {
            fifo.poll();
        }
        if (fifo.size() > 0) {
            return ch;
        }
        else {
            return '#';
        }
    }
}

public class No051 {
    public static void main(String[] args) {

    }
}
