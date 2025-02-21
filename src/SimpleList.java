import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SimpleList<T> implements List<T> {

    private Node<T> head;

    @Override
    public int size() {
        int counter = 0;
        Node<T> aux = head;
        while (aux != null) {
            counter++;
            aux = aux.getNext();
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> aux = head;
        while (aux != null) {
            if ((o == null && aux.getData() == null) || (o != null && o.equals(aux.getData()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> aux = null;

            @Override
            public boolean hasNext() {
                if (aux == null && head != null) {
                    return true;
                } else {
                    if (aux.getNext() != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public T next() {
                if (aux == null) {
                    aux = head;
                } else {
                    aux = aux.getNext();
                }
                return aux.getData();
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node<T> aux = head;
        int index = 0;
        while (aux != null) {
            array[index++] = aux.getData();
            aux = aux.getNext();
        }
        return array;
    }

    @SuppressWarnings({ "unchecked", "hiding" })
    @Override
    public <T> T[] toArray(T[] a) {
        int size = size();
        if (a.length < size) {
            a = (T[]) java.util.Arrays.copyOf(a, size, a.getClass());
        }

        Node<T> aux = (Node<T>) head;
        int index = 0;
        while (aux != null) {
            a[index++] = aux.getData();
            aux = aux.getNext();
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T e) {
        Node<T> aux = new Node<T>(e);
        Node<T> current = head;
        if (head == null) {
            head = aux;
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(aux);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T data : c) {
            add(data);
        }
        return !c.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Node<T> aux = head;
        Node<T> prev = null;

        while (aux != null) {
            if (!c.contains(aux.getData())) {
                if (prev == null) {
                    head = aux.getNext();
                } else {
                    prev.setNext(aux.getNext());
                }
                modified = true;
            } else {
                prev = aux;
            }
            aux = aux.getNext();
        }
        return modified;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> aux = head;
        int i = 0;

        while (aux != null) {
            if (i == index) {
                T oldData = aux.getData();
                aux.setData(element);
                return oldData;
            }
            aux = aux.getNext();
            i++;
        }

        throw new IndexOutOfBoundsException();
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub

    }

    @Override
    public T remove(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int indexOf(Object o) {
        Node<T> aux = head;
        int index = 0;
        while (aux != null) {
            if ((o == null && aux.getData() == null) || (o != null && o.equals(aux.getData()))) {
                return index;
            }
            aux = aux.getNext();
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }

        SimpleList<T> sublist = new SimpleList<>();
        Node<T> aux = head;
        int index = 0;

        while (aux != null) {
            if (index >= fromIndex && index < toIndex) {
                sublist.add(aux.getData());
            }
            aux = aux.getNext();
            index++;
        }

        return sublist;
    }
}
