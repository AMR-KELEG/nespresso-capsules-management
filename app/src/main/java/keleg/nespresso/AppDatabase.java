package keleg.nespresso;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {NespressoCapsule.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NespressoCapsuleDao capsuleDao();
    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "capsules_database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}