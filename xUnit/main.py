from TestCaseTest import TestCaseTest
from TestSuite import TestSuite


def main():
    suite = TestSuite.fromTestCase(TestCaseTest)

    result = suite.run()

    print("Test CaseTest", result.summary())


if __name__ == '__main__':
    main()