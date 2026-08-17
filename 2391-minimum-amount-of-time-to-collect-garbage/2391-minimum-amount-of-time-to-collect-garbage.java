class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {

        int[] prefTravel = new int[garbage.length];
        for (int i = 0; i < travel.length; i++) {
            prefTravel[i + 1] = prefTravel[i] + travel[i];
        }

        int lastM = 0, lastP = 0, lastG = 0;
        int totalCollectionTime = 0;

        for (int i = 0; i < garbage.length; i++) {
            totalCollectionTime += garbage[i].length();
            for (char c : garbage[i].toCharArray()) {
                if (c == 'M') lastM = i;
                else if (c == 'P') lastP = i;
                else if (c == 'G') lastG = i;
            }
        }
        return totalCollectionTime + prefTravel[lastM] + prefTravel[lastP] + prefTravel[lastG];
    }
}