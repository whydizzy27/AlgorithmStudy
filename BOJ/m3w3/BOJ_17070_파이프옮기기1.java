package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
	static int cnt, map[][], N;
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 가로 0 세로 2 대각선 1
		dfs(0, 1, 0);

		bw.write(String.valueOf(cnt) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int i, int j, int cur) {
		if (i == N - 1 && j == N - 1) {
			cnt++;
			return;
		}

		// 순서 가로 대각 세로
		for (int k = 0; k < 3; k++) {
			if (cur == 0) {
				if (k == 2)
					continue;
			} else if (cur == 2) {
				if (k == 0)
					continue;
			}

			int nx = i + dx[k];
			int ny = j + dy[k];

			if (k != 1) {

				if (nx >= 0 && nx < N && ny >= 0 && ny < N)
					if (map[nx][ny] != 1) {
						dfs(nx, ny, k);
					}
			}
			else {
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (map[nx][ny] != 1 &&map[nx-1][ny]!=1 &&map[nx][ny-1]!=1) {
						dfs(nx, ny, k);
					}
				}

			}
		}

	}

}
