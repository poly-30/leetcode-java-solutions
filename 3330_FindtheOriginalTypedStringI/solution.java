class Solution {
    public int possibleStringCount(String word) {
    int total = 1;
    int n = word.length();
    for(int i = 1; i < n; ++i){
        if(word.charAt(i) == word.charAt(i-1)){
            total++;
        }
    }
        return total;
    }
}