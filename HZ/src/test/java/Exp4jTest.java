//exp4j-....jar in classpath

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Exp4jTest {

	public static void main(String[] args) {
		String fun = "sin(x)/x";
		Expression expr = new ExpressionBuilder(fun).variables("x").build();
		for (double x = -3; x <= 3; x += 0.1) {
			System.out.println("\t" + x + "\t" + expr.setVariable("x", x).evaluate());
		}

		fun = "sin(a*x)/x";
		expr = new ExpressionBuilder(fun).variables("x", "a").build();
		for (double x = -3; x <= 3; x += 0.1) {
			System.out.println(x + "\t" + expr.setVariable("a", 1.0).setVariable("x", x).evaluate());
		}
	}

}
