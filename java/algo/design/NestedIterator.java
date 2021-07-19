package algo.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

    private List<Integer> integers = new ArrayList<Integer>();
    private int position = 0; // Pointer to next integer to return.

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenList(nestedList);
    }

    // Recursively unpacks a nested list in DFS order.
    private void flattenList(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                integers.add(nestedInteger.getInteger());
            } else {
                flattenList(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) return -1;
        return integers.get(position++);

    }

    @Override
    public boolean hasNext() {
        return position < integers.size();

    }

    public static void main(String args[]) {

        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(new NestedInteger(1));
        nestedList.add(new NestedInteger(3));
        List<NestedInteger> nestedList1 = new ArrayList<>();
        nestedList1.add(new NestedInteger(5));
        nestedList1.add(new NestedInteger(6));

        nestedList.add(new NestedInteger(nestedList1));
        nestedList.add(new NestedInteger(7));

        List<NestedInteger> nestedList2 = new ArrayList<>();
        nestedList2.add(new NestedInteger(8));
        nestedList2.add(new NestedInteger(Arrays.asList(new NestedInteger[]{new NestedInteger(9), new NestedInteger(10)})));
        nestedList2.add(new NestedInteger(11));
        nestedList.add(new NestedInteger(nestedList2));
        NestedIterator obj = new NestedIterator(nestedList);
        System.out.println("-- -- --");
        while(true){
            if (obj.hasNext()) {
                System.out.print(obj.next() + ", ");
            } else {
                break;
            }
        }
    }
}
