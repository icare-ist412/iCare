package icare.models.persistence;

import java.util.List;

/**
 *
 * @author Dmitry Mikhailov
 */
public interface CrudRepository<T, ID> {

    public void save(T t);

    public List<T> getAll();

    public T findById(ID id);

}
