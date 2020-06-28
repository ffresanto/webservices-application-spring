package com.estudo.webservices.config;

import com.estudo.webservices.entity.*;
import com.estudo.webservices.entity.enums.OrderStatus;
import com.estudo.webservices.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "Steam Game", 90.9, "");
        Product p2 = new Product(null, "Notebook Acer Aspire Nitro 5", "Personal Computer", 4562.9, "");
        Product p3 = new Product(null, "SmartTV 42 HDMI", "Watch yours channels with very resolution", 2500.9, "");
        Product p4 = new Product(null, "GTA V Online Ps4", "Rockstar Games", 110.9, "");
        Product p5 = new Product(null, "PES2020 Xbox One", "Steam Game", 70.9, "");

        User u1 = new User(null, "Franccesco", "fran@email.com", "11966056314", "123456");
        User u2 = new User(null, "Micael", "micael@email.com", "11966056314", "123456");
        User u3 = new User(null, "Maria", "maria@email.com", "11966056314", "123456");

        Order o1 = new Order(null, Instant.parse("2020-06-07T08:07:00Z"), OrderStatus.PAID,u1);
        Order o2 = new Order(null, Instant.parse("2020-06-08T08:07:00Z"), OrderStatus.WAITING_PAYMENT,u2);
        Order o3 = new Order(null, Instant.parse("2020-06-09T08:07:00Z"), OrderStatus.WAITING_PAYMENT,u3);


        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
        userRepository.saveAll(Arrays.asList(u1, u2, u3));
        orderRepository.saveAll(Arrays.asList(o1, o2 , o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Payment pay1 = new Payment(null, Instant.parse("2020-06-07T10:07:00Z"),o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);
    }
}
