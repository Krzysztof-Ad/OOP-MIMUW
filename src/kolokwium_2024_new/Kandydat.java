package kolokwium_2024_new;

public class Kandydat implements Comparable<Kandydat> {
    public String nazwisko;
    public int liczbaGlosow;

    public void powiadom(boolean mandat) {
    }

    @Override
    public int compareTo(Kandydat other) {
        if (this.liczbaGlosow > other.liczbaGlosow) {
            return -1;
        } else if (this.liczbaGlosow < other.liczbaGlosow) {
            return 1;
        }
        return 0;
    }
}