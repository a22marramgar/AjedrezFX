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
public class King extends Piece {

    public King(Color color, ChessField field) {
        super(color, "king", field);
    }

    @Override
    public List<ChessField> getAccessibleFields() {
        List<ChessField> fields = new ArrayList<>();
        int x = this.getField().getX();
        int y = this.getField().getY();
        if (x + 1 < 8) {
            addField(fields, x + 1, y);
            if (y + 1 < 8) {
                addField(fields, x + 1, y + 1);
            }
            if (y - 1 >= 0) {
                addField(fields, x + 1, y - 1);
            }
        }
        if (y + 1 < 8) {
            addField(fields, x, y + 1);

        }
        if (x - 1 >= 0) {
            addField(fields, x - 1, y);
            if (y + 1 < 8) {
                addField(fields, x - 1, y + 1);
            }
            if (y - 1 >= 0) {
                addField(fields, x - 1, y - 1);
            }
        }
        if (y - 1 >= 0) {
            addField(fields, x, y - 1);
        }
        fields = this.getField().getBoard().lookForAttacks(fields, this.getColor());
        return fields;
    }

    private boolean addField(List<ChessField> fields, int x, int y) {
        ChessField field = this.getField().getBoard().getField(x, y);
        boolean added = false;
            if (field.getPiece() == null) {
                fields.add(field);
                added = true;
            } else if (field.getPiece().getColor() != this.getColor()) {
                fields.add(field);
                added = true;
            }
        return added;
    }
}
