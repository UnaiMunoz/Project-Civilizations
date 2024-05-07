use civilizations;
/*DROP TABLE Civilization_attack_stats;
DROP TABLE Civilization_defense_stats;
DROP TABLE Civilization_special_stats;
DROP TABLE Battle_stats;
DROP TABLE Battle_log;
DROP TABLE Enemy_attack_stats;*/

CREATE TABLE Civilization_attack_stats (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    initial INT,
    drops INT,
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

CREATE TABLE Civilization_defense_stats (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    initial INT,
    drops INT,
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

CREATE TABLE Civilization_special_stats (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    initial INT,
    drops INT,
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

CREATE TABLE Battle_stats (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    wood_acquired INT,
    iron_acquired INT,
    PRIMARY KEY (civilization_id, num_battle),
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

CREATE TABLE Battle_log (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    num_line INT NOT NULL,
    log_entry TEXT,
    PRIMARY KEY (civilization_id, num_battle, num_line),
    FOREIGN KEY (civilization_id, num_battle) REFERENCES Battle_stats(civilization_id, num_battle)
);

CREATE TABLE Enemy_attack_stats (
    civilization_id INT NOT NULL,
    num_battle INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    initial INT,
    drops INT,
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id, num_battle) REFERENCES Battle_stats(civilization_id, num_battle)
);
