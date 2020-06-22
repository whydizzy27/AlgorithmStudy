package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_D3_2801_���۹���Ȯ�ϱ�_������ {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] map;
	static int n;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			visited = new boolean[n][n];

			for (int i = 0; i < n; i++) {
				String[] str = br.readLine().split("");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(str[j]);
				}
			}
			
			func();
			

			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j])
						sum += map[i][j];
				}
			}
			bw.write("#" + tc + " " + String.valueOf(sum) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();

	}
	public static void func() {
		for (int i = 0; i <= n/2; i++) {
			for (int j = n/2 - i; j < n/2 - i + 2*i+1; j++) {
				visited[i][j]=true;
			}			
		}
		int k=0;
		for (int i = n/2+1; i < n; i++) {
			k++;
			for (int j = k; j < k+n-2*k; j++) {
				visited[i][j]=true;
			}			
		}
		
		
	}
	

}
