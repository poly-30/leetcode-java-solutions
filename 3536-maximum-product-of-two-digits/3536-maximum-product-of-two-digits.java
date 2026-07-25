import java.util.Arrays;
class Solution {
    private int prod(int[] arr){
        if(arr.length == 2) return arr[0] * arr[1];
        Arrays.sort(arr);
        int n = arr.length;
        return arr[n-1] * arr[n-2];
    }
    public int maxProduct(int number) {
        int length = Integer.toString(number).length(); 
        int[] digits = new int[length];
        
        // Populate the array from right to left
        for (int i = length - 1; i >= 0; i--) {
            digits[i] = number % 10;
            number /= 10;
        }
        return prod(digits);
    }
}