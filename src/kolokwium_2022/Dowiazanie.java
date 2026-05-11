package kolokwium_2022;

public class Dowiazanie extends Element{
    private Element cel;

    public Dowiazanie(Element cel, Katalog katalogNadrzedny) {
        super(katalogNadrzedny);
        this.cel = cel;
        this.cel.rejestrujDowiazanie(this);
    }

    public Element getCel() {
        return cel;
    }

    @Override
    protected String getNazwa() {
        return "(" + this.cel.toString() + ")";
    }

    @Override
    public void usun() {
        this.cel.usunRejestrDowiazania(this);
        super.usun();
    }
}
