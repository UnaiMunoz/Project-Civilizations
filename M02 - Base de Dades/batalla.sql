
CREATE TABLE civilization_attack_stats (
    civilization_id NUMBER(5) NOT NULL,
    num_battle NUMBER(5) NOT NULL,
    type_stats VARCHAR2(50) NOT NULL,
    initial_stats NUMBER(5),
    drops NUMBER(5),
    CONSTRAINT pk_civilization_attack_stats PRIMARY KEY (civilization_id, num_battle, type_stats),
    CONSTRAINT fk_civ_attack_stats_civ FOREIGN KEY (civilization_id) REFERENCES civilization_stats (civilization_id)
);


CREATE TABLE civilization_defense_stats (
    civilization_id NUMBER(5) NOT NULL,
    num_battle NUMBER(5) NOT NULL,
    type_stats VARCHAR2(50) NOT NULL,
    initial_stats NUMBER(5),
    drops NUMBER(5),
    CONSTRAINT pk_civilization_defense_stats PRIMARY KEY (civilization_id, num_battle, type_stats),
    CONSTRAINT fk_civ_defense_stats_civ FOREIGN KEY (civilization_id) REFERENCES civilization_stats (civilization_id)
);



CREATE TABLE civilization_special_stats (
    civilization_id NUMBER(5) NOT NULL,
    num_battle NUMBER(5) NOT NULL,
    type_stats VARCHAR2(50) NOT NULL,
    initial_stats NUMBER(5),
    drops NUMBER(5),
    CONSTRAINT pk_civilization_special_stats PRIMARY KEY (civilization_id, num_battle, type_stats),
    CONSTRAINT fk_civ_special_stats_civ FOREIGN KEY (civilization_id) REFERENCES civilization_stats (civilization_id)
);



CREATE TABLE enemy_attack_stats (
    civilization_id NUMBER(10) NOT NULL,
    num_battle NUMBER(10) NOT NULL,
    type_stats VARCHAR2(50) NOT NULL,
    initial_stats NUMBER(10),
    drops NUMBER(10),
    CONSTRAINT pk_enemy_attack_stats PRIMARY KEY (civilization_id, num_battle, type_stats),
    CONSTRAINT fk_enemy_attack_stats_battle FOREIGN KEY (civilization_id, num_battle) REFERENCES battle_stats (civilization_id, num_battle)
);




-- Crear la tabla battle_stats
CREATE TABLE battle_stats (
    civilization_id NUMBER(10) NOT NULL,
    num_battle NUMBER(10) NOT NULL,
    wood_acquired NUMBER(10),
    iron_acquired NUMBER(10),
    CONSTRAINT pk_battle_stats PRIMARY KEY (civilization_id, num_battle),
    CONSTRAINT fk_battle_stats_civilization FOREIGN KEY (civilization_id) REFERENCES civilization_stats (civilization_id)
);

-- Crear la tabla battle_log
CREATE TABLE battle_log (
    civilization_id NUMBER(10) NOT NULL,
    num_battle NUMBER(10) NOT NULL,
    num_line NUMBER(10) NOT NULL,
    log_entry VARCHAR(20000),
    CONSTRAINT pk_battle_log PRIMARY KEY (civilization_id, num_line),
    CONSTRAINT fk_battle_log_battle FOREIGN KEY (civilization_id, num_battle) REFERENCES battle_stats (civilization_id, num_battle)
);


