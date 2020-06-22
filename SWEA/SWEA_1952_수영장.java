package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_1952_수영장 {
	static int[] month, cost;
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= tn; tc++) {
			
			String[] str = br.readLine().split(" ");
			
			cost = new int[4];
			for (int i = 0; i < 4; i++) {
				cost[i]=Integer.parseInt(str[i]);
			}
			
			String[] str2 = br.readLine().split(" ");

			month = new int[13];
			for (int i = 1; i <= 12; i++) {
				month[i]=Integer.parseInt(str2[i-1]);
			}
			
			min = cost[3];
			
			dfs(1,0);
			
			bw.write("#" + tc + " " + String.valueOf(min) + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void dfs(int idx,int sum) {
		if(idx>12) {
			if(min>sum)
				min=sum;
			return;
		}
		
		dfs(idx+1,sum+cost[0]*month[idx]);
		dfs(idx+1,sum+cost[1]);
		dfs(idx+3,sum+cost[2]);
			
	}

}
