from TestCase import TestCase
from TestResult import TestResult
from TestSuite import TestSuite
from WasRun import WasRun
from WasRunSetupError import WasRunSetupError


class TestCaseTest(TestCase):

    def setUp(self):
        self.result = TestResult()

    def testTemplateMethod(self):
        test = WasRun("testMethod")

        test.run(self.result)

        assert "setUp testMethod tearDown " == test.log
        assert "1 run, 0 failed" == self.result.summary()

    def testFailedResult(self):
        test = WasRun("testBrokenMethod")

        test.run(self.result)

        assert "1 run, 1 failed" == self.result.summary()

    def testFailedResultFormatting(self):
        result = TestResult()
        result.testStarted()
        result.testFailed()

        assert "1 run, 1 failed" == result.summary()

    def testSuite(self):
        suite = TestSuite()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("testBrokenMethod"))
        result = suite.run()
        assert "2 run, 1 failed" == result.summary()

    def testSetUpError(self):
        test = WasRunSetupError("testMethod")

        test.run(self.result)

        assert "0 run, 1 failed" == self.result.summary()

    def testShowSetupErrorException(self):
        self.result.addSetupError("setup error")
        assert "setup error\n" == self.result.summary()

    def testTearDownInBrokenMethod(self):
        test = WasRun("testBrokenMethod")
        test.run(self.result)

        assert "setUp tearDown " == test.log
