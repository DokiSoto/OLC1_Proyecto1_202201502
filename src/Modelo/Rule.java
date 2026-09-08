/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import expr.Expresion;

/**
 *
 * @author dxssm
 */
public class Rule {
    private final Expresion condition;
    private final Accion action;
    
    
    public Rule(Expresion condition, Accion action){
        this.condition = condition;
        this.action = action;
    }

    public Expresion getCondition() {
        return condition;
    }

    public Accion getAction() {
        return action;
    }
}
