/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dxssm
 */
public class Bonus {
private final List<Accion> mageCombo;
    private final int mageComboPoints;
    private final List<Accion> warriorCombo;
    private final int warriorComboPoints;
    private final int lowHealthVictory;

    public Bonus(List<Accion> mageCombo, int mageComboPoints, List<Accion> warriorCombo, int warriorComboPoints, int lowHealthVictory) {
        this.mageCombo = mageCombo;
        this.mageComboPoints = mageComboPoints;
        this.warriorCombo = warriorCombo;
        this.warriorComboPoints = warriorComboPoints;
        this.lowHealthVictory = lowHealthVictory;
    }

    public List<Accion> getMageCombo() {
        return mageCombo;
    }

    public int getMageComboPoints() {
        return mageComboPoints;
    }

    public List<Accion> getWarriorCombo() {
        return warriorCombo;
    }

    public int getWarriorComboPoints() {
        return warriorComboPoints;
    }

    public int getLowHealthVictory() {
        return lowHealthVictory;
    }
    

}
