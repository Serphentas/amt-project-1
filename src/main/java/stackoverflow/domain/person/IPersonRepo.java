package stackoverflow.domain.person;

import stackoverflow.domain.IRepo;

import java.util.Optional;

public interface IPersonRepo extends IRepo<Person, PersonId> {

    public Optional<Person> findByUsername(String username);
}
