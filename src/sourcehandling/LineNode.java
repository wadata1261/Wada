package sourcehandling;

import com.github.javaparser.ast.Node;

public class LineNode {
    protected int lineNumber;
    protected String operationType;
    protected Node n;


    public LineNode(int lineNumber, String operationType, Node n) {
        this.lineNumber = lineNumber;
        this.operationType = operationType;
        this.n = n;
    }



    public LineNode() {
        super();
    }

    public String operationTypeGet(){
        return this.operationType;
    }



    @Override
    public String toString(){
        return "line:"+lineNumber + ", type: "+ operationType+" node: "+n;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getOperationType() {
        return operationType;
    }

    protected void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    protected Node getN() {
        return n;
    }

    protected void setN(Node n) {
        this.n = n;
    }
}
