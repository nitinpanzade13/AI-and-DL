// EightQueensSolver.java
import java.util.*;

public class EightQueensSolver {
    int N=8; int[] board=new int[8];
    List<int[]> solutions=new ArrayList<>();

    boolean isSafe(int col,int row){
        for(int c=0;c<col;c++){
            int r=board[c];
            if(r==row || Math.abs(r-row)==Math.abs(c-col)) return false;
        }
        return true;
    }

    void solve(int col){
        if(col==N){ solutions.add(board.clone()); return; }
        for(int row=0;row<N;row++){
            if(isSafe(col,row)){ board[col]=row; solve(col+1); }
        }
    }

    void printSolution(int[] sol){
        for(int r=0;r<N;r++){
            for(int c=0;c<N;c++){
                System.out.print(sol[c]==r? "Q ":"- ");
            }System.out.println();
        }
    }

    public static void main(String[] args){
        EightQueensSolver eq=new EightQueensSolver();
        eq.solve(0);
        System.out.println("Total Solutions: "+eq.solutions.size());
        if(eq.solutions.size()>0) eq.printSolution(eq.solutions.get(0));
    }
}
