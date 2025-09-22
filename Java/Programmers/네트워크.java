import java.util.*;
import java.io.*;

class Solution {
    static boolean[] visited;
    static int n;
    public static void dfs(int start,int[][] computers){
        visited[start] = true;
        for(int i = 0 ; i < n; i++){
            if(i == start) continue;
            if(computers[start][i] == 1 && !visited[i]){
                dfs(i,computers);
            }
        }
    }
    public int solution(int n, int[][] computers) {
        this.n = n; //전역변수n에 값을 넣어줘야함
        int answer = 0;
        visited = new boolean[n];
        
        for(int i = 0 ; i < n ; i++){
            if(!visited[i]){
                dfs(i,computers);
                answer++;
            }
        }
        return answer;
    }
}
