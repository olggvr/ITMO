package org.example.lab3;

import org.example.lab3.service.CheckHitService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DotCheckerTest {

    private final CheckHitService checkHitService = new CheckHitService();

    @Test
    void testFirstQuarter(){
        assertTrue(checkHitService.checkDot(1f, 1f, 3f));
        assertFalse(checkHitService.checkDot(4f, 1f, 3f));
    }

    @Test
    void testFourthQuarter(){
        assertTrue(checkHitService.checkDot(1.5f, -1f, 3f));
        assertFalse(checkHitService.checkDot(4f, -1f, 3f));
    }

}
