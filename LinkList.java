import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ListIterator;
public class LinkList<E> implements List<E> {
    //Node class used to model the Object E
    protected static class Node<E> {
        private E item;
        private Node<E> next;
        public Node(E item) {
            this.item = item;
            next = null;
        }
    }

    //Instance variables
    private Node<E> head;
    private int size;
    public LinkList() {
        head = null;
        size = 0;
    }

    //Adds the specified element to the end of the list
    public boolean add(E e) {
        if (isEmpty()) {
            head = new Node<>(e);
            ++size;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(e);
        ++size;
        return true;
    }

    //Adds the specified element to the specified position in the list
    public void add(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size || index == 0) {
            add(e);
            return;
        }
        int ind = 0;
        Node current = head;
        Node temp = new Node<>(e);
        while (ind + 1 != index) {
            ++ind;
            current = current.next;
        }
        temp.next = current.next;
        current.next = temp;
        ++size;
    }

    //Adds the specified element to the beginning of the list
    public void addFirst(E e) {
        if (isEmpty()) {
            head = new Node<>(e);
            ++size;
            return;
        }
        Node<E> current = new Node<>(e);
        current.next = head;
        head = current;
        ++size;
    }

    //Removes all of the elements in the list
    public void clear() {
        head = null;
        size = 0;
    }

    //Returns the number of elements in the list
    public int size() {
        return this.size;
    }

    //Checks if the list is empty
    public boolean isEmpty() {
        return head == null;
    }

    /*Check if the specified list has the same
     size as this list and all element are equal
     and appear in the same order for both lists*/
    public boolean equals(Object o) {
        if (!(o instanceof List)) {
            return false;
        }
        List other = (List)o;
        if (this.size() != other.size()) {
            return false;
        }
        if (!(o instanceof LinkList)) {
            return false;
        }
        Iterator i1 = this.iterator();
        Iterator i2 = other.iterator();
        while (i1.hasNext()) {
            Object o1 = i1.next();
            Object o2 = i2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2))) {
                return false;
            }
        }
        return true;
    }

    //Checks if the list contains the specified element
    public boolean contains(Object o) {
        for (E e : this) {
            if (o == null ? e == null : o.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<E> iterator() {
        return new LinkListIterator();
    }

    public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        for (E e : this) {
            array[i++] = e;
        }
        return array;
    }

    public <T> T[] toArray(T[] a) {
        Object[] result = a;
        if (size() > a.length) {
            result = new Object[size()];
        }
        int i = 0;
        for (E e : this) {
            result[i++] = e;
        }
        if (result.length > i) {
            result[i] = null;
        }
        return (T[])result;
    }

    //Removes the specified element from the list
    public boolean remove(Object o) {
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            E e = iterator.next();
            if (o == null ? e == null : o.equals(e)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    //Returns the element at the specified position in the list
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < index; ++i) {
            current = current.next;
        }
        return current.item;
    }

    public E set(int index, E element) {
        return null;
    }

    //Removes the element at the specified position from the list
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < index - 1; ++i) {
            current = current.next;
        }
        E e = current.next.item;
        current.next = current.next.next;
        --size;
        return e;
    }

    //Returns the position of the first occurrence of the specified element in the list
    public int indexOf(Object o) {
        int index = 0;
        for (E e : this) {
            if (o == null ? e == null : o.equals(e)) {
                return index;
            }
            ++index;
        }
        return -1;
    }

    //Returns the position of the last occurrence of the specified element in the list
    public int lastIndexOf(Object o) {
        int temp = 0, index = 0;
        for (E e : this) {
            if (o == null ? e == null : o.equals(e)) {
                index = temp;
            }
            ++temp;
        }
        return index;
    }

    //Returns the clone of this list
    public Object clone() {
        LinkList<E> clone = new LinkList<>();
        for (E e : this) {
            clone.add(e);
        }
        return clone;
    }

    public ListIterator<E> listIterator() {
        return null;
    }

    public ListIterator<E> listIterator(int index) {
        return null;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    //Inner Iterator class
    class LinkListIterator implements Iterator<E> {
        private Node<E> current;
        private Node<E> previous1;
        private Node<E> previous2;
        private boolean removed;
        public LinkListIterator() {
            current = head;
            removed = false;
        }
        public boolean hasNext() {
            return current != null;
        }
        public E next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            E temp = current.item;
            previous2 = previous1;
            previous1 = current;
            current = current.next;
            removed = false;
            return temp;
        }
        public void remove() {
            if (previous1 == null || removed) {
                throw new IllegalStateException();
            }
            if (previous2 == null) {
                head = current;
            } else {
                previous2.next = current;
            }
            --size;
            removed = true;
        }
    }
}