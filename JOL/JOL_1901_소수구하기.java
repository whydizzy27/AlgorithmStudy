package Jungol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class JO_1901_�Ҽ����ϱ� {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//		int n = sc.nextInt();
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
//			pn = sc.nextInt();
			int pn = Integer.parseInt(br.readLine());
//			System.out.println();
			int min = 1000000;
			int temp = 0;
			int cnt = 0;
			int[] minIndex = new int[2];

			for (int j = 2; j < 1000001; j++) {
				for (int k = 1; k < j; k++) {
					if (j % k == 0) {
//						System.out.println(j+"%" +k);
						cnt++;
//						System.out.println(cnt);
					}

				}
				int x = 0;
				if (cnt == 1) {
//					System.out.println(j);
					if (pn >= j)
						temp = pn - j;
					else
						temp = j - pn;
//					System.out.println(temp);
					if (temp < min) {
						minIndex[0] = 0;
						minIndex[1] = 0;

						min = temp;
						minIndex[0] = j;
					} else if (temp == min) {
//						++x;
						minIndex[1] = j;
					}
				}
				cnt = 0;

			}
			if (minIndex[1] != 0) {
				

					bw.write(String.valueOf(minIndex[0]) + " " + String.valueOf(minIndex[1])+ "\n");
				
			} else

				bw.write(String.valueOf(minIndex[0]) + " " + "\n");

		}
		bw.flush();
		br.close();
		bw.close();
	}

}
