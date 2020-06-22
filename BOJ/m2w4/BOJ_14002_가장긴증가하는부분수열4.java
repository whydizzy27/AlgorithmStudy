package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

class Halo implements Comparable<Halo>{
	int len;
	LinkedList<Integer> list;
	public Halo(int len, LinkedList<Integer> list) {
		this.len = len;
		this.list = list;
	}
	@Override
	public int compareTo(Halo o) {
		return this.len-o.len;
	}
}
public class BOJ_14002_가장긴증가하는부분수열4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int[] arr = new int[N];
		Halo[] dp = new Halo[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}

		dp[N - 1] = new Halo(1, new LinkedList<Integer>());
		dp[N - 1].list.add(arr[N-1]);
		for (int i = N - 2; i >= 0; i--) {
			Halo max = new Halo(0, new LinkedList<Integer>());
			for (int j = i + 1; j < N; j++) {
				if (arr[i] < arr[j]) {
					if (dp[j].len > max.len)
						max = dp[j];
				}
			}
			
			dp[i]=new Halo(0, new LinkedList<Integer>());
			if(max.len==0) {
				dp[i].len=1;
				dp[i].list.add(arr[i]);
			}
			else {
				dp[i].len = max.len+1;
				for (int h : max.list) {
					dp[i].list.add(h);
				}
				dp[i].list.add(arr[i]);
			}
			
		}
		Arrays.sort(dp);
		bw.write(String.valueOf(dp[N-1].len)+"\n");
		Collections.reverse(dp[N-1].list);
		for (int h : dp[N-1].list) {
			bw.write(h+" ");
			
		}
		bw.flush();
		br.close();
		bw.close();
	}

}
