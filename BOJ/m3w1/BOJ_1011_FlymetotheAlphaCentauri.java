package m3w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1011_FlymetotheAlphaCentauri {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int i = 0; i < tn; i++) {
			String[] str = br.readLine().split(" ");
			long x = Integer.parseInt(str[0]);
			long y = Integer.parseInt(str[1]);
			long dif = y - x;

			long n = 1;
			long cnt = 0;
			while (true) {
				long ndouble = n * n;
				long nextdouble = (n + 1) * (n + 1);
				if (ndouble <= dif && dif < nextdouble) {
					if (dif == ndouble) {
						cnt = 2 * n - 1;
						break;
					} else if ((ndouble + nextdouble) / 2 >= dif) {
						cnt = 2 * n;
						break;
					} else {
						cnt = 2 * (n + 1) - 1;
						break;
					}
				}
				n++;
			}

			bw.write(String.valueOf(cnt) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

}
