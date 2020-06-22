package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

//백준 안전영역
public class BOJ_2468_안전영역 {
	static int N;
	static int[][] map;
	static int[][] copy;
	static int max;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		copy = new int[N][N];

		int maxH = 0;

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if (maxH < map[i][j])
					maxH = map[i][j];
			}
		}

		for (int h = 1; h < maxH; h++) {
			copy();
			int cnt=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copy[i][j] <= h)
						copy[i][j] = 0;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copy[i][j] == 0)
						continue;
					bfs(i, j);
					cnt++;
				}
			}
			if (max < cnt)
				max = cnt;

		}
		bw.write(String.valueOf(Math.max(1, max)));

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(int i, int j) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(i);
		q.offer(j);
		int nx, ny, x, y;
		copy[i][j] = 0;
		while (!q.isEmpty()) {
			x = q.poll();
			y = q.poll();

			for (int k = 0; k < 4; k++) {
				nx = x + dx[k];
				ny = y + dy[k];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N)
					if (copy[nx][ny] != 0) {
						copy[nx][ny] = 0;
						q.offer(nx);
						q.offer(ny);

					}

			}

		}

	}

	private static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

}
