package cz.uhk.mc.web;

import cz.uhk.mc.model.Kontakty;
import cz.uhk.mc.model.Osoba;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/osoba")
public class OsobaController {
    private Kontakty kontakty;

    @Inject
    public void setKontakty(Kontakty kontakty) {
        this.kontakty = kontakty;
    }

    @Get
    public List<Osoba> getAll() {
        return kontakty.getOsoby();
    }
}
