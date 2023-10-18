/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Piezas;

import board.ChessField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramir
 */
public class Bishop extends Piece {
    
    public Bishop(Color color, ChessField field) {
        super(color, "bishop", field);
    }

    @Override
    public List<ChessField> getAccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        int x = this.getField().getX();
        int y = this.getField().getY();
        for(int i = 1; i+x<8&&y+i<8; i++){
            if(!addField(fields, x+i, y+i)){
                break;
            }
        }
        for(int i = 1; x-i>=0&&y-i>=0; i++){
            if(!addField(fields, x-i, y-i)){
                break;
            }
        }
        for(int i = 1; y+i<8&&x-i>=0; i++){
            if(!addField(fields, x-i, y+i)){
                break;
            }
        }
        for(int i = 1; y-i>=0&&x+i<8; i++){
            if(!addField(fields, x+i, y-i)){
                break;
            }
        }
        return fields;
    }
    
    private boolean addField(List<ChessField> fields, int x, int y){
        ChessField field = this.getField().getBoard().getField(x,y);
        boolean added = false;
        if(field.getPiece() == null){
            fields.add(field);
            added = true;
        }else if(field.getPiece().getColor()!= this.getColor()){
            fields.add(field);
        }
        return added;
    }
}
