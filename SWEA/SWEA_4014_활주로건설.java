package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설 {
	static int map[][], N, X;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 0;
			// 행
			L: for (int i = 0; i < N; i++) {
				for (int j = 1; j < N - 1; j++) {
					if (!check1(i, j))
						continue L;
				}
				cnt++;
			}

			// 열
			L: for (int j = 0; j < N; j++) {
				for (int i = 1; i < N-1; i++) {
					if (!check2(i, j))
						continue L;
				}
				cnt++;
			}

			bw.write("#" + tc + " " + cnt + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean check2(int i, int j) {
		int cur = map[i][j];
		int left = map[i-1][j];
		int right = map[i+1][j];
		// 좌측
		if (left < cur) {
			if (i - X < 0)
				return false;
			for (int k = 2; k <= X; k++) {
				if (map[i-k][j] != left)
					return false;
			}
		}

		// 우측
		if (right < cur) {
			if (i + X >= N)
				return false;
			for (int k = 2; k <= X; k++) {
				if (map[i+k][j] != right)
					return false;
			}
		}

		return true;

	}

	private static boolean check1(int i, int j) {
		int cur = map[i][j];
		int left = map[i][j - 1];
		int right = map[i][j + 1];
		// 좌측
		if (left < cur) {
			if (j - X < 0)
				return false;
			for (int k = 2; k <= X; k++) {
				if (map[i][j - k] != left)
					return false;
			}
		}

		// 우측
		if (right < cur) {
			if (j + X >= N)
				return false;
			for (int k = 2; k <= X; k++) {
				if (map[i][j + k] != right)
					return false;
			}
		}

		return true;

	}

}
