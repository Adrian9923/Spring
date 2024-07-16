package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.models.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.name AS productName, p.price, CONCAT(u.firstName, ' ', u.lastName) AS sellerFullName " +
            "FROM Product p " +
            "JOIN p.seller u " +
            "WHERE p.buyer IS NOT NULL AND p.price BETWEEN :minPrice AND :maxPrice " +
            "ORDER BY p.price")
    List<Object[]> findProductsInRangeWithoutBuyer(BigDecimal minPrice, BigDecimal maxPrice);
}
