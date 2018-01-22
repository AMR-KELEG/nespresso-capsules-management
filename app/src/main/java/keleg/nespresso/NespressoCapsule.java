package keleg.nespresso;


import android.support.annotation.NonNull;

public class NespressoCapsule implements Comparable<NespressoCapsule>{
    int no_of_capsules;
    String name;
    int image_id;
    public  NespressoCapsule(int no_of_capsules, String name, int image_id)
    {
        this.no_of_capsules = no_of_capsules;
        this.name = name;
        this.image_id = image_id;
    }

    @Override
    public int compareTo(NespressoCapsule capsule) {
        return (capsule.no_of_capsules - this.no_of_capsules);
    }
}
