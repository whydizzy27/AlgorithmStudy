package m2w4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import javax.sound.sampled.ReverbType;

public class BOJ_1759_암호만들기 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		LinkedList<String> list = new LinkedList<String>();

		String[] str = br.readLine().split(" ");
		int C = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);

		String[] str2 = br.readLine().split(" ");
		char[] arr = new char[N];
		int[] pick = new int[N];
		for (int i = N - 1; i >= N - C; i--) {
			pick[i] = 1;
		}

		for (int i = 0; i < N; i++) {
			arr[i] = str2[i].charAt(0);
		}

		Arrays.sort(arr);

		do {
			int mcnt = 0;
			String s = "";
			for (int i = 0; i < N; i++) {
				if (pick[i] == 1)
					s += arr[i];
			}

			for (int i = 0; i < C; i++) {
				if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o'
						|| s.charAt(i) == 'u')
					mcnt++;
			}
			if (mcnt >= 1 && mcnt <= C - 2)
				list.add(s);
		} while (np(pick));

		Collections.reverse(list);

		for (int i = 0; i < list.size(); i++) {
			bw.write(list.get(i) + "\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean np(int[] pick) {

		int i = N - 1;
		while (i > 0 && pick[i - 1] >= pick[i])
			--i;

		if (i == 0)
			return false;

		int j = N - 1;
		while (pick[i - 1] >= pick[j])
			--j;

		int temp = pick[i - 1];
		pick[i - 1] = pick[j];
		pick[j] = temp;

		j = N - 1;

		while (i < j) {
			temp = pick[i];
			pick[i] = pick[j];
			pick[j] = temp;

			++i;
			--j;
		}

		return true;
	}

}
