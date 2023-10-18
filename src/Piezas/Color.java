/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Piezas;

/**
 *
 * @author ramir
 */
public enum Color {
    WHITE("white"),
    BLACK("black");
    
    private String _name;
    
    Color(String name){
        this._name=name;
    }
    
    public String getName(){
        return this._name;
    }
}
