package algo.design;

import java.util.List;

public class NestedInteger {
    boolean isDataTypeList;
    int intValue;
    List<NestedInteger> listValue;
    public NestedInteger(){
        this.isDataTypeList = false;
    }

    public NestedInteger(boolean type){
        this.isDataTypeList = type;
    }
    public NestedInteger(int value){
        this.isDataTypeList = false;
        this.intValue = value;
    }

    public NestedInteger(List<NestedInteger> listValue){
        this.isDataTypeList = true;
        this.listValue = listValue;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return !this.isDataTypeList;
    }
    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return this.intValue;
    }
    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return this.listValue;
    }
}
