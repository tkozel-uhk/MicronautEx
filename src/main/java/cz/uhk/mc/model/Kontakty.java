package cz.uhk.mc.model;

import io.micronaut.core.annotation.Introspected;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
@Singleton
public class Kontakty {
    private List<Osoba> osoby;

    public Kontakty() {
    }

    @Inject
    public Kontakty(List<Osoba> osoby) {
        this.osoby = osoby;
    }

    public List<Osoba> getOsoby() {
        return osoby;
    }

}
