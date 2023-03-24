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
import jpos.events.DataEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RFIDScannerController extends CommonController {
    public Label OutputID;
    public TextField ReadTimerInterval;
    public ComboBox<String> ProtocolMask;
    public ComboBox<String> MaskBit;
    public ComboBox<String> ProtocolMaskHelp;
    public ComboBox<String> RT_cmd;
    public ComboBox<String> GMP_timeout;
    public TextArea GMP_password;
    public TextArea RT_filtermask;
    public TextField RT_start;
    public TextField RT_length;
    public TextArea GMP_tagID;
    public TextArea WD_userdata;
    public TextField WD_start;
    public TextArea WI_destID;
    public Label TagCount;
    public Label CurrentTagProtocol;
    public TextArea CurrentTagID;
    public TextArea CurrentTagUserData;

    private RFIDScanner TheRFIDScanner;
    private PropertyTableRow DataCountRow;
    private PropertyTableRow OutputIDRow;
    private PropertyTableRow CurrentTagIDRow;
    private PropertyTableRow CurrentTagProtocolRow;
    private PropertyTableRow CurrentTagUserDataRow;
    private PropertyTableRow ProtocolMaskRow;
    private PropertyTableRow ReadTimerIntervalRow;
    private PropertyTableRow TagCountRow;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheRFIDScanner = (RFIDScanner) Control;
        TheRFIDScanner.addDirectIOListener(this);
        TheRFIDScanner.addStatusUpdateListener(this);
        TheRFIDScanner.addDataListener(this);
        TheRFIDScanner.addErrorListener(this);
        TheRFIDScanner.addOutputCompleteListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(DataCountRow = new PropertyTableRow("DataCount", ""));
        Properties.getItems().add(new PropertyTableRow("DataEventEnabled", ""));
        Properties.getItems().add(OutputIDRow = new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(new PropertyTableRow("ContinuousReadMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapContinuousRead", ""));
        Properties.getItems().add(new PropertyTableRow("CapDisableTag", ""));
        Properties.getItems().add(new PropertyTableRow("CapLockTag", ""));
        Properties.getItems().add(new PropertyTableRow("CapMultipleProtocols", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapReadTimer", ""));
        Properties.getItems().add(new PropertyTableRow("CapWriteTag", "", new CapWriteTagValues()));
        Properties.getItems().add(CurrentTagIDRow = new PropertyTableRow("CurrentTagID", "", new ByteArrayValues()));
        Properties.getItems().add(CurrentTagProtocolRow = new PropertyTableRow("CurrentTagProtocol", "", new CurrentTagProtocolValues()));
        Properties.getItems().add(CurrentTagUserDataRow = new PropertyTableRow("CurrentTagUserData", "", new ByteArrayValues()));
        Properties.getItems().add(ProtocolMaskRow = new PropertyTableRow("ProtocolMask", "", new ProtocolMaskValues()));
        Properties.getItems().add(ReadTimerIntervalRow = new PropertyTableRow("ReadTimerInterval", ""));
        Properties.getItems().add(TagCountRow = new PropertyTableRow("TagCount", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        setPropertyOnFocusLost(ReadTimerInterval, "ReadTimerInterval");
        Values currentvalues = ProtocolMaskRow.getValueConverter();
        for (int i = 1; i < currentvalues.ValueList.length; i += 2)
            ProtocolMask.getItems().add(currentvalues.ValueList[i].toString());
        currentvalues = new CurrentTagProtocolValues();
        for (int i = 1; i < currentvalues.ValueList.length; i += 2)
            MaskBit.getItems().add(currentvalues.ValueList[i].toString());
        currentvalues = new RT_cmdValues();
        for (int i = 1; i < currentvalues.ValueList.length; i += 2)
            RT_cmd.getItems().add(currentvalues.ValueList[i].toString());
        currentvalues = new TimeoutValues();
        for (int i = 1; i < currentvalues.ValueList.length; i += 2)
            GMP_timeout.getItems().add(currentvalues.ValueList[i].toString());
        ProtocolMaskHelp.getItems().addAll("Help",
                "ProtocolMask can be modified directly via hexadecimal entry in field 'ProtocolMask'\n" +
                "or by selecting a mask bit and pressing 'Add Bit' to add the bit to the mask or\n" +
                "'Clear Bit' to remove the bit from the mask. The result will be shown in 'ProtocolMask'\n" +
                "afterwards.\n" +
                "Bit RFID_PR_ALL can only be set via field 'ProtocolMask' directly. Further bits can be\n" +
                "afterwards, if needed for testing, but should not be needed.");
        ProtocolMaskHelp.setValue("Help");
        Conversion = ByteConversion.Hexadecimal;
        MaxConversionLength = Integer.MAX_VALUE;
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            DataCount.setText("DataCount: " + DataCountRow.getValue());
            OutputID.setText(OutputIDRow.getValue());
            ReadTimerInterval.setText(ReadTimerIntervalRow.getValue());
            ProtocolMask.setValue(ProtocolMaskRow.getValue());
            TagCount.setText(TagCountRow.getValue());
            CurrentTagID.setText(CurrentTagIDRow.getValue());
            CurrentTagProtocol.setText(CurrentTagProtocolRow.getValue());
            CurrentTagUserData.setText(CurrentTagUserDataRow.getValue());
            InUpdateGui = false;
        }
    }

    public void setReadTimerInterval(ActionEvent event) {
        if (!InUpdateGui) {
            if (!ReadTimerIntervalRow.getValue().equals(ReadTimerInterval.getText())) {
                try {
                    TheRFIDScanner.setReadTimerInterval(new IntValues().getInteger(ReadTimerInterval.getText()));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setProtocolMask(ActionEvent event) {
        if (!InUpdateGui) {
            if (!ProtocolMaskRow.getValue().equals(ProtocolMask.getValue())) {
                try {
                    TheRFIDScanner.setProtocolMask(ProtocolMaskRow.getValueConverter().getInteger(ProtocolMask.getValue()));
                } catch (JposException e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void addProtocolBit(ActionEvent actionEvent) {
        if (MaskBit.getValue() != null && ProtocolMask.getValue() != null) {
            int bit = new CurrentTagProtocolValues().getInteger(MaskBit.getValue());
            Integer mask = ProtocolMaskRow.getValueConverter().getInteger(ProtocolMask.getValue());
            if (mask != null && (mask & bit) == 0) {
                ProtocolMask.setValue(ProtocolMaskRow.getValueConverter().getSymbol(mask | bit));
                setProtocolMask(actionEvent);
            }
        }
    }

    public void clearProtocolBit(ActionEvent actionEvent) {
        if (MaskBit.getValue() != null && ProtocolMask.getValue() != null) {
            int bit = new CurrentTagProtocolValues().getInteger(MaskBit.getValue());
            Integer mask = ProtocolMaskRow.getValueConverter().getInteger(ProtocolMask.getValue());
            if (mask != null && (mask & bit) == bit) {
                ProtocolMask.setValue(ProtocolMaskRow.getValueConverter().getSymbol(mask & ~bit));
                setProtocolMask(actionEvent);
            }
        }
    }

    public void protokolMaskHelp(ActionEvent actionEvent) {
        ProtocolMaskHelp.setValue("Help");
    }

    public void startReadTags(ActionEvent actionEvent) {
        Integer cmd, start, length;
        byte[] filterID, filtermask, password;
        cmd = new RT_cmdValues().getInteger(RT_cmd.getValue());
        start = new IntValues().getInteger(RT_start.getText());
        length = new IntValues().getInteger(RT_length.getText());
        filterID = hexStringToByteArray(GMP_tagID.getText(), true);
        filtermask = hexStringToByteArray(RT_filtermask.getText(), true);
        password = hexStringToByteArray(GMP_password.getText(), true);
        if (!invalid(cmd, "cmd") && !invalid(filterID, "filterID") && !invalid(filtermask, "filtermask") && !invalid(start, "start") && !invalid(length, "length") && !invalid(password, "password")) {
            try {
                TheRFIDScanner.startReadTags(cmd, filterID, filtermask, start, length, password);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGuiLater();
    }

    public void readTags(ActionEvent actionEvent) {
        Integer cmd, start, length, timeout;
        byte[] filterID, filtermask, password;
        cmd = new RT_cmdValues().getInteger(RT_cmd.getValue());
        start = new IntValues().getInteger(RT_start.getText());
        length = new IntValues().getInteger(RT_length.getText());
        timeout = new TimeoutValues().getInteger(GMP_timeout.getValue());
        filterID = hexStringToByteArray(GMP_tagID.getText(), true);
        filtermask = hexStringToByteArray(RT_filtermask.getText(), true);
        password = hexStringToByteArray(GMP_password.getText(), true);
        if (!invalid(cmd, "cmd") && !invalid(filterID, "filterID") && !invalid(filtermask, "filtermask") && !invalid(start, "start") && !invalid(length, "length") && !invalid(timeout, "timeout") && !invalid(password, "password")) {
            try {
                TheRFIDScanner.readTags(cmd, filterID, filtermask, start, length, timeout, password);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGuiLater();
    }

    public void stopReadTags(ActionEvent actionEvent) {
        byte[] password;
        password = hexStringToByteArray(GMP_password.getText(), true);
        if (!invalid(password, "password")) {
            try {
                TheRFIDScanner.stopReadTags(password);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGuiLater();
    }

    public void lockTag(ActionEvent actionEvent) {
        Integer timeout;
        byte[] tagID, password;
        timeout = new TimeoutValues().getInteger(GMP_timeout.getValue());
        tagID = hexStringToByteArray(GMP_tagID.getText(), true);
        password = hexStringToByteArray(GMP_password.getText(), true);
        if (!invalid(tagID, "tagID") && !invalid(timeout, "timeout") && !invalid(password, "password")) {
            try {
                TheRFIDScanner.lockTag(tagID, timeout, password);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGuiLater();
    }

    public void disableTag(ActionEvent actionEvent) {
        Integer timeout;
        byte[] tagID, password;
        timeout = new TimeoutValues().getInteger(GMP_timeout.getValue());
        tagID = hexStringToByteArray(GMP_tagID.getText(), true);
        password = hexStringToByteArray(GMP_password.getText(), true);
        if (!invalid(tagID, "tagID") && !invalid(timeout, "timeout") && !invalid(password, "password")) {
            try {
                TheRFIDScanner.disableTag(tagID, timeout, password);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGuiLater();
    }

    public void writeTagData(ActionEvent actionEvent) {
        Integer start, timeout;
        byte[] tagID, userdata, password;
        start = new IntValues().getInteger(WD_start.getText());
        timeout = new TimeoutValues().getInteger(GMP_timeout.getValue());
        tagID = hexStringToByteArray(GMP_tagID.getText(), true);
        userdata = hexStringToByteArray(WD_userdata.getText(), true);
        password = hexStringToByteArray(GMP_password.getText(), true);
        if (!invalid(tagID, "tagID") && !invalid(userdata, "userdata") && !invalid(start, "start") && !invalid(timeout, "timeout") && !invalid(password, "password")) {
            try {
                TheRFIDScanner.writeTagData(tagID, userdata, start, timeout, password);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGuiLater();
    }

    public void writeTagID(ActionEvent actionEvent) {
        Integer timeout;
        byte[] sourceID, destID, password;
        timeout = new TimeoutValues().getInteger(GMP_timeout.getValue());
        sourceID = hexStringToByteArray(GMP_tagID.getText(), true);
        destID = hexStringToByteArray(WI_destID.getText(), true);
        password = hexStringToByteArray(GMP_password.getText(), true);
        if (!invalid(sourceID, "sourceID") && !invalid(destID, "destID") && !invalid(timeout, "timeout") && !invalid(password, "password")) {
            try {
                TheRFIDScanner.writeTagID(sourceID, destID, timeout, password);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGuiLater();
    }

    public void firstTag(ActionEvent actionEvent) {
        try {
            TheRFIDScanner.firstTag();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void nextTag(ActionEvent actionEvent) {
        try {
            TheRFIDScanner.nextTag();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void previousTag(ActionEvent actionEvent) {
        try {
            TheRFIDScanner.previousTag();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class CapWriteTagValues extends Values {
        CapWriteTagValues() {
            ValueList = new Object[]{
                    RFIDScannerConst.RFID_CWT_NONE, "CWT_NONE",
                    RFIDScannerConst.RFID_CWT_ID, "CWT_ID",
                    RFIDScannerConst.RFID_CWT_USERDATA, "CWT_USERDATA",
                    RFIDScannerConst.RFID_CWT_ALL, "CWT_ALL"
            };
        }
    }

    private class CurrentTagProtocolValues extends Values {
        CurrentTagProtocolValues() {
            ValueList = new Object[]{
                    RFIDScannerConst.RFID_PR_EPC0, "PR_EPC0",
                    RFIDScannerConst.RFID_PR_0PLUS, "PR_0PLUS",
                    RFIDScannerConst.RFID_PR_EPC1, "PR_EPC1",
                    RFIDScannerConst.RFID_PR_EPC1G2, "PR_EPC1G2",
                    RFIDScannerConst.RFID_PR_EPC2, "PR_EPC2",
                    RFIDScannerConst.RFID_PR_ISO14443A, "PR_ISO14443A",
                    RFIDScannerConst.RFID_PR_ISO14443B, "PR_ISO14443B",
                    RFIDScannerConst.RFID_PR_ISO180006B, "PR_ISO180006B",
                    RFIDScannerConst.RFID_PR_ISO15693, "PR_ISO15693",
                    RFIDScannerConst.RFID_PR_OTHER, "PR_OTHER"
            };
        }
    }

    private class RT_cmdValues extends Values {
        RT_cmdValues() {
            ValueList = new Object[]{
                    RFIDScannerConst.RFID_RT_FULLUSERDATA, "RT_FULLUSERDATA",
                    RFIDScannerConst.RFID_RT_PARTIALUSERDATA, "RT_PARTIALUSERDATA",
                    RFIDScannerConst.RFID_RT_ID, "RT_ID",
                    RFIDScannerConst.RFID_RT_ID_FULLUSERDATA, "RT_ID_FULLUSERDATA",
                    RFIDScannerConst.RFID_RT_ID_PARTIALUSERDATA, "RT_ID_PARTIALUSERDATA"
            };
        }
    }

    private class ProtocolMaskValues extends HexValues {
        ProtocolMaskValues() {
            ValueList = new Object[] {
                    RFIDScannerConst.RFID_PR_ALL, "PR_ALL"
            };
        }
    }

    private class ByteArrayValues extends Values {
        ByteArrayValues() {
            ValueList = new Object[]{ null, "null"};
        }
        @Override
        public String getSymbol(Object value) {
            if (value == ValueList[0])
                return ValueList[1].toString();
            if (value instanceof byte[]) {
                return byteArrayToHexString((byte[]) value, ((byte[]) value).length, false, 100);
            }
            return value.toString();
        }

        public byte[] getByteArray(String symbol) {
            if (ValueList[1].equals(symbol))
                return null;
            return hexStringToByteArray(symbol);
        }
    }
}
