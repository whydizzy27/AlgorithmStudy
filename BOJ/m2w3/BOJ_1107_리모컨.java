package m2w3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_1107_리모컨 {
	static int[] arr;
	static boolean[] visited;
	static int len, difUp, difDown, nogada;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split("");
		len = str.length;
		arr = new int[len];

		for (int i = 0; i < len; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		visited = new boolean[10];

		int bn = Integer.parseInt(br.readLine());

		if (bn != 0) {

			String[] str2 = br.readLine().split(" ");
			for (int i = 0; i < bn; i++) {
				visited[Integer.parseInt(str2[i])] = true;
			}
		}

		int target = 0;
		for (int i = 0; i < len; i++) {
			target = target * 10 + arr[i];
		}

		nogada = Math.abs(target - 100);

		int a = up(target) + difUp;
		int b = down(target) + difDown;
		bw.write(String.valueOf(Math.min(nogada, Math.min(a, b))));

		bw.flush();
		br.close();
		bw.close();
	}

	public static int up(int target) {

		while (true) {
			if (difUp >= nogada)
				return 1000000;
			String s = String.valueOf(target);
			int cnt = 0;
			for (int i = 0; i < s.length(); i++) {
				if (!(visited[s.charAt(i) - '0'])) {
					cnt++;
				} else
					break;
			}
			if (cnt == s.length()) {
				return s.length();
			}
			difUp++;
			target++;
		}
	}

	public static int down(int target) {
		while (true) {
			if (target == -1 || difDown >= nogada) {
				return 1000000;
			}
			String s = String.valueOf(target);
			int cnt = 0;
			for (int i = 0; i < s.length(); i++) {
				if (!(visited[s.charAt(i) - '0'])) {
					cnt++;
				} else
					break;
			}
			if (cnt == s.length()) {
				return s.length();
			}
			difDown++;
			target--;
		}
	}
}
