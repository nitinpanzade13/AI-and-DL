// MinimaxTicTacToe.java
import java.io.*;

public class MinimaxTicTacToe {
    static final char X = 'X', O = 'O', EMPTY = '-';
    char[][] board = new char[3][3];

    static class Move { int row, col; }

    public MinimaxTicTacToe() {
        for (int i=0;i<3;i++) for (int j=0;j<3;j++) board[i][j]=EMPTY;
    }

    void printBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) System.out.print(board[i][j]+" ");
            System.out.println();
        }
    }

    boolean isMovesLeft(){
        for(int i=0;i<3;i++) for(int j=0;j<3;j++) if(board[i][j]==EMPTY) return true;
        return false;
    }

    int evaluate(){
        for(int row=0;row<3;row++){
            if(board[row][0]==board[row][1] && board[row][1]==board[row][2]){
                if(board[row][0]==X) return +10;
                else if(board[row][0]==O) return -10;
            }
        }
        for(int col=0;col<3;col++){
            if(board[0][col]==board[1][col] && board[1][col]==board[2][col]){
                if(board[0][col]==X) return +10;
                else if(board[0][col]==O) return -10;
            }
        }
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2]){
            if(board[0][0]==X) return +10;
            else if(board[0][0]==O) return -10;
        }
        if(board[0][2]==board[1][1] && board[1][1]==board[2][0]){
            if(board[0][2]==X) return +10;
            else if(board[0][2]==O) return -10;
        }
        return 0;
    }

    int minimax(int depth, boolean isMax, int alpha, int beta){
        int score = evaluate();
        if(score==10) return score-depth;
        if(score==-10) return score+depth;
        if(!isMovesLeft()) return 0;

        if(isMax){
            int best=Integer.MIN_VALUE;
            for(int i=0;i<3;i++) for(int j=0;j<3;j++){
                if(board[i][j]==EMPTY){
                    board[i][j]=X;
                    best=Math.max(best, minimax(depth+1,false,alpha,beta));
                    board[i][j]=EMPTY;
                    alpha=Math.max(alpha,best);
                    if(beta<=alpha) return best;
                }
            }
            return best;
        }else{
            int best=Integer.MAX_VALUE;
            for(int i=0;i<3;i++) for(int j=0;j<3;j++){
                if(board[i][j]==EMPTY){
                    board[i][j]=O;
                    best=Math.min(best, minimax(depth+1,true,alpha,beta));
                    board[i][j]=EMPTY;
                    beta=Math.min(beta,best);
                    if(beta<=alpha) return best;
                }
            }
            return best;
        }
    }

    Move findBestMove(){
        int bestVal=Integer.MIN_VALUE; Move bestMove=new Move();
        bestMove.row=-1; bestMove.col=-1;
        for(int i=0;i<3;i++) for(int j=0;j<3;j++){
            if(board[i][j]==EMPTY){
                board[i][j]=X;
                int moveVal=minimax(0,false,Integer.MIN_VALUE,Integer.MAX_VALUE);
                board[i][j]=EMPTY;
                if(moveVal>bestVal){bestMove.row=i; bestMove.col=j; bestVal=moveVal;}
            }
        }
        return bestMove;
    }

    public static void main(String[] args)throws Exception{
        MinimaxTicTacToe game=new MinimaxTicTacToe();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("You are O. Computer is X.");
        game.printBoard();
        while(true){
            System.out.print("Enter your move (row col): ");
            String[] p=br.readLine().split(" ");
            int r=Integer.parseInt(p[0]), c=Integer.parseInt(p[1]);
            if(game.board[r][c]!=EMPTY){System.out.println("Invalid!"); continue;}
            game.board[r][c]=O; game.printBoard();
            if(game.evaluate()==-10){System.out.println("You win!"); break;}
            if(!game.isMovesLeft()){System.out.println("Draw!"); break;}
            Move m=game.findBestMove();
            game.board[m.row][m.col]=X;
            System.out.println("Computer moves: "+m.row+" "+m.col);
            game.printBoard();
            if(game.evaluate()==10){System.out.println("Computer wins!"); break;}
            if(!game.isMovesLeft()){System.out.println("Draw!"); break;}
        }
    }
}
