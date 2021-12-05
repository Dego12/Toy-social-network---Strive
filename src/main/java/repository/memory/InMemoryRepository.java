package repository.memory;

import domain.Entity;
import repository.Repository;
import validators.Validator;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {
    private Validator<E> validator;
    Map<ID, E> entities;


    public InMemoryRepository(Validator<E> validator){
        this.validator = validator;
        entities = new HashMap<ID, E>();
    }

    @Override
    public E findOne(ID id)
    {
        if(id==null)
            throw new IllegalArgumentException("The id cannot be null");
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() { return entities.values();}

    @Override
    public E save(E entity)
    {
        if(entity == null)
            throw new IllegalArgumentException("The entity cannot be null");
        validator.validate(entity);
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void delete(E entity) {
        entities.remove(entity.getId());
    }

    @Override
    public E update(E entity){
        if(entity == null)
            throw new IllegalArgumentException("The entity cannot be null");
        validator.validate(entity);

        entities.put(entity.getId(), entity);

        if (entities.get(entity.getId())!=null){
            entities.put(entity.getId(), entity);
            return null;
        }
        return entity;
    }


}
