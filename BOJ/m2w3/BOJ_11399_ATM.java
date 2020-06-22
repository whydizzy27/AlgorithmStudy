package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_11399_ATM {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		int sum = 0;
		int min = 0;
		for (int i = 0; i < n; i++) {
			sum+=arr[i];
			min+=sum;
		}
		
		bw.write(String.valueOf(min));

		bw.flush();
		br.close();
		bw.close();
	}

}
