package co.crisi.productopolis.gateway.jpamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long stock;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    private boolean featured;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandJpaEntity brand;

    @OneToMany(mappedBy = "product")
    private List<ProductAttributeJpaEntity> attributes;

    @ManyToMany
    @JoinTable(name = "product_category",
               joinColumns = @JoinColumn(name = "category_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<CategoryJpaEntity> categories;

    @OneToMany(mappedBy = "product")
    private List<ImageJpaEntity> images;

    @OneToMany(mappedBy = "product")
    private List<ReviewJpaEntity> reviews;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductJpaEntity that = (ProductJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}