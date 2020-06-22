package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SWEA_4012_요리사 {
	
	static int N,min;
	static boolean[] visited;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= tn; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					arr[i][j]=Integer.parseInt(str[j]);
				}
			}
			//입력 끝
			
			min = Integer.MAX_VALUE;
			combination(0, 0);
			bw.write("#" + tc + " " + String.valueOf(min) + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void combination(int idx, int cur) {
		if(cur>=N) return;
			
		if(idx==N/2) {
			//가지 min 0이면 더이상 조합 ㄴㄴ
			int[] arr1 = new int[N/2];
			int[] arr2 = new int[N/2];
			
			int k=0;
			int k2=0;
			for (int i = 0; i < N; i++) {
				if(visited[i]) {
					arr1[k]=i;
					k++;
				}
				else {
					arr2[k2]=i;
					k2++;
				}
			}
			
			int score1=0;
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < N/2; j++) {
					if(i==j) continue;
					score1 += arr[arr1[i]][arr1[j]];
				}
			}
			int score2=0;
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < N/2; j++) {
					if(i==j) continue;
					score2 += arr[arr2[i]][arr2[j]];
				}
			}
			
			int score = Math.abs(score1 - score2);
			
			if(min>score)
				min=score;
			
			return;
		}
		
		for (int i = cur; i < N; i++) {
			if(min==0) return;
			visited[i]=true;
			combination(idx+1, i+1);
			visited[i]=false;
		}
	}
}
