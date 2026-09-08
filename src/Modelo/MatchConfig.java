/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Modelo;

/**
 *
 * @author dxssm
 */
public class MatchConfig {
    private final String name;
    private final String jugador1;
    private final String jugador2;
    private final int rounds;
    private final Puntaje scoring;
    private final Bonus bonuses;

    public MatchConfig(String name, String jugador1, String jugador2, int rounds, Puntaje scoring, Bonus bonuses) {
        this.name = name;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.rounds = rounds;
        this.scoring = scoring;
        this.bonuses = bonuses;
    }

    public String getName() {
        return name;
    }

    public String getJugador1() {
        return jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public int getRounds() {
        return rounds;
    }

    public Puntaje getScoring() {
        return scoring;
    }

    public Bonus getBonuses() {
        return bonuses;
    }
    
    
}
