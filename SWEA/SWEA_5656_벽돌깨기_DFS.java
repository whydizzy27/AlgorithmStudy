package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_5656_벽돌깨기_DFS {
	static int map[][], copy[][], dropOrder[], N, H, W, min, cx, cy, cv;
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
			int cnt = -1;
			for (int k = 0; k < N; k++) {
				if (cnt == 0)
					break;
				findTop(dropOrder[k]);
				if (cx == 0 && cy == 0 && cv == 0)
					continue;
				dfs(cx, cy, cv);
				down();
				cnt = howManyRemains(copy);
			}
			if (cnt == -1)
				cnt = howManyRemains(copy);
			if (min > cnt)
				min = cnt;
			return;
		}
		for (int i = 0; i < W; i++) {
			if(min==0) return;
			dropOrder[idx] = i;
			permutation(idx + 1);
		}

	}

	// 몇개 남았는지 세는 함수
	private static int howManyRemains(int[][] arr) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] != 0)
					cnt++;
			}
		}
		return cnt;
	}

	private static void findTop(int col) {
		cx = 0;
		cy = 0;
		cv = 0;
		for (int i = 0; i < H; i++) {
			if (copy[i][col] != 0) {
				cx = i;
				cy = col;
				cv = copy[i][col];
				break;
			}
		}
	}

	private static void dfs(int x, int y, int value) {
		
		copy[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x;
			int ny = y;
			for (int j = 0; j < value - 1; j++) {
				nx += dx[i];
				ny += dy[i];
				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					break;
				if (copy[nx][ny] == 0)
					continue;
				if (copy[nx][ny] == 1) {
					copy[nx][ny] = 0;
					continue;
				}
				dfs(nx, ny, copy[nx][ny]);
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
							copy[k - 1][j] = copy[i][j];
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
