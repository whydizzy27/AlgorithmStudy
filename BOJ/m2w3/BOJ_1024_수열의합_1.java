package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_��������_�����ص��ð�Ǯ {
	static int object, min, j;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		// ��ǥ
		object = Integer.parseInt(str[0]);
		// �ּ� ��
		min = Integer.parseInt(str[1]);
		int sum = 0;

		boolean check = false;
		int i = 0;
		boolean done = false;
		if (min < 2 || min > 100)
			System.out.println(-1);
		else {
			while (min <= 100) {
				
				sum = 0;
				i = 0;
				for (int k = 0; k < min; k++)
					sum += k;

				while (true) {
					if(sum>object) break;
					else if(sum==object) {
						for(int u=0;u<min;u++)
							System.out.print((i + u)+" ");
						check=true;
					}
					i++;
					sum+=min;
				}
				if(check==true)
					break;
				min++;
			}
			if(min==101)
				done=true;
			
		}
		
		if(done==true)
			System.out.println(-1);
		bw.flush();
		br.close();
		bw.close();
	}

}
