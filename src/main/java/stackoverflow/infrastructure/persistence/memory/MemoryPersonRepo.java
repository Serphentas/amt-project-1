package stackoverflow.infrastructure.persistence.memory;

import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.person.PersonId;
import stackoverflow.infrastructure.persistence.exception.DataCorruptionException;
import stackoverflow.infrastructure.persistence.exception.IntegrityConstraintViolationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryPersonRepo extends MemoryRepo<Person, PersonId> implements IPersonRepo{

    @Override
    public void save(Person entity) {
        synchronized (entity.getUsername()){
            if(!findByUsername(entity.getUsername()).isEmpty()){
                //throw new IntegrityConstraintViolationException("Cannot save/update Person. Integrity constraint vilation: username");
            }
            super.save(entity);
        }
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        List<Person> matchingEntities = findAll().stream()
            .filter(p -> p.getUsername().equals(username))
            .collect(Collectors.toList());

        if(matchingEntities.size() < 1) {
            return Optional.empty();
        }

        if(matchingEntities.size() > 1) {
            //throw new DataCorruptionException("Your data store is corrupted");
        }

        return Optional.of(matchingEntities.get(0).deepClone());
    }
}
