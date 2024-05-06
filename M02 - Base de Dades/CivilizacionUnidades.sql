DROP DATABASE IF EXISTS civilizations;
CREATE DATABASE IF NOT EXISTS civilizations;
USE civilizations;

CREATE TABLE IF NOT EXISTS Civilization_stats (
    civilization_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    wood_amount INTEGER,
    iron_amount INTEGER,
    food_amount INTEGER,
    mana_amount INTEGER,
    magicTower_counter INTEGER,
    church_counter INTEGER,
    farm_counter INTEGER,
    smithy_counter INTEGER,
    carpentry_counter INTEGER,
    technology_defense_level INTEGER,
    technology_attack_level INTEGER,
    battles_counter INTEGER
);

CREATE TABLE IF NOT EXISTS attack_unit_stats (
    civilization_id INTEGER NOT NULL,
    unit_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type ENUM('swordman', 'spearman', 'crossbow', 'cannon'),
    armor INTEGER,
    base_damage INTEGER,
    experience INTEGER,
    sanctified BOOL DEFAULT FALSE,
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);


CREATE TABLE IF NOT EXISTS defense_units_stats (
    civilization_id INTEGER NOT NULL,
    unit_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type ENUM('ArrowTower', 'Catapult', 'RocketLauncherTower'),
    armor INTEGER,
    base_damage INTEGER,
    experience INTEGER,
    sanctified BOOL DEFAULT FALSE,
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);


CREATE TABLE IF NOT EXISTS special_units_stats (
    civilization_id INTEGER NOT NULL,
    unit_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type ENUM('Magician', 'Priest'),
    armor INTEGER,
    base_damage INTEGER,
    experience INTEGER,
    FOREIGN KEY (civilization_id) REFERENCES Civilization_stats(civilization_id)
);
