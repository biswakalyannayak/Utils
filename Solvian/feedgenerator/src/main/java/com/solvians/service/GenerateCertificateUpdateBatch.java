package com.solvians.service;

import com.solvians.domain.CertificateUpdate;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.solvians.util.CertificateGeneratorUtil.*;

public class GenerateCertificateUpdateBatch implements Callable<List<String>> {

    private int count;

    public GenerateCertificateUpdateBatch(int certificateCount) {
        this.count = count;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> certificateList = new ArrayList<>();
        for (int i = 0; i<= count; i++){
            CertificateUpdate certificate = CertificateUpdate.builder().timeStamp(System.currentTimeMillis())
                    .askPrice(getRandomDoubleBetweenRange(100.00,200.00))
                    .askSize(getRandomIntegerBetweenRange(1000,10000))
                    .bidPrice(getRandomDoubleBetweenRange(100.00,200.00))
                    .bidSize(getRandomIntegerBetweenRange(1000,10000))
                    .ISIN(generateIsin())
                    .build();
            certificateList.add(StringUtils.join(certificate.getTimeStamp(),",",certificate.getISIN(),",",certificate.getAskPrice(),",",
                    certificate.getAskSize(),",",certificate.getBidPrice(),",",certificate.getBidSize()));
        }
        return certificateList;
    }
}
