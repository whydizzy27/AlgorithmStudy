package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_4050_재관이의대량할인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {

			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			int sale=0;
			int cnt=0;
			for (int i = arr.length-1; i >= 0; i--) {
				cnt++;
				if(cnt % 3 !=0) 
					sale+=arr[i];
			}
			
			bw.write("#" + tc + " " + String.valueOf(sale) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
}
