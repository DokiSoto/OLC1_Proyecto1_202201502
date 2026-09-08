/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import expr.EvalContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dxssm
 */
public class Estrategias {
    private final String name;
    private final CHaracterType type;
    private final Accion initialAction;
    private final List<Rule> rules;
    private final Accion defaultAction;
    
    public Estrategias(String name, CHaracterType type, Accion initialAction, List<Rule> rules, Accion defaultAction){
     this.name = name;
     this.type= type;
     this.initialAction=initialAction;
     this.rules = new ArrayList<>(rules);
     this.defaultAction = defaultAction;
    }
    
    public Accion choose(EvalContext context){
        for (Rule rule: rules){
            Object result = rule.getCondition().eval(context);
            
            if(!(result instanceof Boolean)){
                 throw new RuntimeException("Ocurrio un error en algo");
            
            }
            
            if((Boolean)result){
               return rule.getAction();
            }
        }
        return defaultAction;
    }

    public String getName() {
        return name;
    }

    public CHaracterType getType() {
        return type;
    }

    public Accion getInitialAction() {
        return initialAction;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Accion getDefaultAction() {
        return defaultAction;
    }
    
    
}
