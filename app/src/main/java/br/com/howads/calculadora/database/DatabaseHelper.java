package br.com.howads.calculadora.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.howads.calculadora.R;
import br.com.howads.calculadora.historico.Valores;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "historico";
    private static final String TABLE_VALORES = "valores";

    private static final String CREATE_TABLE_VALORES ="CREATE TABLE " + TABLE_VALORES + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "expressao VARCHAR(100), " +
            "resultado VARCHAR(100));";
    private static final String DROP_TABLE_VALORES = "DROP TABLE IF EXISTS " + TABLE_VALORES;



    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_VALORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_VALORES);
        onCreate(db);
    }

    /* incio Insert Valores */

    public long criarHistorico (Valores v) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("expressao", v.getExpressao());
        cv.put("resultado", v.getResultado());
        long id = db.insert(TABLE_VALORES, null, cv);
        db.close();
        return id;
    }

    public  void getAllValores (Context context, ListView lv){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "expressao", "resultado"};
        Cursor data = db.query(TABLE_VALORES,columns,null,null,null,null,"_id" );
        int[] to = {R.id.textView_id_historico, R.id.textView_expressao_historico, R.id.textView_resultado_historico};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
        R.layout.activity_valores, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);

        db.close();
    }
    /* fim */
}
