package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_6109_추억의2048게임 {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int map[][], N;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			visited = new boolean[N][N];
			int dir = 0;
			switch (st.nextToken()) {
			case "up":
				dir = 0;
				break;
			case "down":
				dir = 1;
				break;
			case "left":
				dir = 2;
				break;
			case "right":
				dir = 3;
				break;
			}

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 상
			if (dir == 0) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == 0)
							continue;
						bfs(i, j, dir);
					}
				}
			}
			// 하
			else if (dir == 1) {
				for (int i = N - 1; i >= 0; i--) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == 0)
							continue;
						bfs(i, j, dir);
					}
				}
			}
			// 좌
			else if (dir == 2) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == 0)
							continue;
						bfs(i, j, dir);
					}
				}
			}
			// 우
			else if (dir == 3) {
				for (int i = 0; i < N; i++) {
					for (int j = N - 1; j >= 0; j--) {
						if (map[i][j] == 0)
							continue;
						bfs(i, j, dir);
					}
				}
			}

			bw.write("#" + tc + "\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bw.write(String.valueOf(map[i][j]) + " ");
				}
				bw.write("\n");
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(int curX, int curY, int dir) {

		int nx = curX;
		int ny = curY;
		int cnt = 0;
		while (true) {
//			
			nx += dx[dir];
			ny += dy[dir];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				if (cnt != 0) {
					map[nx - dx[dir]][ny - dy[dir]] = map[curX][curY];
					map[curX][curY] = 0;
				}
				break;
			}
			if (map[nx][ny] != 0) {
				if (map[nx][ny] == map[curX][curY]) {
					if (visited[nx][ny]) {
						map[nx - dx[dir]][ny - dy[dir]] = map[curX][curY];
						map[curX][curY] = 0;
					} else {
						map[nx][ny] *= 2;
						visited[nx][ny] = true;
						map[curX][curY] = 0;
					}
					break;
				} else {
					if (cnt != 0) {
						map[nx - dx[dir]][ny - dy[dir]] = map[curX][curY];
						map[curX][curY] = 0;
					}
					break;
				}
			}
			cnt++;
		}

	}
}
