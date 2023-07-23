package co.crisi.productopolis.domain.factory;

import co.crisi.productopolis.domain.IImage;
import co.crisi.productopolis.domain.IProduct;

public interface IImageFactory {

    IImage create(Long id, String name, String url, IProduct product);

}
