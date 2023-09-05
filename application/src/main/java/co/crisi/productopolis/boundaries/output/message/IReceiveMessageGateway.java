package co.crisi.productopolis.boundaries.output.message;

public interface IReceiveMessageGateway<T>{

    void listen(T message);

}
