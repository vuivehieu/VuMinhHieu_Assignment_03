package vuminhhieu.aprotrain.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "NoteDatabase";
    private static final int DB_VERSION = 1;
    private static final String T_NAME = "tbl_note";

    private static final String KEY_ID = "note_id";
    private static final String KEY_NOTE = "note";
    private static final String KEY_TIME = "time";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)", T_NAME, KEY_ID, KEY_NOTE, KEY_TIME);
        sqLiteDatabase.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_note_database = String.format("DROP TABLE IF EXISTS %s", T_NAME);
        sqLiteDatabase.execSQL(drop_note_database);

        onCreate(sqLiteDatabase);
    }

    public List<Note> getAllNote() {
        List<Note>  notes = new ArrayList<>();
        String query = "SELECT * FROM " + T_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Note student = new Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            notes.add(student);
            cursor.moveToNext();
        }
        return notes;
    }

    public void addTask(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTE, note.getNote());
        values.put(KEY_TIME, note.getTime());

        db.insert(T_NAME, null, values);
        db.close();
    }

    public void updateTask(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTE, note.getNote());
        values.put(KEY_TIME, note.getTime());

        db.update(T_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(note.getId()) });
        db.close();
    }

    public void deleteTask(String noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(T_NAME, KEY_ID + " = ?", new String[] { String.valueOf(noteId) });
        db.close();
    }
}
