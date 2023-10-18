/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Piezas;

import static Piezas.Color.WHITE;
import board.ChessField;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramir
 */
public class Pawn extends Piece {
    
    private int turnMoved = 0;
    
    public Pawn(Color color, ChessField field) {
        super(color, "pawn", field);
    }

    @Override
    public List<ChessField> getAccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        int x = this.getField().getX();
        int y = this.getField().getY();
        if (this.getColor() == WHITE) {
            if (y - 1 >= 0) {
                ChessField field = this.getField().getBoard().getField(x, y - 1);
                if (field.getPiece() == null) {
                    fields.add(field);
                    if (y == 6) {
                        field = this.getField().getBoard().getField(x, y - 2);
                        if (field.getPiece() == null) {
                            fields.add(field);
                        }
                    }
                }
                if (x - 1 >= 0) {
                    field = this.getField().getBoard().getField(x - 1, y - 1);
                    if (field.getPiece() != null && field.getPiece().getColor() != this.getColor()) {
                        fields.add(field);
                    }
                    field = this.getField().getBoard().getField(x - 1, y);
                    if(field.getPiece()!=null&&y==3&&field.getPiece() instanceof Pawn){
                        if(((Pawn)field.getPiece()).firstTurn()){
                            field = this.getField().getBoard().getField(x - 1, y - 1);
                            fields.add(field);
                        }
                    }
                }
                if (x + 1 < 8) {
                    field = this.getField().getBoard().getField(x + 1, y - 1);
                    if (field.getPiece() != null && field.getPiece().getColor() != this.getColor()) {
                        fields.add(field);
                    }
                    field = this.getField().getBoard().getField(x + 1, y);
                    if(field.getPiece()!=null&&y==3&&field.getPiece() instanceof Pawn){
                        if(((Pawn)field.getPiece()).firstTurn()){
                            field = this.getField().getBoard().getField(x + 1, y - 1);
                            fields.add(field);
                        }
                    }
                }
                
            }
        } else {
            if (y + 1 < 8) {
                ChessField field = this.getField().getBoard().getField(x, y + 1);
                if (field.getPiece() == null) {
                    fields.add(field);
                    if (y == 1) {
                        field = this.getField().getBoard().getField(x, y + 2);
                        if (field.getPiece() == null) {
                            fields.add(field);
                        }
                    }
                }
                if (x - 1 >= 0) {
                    field = this.getField().getBoard().getField(x - 1, y + 1);
                    if (field.getPiece() != null && field.getPiece().getColor() != this.getColor()) {
                        fields.add(field);
                    }
                    field = this.getField().getBoard().getField(x - 1, y);
                    if(field.getPiece()!=null&&y==4&&field.getPiece() instanceof Pawn){
                        if(((Pawn)field.getPiece()).firstTurn()){
                            field = this.getField().getBoard().getField(x - 1, y + 1);
                            fields.add(field);
                        }
                    }
                }
                if (x + 1 < 8) {
                    field = this.getField().getBoard().getField(x + 1, y + 1);
                    if (field.getPiece() != null && field.getPiece().getColor() != this.getColor()) {
                        fields.add(field);
                    }
                    field = this.getField().getBoard().getField(x + 1, y);
                    if(field.getPiece()!=null&&y==4&&field.getPiece() instanceof Pawn){
                        if(((Pawn)field.getPiece()).firstTurn()){
                            field = this.getField().getBoard().getField(x + 1, y + 1);
                            fields.add(field);
                        }
                    }
                }
            }
        }
        return fields;
    }
    
    public boolean firstTurn(){
        if(this.getField().getBoard().getCurrentTurn()==this.turnMoved+1){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean moveTo(ChessField field){
        int x = this.getField().getX();
        int y = this.getField().getY();
        boolean moved = super.moveTo(field);
        if(moved){
            if(turnMoved == 0){
                turnMoved = this.getField().getBoard().getCurrentTurn();
            }
            
            if(this.getColor() == WHITE){
                if(y==3&&field.getY()==2){
                    field = this.getField().getBoard().getField(field.getX(), y);
                    if(field.getPiece() instanceof Pawn&&field.getPiece().getColor() != this.getColor()){
                        if(((Pawn)field.getPiece()).firstTurn()){
                            field.setPiece(null);
                        }
                    }
                }
            }else{
                if(y==4&&field.getY()==5){
                    field = this.getField().getBoard().getField(field.getX(), y);
                    if(field.getPiece() instanceof Pawn){
                        if(((Pawn)field.getPiece()).firstTurn()){
                            field.setPiece(null);
                        }
                    }
                }
            }
            
        }
        return moved;
    }

    public List<ChessField> getAttackFields() {
        List<ChessField> fields = new ArrayList<>();
        int x = this.getField().getX();
        int y = this.getField().getY();
        if (this.getColor() == WHITE) {
            ChessField field;
            if (x - 1 >= 0) {
                field = this.getField().getBoard().getField(x - 1, y - 1);
                fields.add(field);

            }
            if (x + 1 < 8) {
                field = this.getField().getBoard().getField(x + 1, y - 1);
                fields.add(field);

            }
        } else {
            ChessField field;
            if (x - 1 >= 0) {
                field = this.getField().getBoard().getField(x - 1, y + 1);
                fields.add(field);
            }
            if (x + 1 < 8) {
                field = this.getField().getBoard().getField(x + 1, y + 1);
                fields.add(field);

            }
        }
        return fields;
    }

    @Override
    public boolean canAttack(ChessField kingField) {
        if(this.getAttackFields().contains(kingField)){
            return true;
        }
        return false;
    }

}
