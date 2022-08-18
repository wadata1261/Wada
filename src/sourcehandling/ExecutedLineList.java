package sourcehandling;

import com.sun.jdi.LocalVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecutedLineList {
    private List<ExecutedLine> executedLineList;

    public ExecutedLineList() {
        this.executedLineList = new ArrayList<>();
    }

    //行を追加し、その行が実行行リストの何番目か(index)を返す
    public int addLine(int lineNumber, String methodName, Map<LocalVariable, Object> variables) {
        executedLineList.add(new ExecutedLine(lineNumber, methodName, variables));
        return executedLineList.size()-1;
    }

    public int addLine(ExecutedLine executedLine) {
        executedLineList.add(executedLine);
        return executedLineList.size()-1;
    }

    //TODO 同名で型が違う変数の場合を考慮する必要あり
    // 変数名#型名などの形で名前を指定すれば、運用でカバーは可能
    public Object getValue(int executeIndex, String name, String type){
        return this.executedLineList.get(executeIndex).getValue(name, type);
    }

    public Object getValue(int executeIndex, String name, String type, int idx){
        return this.executedLineList.get(executeIndex).getValue(name, type, idx);
    }

    public Object getValue(int executeIndex, String name, String type, int idx1, int idx2){
        return this.executedLineList.get(executeIndex).getValue(name, type, idx1, idx2);
    }

    public void show(){
        System.out.println("Executed Line List****************");
        for(ExecutedLine el : this.executedLineList){
            el.show();
        }
        System.out.println("****************");
    }

}
