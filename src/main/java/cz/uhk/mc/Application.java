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

}