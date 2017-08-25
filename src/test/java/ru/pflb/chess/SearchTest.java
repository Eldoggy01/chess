package ru.pflb.chess;


import org.junit.Ignore;
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
    @Ignore
    public void perftTest() {
        Board board = new Board("3kr3/Kr6/8/8/8/8/8/8 w - -");
        int movesNb = Search.perft(board, 2);

        assertThat(movesNb).isEqualTo(65);
    }

    @Test
    public void searchTest() {
        Board board = new Board("r2k4/8/8/8/8/8/R7/K7 w - -");

        List<Move> moves = Search.bestMoves(board);

        assertThat(moves.get(0)).isEqualTo(new Move(A2, A8, B_ROOK));
    }

}