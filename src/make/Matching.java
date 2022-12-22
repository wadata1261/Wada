package make;

public class Matching {
    public ValueLog v1;
    public ValueLog v2;
    public double varsim;
    public Matching(ValueLog v1,ValueLog v2,double varsim){
        this.v1=v1;
        this.v2=v2;
        this.varsim=varsim;
    }

    public ValueLog getV1() {
        return v1;
    }

    public ValueLog getV2() {
        return v2;
    }

    public double getVarsim() {
        return varsim;
    }
}
