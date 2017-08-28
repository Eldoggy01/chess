package ru.pflb.chess;


import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.pflb.chess.Piece.B_ROOK;
import static ru.pflb.chess.Square.A2;
import static ru.pflb.chess.Square.A8;

/**
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>.
 */
public class SearchTest {

    @Test
//    @Ignore
    public void perftTest() {
        long millis = System.currentTimeMillis();
        Board board = new Board("K1k5/1R1b4/8/8/4Q3/8/5b2/8 w - -");
        int movesNb = Search.perft(board, 5);
        System.out.println(movesNb + " " + (System.currentTimeMillis() - millis));

        assertThat(movesNb).isEqualTo(54);
    }

    @Test
    public void searchTest() {
        Board board = new Board("K1k5/1R1b4/8/8/4Q3/8/5b2/8 w - -");

        //List<Move> moves = Search.bestMoves(board);

       // assertThat(moves.get(0)).isEqualTo(new Move(A2, A8, B_ROOK));
    }

}