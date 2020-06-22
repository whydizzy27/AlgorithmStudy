package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_���� {
	static char[][] map;
	static int R,C;
	static boolean[][] visited;
	static int[] dx = {-1,0,1};
	static int[] dy = {1,1,1};
	static boolean isOk;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");

		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j]=s.charAt(j);
			}			
		}
		
		for (int i = 0; i < R; i++) {
			isOk = false;
			dfs(i,0);
		}
		int cnt=0;
		for (int i = 0; i < R; i++) {
			if(visited[i][C-1])
				cnt++;
		}
		bw.write(String.valueOf(cnt));
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void dfs(int i,int j) {
		int x = i;
		int y = j;
		
		for (int k = 0; k < 3; k++) {
			if(isOk ==true) return;
			int nx = x + dx[k];
			int ny = y + dy[k];

			if(nx>=0&&nx<R&&ny>=0&&ny<C) {
				if(!visited[nx][ny]&&map[nx][ny]!='x') {
					visited[nx][ny]=true;
					
					if(ny==C-1) {
						isOk = true;
						return;
					}
					dfs(nx,ny);
				}
				
			}
			
			
		}
	}

}
