from TestResult import TestResult


class SetUpException(Exception):
    pass


class TestCase:

    def __init__(self, name: str):
        self.name = name
        pass

    def run(self, result: TestResult):

        try:
            self.setUp()
            result.testStarted()
            method = getattr(self, self.name)
            method()
        except SetUpException:
            result.addSetupError(str(SetUpException))
            result.testFailed()
        except Exception:
            result.testFailed()

        self.tearDown()

    def setUp(self):
        pass

    def tearDown(self):
        pass
