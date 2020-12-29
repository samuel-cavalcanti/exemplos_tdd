class TestResult:

    def __init__(self):
        self.errorCount = 0
        self.runCount = 0
        self.setupErrors = ""

    def testStarted(self):
        self.runCount += 1

    def summary(self):
        if self.errorCount == 0 and self.runCount == 0:
            return self.setupErrors

        elif self.setupErrors:
            f"{self.setupErrors}{self.runCount} run, {self.errorCount} failed"

        return f"{self.runCount} run, {self.errorCount} failed"

    def testFailed(self):
        self.errorCount += 1
        pass

    def addSetupError(self, message: str):
        self.setupErrors += message + "\n"
