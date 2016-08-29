package main;

/**
 *
 * @author Vinicio
 */
public class Relation {

    private String A;
    private String Type;
    private String B;

    public Relation(String A, String Type, String B) {
        this.A = A;
        this.Type = Type;
        this.B = B;
    }

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getB() {
        return B;
    }

    public void setB(String B) {
        this.B = B;
    }
}
