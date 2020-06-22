package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1027_고층건물 {
	static int max;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 건물 개수
		int N = Integer.parseInt(br.readLine());

		String[] str = br.readLine().split(" ");

		// 1번부터 인덱스 시작
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(str[i - 1]);
		}
		for (int i = 1; i <= N; i++) {
			int cnt = 0;

			L: for (int j = 1; j <= N; j++) {
				// 사이 건물 높이 판단
				if (i == j)
					continue;

				else if (i > j) {
					for (int k = j + 1; k < i; k++) {
						if (arr[k] >= formula(j, arr[j], i, arr[i], k))
							continue L;
					}

				} else {

					for (int k = i + 1; k < j; k++) {
						if (arr[k] >= formula(i, arr[i], j, arr[j], k))
							continue L;
					}
				}
				cnt++;

			}
			if (cnt > max)
				max = cnt;
		}

		bw.write(String.valueOf(max));

		bw.flush();
		br.close();
		bw.close();
	}

	// 기울기 공식
	public static double formula(double x1, double y1, double x2, double y2, double between) {
		double g = (double) (y2 - y1) / (double) (x2 - x1);
		double dif = (x1 * g) - y1;
		double judge = between * g - dif;

		return judge;
	}

}
