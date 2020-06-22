package m3w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_9663_nQueen_2 {
	static int cnt, N;
	static int[] col;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		
		//열 기준. 즉 1열은 무슨 행으로 시작할건지.
		for (int i = 1; i <= N; i++) {
			col = new int[N+1];
			col[1]=i;
			
			//1열부터 스타트
			dfs(1);
		}
		
		bw.write(String.valueOf(cnt));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int idx) {
		if (idx == N) {
			cnt++;
			return;
		}
		
		//행 1~N
		for (int nextRow = 1; nextRow <= N; nextRow++) {
			if(isPossible(idx+1,nextRow)) {
				col[idx+1]=nextRow;
				dfs(idx+1);
			}
		}
		
		
		
	}
	
	private static boolean isPossible(int ny, int nx) {
		
		for (int i = 1; i < ny; i++) {
			//같은 행
			if(col[i]==nx)
				return false;
			//열은 고려할 필요없지
			
			//대각선
			if(Math.abs(nx-col[i])==Math.abs(i-ny))
				return false;
		}
		
		return true;
		
	}

}
