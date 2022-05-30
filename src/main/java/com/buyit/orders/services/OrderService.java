package com.buyit.orders.services;

import com.buyit.orders.entities.OrderEntity;
import com.buyit.orders.repositories.OrderRepository;
import com.buyit.billing.address.DTOs.BillingAddressDTO;
import com.buyit.billing.address.entities.BillingAddressEntity;
import com.buyit.billing.address.repositories.BillingAddressRepository;
import com.buyit.cart.DTOs.CartDTO;
import com.buyit.cart.services.CartService;
import com.buyit.orders.DTOs.CreateOrderDTO;
import com.buyit.orders.DTOs.OrderDTO;
import com.buyit.shipping.address.DTOs.ShippingAddressDTO;
import com.buyit.shipping.address.entities.ShippingAddressEntity;
import com.buyit.shipping.address.repositories.ShippingAddressRepository;
import com.buyit.customer.DTOs.CustomerDTO;
import com.buyit.customer.entities.CustomerEntity;
import com.buyit.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ShippingAddressRepository shippingAddressRepository;
    private final BillingAddressRepository billingAddressRepository;
    private final CustomerRepository userRepository;
    private final CartService cartService;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ShippingAddressRepository shippingAddressRepository,
                        BillingAddressRepository billingAddressRepository,
                        CustomerRepository userRepository,
                        CartService cartService,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.shippingAddressRepository = shippingAddressRepository;
        this.billingAddressRepository = billingAddressRepository;
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.customerRepository = customerRepository;
    }

    public OrderEntity makeNewOrder(String username, CreateOrderDTO createOrderDTO) {

        CustomerEntity customerEntity = this.customerRepository.findByUsername(username);

        int userId = customerEntity.getId();
        int cartId = customerEntity.getCartId();

        OrderEntity orderEntity = new OrderEntity();

        List<OrderEntity> orderEntityList = this.orderRepository.findAll();
        int size = orderEntityList.size() + 1;

        orderEntity.setId(size);
        orderEntity.setCartId(cartId);
        orderEntity.setCustomerId(userId);
        orderEntity.setShippingAddressId(createOrderDTO.getShippingAddressId());
        orderEntity.setBillingAddressId(createOrderDTO.getBillingAddressId());

        cartService.addNewUserCart(username);
        return this.orderRepository.save(orderEntity);
    }

    public List<OrderDTO> getOrdersByUsername(String username) {

        CustomerEntity customerEntity = this.customerRepository.findByUsername(username);

        int userId = customerEntity.getId();

        List<OrderEntity> orderEntityList = this.orderRepository.findByCustomerId(userId);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (OrderEntity orderEntity : orderEntityList) {
            OrderDTO orderDTO = new OrderDTO();

            CartDTO cartDTO = this.cartService.getCartById(orderEntity.getCartId());

            Optional<ShippingAddressEntity> shippingAddressEntity = this.shippingAddressRepository
                    .findById(orderEntity.getShippingAddressId());

            Optional<BillingAddressEntity> billingAddressEntity = this.billingAddressRepository
                    .findById(orderEntity.getBillingAddressId());

            Optional<CustomerEntity> userEntity = this.userRepository
                    .findById(orderEntity.getCustomerId());

            if (shippingAddressEntity.isEmpty()
                    || billingAddressEntity.isEmpty()
                    || userEntity.isEmpty()) {
                return null;
            }

            ShippingAddressDTO shippingAddressDTO = new ShippingAddressDTO(shippingAddressEntity.get().getCity(), shippingAddressEntity.get().getAddress());
            BillingAddressDTO billingAddressDTO = new BillingAddressDTO(billingAddressEntity.get().getCity(), billingAddressEntity.get().getAddress());
            CustomerDTO userDTO = new CustomerDTO(userEntity.get().getUsername(), userEntity.get().getFirstname(), userEntity.get().getLastname());

            orderDTO.setCartDTO(cartDTO);
            orderDTO.setUserDTO(userDTO);
            orderDTO.setShippingAddressDTO(shippingAddressDTO);
            orderDTO.setBillingAddressDTO(billingAddressDTO);

            orderDTOList.add(orderDTO);
        }

        return orderDTOList;
    }
}
