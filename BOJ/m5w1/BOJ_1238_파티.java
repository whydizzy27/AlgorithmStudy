package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1238_파티 {
	static final int INF = 999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int[][] adj = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					adj[i][j] = 0;
				else
					adj[i][j] = INF;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[s][e] = cost;
		}

		// 경출도
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k)
					continue;
				for (int j = 1; j <= N; j++) {
					if (i == j || j == k)
						continue;
					if (adj[i][k] + adj[k][j] < adj[i][j])
						adj[i][j] = adj[i][k] + adj[k][j];
				}
			}
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, adj[i][X] + adj[X][i]);
		}

		bw.write(String.valueOf(max) + " ");
		bw.flush();
		br.close();
		bw.close();
	}
}