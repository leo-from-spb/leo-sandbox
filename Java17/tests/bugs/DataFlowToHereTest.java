package lb.sandbox.java17.bugs;

import org.junit.jupiter.api.Test;

public class DataFlowToHereTest {


    @Test
    void testFibonacci() {
        fibonacciNumber(10);
    }


    public static int fibonacciNumber(int count) {
        if (count == 0) {
            return 0;
        } // Oth fibonacci is 0

        if (count == 1 || count == 2) {
            return 1;
        } // 1st and 2nd Fibonacci are 1 and 1 only

        // calling function recursively for nth Fibonacci
        int n1 = fibonacciNumber(count - 1);
        int n2 = fibonacciNumber(count - 2);
        return n1 + n2;
    }


}
