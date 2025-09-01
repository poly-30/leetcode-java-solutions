import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefixProducts;

    public ProductOfNumbers() {
        prefixProducts = new ArrayList<>();
        prefixProducts.add(1);
    }

    public void add(int num) {
        if (num > 0) {
            int lastProduct = prefixProducts.get(prefixProducts.size() - 1);
            prefixProducts.add(lastProduct * num);
        } else {
            prefixProducts = new ArrayList<>();
            prefixProducts.add(1);
        }
    }
    
    public int getProduct(int k) {
        int n = prefixProducts.size();
        if (k >= n) {
            return 0;
        }
        int totalProduct = prefixProducts.get(n - 1);
        int productBeforeK = prefixProducts.get(n - 1 - k);
        
        return totalProduct / productBeforeK;
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_1 = obj.getProduct(k);
 */