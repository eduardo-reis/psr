import br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.csp.variable.SudokuVariable;
import br.ufs.dcomp.eduard6.pesquisas.psr.sudoku.problem.Sudoku;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TestSuodku {

    public static void main(String[] args) throws IOException {

        Sudoku s = Sudoku.fromFile(new File("C:\\Users\\Eduardo\\Documents\\sudoku-csp\\src\\test\\java\\s1.txt"));

        System.out.println(s.getPlace((short)1, (short) 1));
        System.out.println(s.getPlace((short)0, (short) 0));
        System.out.println(s.getPlace((short)1, (short) 2));
        System.out.println();

        System.out.println(Arrays.toString(s.getColumn((short)0)));
        System.out.println(Arrays.toString(s.getColumn((short)1)));
        System.out.println(Arrays.toString(s.getColumn((short)2)));
        System.out.println();

        System.out.println(Arrays.toString(s.getRow((short)0)));
        System.out.println(Arrays.toString(s.getRow((short)1)));
        System.out.println(Arrays.toString(s.getRow((short)2)));
        System.out.println();

        System.out.println(Arrays.toString(s.getSquare((short)0, (short)0)));
        System.out.println();

        for (SudokuVariable[] sq : s.getAllSquares()){
            System.out.println(Arrays.toString(sq));
        }
    }
}
