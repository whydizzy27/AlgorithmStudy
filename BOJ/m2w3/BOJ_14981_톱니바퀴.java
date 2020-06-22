package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_14981_Åé´Ï¹ÙÄû {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] t1 = new int[8];
		int[] t2 = new int[8];
		int[] t3 = new int[8];
		int[] t4 = new int[8];

		String str = br.readLine();
		for (int j = 0; j < 8; j++) {
			t1[j] = (int) str.charAt(j) - 48;
//			System.out.println(t1[j]);
		}
		String str2 = br.readLine();
		for (int j = 0; j < 8; j++) {
			t2[j] = (int) str2.charAt(j) - 48;
		}
		String str3 = br.readLine();
		for (int j = 0; j < 8; j++) {
			t3[j] = (int) str3.charAt(j) - 48;
		}
		String str4 = br.readLine();
		for (int j = 0; j < 8; j++) {
			t4[j] = (int) str4.charAt(j) - 48;
		}
		int[] arr = new int[8];
//		int[] arr = {1,1,0,0,1,1,1,0};

		int n = Integer.parseInt(br.readLine());
		int[] turn = new int[2];
		for (int i = 0; i < n; i++) {
			String[] s = br.readLine().split(" ");
			turn[0] = Integer.parseInt(s[0]); // Åé´Ï ¹øÈ£
			turn[1] = Integer.parseInt(s[1]); // ¹æÇâ

//			if (turn[0] == 1)
//				arr = t1;
//			else if (turn[0] == 2)
//				arr = t2;
//			else if (turn[0] == 3)
//				arr = t3;
//			else
//				arr = t4;
//			System.out.println(turn[0]+" " + turn[1]);

//			turning(arr, turn[1]);
			
			interaction(turn[0], turn[1], t1, t2, t3, t4);

		}
//		for (int k : t1)
//			System.out.print(k);
//		System.out.println();
//		for (int k : t2)
//			System.out.print(k);
//		System.out.println();
//
//		for (int k : t3)
//			System.out.print(k);
//		System.out.println();
//
//		for (int k : t4)
//			System.out.print(k);
//		System.out.println();

		bw.write(String.valueOf(score(t1, t2, t3, t4)));

		bw.flush();
		br.close();
		bw.close();
	}

	public static void turning(int[] arr, int k) {
		int temp = 0;
		if (k == 1) {
			temp = arr[7];
			for (int i = 6; i >= 0; i--) {
				arr[i + 1] = arr[i];
			}
			arr[0] = temp;
		} else {
			temp = arr[0];
			for (int i = 0; i < 7; i++) {
				arr[i] = arr[i + 1];
			}
			arr[7] = temp;
		}
	}

	public static int score(int[] arr1, int[] arr2, int[] arr3, int[] arr4) {
		return arr1[0] * 1 + arr2[0] * 2 + arr3[0] * 4 + arr4[0] * 8;
	}

	public static void interaction(int idx, int dir, int[] arr1, int[] arr2, int[] arr3, int[] arr4) {
		// Åé´Ï1 È¸Àü
		if (idx == 1 && dir == 1) {

			if (arr1[2] != arr2[6]) {
				turning(arr1, 1);
				
				if (arr2[2] != arr3[6]) {
					turning(arr2, -1);
					if (arr3[2] != arr4[6]) {
						turning(arr3, 1);
						turning(arr4, -1);
					}
					else
						turning(arr3, 1);

				}
				else
					turning(arr2, -1);

			}
			else
				turning(arr1, 1);


		} else if (idx == 1 && dir == -1) {

			if (arr1[2] != arr2[6]) {
				turning(arr1, -1);
				
				if (arr2[2] != arr3[6]) {
					turning(arr2, 1);
					if (arr3[2] != arr4[6]) {
						turning(arr3, -1);
						turning(arr4, 1);
					}
					else
						turning(arr3, -1);

				}
				else
					turning(arr2, 1);

			}
			else
				turning(arr1, -1);


		}
		// Åé´Ï2 È¸Àü
		else if (idx == 2 && dir == 1) {

			if (arr1[2] != arr2[6]) {
				turning(arr2, 1);
				turning(arr1, -1);
			}else {
				turning(arr2, 1);
			}
			turning(arr2, -1);

			if (arr2[2] != arr3[6]) {
				turning(arr2, 1);
				if (arr3[2] != arr4[6]) {
					turning(arr3, -1);
					turning(arr4, 1);
				}else {
					turning(arr3, -1);
				}
			}else {
				turning(arr2, 1);
			}

		} else if (idx == 2 && dir == -1) {

			if (arr1[2] != arr2[6]) {
				turning(arr2, -1);
				turning(arr1, 1);
			}else {
				turning(arr2, -1);
			}
			turning(arr2, 1);

			if (arr2[2] != arr3[6]) {
				turning(arr2, -1);
				if (arr3[2] != arr4[6]) {
					turning(arr3, 1);
					turning(arr4, -1);
				}else {
					turning(arr3, 1);
				}
			}else {
				turning(arr2, -1);
			}

		}
		// Åé´Ï3 È¸Àü
		else if (idx == 3 && dir == 1) {

			if (arr3[2] != arr4[6]) {
				turning(arr3, 1);
				turning(arr4, -1);

			}else {
				turning(arr3, 1);

			}
			turning(arr3, -1);

			if (arr2[2] != arr3[6]) {
				turning(arr3, 1);
				if (arr1[2] != arr2[6]) {
					turning(arr2, -1);
					turning(arr1, 1);
				}else {
					turning(arr2, -1);
				}
					
			}else {
				turning(arr3, 1);
			}

		} else if (idx == 3 && dir == -1) {

			if (arr3[2] != arr4[6]) {
				turning(arr3, -1);
				turning(arr4, 1);

			}else {
				turning(arr3, -1);

			}
			turning(arr3, 1);

			if (arr2[2] != arr3[6]) {
				turning(arr3, -1);
				if (arr1[2] != arr2[6]) {
					turning(arr2, 1);
					turning(arr1, -1);
				}else {
					turning(arr2, 1);
				}
					
			}else {
				turning(arr3, -1);
			}

		}
		// Åé´Ï4 È¸Àü
		else if (idx == 4 && dir == 1) {

			if (arr4[6] != arr3[2]) {
				turning(arr4, 1);

				if (arr3[6] != arr2[2]) {
					turning(arr3, -1);

					
					if (arr2[6] != arr1[2]) {
						turning(arr2, 1);
						turning(arr1, -1);
					}
					else {
						turning(arr2, 1);

					}
				}else {
					turning(arr3, -1);

				}

			}else {
				turning(arr4, 1);

			}

		} else  {

			if (arr4[6] != arr3[2]) {
				turning(arr4, -1);

				if (arr3[6] != arr2[2]) {
					turning(arr3, 1);

					
					if (arr2[6] != arr1[2]) {
						turning(arr2, -1);
						turning(arr1, 1);
					}
					else {
						turning(arr2, -1);

					}
				}else {
					turning(arr3, 1);

				}

			}else {
				turning(arr4, -1);

			}

		}
	}

}
