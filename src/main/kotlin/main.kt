import DAO.ProductDAO
import DAO.ProductPhotoDAO
import DAO.UserDAO
import org.hibernate.cfg.Configuration
import entities.Product
import entities.ProductPhoto
import entities.User

fun main() {
    try {
        val sessionFactory = Configuration().configure()
            .addAnnotatedClass(Product::class.java)
            .addAnnotatedClass(User::class.java)
            .addAnnotatedClass(ProductPhoto::class.java)
            .buildSessionFactory()

        sessionFactory.use { sessionFactory ->
            val productDAO = ProductDAO(sessionFactory)
            val productPhotoDAO = ProductPhotoDAO(sessionFactory)
            val userDAO = UserDAO(sessionFactory)

            val product1 = Product(
                name = "Play Station 5",
                description = "good item",
                price = 100500,
                type = "top"
            )

            val user1 = User(
                username = "zhigalkin",
                email = "shigalkin@mail.ru",
                password = "hkng68%zHFG",
                phone = "720222"
            )

            val productPhoto1 = ProductPhoto(
                url = "www.example.com/123"
            )
            productPhoto1.setPhotoProduct(product1)
            productDAO.save(product1)
            productPhotoDAO.save(productPhoto1)

            user1.addProduct(product1)
            userDAO.save(user1)

            val found = userDAO.find(user1.id!!)
            println("Найден пользователь: $found \n")

            val foundProduct = productDAO.find(product1.id!!)
            println("Найден продукт: $foundProduct \n")

            val foundProductPhoto = productPhotoDAO.find(productPhoto1.id!!)
            println("Найдено фото продукта: $foundProductPhoto \n")

    }

    } catch (e: Exception) {
        println(e.message)
        println(e.printStackTrace())
    }
}