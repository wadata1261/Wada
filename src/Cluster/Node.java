package Cluster;

import make.Code;
import make.ValueLogList;

import java.util.List;

public interface Node {
    List<ValueLogList> getvllist();
    List<String> getpathName();
    List<Code> getcode();
}
