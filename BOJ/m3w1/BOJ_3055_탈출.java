package m3w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

class Escape {
	int x, y, time;
	char c;

	public Escape(int x, int y, int time, char c) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.c = c;
	}
}

public class BOJ_3055_탈출 {
	static int R, C, time;
	static char map[][];
	static Deque<Escape> q;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);

		map = new char[R][C];
		q = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			String str2 = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str2.charAt(j);
				if (map[i][j] == '*') {
					q.offerFirst(new Escape(i, j, 0, '*'));
				} else if (map[i][j] == 'S') {
					q.offerLast(new Escape(i, j, 0, 'S'));
				}
			}
		}

		bfs();
		// 칵투스
		if (time == 0)
			bw.write("KAKTUS");
		else
			bw.write(String.valueOf(time));

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Escape e = q.poll();

			if (e.c == 'D') {
				time = e.time;
				return;
			}

			for (int k = 0; k < 4; k++) {
				int nx = e.x + dx[k];
				int ny = e.y + dy[k];

				if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
					if (e.c == '*') {
						if (map[nx][ny] != 'D' && map[nx][ny] != 'X' && map[nx][ny] != '*') {
							map[nx][ny] = '*';
							q.offer(new Escape(nx, ny, e.time + 1, '*'));
						}
					} else if (e.c == 'S') {
						if (map[nx][ny] != 'S' && map[nx][ny] != 'X' && map[nx][ny] != '*') {
							if (map[nx][ny] == 'D')
								q.offer(new Escape(nx, ny, e.time + 1, 'D'));
							else {
								map[nx][ny] = 'S';
								q.offer(new Escape(nx, ny, e.time + 1, 'S'));
							}
						}
					}
				}

			}
		}

	}

}
