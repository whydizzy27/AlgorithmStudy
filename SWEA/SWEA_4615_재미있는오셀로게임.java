package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution_D3_4615_����ִ¿����ΰ���_������ {
	static int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= tn; tc++) {

			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			int M = Integer.parseInt(str[1]);
			
			map = new int[N + 1][N + 1];
			map[N / 2][N / 2] = 2;
			map[N / 2][N / 2 + 1] = 1;
			map[N / 2 + 1][N / 2] = 1;
			map[N / 2 + 1][N / 2 + 1] = 2;
//			for (int i = 1; i <= N; i++) {
//				for (int j = 1; j <= N; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//				
//			}
			for (int i = 0; i < M; i++) {
				String[] str2 = br.readLine().split(" ");
				int c = Integer.parseInt(str2[0]);
				int r = Integer.parseInt(str2[1]);
				int color = Integer.parseInt(str2[2]);

				map[r][c] = color;
				func(r, c, color);
			}
//			for (int i = 1; i <= N; i++) {
//				for (int j = 1; j <= N; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//				
//			}
			int Wnum = 0;
			int Bnum = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 1)
						Bnum++;
					else if (map[i][j] == 2)
						Wnum++;
				}
			}
			bw.write("#" + tc + " " + String.valueOf(Bnum) + " " + String.valueOf(Wnum) + "\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

	public static void func(int x, int y, int color) {
		Queue<Pair> q = new LinkedList<>();
		
		for (int i = 0; i < 8; i++) {
			int nx = x;
			int ny = y;
			int cnt = 0;
			while (true) {
				nx += dx[i];
				ny += dy[i];

				if (nx < 1 || nx > N || ny < 1 || ny > N) {
					while (!q.isEmpty()) {
						q.poll();
					}
					break;
				}
				if (map[nx][ny] == 0) {
					while (!q.isEmpty()) {
						q.poll();
					}
					break;
				}

				else if (map[nx][ny] == map[x][y]) {
					for (int j = 0; j < cnt; j++) {
						Pair p = q.poll();
						map[p.x][p.y] = color;
					}
					break;
				} else if (map[nx][ny] != map[x][y]) {
					q.offer(new Pair(nx, ny));
					cnt++;
				}

			}

		}
	}

}
