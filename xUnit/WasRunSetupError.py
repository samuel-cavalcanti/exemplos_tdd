from TestCase import SetUpException
from WasRun import WasRun


class WasRunSetupError(WasRun):
    def setUp(self):
        raise SetUpException("setup error")
