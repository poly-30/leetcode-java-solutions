import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

class Solution {
    static class Trie {
        String name;

        Map<String, Trie> children = new HashMap<>();
        boolean deleted = false;
    }

    Map<String, List<Trie>> subtreeMap = new HashMap<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Trie root = new Trie();
        for (List<String> path : paths) {
            Trie curr = root;
            for (String folder : path) {
                curr = curr.children.computeIfAbsent(folder, k -> {
                    Trie node = new Trie();
                    node.name = k;
                    return node;
                });
            }
        }

        serialize(root);

        for (List<Trie> nodelist : subtreeMap.values()) {
            if (nodelist.size() > 1) {
                for (Trie node : nodelist) {
                    node.deleted = true;
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        dfs(root, new ArrayList<>(), result);
        return result;
    }

    private String serialize(Trie node) {
        if (node.children.isEmpty()) {
            return "";
        }

        List<String> parts = new ArrayList<>();
        for (String key : new TreeSet<>(node.children.keySet())) {
            Trie child = node.children.get(key);
            parts.add("(" + key + serialize(child) + ")");
        }

        String serial = String.join("", parts);
        
        if (!serial.isEmpty()) {
            subtreeMap.computeIfAbsent(serial, k -> new ArrayList<>()).add(node);
        }
        return serial;
    }
    
    private void dfs(Trie node, List<String> path, List<List<String>> result) {
        for (Trie child : node.children.values()) {
            if (child.deleted) {
                continue;
            }
            path.add(child.name);
            result.add(new ArrayList<>(path));
            dfs(child, path, result);
            path.remove(path.size() - 1); 
        }
    }
}