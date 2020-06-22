package m2w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class Point {
	int x;
	int y;
	int wc;
	int cnt;

	public Point(int x, int y, int wc, int cnt) {
		this.x = x;
		this.y = y;
		this.wc = wc;
		this.cnt = cnt;
	}
}

public class BOJ_2206_벽부수고이동하기 {
	static int[][] map;
	static int[][] visited;
	static int[] dx = { 0, 1, -1, 0 };
	static int[] dy = { 1, 0, 0, -1 };
	static int N, M, ans;
	static boolean chk;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new int[N + 1][M + 1];
		visited = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String str2 = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str2.charAt(j - 1) - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}

		bfs(new Point(1, 1, 0, 1));
		if (!chk)
			ans = -1;

		bw.write(String.valueOf(ans));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Point pp) {
		Queue<Point> q = new LinkedList<Point>();

		q.offer(pp);
		visited[pp.x][pp.y] = 0;

		while (!q.isEmpty()) {
			Point p = q.poll();
			int cx = p.x;
			int cy = p.y;
			int ccnt = p.cnt;
			int cwc = p.wc;

			if (cx == N && cy == M) {
				chk = true;
				ans = ccnt;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (nx >= 1 && nx <= N && ny >= 1 && ny <= M) {
					if (visited[nx][ny] > cwc && map[nx][ny] == 0) {
						visited[nx][ny] = cwc;
						q.offer(new Point(nx, ny, cwc, ccnt + 1));
					} else if (cwc == 0 && visited[nx][ny] > cwc && map[nx][ny] == 1) {
						visited[nx][ny] = cwc + 1;
						q.offer(new Point(nx, ny, cwc + 1, ccnt + 1));
					}
				}
			}
		}
	}

}
