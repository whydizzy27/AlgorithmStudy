package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_�������Ǻ��Ǵ�÷ {
	static int[] arr,arr2;
	static boolean[] visited;
	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tn; t++) {
			String[] str = br.readLine().split(" ");

			int N = Integer.parseInt(str[0]);
			int M = Integer.parseInt(str[1]);
			String[][] ls = new String[N][2];
			for (int i = 0; i < N; i++) {
				String[] str2 = br.readLine().split(" ");
				ls[i][0] = str2[0];
				ls[i][1] = str2[1];
			}
			String[] my = new String[M];
			for (int i = 0; i < M; i++) {
				my[i] = br.readLine();
			}
			int total = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					
					for (int k = 0; k < 8; k++) {
						if (my[i].charAt(k) == ls[j][0].charAt(k) || ls[j][0].charAt(k) == '*') {
							if(k==7) {
								total += Integer.parseInt(ls[j][1]);
							}
						} else {
							break;
						}
					}
				}
				
			}
			bw.write("#" + t + " " + String.valueOf(total)+"\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
}
