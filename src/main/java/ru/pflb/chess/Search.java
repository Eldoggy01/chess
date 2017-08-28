package ru.pflb.chess;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static ru.pflb.chess.Color.WHITE;

/**
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>.
 */
public class Search {

    class Entry implements Comparable<Integer> {
        int value;
        // principal variation
        List<Move> pv;

        public Entry(int value, List<Move> pv) {
            this.value = value;
            this.pv = pv;
        }

        public List<Move> addPv(Move mv) {
            pv.add(mv);
            return pv;
        }

        @Override
        public int compareTo(Integer other) {
            return Integer.compare(value, other);
        }
    }

    public static int perft(Board board, int depth) {
        if (depth == 0) {
            return 1;
        }
        List<Move> moves = new MoveGenerator(board).generateMoves();
        int positions = 0;

        for (Move move: moves) {
            board.doMove(move);
            if (!board.isCheck(board.getSideToMove().getOpposite())) {
            positions += perft(board, depth-1); }
            board.undoMove(move);


        }
        return positions;
    }

    public static List<Move> bestMoves(Board board) {
        Search search = new Search();
        return board.getSideToMove() == WHITE
                ? search.alphaBetaMax(board, MIN_VALUE, MAX_VALUE, 2).pv :
                search.alphaBetaMin(board, MIN_VALUE, MAX_VALUE, 2).pv;
    }

    private Entry alphaBetaMax(Board board, int alpha, int beta, int depthleft) {
        if (depthleft == 0) {
            return new Entry(new Eval(board).getValue(), new ArrayList<>());
        }
        List<Move> moves = new MoveGenerator(board).generateMoves();
        List<Move> pv = null;
        for (Move move : moves) {
            board.doMove(move);
            if (board.isCheck(board.getSideToMove().getOpposite())) {
                board.undoMove(move);
                continue;
            }
            Entry score = alphaBetaMin(board, alpha, beta, depthleft - 1);
            board.undoMove(move);
            if (score.compareTo(beta) >= 0) {
                // fail hard beta-cutoff
                return new Entry(beta, score.addPv(move));
            }
            if (score.compareTo(alpha) > 0) {
                // alpha acts like max in MiniMax
                alpha = score.value;
                pv = score.pv;
            }
        }
        return new Entry(alpha, pv == null ? new ArrayList<>() : pv);
    }

    private Entry alphaBetaMin(Board board, int alpha, int beta, int depthleft) {
        if (depthleft == 0) {
            return new Entry(-new Eval(board).getValue(), new ArrayList<>());
        }
        List<Move> moves = new MoveGenerator(board).generateMoves();
        List<Move> pv = null;
        for (Move move : moves) {
            board.doMove(move);
            if (board.isCheck(board.getSideToMove().getOpposite())){
                board.undoMove(move);
                continue;
            }
            Entry score = alphaBetaMax(board, alpha, beta, depthleft - 1);
            board.undoMove(move);
            if (score.compareTo(alpha) <= 0) {
                // fail hard alpha-cutoff
                return new Entry(alpha, score.addPv(move));
            }
            if (score.compareTo(beta) < 0) {
                // beta acts like min in MiniMax
                beta = score.value;
            }
        }
        return new Entry(beta, pv == null ? new ArrayList<>() : pv);
    }
}
