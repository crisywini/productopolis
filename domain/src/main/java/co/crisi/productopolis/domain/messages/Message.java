package co.crisi.productopolis.domain.messages;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Message<T> {

    public abstract String getKey();

    private final T value;

}
