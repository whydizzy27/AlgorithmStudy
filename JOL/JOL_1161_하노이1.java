package Jungol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class JO_1161_�ϳ���1 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		
		hanoi("1","2","3",n);
		
		bw.flush();
		br.close();
		bw.close();
	}
	public static void hanoi(String first,String center,String last,int n) {
		if(n==1)
			System.out.println(n+" : " +first+" -> "+ last);
		else {
			hanoi(first,last,center,n-1);
			System.out.println(n+" : " +first+" -> "+ last);
			hanoi(center,first,last,n-1);
			
		}
		
		
		
		
	}

}
