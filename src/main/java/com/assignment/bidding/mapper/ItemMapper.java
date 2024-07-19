package com.assignment.bidding.mapper;

import com.assignment.bidding.dto.ItemDto;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.service.BidService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {BidService.class}
)
public interface ItemMapper {
    Item toItem(ItemDto dto);

    @Mapping(source = "session.winningBid", target = "session.winningBidId")
    ItemDto toItemDto(Item item);

    default Long map(Bid value) {
        return value == null ? null : value.getId();
    }
}
