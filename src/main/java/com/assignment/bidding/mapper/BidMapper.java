package com.assignment.bidding.mapper;

import com.assignment.bidding.dto.BidDto;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.service.ItemService;
import com.assignment.bidding.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {UserService.class, ItemService.class}
)
public interface BidMapper {
    @Mapping(source = "bidderId", target = "bidder")
    @Mapping(source = "itemId", target = "item")
    Bid toBid(BidDto dto);

    @Mapping(source = "bidder.id", target = "bidderId")
    @Mapping(source = "item.id", target = "itemId")
    BidDto toBidDto(Bid bid);
}
