package m3w2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Pott {
	int x, y, h,cnt;

	public Pott(int x, int y, int h, int cnt) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.cnt = cnt;
	}
	
}

public class BOJ_6593_상범빌딩 {
	static int L, R, C, min;
	static char[][][] map;
	static boolean[][][] visited;
	static int[] dx = { -1, 1, 0, 0, 0, 0 };
	static int[] dy = { 0, 0, -1, 1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, -1, 1 };
	static boolean isSame;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			isSame = false;
			String[] str = br.readLine().split(" ");
			L = Integer.parseInt(str[0]);
			R = Integer.parseInt(str[1]);
			C = Integer.parseInt(str[2]);

			if (L == 0 && R == 0 && C == 0)
				break;
			map = new char[L][R][C];
			visited = new boolean[L][R][C];

			int startH = 0;
			int startX = 0;
			int startY = 0;

			for (int k = 0; k < L; k++) {
				boolean sameS = false;
				boolean sameE = false;
				for (int i = 0; i < R; i++) {
					String s = br.readLine();
					for (int j = 0; j < C; j++) {
						map[k][i][j] = s.charAt(j);
						if (map[k][i][j] == 'S') {
							startH = k;
							startX = i;
							startY = j;
							sameS = true;
						} else if (map[k][i][j] == 'E') {
							sameE = true;
						}
					}
					if (sameS && sameE)
						isSame = true;
				}
				br.readLine();
			}

			min = Integer.MAX_VALUE;
			bfs(new Pott(startX, startY, startH,0));
			if (min == Integer.MAX_VALUE)
				bw.write("Trapped!\n");
			else
				bw.write("Escaped in " + min + " minute(s).\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Pott pot) {
		Queue<Pott> q = new LinkedList<>();
		q.offer(pot);
		visited[pot.h][pot.x][pot.y] = true;
		while (!q.isEmpty()) {
			Pott p = q.poll();
			if(map[p.h][p.x][p.y]=='E') {
				min = p.cnt;
				return;
			}
			for (int i = 0; i < 6; i++) {
				if (isSame)
					if (i == 4 || i == 5)
						continue;

				int nh = p.h + dh[i];
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx >= 0 && nx < R && ny >= 0 && ny < C && nh >= 0 && nh < L)
					if ((map[nh][nx][ny] == '.' || map[nh][nx][ny] == 'E') && !visited[nh][nx][ny]) {
						visited[nh][nx][ny] = true;
						q.offer(new Pott(nx, ny, nh,p.cnt+1));
					}
			}

		}

	}

}
