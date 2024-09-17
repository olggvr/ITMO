import main
import unittest


class TestIntervals(unittest.TestCase):
    def testThreeSym(self):
        mask = 'мшт'
        dist = 2
        str = "маршрут"
        res = "маршрут"
        self.assertEqual(res, main.findWords(str, mask, dist))

    def testSymInRow(self):
        mask = 'ППП'
        dist = 0
        str = "ППП, ымПлПоП, свысы"
        res = "ППП"
        self.assertEqual(res, main.findWords(str, mask, dist))

    def testSymWithSpace(self):
        mask = 'ТРА'
        dist = 1
        str = "Т Р А, ыма, ТРА"
        res = ""
        self.assertEqual(res, main.findWords(str, mask, dist))

    def testSymThree(self):
        mask = 'ТРА'
        dist = 0
        str = "ымааввм, ТРАТРАТРА, ТРА, ТывРттА"
        res = "ТРАТРАТРА ТРА"
        self.assertEqual(res, main.findWords(str, mask, dist))

    def testOrder(self):
        mask = 'ТРА'
        dist = 2
        str = "ыавмавы, ТууРааА, РфыАррТ, 1232"
        res = "ТууРааА"
        self.assertEqual(res, main.findWords(str, mask, dist))