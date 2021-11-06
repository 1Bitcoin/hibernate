package DAO

import entities.ProductPhoto
import org.hibernate.SessionFactory

class ProductPhotoDAO(private val sessionFactory: SessionFactory) {
    fun save(productPhoto: ProductPhoto) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.save(productPhoto)
            session.transaction.commit()
        }
    }

    fun update(productPhoto: ProductPhoto) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.saveOrUpdate(productPhoto)
            session.transaction.commit()
        }
    }

    fun delete(productPhoto: ProductPhoto) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.delete(productPhoto)
            session.transaction.commit()
        }
    }
    fun find(id: Long): ProductPhoto? {
        val result: ProductPhoto?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.get(ProductPhoto::class.java, id)
            session.transaction.commit()
        }
        return result
    }

    fun findAll(): List<ProductPhoto> {
        val result: List<ProductPhoto>
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.createQuery("from ProductPhoto").list() as List<ProductPhoto>
            session.transaction.commit()
        }
        return result
    }

}