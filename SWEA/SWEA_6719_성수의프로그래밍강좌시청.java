package com.ssafy.comthink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_6719_성수의프로그래밍강좌시청 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= tn; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			double max=0;
			Arrays.sort(arr);
			
			for (int i = N-K; i <= N-1; i++) {
				max = (max+arr[i])/2;
			}
			
			bw.write("#" + tc + " " + String.valueOf(max) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

}
