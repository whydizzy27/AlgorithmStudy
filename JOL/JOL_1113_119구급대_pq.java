package m3w3;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class JOL_1113_119구급대_pq {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int endX,endY,min=Integer.MAX_VALUE,M,N,map[][];
	static int[][] visited;
	public static class Eme implements Comparable<Eme>{
		int x,y,before,cnt ;

		public Eme(int x, int y, int before, int cnt) {
			this.x = x;
			this.y = y;
			this.before = before;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Eme o) {
			return Integer.compare(this.cnt, o.cnt);
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		endX = Integer.parseInt(st.nextToken());
		endY = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(new Eme(0, 0, -1, 0));
		bw.write(String.valueOf(min) + " ");
		bw.flush();
		br.close();
		bw.close();
	}
	private static void bfs(Eme eme) {
		PriorityQueue<Eme> q= new PriorityQueue<>();
		q.offer(eme);
		visited[eme.x][eme.y]=0;
		while(!q.isEmpty()) {
			Eme e = q.poll();
			
			if(e.x==endX && e.y==endY) {
				if(min > e.cnt)
					min=e.cnt;
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = e.x + dx[i];
				int ny = e.y + dy[i];
				
				if(nx>=0&&nx<N&&ny>=0&&ny<M)
					if(map[nx][ny]==1) {
						int k=0;
						if(e.before!=i && e.before!=-1) {
							k=1;
						}
						if(e.cnt + k <= visited[nx][ny]) {
							visited[nx][ny] = e.cnt + k;
							q.offer(new Eme(nx, ny, i, e.cnt + k));
						}
					}
			}
		}
	}

}
