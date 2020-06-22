package m3w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
	int from, cnt, target;

	public Pair(int from, int cnt, int target) {
		this.from = from;
		this.cnt = cnt;
		this.target = target;
	}
}

public class BOJ_2660_회장뽑기 {
	static int N, value;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		int[] Info = new int[N + 1];
		while (true) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);

			if (a == -1 && b == -1)
				break;

			map[a][b] = 1;
			map[b][a] = 1;
		}
		// 입력끝

		// 자기자신 2
		for (int i = 1; i <= N; i++) {
			map[i][i] = 2;
		}

		// bfs
		for (int i = 1; i <= N; i++) {
			int max = 0;

			for (int j = 1; j <= N; j++) {
				value = 0;

				if (map[i][j] == 1) {
					if (max < 1)
						max = 1;
				} else if (map[i][j] == 0) {

					bfs(new Pair(i, 0, j));
					if (value > max)
						max = value;
				}
			}
			Info[i] = max;
		}

		
//		System.out.println(Arrays.toString(Info));
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < N+1; i++) {
			if(min>Info[i])
				min=Info[i];
		}
		LinkedList<Integer> list = new LinkedList<Integer>();
		int minNum = 0;
		for (int i = 1; i < N+1; i++) {
			if(min==Info[i]) {
				minNum++;
				list.add(i);
			}
		}
		
		bw.write(String.valueOf(min) + " " + String.valueOf(minNum) + "\n");
		Collections.sort(list);
		for (int k : list) {
			bw.write(String.valueOf(k) + " ");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Pair pp) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(pp);
		boolean[] visited = new boolean[N + 1];
		visited[pp.from]=true;
		while (!q.isEmpty()) {
			Pair p = q.poll();

			if (p.from == p.target) {
				value = p.cnt;
				return;
			}

			int from = p.from;
			int target = p.target;

			for (int j = 1; j <= N; j++) {
				if (map[from][j] == 1&&!visited[j]) {
					visited[j]=true;
					q.offer(new Pair(j, p.cnt+1, target));
				}
			}

		}
	}
}
