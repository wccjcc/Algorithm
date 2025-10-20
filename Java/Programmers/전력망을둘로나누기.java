
import java.util.*;
import java.io.*;

class Solution {
    static int songjun;
    static boolean[] visited;
    public static List<Integer>[] friends;

    //dfs 메서드
    public static void dfs(int start){
        visited[start] = true;
        songjun++;
        for(int s : friends[start]){
            if (!visited[s]){
                dfs(s);
            }
        }
        return;
    }
    public int solution(int n, int[][] wires) {
        int answer = 1000;
        for(int i = 0 ; i < wires.length; i++){
            friends = new ArrayList[n+1]; //송전탑 관계 리스트 초기화
            for(int k = 0 ; k < n+1; k++){
                friends[k] = new ArrayList<Integer>();
            }
            //관계 설정하기
            for(int j = 0 ; j < wires.length; j++){
                if(j == i) continue;
                friends[wires[j][0]].add(wires[j][1]);
                friends[wires[j][1]].add(wires[j][0]);
            }
            //나뉜 전력망에서 송전탑의 개수 구해서 차 구하기
            //(i에서 나온 wire 의 양 끝단을 dfs 시작점으로 구하자)
            songjun = 0;
            visited = new boolean[n+1];
            dfs(wires[i][0]);
            int result1 = songjun;
            answer = Math.min(answer,Math.abs(result1-(n-result1)));
        }
        return answer;
    }
}
