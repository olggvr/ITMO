package org.example.lab3;

import org.example.lab3.service.CheckHitService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DotCheckerTest {

    @Test
    void testFirstQuarter(){
        assertTrue(CheckHitService.checkDot(1f, 1f, 3f));
        assertFalse(CheckHitService.checkDot(4f, -1f, 3f));
    }

    @Test
    void tesSecondQuarter(){
        assertFalse(CheckHitService.checkDot(-1f, 1f, 1f));
        assertFalse(CheckHitService.checkDot(-2f, 1f, 1f));
    }

    @Test
    void testThirdQuarter(){
        assertFalse(CheckHitService.checkDot(-1f, -0.5f, 4f));
        assertFalse(CheckHitService.checkDot(-1f, -1f, 3f));
    }

    @Test
    void testFourthQuarter(){
        assertTrue(CheckHitService.checkDot(0.5f, -0.5f, 4f));
        assertFalse(CheckHitService.checkDot(4f, -1f, 3f));
    }

}
