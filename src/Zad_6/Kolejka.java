package Zad_6;

import java.util.LinkedList;

public abstract class Kolejka {
    protected LinkedList<Integer> list = new LinkedList<>();

    abstract public void dodaj(int v);
    public int usuń() {return list.removeFirst();}
    boolean isEmpty() {return list.isEmpty();}
}
