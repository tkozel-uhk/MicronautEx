package cz.uhk.mc.web;

import cz.uhk.mc.model.Kontakty;
import cz.uhk.mc.model.Osoba;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.views.View;
import jakarta.inject.Inject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller("/osoba")
@Headers({
        @Header(name = "Pragma", value = "no-cache"),
        @Header(name = "Cache-Control", value = "no-cache, no-store, must-revalidate"),
        @Header(name = "Expires", value = "0")
})
public class OsobaController {
    private Kontakty kontakty;

    @Inject
    public void setKontakty(Kontakty kontakty) {
        this.kontakty = kontakty;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<Osoba> getAll() {
        return kontakty.getOsoby();
    }

    @Get("/html")
    @View("osoby")
    public Map<String, List<Osoba>> getAllHtml() {
        return Collections.singletonMap("osoby", kontakty.getOsoby());
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Osoba getOsobaById(long id) {
        return kontakty.getOsoby().stream()
                .filter(osoba -> osoba.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Get(value = "/delete")
    public HttpResponse<?> deleteOsobaById(@QueryValue long id) throws URISyntaxException {
        List<Osoba> osoby = kontakty.getOsoby();
        osoby.removeIf(osoba -> (osoba.getId() == id));
        return HttpResponse.redirect(new URI("/osoba/html"));
    }

    @Get("/add")
    @View("osobaForm")
    public Map<String, Osoba> addOsoba() {
        return Collections.singletonMap("osoba", new Osoba());
    }

    @Get("/{id}/edit")
    @View("osobaForm")
    public Map<String, Osoba> editOsoba(long id) {
        Osoba osoba = kontakty.getOsoby().stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
        return Collections.singletonMap("osoba", osoba);
    }

    @Post(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> saveOsoba(@Body Osoba osoba) throws URISyntaxException {
        List<Osoba> osoby = kontakty.getOsoby();
        if (osoba.getId() == 0) {
            osoba.setId(osoby.size()+1);
        } else {
            osoby.removeIf(o -> o.getId() == osoba.getId());
        }
        osoby.add(osoba);
        osoby.sort((o1,o2) -> (int)(o1.getId() - o2.getId()));
        return HttpResponse.redirect(new URI("/osoba/html"));
    }

}
