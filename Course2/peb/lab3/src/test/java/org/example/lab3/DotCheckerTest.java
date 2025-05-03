package org.example.lab3;

import org.example.lab3.repository.ResultRepository;
import org.example.lab3.service.CheckHitService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class DotCheckerTest {

    ResultRepository mockRepo = mock(ResultRepository.class);
    CheckHitService service = new CheckHitService(mockRepo);

    @Test
    void testFirstQuarter(){
        assertTrue(service.checkDot(1f, 1f, 3f));
        assertFalse(service.checkDot(4f, -1f, 3f));
    }

    @Test
    void testFourthQuarter(){
        assertTrue(service.checkDot(0.5f, -0.5f, 4f));
        assertFalse(service.checkDot(4f, -1f, 3f));
    }

}
