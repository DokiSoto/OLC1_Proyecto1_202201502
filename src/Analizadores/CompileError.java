/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Analizadores;

/**
 *
 * @author dxssm
 */
public class CompileError {
    
    private final String tipo;
    private final String descripcion;
    private final int linea;
    private final int columna;
    public CompileError(String tipo, String descripcion, int linea, int columna){
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna= columna;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }
    
}
