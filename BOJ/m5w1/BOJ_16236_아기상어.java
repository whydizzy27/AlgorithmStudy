package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어 {
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int size = 2,N, sx, sy, cnt, time;
	static boolean[][] visited;

	static class Shark {
		int x, y, size, cnt, time;

		public Shark(int x, int y, int size, int cnt, int time) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.cnt = cnt;
			this.time = time;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sx = i;
					sy = j;
					map[i][j] = 0;
				}
			}
		}

		while (true) {
			visited = new boolean[N][N];
//			System.out.println(sx + " " +sy);
//			System.out.println("크기                "+ size);
			if (!bfs(new Shark(sx, sy, size, cnt, time)))
				break;
		}

		bw.write(String.valueOf(time) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean bfs(Shark shark) {
		LinkedList<Shark> list = new LinkedList<Shark>();
		Queue<Shark> q = new LinkedList<>();
		q.offer(shark);
		visited[shark.x][shark.y] = true;
		int st = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Shark s = q.poll();

			if (s.time > st)
				break;

			for (int i = 0; i < 4; i++) {
				int nx = s.x + dx[i];
				int ny = s.y + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N)
					if (!visited[nx][ny] && map[nx][ny] <= s.size) {
						if (map[nx][ny] < s.size && map[nx][ny] != 0) {
							visited[nx][ny] = true;
							st = s.time;
							if (s.cnt + 1 == s.size) {
								list.add(new Shark(nx, ny, s.size+1, 0, s.time+1));
							} else {
								list.add(new Shark(nx, ny, s.size, s.cnt+1, s.time+1));
							}
						} else if (map[nx][ny] == s.size || map[nx][ny] == 0) {
							visited[nx][ny] = true;
							q.offer(new Shark(nx, ny, s.size, s.cnt, s.time + 1));
						}
					}
			}
		}

		if(list.size()==0)
			return false;
		
		list.sort(new Comparator<Shark>() {
			@Override
			public int compare(Shark s1, Shark s2) {
				if (s1.x == s2.x)
					return Integer.compare(s1.y, s2.y);
				return Integer.compare(s1.x, s2.x);
			}
		});

		Shark sh = list.get(0);

		map[sh.x][sh.y] = 0;
		sx = sh.x;
		sy = sh.y;
		
		size = sh.size;
		cnt = sh.cnt;
		time = sh.time;
		
		return true;
	}

}
