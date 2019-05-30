package client;

import compute.Task;

import java.math.BigDecimal;
import static java.math.BigDecimal.*;

public class Pi implements Task<BigDecimal> {
    private static final long serialVersionUID = 227L;
    private static final BigDecimal FOUR = new BigDecimal(4);
    private static final BigDecimal TWO = new BigDecimal(2);
    private final int digits;

    public Pi(int digits) {
        this.digits = digits;
    }

    public BigDecimal execute() {
        return computePi(digits);
    }

    public static BigDecimal computePi(final int digits) {
        BigDecimal a = ONE;
        BigDecimal b = ONE.divide(sqrt(TWO, digits),digits, ROUND_HALF_UP);
        BigDecimal t = new BigDecimal(0.25);
        BigDecimal x = ONE;
        BigDecimal y;
        while (!a.equals(b)){
            y=a;
            a = a.add(b).divide(TWO, digits, ROUND_HALF_UP);
            b = sqrt(b.multiply(y), digits);
            t = t.subtract(x.multiply(y.subtract(a).multiply(y.subtract(a))));
            x = x.multiply(TWO);
        }
        return a.add(b).multiply(a.add(b)).divide(t.multiply(FOUR), digits, ROUND_HALF_UP);
    }


    public static BigDecimal sqrt(BigDecimal A, final int SCALE){
        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));

        while (!x0.equals(x1)){
            x0=x1;
            x1 = A.divide(x0, SCALE, ROUND_HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, SCALE, ROUND_HALF_UP);
        }
        return x1;
    }

}
