package com.revature.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The type Db tables this class creates the tables
 * if they don't exist its this class that is responsible for creating backend database.
 */
public class DBTables {

    /**
     * Create user table.
     *
     * @param conn to conn
     */
    public static void createUserTable(Connection conn){
        String tablesSql = "CREATE TABLE IF NOT EXISTS \"users\"\n" +
                "(\n" +
                "    id SERIAL NOT NULL,\n" +
                "    first_name character varying(255),\n" +
                "    last_name character varying(255),\n" +
                "    email character varying(255),\n" +
                "    pass_word character varying(255),\n" +
                "    is_manager boolean DEFAULT false,\n" +
                "    PRIMARY KEY (id)\n" +
                ");\n";


        try {
            PreparedStatement pst1 = conn.prepareStatement(tablesSql);
            pst1.execute();
            pst1.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * Create flashcard table.
     *
     * @param conn to conn
     */
    public static void createFlashcardTable(Connection conn){
        String tablesSql =
                "CREATE TABLE IF NOT EXISTS \"flashcards-demo\".\"flashcards\"\n" +
                        "(\n" +
                        "    id SERIAL NOT NULL,\n" +
                        "    question character varying(255),\n" +
                        "    answer character varying(255),\n" +
                        "    creator_id int NOT NULL,\n" +
                        "    FOREIGN KEY (creator_id) REFERENCES public.\"users\"(id),\n" +
                        "    PRIMARY KEY (id)\n" +
                        ");";


        try {
            PreparedStatement pst1 = conn.prepareStatement(tablesSql);
            pst1.execute();
            pst1.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Create tickets table.
     *
     * @param conn to conn
     */
    public static void createTicketsTable(Connection conn){
        String tablesSql = "\n" +
                "CREATE TABLE IF NOT EXISTS \"flashcards-demo\".\"tickets\"\n" +
                "(\n" +
                "    id SERIAL NOT NULL,\n" +
                "    userid int NOT NULL,\n" +
                "    description character varying(255),\n" +
                "    approved boolean DEFAULT false,\n" +
                "    denied boolean DEFAULT false,\n" +
                "    amount double precision default 0,\n" +
                "    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),\n" +
                "    FOREIGN KEY (userid) REFERENCES public.\"users\"(id),\n" +
                "    PRIMARY KEY (id)\n" +
                ");\n" +
                "\n";


        try {
            PreparedStatement pst1 = conn.prepareStatement(tablesSql);
            pst1.execute();
            pst1.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
