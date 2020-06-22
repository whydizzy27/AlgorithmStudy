package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// DFS든 BFS든 중복호출이 너무 많다. 1-2-3-4 로 치면 모든 정점 완탐 시 1번경우에서 2 3 4 구했는데 2번경우에서 3 4 또 구해야하므로 시간이 너무 오래걸림
// 고로 DFS에 메모이제이션을 접목한 방법을 사용. 태희쌤코드에 있으니 보구 이건 실패작.
public class SWEA_5643_키순서_DFS_비효율 {
	static int N, M, map[][],result,cnt;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			map = new int[N+1][N+1];
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b]=1;
			}
			
			result=0;
			
			//모든 정점에서 나보다 큰사람수 세고 나보다 작은 사람 세고
			//그 합이 N-1이면 알 수 있다. result++;
			for (int i = 1; i <= N; i++) {
				cnt=0;
				//나보다 큰사람 세기
				visited = new boolean[N+1];
				dfs(i);
				//나보다 작은사람 세기
				visited = new boolean[N+1];
				dfs1(i);
				if(cnt == N-1) {
					result++;
				}
			}
			bw.write("#" + tc + " " + String.valueOf(result) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
	
	//dfs가 진입차수, dfs1이 진출차수. 각각 큰거 / 작은거
	private static void dfs(int idx) {
		visited[idx]=true;
		
		for (int i = 1; i <= N; i++) {
			if(visited[i]) continue;
			if(map[idx][i]==0) continue;
			cnt++;
			dfs(i);
		}
		
		
	}
	
	private static void dfs1(int idx) {
		visited[idx]=true;
		
		for (int i = 1; i <= N; i++) {
			if(visited[i]) continue;
			//내가 가리킴 받는게 곧 왼쪽 놈이 나보다 작다는 거니까
			if(map[i][idx]==0) continue;
			cnt++;
			dfs1(i);
		}
		
		
	}

}
