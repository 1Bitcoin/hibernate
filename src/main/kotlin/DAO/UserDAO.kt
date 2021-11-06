package DAO

import entities.User
import org.hibernate.SessionFactory

class UserDAO(private val sessionFactory: SessionFactory) {
    fun save(user: User) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.save(user)
            session.transaction.commit()
        }
    }

    fun update(user: User) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.saveOrUpdate(user)
            session.transaction.commit()
        }
    }

    fun delete(user: User) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.delete(user)
            session.transaction.commit()
        }
    }
    fun find(id: Long): User? {
        val result: User?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.get(User::class.java, id)
            session.transaction.commit()
        }
        return result
    }

    fun findAll(): List<User> {
        val result: List<User>
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.createQuery("from User").list() as List<User>
            session.transaction.commit()
        }
        return result
    }

}