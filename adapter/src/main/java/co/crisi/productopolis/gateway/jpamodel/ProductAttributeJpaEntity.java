package co.crisi.productopolis.gateway.jpamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductAttributeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private AttributeJpaEntity attribute;

    private String value;

    public ProductJpaEntity getProduct() {
        return new ProductJpaEntity(product);
    }

    public AttributeJpaEntity getAttribute() {
        return new AttributeJpaEntity(attribute);
    }

}
