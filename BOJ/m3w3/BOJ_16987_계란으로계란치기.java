package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_16987_계란으로계란치기 {
	static int N, max;
	static int[] arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0);
		bw.write(String.valueOf(max) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int idx, int cnt) {
		if (idx == N) {
			if (max < cnt)
				max = cnt;
			return;
		}
		if (arr[idx][0] <= 0)
			dfs(idx + 1, cnt);
		else {
			boolean check = false;
			for (int i = 0; i < N; i++) {
				if (i == idx)
					continue;
				if (arr[i][0] <= 0)
					continue;
				check = true;
				arr[idx][0] -= arr[i][1];
				arr[i][0] -= arr[idx][1];
				if (arr[idx][0] <= 0 && arr[i][0] <= 0) {
					dfs(idx + 1, cnt + 2);
				} else if (arr[idx][0] <= 0) {
					dfs(idx + 1, cnt + 1);
				} else if (arr[i][0] <= 0) {
					dfs(idx + 1, cnt + 1);
				} else if (arr[idx][0] > 0 && arr[i][0] >= 0) {
					dfs(idx + 1, cnt);
				}

				arr[idx][0] += arr[i][1];
				arr[i][0] += arr[idx][1];
			}
			if (!check)
				dfs(idx + 1, cnt);
		}
	}

}
