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
public class Knight extends Piece {

    public Knight(Color color, ChessField field) {
        super(color, "knight", field);
    }

    @Override
    public List<ChessField> getAccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        int x = this.getField().getX();
        int y = this.getField().getY();
        if(x+2<8){
            if(y-1>=0){
                addField(fields, x+2, y-1);
            }
            if(y+1<8){
                addField(fields, x+2, y+1);
            }
        }
        if(x-2>=0){
            if(y-1>=0){
                addField(fields, x-2, y-1);
            }
            if(y+1<8){
                addField(fields, x-2, y+1);
            }
        }
        if(y+2<8){
            if(x-1>=0){
                addField(fields, x-1, y+2);
            }
            if(x+1<8){
                addField(fields, x+1, y+2);
            }
        }
        if(y-2>=0){
            if(x-1>=0){
                addField(fields, x-1, y-2);
            }
            if(x+1<8){
                addField(fields, x+1, y-2);
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
