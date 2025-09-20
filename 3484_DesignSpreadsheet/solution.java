import java.util.HashMap;
import java.util.Map;

class Spreadsheet {
    private final Map<String, Integer> spreadsheet = new HashMap<>();

    public Spreadsheet(int rows) {
        // All cells default to 0, handled by getOrDefault during getValue.
    }

    public void setCell(String cell, int value) {
        spreadsheet.put(cell, value);
    }

    public void resetCell(String cell) {
        spreadsheet.put(cell, 0);
    }

    public int getValue(String formula) {
        int i = formula.indexOf('+');
        // formula always starts with '='
        return getToken(formula.substring(1, i)) + getToken(formula.substring(i + 1));
    }
    
    private int getToken(String token) {
        token = token.trim();
        if (Character.isDigit(token.charAt(0))) {
            return Integer.parseInt(token);
        }
        return spreadsheet.getOrDefault(token, 0);
    }
}