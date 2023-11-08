package cz.uhk.mc.web;

import cz.uhk.mc.model.Osoba;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.transaction.annotation.Transactional;
import io.micronaut.views.View;
import jakarta.inject.Inject;
import cz.uhk.mc.repos.OsobaRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/osoba")
public class OsobaController {
    private OsobaRepository osobaRepository;

    @Inject
    public void setOsobaRepository(OsobaRepository osobaRepository) {
        this.osobaRepository = osobaRepository;
    }
    @Inject
    DateTimeFormatter formatter;

    @Get
    public List<Osoba> getAll() {
        return osobaRepository.findAll();
    }

    @Get("/html")
    @View("osoby")
    public HttpResponse<Map<String, Object>> getAllHtml() {
        HashMap<String, Object> model = new HashMap<>();
        //posleme 2 objekty - seznam osob a aktualni cas
        model.put("osoby", osobaRepository.findAll());
        model.put("time", LocalDateTime.now().format(formatter));
        return HttpResponse.ok(model);
    }

    @Get("/{id}")
    public Osoba getOsobaById(long id) {
        return osobaRepository.findById(id).orElse(null);
    }

    @Get("/delete")
    public HttpResponse<?> deleteOsobaById(@QueryValue long id) throws URISyntaxException {
        osobaRepository.deleteById(id);
        return HttpResponse.temporaryRedirect(new URI("/osoba/html"));
    }

    @Get("/add")
    @View("osobaForm")
    public Map<String, Osoba> addOsoba() {
        return Collections.singletonMap("osoba", new Osoba());
    }

    @Get("/{id}/edit")
    @View("osobaForm")
    public Map<String, Osoba> editOsoba(long id) {
        Osoba osoba = osobaRepository.findById(id).orElse(null);
        return Collections.singletonMap("osoba", osoba);
    }

    @Post(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public HttpResponse<?> saveOsoba(@Body Osoba osoba) throws URISyntaxException {
        osobaRepository.update(osoba);
        return HttpResponse.redirect(new URI("/osoba/html"));
    }

}
