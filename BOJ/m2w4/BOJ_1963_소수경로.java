package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Sosu {
	int n;
	int cnt;

	public Sosu(int n, int cnt) {
		this.n = n;
		this.cnt = cnt;
	}
}

public class BOJ_1963_소수경로 {
	static int cnt, end;
	static boolean[] falseIsPrime, visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		falseIsPrime = new boolean[10000];
		falseIsPrime[0] = true;
		falseIsPrime[1] = true;
		for (int i = 2; i <= 5000; i++) {
			for (int j = 2; i * j <= 9999; j++) {
				falseIsPrime[i * j] = true;
			}
		}

		for (int tc = 0; tc < tn; tc++) {
			cnt = 0;
			String[] str = br.readLine().split(" ");
			int start = Integer.parseInt(str[0]);
			end = Integer.parseInt(str[1]);

			visited = new boolean[10000];

			bfs(new Sosu(start, 0));
			bw.write(String.valueOf(cnt)+"\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Sosu sosu) {
		Queue<Sosu> q = new LinkedList<>();
		q.offer(sosu);
		int temp = 0;
		visited[temp]=true;
		while (!q.isEmpty()) {
			Sosu so = q.poll();
			if(so.n == end) {
				cnt=so.cnt;
				return;
			}
				
			if (so.n >= 1000 && so.n<=9999) {

				for (int i = 0; i < 4; i++) {
					for (int j = 0; j <= 9; j++) {
						if (i == 0 && j == 0)
							continue;
						temp = change(so.n,i,(char)(j+'0'));
						if (!visited[temp] && !falseIsPrime[temp]) {
							visited[temp]=true;
							q.offer(new Sosu(temp, so.cnt+1));
						}
					}
				}

			}

		}

	}

	private static int change(int num,int i,char j) {
		StringBuilder sb = new StringBuilder(String.valueOf(num));
		sb.setCharAt(i, j);
		return Integer.parseInt(sb.toString());
	}

}
