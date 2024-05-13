package com.civilizations;
import java.util.ArrayList;

public class Civilization implements Variables {
    int technologyDefense;
    int technologyAttack;
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
    ArrayList<MilitaryUnit>[] army;

    // Constructor
    public Civilization(int technologyDefense, int technologyAttack, int wood, int iron, int food, int mana, int magicTower, int church, int farm, int smithy, int carpentry, int battles) {
        this.technologyDefense = 0;
        this.technologyAttack = 0;
        this.wood = 0;
        this.iron = 0;
        this.food = 0;
        this.mana = 0;
        this.magicTower = 0;
        this.church = 0;
        this.farm = 0;
        this.smithy = 0;
        this.carpentry = 0;
        this.battles = 0;

        this.army = new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            this.army[i] = new ArrayList<>();
        }
    }

    // Getters and Setters
    public int getTechnologyDefense() {
        return this.technologyDefense;
    }

    public void setTechnologyDefense(int technologyDefense) {
        this.technologyDefense = technologyDefense;
    }

    public int getTechnologyAttack() {
        return this.technologyAttack;
    }

    public void setTechnologyAttack(int technologyAttack) {
        this.technologyAttack = technologyAttack;
    }

    public int getWood() {
        return this.wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getIron() {
        return this.iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getFood() {
        return this.food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getMana() {
        return this.mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMagicTower() {
        return this.magicTower;
    }

    public void setMagicTower(int magicTower) {
        this.magicTower = magicTower;
    }

    public int getChurch() {
        return this.church;
    }

    public void setChurch(int church) {
        this.church = church;
    }

    public int getFarm() {
        return this.farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public int getSmithy() {
        return this.smithy;
    }

    public void setSmithy(int smithy) {
        this.smithy = smithy;
    }

    public int getCarpentry() {
        return this.carpentry;
    }

    public void setCarpentry(int carpentry) {
        this.carpentry = carpentry;
    }

    public int getBattles() {
        return this.battles;
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    // Other Methods
    public void newChurch() throws ResourceException {
        if (food < FOOD_COST_CHURCH && wood < WOOD_COST_CHURCH && iron < IRON_COST_CHURCH) {
            throw new ResourceException("No tens materials suficients");
        } else {
            church++;
            System.out.println("Has construit una esglesia, tens " + church + " esglesies");
        }
    }

    public void newMagicTower() throws ResourceException {
        if (food < FOOD_COST_MAGICTOWER && wood < WOOD_COST_MAGICTOWER && iron < IRON_COST_MAGICTOWER) {
            throw new ResourceException("No tens materials suficients");
        } else {
            magicTower++;
            System.out.println("Has construit una torre màgica, tens " + magicTower + " Torres magiques");
        }
    }

    public void newFarm() throws ResourceException {
        if (food < FOOD_COST_FARM && wood < WOOD_COST_FARM && iron < IRON_COST_FARM) {
            throw new ResourceException("No tens materials suficients");
        } else {
            farm++;
            System.out.println("Has construit una granja, tens " + farm + " granges");
        }
    }

    public void newCarpentry() throws ResourceException {
        if (food < FOOD_COST_CARPENTRY && wood < WOOD_COST_CARPENTRY && iron < IRON_COST_CARPENTRY) {
            throw new ResourceException("No tens materials suficients");
        } else {
            carpentry++;
            System.out.println("Has construit una fusteria, tens " + carpentry + " fusterias");
        }
    }

    public void newSmithy() throws ResourceException {
        if (food < FOOD_COST_SMITHY && wood < WOOD_COST_SMITHY && iron < IRON_COST_SMITHY) {
            throw new ResourceException("No tens materials suficients");
        } else {
            smithy++;
            System.out.println("Has construit una ferraria, tens " + smithy + " ferraries");
        }
    }

    public void upgradeTechnologyDefense() throws ResourceException {
        if (technologyAttack < 1 && iron > UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST) {
            iron -= UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST;
            technologyDefense++;
            System.out.println("Has pujat les defenses de nivell");
        } else if (technologyDefense > 1) {
            double CosteMejora = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST;
            double porcentajeAumento = 0.10; 
            for (int i = 2; i <= technologyAttack; i++) {
                CosteMejora += UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST * porcentajeAumento;
            }
            if (iron >= CosteMejora) {
                iron -= CosteMejora;
                technologyDefense++; 
                System.out.println("Has pujat les defenses de nivell");
            } else {
                throw new ResourceException("No tens ferro suficient");
            }
        } else {
            throw new ResourceException("No tens ferro suficient");
        }
    };


    public void upgradeTechnologyAttack() throws ResourceException {
        if (technologyAttack < 1 && iron > UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST) {
            iron -= UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST;
            technologyAttack++;
            System.out.println("Has pujat els atacants de nivell");
        } else if (technologyAttack > 1) {
            double CosteMejora = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST;
            double porcentajeAumento = 0.10; 
            for (int i = 2; i <= technologyAttack; i++) {
                CosteMejora += UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST * porcentajeAumento;
            }
            if (iron >= CosteMejora) {
                iron -= CosteMejora;
                technologyAttack++; 
                System.out.println("Has pujat els atacants de nivell");
            } else {
                throw new ResourceException("No tens ferro suficient");
            }
        } else {
            throw new ResourceException("No tens ferro suficient");
        }
    }
};


    
    /*Con estos métodos, pretendemos actualizar nuestras tecnologías de ataque/defensa, pero
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
    
    /*public void newSwordsman(int n){




    }*/
        //FOOD_COST_SWORDSMAN,WOOD_COST_SWORDSMAN,IRON_COST_SWORDSMAN,MANA_COST_SWORDSMAN;


    
    
    
    /*void newSpearman(int n),
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