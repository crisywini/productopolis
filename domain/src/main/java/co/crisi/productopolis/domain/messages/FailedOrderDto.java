package co.crisi.productopolis.domain.messages;

public record FailedOrderDto(Long orderId, String errorMessage) {

}
