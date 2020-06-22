package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_��������_�߰��������̿� {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		// ��ǥ
		int N = Integer.parseInt(str[0]);
		// �ּ� ��
		int L = Integer.parseInt(str[1]);
		boolean chk = false;
		boolean minus = false;
		
		if (L < 2 || L > 100||N>1000000000||N<0)
			System.out.println(-1);
		else {
			while (true) {
				int sum = 0;
				if (L > 100) {
					minus=true;
					break;
				}
					

				// ¦��
				if (L % 2 == 0) {
					for (int i = (L / 2) - 1; i >= 0; i--) {
						if(N/L - i<0) {
							minus=true;
							break;
						}
						sum+= N/L - i;
					}
					if(minus==true)
						break;
					for (int i = 0; i <= (L / 2); i++) {
						sum+= N/L + i;
					}
					sum -= N/L;
					if(sum==N) {
						chk=true;
//						System.out.println("gg");
						for (int i = (L / 2) - 1; i >= 0; i--) {
							
							System.out.print(N/L-i + " ");
						}
						for (int i = 1; i <= (L / 2); i++) {
							System.out.print(N/L+i + " ");
						}
					}
				}
				// Ȧ��
				else {
					for (int i = (L / 2); i >=0; i--) {
						if(N/L - i<0) {
							minus=true;
							break;
						}
						sum+= N/L - i;
					}
					if(minus==true)
						break;
					for (int i = 0; i <= (L / 2); i++) {
						sum+= N/L + i;
					}
					sum -= N/L;
					if(sum==N) {
						chk=true;
//						System.out.println("gg");

						for (int i = (L / 2); i >=0; i--) {
							System.out.print(N/L-i + " ");
						}
//						System.out.println("cut");
						for (int i = 1; i <= (L / 2); i++) {
							System.out.print(N/L+i + " ");
						}
//						System.out.println("cut");

					}
				}
				if(chk==true)
					break;
				
				L++;
			}
			
			if(minus==true)
				System.out.println(-1);
		}

		br.close();
	}

}
