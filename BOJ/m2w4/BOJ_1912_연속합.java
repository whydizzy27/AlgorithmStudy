package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_1912_연속합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		String[] str = br.readLine().split(" ");
		int[] arr = new int[N];
		int[] dp = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}

		dp[N - 1] = arr[N - 1];
		
		for (int i = N - 2; i >= 0; i--) {
			dp[i]=Math.max(dp[i+1]+arr[i],arr[i] );
		}
		Arrays.sort(dp);
		
		
		bw.write(String.valueOf(dp[N-1]));
		bw.flush();
		br.close();
		bw.close();
	}

}
