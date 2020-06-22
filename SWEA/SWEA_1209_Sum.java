package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution_D3_1209_Sum_������ {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int[][] arr = new int[100][100];
		
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			for (int i = 0; i < 100; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 0; j < 100; j++) {
					arr[i][j]=Integer.parseInt(str[j]);
				}
			}
			
			int rowS = 0;
			int colS = 0;
			int daeRS = 0;
			int daeLS = 0;
			int max =0;
			
			for (int i = 0; i < 100; i++) {
				rowS=0;
				for (int j = 0; j < 100; j++) {
					rowS+=arr[i][j];
				}
				if(max<rowS)
					max=rowS;
			}
			for (int i = 0; i < 100; i++) {
				colS=0;
				for (int j = 0; j < 100; j++) {
					colS+=arr[j][i];
				}
				if(max<colS)
					max=colS;
			}
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					if(i==j) {
						daeLS+=arr[i][j];
					}
					if(j==99-i) {
						daeRS+=arr[i][j];
					}
				}
				if(max<daeLS)
					max=daeLS;
				if(max<daeRS)
					max=daeRS;
			}
			
			bw.write("#"+tc+" "+String.valueOf(max)+"\n");

			
		}
		
		bw.flush();
		br.close();
		bw.close();
	}

}
