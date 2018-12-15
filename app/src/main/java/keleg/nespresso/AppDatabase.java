package keleg.nespresso;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {NespressoCapsule.class, Transaction.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NespressoCapsuleDao capsuleDao();
    public abstract TransactionDao transactionDao();
    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "capsules_database").allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE capsule_table"
                    + " ADD COLUMN intensity INTEGER"
                    + " DEFAULT 0 NOT NULL"
            );
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE transaction_table (transaction_id INTEGER NOT NULL," +
                            "capsule_name TEXT NOT NULL," +
                            "quantity INTEGER NOT NULL," +
                            "PRIMARY KEY(transaction_id)," +
                            "FOREIGN KEY(capsule_name) REFERENCES capsule_table(name)" +
                            "ON DELETE CASCADE)");
        }
    };
}