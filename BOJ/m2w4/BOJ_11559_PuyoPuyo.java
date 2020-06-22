package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Peir{
	int x,y;

	public Peir(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
}
public class BOJ_11559_PuyoPuyo {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static char[][] map;
	static boolean[][] visited;
	static int ys;
	static LinkedList<Peir> totallist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new char[12][6];

		for (int i = 0; i < 12; i++) {
			String s = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		while (true) {
			visited = new boolean[12][6];
			totallist = new LinkedList<Peir>();

			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] == '.' || visited[i][j])
						continue;
					bfs(i, j);
//					System.out.println(i+" "+j);
				}
			}
//			System.out.println(ys);
			if(totallist.isEmpty()) break;
			transform();
			down();
		}

		bw.write(String.valueOf(ys));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(int i, int j) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		q.offer(j);
		visited[i][j] = true;
		int cnt=0;
		
		LinkedList<Peir> list = new LinkedList<Peir>();
		while (!q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();
//			System.out.println(x+" "+y);
			cnt++;
			list.add(new Peir(x, y));
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];

				if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6)
					if (!visited[nx][ny] && map[nx][ny] == map[x][y]) {
						q.offer(nx);
						q.offer(ny);
						visited[nx][ny]=true;
					}
			}
		}
		
		if(cnt>=4) {
			for (Peir p : list) {
				totallist.add(p);
			}
		}

	}
	
	private static void transform() {
		for (Peir p : totallist) {
			map[p.x][p.y]='.';
		}
		ys++;
	}
	
	private static void down() {
		for (int i = 10; i >=0; i--) {
			for (int j = 0; j < 6; j++) {
				if (map[i][j] == '.')
					continue;
				int k=i;
				while(true) {
					if(k>=11) break;
					if(map[k+1][j]=='.') {
						map[k+1][j]=map[k][j];
						map[k][j]='.';
					}
					k++;
					
				}
				
			}
		}
	}

}
