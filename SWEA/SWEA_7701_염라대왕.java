package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SWEA_7701_염라대왕 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int i = 0; i < tn; i++) {
			// ���� ����
			int n = Integer.parseInt(br.readLine());

			// set�� ����Ŭ���� ���
			// set�� �ߺ��� ������ ����. �׷��� �̷� ������ ����
			Set<String> set = new TreeSet<String>(new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					// ���̷� ��������
					int len = o1.length() - o2.length();
					// ���� ������ ������ �迭
					if (len == 0) {
						return o1.compareTo(o2);
					}
					return len;
				}
			});
			//���� �ϼ��� �¿� �� �־�!
			for (int j = 0; j < n; j++) {
				set.add(br.readLine());
			}
			bw.write("#"+(i+1)+"\n");
			
			for (String s : set) {
				bw.write(s+"\n");
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

}
