package sourcehandling;

import com.sun.jdi.ArrayReference;
import com.sun.jdi.LocalVariable;
import com.sun.jdi.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Procedure{
    private List<Map<LocalVariable, Value>> procedure;

    public Procedure(){
        this.procedure = new ArrayList<>();
    }

    public List<Map<LocalVariable, Value>> getProcedure() {
        return procedure;
    }

    public void show(){
        for(int i = 0 ; i < procedure.size() ; i++) {
            for (Map.Entry<LocalVariable, Value> e : procedure.get(i).entrySet()) {
                if(e.getValue() instanceof ArrayReference){
                    ArrayReference ar = (ArrayReference)(e.getValue());
                    for(Value v : ar.getValues()){
                        if(v instanceof ArrayReference){
                            ArrayReference ar2 = ((ArrayReference)v);
                            for(Value v2 : ar2.getValues()){
                                System.out.println(v2.type() + " : " + v2);
                            }
                        }
                        else {
                            System.out.println(v.type() + " : " + v);
                        }
                    }
                }
                else {
                    System.out.print(e.getKey() + ":" + e.getValue() + ", ");
                }
            }
            System.out.println();
        }
    }
}
