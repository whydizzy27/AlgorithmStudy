package m2w3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
	int x;
	int y;
	int wc;

	public Pair(int x, int y, int wc) {
		this.x = x;
		this.y = y;
		this.wc = wc;
	}
}

public class BOJ_1261_알고스팟 {
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);

		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String[] str2 = br.readLine().split("");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(str2[j - 1]);
			}
		}

		bw.write(String.valueOf(bfs(new Pair(1, 1, 0))));
		bw.flush();
		br.close();
		bw.close();
	}

	private static int bfs(Pair p2) {
		Deque<Pair> dq = new LinkedList<>();
		dq.offer(p2);

		while (!dq.isEmpty()) {
			Pair p = dq.poll();
			
			if(p.x==N&&p.y==M) {
				return p.wc;
			}
			
			visited[p.x][p.y] = true;

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx >= 1 && nx <= N && ny >= 1 && ny <= M) {
					if (map[nx][ny] == 0 && !visited[nx][ny]) {
						dq.offerFirst(new Pair(nx, ny, p.wc));
						visited[nx][ny] = true;
					}
					else if(map[nx][ny] == 1 && !visited[nx][ny]){
						dq.offerLast(new Pair(nx, ny, p.wc+1));
						visited[nx][ny] = true;
					}
				}
			}

		}
		
		return -1;

	}

}
