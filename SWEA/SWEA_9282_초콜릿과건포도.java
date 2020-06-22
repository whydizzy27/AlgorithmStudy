package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_9282_초콜릿과건포도 {
	static int N, M;
	static int[][] map;
	static int[][][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);

			dp = new int[N][N][M][M];
			map = new int[N][M];

			for (int i = 0; i < N; i++) {
				String[] str2 = br.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(str2[j]);
				}
			}
			bw.write("#" + tc + " " + String.valueOf(dfs(0, N - 1, 0, M - 1)) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static int dfs(int x1, int x2, int y1, int y2) {
		if (x1 == x2 && y1 == y2)
			return 0;

		if (dp[x1][x2][y1][y2] > 0)
			return dp[x1][x2][y1][y2];

		int min = Integer.MAX_VALUE;

		int sum = 0;
		for (int k = x1; k <= x2; k++) {
			for (int k2 = y1; k2 <= y2; k2++) {
				sum += map[k][k2];
			}
		}
		// 행으로자르기
		for (int k = x1; k < x2; k++) {
			min = Math.min(min, sum + dfs(x1, k, y1, y2) + dfs(k + 1, x2, y1, y2));
		}
		// 열로자르기
		for (int k = y1; k < y2; k++) {
			min = Math.min(min, sum + dfs(x1, x2, y1, k) + dfs(x1, x2, k + 1, y2));

		}

		dp[x1][x2][y1][y2] = min;
		return min;

	}

}
