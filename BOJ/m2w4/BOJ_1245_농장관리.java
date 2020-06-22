package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1245_농장관리 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int cnt;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String[] str2 = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str2[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 || visited[i][j])
					continue;
				bfs(i, j);
			}
		}

		bw.write(String.valueOf(cnt));

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(int i, int j) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(i);
		q.offer(j);
		int nx, ny, x, y;

		while (!q.isEmpty()) {
			x = q.poll();
			y = q.poll();

			for (int k = 0; k < 8; k++) {
				nx = x + dx[k];
				ny = y + dy[k];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;

				if (map[nx][ny] > map[x][y])
					return;

			}
			//
			visited[x][y] = true;
			for (int k = 0; k < 8; k++) {
				nx = x + dx[k];
				ny = y + dy[k];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M)
				if (map[nx][ny] == map[x][y] && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(nx);
					q.offer(ny);
				}
			}

		}

		cnt++;

	}

}
