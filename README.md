# JavaPOS-SPF-Test
GUI Based Test Suite for JavaPOS Devices.

This test suite has been implemented to allow testing JavaPOS devices developed with the JavaPOS-SPF framework.
Nevertheless, it can be used for testing any other JavaPOS device, too, as long as the device class is supported.
The advantage compared with other test suites are
- It is possible to hold several devices open at the same time.

- Methods that potentially need some time to complete run in the background. Therefore, the GUI and event handling
remains possible in the meantime.

- There are only very few plausibility checks to allow calling methods with invalid parameters or under invalid conditions,
e.g. when the device has not been claimed or enabled. For example, with other test suites, it might not be possible to test whether
a LineDisplay service implementation throws a JposException with ErrorCode JPOS_E_NOTCLAIMED when you try to set DeviceEnabled
because the test suite blocks setting DeviceEnabled while the device has not been claimed.

- The GUI is very compact. Only devices specified in Jpos.xml will be shown.

For more information, see the Wiki pages.
