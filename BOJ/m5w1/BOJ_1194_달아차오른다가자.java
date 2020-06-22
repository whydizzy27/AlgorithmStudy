package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import my.solve.BOJ_1194_달아차오른다가자.Miro;

public class BOJ_1194_달아차오른다가자 {
	static char[][] map;
	static int N, M;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][][] visited;

	static class Miro {
		int x, y, key;

		public Miro(int x, int y, int key) {
			this.x = x;
			this.y = y;
			this.key = key;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Miro start = null;
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '0') {
					start = new Miro(i, j, 0);
					map[i][j] = '.';
				}
			}
		}
		// 3차원 비지티드 x y 키보유현황 6비트니까 총 0~63
		visited = new boolean[N][M][64];

		bw.write(String.valueOf(bfs(start)) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static int bfs(Miro miro) {
		Queue<Miro> q = new LinkedList<>();
		q.offer(miro);
		visited[miro.x][miro.y][0] = true;
		int time = 0;
		while (!q.isEmpty()) {
			int qsize = q.size();
			while (qsize-- > 0) {
				Miro m = q.poll();
				// 현재 키
				for (int i = 0; i < 4; i++) {
					int nx = m.x + dx[i];
					int ny = m.y + dy[i];
					int key = m.key;

					if (nx < 0 || nx >= N || ny < 0 || ny >= M)
						continue;
					if (map[nx][ny] == '#')
						continue;

					// a-f키
					// 비트마스킹으로 키 보유 현황 표현
					if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
						// 키 추가. 기존에 있어도 덮.
						key |= (1 << map[nx][ny] - 'a');
					}

					if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
						// 해당하는 열쇠가 없으면 컨티뉴
						if ((key & (1 << map[nx][ny] - 'A')) == 0)
							continue;
					}
					
					if(map[nx][ny]=='1') {
						return time +1;
					}

					if(visited[nx][ny][key]) continue;
					visited[nx][ny][key] = true;
					q.offer(new Miro(nx, ny, key));

				}

			}
			time++;
		}

		return -1;
	}

}
