/**
 * 
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.function.Function;

import wheelsunh.users.Ellipse;
import wheelsunh.users.Frame;
import wheelsunh.users.Rectangle;
import wheelsunh.users.TextBox;
/**
 * @author Michael
 *
 */
public class area_modded 
{

	/**
	 * @param args
	 */
	public int curHistoryI[][];
	public int curHistoryJ[][];
	public boolean ready;
	TextBox text;
	
	public area_modded(IFunction fh_i, IFunction fh_j, double[][] pMatrix, int numGames, int moveTime)
	{
		super();
		double pay_weight = 0.01;	
		double rMax = sum(pMatrix)/pay_weight;
		
		curHistoryI = new int[2][2];
		curHistoryJ = new int[2][2];
		
		double score[] = new double[2];		
		
		double cResource1[] = new double[numGames+moveTime];
		double cResource2[] = new double[numGames+moveTime];
		
		double interations[] = new double[numGames+moveTime];//May not be neccessary
		
		double avgFeedRate1[] = new double[numGames+moveTime];
		double avgFeedRate2[] = new double[numGames+moveTime];
				
		double rCur;
		for(int i = 0; i < numGames; i++)
		{
			int curI = fh_i.function(pMatrix,curHistoryI);
			int curJ = fh_j.function(pMatrix,curHistoryI);
			score[0] += pMatrix[curI][curJ];
			score[1] += pMatrix[curJ][curI];
			rCur = rMax - (score[0] + score[1]);
			multiply(pMatrix,rCur/rMax); 
			curHistoryI[curI][curJ]=curHistoryI[curI][curJ]+1;
			curHistoryJ[curJ][curI]=curHistoryJ[curJ][curI]+1;	
			System.out.println("There are "+ rCur +" resources left. The max is  "+rMax);
			
			System.out.println("Score 1 is "+score[0]+"Score 2 is "+score[1]);			

			print(curHistoryI);
			print(curHistoryJ);
		}		
	}
	

	public void multiply(double[][] mod, double mult)
	{
		for(int i = 0; i < mod.length; i++)
		{
			for(int j = 0; j < mod[i].length; j++)
			{
				mod[i][j] = mod[i][j]*mult;
			}
		}
	}
	
	public double sum(double[][] payoff)
	{
		double sum = 0.0;
		for(int i = 0; i < payoff.length; i++)
		{
			for(int j = 0; j < payoff[i].length; j++)
			{
				sum += payoff[i][j];
			}
		}
		return sum;
	}
	
	public static String print(int[][] arry)
	{
		String ret = "";
		for(int i = 0; i < arry.length; i++)
		{
			for(int j = 0; j < arry[i].length; j++)
			{
				ret = ret+arry[i][j]+" ";
				System.out.print(arry[i][j]+" ");
			}
			ret = ret+"\n";
			System.out.println();
		}	
		ret = ret+"\n";
		System.out.println();
		return ret;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IFunction ran = new pd_Random();
		IFunction suck = new Sucker();
		IFunction pd_defector = new pd_defector();
		IFunction pd_optimist = new pd_optimist();
		IFunction pd_Sinister = new pd_Sinister();
		
		double[][] pMatrix = new double[2][2];
		pMatrix[0][0] = 100; pMatrix[0][1] = 100;
		pMatrix[1][0] = 100; pMatrix[1][1] = 100;		
		area_modded mod = new area_modded(ran,ran,pMatrix,1000,15);	
//		print(mod.curHistoryI);
//		print(mod.curHistoryJ);
	}


}
