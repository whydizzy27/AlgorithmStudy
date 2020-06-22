package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class YooDongGyun_BOJ_���ڻ��ǰ�� {
	static int[][] map;
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	static int N,M;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		int K = Integer.parseInt(str[2]);

		int row = (K - 1) / M;
		int col = (K - 1) % M;
		int ans=0;
		map = new int[N][M];
		if (K == 0) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j]=1;
				}
			}
			dfs(0,0);
			ans = map[N-1][M-1] -1;
		} else {

			for (int i = 0; i <= row; i++) {
				for (int j = 0; j <= col; j++) {
					map[i][j]=1;
				}
			}
			
			dfs(0,0);
			int a = map[row][col]-1;
			for (int i = row; i < N; i++) {
				for (int j = col; j < M; j++) {
					map[i][j]=1;
				}
			}
			
			dfs(row,col);
			
			ans=a*(map[N-1][M-1]-1);
		}
		
		bw.write(String.valueOf(ans));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int row, int col) {
		
		for (int i = 0; i < 2; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			
			if(nx>=0&&nx<N&&ny>=0&&ny<M) {
				if(map[nx][ny]>0) {
					map[nx][ny]++;
					dfs(nx,ny);
				}
			}
		}
	}

}
