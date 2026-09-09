/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author dxssm
 */

import engine.BattleScriptExecutionException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class ProgramaMOdelo {
    private final List<Estrategias> strategies;
    private final List<MatchConfig> matches;
    private final List<RunCommand> runs;

    public ProgramaMOdelo(List<Estrategias> strategies, List<MatchConfig> matches, List<RunCommand> runs) {
        this.strategies = new ArrayList<>(strategies);
        this.matches = new ArrayList<>(matches);
        this.runs = new ArrayList<>(runs);
    }
    
    public void validateForExecution(){
        
         Set<String> strategyNames = new HashSet<>();
         for(Estrategias strategy : strategies){
             
             if(!strategyNames.add(strategy.getName())){
                 
               throw new BattleScriptExecutionException("Estrategia duplicada" + strategy.getName());

             }
         
         }

    
        Set<String> matchNames = new HashSet<>();
        for (MatchConfig match : matches) {

            if (!matchNames.add(match.getName())) {throw new BattleScriptExecutionException("Partida duplicada: " + match.getName());
            
            
            }

            if (match.getRounds() <= 0) {throw new BattleScriptExecutionException("Las rondas de " + match.getName()+ " deben ser mayores que cero.");
            }

            if (findStrategy(match.getJugador1()) == null) {throw new BattleScriptExecutionException("No existe la estrategia: "+ match.getJugador1());
            
            }

            if (findStrategy(match.getJugador2()) == null) {throw new BattleScriptExecutionException("No existe la estrategia: "+ match.getJugador2());
            }

            validateScoring(match.getScoring());
            validateBonuses(match.getBonuses());
        }
        for(RunCommand run: runs){
              if(run.getSeed() <= 0){
                  throw new BattleScriptExecutionException("La semilla debe ser algo que sirva");
              
              }
              
              if(run.getMatches().isEmpty()){
                   throw new BattleScriptExecutionException("debe haber algo");
             
              
              }
              
              for(String name : run.getMatches()){
                     if(findMatch(name) == null){
                                        throw new BattleScriptExecutionException("la partida no existe" + name);
   
                     
                     }
              
              }
        
        }
    }
    
    private void validateScoring(Puntaje scoring){
     if(scoring.getDamagePoint() <= 0){
       throw new BattleScriptExecutionException("damage_point debe ser mayor a 0");
     
     }
     
     if(scoring.getHealingPoint() <0 || scoring.getSuccesfulDefense() < 0 || scoring.getVictoryBonus() < 0 || scoring.getFailedActionPenalty() <0){
     
       throw new BattleScriptExecutionException("Los valores dde scoring deben ser positivos");
     }
     
     
    
    }
    
    private void validateBonuses(Bonus bonuses){
          if(bonuses.getMageCombo().isEmpty()){
          throw new BattleScriptExecutionException ( "esa madre no puede estar vacia");
          
          }
          
          if(bonuses.getWarriorCombo().isEmpty()){
                 throw new BattleScriptExecutionException("el combo contiene algo que no es suyo");
          }
          
          for(Accion action : bonuses.getMageCombo()){
               if(!action.allowedFor(CHaracterType.MAGE)){
                 throw new BattleScriptExecutionException("El mage contiene una accion ");
               }
          }
          
          for (Accion action : bonuses.getWarriorCombo()){
            if(!action.allowedFor(CHaracterType.WARRIOR)){
            throw new BattleScriptExecutionException("Warrior");
          }
          }
          
          if (bonuses.getMageComboPoints() < 0 || bonuses.getWarriorComboPoints() <0 || bonuses.getLowHealthVictory() <0){
        throw new BattleScriptExecutionException("los bonos no pueden ser negativas ");
    }
          
    }
    
    public Estrategias findStrategy(String name){
       for(Estrategias strategy : strategies){
           if(strategy.getName().equals(name)){
               return strategy;
           }
       }
       
       return null;
    
    }
    
    public MatchConfig findMatch(String name){
        for(MatchConfig match : matches){
          if(match.getName().equals(name)){
            return match;
          
          }
        
        }
        return null;
    }
    
    public List<Estrategias> getStrategies(){
       return new ArrayList<>(strategies);
    }
    
    public List<MatchConfig> getMatches(){
         return new ArrayList<>(matches);
    
    }
    
    public  List<RunCommand> getRuns(){
    
        return new ArrayList<>(runs);
    }
}
