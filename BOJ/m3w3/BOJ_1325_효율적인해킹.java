package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325_효율적인해킹 {
	static ArrayList<Integer>[] listArr;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
	      
	      int N = Integer.parseInt(st.nextToken());
	      int M = Integer.parseInt(st.nextToken());
		int max = -1;
		listArr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<Integer>();
		}

		 for(int i = 0; i < M; i++) {
	         st = new StringTokenizer(br.readLine());
	         int start = Integer.parseInt(st.nextToken());
	         int end = Integer.parseInt(st.nextToken());
	         listArr[end].add(start);
		 }

		int[] ans = new int[N+1];
		// N만큼 반복하고
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N+1];

			int value = bfs(i);
			if (max < value) 
				max=value;
			ans[i]=value;
		}



		for (int i = 1; i <= N; i++) {
			if(ans[i]==max)
		
			bw.write(String.valueOf(i) + " ");
		}
		bw.flush();
		br.close();
		bw.close();
	}

	private static int bfs(int i) {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(i);
		int cnt=0;
		visited[i]=true;
		while(!q.isEmpty()) {
			int x = q.poll();
			cnt++;
			
			for (int y : listArr[x]) {
			
				if(visited[y]) continue;
				q.offer(y);
				visited[y]=true;
			}
			
		}
		return cnt;
	}	

}
