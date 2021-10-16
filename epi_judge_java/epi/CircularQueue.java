package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class CircularQueue {

  public static class Queue {
    private Integer[] storage;
    private int size = 0, head = 0, tail = 0;

    public Queue(int capacity) {
      storage = new Integer[capacity];
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public void enqueue(Integer x) {
      if (size == storage.length){
        Collections.rotate(Arrays.asList(storage), -head);
        head = 0;
        tail = size;
        int newSize = storage.length * 2;
        storage = Arrays.copyOf(storage, newSize);
      }
      storage[tail] = x;
      tail = (tail + 1) % storage.length;
      size++;
    }

    public Integer dequeue() {
      if (size != 0) {
        Integer end = storage[head];
        head = (head + 1) % storage.length;
        size--;
        return end;
      }
      return null;
    }
    public int size() { return size; }

    @Override
    public String toString() {
      return String.format("Queue{ head=%d, tail=%d, entries=%s }", head, tail, storage);
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
