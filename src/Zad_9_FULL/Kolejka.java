package Zad_9_FULL;

import java.util.LinkedList;

public class Kolejka {
    private final LinkedList<WadliwyInt> elements = new LinkedList<>();

    public void add(int value) throws UnexpectedBehavior, RandomException {
        if (containsValue(value)) {
            throw new UnexpectedBehavior("Wartosc " + value + " juz znajduje sie w kolejce");
        }

        // We create a new container if it throws an exception, list stays untouched
        WadliwyInt nowyPojemnik = new WadliwyInt(value);
        elements.addLast(nowyPojemnik);
    }

    public int remove() throws UnexpectedBehavior, RandomException {
        if (elements.isEmpty()) {
            throw new UnexpectedBehavior("Proba usuniecia elementu pustej kolejki");
        }

        WadliwyInt pierwszyPojemnik = elements.getFirst();

        // If there will be the RandomException error we will not delete an element
        int wartosc = pierwszyPojemnik.get();
        elements.removeFirst();

        return wartosc;
    }

    public void change (int value1, int value2) throws UnexpectedBehavior, RandomException {
        if (value1 == value2) {
            if (!containsValue(value1)) {
                throw new UnexpectedBehavior("Brak wartosci " + value1 + " do zamiany");
            }
            return;
        }

        if (containsValue(value2)) {
            throw new UnexpectedBehavior("Wartosc " + value2 + " juz znajduje sie w kolejce");
        }

        int indexDoZmiany = -1;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).get() == value1) {
                indexDoZmiany = i;
                break;
            }
        }

        if (indexDoZmiany == -1) {
            throw new UnexpectedBehavior("Brak wartosci " + value1 + " do zamiany");
        }

        // Safe change - first we create the object and later we place it in a list
        WadliwyInt nowyPojemnik = new WadliwyInt(value2);
        elements.set(indexDoZmiany, nowyPojemnik);

        // Alternatively we could use  elements.get(indexDoZmiany).set(value2)
        // because set() in WadliwyInt throws exception before modification so it is also safe
    }

    private boolean containsValue (int value) throws RandomException {
        for (WadliwyInt pojemnik : elements) {
            if (pojemnik.get() == value) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}