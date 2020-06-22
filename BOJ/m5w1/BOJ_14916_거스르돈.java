package m5w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14916_거스르돈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int money = Integer.parseInt(br.readLine());

		int mok = money / 5;
		int rem = money % 5;

		while (mok >= 0) {
			if (rem % 2 == 0) {
				mok += rem / 2;
				break;
			}
			rem += 5;
			mok--;

		}

		bw.write(String.valueOf(mok) + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

}
