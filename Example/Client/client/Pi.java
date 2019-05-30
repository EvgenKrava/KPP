package client;

import compute.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pi implements Task<BigDecimal> {

    private static final BigDecimal TWO = new BigDecimal("2");
    private static final BigDecimal FOUR = new BigDecimal("4");
    private static final BigDecimal FIVE = new BigDecimal("5");
    private static final BigDecimal TWO_THIRTY_NINE = new BigDecimal("239");
    private int digitsCount;

    public Pi(int digitsCount) {
        this.digitsCount = digitsCount;
    }

    @Override
    public BigDecimal execute() {

        int calcDigits = digitsCount + 10;

        return FOUR.multiply((FOUR.multiply(arctan(FIVE, calcDigits)))
                .subtract(arctan(TWO_THIRTY_NINE, calcDigits)))
                .setScale(digitsCount, RoundingMode.DOWN);
    }

    private static BigDecimal arctan(BigDecimal x, int numDigits) {

        BigDecimal unity = BigDecimal.ONE.setScale(numDigits, RoundingMode.DOWN);
        BigDecimal sum = unity.divide(x, RoundingMode.DOWN);
        BigDecimal xpower = new BigDecimal(sum.toString());
        BigDecimal term = null;

        boolean add = false;

        for (BigDecimal n = new BigDecimal("3"); term == null || term.compareTo(BigDecimal.ZERO) != 0; n = n.add(TWO)) {
            xpower = xpower.divide(x.pow(2), RoundingMode.DOWN);
            term = xpower.divide(n, RoundingMode.DOWN);
            sum = add ? sum.add(term) : sum.subtract(term);
            add = !add;
        }
        return sum;
    }
}
