package DAO

import entities.Product
import org.hibernate.SessionFactory

class ProductDAO(private val sessionFactory: SessionFactory) {
    fun save(product: Product) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.save(product)
            session.transaction.commit()
        }
    }

    fun update(product: Product) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.saveOrUpdate(product)
            session.transaction.commit()
        }
    }

    fun delete(product: Product) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.delete(product)
            session.transaction.commit()
        }
    }
    fun find(id: Long): Product? {
        val result: Product?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.get(Product::class.java, id)
            session.transaction.commit()
        }
        return result
    }

    fun findAll(): List<Product> {
        val result: List<Product>
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.createQuery("from Product").list() as List<Product>
            session.transaction.commit()
        }
        return result
    }

}