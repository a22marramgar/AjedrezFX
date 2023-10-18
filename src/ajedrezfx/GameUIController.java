/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ajedrezfx;

import Piezas.King;
import Piezas.Piece;
import board.ChessBoard;
import board.ChessField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author ramir
 */
public class GameUIController implements Initializable {

    private Piece pieceSelected;
    private ChessBoard board;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView gridImage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gridImage.fitHeightProperty().bind(grid.heightProperty());
        gridImage.fitWidthProperty().bind(grid.widthProperty());
        board = new ChessBoard();
        board.prepareBoard();
        for (int i = 0; i < 8; i++) {
            grid.add(board.getField(i, 0), i, 0);
            grid.add(board.getField(i, 1), i, 1);
            grid.add(board.getField(i, 2), i, 2);
            grid.add(board.getField(i, 3), i, 3);
            grid.add(board.getField(i, 4), i, 4);
            grid.add(board.getField(i, 5), i, 5);
            grid.add(board.getField(i, 6), i, 6);
            grid.add(board.getField(i, 7), i, 7);
        }
    }

    @FXML
    public void doAction(MouseEvent event) {
        Node source = (Node) event.getTarget();
        if (source instanceof ChessField) {
            if (pieceSelected == null) {

                Integer colIndex = GridPane.getColumnIndex(source);
                Integer rowIndex = GridPane.getRowIndex(source);
                pieceSelected = board.getField(colIndex, rowIndex).getPiece();
                if (pieceSelected != null && pieceSelected.getColor() == board.getTurn()) {
                    //TODO show accessibleFields
                    Color blue = Color.rgb(0, 206, 209, 0.5);
                    source.setStyle("-fx-background-color: " + toRGBCode(blue) + ";");
                    for (Node node : grid.getChildren()) {
                        if (board.getRealAccessibleFields(pieceSelected).contains((ChessField) node)) {
                            if (((ChessField) node).getPiece() == null) {
                                Color green = Color.rgb(124, 252, 0, 0.5);
                                node.setStyle("-fx-background-color: " + toRGBCode(green) + ";");
                            } else {
                                Color red = Color.rgb(255, 0, 0, 0.5);
                                node.setStyle("-fx-background-color: " + toRGBCode(red) + ";");
                            }

                        }
                    }

                }else{
                    pieceSelected = null;
                }

            } else {
                Integer colIndex = GridPane.getColumnIndex(source);
                Integer rowIndex = GridPane.getRowIndex(source);
                ChessField targetField = board.getField(colIndex, rowIndex);
                if (board.getRealAccessibleFields(pieceSelected).contains(targetField)) {
                    board.movePiece(pieceSelected, targetField.getX(), targetField.getY());
                    pieceSelected = null;
                    resetHighlightedFields();
                } else if (pieceSelected.getField().equals(targetField)) {
                    pieceSelected = null;
                    resetHighlightedFields();
                }

            }
        }
    }

    private void resetHighlightedFields() {
        //TODO unshow highlightedFields
        for (Node node : grid.getChildren()) {
            Color blank = Color.rgb(0, 0, 0, 0);
            node.setStyle("-fx-background-color: " + toRGBCode(blank) + ";");
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255),
                (int) (color.getOpacity() * 255));
    }

}
