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

class Dama {
	char[][] map;
	int cnt, Rx, Ry, Bx, By, before;

	public Dama(char[][] map, int cnt, int rx, int ry, int bx, int by, int before) {
		this.map = map;
		this.cnt = cnt;
		Rx = rx;
		Ry = ry;
		Bx = bx;
		By = by;
		this.before = before;
	}

}

public class BOJ_13460_구슬탈출2 {
	static int ans, N, M;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean redisOver;
	static boolean blueisOver;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		char[][] map = new char[N][M];
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
		bfs(new Dama(map, 0, Rx, Ry, Bx, By, -1));
		if (ans != 0)
			bw.write(String.valueOf(ans));
		else
			bw.write(String.valueOf(-1));

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Dama dama) {
		Queue<Dama> q = new LinkedList<Dama>();
		q.offer(dama);
		while (!q.isEmpty()) {
			Dama d = q.poll();
			if (d.cnt > 9)
				return;

			L: for (int i = 0; i < 4; i++) {
				if (d.before == i)
					continue;
				blueisOver = false;
				redisOver = false;

				int rx = d.Rx;
				int ry = d.Ry;
				int bx = d.Bx;
				int by = d.By;
				char[][] temp = new char[N][M];
				for (int j = 0; j < N; j++) {
					temp[j] = d.map[j].clone();
				}

//				
//				
				Dama td = null;
				if (i == 0) {
					if (rx < bx)
						td = move(i, temp, rx, ry, bx, by);
					else
						td = move(i, temp, bx, by, rx, ry);
				} else if (i == 1) {
					if (ry > by)
						td = move(i, temp, rx, ry, bx, by);
					else
						td = move(i, temp, bx, by, rx, ry);
				} else if (i == 2) {
					if (rx > bx)
						td = move(i, temp, rx, ry, bx, by);
					else
						td = move(i, temp, bx, by, rx, ry);
				} else {
					if (ry < by)
						td = move(i, temp, rx, ry, bx, by);
					else
						td = move(i, temp, bx, by, rx, ry);
				}
//				
				if (redisOver && blueisOver) {
					continue;
				}
				if (redisOver) {
					ans = d.cnt + 1;
					return;
				}
				if (blueisOver)
					continue;

				boolean chk = false;
				T: for (int j = 1; j < N - 1; j++) {
					for (int j2 = 1; j2 < M - 1; j2++) {
						if (temp[j][j2] != d.map[j][j2]) {
							chk = true;
							break T;
						}
					}
				}
				if (!chk)
					continue L;
//				System.out.println(d.cnt);
//				for (int j = 0; j < N; j++) {
//					for (int j2 = 0; j2 < M; j2++) {
//						System.out.print(temp[j][j2]);
//					}
//					System.out.println();
//				}
				q.offer(new Dama(temp, d.cnt + 1, td.Rx, td.Ry, td.Bx, td.By, i));
			}
		}

	}

	private static Dama move(int lev, char[][] arr, int a, int b, int x, int y) {
		char cA = arr[a][b];
		while (true) {
			a += dx[lev];
			b += dy[lev];

			if (a < 1 || a >= N - 1 || b < 1 || b >= M - 1)
				break;
			if (arr[a][b] != '.' && arr[a][b] != 'O')
				break;
			if (arr[a][b] == 'O' && cA == 'R') {
				redisOver = true;
				arr[a - dx[lev]][b - dy[lev]] = '.';
				break;
			} else if (arr[a][b] == 'O' && cA == 'B') {
				blueisOver = true;
				arr[a - dx[lev]][b - dy[lev]] = '.';
				break;
			} else {
				arr[a][b] = cA;
				arr[a - dx[lev]][b - dy[lev]] = '.';
			}
		}
		char cB = arr[x][y];
		while (true) {
			x += dx[lev];
			y += dy[lev];

			if (x < 1 || x > N - 2 || y < 1 || y > M - 2)
				break;
			if (arr[x][y] != '.' && arr[x][y] != 'O')
				break;
			if (arr[x][y] == 'O' && cB == 'R') {
				redisOver = true;
				arr[x - dx[lev]][y - dy[lev]] = '.';
				break;
			} else if (arr[x][y] == 'O' && cB == 'B') {
				blueisOver = true;
				arr[x - dx[lev]][y - dy[lev]] = '.';
				break;
			} else {
				arr[x][y] = cB;
				arr[x - dx[lev]][y - dy[lev]] = '.';
			}
		}

		if (cA == 'R')
			return new Dama(null, 0, a - dx[lev], b - dy[lev], x - dx[lev], y - dy[lev], 0);
		else
			return new Dama(null, 0, x - dx[lev], y - dy[lev], a - dx[lev], b - dy[lev], 0);

	}

}
