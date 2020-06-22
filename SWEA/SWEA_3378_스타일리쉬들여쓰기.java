package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class SWEA_3378_스타일리쉬들여쓰기 {
	static int[] temp, yourSentenceInfo[], yourTab;
	static int p;
	static LinkedList<int[]> list;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			list = new LinkedList<int[]>();
			temp = new int[3];
			String[] str = br.readLine().split(" ");
			p = Integer.parseInt(str[0]);// 멘토거
			int q = Integer.parseInt(str[1]);// 내거

			// 각 줄마다 소 중 대 괄호 개수 저장 배열
			yourSentenceInfo = new int[p + 1][6];
			int[][] mySentenceInfo = new int[q + 1][6];
			// 줄마다의 들여쓰기 개수 저장 배열
			yourTab = new int[p];
			int[] myTab = new int[q];
			// 문장 저장
			String[] yourSentence = new String[p];
			String[] mySentence = new String[q];

			// String값 저장
			for (int i = 0; i < p; i++) {
				yourSentence[i] = br.readLine();
			}
			for (int i = 0; i < q; i++) {
				mySentence[i] = br.readLine();
			}

			infoMake(yourSentenceInfo, yourSentence, p);
			infoMake(mySentenceInfo, mySentence, q);

			howManyTab(yourTab, yourSentence, p);

			permutation(0);
//			System.out.println(Arrays.toString(yourTab));
//			System.out.println(Arrays.toString(yourSentence));
//			System.out.println(Arrays.toString(mySentence));
//			for (int i = 0; i < p; i++) {
//				for (int j = 0; j < 6; j++) {
//					System.out.print(yourSentenceInfo[i][j]+" ");
//				}
//				System.out.println();
//			}
			int len = list.size();

			if (len == 1) {
				for (int i = 1; i < q; i++) {
					myTab[i] = calc(list.get(0), mySentenceInfo[i]);
				}
			} else {
				L: for (int i = 1; i < q; i++) {
					int k = 1;
					int tem = calc(list.get(0), mySentenceInfo[i]);
					;
					while (k != len) {
						if (tem != calc(list.get(k), mySentenceInfo[i])) {
							myTab[i] = -1;
							continue L;
						}
						k++;
					}
					myTab[i] = tem;
				}
			}

			bw.write("#" + tc + " ");
			for (int i = 0; i < q; i++) {
				bw.write(String.valueOf(myTab[i]) + " ");
			}
			bw.write("\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void permutation(int idx) {
		if (idx == 3) {
//			System.out.println("꺄악");
//			System.out.println("------------------------------");
//			System.out.println(Arrays.toString(temp));

			for (int j = 0; j < p; j++) {
//				System.out.println(j+"번째줄");
//				System.out.println(calc(temp, yourSentenceInfo[j]));
				if (calc(temp, yourSentenceInfo[j]) != yourTab[j])
					return;
			}

//			System.out.println("생존");
			int[] temppp = temp.clone();
			list.add(temppp);
			return;
		}

		L: for (int i = 1; i <= 20; i++) {
			temp[idx] = i;
			for (int j = 0; j < p; j++) {
				if (calc(temp, yourSentenceInfo[j]) > yourTab[j]) {
					temp[idx] = 0;
					continue L;
				}
			}
			permutation(idx + 1);
			temp[idx] = 0;
		}

	}

	private static void howManyTab(int[] tab, String[] obj, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < obj[i].length(); j++) {
				if (obj[i].charAt(j) == '.')
					tab[i]++;
				else
					break;
			}
		}
	}

	private static void infoMake(int[][] infoArr, String[] obj, int n) {

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < obj[i - 1].length(); j++) {
				char c = obj[i - 1].charAt(j);
				if (c == '(') {
					infoArr[i][0]++;
					for (int k = i + 1; k < n; k++) {
						infoArr[k][0]++;
					}
				} else if (c == ')') {
					infoArr[i][1]++;
					for (int k = i + 1; k < n; k++) {
						infoArr[k][1]++;
					}
				} else if (c == '{') {
					infoArr[i][2]++;
					for (int k = i + 1; k < n; k++) {
						infoArr[k][2]++;
					}
				} else if (c == '}') {
					infoArr[i][3]++;
					for (int k = i + 1; k < n; k++) {
						infoArr[k][3]++;
					}
				} else if (c == '[') {
					infoArr[i][4]++;
					for (int k = i + 1; k < n; k++) {
						infoArr[k][4]++;
					}
				} else if (c == ']') {
					infoArr[i][5]++;
					for (int k = i + 1; k < n; k++) {
						infoArr[k][5]++;
					}
				}
			}
		}
	}

	private static int calc(int[] arr, int[] info) {

		return arr[0] * (info[0] - info[1]) + arr[1] * (info[2] - info[3]) + arr[2] * (info[4] - info[5]);
	}
}
