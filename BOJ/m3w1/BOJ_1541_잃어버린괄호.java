package m3w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1541_잃어버린괄호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split("-", 2);

		String[] plus = str[0].split("\\+");
		int sum = 0;

		for (int i = 0; i < plus.length; i++) {
			sum += Integer.parseInt(plus[i]);
		}

		if (str.length !=1) {
			String[] minus = str[1].split("\\+|\\-");
			for (int i = 0; i < minus.length; i++) {
				sum -= Integer.parseInt(minus[i]);
			}
		}
		bw.write(String.valueOf(sum));
		bw.flush();
		br.close();
		bw.close();
	}

}
