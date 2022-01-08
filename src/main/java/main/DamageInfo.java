package main;

public class DamageInfo {
    private final int damage;
    private final boolean critHit;

    public DamageInfo(int damage, boolean critHit) {
        this.damage = damage;
        this.critHit = critHit;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isCritHit() {
        return critHit;
    }
}
