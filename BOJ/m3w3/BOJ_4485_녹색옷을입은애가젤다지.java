package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Jel {
	int x, y, cost;

	public Jel(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

}

public class BOJ_4485_녹색옷을입은애가젤다지 {
	static int N, min;
	static int[][] map,visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = 1;

		while (true) {
			min=Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());

			if (N == 0)
				break;
			map = new int[N][N];
			visited = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], Integer.MAX_VALUE);
			}
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs(new Jel(0, 0, 0));

			bw.write("Problem " + tc + ": " + String.valueOf(min) + "\n");
			tc++;
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Jel jel) {
		Queue<Jel> q = new LinkedList<Jel>();
		q.offer(jel);
		visited[jel.x][jel.y]=jel.cost;
		while(!q.isEmpty()) {
			Jel jl = q.poll();
			jl.cost+=map[jl.x][jl.y];
			if(jl.x==N-1 && jl.y==N-1) {
				if(min>jl.cost)
					min = jl.cost;
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nx = jl.x + dx[i];
				int ny = jl.y + dy[i];
				
				if(nx>=0 && nx<N && ny>=0 && ny<N)
					if(jl.cost+map[nx][ny]<visited[nx][ny]) {
						visited[nx][ny]=jl.cost+map[nx][ny];
						q.offer(new Jel(nx, ny, jl.cost));
					}
			}
			
			
		}

	}

}
