package Repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericReposiroty<T, ID> {

    T getById(ID id) throws ClassNotFoundException, SQLException;

    List<T> getAll() throws ClassNotFoundException, SQLException;

    T save(T t) throws SQLException, ClassNotFoundException;

    T update(T t) throws ClassNotFoundException, SQLException;

    void deleteById(ID id) throws ClassNotFoundException, SQLException;
}
