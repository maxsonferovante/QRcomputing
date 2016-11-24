package participant;

import java.io.Serializable;

public final class Participant implements Serializable {
    private String mane;
    private int enrollament;

    public Participant(String name, int enrollment) {
        this.mane = name;
        this.enrollament = enrollment;
    }

    public String getMane() {
        return mane;
    }

    public void setMane(String mane) {
        this.mane = mane;
    }

    public int getEnrollament() {
        return enrollament;
    }

    public void setEnrollament(int enrollament) {
        this.enrollament = enrollament;
    }
}
