package perflab;

/**
 * @author Leonid Bushuev from JetBrains
 */
@SuppressWarnings("StatementWithEmptyBody")
public class Long1 {

    public static void main(String args[]) {
        System.out.println("Begin");

        MeasureIntCounter();
        MeasureLongCounter();

        System.out.println("End");
    }


    private static void MeasureIntCounter() {
        sleep(400);
        long timestamp1 = System.currentTimeMillis();
        int x = 0;
        for (int a = 1; a < 100000000; a++) {
            for (int b = 1; b < 20; b++) {
                x = (x >> 1) + (a - b);
            }
        }
        long time = System.currentTimeMillis() - timestamp1;
        System.out.println("x = " + x);
        System.out.println("Int counters time: " + time);
    }

    private static void MeasureLongCounter() {
        sleep(400);
        long timestamp1 = System.currentTimeMillis();
        long x = 0;
        for (long a = 1; a < 100000000; a++) {
            for (long b = 1; b < 20; b++) {
                x = (x >> 1) + (a - b);
            }
        }
        long time = System.currentTimeMillis() - timestamp1;
        System.out.println("x = " + x);
        System.out.println("Long counters time: " + time);
    }


    private static void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException inte) {
            // do nothing
        }
    }

}
