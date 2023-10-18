/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package board;

import Piezas.*;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ramir
 */
public class ChessBoard extends GridPane {

    private int currentTurn = 1;
    private ChessField[][] _fields = new ChessField[8][8];
    private King kingCheck;

    public ChessBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessField field = new ChessField(this, i, j);
                add(field, i, j);
                this._fields[i][j] = field;
            }
        }
    }
    
    public ChessBoard(ChessField[][] fields) {
    this._fields = deepCopy(fields);
}

public ChessBoard(ChessBoard board) {
    this._fields = deepCopy(board.getFields());
}
private ChessField[][] deepCopy(ChessField[][] fields) {
    ChessField[][] copy = new ChessField[fields.length][fields[0].length];
    for (int i = 0; i < fields.length; i++) {
        for (int j = 0; j < fields[i].length; j++) {
            ChessField field = fields[i][j];
            ChessField fieldCopy = new ChessField(field.getBoard(), field.getX(), field.getY());
            if (field.getPiece() != null) {
                Piece piece = field.getPiece();
                Piece pieceCopy = createPieceCopy(piece, fieldCopy);
                fieldCopy.setPiece(pieceCopy);
            }
            copy[i][j] = fieldCopy;
        }
    }
    return copy;
}

private Piece createPieceCopy(Piece piece, ChessField field) {
    if (piece instanceof Rook) {
        return new Rook(piece.getColor(), field);
    } else if (piece instanceof Knight) {
        return new Knight(piece.getColor(), field);
    } else if (piece instanceof Bishop) {
        return new Bishop(piece.getColor(), field);
    } else if (piece instanceof Queen) {
        return new Queen(piece.getColor(), field);
    } else if (piece instanceof King) {
        return new King(piece.getColor(), field);
    } else if (piece instanceof Pawn) {
        return new Pawn(piece.getColor(), field);
    }
    return null;
}
    
    public ChessField getField(int x, int y) {
        return this._fields[x][y];
    }
    
    public ChessField[][] getFields(){
        return this._fields;
    }

    public void movePiece(Piece piece, int x, int y) {
        if (piece == null) {
            return; // No hacer nada si la pieza es nula
        }
        
        this._fields[piece.getField().getX()][piece.getField().getY()].setPiece(null);
        if (piece.moveTo(this._fields[x][y])) {
            this._fields[x][y].setPiece(piece);
            this.currentTurn++;
            for (ChessField[] _field : _fields) {
                for (ChessField chessField : _field) {
                    if( chessField.getPiece() instanceof King&&chessField.getPiece().getColor()!=piece.getColor()){
                        if(piece.getAccessibleFields().contains(chessField)){
                            //Enemy king in check
                            kingCheck = ((King)chessField.getPiece());
                        }
                    }
                }
            }
        }
    }
    public Color getTurn(){
        if(this.currentTurn%2==0){
            return Color.BLACK;
        }else{
            return Color.WHITE;
        }
    }
    
    public int getCurrentTurn(){
        return this.currentTurn;
    }

    public void prepareBoard() {

        Piece blackRook1 = new Rook(Color.BLACK, this._fields[0][0]);
        Piece blackKnight1 = new Knight(Color.BLACK, this._fields[1][0]);
        Piece blackBishop1 = new Bishop(Color.BLACK, this._fields[2][0]);
        Piece blackQueen = new Queen(Color.BLACK, this._fields[3][0]);
        Piece blackKing = new King(Color.BLACK, this._fields[4][0]);
        Piece blackBishop2 = new Bishop(Color.BLACK, this._fields[5][0]);
        Piece blackKnight2 = new Knight(Color.BLACK, this._fields[6][0]);
        Piece blackRook2 = new Rook(Color.BLACK, this._fields[7][0]);

        this._fields[0][0].setPiece(blackRook1);
        this._fields[1][0].setPiece(blackKnight1);
        this._fields[2][0].setPiece(blackBishop1);
        this._fields[3][0].setPiece(blackQueen);
        this._fields[4][0].setPiece(blackKing);
        this._fields[5][0].setPiece(blackBishop2);
        this._fields[6][0].setPiece(blackKnight2);
        this._fields[7][0].setPiece(blackRook2);
        for (int i = 0; i < 8; i++) {
            this._fields[i][1].setPiece(new Pawn(Color.BLACK, this._fields[i][1]));
        }
        Piece whiteRook1 = new Rook(Color.WHITE, this._fields[0][7]);
        Piece whiteKnight1 = new Knight(Color.WHITE, this._fields[1][7]);
        Piece whiteBishop1 = new Bishop(Color.WHITE, this._fields[2][7]);
        Piece whiteQueen = new Queen(Color.WHITE, this._fields[3][7]);
        Piece whiteKing = new King(Color.WHITE, this._fields[4][7]);
        Piece whiteBishop2 = new Bishop(Color.WHITE, this._fields[5][7]);
        Piece whiteKnight2 = new Knight(Color.WHITE, this._fields[6][7]);
        Piece whiteRook2 = new Rook(Color.WHITE, this._fields[7][7]);

        this._fields[0][7].setPiece(whiteRook1);
        this._fields[1][7].setPiece(whiteKnight1);
        this._fields[2][7].setPiece(whiteBishop1);
        this._fields[4][7].setPiece(whiteKing);
        this._fields[3][7].setPiece(whiteQueen);
        this._fields[5][7].setPiece(whiteBishop2);
        this._fields[6][7].setPiece(whiteKnight2);
        this._fields[7][7].setPiece(whiteRook2);
        for (int i = 0; i < 8; i++) {
            this._fields[i][6].setPiece(new Pawn(Color.WHITE, this._fields[i][6]));
        }

        //center labels on GridPane
        for (ChessField[] _field : _fields) {
            for (ChessField chessField : _field) {
                GridPane.setHalignment(chessField, HPos.CENTER);
            }
        }
    }

    public List<ChessField> lookForAttacks(List<ChessField> accessibleFields, Color color) {
        List<ChessField> attackedFields = getAttackedFields(color);
        List<ChessField> realAccessibleFields = new ArrayList<>();
        for (ChessField accessibleField : accessibleFields) {
            if(!attackedFields.contains(accessibleField)){
                realAccessibleFields.add(accessibleField);
            }
        }
        return realAccessibleFields;
    }

    public List<ChessField> getAttackedFields(Color color) {
        List<ChessField> attackedFields = new ArrayList<>();
        for (ChessField[] fields : _fields) {
            for (ChessField field : fields) {
                Piece piece = field.getPiece();
                if(piece!=null&&!(piece instanceof King)&&piece.getColor()!=color){
                    List<ChessField> accessibleFields = piece.getAccessibleFields();
                    if(piece instanceof Pawn){
                        accessibleFields = ((Pawn) piece).getAttackFields();
                    }
                    for (ChessField accessibleField : accessibleFields) {
                        if(!attackedFields.contains(accessibleField)){
                            attackedFields.add(accessibleField);
                        }
                    }
                }
            }
        }
        return attackedFields;
    }

    public List<ChessField> getRealAccessibleFields(Piece piece) {
    List<ChessField> accessibleFields = piece.getAccessibleFields();
    List<ChessField> realAccessibleFields = new ArrayList<>();

    for (ChessField field : accessibleFields) {
        ChessBoard testBoard = new ChessBoard(this); // Crea una copia del tablero actual para realizar pruebas
        Piece testPiece = testBoard.getField(piece.getField().getX(), piece.getField().getY()).getPiece();

        // Realiza el movimiento en el tablero de prueba
        testBoard.movePiece(testPiece, field.getX(), field.getY());

        // Verifica si el movimiento resulta en un jaque al rey del mismo color
        if (!testBoard.isKingInCheck(piece.getColor())) {
            realAccessibleFields.add(field);
        }
    }

    return realAccessibleFields;
}
    private boolean isKingInCheck(Color color) {
    // Encuentra la posición del rey del color especificado
    ChessField kingField = findKingField(color);

    // Verifica si alguna pieza enemiga puede atacar al rey
    for (ChessField[] row : _fields) {
        for (ChessField field : row) {
            Piece piece = field.getPiece();

            // Verifica si la pieza es enemiga y puede atacar al rey
            if (piece != null && piece.getColor() != color && piece.canAttack(kingField)) {
                return true; // El rey está en jaque
            }
        }
    }

    return false; // El rey no está en jaque
}

private ChessField findKingField(Color color) {
    for (ChessField[] row : _fields) {
        for (ChessField field : row) {
            Piece piece = field.getPiece();
            if (piece instanceof King && piece.getColor() == color) {
                return field; // Devuelve el campo donde se encuentra el rey del color especificado
            }
        }
    }
    return null; // No se encontró el rey
}

}
