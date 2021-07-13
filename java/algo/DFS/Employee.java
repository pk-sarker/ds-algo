package algo.DFS;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
    public Employee(int id, int importance) {
        this.id = id;
        this.importance = importance;
        subordinates = new ArrayList<>();
    }
}
