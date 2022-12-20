package Data;

public class Zbozi {
    int id;
    String kategory;
    String zbozy;
    int mnozstvi;
    int cena;

    public Zbozi(int id, String kategory, String zbozy, int mnozstvi, int cena) {
        this.id = id;
        this.kategory = kategory;
        this.zbozy = zbozy;
        this.mnozstvi = mnozstvi;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategory() {
        return kategory;
    }

    public void setKategory(String kategory) {
        this.kategory = kategory;
    }

    public String getZbozy() {
        return zbozy;
    }

    public void setZbozy(String zbozy) {
        this.zbozy = zbozy;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(int mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
