package com.micro.ak.orderservice.service;

//import com.micro.ak.orderservice.dto.InventoryResponse;
//import com.micro.ak.orderservice.dto.OrderLineItemsDto;
import com.micro.ak.orderservice.dto.InventoryResponse;
import com.micro.ak.orderservice.dto.OrderLineItemsDto;
import com.micro.ak.orderservice.dto.OrderRequest;
//import com.micro.ak.orderservice.event.OrderPlacedEvent;
import com.micro.ak.orderservice.model.Order;
import com.micro.ak.orderservice.model.OrderLineItems;
import com.micro.ak.orderservice.repository.OrderRepository;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    //INject webclient bean here
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);
        //TO use the webCLient we will have to get all thje order skuCOde

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        //Calling inventory service , and place order if product is in stock
        //We will use webclient for that
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                //We are making a get call so to be able to retrieve the response we will use retireve keyword
                .retrieve()
                //We also need to define the type of response we are getting so we use bodyToMono if you go to controller of inventory it is returning a boolean type so
                //Update we chabegd the type of return for the controller it is now  a list of inventory response from dto so what I am going to do
                //COpy code of InventoryResponse from dt of Inventory service and create a new class with same name in the Order Service dto folder because you cannot access classes from other microservices
                .bodyToMono(InventoryResponse[].class)
                //by adding block it becomnes a synchronous request
                .block();
        //save this response in a local variable so we added boolean result

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);
        if(allProductsInStock) {
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product is not in stock");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
