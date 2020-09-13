package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class is a min max heap.
 *
 * @author Gilad Ilani
 * @version 19/05/20
 */
public class maxMinTree {
    public ArrayList _maxMinTree;
    private int _size;

    /**
     * empty constructor.
     * construct a new min-max-heap.
     */
    public maxMinTree() {
        _maxMinTree = new ArrayList();
        _size = 0;
    }


    /**
     * copy constructor
     *
     * @param toCopy the heap to be copied.
     */
    private maxMinTree(maxMinTree toCopy) {
        _maxMinTree = new ArrayList();
        _size = toCopy.getSize();
        for (int i = 0; i < getSize(); i++) {
            this._maxMinTree.add(toCopy.getValue(i));
        }

    }

    /**
     * calculate log function with base 2 of val.
     * .     * @param val value to calculate.
     *
     * @return log function with base 2 of val.
     */
    private static double log2(double val) {
        return (Math.log10(val)) / Math.log10(2);
    }

    /**
     * calculate the depth of an index in a man min heap.
     *
     * @param ind the index to calculate.
     * @return the index depth.
     */
    public static double getDepth(int ind) { // not good.
        if (ind == 0)
            return 0;
        if (ind == 1)
            return 1;
        return Math.floor(log2(ind + 1));
    }

    /**
     * get the heap's size.
     *
     * @return the heap's size.
     */
    private int getSize() {
        return _size;
    }

    /**
     * set the heap's size
     *
     * @param size the new heap's size.
     */
    private void setSize(int size) {
        if (size >= 0) {
            _size = size;
        }
    }

    /**
     * get the value of a node.
     *
     * @param ind the node's index value.
     * @return the index's value.
     */
    private int getValue(int ind) {
        return (int) _maxMinTree.get(ind);
    }

    /**
     * set an index's value.
     *
     * @param ind the index where value is changed.
     * @param val value to be changed to.
     */
    private void setValue(int ind, int val) {
        _maxMinTree.set(ind, val);
    }

    /**
     * get's an the index of another index in a max mun heap
     *
     * @param ind the index to checked it's father.
     * @return the father's index.
     */
    private int getFather(int ind) {
        return (int) Math.floor(((ind + 1) / 2)) - 1;
    }

    /**
     * get the left son's index of a specific index.
     *
     * @param ind the index to check it's son
     * @return the index's left son/
     */
    private int getLeftSon(int ind) {
        if (ind == 0) return 1;
        return ind * 2 + 1;
    }

    /**
     * get the right son's index of a specific index.
     *
     * @param ind the index to check it's son
     * @return the index's right son
     */
    private int getRightSon(int ind) {
        if (ind == 0) return 2;
        return ind * 2 + 2;
    }

    /**
     * checks of a spcipic index is in a max line
     *
     * @param ind the index to check
     * @return true if in a max line, false if in a min.
     */
    private boolean isInMaxLine(int ind) {
        return (getDepth(ind) % 2 == 0);
    }

    /**
     * bring node in index i to it's right location in a max-min-heap:
     * if a node is in an even depth, it's even or bigger than it's descendant.
     * if a node is in an odd depth, it's even or smaller than it's descendant.
     *
     * @param i the node's index to bring to a right location.
     */
    public void heapify(int i) {
        if (isInMaxLine(i))
            maxHeapify(i);
        else
            minHeapify(i);

    }

    /**
     * heapify a node in an odd depth.
     *
     * @param i the index to heapify.
     */
    private void minHeapify(int i) {
        if (getLeftSon(i) < getSize() || getRightSon(i) < getSize()) { //if node i has a son. if he don't have- do nothing.
            int min = minDescendant(i);                            // find the minimum node value between node i's 6 close descendants and himself.
            if (min != i) {                                             // if i is the smallest of it's descendants, he is in a legal place.
                swap(min, i);                                // if not, switch between them.
                if (isGrandChild(min, i) & getValue(min) > getValue(getFather(min))) {
                    swap(min, getFather(min));
                }
                heapify(min);
            }

        }
    }

    /**
     * checks if index b is the grand-son of a in a boolean tree.
     *
     * @param child  a the index supposed to be the grand-son.
     * @param father the index supposed to be the grand-father.
     * @return true if b is the grand-son of a.
     * @run_Time O(1).
     */
    private boolean isGrandChild(int child, int father) {
        return (child == getLeftSon(getLeftSon(father)) || child == getLeftSon(getRightSon(father))
                || child == getRightSon(getLeftSon(father)) || child == getRightSon(getRightSon(father)));
    }

    /**
     * search for minimal value in root's descendant
     *
     * @param root the index to search between his descendant
     * @return return root's minimum value's son or grand son index.
     */
    private int minDescendant(int root) {

        int min = root;
        if (isSmallerThanMin(getLeftSon(root), min))
            min = getLeftSon(root);
        if (isSmallerThanMin(getLeftSon(getLeftSon(root)), min))
            min = getLeftSon(getLeftSon(root));
        if (isSmallerThanMin(getLeftSon(getRightSon(root)), min))
            min = getLeftSon(getRightSon(root));
        if (isSmallerThanMin(getRightSon(root), min))
            min = getRightSon(root);
        if (isSmallerThanMin(getRightSon(getLeftSon(root)), min))
            min = getRightSon(getLeftSon(root));
        if (isSmallerThanMin(getRightSon(getRightSon(root)), min))
            min = getRightSon(getRightSon(root));
        return min;

    }

    /**
     * checks if i is inside the heap and if his value is smaller than min's.
     *
     * @param i   possible min.
     * @param min current min.
     * @return true if he is both in the heap and smaller than min. else false.
     */
    private boolean isSmallerThanMin(int i, int min) {
        if (i < getSize())
            return (getValue(i) < getValue(min));
        return false;
    }

    /**
     * heapify a node in an even depth.
     *
     * @param i the index to heapify.
     */
    private void maxHeapify(int i) {
        if (getLeftSon(i) < getSize() || getRightSon(i) < getSize()) {
            int max = maxDescendant(i);
            if (max != i) {
                swap(max, i);
                if (isGrandChild(max, i) & getValue(max) < getValue(getFather(max))) {
                    swap(max, getFather(max));
                    heapify(getFather(max));
                }
                heapify(max);
            }
        }
    }

    /**
     * search for maximal value in root's descendant
     *
     * @param root the index to search between his descendant
     * @return return root's maximum value's son or grand son index.
     */
    private int maxDescendant(int root) {

        int max = root;
        if (isBiggerThanMax(getLeftSon(root), max))
            max = getLeftSon(root);
        if (isBiggerThanMax(getLeftSon(getLeftSon(root)), max))
            max = getLeftSon(getLeftSon(root));
        if (isBiggerThanMax(getLeftSon(getRightSon(root)), max))
            max = getLeftSon(getRightSon(root));
        if (isBiggerThanMax(getRightSon(root), max))
            max = getRightSon(root);
        if (isBiggerThanMax(getRightSon(getLeftSon(root)), max))
            max = getRightSon(getLeftSon(root));
        if (isBiggerThanMax(getRightSon(getRightSon(root)), max))
            max = getRightSon(getRightSon(max));
        return max;
    }

    /**
     * checks if i is inside the heap and if it's value is bigger than min's.
     *
     * @param i   possible max.
     * @param max current max.
     * @return true if he is both in the heap and bigger  than min. else false.
     */
    private boolean isBiggerThanMax(int i, int max) {
        if (i < getSize())
            return (getValue(i) > getValue(max));
        return false;
    }

    /**
     * swap places between two indexes.
     *
     * @param a first index
     * @param b second index
     */
    private void swap(int a, int b) {
        int temp = getValue(a);
        setValue(a, getValue(b));
        setValue(b, temp);

    }

    /**
     * turn a regular heap into a max-min-heap
     */
    public void buildHeap() {
        if (getSize() == 0) {
            System.out.println("heap is empty");
            return;
        }
        int i = (getSize() / 2) - 1;
        while (i >= 0) {
            heapify(i);
            i -= 1;
        }
    }

    /**
     * extract max value in max min heap .
     *
     * @return max value in heap
     */
    public Integer extractMax() {
        if (getSize() == 0) {
            System.out.println("heap is empty.");
            return null;
        }
        int max = getValue(0);
        if (getSize() != 1) {
            swap(0, getSize() - 1);
        }
        _maxMinTree.remove(getSize() - 1);
        setSize(getSize() - 1);
        heapify(0);
        return (int) max;
    }

    /**
     * extract min value in max min heap .
     *
     * @return min value in heap
     */

    public Integer extractMin() {
        int min;
        int minInd;
        if (getSize() == 0) {
            System.out.println("heap is empty");
            return null;
        }
        if (getSize() == 1) {
            min = getValue(0);
            setSize(0);
            _maxMinTree.remove(0);
            return (int) min;
        }
        if (getSize() == 2) {
            min = getValue(1);
            setSize(1);
            _maxMinTree.remove(1);
            return (int) min;
        }
        if (getValue(1) < getValue(2))
            minInd = 1;
        else
            minInd = 2;
        min = getValue(minInd);
        swap(getSize() - 1, minInd);
        _maxMinTree.remove(getSize() - 1);
        setSize(getSize() - 1);
        heapify(minInd);
        return (int) min;
    }

    /**
     * insert a new value in a max min heap while keeping it by legal order.
     *
     * @param key new value to insert
     */
    public void heapInsert(int key) {
        setSize(getSize() + 1);
        _maxMinTree.add(key);
        if (getSize() > 1)
            pushUp(getSize() - 1);

    }

    /**
     * push up a index from down up to it's legal location in max min heap.
     *
     * @param i index of a node to push up.
     */
    private void pushUp(int i) {
        if (isInMaxLine(i))
            maxPushUp(i);
        else
            minPushUp(i);
    }

    /**
     * push up a index from down up to it's legal location in max min heap, from an even depth.
     *
     * @param i index of a node to push up.
     */
    private void maxPushUp(int i) {
        if (i == 0) return;
        if (fatherExist(i) & getValue(i) < getValue(getFather(i))) {
            swap(i, getFather(i));
            minPushUp(getFather(i));
            return;
        }
        if (grandFatherExist(i) & getValue(i) > getValue(getFather(getFather(i)))) {
            swap(i, getFather(getFather(i)));
            maxPushUp(getFather(getFather(i)));
        }
    }

    /**
     * checks if i has a father
     *
     * @param i index to be checked
     * @return true is he has, else false.
     */
    private boolean fatherExist(int i) {
        return i != 0;
    }

    /**
     * checks if i has a grand father
     *
     * @param i index to be checked
     * @return true is he has, else false.
     */
    private boolean grandFatherExist(int i) {
        return i >= 3;
    }

    /**
     * push up a index from down up to it's legal location in max min heap, from an odd depth.
     *
     * @param i index of a node to push up.
     */
    private void minPushUp(int i) {
        if (fatherExist(i) & getValue(i) > getValue(getFather(i))) {
            swap(i, getFather(i));
            maxPushUp(getFather(i));
            return;
        }
        if (grandFatherExist(i)) {
            if (getValue(i) < getValue(getFather(getFather(i)))) {
                swap(i, getFather(getFather(i)));
                minPushUp(getFather(getFather(i)));
            }
        }
    }

    /**
     * delete a value from max min heap
     *
     * @param i index to be deleted
     */
    public void heapDelete(int i) {
        if (i >= getSize()) {
            System.out.println("index is out of heap");
            return;
        }
        swap(i, getSize() - 1);
        _maxMinTree.remove(getSize() - 1);
        setSize(getSize() - 1);
        heapify(i);
    }

    /**
     * creat a not legal max min heap from a given file.
     * before using the methods, put file path.
     *
     * @throws FileNotFoundException
     */
    public void addFromFile(String filePath) throws FileNotFoundException {
        if (getSize() == 0) {
            File file = new File(filePath); //put true file path here
            Scanner scan = new Scanner(file);
            while (scan.hasNextInt()) {
                dumAdder(scan.nextInt());
            }
        } else
            System.out.println("heap is not empty");
    }

    /**
     * adding values to a heap in it's last index without keeping it legal.
     *
     * @param val the value to add
     */
    private void dumAdder(int val) {
        _maxMinTree.add(val);
        setSize(getSize() + 1);
    }

    /**
     * @return a string of a min max heap
     */
    public String toString() {
        return String.valueOf(_maxMinTree);
    }

    public int[] sort() {
        maxMinTree copy = new maxMinTree(this);
        int sorted[] = new int[copy.getSize()];
        int i = 0;
        while (copy.getSize() != 0) {
            sorted[i] = copy.extractMin();
            i++;
        }
        return sorted;

    }
}