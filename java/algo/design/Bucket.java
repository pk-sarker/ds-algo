package algo.design;

import java.util.LinkedList;
import java.util.List;

public class Bucket {
    private List<int[]> bucket;

    public Bucket() {
        this.bucket = new LinkedList<int[]>();
    }

    public Integer get(Integer key) {
        for (int[] pair : this.bucket) {
            if (pair[0] == key.intValue()) {
                return pair[1];
            }
        }
        return -1;
    }

    public void update(Integer key, Integer value) {
        boolean found = false;
        for (int[] pair : this.bucket) {
            if (pair[0] == key.intValue()) {
                pair[1] = value.intValue();
                found = true;
            }
        }
        if (!found)
            this.bucket.add(new int[]{key, value});
    }

    public void remove(Integer key) {
        for (int[] pair : this.bucket) {
            if (pair[0] == key.intValue()) {
                    this.bucket.remove(pair);
                break;
            }
        }
    }
}
