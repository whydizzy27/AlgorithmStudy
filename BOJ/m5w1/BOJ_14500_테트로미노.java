package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
	static int max = 0, N, M, map[][];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 나머지 모형은 dfs로 모두 만들어질 수 있음 depth 4까지
				dfs(i, j, 1, map[i][j]);
				// ㅗ자
				uja(i, j);
			}
		}

		bw.write(max + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static void uja(int i, int j) {
		int min = Integer.MAX_VALUE;
		int sum = map[i][j];
		int side = 4;// 십자 방향 중 모서리들 4개. 최소 3개가 되야한다.
		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
				side--;
				continue;
			}

			if (side < 3)
				return;

			min = Math.min(min, map[nx][ny]);
			sum += map[nx][ny];
		}
		if (side == 4)
			sum -= min;

		max = Math.max(max, sum);
	}

	private static void dfs(int i, int j, int depth, int sum) {
		if (depth == 4) {
			max = Math.max(max, sum);
			return;
		}
		visited[i][j] = true;
		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])
				continue;
			dfs(nx, ny, depth + 1, sum + map[nx][ny]);
		}
		visited[i][j] = false;
	}
}