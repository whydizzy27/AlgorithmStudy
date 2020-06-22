package m3w2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class DM {
	int Rx, Ry, Bx, By, cnt;

	public DM(int rx, int ry, int bx, int by, int cnt) {
		Rx = rx;
		Ry = ry;
		Bx = bx;
		By = by;
		this.cnt = cnt;
	}

}

public class BOJ_13459_구슬탈출 {
	static int ans, N, M;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][][][] visited;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		int Rx = 0, Ry = 0, Bx = 0, By = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					Rx = i;
					Ry = j;
				} else if (map[i][j] == 'B') {
					Bx = i;
					By = j;
				}
			}
		}
		bfs(new DM(Rx, Ry, Bx, By, 0));
		bw.write(String.valueOf(ans));

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(DM dama) {
		Queue<DM> q = new LinkedList<DM>();
		q.offer(dama);
		visited[dama.Rx][dama.Ry][dama.Bx][dama.By] = true;
		while (!q.isEmpty()) {
			DM d = q.poll();
			if (d.cnt > 9)
				return;
			for (int k = 0; k < 4; k++) {
				// 굴려굴려
				boolean redOver = false;
				boolean blueOver = false;

				int rx = d.Rx;
				int ry = d.Ry;
				int rcnt = 0;
				while (true) {
					rx += dx[k];
					ry += dy[k];

					if (rx < 1 || rx > N - 2 || ry < 1 || ry > M - 2)
						break;
					if (map[rx][ry] =='#')
						break;
					if (map[rx][ry] == 'O') {
						redOver = true;
						break;
					}
					rcnt++;
				}
				rx -= dx[k];
				ry -= dy[k];
				int bx = d.Bx;
				int by = d.By;
				int bcnt = 0;
				while (true) {
					bx += dx[k];
					by += dy[k];

					if (bx < 1 || bx > N - 2 || by < 1 || by > M - 2)
						break;
					if (map[bx][by] =='#')
						break;
					if (map[bx][by] == 'O') {
						blueOver = true;
						break;
					}
					bcnt++;
				}
				bx -= dx[k];
				by -= dy[k];
				// 불린값 조합
				if (blueOver && redOver)
					continue;
				if (blueOver)
					continue;
				if (redOver) {
					ans = 1;
					return;
				}
				// 같으면 하나 백
				if (rx == bx && ry == by) {
					if (rcnt < bcnt) {
						bx -= dx[k];
						by -= dy[k];
					} else {
						rx -= dx[k];
						ry -= dy[k];
					}
				}
				// 이 구도 비지티드
				if (!visited[rx][ry][bx][by]) {
					visited[rx][ry][bx][by] = true;
					q.offer(new DM(rx, ry, bx, by, d.cnt + 1));
				} else
					continue;
			}

		}
	}

}
