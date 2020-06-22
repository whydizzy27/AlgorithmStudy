package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Dama {
	int x, y, value;

	public Dama(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

}

public class SWEA_5656_벽돌깨기_BFS {
	static int map[][], copy[][], dropOrder[], N, H, W, min;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			String[] str = br.readLine().split(" ");

			N = Integer.parseInt(str[0]);
			W = Integer.parseInt(str[1]);
			H = Integer.parseInt(str[2]);

			map = new int[H][W];
			copy = new int[H][W];
			dropOrder = new int[N];
			for (int i = 0; i < H; i++) {
				String[] str2 = br.readLine().split(" ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(str2[j]);
				}
			}
			min = Integer.MAX_VALUE;
			permutation(0);
			bw.write("#" + tc + " " + String.valueOf(min) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void permutation(int idx) {
		if (idx == N) {
			copyMap();

			for (int k = 0; k < N; k++) {
				bfs(findTop(dropOrder[k]));
				
				down();
			}
			int cnt = howManyRemains();
			
			if (min > cnt)
				min = cnt;

			return;
		}

		for (int i = 0; i < W; i++) {
			dropOrder[idx] = i;
			permutation(idx + 1);
		}

	}

	// 몇개 남았는지 세는 함수
	private static int howManyRemains() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (copy[i][j] != 0)
					cnt++;
			}
		}

		return cnt;
	}

	private static Dama findTop(int col) {
		int cx = 0;
		int cy = 0;
		int cv = 0;
		for (int i = 0; i < H; i++) {
			if (copy[i][col] != 0) {
				cx = i;
				cy = col;
				cv = copy[i][col];
				break;
			}
		}

		return new Dama(cx, cy, cv);
	}

	private static void bfs(Dama dd) {
		Queue<Dama> q = new LinkedList<>();
		q.offer(dd);

		while (!q.isEmpty()) {
			Dama d = q.poll();

			if (copy[d.x][d.y] == 0)
				continue;

			copy[d.x][d.y] = 0;

			for (int i = 0; i < 4; i++) {
				int nx = d.x;
				int ny = d.y;

				for (int j = 0; j < d.value - 1; j++) {
					nx += dx[i];
					ny += dy[i];

					if (nx < 0 || nx >= H || ny < 0 || ny >= W)
						break;
					if (copy[nx][ny] == 0)
						continue;

					q.offer(new Dama(nx, ny, copy[nx][ny]));
				}

			}
		}
	}

	private static void copyMap() {
		for (int i = 0; i < H; i++) {
			copy[i] = map[i].clone();
		}
	}

	private static void down() {
		for (int i = H - 2; i >= 0; i--) {
			for (int j = 0; j < W; j++) {
				if (copy[i][j] != 0 && copy[i + 1][j] == 0) {
					int k = i + 1;
					while (true) {
						if (k == H) {
							copy[k-1][j] = copy[i][j];
							copy[i][j] = 0;
							break;
						}
						if (copy[k][j] != 0) {
							copy[k - 1][j] = copy[i][j];
							copy[i][j] = 0;
							break;
						}
						k++;
					}
				}
			}
		}
	}
}
