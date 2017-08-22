/**
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>.
 */
public class Piece {

    private final PieceType pieceType;

    private final Color color;

    public Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    /**
     * 0 - белый король
     * 1 - белая ладья
     *
     * 6 - черный король
     * 7 - черная ладья
     */
    public int getCode() {
        return color.getCode() * 6 + pieceType.getCode();
    }
}
