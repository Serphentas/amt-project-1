package stackoverflow.domain;

import java.util.Collection;
import java.util.Optional;

public interface IRepo<ENTITY extends IEntity, ID extends Id> {

    public void save(ENTITY entity);
    public  void remove(ID id);
    public Optional<ENTITY> findById(ID id);
    public Collection<ENTITY> findAll();

}