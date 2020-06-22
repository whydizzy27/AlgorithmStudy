package com.ssafy.comthink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5607_조합 {
	static final int p = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			long[] fact = new long[N + 1];
			fact[0] = 1;
			for (int i = 1; i <= N; i++) {
				fact[i] = (fact[i - 1] * i ) % p;
			}
			
			long a = fact[N];
			long b = func(fact[N-R]*fact[R],p-2) % p;

			long ans = a*b;
			bw.write("#" + tc + " " + String.valueOf(ans%p) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
	
	private static long func(long n, int i) {
		if(i==0) return 1;
		long temp = func(n, i/2);
		long val = (temp * temp) % p;
		if( i % 2 == 0) return val;
		else return (val * n) % p;
	}

}
