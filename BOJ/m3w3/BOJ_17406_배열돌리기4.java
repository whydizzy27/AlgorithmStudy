package m3w3;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_17406_배열돌리기4 {
	static int[][] map,rotate,temp;
	static int min=Integer.MAX_VALUE,N,M,K;
	static int[] order;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		temp = new int[N+1][M+1];
		order = new int[K];
		visited = new boolean[K];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		rotate = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rotate[i][0]=Integer.parseInt(st.nextToken());
			rotate[i][1]=Integer.parseInt(st.nextToken());
			rotate[i][2]=Integer.parseInt(st.nextToken());
		}
		
		
		permutation(0);
		
		//각 행 최소 구하는 함수
		findMin();
		
		bw.write(String.valueOf(min) + " ");
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void permutation(int idx) {
		if(idx==K) {
			
			copy();
			
			for (int i = 0; i < K; i++) {
				int r = rotate[order[i]][0];
				int c = rotate[order[i]][1];
				int s = rotate[order[i]][2];
				
				int rms = r-s;
				int rps = r+s;
				int cms = c-s;
				int cps = c+s;
				
				dfs(rms,rps,cms,cps,cps-cms+1);
				
			}
			findMin();
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if(visited[i]) continue;
			visited[i]=true;
			order[idx]=i;
			permutation(idx+1);
			visited[i]=false;
		}
	}
	
	private static void dfs(int rms, int rps, int cms, int cps, int idx) {
		if(idx<=1)
			return;
		
		int tempValue = temp[rms][cms];
		for (int i = rms+1; i <= rps; i++) {
			temp[i-1][cms]=	temp[i][cms];
		}
		for (int i = cms+1; i <= cps; i++) {
			temp[rps][i-1]=	temp[rps][i];
		}
		for (int i = rps-1; i >=rms; i--) {
			temp[i+1][cps]=	temp[i][cps];
		}
		for (int i = cps-1; i >= cms+1; i--) {
			temp[rms][i+1]=	temp[rms][i];
		}
		temp[rms][cms+1]=tempValue;
		
		
		dfs(rms+1,rps-1,cms+1,cps-1,idx-2);
	}

	private static void findMin() {
		for (int i = 1; i <= N; i++) {
			int sum=0;
			for (int j = 1; j <= M; j++) {
				sum+=temp[i][j];
			}
			if(min>sum)
				min=sum;
			
		}
	}
	
	private static void copy() {
		for (int i = 1; i <= N; i++) {
			temp[i]=map[i].clone();
		}
	}
}
