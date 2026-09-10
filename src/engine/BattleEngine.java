/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package engine;
import expr.EvalContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Modelo.Accion;
import Modelo.Bonus;
import Modelo.CHaracterType;
import Modelo.MatchConfig;
import Modelo.ProgramaMOdelo;
import Modelo.RunCommand;
import Modelo.Puntaje;
import Modelo.Estrategias;
/**
 *
 * @author dxssm
 */
public class BattleEngine {
    public String execute (ProgramaMOdelo program){
            program.validateForExecution();
            StringBuilder output = new StringBuilder();
            
            for(RunCommand run: program.getRuns()){
                for(String matchName : run.getMatches()){
                    MatchConfig match = program.findMatch(matchName);
                    output.append("partida: ").append(match.getName()).append("\n");
                output.append("SEED: ").append(run.getSeed()).append("\n");
                
                executeMatch(program, match , run.getSeed(), output);
                
                output.append("\n");
                }
            }
            
            return output.toString();
    }
    private void executeMatch(ProgramaMOdelo program, MatchConfig match, int seed, StringBuilder output){
      Estrategias strategy1 = program.findStrategy(match.getJugador1());
      Estrategias strategy2 = program.findStrategy(match.getJugador2());
      
      Combatant player1 = new Combatant(strategy1);
      Combatant player2 = new Combatant(strategy2);
      Random random1 = new Random(seed);
      Random random2 = new Random((long) seed + 1L);
      
      boolean finished = false;
      
      for(int round =0; round < match.getRounds(); round++){
           player1.defending = false;
           player2.defending = false;
           
           double randomValue1 =0.0;
           double randomValue2 =0.0;
           
           if(round > 0){
               randomValue1 = random1.nextDouble();
               randomValue2 = random2.nextDouble();
           }
           
           Accion action1;
           Accion action2;
           
           if(round ==0){
               action1 = strategy1.getInitialAction();
               action2 = strategy2.getInitialAction();
           } else {
              EvalContext = context1 = createContext(round, match.getRounds(), player1. player2, randomValue1);
              EvalContext = context2 = createContext(round, match.getRounds(), player2. player1, randomValue2);
            action1 = strategy1.choose(context1);
            action2 = strategy1.choose(context2);
            
           }
           
           output.append("\nRonda").append(round).append("\n");
           
           if(round >0){
               output.append("Random ")
                        .append(player1.name)
                        .append(": ")
                        .append(randomValue1)
                        .append("\n");

                output.append("Random ")
                        .append(player2.name)
                        .append(": ")
                        .append(randomValue2)
                        .append("\n"); 
           }
      output.append(player1.name)
                    .append(" selecciona ")
                    .append(action1)
                    .append("\n");

            output.append(player2.name)
                    .append(" selecciona ")
                    .append(action2)
                    .append("\n");
            
            boolean player1First = goesFirst(player1, action1, player2, action2);
            
            if(player1First){
               executeAction(player1, player2, action1, match, output);
               
               if(player2.health <= 0){
                  finished = true;
               } else{
                   executeAction(player2, player1, action2,match, output);
                   
                   if(player1.health <=0){
                       finished = true;
                   }
               }
            } else{
                executeAction(player2, player1, action2, match, output);
                
                if(player1.health <=0){
                    finished = true;
                    
                
                }
                else{
                   executeAction(player1, player2, action1, match, output);
                   
                   if(player2.health <=0){
                   
                    finished = true;
                   }
                
                }
            }
            output.append("Estado -> ")
                    .append(player1.name)
                    .append(": HP=")
                    .append(player1.health)
                    .append(", recurso=")
                    .append(player1.resource)
                    .append(", puntos=")
                    .append(player1.score)
                    .append(" | ")
                    .append(player2.name)
                    .append(": HP=")
                    .append(player2.health)
                    .append(", recurso=")
                    .append(player2.resource)
                    .append(", puntos=")
                    .append(player2.score)
                    .append("\n");
            
            if(finished){
              break;
            }
      }
      
      Combatant winner = determineWinner(player1, player2);
      
      if(winner = null){
         output.append("\nnadie gano maje\n");
      }
      
      else{
         winner.score += match.getScoring().getVictoryBonus();
         output.append("gano ese maje").append(winner.name).append(": +").append(match.getScoring().getVictoryBonus()).append("\n");
          if(winner.health * 4 <= winner.maxHealth){
             winner.score += match.getBonuses().getLowHealthVictory();
             
             output.append("ganaste raspado maje+ ").append(match.getBonuses().getLowHealthVictory()).append("\n");
          }
          
          output.append("GANADOR: ").append(winner.name).append("n");
      }
                output.append("puntaje final").append(player1.name).append(":").append(player1.score).append("\n");
          output.append("puntaje final").append(player2.name).append(":").append(player2.score).append("\n");

    }
    private EvalContext createContext(
            int round,
            int totalRounds,
            Combatant self,
            Combatant opponent,
            double random
    ) {

        return new EvalContext(
                round,
                totalRounds,
                self.health,
                opponent.health,
                self.resource,
                opponent.resource,
                self.score,
                opponent.score,
                new ArrayList<>(self.history),
                new ArrayList<>(opponent.history),
                random
        );
    }
    
    private boolean goesFirst(
            Combatant player1,
            Accion action1,
            Combatant player2,
            Accion action2
    ) {

        if (action1.getPriority()
                != action2.getPriority()) {

            return action1.getPriority()
                    > action2.getPriority();
        }

        if (player1.speed != player2.speed) {

            return player1.speed
                    > player2.speed;
        }

        return true;
    }
    
}
