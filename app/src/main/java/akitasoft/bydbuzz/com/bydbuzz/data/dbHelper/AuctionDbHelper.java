package akitasoft.bydbuzz.com.bydbuzz.data.dbHelper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import akitasoft.bydbuzz.com.bydbuzz.data.contracts.AuctionContract;

/**
 * Created by marty on 1/18/2017.
 */

public class AuctionDbHelper {

    private SQLiteDatabase sql;

    public AuctionDbHelper(SQLiteDatabase sql) {
        this.sql = sql;
    }

    public void create() {
        final String SQL_CREATE_AUCTION_TABLE = "CREATE TABLE " +
                AuctionContract.AuctionEntry.TABLE_NAME + " (" +
                AuctionContract.AuctionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AuctionContract.AuctionEntry.COLUMN_EVENT_ID + " INTEGER, " +
                AuctionContract.AuctionEntry.COLUMN_SEAT_ID + " INTEGER, " +
                AuctionContract.AuctionEntry.COLUMN_EXPIRE + " TEXT " +
                ");";
        sql.execSQL(SQL_CREATE_AUCTION_TABLE);
    }

    public void truncate() {
        sql.execSQL("DELETE FROM " + AuctionContract.AuctionEntry.TABLE_NAME);
    }

    public void drop() {
        sql.execSQL("DROP TABLE IF EXISTS " + AuctionContract.AuctionEntry.TABLE_NAME);
    }

    public void load() {
        // TODO: INSERT into table
        insert(1,1,"expire one");
//        insert(1,2,"expire two");
//        insert(1,3,"expire three");
//        insert(2,1,"expire one");
//        insert(2,2,"expire two");
//        insert(2,3,"expire three");
    }

    public long insert(Integer event_id, Integer seat_id, String expire) {
        ContentValues cv = new ContentValues();
        cv.put(AuctionContract.AuctionEntry.COLUMN_EVENT_ID, event_id);
        cv.put(AuctionContract.AuctionEntry.COLUMN_SEAT_ID, seat_id);
        cv.put(AuctionContract.AuctionEntry.COLUMN_EXPIRE, expire);
        return sql.insert(AuctionContract.AuctionEntry.TABLE_NAME, null, cv);
    }
}
