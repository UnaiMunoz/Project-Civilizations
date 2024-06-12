package com.civilizations;

public interface MilitaryUnit {
    int getUnit_id();
    //DEVUELVE LA ID DE LA TROPA
    String getName();
    //DEVUELVE NOMBRE
    int attack();
    // Nos devolverá el poder de ataque que tenga la unidad.
    void takeDamage(int receivedDamage);
    // Restará a nuestro blindaje el daño recibido por parámetro.
    int getActualArmor();
    // Nos devolverá el blindaje que tengamos actualmente, después de haber
    // recibido un ataque.
    int getFoodCost();
    // Nos devolverá el coste de Comida que tiene crear una nueva unidad.
    int getWoodCost();
    // Nos devolverá el coste de Madera que tiene crear una nueva unidad.
    int getIronCost();
    // Nos devolverá el coste de Hierro que tiene crear una nueva unidad.
    int getManaCost();
    // Nos devolverá el coste de Mana que tiene crear una nueva unidad.
    int getChanceGeneratinWaste();
    // Nos la probabilidad de generar residuos al ser totalmente eliminada(blindaje 0 o inferior).
    int getChanceAttackAgain();
    // Nos la probabilidad de volver a atacar.
    void resetArmor();
    // Nos restablecerá nuestro blindaje a su valor original.
    void setExperience(int n);
    // Establecerá la experiencia a un nuevo valor.
    int getExperience();
    // Nos devolverá la experiencia actual de la unidad
}
