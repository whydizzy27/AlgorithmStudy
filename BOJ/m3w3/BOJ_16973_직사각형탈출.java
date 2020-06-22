package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Esc {
	int x, y, cnt;

	public Esc(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

}

public class BOJ_16973_직사각형탈출 {
	static int N, M, min=-1, map[][], H, W, fn, fm;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Esc> list;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<Esc>();
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1)
					list.add(new Esc(i, j,0));
			}
		}
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int sn = Integer.parseInt(st.nextToken());
		int sm = Integer.parseInt(st.nextToken());
		fn = Integer.parseInt(st.nextToken());
		fm = Integer.parseInt(st.nextToken());

		bfs(new Esc(sn, sm, 0));

		bw.write(String.valueOf(min));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Esc esc) {
		Queue<Esc> q = new LinkedList<>();
		q.offer(esc);
		visited[esc.x][esc.y] = true;
		while (!q.isEmpty()) {
			Esc e = q.poll();

			if (e.x == fn && e.y == fm) {
				min = e.cnt;
				return;
			}
			L:for (int i = 0; i < 4; i++) {
				int nx = e.x + dx[i];
				int ny = e.y + dy[i];

				if (nx >= 1 && nx <= N && ny >= 1 && ny <= M)
					if (nx + H - 1 >= 1 && nx + H - 1 <= N && ny + W - 1 >= 1 && ny + W - 1 <= M)
						if (!visited[nx][ny] && map[nx][ny] != 1) {

//							for (int j = nx; j < nx + H; j++) {
//								for (int j2 = ny; j2 < ny + W; j2++) {
//									if(map[j][j2]==1)
//										continue L;
//								}
//							}

							for (Esc s : list) {
								if(s.x>=nx&&s.x<=nx+H-1&&s.y>=ny&&s.y<=ny+W-1)
									continue L;
							}
							
							visited[nx][ny] = true;
							q.offer(new Esc(nx, ny, e.cnt + 1));
						}
			}
		}
	}

}
