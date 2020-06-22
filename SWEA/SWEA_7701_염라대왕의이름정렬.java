package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.TreeSet;

public class SWEA_7701_염라대왕의이름정렬 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= tn; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			TreeSet<String> set = new TreeSet<String>(new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					if(o1.length()==o2.length()) 
						return o1.compareTo(o2);
					
					return o1.length()-o2.length();
				}
			});
			for (int i = 0; i < N; i++) {
				set.add(br.readLine());
			}
			
			bw.write("#" + tc+"\n");
			for (String s : set) {
				bw.write(s+"\n");
			}
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}
