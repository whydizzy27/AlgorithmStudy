package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Pairrr {
	int x, y;

	public Pairrr(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class SWEA_1868_파핑파핑지뢰 {
	static char map[][];
	static boolean[][] visited;
	static int N;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			N = Integer.parseInt(br.readLine());
			int click = 0;
			map = new char[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*')
						continue;
					make(new Pairrr(i, j));
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*' || visited[i][j])
						continue;
					if (map[i][j] == '0') {
						bfs(new Pairrr(i, j));
						click++;
					}
				}
			}
//			System.out.println(click);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*' || visited[i][j])
						continue;
					click++;
				}
			}
//			System.out.println(click);

			bw.write("#" + tc + " " + String.valueOf(click) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void make(Pairrr p) {
		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			int nx = p.x + dx[i];
			int ny = p.y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;

			if (map[nx][ny] == '*')
				cnt++;

		}
		map[p.x][p.y] = (char) (cnt + '0');

	}

	private static void bfs(Pairrr ppp) {
		Queue<Pairrr> q = new LinkedList<>();
		q.offer(ppp);
		visited[ppp.x][ppp.y] = true;

		while (!q.isEmpty()) {
			Pairrr p = q.poll();

			for (int i = 0; i < 8; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;

				if (visited[nx][ny])
					continue;

				if (map[nx][ny] == '*')
					continue;

				if (map[nx][ny] != '0') {
					visited[nx][ny] = true;
					continue;
				}
				visited[nx][ny] = true;
				q.offer(new Pairrr(nx, ny));
			}
		}
	}

}
