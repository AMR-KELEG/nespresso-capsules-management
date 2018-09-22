package keleg.nespresso;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "capsule_table")
public class NespressoCapsule implements Comparable<NespressoCapsule>{
    @PrimaryKey
    @NonNull
    String name;
    @NonNull
    int no_of_capsules;
    @NonNull
    int image_id;
    @NonNull
    int intensity;

    @NonNull
    public int getNo_of_capsules() {
        return no_of_capsules;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public int getImage_id() {
        return image_id;
    }

    @NonNull
    public int getIntensity() {
        return intensity;
    }

    public  NespressoCapsule(int no_of_capsules, String name, int image_id, int intensity)
    {
        this.no_of_capsules = no_of_capsules;
        this.name = name;
        this.image_id = image_id;
        this.intensity = intensity;
    }

    @Override
    public int compareTo(NespressoCapsule capsule) {
        if (capsule.no_of_capsules != this.no_of_capsules){
            return (capsule.no_of_capsules - this.no_of_capsules);
        }
        else{
            return this.name.compareTo(capsule.getName());
        }
    }

    public void incrementInventory(int val){
        this.no_of_capsules += val;
    }

    public void decrementInventory(int val) throws ArithmeticException
    {
        if (this.no_of_capsules == 0)
        {
            throw new ArithmeticException(this.name + ": Empty Inventory!");
        }
        this.no_of_capsules-= val;
    }
}
