package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.IImage;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.Image;
import co.crisi.productopolis.domain.factory.IImageFactory;

public class ImageFactory implements IImageFactory {

    @Override
    public IImage create(Long id, String name, String url, IProduct product) {
        return new Image(id, name, url, product);
    }

}
