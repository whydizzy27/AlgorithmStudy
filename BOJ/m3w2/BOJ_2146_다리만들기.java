package m3w2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Dist{
	int x,y;
	public Dist(int x,int y) {
		this.x=x;
		this.y=y;
	}
}
public class BOJ_2146_다리만들기 {
	static int[][] map;
	static boolean[][] visited;
	static int min,N;
	static ArrayList<Dist> list;
	static int[] dx = {-1,1,0,0}, temp;
	static int[] dy = {0,0,-1,1};
	static ArrayList<ArrayList<Dist>> listArr;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		temp = new int[2];

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		listArr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==0||visited[i][j]) continue;
				list = new ArrayList<>();
				bfs(new Dist(i, j));
				listArr.add(list);
			}
		}
		min = Integer.MAX_VALUE;
		
		combination(0,0);
//		System.out.println(listArr.size());
		bw.write(String.valueOf(min-1) + "\n");

		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void combination(int idx, int cur) {
		if(idx==2) {
			int value=0;
			ArrayList<Dist> a = listArr.get(temp[0]);
			ArrayList<Dist> b = listArr.get(temp[1]);
			
			for (int i = 0; i < a.size(); i++) {
				for (int j = 0; j < b.size(); j++) {
					value = calc(a.get(i).x,a.get(i).y,b.get(j).x,b.get(j).y);
					if(min>value)
						min=value;
				}
			}
			
			
			return;
		}
		
		for (int i = cur; i < listArr.size(); i++) {
			if(min==1) return;
			temp[idx]=i;
			combination(idx+1, i+1);
		}
	}
	private static void bfs(Dist dd) {
		Queue<Dist> q = new LinkedList<>();
		q.offer(dd);
		visited[dd.x][dd.y]=true;

		while(!q.isEmpty()) {
			Dist d = q.poll();
			list.add(d);
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				
				if(nx>=0&&nx<N&&ny>=0&&ny<N)
					if(map[nx][ny]==1&&!visited[nx][ny]) {
						visited[nx][ny]=true;
						q.offer(new Dist(nx, ny));
					}
			}
			
			
		}
	}
	
	private static int calc(int a,int b,int x,int y) {
		return Math.abs(a-x) + Math.abs(b-y);
	}

}
