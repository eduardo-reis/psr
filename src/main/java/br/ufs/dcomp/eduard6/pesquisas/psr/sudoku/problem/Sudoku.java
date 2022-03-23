package br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.problem;


import aima.core.search.csp.Assignment;
import aima.core.search.csp.Variable;
import br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.csp.variable.SudokuVariable;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sudoku {
    private final SudokuVariable[][] matrix;

    public Sudoku(SudokuVariable[][] matrix) {
        if (matrix.length != 9) throw new AssertionError();
        this.matrix = matrix;
    }

    public void setPlace(short row, short col, Short value) {
        matrix[row][col] = new SudokuVariable(row, col, value);
    }

    public SudokuVariable getPlace(short row, short col) {
        return matrix[row][col];
    }

    public static Sudoku fromAssigment(Assignment<SudokuVariable, Short> assignment) {
        SudokuVariable[][] m = new SudokuVariable[9][9];

        for (SudokuVariable variable : assignment.getVariables())
            m[variable.getRow()][variable.getCol()] = new SudokuVariable(variable.getRow(), variable.getCol(), assignment.getValue(variable));

        return new Sudoku(m);
    }

    public static Sudoku fromFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            SudokuVariable[][] m = new SudokuVariable[9][9];

            String line;
            short row = 0;
            short col;
            while ((line = reader.readLine()) != null) {
                col = 0;
                Short[] lineValues = Arrays.stream(line.split(" ")).map(Short::valueOf).toArray(Short[]::new);
                for (Short value : lineValues) {
                    m[row][col] = new SudokuVariable(row, col, value);
                    col++;
                }
                row++;
            }

            return new Sudoku(m);
        }
    }

    public SudokuVariable[] getRow(short row) {
        return matrix[row];
    }

    public SudokuVariable[] getColumn(short column) {
        return Arrays.stream(matrix).map(l -> l[column]).toArray(SudokuVariable[]::new);
    }

    public SudokuVariable[] getSquare(short row, short col) {
        if (col < 0 || col > 6 || col % 3 != 0) throw new AssertionError();
        if (row < 0 || row > 6 || row % 3 != 0) throw new AssertionError();

        SudokuVariable[] result = new SudokuVariable[9];
        short k = 0;

        for (short i = row; i < row + 3; i++) {
            for (short j = col; j < col + 3; j ++) {
                result[k] = matrix[i][j];
                k++;
            }
        }

        return result;
    }


    public List<SudokuVariable[]> getAllRows() {
        List<SudokuVariable[]> result = new ArrayList<>();

        for (short i = 0; i < matrix.length; i++) {
            result.add(getRow(i));
        }

        return result;
    }

    public List<SudokuVariable[]> getAllColumns() {
        List<SudokuVariable[]> result = new ArrayList<>();

        for (short i = 0; i < matrix.length; i++) {
            result.add(getColumn(i));
        }

        return result;
    }

    public List<SudokuVariable[]> getAllSquares() {
        ArrayList<SudokuVariable[]> result = new ArrayList<>(9);

        for (short i = 0; i < matrix.length; i += 3) {
            for (short j = 0; j < matrix.length; j += 3) {
                result.add(this.getSquare(i, j));
            }
        }

        return result;
    }

    public boolean isSolved() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (short i = 0; i < matrix.length; i++) {
            for (short j = 0; j < matrix.length; j++) {
                sb.append(this.getPlace(i, j).getValue());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
