package br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.csp.variable;

import aima.core.search.csp.Variable;

public class SudokuVariable extends Variable {

    private short value;
    private short row;
    private short col;

    public SudokuVariable(short row, short col, short value) {
        super(String.format("(%d,%d)", row, col));
        this.col = col;
        this.row = row;
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public short getRow() {
        return row;
    }

    public void setRow(short row) {
        this.row = row;
    }

    public short getCol() {
        return col;
    }

    public void setCol(short col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d)", row, col, value);
    }
}
