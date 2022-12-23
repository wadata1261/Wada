package make;

import sourcehandling.ExecutedLineList;

public class twoCheck {
    public double codedis;
    public twoCheck(){this.codedis=getCodedis();}
    public void twoCheck(ExecutedLineList ell,ExecutedLineList ell2){
        ValueLogList vll = new ValueLogList();
        ValueLogList vll2 = new ValueLogList();
        ValueLogGet vlg = new ValueLogGet();
        ValueLogGet vlg2 = new ValueLogGet();
        MatchingList mlist=new MatchingList();
        CodeDis cd=new CodeDis();
        ell.show();
        vll = vlg.Print(ell);
        vll2 = vlg2.Print(ell2);
        for (ValueLog v : vll.getValueLogList()) {
            System.out.println("Main type=" + v.getType() + ",name=" + v.getName() + ",value=" + v.getValueLog());
        }
        for (ValueLog v2 : vll2.getValueLogList()) {
            System.out.println("Main2 type=" + v2.getType() + ",name=" + v2.getName() + ",value=" + v2.getValueLog());
        }
        VarSimList vslist=new VarSimList();
        VarSim vs=new VarSim();

        if(vll.getValueLogList().size()>vll2.getValueLogList().size()){
            ValueLogList v1=new ValueLogList();
            v1=vll;
            vll=vll2;
            vll2=v1;
        }
        Levenshtein l=new Levenshtein();
        //要素数が少ない方がv1,多い方がv2　v1の数だけマッチングを探す
        for(ValueLog v1: vll.getValueLogList()) {
            VarSimMatch vsm=new VarSimMatch(v1);
            for (ValueLog v2 : vll2.getValueLogList()) {
                vs.VarSim(v1, v2);

                VarSimLog vsl=new VarSimLog(v2,vs.getVarSim());
                vsm.Vsm(vsl);

                //if(vs.getVarSim()>=0.7){
                    Matching m=new Matching(v1,v2,vs.getVarSim());
                    mlist.addMlist(m);
                //}

                //System.out.println("////↓");
            }

            //vsm.Show();
            vslist.addVarSimList(vsm);
            for(VarSimLog v:vsm.getVlist()){
                System.out.println(v.getV2().getName());
            }
        }
        vslist.Show();
        setCodedis(vslist.getVarsimsum()/vll2.getValueLogList().size());


        for(Matching m:mlist.getMlist()){
            //System.out.println("MLIST:name1="+m.getV1().getName()+",name2="+m.getV2().getName()+",varsim="+m.getVarsim());
        }
        cd.vllCodeDis(vll,vll2);
        System.out.println("CodeDis="+cd.getCodedis());
    }

    public double getCodedis() {
        return codedis;
    }

    public void setCodedis(double codedis) {
        this.codedis = codedis;
    }

}
