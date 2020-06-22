package m3w2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class CCTV {
	int x, y, type;

	public CCTV(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
}

public class BOJ_15683_감시 {
	static int N, M, min, len;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static ArrayList<CCTV> list;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");

		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		list = new ArrayList<CCTV>();
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] str2 = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str2[j]);
				if (map[i][j] != 0 && map[i][j] != 6)
					list.add(new CCTV(i, j, map[i][j]));
			}
		}

		min = Integer.MAX_VALUE;
		len = list.size();

		dfs(0, map);
		bw.write(String.valueOf(min) + "\n");

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int idx, int[][] arr) {
		if (idx == len) {
			sideCount(arr);
			return;
		}
		int mode = list.get(idx).type;
		int x = list.get(idx).x;
		int y = list.get(idx).y;

		// 1~5번 경우의 회전 을 담은 dfs
		if (mode == 1) {
			for (int i = 0; i < 4; i++) {

				int[][] temp = new int[N][M];
				for (int k = 0; k < N; k++) {
					temp[k] = arr[k].clone();
				}

				int nx = x;
				int ny = y;

				while (true) {
					nx += dx[i];
					ny += dy[i];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M)
						break;

					if (temp[nx][ny] == 6)
						break;

					if (temp[nx][ny] != 0)
						continue;

					temp[nx][ny] = 9;
				}

				dfs(idx + 1, temp);
			}

		} else if (mode == 2) {
			for (int i = 0; i < 2; i++) {

				int[][] temp = new int[N][M];
				for (int k = 0; k < N; k++) {
					temp[k] = arr[k].clone();
				}
				int k=i;
				for (int j = 0; j < 2; j++) {

					int nx = x;
					int ny = y;

					while (true) {
						nx += dx[k];
						ny += dy[k];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M)
							break;

						if (temp[nx][ny] == 6)
							break;

						if (temp[nx][ny] != 0)
							continue;

						temp[nx][ny] = 9;
					}
					k += 2;
				}
				dfs(idx + 1, temp);
			}
		} else if (mode == 3) {
			for (int i = 0; i < 4; i++) {

				int[][] temp = new int[N][M];
				for (int k = 0; k < N; k++) {
					temp[k] = arr[k].clone();
				}
				int k = i;
				for (int j = 0; j < 2; j++) {

					int nx = x;
					int ny = y;

					while (true) {
						nx += dx[k];
						ny += dy[k];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M)
							break;

						if (temp[nx][ny] == 6)
							break;

						if (temp[nx][ny] != 0)
							continue;

						temp[nx][ny] = 9;
					}
					k = (k + 1) % 4;
				}
				dfs(idx + 1, temp);
			}
		} else if (mode == 4) {
			for (int i = 0; i < 4; i++) {

				int[][] temp = new int[N][M];
				for (int k = 0; k < N; k++) {
					temp[k] = arr[k].clone();
				}
				int k = i;

				for (int j = 0; j < 3; j++) {

					int nx = x;
					int ny = y;

					while (true) {
						nx += dx[k];
						ny += dy[k];
						if (nx < 0 || nx >= N || ny < 0 || ny >= M)
							break;

						if (temp[nx][ny] == 6)
							break;

						if (temp[nx][ny] != 0)
							continue;

						temp[nx][ny] = 9;
					}
					k = (k + 1) % 4;
				}
				dfs(idx + 1, temp);
			}
		} else {

			int[][] temp = new int[N][M];
			for (int k = 0; k < N; k++) {
				temp[k] = arr[k].clone();
			}
			for (int j = 0; j < 4; j++) {
				int nx = x;
				int ny = y;

				while (true) {
					nx += dx[j];
					ny += dy[j];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M)
						break;

					if (temp[nx][ny] == 6)
						break;

					if (temp[nx][ny] != 0)
						continue;

					temp[nx][ny] = 9;
				}

			}
			dfs(idx + 1, temp);
		}
	}

	// 9로 구역 두는데 남은 사각지대 0개수 세는 함수
	private static void sideCount(int[][] arr) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0)
					cnt++;
			}
		}
		if (min > cnt)
			min = cnt;
	}
}
