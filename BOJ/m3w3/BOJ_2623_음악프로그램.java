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

public class BOJ_2623_음악프로그램 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] list = new ArrayList[N + 1];
		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		int[] indegree = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] temp = new int[n];
			for (int j = 0; j < n; j++) {
				temp[j] = Integer.parseInt(st.nextToken());
			}

			for (int j = 0; j < n; j++) {
				for (int j2 = j + 1; j2 < n; j2++) {
					if (!list[temp[j]].contains(temp[j2])) {
						list[temp[j]].add(temp[j2]);
						indegree[temp[j2]]++;
					}
				}
			}

		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < N+1; i++) {
			if(indegree[i]==0)
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int current = q.poll();
			ans.add(current);
			
			for (int k : list[current]) {
				indegree[k]--;
				
				if(indegree[k]==0)
					q.offer(k);
			}
		}
		
		if(ans.size()!=N) {
			bw.write(String.valueOf(0));
		}
		else {
			for (int integer : ans) {
				bw.write(String.valueOf(integer)+"\n");
				
			}
		}
		bw.flush();
		br.close();
		bw.close();
	}

}
