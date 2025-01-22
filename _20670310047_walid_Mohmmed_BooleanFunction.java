package Mantiksal;

import java.util.ArrayList;
import java.util.List;

class _20670310047_walid_Mohmmed_BooleanFunction {
    private String function;

    public _20670310047_walid_Mohmmed_BooleanFunction(String function) {
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public List<Character> extractVariables() {
        List<Character> variables = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            if (function.contains(String.valueOf(c))) {
                variables.add(c);
            }
        }
        return variables;
    }
}
