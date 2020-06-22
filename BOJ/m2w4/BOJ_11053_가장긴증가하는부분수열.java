package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_11053_가장긴증가하는부분수열 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int[] arr = new int[N];
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}

		dp[N - 1] = 1;
		for (int i = N - 2; i >= 0; i--) {
			int max = 0;
			for (int j = i + 1; j < N; j++) {
				if (arr[i] < arr[j]) {
					if (dp[j] > max)
						max = dp[j];
				}
			}
			if(max==0) dp[i]=1;
			else {
				dp[i] = max+1;
			}
			
		}
		Arrays.sort(dp);
		bw.write(String.valueOf(dp[N-1]));
		bw.flush();
		br.close();
		bw.close();
	}

}
