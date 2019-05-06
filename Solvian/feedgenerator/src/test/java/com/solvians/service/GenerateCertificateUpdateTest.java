package com.solvians.service;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class GenerateCertificateUpdateTest {

    @Test
    public void whenSendingParameterToCallable_thenSuccessful() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(new GenerateCertificateUpdate());
        try {
            assertNotNull(result.get());
            assertEquals(6,result.get().split(",").length);
        } finally {
            executorService.shutdown();
        }
    }

}