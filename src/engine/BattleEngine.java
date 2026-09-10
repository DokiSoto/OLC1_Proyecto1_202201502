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
            
            }
    }
}
