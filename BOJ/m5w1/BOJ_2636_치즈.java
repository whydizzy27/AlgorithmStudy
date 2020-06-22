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

import my.solve.BOJ_2636_치즈.Chi;

//백준 치즈
public class BOJ_2636_치즈 {
	static int N,M;
	static int[][] map;
	static int[][] copy;
	static int max;
	static boolean[][] visited;
	static ArrayList<Chi> list;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static class Chi{
		int x,y;

		public Chi(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1)
					cnt++;
			}
		}
		Chi start = new Chi(0, 0);
		int before = 0;
		int time = 0;
		while(true) {
			if(cnt==0) break;
			visited = new boolean[N][M];
			list = new ArrayList<>();
			int temp = bfs(start);
			cnt-=temp;
			before=cnt+temp;
			time++;
		}

		bw.write(String.valueOf(time)+"\n");
		bw.write(String.valueOf(before)+"\n");

		bw.flush();
		br.close();
		bw.close();
	}
	private static int bfs(Chi start) {
		Queue<Chi> q = new LinkedList<>();
		q.offer(start);
		visited[start.x][start.y]=true;
		int cnt=0;
		while(!q.isEmpty()) {
			Chi c = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = c.x + dx[i];
				int ny = c.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny]) continue;
				
				if(map[nx][ny]==0) {
					visited[nx][ny]=true;
					q.offer(new Chi(nx, ny));
				}else {
					visited[nx][ny]=true;
					list.add(new Chi(nx, ny));
					cnt++;
				}
			}
		}
		
		for (Chi c : list) {
			map[c.x][c.y]=0;
		}
		return cnt;
	}


}
