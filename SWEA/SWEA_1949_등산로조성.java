package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Mou {
	int x, y;

	public Mou(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class SWEA_1949_등산로조성 {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int map[][], N, K, maxLength;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			ArrayList<Mou> topList = new ArrayList<Mou>();
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > max) {
						max = map[i][j];
						topList.clear();
						topList.add(new Mou(i, j));
					} else if (map[i][j] == max) {
						topList.add(new Mou(i, j));
					}
				}
			}
			maxLength = 0;
			for (Mou mou : topList) {
				visited[mou.x][mou.y] = true;
				dfs(mou.x, mou.y, 1, false);
				visited[mou.x][mou.y] = false;
			}
			bw.write("#" + tc + " " + String.valueOf(maxLength) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int x, int y, int cnt, boolean useK) {
		boolean possible = false;
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;

			if (visited[nx][ny])
				continue;

			// 작으면 ㄱㄱ
			if (map[nx][ny] < map[x][y]) {
				possible = true;
				visited[nx][ny] = true;
				dfs(nx, ny, cnt + 1, useK);
				visited[nx][ny] = false;

			} else {
				if (!useK) {

					for (int j = 1; j <= K; j++) {

						map[nx][ny] -= j;
						if (map[nx][ny] < map[x][y]) {
							possible = true;
							visited[nx][ny] = true;
							dfs(nx, ny, cnt + 1, true);
							visited[nx][ny] = false;
						}
						map[nx][ny] += j;

					}
				}

			}
		}

		if (!possible) {
			if (maxLength < cnt)
				maxLength = cnt;

			return;
		}

	}

}
