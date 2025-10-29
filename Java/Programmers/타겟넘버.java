class Solution {
    static int answer = 0;
    static int[] numbers;
    static int target;
    public void dfs(int depth, int sum){
        
        if(depth == numbers.length){
            if(sum == target) answer++;
            return;
        }
        
        dfs(depth+1,sum + numbers[depth]);
        dfs(depth+1,sum - numbers[depth]);
        
    }
    
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        dfs(0,0);
   
        return answer;
    }
}
