package keleg.nespresso;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("SELECT * FROM transaction_table")
    List<Transaction> getAll();

    @Insert
    void insertAll(Transaction... transactions);

    @Insert
    void insert(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Update
    public void update(Transaction... transactions);
}
