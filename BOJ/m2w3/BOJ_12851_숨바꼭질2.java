package m2w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Dot {
	int x;
	int cost;

	public Dot(int x, int cost) {
		this.x = x;
		this.cost = cost;
	}
}

public class BOJ_12851_숨바꼭질2 {

	static int K, N, nogada, value, cnt;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
		nogada = Math.abs(N - K);
		visited = new int[200001];

		if (N > K) {
			cnt=1;
			value = nogada;
		}
		
		else
			bfs(new Dot(N, 0));

		bw.write(String.valueOf(Math.min(value, nogada)) + "\n");
		bw.write(String.valueOf(cnt));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Dot dd) {
		boolean isOk = false;
		Queue<Dot> dq = new LinkedList<>();
		dq.offer(dd);

		while (!dq.isEmpty()) {
			Dot d = dq.poll();

			if (d.x >= 0 && d.x <= 200000) {
				if (visited[d.x] == 0 || visited[d.x] >= d.cost) {

					visited[d.x] = d.cost;
					if (d.x == K && !isOk) {
						isOk = true;

						value = d.cost;
						cnt++;
					} else if (d.x == K && isOk && d.cost == value) {
						cnt++;
					}

					dq.offer(new Dot(d.x * 2, d.cost + 1));
					dq.offer(new Dot(d.x - 1, d.cost + 1));
					dq.offer(new Dot(d.x + 1, d.cost + 1));

				}
			}
		}

	}

}
