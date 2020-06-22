package Jungol;

import java.util.Arrays;
import java.util.Scanner;

public class JO_1002_�ִ������ּҰ���� {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int mul = 0;
		int gcd = arr[0];
		int lcm = arr[0];
		int tempgcd=0;

		for (int i = 0; i < n - 1; i++) {
			mul = lcm * arr[i + 1]; //��
			gcd = gcd(gcd, arr[i + 1]); //3�� �ִ�����
			tempgcd=gcd(lcm,arr[i+1]);//i i+1 �ִ�����. mul ������ ��
			lcm = mul / tempgcd; //�ּҰ����
			
		}
		System.out.println(gcd + " " + lcm);
//		System.out.println(gcd(3,16));
	}

	// �ִ�����
	public static int gcd(int a, int b) {
		int big = 0;
		int small = 0;
		if (a > b) {
			big = a;
			small = b;
		} else {
			big = b;
			small = a;
		}
		if (big % small == 0)
			return small;
		else
			return gcd(small, big % small);
	}

}
