package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_14501_퇴사 {
	static int max,N;
	static int[] during,pay;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		during = new int[N + 1];
		pay = new int[N + 1];

		for (int i = 1; i <= N; i++) {

			String[] str = br.readLine().split(" ");
			during[i] = Integer.parseInt(str[0]);
			pay[i] = Integer.parseInt(str[1]);

		}

		
		dfs(0,1);
		
		bw.write(String.valueOf(max));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int earn, int day) {
		if(day>N) {
			
			if(max<earn)
				max=earn;
			return;
		}
		
		dfs(earn,day+1);
		if(day+during[day]-1<=N)
			dfs(earn+pay[day],day+during[day]);
		
	}

}
