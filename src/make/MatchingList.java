package make;

import make.Matching;

import java.util.ArrayList;
import java.util.List;

public class MatchingList {
    public List<Matching> mlist;
    public MatchingList(){
        this.mlist=new ArrayList<>();
    }

    public void addMlist(Matching m){
        this.mlist.add(m);
    }

    public List<Matching> getMlist(){return this.mlist;}

}
