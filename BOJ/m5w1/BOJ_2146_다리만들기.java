package my.solve;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146_다리만들기 {
	static int[][] map;
	static int N,min=Integer.MAX_VALUE,islNo=2;;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	static class Isl{
		int x,y,cnt;

		public Isl(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		public Isl(int x, int y) {
			this.x = x;
			this.y = y;
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
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]!=1) continue;
				map[i][j]=islNo;
				bfs(new Isl(i, j));
				islNo++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==0)continue;
				visited = new boolean[N][N];
				bfs2(new Isl(i, j,0));
			}
		}
		
		bw.write(String.valueOf(min) + " ");
		bw.flush();
		br.close();
		bw.close();
	}
	private static void bfs2(Isl isl) {
		Queue<Isl> q = new LinkedList<>();
		q.offer(isl);
		int cur = map[isl.x][isl.y];
		while(!q.isEmpty()) {
			Isl is = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = is.x + dx[i];
				int ny = is.y + dy[i];
				
				if(nx>=0 && nx<N && ny>=0 && ny<N)
					if(map[nx][ny]!=cur && !visited[nx][ny]) {
						if(map[nx][ny]==0) {
							visited[nx][ny]=true;
							q.offer(new Isl(nx, ny,is.cnt+1));
						}
						else {
							int leg = is.cnt;
							if(min>leg)
								min=leg;
							return;
						}
					}
			} 
			
		}
	}
	private static void bfs(Isl isl) {
		Queue<Isl> q = new LinkedList<>();
		q.offer(isl);
		
		while(!q.isEmpty()) {
			Isl is = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = is.x + dx[i];
				int ny = is.y + dy[i];
				
				if(nx>=0 && nx<N && ny>=0 && ny<N)
					if(map[nx][ny]==1) {
						map[nx][ny]=islNo;
						q.offer(new Isl(nx, ny));
					}
			} 
			
		}
	}

}
