public class Zakaznik {
    private String prijmeni;
    private String jmeno;
    private String nakup_nazev;
    private int mnozstvi;
    private int cena;

    public Zakaznik(String jmeno, String prijmeni) {
        this.prijmeni = prijmeni;
        this.jmeno = jmeno;

    }

    public Zakaznik(String jmeno, String prijmeni, String nakup_nazev, int mnozstvi, int cena) {
        this.prijmeni = prijmeni;
        this.jmeno = jmeno;
        this.nakup_nazev = nakup_nazev;
        this.mnozstvi = mnozstvi;
        this.cena = cena;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getNakup_nazev() {
        return nakup_nazev;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public int getCena() {
        return cena;
    }


    @Override
    public String toString() {
        return "Zakaznik{" +
                "prijmeni='" + prijmeni + '\'' +
                ", jmeno='" + jmeno + '\'' +
                ", nakup_nazev='" + nakup_nazev + '\'' +
                ", mnozstvi=" + mnozstvi +
                ", cena=" + cena +
                '}';
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setNakup_nazev(String nakup_nazev) {
        this.nakup_nazev = nakup_nazev;
    }

    public void setMnozstvi(int mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
