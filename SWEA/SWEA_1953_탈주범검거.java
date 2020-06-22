package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//SWEA 탈주범 검거
public class SWEA_1953_탈주범검거 {
	static class Dot{
		int x,y;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int map[][],path[][],N,M,hour;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static boolean visited[][],connection[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N =  Integer.parseInt(st.nextToken());
			M =  Integer.parseInt(st.nextToken());
			int startX =  Integer.parseInt(st.nextToken());
			int startY =  Integer.parseInt(st.nextToken());
			hour =  Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//파이프 연결 정보
			connection = new boolean[8][4];
			connection[1][0]=true;
			connection[1][1]=true;
			connection[1][2]=true;
			connection[1][3]=true;
			
			connection[2][0]=true;
			connection[2][2]=true;
			
			connection[3][1]=true;
			connection[3][3]=true;
			
			connection[4][0]=true;
			connection[4][1]=true;
			
			connection[5][1]=true;
			connection[5][2]=true;
			
			connection[6][2]=true;
			connection[6][3]=true;
			
			connection[7][3]=true;
			connection[7][0]=true;
			
			
			visited = new boolean[N][M];
			int spot = bfs(new Dot(startX, startY));
			
			bw.write("#" + tc + " " + spot + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
	private static int bfs(Dot dot) {
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		visited[dot.x][dot.y]=true;
		int time = 0;
		int cnt=0;
		while(!q.isEmpty()) {
			int qsize = q.size();
			while(qsize-->0) {
				Dot d = q.poll();
				cnt++;
				//현재 파이프 모양
				int cur = map[d.x][d.y];
				for (int i = 0; i < 4; i++) {
					//가능한 방향 아니면 넘김
					if(!connection[cur][i]) continue;
					
					int nx = d.x + dx[i];
					int ny = d.y + dy[i];
					
					if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny] || map[nx][ny]==0) continue;
					
					//다음 파이프가 나와 연결 될 수 있는지
					if(!connection[map[nx][ny]][(i+2)%4]) continue;
					
					q.offer(new Dot(nx, ny));
					visited[nx][ny]=true;
				}
				
			}//end sm
			time++;
			
			if(time==hour)
				return cnt;
		}//end big
		
		return cnt;
	}

}
