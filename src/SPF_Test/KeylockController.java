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

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import jpos.*;

import javax.swing.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for Keylock properties, methods and events.
 */
public class KeylockController extends CommonController {
    public Label KeyValue;
    public ComboBox<String> WFKC_keyPosition;
    public ComboBox<String> WFKC_timeout;

    private Keylock TheLock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Hexadecimal;
        super.initialize(url, resourceBundle);
        TheLock = (Keylock) Control;
        TheLock.addDirectIOListener(this);
        TheLock.addStatusUpdateListener(this);
        StatusUpdateEventStatusValueConverter = new KStatusUpdateValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(KeylockTypeEntry = new PropertyTableRow("CapKeylockType", "", new CapKeylockTypeValues()));
        Properties.getItems().add(EKeyValueEntry = new PropertyTableRow("ElectronicKeyValue", ""));
        Properties.getItems().add(KeyPositionEntry = new PropertyTableRow("KeyPosition", "", new KeyPositionValues()));
        Properties.getItems().add(PositionCount = new PropertyTableRow("PositionCount", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        WFKC_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        updateGui();
    }

    PropertyTableRow KeylockTypeEntry;
    PropertyTableRow KeyPositionEntry;
    PropertyTableRow EKeyValueEntry;
    PropertyTableRow PositionCount;

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            CapKeylockTypeValues kltv = new CapKeylockTypeValues();
            if (KeylockTypeEntry.getValue().equals(kltv.getSymbol(KeylockConst.LOCK_KT_ELECTRONIC))) {
                ByteArrayConversion.setVisible(true);
                KeyValue.setText(EKeyValueEntry.getValue());
            } else {
                ByteArrayConversion.setVisible(false);
                KeyValue.setText(KeylockTypeEntry.getValue().equals(kltv.getSymbol(KeylockConst.LOCK_KT_STANDARD))
                        ? KeyPositionEntry.getValue() : "");
            }
            Integer count = new IntValues().getInteger(PositionCount.getValue());
            if (WFKC_keyPosition.getItems().size() == 0 && count != null && count >= 0) {
                WFKC_keyPositionValues kpv = new WFKC_keyPositionValues();
                for (int i = 0; i <= count; i++) {
                    WFKC_keyPosition.getItems().add(kpv.getSymbol(i));
                }
                WFKC_keyPosition.setValue(kpv.getSymbol(0));
            }
            InUpdateGui = false;
        }
    }

    /**
     * Processor for method DirectIO
     */
    class WaitForKeylockChangeHandler extends MethodProcessor {
        /**
         * Constructor. Stores method name <b>WaitForDrawerCloseHandler</b> and parameters <i>keyPosition, timeout</i>.
         * @param keyPosition Parameter <i>keyPosition</i>
         * @param timeout Parameter <i>timeout</i>
         */
        WaitForKeylockChangeHandler(int keyPosition, int timeout) {
            super("WaitForDrawerClose");
            KeyPosition = keyPosition;
            Timeout = timeout;
        }

        private int KeyPosition;
        private int Timeout;

        @Override
        public void runIt() throws JposException {
            TheLock.waitForKeylockChange(KeyPosition, Timeout);
        }
    }

    public void waitForKeylockChange(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        WFKC_keyPositionValues kpv = new WFKC_keyPositionValues();
        TimeoutValues tiov = new TimeoutValues();
        Integer keyPosition = kpv.getInteger(WFKC_keyPosition.getValue()), timeout = tiov.getInteger(WFKC_timeout.getValue());
        if (!invalid(keyPosition, "keyPosition") && !invalid(timeout, "timeout"))
            new WaitForKeylockChangeHandler(keyPosition, timeout).start();
    }

    private class CapKeylockTypeValues extends Values {
        CapKeylockTypeValues() {
            ValueList = new Object[]{
                    KeylockConst.LOCK_KT_STANDARD, "KT_STANDARD",
                    KeylockConst.LOCK_KT_ELECTRONIC, "KT_ELECTRONIC"
            };
        }
    }

    private class KeyPositionValues extends Values {
        KeyPositionValues() {
            ValueList = new Object[]{
                    KeylockConst.LOCK_KP_ELECTRONIC, "KP_ELECTRONIC",
                    KeylockConst.LOCK_KP_LOCK, "KP_LOCK",
                    KeylockConst.LOCK_KP_NORM, "KP_NORM",
                    KeylockConst.LOCK_KP_SUPR, "KP_SUPR"
            };
        }
    }

    private class WFKC_keyPositionValues extends KeyPositionValues {
        WFKC_keyPositionValues() {
            ValueList[0] = KeylockConst.LOCK_KP_ANY;
            ValueList[1] = "KP_ANY";
        }
    }

    private class KStatusUpdateValues extends StatusUpdateValues {
        KStatusUpdateValues() {
            super();
            Object[] kvalues = new KeyPositionValues().ValueList;
            ValueList = Arrays.copyOf(ValueList, ValueList.length + kvalues.length);
            System.arraycopy(kvalues, 0, ValueList, ValueList.length - kvalues.length, kvalues.length);
        }
    }
}
