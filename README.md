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

The first picture shows you the GUI after starting JavaPOS-SPF-Test:

<img src="https://mjpcger.github.io/JavaPOS-SPF-Test/doc/StartScreen.png" alt="Startup Screen">

During startup, JavaPOS-SPF-Test reads the jpos.xml and fills an internal list with all configured devices. The <b>Device Category</b>
combobox will be filled with those categories where at least one device is available. After category selection, the <b>Logical Name</b>
combobox will be filled with the locical device names of that category.

To start testing a specific device, you have to select its device category first, then select the logical device name and start testing.
If there is only one logical device name per category, or of you want to test the first one in the list, you do not need to select it explicitly. After category selection, the first one is selected by default.

After selecting a device category, the test screen for the selected category will be shown. In the upper area, you have methods and properties common for all device categories and a table view where you can find all available property values.  

<img src="https://mjpcger.github.io/JavaPOS-SPF-Test/doc/DeviceScreen.png" alt="Device Screen">

Before you press any method button, you should enter any valid value. Valid means numeric for integer values or one of the string 
values offered by a combobox. Currency, quantity and percentage values will be shown and shall be entered with decimal point. Whether 
a value is really valid of whether a method call is valid depends almost always on the context and if the service generate an error
message or not should be a part of your test. For example, before the device has been opened, only invoking method <b>Open</b> should
generate no error message.

To set writable properties, simply select the new value in case of setting via combobox or checkbox (checkboxes will be used only for
boolean values). If a property can be set via text field, the new value will be set after leaving the field. <b>Keep in mind</b>: If
you click into a property text field, you will get an error message when you leave the field without a valid value or if writing the 
property is invalid in the current device state!

The following screen shots show some kinds of parameter passing for different methods. Which type of method passing has been used, depended
on things like number of methods, number of parameters, number of common parameters for different methods, possiblesize of parameters...

Look and marvel:

<img src="https://mjpcger.github.io/JavaPOS-SPF-Test/doc/MethodScreen1.png" alt="Method calls (1)">

<img src="https://mjpcger.github.io/JavaPOS-SPF-Test/doc/MethodScreen2.png" alt="Method calls (2)">

<img src="https://mjpcger.github.io/JavaPOS-SPF-Test/doc/MethodScreen3.png" alt="Method calls (3)">

<img src="https://mjpcger.github.io/JavaPOS-SPF-Test/doc/MethodScreen4.png" alt="Method calls (4)">

A guide how to install JavaPOS-SPF-Test is <a href="https://mjpcger.github.io/JavaPOS-SPF-Test/doc/Install.html">here</a>.
