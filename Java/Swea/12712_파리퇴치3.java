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
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int N = Integer.parseInt(st.nextToken());
		    int M = Integer.parseInt(st.nextToken());
		    
		    int [][] array = new int[3*N][3*N];
		    for(int i = N; i < 2*N; i++){
		        st = new StringTokenizer(br.readLine());
		        for (int j = N; j < 2*N; j++){
		            array[i][j] = Integer.parseInt(st.nextToken());
		        }
		    }
		    int max = 0;
		    
		    for (int i = N; i < 2*N; i++){
		        for (int j = N; j< 2*N ; j++){
		            int sum = 0;
		            
		            //1. +형으로 분사
		            for (int k = -M+1; k < M; k++){
		                sum += array[i][j+k];
		                sum += array[i+k][j];
		            }
		            sum -= array[i][j];
		            max = Math.max(max,sum);
		            
		            sum = 0;
		            
		            //2. x형으로 분사
		            for(int h = -M+1; h < M; h++){
		                sum += array[i+h][j+h];
		                sum += array[i+h][j-h];
		                
		            }
		            sum -= array[i][j];
		            
		            max = Math.max(max,sum);
		        }
		    }
		    
		    System.out.println("#"+tc+" "+max);
		    
		}
	}
}

/*
## 아이디어

- 배열 인덱스 벗어나는 문제를 해결하기위해 처음부터 3N*3N 의 넉넉한 길이로 배열을 설정한다 (M이 최대 N이하므로 3N으로 설정)
- x형 분사시, 방향을 두개로 설정해야함
*/
