package br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.csp.constraint;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.csp.variable.SudokuVariable;

import java.util.Arrays;
import java.util.List;

public class AllDiff implements Constraint<SudokuVariable, Short> {

    private final SudokuVariable[] variables;

    public AllDiff(SudokuVariable... variables) {
        this.variables = variables;
    }

    @Override
    public List<SudokuVariable> getScope() {
        return Arrays.asList(variables);
    }

    @Override
    public boolean isSatisfiedWith(Assignment<SudokuVariable, Short> assignment) {
        boolean[] found = new boolean[10];

        for (SudokuVariable variable : variables) {
            Short variableValue = assignment.getValue(variable);
            if (variableValue != null) {
                if (found[variableValue])
                    return false;

                found[variableValue] = true;
            }
        }

        return true;
    }
}
