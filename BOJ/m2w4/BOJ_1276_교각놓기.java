package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class Kyo implements Comparable<Kyo> {
	int sx;
	int ex;
	int h;

	public Kyo(int sx, int ex, int h) {
		this.sx = sx;
		this.ex = ex;
		this.h = h;
	}

	@Override
	public int compareTo(Kyo o) {
		return this.h - o.h;
	}

}

public class BOJ_1276_교각놓기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 교각 수
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		Kyo[] arr = new Kyo[N];
		int[] visited = new int[10001];

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			// y x1 x2. x2는 포함x
			arr[i] = new Kyo(Integer.parseInt(str[1]), Integer.parseInt(str[2]),Integer.parseInt(str[0]));
		}
		Arrays.sort(arr);

		for (int i = 0; i < N; i++) {

			for (int j = arr[i].sx; j < arr[i].ex; j++) {
				if (visited[j] < arr[i].h) {
					if (j == arr[i].sx || j == (arr[i].ex) - 1) {
						cnt += arr[i].h - visited[j];
					}
					// 높이 갱신
					visited[j] = arr[i].h;
				}

			}

		}

		bw.write(String.valueOf(cnt));
		bw.flush();
		br.close();
		bw.close();
	}

}
