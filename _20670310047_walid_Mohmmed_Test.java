package Mantiksal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class _20670310047_walid_Mohmmed_Test {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Walid muhammed\\Desktop\\LessonsThirdYear\\boole.txt";

        try {
            String booleanFunction = _20670310047_walid_Mohmmed_FileManager.readBooleanFunction(filePath);
            List<Character> variables = new _20670310047_walid_Mohmmed_BooleanFunction(booleanFunction).extractVariables();
            int numVariables = variables.size();
            List<List<Integer>> truthTable = _20670310047_walid_Mohmmed_FileManager.generateTruthTable(numVariables);

            for (List<Integer> row : truthTable) {
                List<Integer> variableValues = new ArrayList<>(row);
                int F = evaluate(booleanFunction, variableValues, variables);
                row.add(F);
            }

            _20670310047_walid_Mohmmed_FileManager.printTruthTable(truthTable, variables);
            _20670310047_walid_Mohmmed_FileManager.expressAsSumOfMinterms(booleanFunction, variables, truthTable);
            _20670310047_walid_Mohmmed_FileManager.expressAsProductOfMaxterms(booleanFunction, variables, truthTable);

        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static int evaluate(String booleanFunction, List<Integer> values, List<Character> variables) {
        boolean result = false;
        String[] terms = booleanFunction.split("\\+");

        for (String term : terms) {
            boolean termResult = true;

            for (int i = 0; i < term.length(); i++) {
                char literal = term.charAt(i);

                if (Character.isLetter(literal)){
                    boolean variableValue;
                    int index = variables.indexOf(literal);

                    if (i < term.length() - 1 && term.charAt(i + 1) == '’') {
                        variableValue = values.get(index) == 0;
                        i++;
                    } else {
                        variableValue = values.get(index) == 1;
                    }

                    termResult &= variableValue;
                }
            }

            result |= termResult;
        }

        return result ? 1 : 0;
    }
}
