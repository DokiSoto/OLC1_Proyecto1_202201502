/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author dxssm
 */
public enum Accion {
    
    ARCANE_BOLT(CHaracterType.MAGE, Kind.MAGICAL_ATTACK, 12, 10, 4),
    FIREBALL(CHaracterType.MAGE, Kind.MAGICAL_ATTACK, 25, 30, 2),
    MAGIC_BARRIER(CHaracterType.MAGE, Kind.DEFENSE, 50, 20, 7),
    HEALING_RUNE(CHaracterType.MAGE, Kind.HEAL, 25, 30, 5),
    MEDITATE(CHaracterType.MAGE, Kind.RECOVERY, 25, 0, 1),

    SLASH(CHaracterType.WARRIOR, Kind.PHYSICAL_ATTACK, 12, 10, 4),
    HEAVY_STRIKE(CHaracterType.WARRIOR, Kind.PHYSICAL_ATTACK, 25, 25, 2),
    SHIELD_BLOCK(CHaracterType.WARRIOR, Kind.DEFENSE, 50, 15, 7),
    WAR_CRY(CHaracterType.WARRIOR, Kind.BUFF, 10, 20, 6),
    REST(CHaracterType.WARRIOR, Kind.RECOVERY, 25, 0, 1);
    
    public enum Kind{
        PHYSICAL_ATTACK,
        MAGICAL_ATTACK,
        DEFENSE,
        HEAL,
        RECOVERY,
        BUFF
    }
    
    private final CHaracterType owner;
    private final Kind kind;
    private final int power;
    private final int cost;
    private final int priority;
    
    Accion(CHaracterType owner, Kind kind, int power, int cost, int priority){
        this.owner = owner;
        this.kind = kind;
        this.power= power;
        this.cost = cost;
        this.priority = priority;
    }

    public CHaracterType getOwner() {
        return owner;
    }

    public Kind getKind() {
        return kind;
    }

    public int getPower() {
        return power;
    }

    public int getCost() {
        return cost;
    }

    public int getPriority() {
        return priority;
    }
    
    public boolean allowedFor(CHaracterType type){
        return owner == type;
    }
    
    public boolean isOffensive(){
        return kind == Kind.PHYSICAL_ATTACK || kind == Kind.MAGICAL_ATTACK;
    }
    
    
    
}
