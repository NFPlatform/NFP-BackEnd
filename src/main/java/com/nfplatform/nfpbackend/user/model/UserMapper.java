package com.nfplatform.nfpbackend.user.model;

import com.nfplatform.nfpbackend.user.controller.dto.UserDTO;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import com.nfplatform.nfpbackend.user.repository.entity.UserWithOwnerShipCount;

public class UserMapper {

    public static UserDTO.TopCollector entityWithCountToTopCollector(UserWithOwnerShipCount user) {
        return UserDTO.TopCollector.builder()
                .id(user.getId())
                .name(user.getName())
                .klay(user.getKlay())
                .countOfPiece(user.getCountOfOwnership())
                .build();
    }

    public static UserDTO.TopCollector entityToTopCollector(User user) {
        return UserDTO.TopCollector.builder()
                .id(user.getId())
                .name(user.getName())
                .klay(user.getKlay())
                .countOfPiece(0)
                .build();
    }
}
