import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class FoodRatings {
    private static class Food {
        String name;
        String cuisine;
        int rating;
        Food(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }
    private final Map<String, Food> foodInfoMap;
    private final Map<String, TreeSet<Food>> cuisineRatingsMap;
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodInfoMap = new HashMap<>();
        cuisineRatingsMap = new HashMap<>();
        java.util.Comparator<Food> foodComparator = (a, b) -> {
            if (a.rating != b.rating) {
                return Integer.compare(b.rating, a.rating);
            }
            return a.name.compareTo(b.name);
        };
        for (int i = 0; i < foods.length; i++) {
            Food food = new Food(foods[i], cuisines[i], ratings[i]);
            foodInfoMap.put(foods[i], food);
            cuisineRatingsMap.computeIfAbsent(cuisines[i], k -> new TreeSet<>(foodComparator))
                           .add(food);
        }
    }
    public void changeRating(String foodName, int newRating) {
        Food food = foodInfoMap.get(foodName);
        TreeSet<Food> cuisineSet = cuisineRatingsMap.get(food.cuisine);
        cuisineSet.remove(food);
        food.rating = newRating;
        cuisineSet.add(food);
    }
    public String highestRated(String cuisine) {
        return cuisineRatingsMap.get(cuisine).first().name;
    }
}