package ds.BST;

public class ConstructBST {
    public static class BST {
        public int value;
        public BST leftChild;
        public BST rightChild;

        public BST(int value) {
            this.value = value;
        }

        /**
         * Time Complexity:
         *   Average: O(log(n))
         *   Worse case: O(n)
         * Space Complexity: O(n)
         */
        public BST insert(int newValue) {
            if (newValue < this.value) {
                if (this.leftChild == null) {
                    this.leftChild = new BST(newValue);
                } else {
                    this.leftChild.insert(newValue);
                }
            } else {
                if (this.rightChild == null) {
                    this.rightChild = new BST(newValue);
                } else {
                    this.rightChild.insert(newValue);
                }
            }
            return this;
        }
        public BST remove(int value) {
            this.remove(value, null);
            return this;
        }
        /**
         * Time Complexity:
         *   Average: O(log(n))
         *   Worse case: O(n)
         * Space Complexity:
         *   Average: O(log(n))
         *   Worse case: O(n)
         */
        public void remove(int value, BST parent) {
            // move to the position where the node is
            if (value < this.value) {
                if (leftChild != null) {
                    leftChild.remove(value, this);
                }
            } else if (value > this.value) {
                if (rightChild != null) {
                    rightChild.remove(value, this);
                }
            } else {
                // we are at node which needs to be delete
                // 1) if the node has both left and right child
                // 2) if the node is root node
                // 3) if the node has either left/right child
                if (leftChild != null && rightChild != null) {
                    this.value = rightChild.getMinValue();
                    rightChild.remove(this.value, this); // we just got the value using getMinValue, didn't removed
                } else if (parent == null) {
                    if (leftChild != null) {
                        this.value = leftChild.value;
                        this.leftChild = leftChild.leftChild;
                        this.rightChild = leftChild.rightChild;
                    } else if (rightChild != null) {
                        this.value = rightChild.value;
                        this.leftChild = rightChild.leftChild;
                        this.rightChild = rightChild.rightChild;
                    } else {
                        // single node BST
                    }
                } else {
                    /*
                        5
                       /
                      3 <- remove
                       \
                        4
                     */
                    if (parent.leftChild == this) {
                        parent.leftChild = leftChild != null ? leftChild : rightChild;
                    } else if (parent.rightChild == this) {
                        parent.rightChild = rightChild != null ? rightChild: leftChild;
                    }
                }
            }
        }
        // [ left sub tree ] [root] [ right sub tree]
        // left most node in right sub tree
        public int getMinValue() {
            if (leftChild == null) {
                return this.value;
            } else {
                return leftChild.getMinValue();
            }
        }

        /**
         * Time Complexity:
         *   Average: O(log(n))
         *   Worse case: O(n)
         * Space Complexity:
         *   Average: O(log(n))
         *   Worse case: O(n)
         */
        public boolean contains(int value) {
            if (value < this.value) {
                if (leftChild != null) {
                    return leftChild.contains(value);
                } else {
                    return false;
                }
            } else if (value > this.value) {
                if (rightChild != null) {
                    return rightChild.contains(value);
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }


    public static void main(String args[]) {

    }
}
