package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_5604_구간합 {
	static long[] arr;
	static long sum;
	static int len;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			String[] str = br.readLine().split(" ");

			String A = str[0];
			String B = str[1];

			arr = new long[16];

			for (int i = 0; i < 16; i++) {
				arr[i] = 45 * (long) Math.pow(10, i) * (i + 1);
			}

			sum = 0;
			len = A.length();
			dfs(A, 0);
			//얘는 포함되면 안되므로. A~B가 A B포함이라 빼줄 0~A범위는 A를 포함하면 안됨
			long resultA = sum;
			sum = 0;
			len = B.length();
			dfs(B, 0);
			for (int i = 0; i < len; i++) {
				sum += B.charAt(i) - '0';
			}
			long resultB = sum;

			bw.write("#" + tc + " " + String.valueOf(resultB - resultA) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(String s, int k) {
		if (k == len)
			return;

		int value = s.charAt(k) - '0';
		long cnt = 0;
		for (int i = 0; i < value; i++) {
			if (k == len - 1) {
				sum += i;
			} else {
				sum += i * Math.pow(10, len - 1 - k) + arr[len - k - 2];
			}
			cnt++;
		}
		cnt *= Math.pow(10, len - 1 - k);

		for (int i = 0; i < k; i++) {
			sum += cnt * (s.charAt(i) - '0');
		}

		dfs(s, k + 1);

	}

}
