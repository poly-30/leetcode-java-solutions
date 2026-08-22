from collections import Counter
from typing import List

class Solution:
    def canArrange(self, arr: List[int], k: int) -> bool:
        freq = Counter((x % k + k) % k for x in arr)
        
        for remainder in freq:
            if remainder == 0:
                if freq[remainder] % 2 != 0:
                    return False
            elif remainder * 2 == k:
                if freq[remainder] % 2 != 0:
                    return False
            else:
                if freq[remainder] != freq[k - remainder]:
                    return False
                    
        return True