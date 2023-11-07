package cz.uhk.mc;

import cz.uhk.mc.model.Osoba;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.runtime.Micronaut;

import java.util.ArrayList;
import java.util.List;

@Factory //instancializace beanu
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @Bean
    public List<Osoba> getOsoby() {
        return new ArrayList<>(List.of(
                new Osoba(1L, "Jan", "Novák", "jan@novak.cz"),
                new Osoba(2L, "Petr", "Svoboda", "petr.svoboda@email.cz"),
                new Osoba(3L, "Jana", "Králová", "jana@kralovi.com")
        ));
    }
}