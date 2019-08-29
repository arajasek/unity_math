package org.aion.util.math;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SqrtApproximatorTest {

    // This precision is low because we're actually *more* accurate than Math.sqrt
    private static double delta = 0.0001;

    @Test
    public void testSqrt123456789() {
        Assert.assertEquals(Math.sqrt(129), SqrtApproximator.sqrt(new BigInteger("129")).toBigDecimal().doubleValue(), delta);
    }

    // Note that this test includes the time for both Math.sqrt() as well as the sqrt approximation
    @Test(timeout = 2000)
    public void test100000RandomSqrts() {

        Random rng = new Random();
        long seed = rng.nextLong();
        System.out.println("Random test's seed is " + seed);
        rng.setSeed(seed);

        for (int i = 0; i < 100000; i++) {
            long value = Math.abs(rng.nextLong());
            if (value == 0) {
                value++;
            }
            Assert.assertEquals(Math.sqrt(value), SqrtApproximator.sqrt(BigInteger.valueOf(value)).toBigDecimal().doubleValue(), delta);
        }

    }
}