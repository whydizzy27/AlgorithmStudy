package Jungol;

import java.util.Scanner;

public class JOL_1291_구구단 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int s = sc.nextInt();
		int e = sc.nextInt();
		while ((s < 2 || e > 9) || (e < 2 || s > 9)) {

			System.out.println("INPUT ERROR!");
			s = sc.nextInt();
			e = sc.nextInt();
		}

		for (int i = 1; i < 10; i++) {
			if (s > e) {
				for (int j = s; j >= e; j--) {
					System.out.printf("%d * %d = %2d   ", j, i, j * i);
				}
				System.out.println("");
			} else {

				for (int j = s; j <= e; j++) {
					System.out.printf("%d * %d = %2d   ", j, i, j * i);
				}
				System.out.println("");
			}

		}


	}

}
