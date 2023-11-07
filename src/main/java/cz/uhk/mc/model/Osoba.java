package cz.uhk.mc.model;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable  //serializace
public class Osoba {
    private Long id;
    private String jmeno;
    private String prijmeni;
    private String email;

    public Osoba() {
    }

    public Osoba(Long id, String jmeno, String prijmeni, String email) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "id=" + id +
                ", jmeno='" + jmeno + '\'' +
                ", prijmeni='" + prijmeni + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
