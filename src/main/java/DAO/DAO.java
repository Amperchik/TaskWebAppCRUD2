package DAO;

import model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DAO {
    @PersistenceContext
    private EntityManager em;

    /**
     * Метод Create - даешь в агрументы сущность, метод ее добавляет в контекст постоянства
     * и при commit/rollback обновит базу
     */
    public void create(User a) {
        em.persist(a);
    }

    /**
     * Метод Read проверяет есть у сущности id , если есть то вернет обьект под этим ключом
     */
    public User read(long id) {
        return em.find(User.class, id);
    }

    /**
     * Метод Delete - сначала ищет сущность в БД/PersistentContext,
     * удаляет его(remove) и по окончании транзакции обновляет в базе
     */
    public void delete(long id) {
        em.remove(em.find(User.class,id));
    }

    /**
     * Метод Update добавляет сущность в контекст постоянства и
     * при commit/rollback обновит его состояние в базе
     */
    public void update(User f) {
        em.merge(f);
    }

    /**
     * Опциональный метод создания таблицы ( если нет )
     */
    public void createTable() {
        em.createNativeQuery("Create table  if NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT " +
                "NOT NULL,firstName varchar(256) NOT NULL ,lastName varchar(256) not null,email int not null);");
    }

    /**
     * Опциональный метод удаления таблицы
     */
    public void dropTable() {
        em.createNativeQuery("DROP table  if  EXISTS users");
    }

    /**
     * Опциональный метод очистка таблицы
     */
    public void cleanTable() {
        em.createQuery("delete from User").executeUpdate();
    }

    /**
     * Получение всех элементов таблицы
     */
    public List<User> getAll() {
        return em.createQuery("From " + User.class.getSimpleName()).getResultList();
    }
}
