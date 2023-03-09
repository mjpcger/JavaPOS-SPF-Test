/*
 * Copyright 2023 Martin Conrad
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
 *
 */

package SPF_Test;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import jpos.*;

import java.net.URL;
import java.util.*;

public class SmartCardRWController extends CommonController {
    public ComboBox<String> BI_timeout;
    public ComboBox<String> BR_timeout;
    public TextArea RW_data;
    public ComboBox<String> R_action;
    public TextField R_count;
    public ComboBox<String> W_action;
    public TextField W_count;
    public ComboBox<String> InterfaceMode;
    public ComboBox<String> IsoEmvMode;
    public ComboBox<String> SCSlot;
    public TextField TransactionInProgress;
    public ComboBox<String> RW_conversion;
    private SmartCardRW TheSmartCardRW;
    private PropertyTableRow CapInterfaceModeRaw;
    private PropertyTableRow CapIsoEmvModeRow;
    private PropertyTableRow CapSCSlotsRow;
    private PropertyTableRow InterfaceModeRow;
    private PropertyTableRow IsoEmvModeRow;
    private PropertyTableRow SCSlotRow;
    private PropertyTableRow TransactionInProgressRow;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheSmartCardRW = (SmartCardRW) Control;
        TheSmartCardRW.addDirectIOListener(this);
        TheSmartCardRW.addStatusUpdateListener(this);
        TheSmartCardRW.addDataListener(this);
        TheSmartCardRW.addErrorListener(this);
        TheSmartCardRW.addOutputCompleteListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("CapCardErrorDetection", ""));
        Properties.getItems().add(CapInterfaceModeRaw = new PropertyTableRow("CapInterfaceMode", "", new HexValues()));
        Properties.getItems().add(CapIsoEmvModeRow = new PropertyTableRow("CapIsoEmvMode", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapSCPresentSensor", "", new HexValues()));
        Properties.getItems().add(CapSCSlotsRow = new PropertyTableRow("CapSCSlots", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapTransmissionProtocol", "", new CapTransmissionProtocolValues()));
        Properties.getItems().add(InterfaceModeRow = new PropertyTableRow("InterfaceMode", "", new InterfaceModeValues()));
        Properties.getItems().add(IsoEmvModeRow = new PropertyTableRow("IsoEmvMode", "", new IsoEmvModeValues()));
        Properties.getItems().add(new PropertyTableRow("SCPresentSensor", "", new HexValues()));
        Properties.getItems().add(SCSlotRow = new PropertyTableRow("SCSlot", "", new SCSlotValues()));
        Properties.getItems().add(TransactionInProgressRow = new PropertyTableRow("TransactionInProgress", ""));
        Properties.getItems().add(new PropertyTableRow("TransmissionProtocol", "", new TransmissionProtocolValues()));
        Properties.getItems().add(new PropertyTableRow("DataCount", ""));
        Properties.getItems().add(new PropertyTableRow("DataEventEnabled", ""));
        Properties.getItems().add(new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        Values currentValue = new TimeoutValues();
        for (int i = 1; i < currentValue.ValueList.length; i += 2) {
            BI_timeout.getItems().add(currentValue.ValueList[i].toString());
            BR_timeout.getItems().add(currentValue.ValueList[i].toString());
        }
        currentValue = new R_actionValues();
        for (int i = 1; i < currentValue.ValueList.length; i += 2)
            R_action.getItems().add(currentValue.ValueList[i].toString());
        currentValue = new W_actionValues();
        for (int i = 1; i < currentValue.ValueList.length; i += 2)
            W_action.getItems().add(currentValue.ValueList[i].toString());
        RW_conversion.getItems().add("No Conversion");
        RW_conversion.getItems().add("Hexadecimal ASCII");
        RW_conversion.getItems().add("ASCII with \\-Escape");
        updateGui();
    }

    @Override
    void updateGui() {
        boolean again;
        do {
            String oldInterfaceMode = CapInterfaceModeRaw.getValue();
            String oldIsoEmvMode = CapIsoEmvModeRow.getValue();
            String oldSCSlots = CapSCSlotsRow.getValue();
            again = false;
            super.updateGui();
            if (!InUpdateGui) {
                InUpdateGui = true;
                if (!oldInterfaceMode.equals(CapInterfaceModeRaw.getValue())) {
                    again = true;
                    InterfaceModeValues val = (InterfaceModeValues) InterfaceModeRow.getValueConverter();
                    val.updateValueList();
                    InterfaceMode.getItems().clear();
                    for (int i = 1; i < val.ValueList.length; i += 2)
                        InterfaceMode.getItems().add(val.ValueList[i].toString());
                } else
                    InterfaceMode.setValue(InterfaceModeRow.getValue());
                if (!oldIsoEmvMode.equals(CapIsoEmvModeRow.getValue())) {
                    again = true;
                    IsoEmvModeValues val = (IsoEmvModeValues) IsoEmvModeRow.getValueConverter();
                    val.updateValueList();
                    IsoEmvMode.getItems().clear();
                    for (int i = 1; i < val.ValueList.length; i += 2)
                        IsoEmvMode.getItems().add(val.ValueList[i].toString());
                } else
                    IsoEmvMode.setValue(IsoEmvModeRow.getValue());
                if (!oldSCSlots.equals(CapSCSlotsRow.getValue())) {
                    again = true;
                    SCSlotValues val = (SCSlotValues) SCSlotRow.getValueConverter();
                    val.updateValueList();
                    SCSlot.getItems().clear();
                    for (int i = 1; i < val.ValueList.length; i += 2)
                        SCSlot.getItems().add(val.ValueList[i].toString());
                } else
                    SCSlot.setValue(SCSlotRow.getValue());
                TransactionInProgress.setText(TransactionInProgressRow.getValue());
                InUpdateGui = false;
            }
        } while (again);
    }

    private class BeginInsertionHandler extends MethodProcessor {
        private int Timeout;
        public BeginInsertionHandler(Integer timeout) {
            super(null);
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            TheSmartCardRW.beginInsertion(Timeout);
        }
    }

    public void beginInsertion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer timeout = new TimeoutValues().getInteger(BI_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new BeginInsertionHandler(timeout).start();
    }

    private class EndInsertionHandler extends MethodProcessor {
        public EndInsertionHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            TheSmartCardRW.endInsertion();
        }
    }

    public void endInsertion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new EndInsertionHandler().start();
    }

    private class BeginRemovalHandler extends MethodProcessor {
        private int Timeout;
        public BeginRemovalHandler(Integer timeout) {
            super(null);
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            TheSmartCardRW.beginRemoval(Timeout);
        }
    }

    public void beginRemoval(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer timeout = new TimeoutValues().getInteger(BR_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new BeginRemovalHandler(timeout).start();
    }

    private class EndRemovalHandler extends MethodProcessor {
        public EndRemovalHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            TheSmartCardRW.endRemoval();
        }
    }

    public void endRemoval(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new EndRemovalHandler().start();
    }

    private class ReadDataHandler extends MethodProcessor {
        private Integer Action, Count;
        private String Data;
        public ReadDataHandler(int action, Integer count, String data) {
            super(null);
            Action = action;
            Count = count;
            Data = data;
        }

        @Override
        void runIt() throws JposException {
            int[] count = { Count == null ? 0 : Count };
            String[] data = { Data };
            TheSmartCardRW.readData(Action, count, data);
            Count = count[0];
            Data = data[0];
        }

        @Override
        void finish() {
            String encoding = RW_conversion.getValue();
            if ("Hexadecimal ASCII".equals(encoding) && RW_data.getText() != null) {
                byte[] data = new byte[Data.length()];
                for (int k = Data.length() - 1; k >= 0; k--)
                    data[k] = (byte)Data.charAt(k);
                Data = byteArrayToHexString(data, data.length, true, 8);
            }
            else if ("ASCII with \\-Escape".equals(encoding)) {
                byte[] data = new byte[Data.length()];
                for (int k = Data.length() - 1; k >= 0; k--)
                    data[k] = (byte)Data.charAt(k);
                Data = byteArrayToAsciiString(data, 28);
            }
            RW_data.setText(Data);
            R_count.setText(String.valueOf(Count));
            super.finish();
         }
    }

    public void readData(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer action = new R_actionValues().getInteger(R_action.getValue());
        Integer count = new IntValues().getInteger(R_count.getText());
        String data = RW_data.getText();
        String encoding = RW_conversion.getValue();
        if ("Hexadecimal ASCII".equals(encoding) && data != null) {
            byte[] bytes = hexStringToByteArray(data);
            char[] chars = new char[bytes.length];
            for (int i = bytes.length - 1; i >= 0; --i)
                chars[i] = (char)(bytes[i] & 0xff);
            data = new String(chars);
        }
        else if ("ASCII with \\-Escape".equals(encoding) && RW_data.getText() != null) {
            byte[] bytes = asciiStringToByteArray(RW_data.getText());
            char[] chars = new char[bytes.length];
            for (int i = bytes.length - 1; i >= 0; --i)
                chars[i] = (char)(bytes[i] & 0xff);
            data = new String(chars);
        }
        if (!invalid(action, "action"))
            new ReadDataHandler(action, count, data).start();
    }

    private class WriteDataHandler extends MethodProcessor {
        private int Action, Count;
        private String Data;
        public WriteDataHandler(int action, int count, String data) {
            super(null);
            Action = action;
            Count = count;
            Data = data;
        }

        @Override
        void runIt() throws JposException {
            TheSmartCardRW.writeData(Action, Count, Data);
        }
    }

    public void writeData(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer action = new W_actionValues().getInteger(W_action.getValue());
        Integer count = new IntValues().getInteger(W_count.getText());
        String data = RW_data.getText();
        String encoding = RW_conversion.getValue();
        if ("Hexadecimal ASCII".equals(encoding) && RW_data.getText() != null) {
            byte[] bytes = hexStringToByteArray(RW_data.getText());
            char[] chars = new char[bytes.length];
            for (int i = bytes.length - 1; i >= 0; --i)
                chars[i] = (char)(bytes[i] & 0xff);
            data = new String(chars);
        }
        else if ("ASCII with \\-Escape".equals(encoding) && RW_data.getText() != null) {
            byte[] bytes = asciiStringToByteArray(RW_data.getText());
            char[] chars = new char[bytes.length];
            for (int i = bytes.length - 1; i >= 0; --i)
                chars[i] = (char)(bytes[i] & 0xff);
            data = new String(chars);
        }
        if (!invalid(action, "action") && !invalid(count, "count") && !invalid(data, "data"))
            new WriteDataHandler(action, count, data).start();
    }

    public void refresh(ActionEvent actionEvent) {
        if (!InUpdateGui)
            updateGui();
    }

    private class CapTransmissionProtocolValues extends Values {
        CapTransmissionProtocolValues() {
            ValueList = new Object[]{
                    SmartCardRWConst.SC_CTRANS_PROTOCOL_T0, "T0",
                    SmartCardRWConst.SC_CTRANS_PROTOCOL_T1, "T1",
                    SmartCardRWConst.SC_CTRANS_PROTOCOL_T0|
                            SmartCardRWConst.SC_CTRANS_PROTOCOL_T0, "T0 + T1",

            };
        }
    }

    private class R_actionValues extends Values {
        R_actionValues() {
            ValueList = new Object[]{
                    SmartCardRWConst.SC_READ_DATA, "READ_DATA",
                    SmartCardRWConst.SC_READ_PROGRAM, "READ_PROGRAM",
                    SmartCardRWConst.SC_EXECUTE_AND_READ_DATA, "EXECUTE_AND_READ_DATA",
                    SmartCardRWConst.SC_XML_READ_BLOCK_DATA, "XML_READ_BLOCK_DATA"
            };
        }
    }

    private class W_actionValues extends Values {
        W_actionValues() {
            ValueList = new Object[]{
                    SmartCardRWConst.SC_STORE_DATA, "STORE_DATA",
                    SmartCardRWConst.SC_STORE_PROGRAM, "STORE_PROGRAM",
                    SmartCardRWConst.SC_EXECUTE_DATA, "EXECUTE_DATA",
                    SmartCardRWConst.SC_XML_BLOCK_DATA, "XML_BLOCK_DATA",
                    SmartCardRWConst.SC_SECURITY_FUSE, "SECURITY_FUSE",
                    SmartCardRWConst.SC_RESET, "RESET"
            };
        }
    }

    private class InterfaceModeValues extends Values {
        InterfaceModeValues() {
            ValueList = new Object[0];
        }
        void updateValueList() {
            Integer allowed;
            if (CapInterfaceModeRaw == null
                    || (allowed = CapInterfaceModeRaw.getValueConverter().getInteger(CapInterfaceModeRaw.getValue())) == null
                    || allowed == 0) {
                ValueList = new Object[0];
            } else {
                Object[] values = new Object[]{
                        SmartCardRWConst.SC_CMODE_TRANS, SmartCardRWConst.SC_MODE_TRANS, "TRANS",
                        SmartCardRWConst.SC_CMODE_BLOCK, SmartCardRWConst.SC_MODE_BLOCK, "BLOCK",
                        SmartCardRWConst.SC_CMODE_APDU, SmartCardRWConst.SC_MODE_APDU, "APDU",
                        SmartCardRWConst.SC_CMODE_XML, SmartCardRWConst.SC_MODE_XML, "XML"
                };
                int j = 0;
                for (int i = 0; i < values.length; i += 3) {
                    if ((allowed & (Integer)values[i]) != 0) {
                        values[j++] = values[i + 1];
                        values[j++] = values[i + 2];
                    }
                }
                ValueList = Arrays.copyOf(values, j);
            }
        }
    }

    private class IsoEmvModeValues extends Values {
        IsoEmvModeValues() {
            ValueList = new Object[0];
        }
        void updateValueList() {
            Integer allowed;
            if (CapIsoEmvModeRow == null
                    || (allowed = CapIsoEmvModeRow.getValueConverter().getInteger(CapIsoEmvModeRow.getValue())) == null
                    || allowed == 0) {
                ValueList = new Object[0];
            } else {
                Object[] values = new Object[]{
                        SmartCardRWConst.SC_CMODE_ISO, SmartCardRWConst.SC_MODE_ISO, "ISO",
                        SmartCardRWConst.SC_CMODE_EMV, SmartCardRWConst.SC_MODE_EMV, "EMV"
                };
                int j = 0;
                for (int i = 0; i < values.length; i += 3) {
                    if ((allowed & (Integer)values[i]) != 0) {
                        values[j++] = values[i + 1];
                        values[j++] = values[i + 2];
                    }
                }
                ValueList = Arrays.copyOf(values, j);
            }
        }
    }

    private class SCSlotValues extends Values {
        SCSlotValues() {
            ValueList = new Object[0];
        }
        void updateValueList() {
            Integer allowed;
            if (CapSCSlotsRow == null
                    || (allowed = CapSCSlotsRow.getValueConverter().getInteger(CapSCSlotsRow.getValue())) == null
                    || allowed == 0) {
                ValueList = new Object[0];
            } else {
                Object[] values = new Object[Integer.SIZE * 2];
                int j = 0, bit = 1;
                for(int i = 0; allowed != 0; i++) {
                    if ((allowed & bit) != 0) {
                        values[j++] = bit;
                        values[j++] = "Slot " + i;
                        allowed &= ~bit;
                    }
                    bit <<= 1;
                }
                ValueList = Arrays.copyOf(values, j);
            }
        }
    }

    private class TransmissionProtocolValues extends Values {
        TransmissionProtocolValues() {
            ValueList = new Object[]{
                    SmartCardRWConst.SC_TRANS_PROTOCOL_T0, "T0",
                    SmartCardRWConst.SC_TRANS_PROTOCOL_T1, "T1"
            };
        }
    }
}
