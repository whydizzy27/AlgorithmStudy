package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//SWEA 준환이의 양팔저울
public class SWEA_3234_준환이의양팔저울 {
	static int cnt, choo[], N, temp[];
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			N = Integer.parseInt(br.readLine());
			cnt = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			choo = new int[N];
			for (int i = 0; i < N; i++) {
				choo[i] = Integer.parseInt(st.nextToken());
			}
			temp = new int[N];
			visited = new boolean[N];

			permutation(0);
			bw.write("#" + tc + " " + cnt + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void permutation(int idx) {
		if (idx == N) {
			dfs(0, 0, 0);

			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			temp[idx] = choo[i];
			permutation(idx + 1);
			visited[i] = false;
		}
	}

	private static void dfs(int idx, int left, int right) {
		if (idx == N) {
			cnt++;
			return;
		}
		int cost = temp[idx];
		dfs(idx + 1, left + cost, right);
		if (left >= right + cost)
			dfs(idx + 1, left, right + cost);
	}
}
