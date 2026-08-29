class Solution:
    def lexicographicallySmallestArray(self, nums: list[int], limit: int) -> list[int]:
        n = len(nums)
        paired = sorted([(val, idx) for idx, val in enumerate(nums)])
        
        result = [0] * n
        i = 0
        
        while i < n:
            j = i
            indices = []
            while j < n and (j == i or paired[j][0] - paired[j - 1][0] <= limit):
                indices.append(paired[j][1])
                j += 1
            
            indices.sort()
            for k, idx in enumerate(indices):
                result[idx] = paired[i + k][0]
                
            i = j
            
        return result