package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class SB {
	int spot, cnt;
	LinkedList<Integer> list;

	public SB(int spot, int cnt, LinkedList<Integer> list) {
		this.spot = spot;
		this.cnt = cnt;
		this.list = list;
	}

}

public class BOJ_13913_숨바꼭질4 {
	static int min = Integer.MAX_VALUE, N, K;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		if (N == K) {
			System.out.println(0);
			System.out.println(N);
		} else if (N > K) {
			System.out.println(N - K);
			for (int i = N; i >= K; i--) {
				System.out.print(i + " ");
			}
		} else {
			bfs(new SB(N, 0, new LinkedList<Integer>()));
		}
		br.close();
	}

	private static void bfs(SB sb) {
		Queue<SB> q = new LinkedList<>();
		q.offer(sb);
		visited[sb.spot] = true;
		while (!q.isEmpty()) {
			SB s = q.poll();
			// 0
			// 100000
			// 답도출
			int x = s.spot;
			LinkedList<Integer> y = s.list;
			int z = s.cnt;
			if (x == K) {
				System.out.println(z);
				System.out.print(N + " ");
				for (int b : y) {
					System.out.print(b + " ");
				}
				return;
			}
			LinkedList<Integer> temp;
			if (x + 1 < 100001 && x + 1 >= 0 && !visited[x + 1]) {
				visited[x + 1] = true;
				temp = (LinkedList<Integer>) y.clone();
				temp.add(x + 1);
				q.offer(new SB(x + 1, z + 1, temp));
			}
			if (x - 1 < 100001 && x - 1 >= 0 && !visited[x - 1]) {
				visited[x - 1] = true;
				temp = (LinkedList<Integer>) y.clone();
				temp.add(x - 1);
				q.offer(new SB(x - 1, z + 1, temp));
			}
			if (x * 2 < 100001 && x * 2 >= 0 && !visited[x * 2]) {
				visited[x * 2] = true;
				temp = (LinkedList<Integer>) y.clone();
				temp.add(x * 2);
				q.offer(new SB(x * 2, z + 1, temp));
			}

		}
	}

}
