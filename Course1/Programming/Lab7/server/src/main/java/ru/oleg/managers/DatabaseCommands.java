package ru.oleg.managers;

public class DatabaseCommands {
    public static final String allTablesCreation = """
            CREATE TYPE WEAPON_TYPE AS ENUM(
                'HEAVY_BOLTGUN',
                'GRAV_GUN',
                'HEAVY_FLAMER'
            );
            CREATE TABLE IF NOT EXISTS spacemarine (
                id SERIAL PRIMARY KEY,
                name TEXT NOT NULL ,
                cord_x NUMERIC NOT NULL,
                cord_y NUMERIC NOT NULL ,
                creation_date TIMESTAMP NOT NULL ,
                health FLOAT NOT NULL ,
                achievements TEXT NOT NULL ,
                height FLOAT NOT NULL ,
                weapon_type WEAPON_TYPE NOT NULL ,
                chapter_name TEXT NOT NULL ,
                chapter_marines_count FLOAT NOT NULL ,
                owner_login TEXT NOT NULL
            );
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                login TEXT,
                password TEXT,
                salt TEXT
            );
            """;

    public static final String addObject = """
            INSERT INTO spacemarine(name, cord_x, cord_y, creation_date,health,achievements,height,weapon_type,chapter_name,chapter_marines_count, owner_login)
            VALUES (?,?,?,?,?,?,?,?,?,?,?)
            RETURNING id;
            """;

    public static final String updateUserObject = """
            UPDATE spacemarine
            SET (name, cord_x, cord_y, creation_date,health,achievements,height,weapon_type,chapter_name,chapter_marines_count)
             = (?, ?, ?, ?,?,?,?,?,?,?)
            WHERE (id = ?) AND (owner_login = ?)
            RETURNING id;
            """;

    public static final String getAllObjects = """
            SELECT * FROM spacemarine;
            """;

    public static final String addUser = """
            INSERT INTO users(login, password, salt) VALUES (?, ?, ?);""";

    public static final String getUser = """
            SELECT * FROM users WHERE (login = ?);""";


    public static final String deleteUserOwnedObjects = """
            DELETE FROM spacemarine WHERE (owner_login = ?) AND (id = ?) RETURNING id;
            """;

    public static final String deleteUserObject = """
            DELETE FROM spacemarine WHERE (owner_login = ?) AND (id = ?) RETURNING id;
            """;


}
