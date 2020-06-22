package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5643_키순서_플로이드워셜 {
	static int N, M, map[][],result;
	static int INF=999999;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			map = new int[N+1][N+1];
			
			for (int[] arr : map) {
				//맥스주면 오버플로우 날지도 모르니
				Arrays.fill(arr, INF);
			}
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b]=1;
			}
			
			//플로이드워셜
			//map을 INF로 초기화하고 거기에 연결 정보 넣고 그 후 건너 건너의 정보들의 값을 INF -> 다른 값으로 바꿔준다.
			// 그 후 마지막에 INF 아닌 기준 행과 기준 열의 개수 세면 끝이다. 자기 제외. INF가 아니라는건 관계가 있다는 거지
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <=N; i++) {
					if(i==k) continue;
					for (int j = 1; j <=N; j++) {
						if(i==j || j==k) continue;
						
						//우항 두 요소 중 하나라도 INF(연결안된상태)라면 갱신이 안되겟지. 즉 연결 안됐다는 소리다.
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = 1;
						}
					}
				}
			}
			
			//세주기
			int[] counts = new int[N+1];
			for (int i = 1; i <=N; i++) {
				for (int j = 1; j <= N; j++) {
					if(map[i][j]!=INF) {
						counts[i]++;
						counts[j]++;
					}
				}
			}
			
			result=0;
			for (int i = 1; i <= N; i++) {
				if(counts[i]==N-1)
					result++;
			}
			
			bw.write("#" + tc + " " + String.valueOf(result) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
	
	

}
