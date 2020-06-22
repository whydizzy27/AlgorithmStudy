package m2w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

//class Dot {
//	int x;
//	int cost;
//
//	public Dot(int x, int cost) {
//		this.x = x;
//		this.cost = cost;
//	}
//}

public class BOJ_13549_숨바꼭질3 {

	static int K, N, nogada, value;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
		nogada = Math.abs(N - K);
		visited = new boolean[200001];

		if (N > K)
			value = nogada;
		else
			bfs(new Dot(N, 0));

		bw.write(String.valueOf(Math.min(value, nogada)));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Dot dd) {

		Deque<Dot> dq = new LinkedList<>();
		dq.offer(dd);

		while (!dq.isEmpty()) {
			Dot d = dq.poll();
			if (d.x >= 0 && d.x <= 200000) {
				if (!visited[d.x]) {

					visited[d.x] = true;
					if (d.x == K) {
						value = d.cost;
						return;
					}
					if (d.x < K)
						dq.offerFirst(new Dot(d.x * 2, d.cost));
					dq.offerLast(new Dot(d.x - 1, d.cost + 1));
					dq.offerLast(new Dot(d.x + 1, d.cost + 1));

				}
			}
		}

	}

}
