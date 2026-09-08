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
public class RunCommand {
    private final List<String> matches;
    private final int seed;

    public RunCommand(List<String> matches, int seed) {
        this.matches = matches;
        this.seed = seed;
    }

    public List<String> getMatches() {
        return matches;
    }

    public int getSeed() {
        return seed;
    }
    
    
}
