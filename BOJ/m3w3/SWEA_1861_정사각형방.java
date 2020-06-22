package m3w3;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Rec{
	int x,y;

	public Rec(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class SWEA_1861_정사각형방 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1}; 
	static int[][] map;
	static int max,maxNum,N;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= tn; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			max=0;
			maxNum=Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j]= Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(new Rec(i, j));
				}
			}
			
			bw.write("#"+tc+" "+String.valueOf(maxNum)+" " +String.valueOf(max)+ "\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
	private static void bfs(Rec rec) {
		Queue<Rec> q = new LinkedList<>();
		q.offer(rec);
		int cnt=1;
		while(!q.isEmpty()) {
			Rec r = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = r.x + dx[i];
				int ny = r.y + dy[i];
				
				if(nx>=0&&nx<N&&ny>=0&&ny<N)
					if(map[r.x][r.y]+1==map[nx][ny]) {
						cnt++;
						q.offer(new Rec(nx, ny));
					}
			}
		}
		
		if(max <= cnt) {
			if(max==cnt && map[rec.x][rec.y]<maxNum) {
				maxNum=map[rec.x][rec.y];
			}
			else if(max<cnt){
				max=cnt;
				maxNum=map[rec.x][rec.y];
			}
		}
		
	}

}
