package com.jeremydyer.core.factory;

import com.jeremydyer.core.IDX;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jeremydyer on 4/10/15.
 */
public class IDXFactoryTest {

    @Test
    public void testCreateIDXFromReader() {
        BufferedReader br = new BufferedReader(new InputStreamReader(IDXFactoryTest.class.getResourceAsStream("/idx/idx_sample.txt")));
        IDX idx = IDXFactory.createIDXFromReader(br);
    }
}
