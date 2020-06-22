package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_3190_뱀 {
	static int[][] map;
	static String[] visited;
	static int cnt, N;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 크기
		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		// 사과개수
		int Ac = Integer.parseInt(br.readLine());

		for (int i = 0; i < Ac; i++) {
			String[] str = br.readLine().split(" ");
			map[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 1;
		}

		visited = new String[10001];
		int Lc = Integer.parseInt(br.readLine());

		for (int i = 0; i < Lc; i++) {
			String[] str = br.readLine().split(" ");
			visited[Integer.parseInt(str[0])] = str[1];
		}

		// 터닝포인트 배열
		bfs(new Pair(1, 1));

		bw.write(String.valueOf(cnt+1));
		bw.flush();
		br.close();
		bw.close();
	}

	public static void bfs(Pair pp) {
		Deque<Pair> q = new LinkedList<Pair>();
		
		q.offer(pp);
		int k = 0; // 디폴트 방향 우측
		int nx = 0;
		int ny = 0;
		
		while (!q.isEmpty()) {
			Pair p = q.peek();

			if (visited[cnt]!=null) {
				if (visited[cnt].equals("D")) {
					k = (k + 1) % 4;
				} else {
					k = (k + 3) % 4;
				}
			}
			nx = p.x + dx[k];
			ny = p.y + dy[k];
			
			if (nx < 1 || nx > N || ny < 1 || ny > N)
				return;

			if (map[nx][ny] == 2)
				return;
			
			q.offerFirst(new Pair(nx,ny));
			if(map[nx][ny]!=1) {
				Pair b = q.pollLast();
				map[b.x][b.y]=0;
			}
			map[nx][ny]=2;



			cnt++;
		}
	}

}
