package com.civilizations;

public class Civilization {
    int technologyDefense ;
    int technologyAtack ;
    int wood;
    int iron;
    int food;
    int mana;
    int magicTower;
    int church;
    int farm;
    int smithy;
    int carpentry;
    int battles;
    /*ArrayList<MilitaryUnit> army = new ArrayList[9];*/
 
    /*
    void new Church,
    void new MagicTower,
    void new Farm,
    void new Carpentry,
    void new Smithy:
    Con estos métodos podemos crear una nueva iglesia o una nueva torre mágica.
    En caso de no tener recursos suficientes, lanzaremos una excepción del tipo
    ResourceException, que comentaremos más adelante
    */
    /*
    void upgradeTechnologyDefense,
    void upgradeTechnologyAttack:
    Con estos métodos, pretendemos actualizar nuestras tecnologías de ataque/defensa, pero
    antes tendremos que comprobar que tenemos recursos suficientes para actualizar dicha
    tecnología. En caso de no tener recursos suficientes, lanzaremos una excepción del tipo
    ResourceException, que comentaremos más adelante.
    Tenemos que tener en cuenta, que cada vez que subimos un nivel de tecnología, la siguiente
    actualización será un porcentaje establecido más caro.
    Por ejemplo, si pasar del nivel 1 de defensa al nivel 2 de defensa costase 100 de Hierro, y el
    porcentaje establecido de incremento de precio fuese un 10%, pasar del nivel 2 al nivel 3
    nos costaría 110 de hierro. Estos valores estaría previamente indicados en las características
    upgradeDefenseTechnologyIronCost, upgradeAttackTechnologyIronCost,
    upgradeDefenseTechnologyWoodCost, upgradeAttackTechnologyWoodCost = 0;
     */
    /*
    void newSwordsman(int n),
    void newSpearman(int n),
    void newCrossbow(int n),
    void newCannon(int n),
    void newArrowTower(int n),
    void newCatapult(int n),
    void newRocketLauncher(int n),
    void newMagician(int n),
    void newPriest(int n).
    Estos métodos servirán para añadir nuevas unidades militares a nuestro ejército army
    mencionado anteriormente.
    Estos métodos reciben un entero n que indica el número de unidades que queremos añadir, si
    no tenemos suficientes recursos para añadir las unidades que queremos, lanzará una
    excepción del tipo ResourceException indicando el mensaje informativo. Pero se añadirán
    todas las unidades posibles que permitan nuestros recursos.
    Es decir, si queremos añadir 10 swordsman, y sólo tenemos recursos para añadir 5, se
    lanzará una excepción del tipo ResourceException, pero se añadirán los 5 swordsman que
    podemos generar y se nos mostrará también un mensaje informativo indicando el número de
    swordsman que se han añadido.
    En el caso de crear magos, si no tenemos al menos una torre mágica, lanzaremos una
    excepción del tipo BuildingException.
    En el casod de crear sacerdotes, si no tenemos al menos una iglesia, lanzaremos una
    excepción del tipo BuildingException.
     */
    /*
    Void printStats(). Este método nos servirá para mostrar una visión del estado de nuestro
    planeta por consola, una posible salida cuando llamamos a este método podría ser
     */


}
