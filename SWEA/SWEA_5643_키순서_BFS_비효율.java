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

public class SWEA_5643_키순서_BFS_비효율 {
	static ArrayList<Integer>[] uplist, downlist;
	static int N, M, temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			// 정점 별 리스트관리
			// 자기보다 큰애들
			uplist = new ArrayList[N + 1];
			// 작은 애들
			downlist = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				uplist[i] = new ArrayList<Integer>();
				downlist[i] = new ArrayList<Integer>();
			}

			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				uplist[a].add(b);
				downlist[b].add(a);
			}
			boolean[] visited;
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				visited = new boolean[N + 1];
				Queue<Integer> q = new LinkedList<>();
				q.offer(i);
				int upcnt = 0;
				visited[i] = true;
				while (!q.isEmpty()) {
					int cur = q.poll();

					for (int j = 0; j < uplist[cur].size(); j++) {
						int next = uplist[cur].get(j);

						if (!visited[next]) {
							visited[next] = true;
							q.offer(next);
							upcnt++;
						}
					}
				}
				
				q.offer(i);
				visited = new boolean[N + 1];
				int downcnt = 0;
				while (!q.isEmpty()) {
					int cur = q.poll();

					for (int j = 0; j < downlist[cur].size(); j++) {
						int next = downlist[cur].get(j);

						if (!visited[next]) {
							visited[next] = true;
							q.offer(next);
							downcnt++;
						}
					}
				}

				if (upcnt + downcnt == N - 1) {
					cnt++;
				}
			}
			bw.write("#" + tc + " " + String.valueOf(cnt) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

}
