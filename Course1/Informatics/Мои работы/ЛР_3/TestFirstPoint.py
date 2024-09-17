import main
import unittest


class TestFindSmile(unittest.TestCase):
    def testNoSmiles(self):
        str = "sfdvfdvfdv||3324!"
        res = 0
        self.assertEqual(res, main.findPat(str))

    def testOneSmile(self):
        str = "scsfvd;<)scsq23435"
        res = 1
        self.assertEqual(res, main.findPat(str))

    def testManySmiles(self):
        str = "232432;<)!!kdsc;<);<)"
        res = 3
        self.assertEqual(res, main.findPat(str))

    def testSmilesInRow(self):
        str = ";<);<);<);<);<)"
        res = 5
        self.assertEqual(res, main.findPat(str))

    def testBrokenSmiles(self):
        str = ";P< );e<);r<)"
        res = 0
        self.assertEqual(res, main.findPat(str))