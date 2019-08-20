package org.aion.util.math;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyTest {

    private static List<Double> randoms = new ArrayList<>();

    private static int numberOfDigits = 20;
    private static int twoRaisedToNumberOfDigits = 1 << numberOfDigits;
    private static double logBoundary = Math.log(twoRaisedToNumberOfDigits);

    @BeforeClass
    public static void setup() {
        randoms.add(0.0);
        double sum = 0;
        for (int i = 1; i < twoRaisedToNumberOfDigits; i++) {
            double log = Math.log(i) - logBoundary;
            double random = -20 * log;
            randoms.add(random);
            sum += random;
        }
        System.out.println(sum / (twoRaisedToNumberOfDigits - 1));
    }

    @Test
    public void testAvgRate() {
        int max = twoRaisedToNumberOfDigits;
        int samples = 10000;
        Random random = new Random();
        double sum = 0.0;
        for (int i = 0; i < 10000; i++) {
            int randomInt = random.nextInt(max - 1);
            sum += randoms.get(randomInt);
//            System.out.println(" I drew " + randoms.get(randomInt));
        }
        System.out.println(sum / samples);
    }
}