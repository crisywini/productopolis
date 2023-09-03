package co.crisi.productopolis.boundaries.output.message;

public interface ISendMessageGateway<T> {

    void sendMessage(T message);

}
