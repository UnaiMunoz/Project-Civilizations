
CREATE TABLE Civilization_attack_stats (
    civilization_id NUMBER(10) NOT NULL,
    num_battle NUMBER(10) NOT NULL,
    type VARCHAR2(50) NOT NULL,
    initial NUMBER(10),
    drops NUMBER(10),
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

CREATE TABLE Civilization_defense_stats (
    civilization_id NUMBER(10) NOT NULL,
    num_battle NUMBER(10) NOT NULL,
    type VARCHAR2(50) NOT NULL,
    initial NUMBER(10),
    drops NUMBER(10),
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

CREATE TABLE Civilization_special_stats (
    civilization_id NUMBER(10) NOT NULL,
    num_battle NUMBER(10) NOT NULL,
    type VARCHAR2(50) NOT NULL,
    initial NUMBER(10),
    drops NUMBER(10),
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

CREATE TABLE Battle_stats (
    civilization_id NUMBER(10) NOT NULL,
    num_battle NUMBER(10) NOT NULL,
    wood_acquired NUMBER(10),
    iron_acquired NUMBER(10),
    PRIMARY KEY (civilization_id, num_battle),
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);

CREATE TABLE Battle_log (
    civilization_id NUMBER(10) NOT NULL,
    num_battle NUMBER(10) NOT NULL,
    num_line NUMBER(10) NOT NULL,
    log_entry CLOB,
    PRIMARY KEY (civilization_id, num_battle, num_line),
    FOREIGN KEY (civilization_id, num_battle) REFERENCES Battle_stats(civilization_id, num_battle)
);

CREATE TABLE Enemy_attack_stats (
    civilization_id NUMBER(10) NOT NULL,
    num_battle NUMBER(10) NOT NULL,
    type VARCHAR2(50) NOT NULL,
    initial NUMBER(10),
    drops NUMBER(10),
    PRIMARY KEY (civilization_id, num_battle, type),
    FOREIGN KEY (civilization_id, num_battle) REFERENCES Battle_stats(civilization_id, num_battle)
);
