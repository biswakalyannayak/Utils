package com.solvians.service;
import static com.solvians.util.CertificateGeneratorUtil.getRandomDoubleBetweenRange;
import static com.solvians.util.CertificateGeneratorUtil.getRandomIntegerBetweenRange;
import static com.solvians.util.CertificateGeneratorUtil.generateIsin;
import com.solvians.domain.CertificateUpdate;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.Callable;

public class GenerateCertificateUpdate implements Callable<String> {

    @Override
    public String call() throws Exception {

        CertificateUpdate certificate = CertificateUpdate.builder().timeStamp(System.currentTimeMillis())
                .askPrice(getRandomDoubleBetweenRange(100.00,200.00))
                .askSize(getRandomIntegerBetweenRange(1000,10000))
                .bidPrice(getRandomDoubleBetweenRange(100.00,200.00))
                .bidSize(getRandomIntegerBetweenRange(1000,10000))
                .ISIN(generateIsin())
                .build();
        return StringUtils.join(certificate.getTimeStamp(),",",certificate.getISIN(),",",certificate.getAskPrice(),",",
                certificate.getAskSize(),",",certificate.getBidPrice(),",",certificate.getBidSize());
    }
}
