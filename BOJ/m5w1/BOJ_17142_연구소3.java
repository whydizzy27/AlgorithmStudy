package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_17142_연구소3 {
	static class Vir{
		int x,y;

		public Vir(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	static int[][] map,copymap;
	static int N,M,V,min=Integer.MAX_VALUE,total,temptotal;
	static boolean temp[],visited[][];
	static ArrayList<Vir> list;
	static Queue<Vir> q;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		copymap = new int[N][N];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2)
					list.add(new Vir(i, j));
				else if(map[i][j]==0)
					total++;
			}
		}
		V = list.size();
		temp = new boolean[V];
		if(total==0) {
			min=0;
		}else {
			combi(0,0);
		}
		
		bw.write(min==Integer.MAX_VALUE?String.valueOf(-1):String.valueOf(min) + "");
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void combi(int idx, int cur) {
		if(idx==V-M) {
			copy();
			visited = new boolean[N][N];
			q = new LinkedList<Vir>();
			temptotal = total;
			//바이러스 제거
			for (int i = 0; i < V; i++) {
				Vir v=list.get(i);
				if(temp[i]) {
					copymap[v.x][v.y]=3;
				}
				else {
					q.offer(v);
					visited[v.x][v.y]=true;
				}
				
			}
			
			int time = bfs();
			if(temptotal==0)
				min = Math.min(min, time);
			
			return;
		}
		
		for (int i = cur; i < V; i++) {
			temp[i]=true;
			combi(idx+1,i+1);
			temp[i]=false;
		}
	}

	private static int bfs() {
		int time=0;
		while(!q.isEmpty()) {
			int qsize = q.size();
			while(qsize-->0) {
				Vir v = q.poll();
				
				for (int i = 0; i < 4; i++) {
					int nx = v.x +dx[i];
					int ny = v.y +dy[i];
					
					if(nx<0 || nx>=N ||ny<0 || ny>=N || visited[nx][ny]) continue;
					if(copymap[nx][ny]==0) {
						visited[nx][ny]=true;
						q.offer(new Vir(nx, ny));
						temptotal--;
					}
					else if(copymap[nx][ny]==3) {
						visited[nx][ny]=true;
						q.offer(new Vir(nx, ny));
					}
				}
			}//mini while
			time++;
			
			if(temptotal==0)
				return time;

		}//big while
		
		return 0;
	}

	private static void copy() {
		for (int i = 0; i < N; i++) {
			copymap[i]=map[i].clone();
		}
	}
	
	
}