from teamcity.messages import TeamcityServiceMessages

__author__ = 'Leonid.Bushuev'


TCM = TeamcityServiceMessages()


def produce_messages():
    print("This message is outputted before the suite")
    TCM.testSuiteStarted("CopperSuite")

    TCM.testStarted("StonePickerTest")
    TCM.testStdOut("StonePickerTest", "This stone hammer is cool!")
    TCM.testFinished("StonePickerTest")

    TCM.testStarted("ShovelTest")
    TCM.testStdOut("ShovelTest", "This shovel was broken yesterday :(")
    TCM.testFailed("ShovelTest", message="The shovel is broken :(")
    TCM.testFinished("ShovelTest")

    TCM.testSuiteFinished("CopperSuite")
    print("This message is outputted after the suite")





if __name__ == "__main__":
    produce_messages()
