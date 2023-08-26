package co.crisi.productopolis.gateway.jpamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;

@Entity
@Table(name = "review")
@Getter
public class ReviewJpaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private Integer rating;

    private String message;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    public ProductJpaEntity getProduct() {
        return new ProductJpaEntity(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReviewJpaEntity that = (ReviewJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
