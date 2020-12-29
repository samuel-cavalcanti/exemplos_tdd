from TestCase import TestCase


class WasRun(TestCase):
    def __init__(self, name: str):
        TestCase.__init__(self, name)
        self.log = ""

    def testMethod(self):
        self.log += "testMethod "

    def setUp(self):
        self.log = "setUp "
        pass

    def tearDown(self):
        self.log += "tearDown "

    def testBrokenMethod(self):
        raise Exception
