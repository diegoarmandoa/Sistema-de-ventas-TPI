package TPI.TPI.Commons;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
@Service
public interface GenericServiceAPI  <T,ID extends Serializable> {
    T save(T entity);
    void delete(ID id);
    T get(ID id);
    List<T> getAll();

}
