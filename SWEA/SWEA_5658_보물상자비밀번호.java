package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA_5658_보물상자비밀번호 {
	static int N, K,no;
	static StringBuilder sb;
	static char[] change;
	static TreeSet<Integer> set;
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			change = br.readLine().toCharArray();
			
			no = N / 4;
			set = new TreeSet<Integer>();
			
			mysubstr();
			rotate(change);
			
			//회전
			for (int i = 0; i < N-1; i++) {
				mysubstr();
				rotate(change);
			}
			
			Object[] arr = set.toArray();
			bw.write("#" + tc + " " + String.valueOf(arr[arr.length-K]) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void rotate(char[] c) {
		
		char temp = c[c.length-1];
		for (int i = c.length-1; i >0; i--) {
			c[i] = c[i-1];
		}
		c[0] = temp;
		
	}
	
	private static void mysubstr() {
		sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			if(i%no==0 && i!=0) {
				set.add(Integer.parseInt(sb.toString(),16));
				sb = new StringBuilder();
			}
			sb.append(change[i]);
		}
		
	}

}
