package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2999_비밀이메일 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		int N = str.length();
		int R=0;
		int C=0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(i<=j&&i>R&&i*j==N) {
					R=i;
					C=j;
				}
			}
		}
		int k=0;
		char[][] arr = new char[R][C];
		for(int i=0;i<C;i++) {
			for(int j=0;j<R;j++) {
				arr[j][i]=str.charAt(k);
				k++;
			}
		}
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(arr[i][j]);
			}
		}
		
		br.close();
	}

}
