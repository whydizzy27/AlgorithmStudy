package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_7208_지도칠하기 {
	static int N,adj[][],ncolor[],min;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= tn; tc++) {
			min=Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			
			ncolor = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				ncolor[i]=Integer.parseInt(st.nextToken());
			}
			
			adj = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					adj[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//중복순열
			permutation(0,0);
			
			bw.write("#" + tc + " " + String.valueOf(min) + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	private static void permutation(int idx, int cnt) {
		if(idx==N) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(adj[i][j]==1 && ncolor[i]==ncolor[j])return;
				}
			}
			
			min = Math.min(min, cnt);
			return;
		}
		
		int temp=0;
		//국가 색깔로 중복 순열. 모든경우 다해보는것. 모든 4가지 중복순열 다한 다음 기저에서 테스트해봐도 되지만 이렇게 중간에 cnt매겨가면서 해도 됨.
		for (int i = 1; i <= 4; i++) {
			//기존 색깔과 같으면
			if(ncolor[idx]==i) {
				permutation(idx+1,cnt);
			}
			//다르면
			else {
				temp=ncolor[idx];
				ncolor[idx]=i;
				permutation(idx+1, cnt+1);
				ncolor[idx]=temp;
			}
		}
		
	}
	

}
