package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1010_다리놓기 {
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			String[] str = br.readLine().split(" ");

			int N = Integer.parseInt(str[0]);
			int M = Integer.parseInt(str[1]);
			dp = new int[N+1][M + 1];

			bw.write(String.valueOf( func(N, M)) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	public static int func(int n,int m) {
		if(n==1) return m;
		if (n == m)
			return 1;
		if (dp[n][m] > 0)
			return dp[n][m];

		dp[n][m] = func(n-1,m-1) + func(n,m-1);
		return dp[n][m];

	}

}
