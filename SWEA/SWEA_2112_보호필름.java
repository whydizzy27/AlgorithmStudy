package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2112_보호필름 {
	static int min, K, N, M, map[][], temp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			temp = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 초기 체크
			if (K==1 || isOk()) {
				bw.write("#" + tc + " " + 0 + "\n");
			} else {
				permutation(0, 0);
				if (min == Integer.MAX_VALUE)
					min = K;
				bw.write("#" + tc + " " + min + "\n");
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void permutation(int idx, int inject) {
		if (inject == K)
			return;
		if (min <= inject)
			return;
		if (idx == N) {
			if (!isOk())
				return;

			min = Math.min(min, inject);
			return;
		}

		// 0:변화없 1:A 2:B
		for (int i = 0; i < 3; i++) {
			temp[idx] = i;
			if (i == 0) {
				permutation(idx + 1, inject);
			} else {
				permutation(idx + 1, inject + 1);
			}
		}
	}

	private static boolean isOk() {

		int cur = 0;
		int next = 0;
		//열
		L: for (int j = 0; j < M; j++) {
			int count = 1;
			//행
			for (int i = 0; i < N - 1; i++) {
				cur = map[i][j];
				next = map[i + 1][j];

				if (temp[i] > 0) {
					cur = temp[i]-1;
				}
				if (temp[i + 1] > 0) {
					next = temp[i + 1]-1;
				}
				if (cur == next) {
					count++;
					if (count == K)
						continue L;
				} else {
					count = 1;
				}

			}
			return false;
		}

		return true;
	}
}
