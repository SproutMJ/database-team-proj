package com.example.databaseproject.dto.account.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OwnerAccountsDTO {
    private String ownerName;
    private List<AccountInfoDTO> accountInfoDTOs;
}
