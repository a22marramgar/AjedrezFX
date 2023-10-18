/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Piezas;

import board.ChessField;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ramir
 */
public abstract class Piece {
    private Color _color;
    private String _name;
    private String _imageFileName;
    private Image _image;
    private ChessField _field;
    
    public Piece(Color color, String name, ChessField field){
        this._color = color;
        this._name = name;
        this._field = field;
        this._imageFileName = "images/"+color.getName()+"_"+name+".png";
        this._image = new Image(this._imageFileName);
    }

    /**
     * @return the _color
     */
    public Color getColor() {
        return _color;
    }

    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @return the _image
     */
    public Image getImage() {
        return _image;
    }
    
    public ImageView getImageView(){
        return new ImageView(getImage());
    }
    
    public abstract List<ChessField> getAccessibleFields();
    
    public boolean moveTo(ChessField field){
        if(getAccessibleFields().contains(field)){
            this._field = field;
            return true;
        }
        return false;
    }

    /**
     * @param _image the _image to set
     */
    public void setImage(Image _image) {
        this._image = _image;
    }

    /**
     * @return the _field
     */
    public ChessField getField() {
        return _field;
    }

    public boolean canAttack(ChessField kingField){
        if(this.getAccessibleFields().contains(kingField)){
            return true;
        }
        return false;
    }
}
