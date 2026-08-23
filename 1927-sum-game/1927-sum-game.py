class Solution:
    def sumGame(self, num: str) -> bool:
        n = len(num) // 2
        
        # Calculate sum and count of '?' for left half
        s_left = sum(int(c) for c in num[:n] if c != '?')
        q_left = num[:n].count('?')
        
        # Calculate sum and count of '?' for right half
        s_right = sum(int(c) for c in num[n:] if c != '?')
        q_right = num[n:].count('?')
        
        # Total sum difference and question mark count difference
        s_diff = s_left - s_right
        q_diff = q_left - q_right
        
        # Bob wins if and only if s_diff + 4.5 * q_diff == 0
        return (s_diff * 2 + q_diff * 9) != 0