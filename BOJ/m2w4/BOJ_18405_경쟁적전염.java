package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Virus implements Comparable<Virus>{
	int x,y,lev,time;

	public Virus(int x, int y, int lev, int time) {
		this.x = x;
		this.y = y;
		this.lev = lev;
		this.time = time;
	}

	@Override
	public int compareTo(Virus o) {
		return this.lev-o.lev;
	}
	
}
public class BOJ_18405_경쟁적전염 {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] visited;
	static Queue<Virus> q;
	static LinkedList<Virus> list;
	static int S,N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		map= new int[N+1][N+1];
		visited= new boolean[N+1][N+1];
		
		q = new LinkedList<Virus>();
		list =new LinkedList<Virus>();
		for (int i = 1; i <= N; i++) {
			String[] str2 = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				map[i][j]= Integer.parseInt(str2[j-1]);
				if(map[i][j]!=0)
					list.add(new Virus(i, j, map[i][j],0));
			}
		}
		
		String[] str3 = br.readLine().split(" ");
		S =Integer.parseInt(str3[0]);
		int X =Integer.parseInt(str3[1]);
		int Y =Integer.parseInt(str3[2]);
		
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			q.offer(list.get(i));
		}
		bfs();
		bw.write(String.valueOf(map[X][Y]));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Virus v = q.poll();
			if(v.time==S)
				return;
			int x = v.x;
			int y = v.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx>=1&&nx<=N&&ny>=1&&ny<=N)
					if(!visited[nx][ny]) {
						map[nx][ny]=v.lev;
						visited[nx][ny]=true;
						q.offer(new Virus(nx, ny, v.lev, v.time+1));
					}
			}
		}
		
	}

	

}
