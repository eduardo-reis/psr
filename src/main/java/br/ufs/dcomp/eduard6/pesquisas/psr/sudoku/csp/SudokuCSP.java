package br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.csp;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.csp.constraint.AllDiff;
import br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.csp.variable.SudokuVariable;
import br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.problem.Sudoku;

import java.util.List;

public class SudokuCSP extends CSP<SudokuVariable, Short> {
    protected static final Short[] SUDOKU_DOMAIN = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    private final Sudoku sudoku;

    public SudokuCSP(Sudoku sudoku) {
        this.sudoku = sudoku;
        loadCSP();
    }

    private void loadCSP() {
        loadVariables();
        loadDomains();
        loadConstraints();
    }

    private void loadVariables() {
        for (short i = 0; i < 9; i ++) {
            for (short j = 0; j < 9; j++) {
                addVariable(sudoku.getPlace(i,j));
            }
        }
    }

    private void loadDomains() {
        for (int i = 0; i < getVariables().size(); i++) {
            SudokuVariable currentVariable = getVariables().get(i);
            if (currentVariable.getValue() == 0) {
                setDomain(currentVariable, new Domain<>(SUDOKU_DOMAIN));
            } else {
                setDomain(currentVariable, new Domain<>(currentVariable.getValue()));
            }
        }
    }

    private void loadConstraints() {
        numbersInTheSameRowMustDiffer();
        numbersInTheSameColumnMustDiffer();
        numbersInTheSameSquareMustDiffer();
    }

    private void numbersInTheSameRowMustDiffer() {
        List<SudokuVariable[]> rows = sudoku.getAllRows();
        for (SudokuVariable[] row : rows)
            addConstraint(new AllDiff(row));
    }

    private void numbersInTheSameColumnMustDiffer() {
        List<SudokuVariable[]> columns = sudoku.getAllColumns();
        for (SudokuVariable[] column : columns)
            addConstraint(new AllDiff(column));
    }

    private void numbersInTheSameSquareMustDiffer() {
        List<SudokuVariable[]> squares = sudoku.getAllSquares();
        for (SudokuVariable[] square : squares)
            addConstraint(new AllDiff(square));
    }
}
