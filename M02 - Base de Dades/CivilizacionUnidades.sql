
CREATE TABLE Civilization_stats (
    civilization_id NUMBER(10) NOT NULL PRIMARY KEY,
    name VARCHAR2(20),
    wood_amount NUMBER(10),
    iron_amount NUMBER(10),
    food_amount NUMBER(10),
    mana_amount NUMBER(10),
        
    magicTower_counter NUMBER(10),  
    church_counter NUMBER(10),
    farm_counter NUMBER(10),
    smithy_counter NUMBER(10),
    carpentry_counter NUMBER(10),
        
    technology_defense_level NUMBER(10),
    technology_attack_level NUMBER(10),
    battles_counter NUMBER(10)
);

CREATE SEQUENCE civilization_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE attack_unit_stats (
    civilization_id NUMBER(10) NOT NULL,
    unit_id NUMBER(10) NOT NULL,
    type_unit VARCHAR2(10),
    armor NUMBER(10),
    base_damage NUMBER(10),
    experience NUMBER(10),
    sanctified BOOL DEFAULT FALSE,
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    CONSTRAINT pk_attack PRIMARY KEY (civilization_id, unit_id),
    CONSTRAINT attack CHECK (type_unit IN ('swordman', 'spearman', 'crossbow', 'cannon'))
);

CREATE TABLE defense_units_stats (
    civilization_id NUMBER(10) NOT NULL,
    unit_id NUMBER(10) NOT NULL,
    type_unit VARCHAR2(10),
    armor NUMBER(10),
    base_damage NUMBER(10),
    experience NUMBER(10),
    sanctified BOOL DEFAULT FALSE,
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    CONSTRAINT pk_defense PRIMARY KEY (civilization_id, unit_id),
    CONSTRAINT defense CHECK (type_unit IN ('ArrowTower', 'Catapult', 'RocketLauncherTower'))
);

CREATE TABLE special_units_stats (
    civilization_id NUMBER(10) NOT NULL,
    unit_id NUMBER(10) NOT NULL,
    type_unit VARCHAR2(10),
    armor NUMBER(10),
    base_damage NUMBER(10),
    experience NUMBER(10),
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id),
    CONSTRAINT pk_special PRIMARY KEY (civilization_id, unit_id),
    CONSTRAINT special CHECK (type_unit IN ('Magician', 'Priest'))
);
