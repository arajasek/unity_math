package org.aion.util.math;

import java.math.BigInteger;

public class SqrtApproximator {
    
    public static int FRACTIONAL_BITCOUNT = 30;

    public static FixedPoint sqrt(BigInteger n) {
        n = n.shiftLeft(FRACTIONAL_BITCOUNT * 2);
        
        // If n is odd, we want n/2, if n is even, we want n/2 - 1
        int shift = (n.bitLength() - 1) / 2;

        BigInteger bestGuess = BigInteger.ONE.shiftLeft(shift);
        BigInteger bestGuessSquare = BigInteger.ONE.shiftLeft(shift * 2);
        
        do {
            shift--;
            
            // We want to see if setting the bit at position "shift" makes the square go over n
            BigInteger term1 = bestGuess.shiftLeft(shift + 1);
            BigInteger term2 = BigInteger.ONE.shiftLeft(shift * 2);
            
            BigInteger newGuessSquare = bestGuessSquare.add(term1).add(term2);
            if (newGuessSquare.compareTo(n) <= 0) {
                bestGuess = bestGuess.add(BigInteger.ONE.shiftLeft(shift));
                bestGuessSquare = newGuessSquare;
            }
        } while (shift > 0);

        return new FixedPoint(bestGuess.shiftLeft(FixedPoint.PRECISION - FRACTIONAL_BITCOUNT));
    }
}
