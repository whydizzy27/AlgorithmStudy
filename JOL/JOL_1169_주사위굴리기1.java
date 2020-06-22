package Jungol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class JO_1169_�ֻ���������1 {
	static int[] arr = new int[7];
	static int[] check = new int[7];

	static int n;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		arr[0]=1;
		
		if (m == 1)
			one(1,n);
		else if (m == 2)
			two(1,n);
		else
			three(1,n);

		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void printArr() {
		for (int j = 1; j <= n; j++)
			System.out.print(arr[j] + " ");
		System.out.println();
	}

	public static void one(int k, int n) {
		if(n==0) {
			printArr();
			return;
		}
		else {
			for(int i=1;i<=6;i++) {
				arr[k]=i;
				one(k+1,n-1);
			}
		}
		

	}

	public static void two(int k, int n) {

		if(n==0) {
			printArr();
			return;
		}
		else {
			for(int i=arr[k-1];i<=6;i++) {
				arr[k]=i;
				two(k+1,n-1);
			}
		}
	}

	public static void three(int k, int n) {
		if(n==0) {
			printArr();
			return;
		}
		else {
			for(int i=1;i<=6;i++) {
				if(check[i]==1)
					continue;
				arr[k]=i;
				check[i]=1;
				three(k+1,n-1);
				check[i]=0;
			}
		}
		
	}
}
