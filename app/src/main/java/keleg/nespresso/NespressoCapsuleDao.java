package keleg.nespresso;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NespressoCapsuleDao {
    @Query("SELECT * FROM capsule_table")
    List<NespressoCapsule> getAll();

    @Query("SELECT * FROM capsule_table WHERE name IN (:capsuleNames)")
    List<NespressoCapsule> loadAllByNames(String[] capsuleNames);

    @Insert
    void insertAll(NespressoCapsule... capsules);

    @Insert
    void insert(NespressoCapsule capsule);

    @Delete
    void delete(NespressoCapsule capsule);

    @Update
    public void update(NespressoCapsule... capsules);

    @Query("UPDATE capsule_table SET intensity = :intensity WHERE name = :name")
    int update_intensity(String name, int intensity);
}