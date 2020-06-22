package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Solution_D2_1966_���ڸ���������_������ {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tn = Integer.parseInt(br.readLine());
		for (int i = 0; i < tn; i++) {
			int n = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");
			int[] arr = new int[n];
			
			for (int j = 0; j < n; j++) {
				arr[j]=Integer.parseInt(str[j]);
			}
			
			Arrays.sort(arr);
			bw.write("#"+(i+1)+" ");
			for (int j = 0; j < n; j++) {
				bw.write(arr[j]+" ");
				
			}
			bw.write("\n");

			
		}
		
			

			
		
		
		bw.flush();
		br.close();
		bw.close();
	}

}
