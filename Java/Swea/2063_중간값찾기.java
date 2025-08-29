/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.*;
import java.io.*;

public class Solution
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] array = new int[N];
		for(int i = 0; i < N; i++){
		    array[i] = Integer.parseInt(st.nextToken());
		}
        Arrays.sort(array);
		System.out.println(array[N/2]);
	}
}
