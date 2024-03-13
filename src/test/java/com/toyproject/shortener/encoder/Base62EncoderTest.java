package com.toyproject.shortener.encoder;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Base62EncoderTest {
    @Test
    public void encoderTestN(){
        // generate test case
        Random random = new Random();
        List<Long> testInputs = new ArrayList<>();
        for(int i=1;i<=10;i++) {
            Long testInput;
            do {
                testInput = random.nextLong();
            } while (testInput <= 0);

            testInputs.add(testInput);
        }

        // encode
        List<String> code = new ArrayList<>();
        for (Long i : testInputs){
            code.add(
                    Base62Encoder.encode(i)
            );
        }

        // decode
        for(int i=0; i<testInputs.size();i++){
            String encode = code.get(i);
            Long plain = testInputs.get(i);

            long decode = Base62Encoder.decode(encode);
            assertEquals(plain, decode);
        }
    }

    @Test
    public void encoderTest1(){
        long plain = 1;
        String code = Base62Encoder.encode(plain);
        Long decode = Base62Encoder.decode(code);

        assertEquals(plain, decode);
    }

}