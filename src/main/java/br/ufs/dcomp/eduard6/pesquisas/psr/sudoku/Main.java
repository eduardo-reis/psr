package br.ufs.dcomp.eduard6.pesquisas.psr.sudoku;

import aima.core.search.csp.CspListener;
import aima.core.search.csp.CspSolver;
import aima.core.search.csp.FlexibleBacktrackingSolver;
import aima.core.search.csp.MinConflictsSolver;
import br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.csp.SudokuCSP;
import br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.csp.variable.SudokuVariable;
import br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.problem.Sudoku;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SudokuCSP sudokuCSP = new SudokuCSP(Sudoku.fromFile(new File("C:\\Users\\Eduardo\\Documents\\sudoku-csp\\src\\test\\java\\s2.txt")));
        CspListener.StepCounter<SudokuVariable, Short> stepCounter1 = new CspListener.StepCounter<>();
        CspListener.StepCounter<SudokuVariable, Short> stepCounter2 = new CspListener.StepCounter<>();
        CspListener.StepCounter<SudokuVariable, Short> stepCounter3 = new CspListener.StepCounter<>();

        solve(sudokuCSP, stepCounter1, new FlexibleBacktrackingSolver<SudokuVariable, Short>().setAll());
        solve(sudokuCSP, stepCounter2, new FlexibleBacktrackingSolver<>());
        solve(sudokuCSP, stepCounter3, new MinConflictsSolver<>(1000000));
    }

    private static void solve(SudokuCSP sudokuCSP, CspListener.StepCounter<SudokuVariable, Short> stepCounter, CspSolver<SudokuVariable, Short> solver) {
        solver.addCspListener(stepCounter);
        solver.solve(sudokuCSP).ifPresent(ass -> System.out.println(ass.isSolution(sudokuCSP)+ "\n" + Sudoku.fromAssigment(ass)));
        System.out.println(stepCounter.getResults());

    }
}
