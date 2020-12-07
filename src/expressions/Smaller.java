package expressions;

public class Smaller extends BinaryExpression {
	public Smaller(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public double calculate() {
		if( left.calculate() < right.calculate())
		{
			return 1;
		}
		return 0;
	}
}
