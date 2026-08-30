class Solution:
    def minimumDeletions(self, nums: List[int]) -> int:
        n = len(nums)
        if n <= 2:
            return n
        
        # Find indices of min and max elements
        min_idx = nums.index(min(nums))
        max_idx = nums.index(max(nums))
        
        # Determine smaller and larger index positions
        i, j = min(min_idx, max_idx), max(min_idx, max_idx)
        
        # Calculate moves for 3 options and return the minimum
        both_front = j + 1
        both_back = n - i
        front_and_back = (i + 1) + (n - j)
        
        return min(both_front, both_back, front_and_back)