import main
import unittest


class TestDuplicates(unittest.TestCase):
    def testZeroDuplicateWords(self):
        str = "Lorem Ipsum - это текст-'рыба', часто используемый в печати и вэб-дизайне"
        res = "Lorem Ipsum - это текст-'рыба', часто используемый в печати и вэб-дизайне"
        self.assertEqual(res, main.correctMist(str))

    def testDuplicateWords(self):
        str = "Lorem Lorem Ipsum Ipsum - это это текст текст-'рыба', часто используемый в печати и вэб-дизайне"
        res = "Lorem Ipsum - это текст-'рыба', часто используемый в печати и вэб-дизайне"
        self.assertEqual(res, main.correctMist(str))

    def testSingleWord(self):
        str = "привет привет"
        res = "привет"
        self.assertEqual(res, main.correctMist(str))

    def testZeroWords(self):
        str = ""
        res = ""
        self.assertEqual(res, main.correctMist(str))

    def testDoubleSimbols(self):
        str = "f f g g"
        res = "f g"
        self.assertEqual(res, main.correctMist(str))

