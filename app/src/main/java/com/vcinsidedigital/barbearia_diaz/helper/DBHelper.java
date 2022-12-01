package com.vcinsidedigital.barbearia_diaz.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    public static int VERSION = 1;
    public static String NAME_DB = "DB_barbearia_diaz";
    public static String TABELA_MESES = "meses";
    public static String TABELA_AGENDA = "agenda";
    public static String TABELA_DESPESAS = "despesas";
    public static String TABELA_LUCROS = "lucros";
    public static String TABELA_ANOS = "anos";
    public static String TABELA_SEMANAS = "semanas";

    public DBHelper(@Nullable Context context)
    {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql_mes = "CREATE TABLE IF NOT EXISTS " + TABELA_MESES
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " mes TEXT NOT NULL ,"
                + " ano INTEGER NOT NULL ) ; ";

        String sql_ano = "CREATE TABLE IF NOT EXISTS " + TABELA_ANOS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " ano INTEGER NOT NULL ); ";

        String sql_semanas = "CREATE TABLE IF NOT EXISTS " + TABELA_SEMANAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " semana TEXT NOT NULL, "
                + " mesID INTEGER NOT NULL) ;";

        String sql_agenda = "CREATE TABLE IF NOT EXISTS " + TABELA_AGENDA
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " dia TEXT NOT NULL, "
                + " data TEXT NOT NULL, "
                + " hora TEXT NOT NULL, "
                + " preco DECIMAL NOT NULL, "
                + " cliente NOT NULL, "
                + " obs TEXT NOT NULL, "
                + " semana_id INTEGER NOT NULL );";

        String sql_despesas = "CREATE TABLE IF NOT EXISTS " + TABELA_DESPESAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " titulo TEXT NOT NULL, "
                + " valor DECIMAL NOT NULL, "
                + " semana_id INTEGER NOT NULL );";

        String sql_lucros = "CREATE TABLE IF NOT EXISTS " + TABELA_LUCROS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " dia TEXT NOT NULL, "
                + " titulo TEXT NOT NULL, "
                + " valor DECIMAL NOT NULL, "
                + " gasto DECIMAL NOT NULL,"
                + " semana_id INTEGER NOT NULL, "
                + " quantidade INTEGER NOT NULL); ";


        try{
            db.execSQL(sql_mes);
            db.execSQL(sql_ano);
            db.execSQL(sql_semanas);
            db.execSQL(sql_agenda);
            db.execSQL(sql_lucros);
            db.execSQL(sql_despesas);
        }catch (Exception e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        String sql_mes = "DROP TABLE IF EXISTS " + TABELA_MESES + " ;" ;
        try{
            db.execSQL(sql_mes);
        }catch (Exception e){

        }
    }

    public static boolean deleteDatabse(Context context){
        try {
            context.deleteDatabase(DBHelper.NAME_DB);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
