/*nombre de la conexion: civilizationewe*/

/*Usuario*/

/*
ALTER SESSION SET "_ORACLE_SCRIPT"=true;

CREATE USER AdminCivilization IDENTIFIED BY ewe;
GRANT ALL PRIVILEGES TO AdminCivilization;

COMMIT;
*/
-- Borra todas las tablas y secuencias si existen
/*
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE seq_id_log';
    EXECUTE IMMEDIATE 'DROP SEQUENCE seq_id_stat';
EXCEPTION
    WHEN OTHERS THEN
        NULL; 
END;
/

-- Borrar restricciones FK de las tablas si existen
BEGIN
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_AttackUnits_stats DROP CONSTRAINT fk_civilization_bat';
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_AttackUnits_stats DROP CONSTRAINT fk_un_ty_bat';
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_AttackUnits_stats DROP CONSTRAINT fk_numbattle_bat';
    EXECUTE IMMEDIATE 'DROP TABLE Battle_AttackUnits_stats';
    
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_DefenseUnits_stats DROP CONSTRAINT fk_civilization_bdef';
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_DefenseUnits_stats DROP CONSTRAINT fk_un_ty_bdef';
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_DefenseUnits_stats DROP CONSTRAINT fk_numbattle_bdef';
    EXECUTE IMMEDIATE 'DROP TABLE Battle_DefenseUnits_stats';
    
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_SpecialUnits_stats DROP CONSTRAINT fk_civilization_bsp';
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_SpecialUnits_stats DROP CONSTRAINT fk_un_ty_bsp';
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_SpecialUnits_stats DROP CONSTRAINT fk_numbattle_bsp';
    EXECUTE IMMEDIATE 'DROP TABLE Battle_SpecialUnits_stats';
    
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_log DROP CONSTRAINT fk_civilization_bl';
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_log DROP CONSTRAINT fk_numbattle_bl';
    EXECUTE IMMEDIATE 'DROP TABLE Battle_log';
    
    EXECUTE IMMEDIATE 'ALTER TABLE Enemy_attack_stats DROP CONSTRAINT fk_civilization_be';
    EXECUTE IMMEDIATE 'ALTER TABLE Enemy_attack_stats DROP CONSTRAINT fk_un_ty_be';
    EXECUTE IMMEDIATE 'ALTER TABLE Enemy_attack_stats DROP CONSTRAINT fk_numbattle_be';
    EXECUTE IMMEDIATE 'DROP TABLE Enemy_attack_stats';
    
    EXECUTE IMMEDIATE 'ALTER TABLE Battle_stats DROP CONSTRAINT fk_civilization_bs';
    EXECUTE IMMEDIATE 'DROP TABLE Battle_stats';
    
    EXECUTE IMMEDIATE 'ALTER TABLE SpecialUnits DROP CONSTRAINT fk_civilization_sp';
    EXECUTE IMMEDIATE 'ALTER TABLE SpecialUnits DROP CONSTRAINT fk_un_ty_sp';
    EXECUTE IMMEDIATE 'DROP TABLE SpecialUnits';
    
    EXECUTE IMMEDIATE 'ALTER TABLE DefenseUnits DROP CONSTRAINT fk_civilization_def';
    EXECUTE IMMEDIATE 'ALTER TABLE DefenseUnits DROP CONSTRAINT fk_un_ty_def';
    EXECUTE IMMEDIATE 'DROP TABLE DefenseUnits';
    
    EXECUTE IMMEDIATE 'ALTER TABLE AttackUnits DROP CONSTRAINT fk_civilization_atk';
    EXECUTE IMMEDIATE 'ALTER TABLE AttackUnits DROP CONSTRAINT fk_un_ty_atk';
    EXECUTE IMMEDIATE 'DROP TABLE AttackUnits';
    
    EXECUTE IMMEDIATE 'DROP TABLE Type_Unit';
    
    EXECUTE IMMEDIATE 'ALTER TABLE gui DROP CONSTRAINT fk_civilization_gui';
    EXECUTE IMMEDIATE 'DROP TABLE gui';
    
    EXECUTE IMMEDIATE 'DROP TABLE Civilizations_stats';
EXCEPTION
    WHEN OTHERS THEN
        NULL; 
END;
/

*/
-- Volver a crear todas las tablas
CREATE TABLE Civilizations_stats(
    civilization_id NUMBER PRIMARY KEY,
    c_name VARCHAR2(255) NOT NULL,
    wood NUMBER NULL,
    iron NUMBER NULL,
    food NUMBER NULL,
    mana NUMBER NULL,
    farm_amount NUMBER NULL,
    smithy_amount NUMBER NULL,
    carpentry_amount NUMBER NULL,
    church_amount NUMBER NULL,
    magicTower_amount NUMBER NULL,
    technology_attack_level NUMBER,
    technology_defense_level NUMBER,
    battles_count NUMBER
);

/*----------------------- TABLA PARA EL CONTROL DE GUI -----------------------------*/
CREATE TABLE gui (
    civilization_id NUMBER NOT NULL,
    x_position NUMBER NOT NULL,
    y_position NUMBER NOT NULL,
    is_occupied NUMBER(1) NOT NULL,
    ppindex NUMBER NOT NULL,
    structure_type VARCHAR(255),
    PRIMARY KEY (x_position, y_position),
    CONSTRAINT fk_civilization_gui FOREIGN KEY (civilization_id) REFERENCES Civilizations_stats(civilization_id)
    ON DELETE CASCADE
); 

CREATE TABLE Type_Unit(
    type_id NUMBER PRIMARY KEY NOT NULL,
    type_name VARCHAR(50)
);

/* Insert de los tipos de unidades */
INSERT INTO Type_Unit (type_id, type_name) VALUES (1, 'Swordsman');
INSERT INTO Type_Unit (type_id, type_name) VALUES (2, 'Spearman');
INSERT INTO Type_Unit (type_id, type_name) VALUES (3, 'Crossbow');
INSERT INTO Type_Unit (type_id, type_name) VALUES (4, 'Cannon');
INSERT INTO Type_Unit (type_id, type_name) VALUES (5, 'ArrowTower');
INSERT INTO Type_Unit (type_id, type_name) VALUES (6, 'Catapult');
INSERT INTO Type_Unit (type_id, type_name) VALUES (7, 'RocketLauncherTower');
INSERT INTO Type_Unit (type_id, type_name) VALUES (8, 'Magician');
INSERT INTO Type_Unit (type_id, type_name) VALUES (9, 'Priest');

CREATE TABLE AttackUnits(
    unit_id NUMBER NOT NULL,
    civilization_id NUMBER,
    unit_type NUMBER NOT NULL,
    armor NUMBER NULL,
    base_damage NUMBER NULL,
    experience NUMBER DEFAULT 0,
    sanctified NUMBER(1) DEFAULT 0,
    PRIMARY KEY (unit_id, civilization_id),
    CONSTRAINT fk_civilization_atk FOREIGN KEY (civilization_id) REFERENCES Civilizations_stats(civilization_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_un_ty_atk FOREIGN KEY (unit_type) REFERENCES Type_Unit(type_id)
    ON DELETE CASCADE
);

CREATE TABLE DefenseUnits(
    unit_id NUMBER NOT NULL,
    civilization_id NUMBER,
    unit_type NUMBER NOT NULL,
    armor NUMBER NULL,
    base_damage NUMBER NULL,
    experience NUMBER DEFAULT 0,
    sanctified NUMBER(1) DEFAULT 0,
    PRIMARY KEY (unit_id, civilization_id),
    CONSTRAINT fk_civilization_def FOREIGN KEY (civilization_id) REFERENCES Civilizations_stats(civilization_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_un_ty_def FOREIGN KEY (unit_type) REFERENCES Type_Unit(type_id)
    ON DELETE CASCADE
);

CREATE TABLE SpecialUnits(
    unit_id NUMBER NOT NULL,
    civilization_id NUMBER,
    unit_type NUMBER NOT NULL,
    armor NUMBER DEFAULT 0,
    base_damage NUMBER NULL,
    experience NUMBER DEFAULT 0,
    PRIMARY KEY (unit_id, civilization_id),
    CONSTRAINT fk_civilization_sp FOREIGN KEY (civilization_id) REFERENCES Civilizations_stats(civilization_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_un_ty_sp FOREIGN KEY (unit_type) REFERENCES Type_Unit(type_id)
    ON DELETE CASCADE
);

/*----------------------- REGISTROS DE BATALLAS -----------------------------*/

/* Informacion acerca de la batalla */
CREATE SEQUENCE seq_id_stat START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE TABLE Battle_stats(
    num_battle NUMBER DEFAULT seq_id_stat.NEXTVAL NOT NULL,
    civilization_id NUMBER NOT NULL,
    report CLOB NULL,
    PRIMARY KEY (num_battle, civilization_id),
    CONSTRAINT fk_civilization_bs FOREIGN KEY (civilization_id) REFERENCES Civilizations_stats(civilization_id)
    ON DELETE CASCADE
);

/* Registro de los textos de la batalla (secuencia para autoincrement) */
CREATE SEQUENCE seq_id_log START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE TABLE Battle_log (
    id_log NUMBER DEFAULT seq_id_log.NEXTVAL NOT NULL,
    civilization_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    log_entry CLOB NULL,
    PRIMARY KEY (id_log, civilization_id, num_battle),
    CONSTRAINT fk_civilization_bl FOREIGN KEY (civilization_id) REFERENCES Civilizations_stats(civilization_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_numbattle_bl FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id)
    ON DELETE CASCADE
);

/* Informacion de las unidades del tipo Attack en la batalla */
CREATE TABLE Battle_AttackUnits_stats (
    civilization_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    unit_type NUMBER NOT NULL,
    c_initial NUMBER NULL,
    death NUMBER NULL,
    PRIMARY KEY (civilization_id, num_battle, unit_type),
    CONSTRAINT fk_civilization_bat FOREIGN KEY (civilization_id) REFERENCES Civilizations_stats(civilization_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_un_ty_bat FOREIGN KEY (unit_type) REFERENCES Type_Unit(type_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_numbattle_bat FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id)
    ON DELETE CASCADE
);

/* Informacion de las unidades del tipo Defense en la batalla */
CREATE TABLE Battle_DefenseUnits_stats (
    civilization_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    unit_type NUMBER NOT NULL,
    c_initial NUMBER NULL,
    death NUMBER NULL,
    PRIMARY KEY (civilization_id, num_battle, unit_type),
    CONSTRAINT fk_civilization_bdef FOREIGN KEY (civilization_id) REFERENCES Civilizations_stats(civilization_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_un_ty_bdef FOREIGN KEY (unit_type) REFERENCES Type_Unit(type_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_numbattle_bdef FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id)
    ON DELETE CASCADE
);

/* Informacion de las unidades del tipo Special en la batalla */
CREATE TABLE Battle_SpecialUnits_stats(
    civilization_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    unit_type NUMBER NOT NULL,
    c_initial NUMBER NULL,
    death NUMBER NULL,
    PRIMARY KEY (civilization_id, num_battle, unit_type),
    CONSTRAINT fk_civilization_bsp FOREIGN KEY (civilization_id) REFERENCES Civilizations_stats(civilization_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_numbattle_bsp FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_un_ty_bsp FOREIGN KEY (unit_type) REFERENCES Type_Unit(type_id)
    ON DELETE CASCADE
);

/* Informacion de las unidades atacantes enemigas en la batalla */
CREATE TABLE Enemy_attack_stats (
    civilization_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    unit_type NUMBER NOT NULL,
    c_initial NUMBER,
    death NUMBER,
    PRIMARY KEY (civilization_id, num_battle, unit_type),
    CONSTRAINT fk_civilization_be FOREIGN KEY (civilization_id) REFERENCES Civilizations_stats(civilization_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_un_ty_be FOREIGN KEY (unit_type) REFERENCES Type_Unit(type_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_numbattle_be FOREIGN KEY (num_battle, civilization_id) REFERENCES Battle_stats(num_battle, civilization_id)
    ON DELETE CASCADE
);
