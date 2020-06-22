package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;


public class BOJ_1074_Z {
	static int[] dx = { 0, 0, 1, 1 };
	static int[] dy = { 0, 1, 0, 1 };
	static int r, c, cnt,ans;
	static boolean isOver;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		r = Integer.parseInt(str[1]);
		c = Integer.parseInt(str[2]);

		int powN = (int) Math.pow(2, N);
		dfs(0, 0, powN);
		bw.write(String.valueOf(ans));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int i, int j, int powN) {
		if(isOver)
			return;
		
		if (powN == 0) {
			if(i==r&&j==c) {
				isOver=true;
				ans = cnt;
			}
			cnt++;
			return;
		}

		dfs(i, j, powN / 2);
		dfs(i, j + powN, powN / 2);
		dfs(i + powN, j, powN / 2);
		dfs(i + powN, j + powN, powN / 2);

	}

}
