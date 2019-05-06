package com.solvians.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CertificateUpdate {

    private Long timeStamp;
    private String ISIN;
    private Double bidPrice;
    private Integer bidSize;
    private Double askPrice;
    private Integer askSize;

}
