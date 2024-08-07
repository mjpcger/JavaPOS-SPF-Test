/*
 * Copyright 2020 Martin Conrad
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package SPF_Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import jpos.*;
import jpos.Scanner;
import jpos.config.*;
import jpos.loader.JposServiceLoader;

import javax.swing.*;
import java.io.File;
import java.util.*;

import static SPF_Test.CommonController.myMessageDialog;

/**
 * Class containing device controls with corresponding name, category and usage description.
 */
public class DeviceControl {
    /**
     * Map of JavaFX controls, each representing a JavaPOS control. One entry per JavaPOS logical name in jpos.xml.
     */
    public static Map<String, DeviceControl> Devices = new HashMap<>(0);

    private BaseJposControl Control;

    /**
     * GUI objects for the specific control
     */
    Parent Gui = null;

    /**
     * The CommonController that handles Control
     */
    CommonController Controller = null;

    /**
     * Retrieves JavaPOS control belonging to this DeviceControl instance.
     */
    public BaseJposControl getControl() {
        return Control;
    }

    private String Name;

    /**
     * Retrieves logicalName bound to the JavaPOS control object of this DeviceControl instance.
     * @return
     */
    public String getName() {
        return Name;
    }

    private String Usage;

    /**
     * Retrieves the value of  the optional JavaPOS property <b>SPF-Test-Usage</b>. Will be displayed on the GUI
     * as a usage hint for user, if present.
     * @return Usage hint if present, otherwise <b>null</b>.
     */
    public String getUsage() {
        return Usage;
    }

    /**
     * Returns a list containing the logical names of all devices specified in jpos.xml.
     * @return List containing all logical names specified in jpos.xml.
     */
    public static ArrayList<String> getNames() {
        ArrayList<String> result = new ArrayList<>();
        for (DeviceControl actdev : Devices.values()) {
            result.add(actdev.Name);
        }
        return result;
    }

    /**
     * Returns a list containing the logical names of all devices in jpos.xml that match the given device category.
     * @param category One of the implemented device categories, e.g. CashDrawer, POSPrinter, ToneIndicator...
     * @return List containing all locical names specified in jpos.xml for devices ofthe given category.
     */
    public static ArrayList<String> getNames(String category) {
        ArrayList<String> result = new ArrayList<>();
        category = "jpos." + category;
        for (DeviceControl actdev : Devices.values()) {
            String clsname = actdev.Control.getClass().getName();
            if (clsname.equals(category)) {
                result.add(actdev.Name);
            }
        }
        return result;
    }

    /**
     * Returns a list containing all device categories of devices used in jpos.xml.
     * @return List containing all devicecategories specified in jpos.xml.
     */
    public static ArrayList<String> getCategories() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Class<?>> help = new ArrayList<>();
        for (DeviceControl actdev : Devices.values()) {
            Class<?> clact = actdev.Control.getClass();
            if (!help.contains(clact)) {
                help.add(clact);
                result.add(clact.getName().substring(5));
            }
        }
        return result;
    }

    /**
     * Helper class to allow passing DeviceControl objects to the initialization method of the corresponding JavaFX
     * control.
     */
    static class DeviceResources extends ListResourceBundle {
        /**
         * DeviceControl to be passed.
         */
        public DeviceControl TheObject;

        /**
         * Constructor. Stores the DeviceControl to be passed to a JavaFX control.
         * @param object DeviceControl to be passed.
         */
        DeviceResources(DeviceControl object) {
            TheObject = object;
        }

        /**
         * Returns the stored DeviceControl as only Object in a one-dimensional array of a one-dimensional array of
         * arrays of Objects.
         * @return
         */
        @Override
        protected Object[][] getContents() {
            return new Object[][]{new Object[]{TheObject}};
        }
    }

    /**
     * Retrieve the configured JavaPOS devices and generate the corresponding DeviceControl objects.
     * @param jposXml Path of the jpos.xml file containing the JavaPOS configuration.
     * @param control JavaFX control of the calling application.
     */
    @SuppressWarnings("unchecked")
    static void loadDevices(Controller control) {

        JposRegPopulator devicegetter = JposServiceLoader.getManager().getRegPopulator();
        Enumeration<JposEntry> entries;
        try {
            entries = devicegetter.getEntries();
            devicegetter.save(entries, "xxxxxx.xml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        entries = devicegetter.getEntries();
        while (entries.hasMoreElements()) {
            JposEntry jdev = entries.nextElement();
            String name = jdev.getLogicalName();
            String category = jdev.getPropertyValue(JposEntry.DEVICE_CATEGORY_PROP_NAME).toString();
            Object usage = jdev.getPropertyValue("SPF-Test-Usage");
            if (name != null && category != null) {
                DeviceControl actdev = new DeviceControl();
                actdev.Name = name;
                actdev.Usage = usage == null ? "" : usage.toString();
                try {
                    if (category.equals("Belt")) {
                        actdev.Control = new Belt();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("Belt.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("BillAcceptor")) {
                        actdev.Control = new BillAcceptor();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("BillAcceptor.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("BillDispenser")) {
                        actdev.Control = new BillDispenser();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("BillDispenser.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("Biometrics")) {
                        actdev.Control = new Biometrics();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("Biometrics.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("BumpBar")) {
                        actdev.Control = new BumpBar();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("BumpBar.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("CashChanger")) {
                        actdev.Control = new CashChanger();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("CashChanger.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("CashDrawer")) {
                        actdev.Control = new CashDrawer();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("CashDrawer.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("CAT")) {
                        actdev.Control = new CAT();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("CAT.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("CheckScanner")) {
                        actdev.Control = new CheckScanner();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("CheckScanner.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("CoinAcceptor")) {
                        actdev.Control = new CoinAcceptor();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("CoinAcceptor.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("CoinDispenser")) {
                        actdev.Control = new CoinDispenser();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("CoinDispenser.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("DeviceMonitor")) {
                        actdev.Control = new DeviceMonitor();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("DeviceMonitor.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("ElectronicJournal")) {
                        actdev.Control = new ElectronicJournal();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("ElectronicJournal.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("ElectronicValueRW")) {
                        actdev.Control = new ElectronicValueRW();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("ElectronicValueRW.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("FiscalPrinter")) {
                        actdev.Control = new FiscalPrinter();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("FiscalPrinter.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("Gate")) {
                        actdev.Control = new Gate();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("Gate.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("GestureControl")) {
                        actdev.Control = new GestureControl();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("GestureControl.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("GraphicDisplay")) {
                        actdev.Control = new GraphicDisplay();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("GraphicDisplay.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("HardTotals")) {
                        actdev.Control = new HardTotals();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("HardTotals.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("ImageScanner")) {
                        actdev.Control = new ImageScanner();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("ImageScanner.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("IndividualRecognition")) {
                        actdev.Control = new IndividualRecognition();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("IndividualRecognition.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("ItemDispenser")) {
                        actdev.Control = new ItemDispenser();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("ItemDispenser.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("Keylock")) {
                        actdev.Control = new Keylock();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("Keylock.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("Lights")) {
                        actdev.Control = new Lights();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("Lights.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("LineDisplay")) {
                        actdev.Control = new LineDisplay();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("LineDisplay.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("MICR")) {
                        actdev.Control = new MICR();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("MICR.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("MotionSensor")) {
                        actdev.Control = new MotionSensor();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("MotionSensor.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("MSR")) {
                        actdev.Control = new MSR();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("MSR.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("PINPad")) {
                        actdev.Control = new PINPad();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("PINPad.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("PointCardRW")) {
                        actdev.Control = new PointCardRW();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("PointCardRW.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("POSKeyboard")) {
                        actdev.Control = new POSKeyboard();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("POSKeyboard.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("POSPower")) {
                        actdev.Control = new POSPower();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("POSPower.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("POSPrinter")) {
                        actdev.Control = new POSPrinter();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("POSPrinter.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("RemoteOrderDisplay")) {
                        actdev.Control = new RemoteOrderDisplay();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("RemoteOrderDisplay.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("RFIDScanner")) {
                        actdev.Control = new RFIDScanner();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("RFIDScanner.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("Scale")) {
                        actdev.Control = new Scale();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("Scale.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("Scanner")) {
                        actdev.Control = new Scanner();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("Scanner.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("SignatureCapture")) {
                        actdev.Control = new SignatureCapture();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("SignatureCapture.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("SmartCardRW")) {
                        actdev.Control = new SmartCardRW();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("SmartCardRW.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("SoundPlayer")) {
                        actdev.Control = new SoundPlayer();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("SoundPlayer.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("SoundRecorder")) {
                        actdev.Control = new SoundRecorder();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("SoundRecorder.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("SpeechSynthesis")) {
                        actdev.Control = new SpeechSynthesis();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("SpeechSynthesis.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("ToneIndicator")) {
                        actdev.Control = new ToneIndicator();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("ToneIndicator.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("VideoCapture")) {
                        actdev.Control = new VideoCapture();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("VideoCapture.fxml"), new DeviceResources(actdev));
                    } else if (category.equals("VoiceRecognition")) {
                        actdev.Control = new VoiceRecognition();
                        actdev.Gui = FXMLLoader.load(control.getClass().getResource("VoiceRecognition.fxml"), new DeviceResources(actdev));
                    } else
                        continue;
                    Devices.put(actdev.Name, actdev);
                } catch (Exception e) {
                    myMessageDialog(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
