package Jungol;

import java.util.Scanner;

public class JOL_1495_지그재그 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[n][n];
		
		//1~25����
		int num=1;
		int row=0;
		int col=0;
		int currow=0;
		int curcol=0;
		while(num<=Math.pow(n, 2)) {
			currow = row;
			curcol = col;
//			System.out.println(row +" "+ col);
			//¦
			if((row+col)%2==0) {
				if(col==0) {
					if(row!=n-1)
						row++;
					else
						col++;
				}
				else if(row==n-1) {
					col++;
				}
				else {
					row++;
					col--;
				}
			}
			//Ȧ
			else{
				if(row==0) {
					if(col!=n-1)
						col++;
					else
						row++;
				}
				else if(col==n-1) {
					row++;
				}
				else {
					row--;
					col++;
				}
			}
			arr[currow][curcol]=num;
			num++;
		}
		
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.printf("%d ",arr[i][j]);	
			}
			System.out.println();
		}
	}

}
