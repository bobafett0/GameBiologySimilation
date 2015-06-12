import java.util.Random;

public class pd_Random implements IFunction
{

	@Override
	public int function(double[][] payoff, int[][] moveHistory) {
		// TODO Auto-generated method stub
		Random randomGenerator = new Random();
		return (randomGenerator.nextInt(2));
	}
	

}
