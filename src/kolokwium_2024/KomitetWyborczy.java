package kolokwium_2024;

public class KomitetWyborczy implements Comparable<KomitetWyborczy> {
    public String nazwa;
    public int sumaGlosow;
    public Kandydat[] kandydaci;
    public Kandydat[] kandydaciZMandatem;
    public int id;

    public int zdobyteMandaty = 0;
    public int dzielnik = 1;
    public double aktualnyIloraz;

    @Override
    public int compareTo(KomitetWyborczy other) {
        if (this.aktualnyIloraz > other.aktualnyIloraz) {
            return -1;
        } else if (this.aktualnyIloraz < other.aktualnyIloraz) {
            return 1;
        } else {
            if (this.id < other.id) {
                return -1;
            } else if (this.id > other.id) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}