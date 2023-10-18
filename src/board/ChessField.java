/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package board;

import Piezas.Piece;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author ramir
 */
public class ChessField extends Label{
    private int _x;
    private int _y;
    private ChessBoard _board;
    private Piece _piece;
    
    public ChessField(ChessBoard board, int x, int y){
        this._board = board;
        this._x = x;
        this._y = y;
        this.setMaxHeight(Double.MAX_VALUE);
        this.setMaxWidth(Double.MAX_VALUE);
        this.setWrapText(true);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * @return the _x
     */
    public int getX() {
        return _x;
    }

    /**
     * @return the _y
     */
    public int getY() {
        return _y;
    }

    /**
     * @return the _piece
     */
    public Piece getPiece() {
        return _piece;
    }

    /**
     * @param _piece the _piece to set
     */
    public void setPiece(Piece _piece) {
        this._piece = _piece;
        if(this._piece!=null){
            setGraphic(this._piece.getImageView());
        }else{
            setGraphic(null);
        }
    }

    /**
     * @return the _board
     */
    public ChessBoard getBoard() {
        return _board;
    }
}
