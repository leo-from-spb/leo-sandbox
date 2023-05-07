package lb.sandbox.java21.seq;


import java.util.Arrays;
import java.util.Iterator;
import java.util.SequencedCollection;

class TrySeq1 {

    public static void main(String[] args) {

        var arr = new Number[] { 11, 22, 33, 44, 55, 66, 77, 88, 99 };

        SequencedCollection<Number> seq = Arrays.asList(arr);
        SequencedCollection<Number> rev = seq.reversed();

        Iterator<Number> it = rev.iterator();
        while (it.hasNext())  System.out.print(it.next() + ", ");
        System.out.println();
    }

}