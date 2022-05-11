package ga.tight.shortenurl.gloabl.repository;

import java.util.List;
import java.util.Optional;

public interface IBaseRepository<T, ID> {
    T save(T obj);

    Optional<T> findById(ID id);

    List<T> findAll();
}