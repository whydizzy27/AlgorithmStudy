package com.ssafy.comthink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_9659_다항식계산 {
	static final long MOD = 998244353;
	static long dp[];
	static int dahang[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			int N = Integer.parseInt(br.readLine());
			dahang = new int[N + 1][3];

			for (int i = 2; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				dahang[i][0] = Integer.parseInt(st.nextToken());
				dahang[i][1] = Integer.parseInt(st.nextToken());
				dahang[i][2] = Integer.parseInt(st.nextToken());
			}

			int M = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			bw.write("#" + tc + " ");

			for (int i = 0; i < M; i++) {
				dp = new long[N + 1];
				Arrays.fill(dp, -1);
				long k = Integer.parseInt(st.nextToken());
				dp[0] = 1;
				dp[1] = k%MOD;
				bw.write(String.valueOf(func(N)) + " ");

			}
			bw.write("\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static long func(int idx) {
		if (dp[idx] >= 0)
			return dp[idx];

		int mod = dahang[idx][0];
		int a = dahang[idx][1];
		int b = dahang[idx][2];

		if(mod==1) {
			return dp[idx]=(func(a)+func(b))%MOD;
		}
		else if(mod==2) {
			return dp[idx]=(a*func(b))%MOD;
		}
		else {
			return dp[idx]=(func(a)*func(b))%MOD;
		}
	}

}
