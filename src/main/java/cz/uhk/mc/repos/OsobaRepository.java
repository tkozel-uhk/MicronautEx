package cz.uhk.mc.repos;

import cz.uhk.mc.model.Osoba;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Long> {
}
