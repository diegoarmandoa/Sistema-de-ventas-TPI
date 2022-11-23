package TPI.TPI.Commons;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl <T,ID extends Serializable> implements GenericServiceAPI<T,ID> {
    @Override
    public T save(T entity) {
        return getDao().save(entity);
    }

    @Override
    public void delete(ID id) {
        getDao().deleteById(id);

    }

    @Override
    public T get(ID id) {
        Optional<T>obj = getDao().findById(id);
        return obj.isPresent() ? obj.get() : null;
    }

    @Override
    public List<T> getAll() {
        List<T> list  = new ArrayList<>();
        getDao().findAll().forEach(obj ->list.add(obj));
        return  list;
    }
    public abstract CrudRepository<T,ID> getDao();
}
