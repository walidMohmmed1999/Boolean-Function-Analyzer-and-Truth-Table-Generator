package Mantiksal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class _20670310047_walid_Mohmmed_FileManager {
    public static String readBooleanFunction(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine().trim();
        reader.close();
        return line.split("=")[1].trim();
    }

    public static List<List<Integer>> generateTruthTable(int numVariables) {
        List<List<Integer>> truthTable = new ArrayList<>();
        int numRows = (int) Math.pow(2, numVariables);

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = numVariables - 1; j >= 0; j--) {
                int variableValue = (i >> j) & 1;
                row.add(variableValue);
            }
            truthTable.add(row);
        }

        return truthTable;
    }

    public static void printTruthTable(List<List<Integer>> truthTable, List<Character> variables) {
        System.out.println("boole.txt dosyası okundu.");
        System.out.println("doğruluk tablosu:");
        StringBuilder header = new StringBuilder();
        for (char variable : variables) {
            header.append(variable).append(" ");
        }
        header.append("F");
        System.out.println(header);

        for (List<Integer> row : truthTable) {
            StringBuilder rowString = new StringBuilder();
            for (int value : row) {
                rowString.append(value).append(" ");
            }
            System.out.println(rowString);
        }
    }

    public static void expressAsSumOfMinterms(String expression, List<Character> variables, List<List<Integer>> truthTable) {
        List<String> minterms = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < truthTable.size(); i++) {
            if (truthTable.get(i).get(truthTable.get(0).size() - 1) == 1) {
                indices.add(i);
            }
        }

        for (Integer index : indices) {
            StringBuilder minterm = new StringBuilder();

            List<Integer> row = truthTable.get(index);
            for (int j = 0; j < variables.size(); j++) {
                char variable = variables.get(j);
                if (row.get(j) == 0) {
                    minterm.append(variable).append("'");
                } else {
                    minterm.append(variable);
                }
            }

            minterms.add(minterm.toString());
        }

        System.out.println("Fonksiyon İfadeleri:");
        System.out.println("F = " + String.join(" + ", minterms));
        System.out.println("F = Σ(" + indices.toString().replaceAll("[\\[\\]]", "") + ")");
    }

    public static void expressAsProductOfMaxterms(String expression, List<Character> variables, List<List<Integer>> truthTable) {
        List<String> maxterms = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < truthTable.size(); i++) {
            if (truthTable.get(i).get(truthTable.get(0).size() - 1) == 0) {
                indices.add(i);
            }
        }

        for (Integer index : indices) {
            StringBuilder maxterm = new StringBuilder("(");

            List<Integer> row = truthTable.get(index);
            for (int j = 0; j < variables.size(); j++) {
                char variable = variables.get(j);
                if (row.get(j) == 0) {
                    maxterm.append(variable);
                } else {
                    maxterm.append(variable).append("'");
                }
                maxterm.append(" + ");
            }

            maxterm.delete(maxterm.length() - 3, maxterm.length());
            maxterm.append(")");
            maxterms.add(maxterm.toString());
        }

        System.out.println("F = " + String.join(" . ", maxterms));
        System.out.println("F = ∏(" + indices.toString().replaceAll("[\\[\\]]", "") + ")");
    }
}
