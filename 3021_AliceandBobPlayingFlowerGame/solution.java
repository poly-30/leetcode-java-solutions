class Solution {
    public long flowerGame(int n, int m) {
        // Use long to avoid overflow when n or m are large.
        long n_long = n;
        long m_long = m;

        // Count odd and even numbers for Alice's choices (1 to n)
        long odd_n = (n_long + 1) / 2;
        long even_n = n_long / 2;

        // Count odd and even numbers for Bob's choices (1 to m)
        long odd_m = (m_long + 1) / 2;
        long even_m = m_long / 2;

        // Total winning pairs = (Alice picks odd * Bob picks even) + (Alice picks even * Bob picks odd)
        return (odd_n * even_m) + (even_n * odd_m);
    }
}