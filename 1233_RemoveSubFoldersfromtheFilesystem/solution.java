import java.util.*;

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        List<String> ans = new ArrayList<>();
        Arrays.sort(folder);
        String prev = "";
        for (String f : folder) {
            if (!prev.isEmpty() && f.startsWith(prev) && f.charAt(prev.length()) == '/')
                continue;
            ans.add(f);
            prev = f;
        }
        return ans;
    }
}
