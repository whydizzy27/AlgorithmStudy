package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Hello {
	int x, y;

	public Hello(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

public class BOJ_10026_적록색약 {
	static char[][] map;
	static int cnt,N;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		boolean[][] visitedNormal = new boolean[N][N];
		boolean[][] visitedRG = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visitedNormal[i][j])
					continue;
				bfs(new Hello(i, j), visitedNormal);
			}
		}
		int Ncnt = cnt;
		cnt=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'G')
					map[i][j] = 'R';
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visitedRG[i][j])
					continue;
				bfs(new Hello(i, j), visitedRG);
			}
		}
		int RGcnt = cnt;

		bw.write(String.valueOf(Ncnt + " " + RGcnt));
		bw.flush();
		br.close();
		bw.close();

	}

	private static void bfs(Hello hh, boolean[][] visited) {
		Queue<Hello> q = new LinkedList<>();
		q.offer(hh);
		cnt++;
		visited[hh.x][hh.y]=true;
		char color = map[hh.x][hh.y];
		while (!q.isEmpty()) {
			Hello h = q.poll();
			int x = h.x;
			int y = h.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx>=0&&nx<N&&ny>=0&&ny<N)
					if(!visited[nx][ny]&&map[nx][ny]==color) {
						visited[nx][ny]=true;
						q.offer(new Hello(nx, ny));
					}
			}
			
			
		}
	}

}
