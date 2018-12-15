package keleg.nespresso;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "transaction_table",
        foreignKeys = @ForeignKey(entity = NespressoCapsule.class,
        parentColumns = "name",
        childColumns = "capsule_name",
        onDelete = ForeignKey.CASCADE))
public class Transaction {
    @PrimaryKey
    @NonNull
    int transaction_id;
    @NonNull
    String capsule_name;
    @NonNull
    int quantity;

    public Transaction(int transaction_id, String capsule_name, int quantity)
    {
        this.transaction_id = transaction_id;
        this.capsule_name = capsule_name;
        this.quantity = quantity;
    }
}
