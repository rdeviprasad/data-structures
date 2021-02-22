import java.util.Arrays;
import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {
  private Object[] elements;
  private int length = 0;
  private int capacity = 0;

  public DynamicArray() {
    this(10);
  }

  public DynamicArray(int capacity) {
    this.capacity = capacity;
    this.length = 0;
    this.elements = new Object[capacity];
  }

  public int size() {
    return length;
  }

  public boolean isEmpty() {
    return size() > 0;
  }

  public T get(int index) {
    validateIndexValue(index);
    return (T)elements[index];
  }

  public void set(int index, T element) {
    validateIndexValue(index);
    elements[index] = element;
  }

  public void clear() {
    for(int i = 0; i < length; i++) {
      elements[i] = null;
    }
    length = 0;
  }

  public boolean add(T element) {
    ensureCapacity(length + 1);
    elements[length++] = element;
    return true;
  }

  public T removeAt(int index) {
    validateIndexValue(index);
    T element = (T) elements[index];
    removeElement(index);
    return element;
  }

  public boolean remove(T element) {
    if(element == null) {
      for(int i = 0; i < length; i++) {
        if(elements[i] == null) {
          removeElement(i);
          return true;
        }
      }
    } else {
      for(int i = 0; i < length; i++) {
        if(element.equals(elements[i] == null)) {
          removeElement(i);
          return false;
        }
      }
    }
    return false;
  }

  public int indexOf(T element) {
    if(element == null) return -1;
    for(int i = 0; i < length; i++) {
      if(element.equals(elements[i])) {
        return i;
      }
    }
    return -1;
  }

  public boolean contains(T element) {
    return indexOf(element) > -1;
  }

  public java.util.Iterator<T> iterator() {
    return new java.util.Iterator<T>() {
      int index = 0;
      public boolean hasNext() {
        return index < length;
      }

      public T next() {
        return (T) elements[index++];
      }
    };
  }

  private void removeElement(int index) {
    int elementsToMove = length - index - 1;
    if(elementsToMove > 0) {
      System.arraycopy(elements, index + 1, elements, index, elementsToMove);
    }
    elements[--length] = null;
  }

  private void ensureCapacity(int minSize) {
    if(minSize >= capacity) {
      capacity *= 2;
      elements = Arrays.copyOf(elements, capacity);
    }
  }

  private void validateIndexValue(int index) {
    if(index >= length || index < 0) {
      throw new IllegalArgumentException("Bad index value passed : " + index);
    }
  }
}

class DynamicArrayDriver {
  public static void main(String[] args) {
    try {
      DynamicArray<Long> dArray = new DynamicArray<>();
      dArray.add(1l);
      dArray.add(2l);
      dArray.add(3l);
      dArray.add(4l);
      //dArray.removeAt(1);

      for(Long element: dArray) {
        System.out.println(element);
      }
      // Iterator<Integer> iter = dArray.iterator();
      // while(iter.hasNext()) {
      //   System.out.println(iter.next());
      // }
    } catch(Exception e) {
      System.out.println("Exception occurred while running DynamicArray program : " + e);
    }
  }
}