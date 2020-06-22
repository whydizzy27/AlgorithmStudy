package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1182_부분수열의합 {
	static int sum;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");

		int n = Integer.parseInt(str[0]);
		sum = Integer.parseInt(str[1]);
		String[] str2 = br.readLine().split(" ");
		int[] arr = new int[n];
		int i = 0;
		for (String k : str2) {
			arr[i] = Integer.parseInt(k);
			i++;
//			System.out.println(arr[i]);
		}
		
		int[] check = new int[n];// 방문 검사용 배열

		powerSet(arr, check, 0);
		if(sum==0)
			count--;
		bw.write(String.valueOf(count));

		bw.flush();
		br.close();
		bw.close();
	}

	public static void powerSet(int[] arr, int[] check, int depth) {
		//arr length까지니까 모든 원소에 대한 부분 집합 출력. 
		//nCr은 에버노트에 있음. 재귀에 for문 넣는 거지
		
		if (depth==arr.length) {
			checksum(arr, check);
			return;
		}
		// 원소를 포함시키는 경우
		check[depth] = 1;
		powerSet(arr, check, depth + 1);

		// 원소를 포함시키지 않는 경우
		check[depth] = 0;
		powerSet(arr, check, depth + 1);
		return;
	}

	public static void checksum(int[] arr, int[] check) {
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			if(check[i]==1) {
				total += arr[i];
//				System.out.println(arr[i]);
			}
				
		}
//		System.out.println(total);
		if (total == sum)
			count++;
	}

}
