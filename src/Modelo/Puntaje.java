/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author dxssm
 */
public class Puntaje {
    private final int damagePoint;
    private final int healingPoint;
    private final int succesfulDefense;
    private final int victoryBonus;
    private final int failedActionPenalty;
    
    public Puntaje(int damagePoint, int healingPoint, int succesfulDefense, int victoryBonus, int failedActionPenalty){
        this.damagePoint = damagePoint;
        this.healingPoint = healingPoint;
        this.succesfulDefense = succesfulDefense;
        this.victoryBonus = victoryBonus;
        this.failedActionPenalty = failedActionPenalty;
    }

    public int getDamagePoint() {
        return damagePoint;
    }

    public int getHealingPoint() {
        return healingPoint;
    }

    public int getSuccesfulDefense() {
        return succesfulDefense;
    }

    public int getVictoryBonus() {
        return victoryBonus;
    }

    public int getFailedActionPenalty() {
        return failedActionPenalty;
    }
    
    
}
