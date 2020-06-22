package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//월드컵 BOJ 6987
public class BOJ_6987_월드컵 {
	static int[] teamA = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	static int[] teamB = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
	static int[][] arr,result;
	static int ans;
	static boolean ok;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int tn = 0; tn < 4; tn++) {
			arr = new int[6][3];
			result = new int[6][3];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans=0;
			ok=false;
			dfs(0);
			
			bw.write(String.valueOf(ans) + " ");
		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void dfs(int idx) {
		if(ok) return;
		if(idx==15) {
			
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					if(arr[i][j]!=result[i][j]) 
						return;
				}
			}
			ans=1;
			ok=true;
			
			return;
		}
		
		int t1 = teamA[idx];
		int t2 = teamB[idx];
		
		//t1 승 t2 패
		result[t1][0]++;
		result[t2][2]++;
		dfs(idx+1);
		result[t1][0]--;
		result[t2][2]--;
		
		//t1 무 t2 무
		result[t1][1]++;
		result[t2][1]++;
		dfs(idx+1);
		result[t1][1]--;
		result[t2][1]--;
		
		//t1 패 t2 승
		result[t1][2]++;
		result[t2][0]++;
		dfs(idx+1);
		result[t1][2]--;
		result[t2][0]--;
		
	}

}
