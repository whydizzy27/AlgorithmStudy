package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_�丶�� {
	static int N, M;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Integer> q;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);//��
		M = Integer.parseInt(str[1]);//��
		map = new int[M][N];
		q = new LinkedList<Integer>();
		for (int i = 0; i < M; i++) {
			String[] str2 = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str2[j]);
				if(map[i][j]==1) {
					
					q.offer(i);
					q.offer(j);
				}
			}
		}
		bfs();
		int day=0;
		L:for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==0) {
					day=-1;
					break L;
				}
				day = Math.max(day, map[i][j]);
			}
		}
		if(day!=-1)
			day-=1;
		bw.write(String.valueOf(day));
		bw.flush();
		br.close();
		bw.close();
	}

	public static void bfs() {
		
		while(!q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();
			
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if (nx >= 0 && nx < M && ny >= 0 && ny < N)
					if (map[nx][ny]==0) {
						q.offer(nx);
						q.offer(ny);
						map[nx][ny]=map[x][y]+1;
					}
				
				
			}
		}

	}

	

}
