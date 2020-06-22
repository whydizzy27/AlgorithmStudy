package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class winDot implements Comparable<winDot> {
	int x;
	int y;

	public winDot(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(winDot o) {
		if (this.y == o.y)
			return this.x - o.x;
		return this.y - o.y;
	}

}

public class BOJ_2615_오목 {
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[][] map;
	static boolean[][] visited;
	static int winner;
	static boolean isOver;
	static LinkedList<winDot> list;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new int[20][20];
		visited = new boolean[20][20];

		for (int i = 1; i < 20; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 1; j < 20; j++) {
				map[i][j] = Integer.parseInt(str[j - 1]);
			}
		}

		L: for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				if (map[i][j] == 0)
					continue;
				if (isOver)
					break L;
				search(i, j);
			}
		}

		bw.write(String.valueOf(winner) + "\n");
		Collections.sort(list);
		if (winner != 0)
			bw.write(String.valueOf(list.get(0).x + " " + list.get(0).y));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void search(int i, int j) {

		for (int k = 0; k < 4; k++) {
			int nx = i;
			int ny = j;
			int rx = i;
			int ry = j;
			int ccnt = 1;
			int rcnt = 0;
			
			list = new LinkedList<winDot>();
			while (true) {
				list.add(new winDot(nx, ny));
				nx += dx[k];
				ny += dy[k];
				if (nx < 1 || nx > 19 || ny < 1 || ny > 19)
					break;

				if (map[nx][ny] != map[i][j])
					break;

				ccnt++;

			}
			while (true) {
				list.add(new winDot(rx, ry));
				rx += dx[k+4];
				ry += dy[k+4];
				if (rx < 1 || rx > 19 || ry < 1 || ry > 19)
					break;

				if (map[rx][ry] != map[i][j])
					break;

				rcnt++;

			}
			int totalcnt = ccnt+rcnt;
			if (totalcnt == 5) {
				winner = map[i][j];
				isOver=true;
				return;
			}

		}

	}

}
