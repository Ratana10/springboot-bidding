package com.assignment.bidding.config;

import com.assignment.bidding.enums.Role;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.User;
import com.assignment.bidding.repository.ItemRepository;
import com.assignment.bidding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDatabase implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {

        List<User> all = userRepository.findAll();
        if (all.isEmpty()) {
            User client1 = User.builder()
                    .username("client1")
                    .password("client1")
                    .email("client1@gmail.com")
                    .role(Role.CLIENT)
                    .build();

            User client2 = User.builder()
                    .username("client2")
                    .password("client2")
                    .email("client2@gmail.com")
                    .role(Role.CLIENT)
                    .build();

            User bidder1 = User.builder()
                    .username("bidder1")
                    .password("bidder1")
                    .email("bidder1@gmail.com")
                    .role(Role.BIDDER)
                    .build();

            User bidder2 = User.builder()
                    .username("bidder2")
                    .password("bidder2")
                    .email("bidder2@gmail.com")
                    .role(Role.BIDDER)
                    .build();

            User bidder3 = User.builder()
                    .username("bidder3")
                    .password("bidder3")
                    .email("bidder3@gmail.com")
                    .role(Role.BIDDER)
                    .build();
            List<User> list = List.of(client1, client2, bidder1, bidder2, bidder3);
            userRepository.saveAll(list);

            Item item1 = Item.builder()
                    .name("Shoes")
                    .price(BigDecimal.valueOf(10))
                    .status("ACTIVE")
                    .build();

            Item item2 = Item.builder()
                    .name("Laptop")
                    .price(BigDecimal.valueOf(10))
                    .status("ACTIVE")
                    .build();

            List<Item> items = List.of(item1, item2);
            itemRepository.saveAll(items);
        }
    }
}
