package expressions;

public class Bigger extends BinaryExpression {
	public Bigger(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public double calculate() {
		if( left.calculate() > right.calculate())
		{
			return 1;
		}
		return 0;
	}
}
