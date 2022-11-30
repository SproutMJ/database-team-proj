package com.example.databaseproject.dto.creditcard.response;

import com.example.databaseproject.domain.creditcard.CreditCardRecord;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CreditCardInfoDTO {
    List<CreditCardRecord> creditCardRecords;
}
