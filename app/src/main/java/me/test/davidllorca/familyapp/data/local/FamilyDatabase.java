package me.test.davidllorca.familyapp.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import me.test.davidllorca.familyapp.data.model.Member;

/**
 * Local database implemented with {@link RoomDatabase}. Android Architecture Components Library.
 * <p>
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/11/17.
 */
@Database(entities = {Member.class}, version = 1)
public abstract class FamilyDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "family";

    // Singleton implementation
    private static final Object LOCK = new Object();
    private static volatile FamilyDatabase sInstance;

    public abstract MemberDao memberDao();

    /**
     * Return single instance of local database.
     *
     * @param context Context
     * @return {@link FamilyDatabase} instance.
     */
    public static FamilyDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            FamilyDatabase.class, FamilyDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }

}
