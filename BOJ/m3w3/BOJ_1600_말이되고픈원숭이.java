package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Mon {
	int x, y, cnt, jump;

	public Mon(int x, int y, int cnt, int jump) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.jump = jump;
	}

}

public class BOJ_1600_말이되고픈원숭이 {
	static int[][] map;
	static int[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] hdx = { -2, -2, 2, 2, -1, 1, -1, 1 };
	static int[] hdy = { -1, 1, -1, 1, -2, -2, 2, 2 };
	static int min = Integer.MAX_VALUE, N, M, K;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j]=-1;
			}
		}

		bfs(new Mon(0, 0, 0, K));

		bw.write((min == Integer.MAX_VALUE) ? String.valueOf(-1):String.valueOf(min));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Mon mon) {
		Queue<Mon> q = new LinkedList<>();
		q.offer(mon);
		visited[0][0] = K;

		while (!q.isEmpty()) {
			Mon m = q.poll();
			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(visited[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			if(m.x==N-1 && m.y==M-1) {
				min=m.cnt;
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = m.x + dx[i];
				int ny = m.y + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M)
					if (visited[nx][ny]<m.jump && map[nx][ny] != 1) {
						visited[nx][ny] = m.jump;
						q.offer(new Mon(nx, ny, m.cnt + 1, m.jump));
					}
			}
			if (m.jump != 0) {
				for (int i = 0; i < 8; i++) {
					int nx = m.x + hdx[i];
					int ny = m.y + hdy[i];

					if (nx >= 0 && nx < N && ny >= 0 && ny < M)
						if (visited[nx][ny]<m.jump-1 && map[nx][ny] != 1) {
							visited[nx][ny] = m.jump-1;
							q.offer(new Mon(nx, ny, m.cnt + 1, m.jump - 1));
						}
				}
			}

		}
	}

}
