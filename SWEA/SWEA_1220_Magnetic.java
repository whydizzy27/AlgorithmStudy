package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution_D3_1220_Magnetic_������ {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int tc = 1; tc <= 10; tc++) {
			
			br.readLine();
			int[][] arr = new int[100][100];
			for (int i = 0; i < 100; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 0; j < 100; j++) {
					arr[i][j]=Integer.parseInt(str[j]);
				}				
			}
			int cnt=0;
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					if(arr[i][j]==0) continue;
					else if(arr[i][j]==2) {
						for (int k = i-1; k >0; k--) {
							if(arr[i][j]!=arr[k][j]&&arr[k][j]!=0) {
								cnt++;
								arr[i][j]=0;

								arr[k][j]=0;
								break;
							}
							else if(arr[i][j]==arr[k][j]) {
								arr[i][j]=0;
								break;

							}
							
						}
					}
					else {
						for (int k = i+1; k < 100; k++) {
							if(arr[i][j]!=arr[k][j]&&arr[k][j]!=0) {
								cnt++;
								arr[i][j]=0;

								arr[k][j]=0;
								break;
							}
							else if(arr[i][j]==arr[k][j]) {
								arr[i][j]=0;
								break;

							}
							
						}
					}
					
				}				
			}
			
			
			
			
			bw.write("#"+tc+" "+String.valueOf(cnt)+"\n");

		}
		bw.flush();
		br.close();
		bw.close();
	}

}
