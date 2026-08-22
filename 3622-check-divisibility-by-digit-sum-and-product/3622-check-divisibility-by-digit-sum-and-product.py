class Solution:
    def checkDivisibility(self, n: int) -> bool:
        a = n
        sum = 0
        prod = 1
        while a > 0:
            sum += a % 10
            prod *= a % 10
            a //= 10
        return n % (sum + prod) == 0
        