import java.util.ArrayList;
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
        if (head == null) {
            return false;
        }
        if (head.getData().equals(o)) {
            head = head.getNext();
            return true;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(o)) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!this.contains(element)) {
                return false;
            }
        }
        return true;
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
        if (c.isEmpty()) {
            return false;
        }
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        List<Node<T>> nodesToAdd = new ArrayList<>();
        for (T item : c) {
            nodesToAdd.add(new Node<>(item));
        }
        if (index == 0) {
            Node<T> lastNewNode = nodesToAdd.get(nodesToAdd.size() - 1);
            lastNewNode.setNext(head);
            head = nodesToAdd.get(0);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            Node<T> nextNode = current.getNext();
            current.setNext(nodesToAdd.get(0));
            nodesToAdd.get(nodesToAdd.size() - 1).setNext(nextNode);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;

        while (head != null && c.contains(head.getData())) {
            head = head.getNext();
            modified = true;
        }
    
        Node<T> current = head;
        while (current != null && current.getNext() != null) {
            if (c.contains(current.getNext().getData())) {
                current.setNext(current.getNext().getNext());
                modified = true;
            } else {
                current = current.getNext();
            }
        }
    
        return modified;
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
        Node<T> aux = head;
        int counter = 0; 
        while (aux != null && counter < index) {
            aux = aux.getNext();
            counter++;
        }
        if (counter == index) {
            return aux.getData();
        }
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
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        Node<T> newNode = new Node<T>(element);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<T> aux = head;
            for (int i = 0; i < index - 1; i++) {
                aux = aux.getNext();
            }
            newNode.setNext(aux.getNext());
            aux.setNext(newNode);
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        Node<T> aux = head;
        Node<T> deleted = null;
        if (index == 0) {
            deleted = head;
            head = head.getNext();
        } else {
            for (int i = 0; i < index - 1; i++) {
                aux = aux.getNext();
            }
            deleted = aux.getNext();
            aux.setNext(deleted.getNext());
        }
        return deleted.getData();
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
        int lastIndex = -1;
        Node<T> aux = head;
        if (this.isEmpty()) {
            lastIndex;
        }
        while (aux != null) {
            if (aux.getData().equals(o)) {
            }
            aux = aux.getNext();
        }
        return -1;
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
