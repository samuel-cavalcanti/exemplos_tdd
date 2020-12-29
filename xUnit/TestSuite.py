from TestResult import TestResult
from WasRun import WasRun


class TestSuite:
    def __init__(self):
        self.tests: [WasRun] = list()

    def add(self, test: WasRun):
        self.tests.append(test)

    def run(self):
        result = TestResult()
        for test in self.tests:
            test.run(result)

        return result

    @staticmethod
    def fromTestCase(testCase):
        suite = TestSuite()

        for test in list(dir(testCase)):
            if "test" == test[:4]:
                suite.add(testCase(test))

        return suite
