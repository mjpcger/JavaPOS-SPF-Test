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

import de.gmxhome.conrad.jpos.jpos_base.cashdrawer.CashDrawerProperties;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import jpos.JposConst;
import jpos.JposException;
import jpos.MSR;
import jpos.MSRConst;
import jpos.events.DataEvent;

import javax.swing.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for MSR properties, methods and events.
 */
public class MSRController extends CommonController {
    public CheckBox Track1Discretionary;
    public CheckBox Track1Encrypted;
    public TextField Track1;
    public CheckBox Track2Discretionary;
    public CheckBox Track2Encrypted;
    public TextField Track2;
    public CheckBox Track3Encrypted;
    public TextField Track3;
    public CheckBox Track4Encrypted;
    public TextField Track4;
    public ComboBox<String> TrackToRead;
    public ComboBox<String> TrackToWrite;
    public ComboBox<String> DataEncryptionAlgorithm;
    public ComboBox<String> ErrorReportingType;
    public TextField WriteCardType;
    public CheckBox DecodeData;
    public CheckBox ParseDecodeData;
    public CheckBox TransmitSentinels;
    public TextField Title;
    public TextField FirstName;
    public TextField SurName;
    public TextField AccountNumber;
    public TextField ExpirationDate;
    public TextField ServiceCode;
    public TextField MiddleInitial;
    public TextField Suffix;
    public TextField ChallengeResponse;
    public ComboBox<String> RCP_name;
    public TextField RCP_value;
    public TextField UK_key;
    public TextField UK_keyName;
    public ComboBox<String> WT_timeout;

    private MSR TheMsr;
    private PropertyTableRow CardPropertyListRow;
    private PropertyTableRow CapWritableTracksRow;
    private PropertyTableRow ClaimedRow;
    private PropertyTableRow DataEncryptionAlgorithmRow;
    private PropertyTableRow DecodeDataRow;
    private PropertyTableRow ErrorReportingTypeRow;
    private PropertyTableRow ParseDecodeDataRow;
    private PropertyTableRow Track1DataRow;
    private PropertyTableRow Track1DiscretionaryDataRow;
    private PropertyTableRow Track1EncryptedDataRow;
    private PropertyTableRow Track1EncryptedDataLengthRow;
    private PropertyTableRow Track2DataRow;
    private PropertyTableRow Track2DiscretionaryDataRow;
    private PropertyTableRow Track2EncryptedDataRow;
    private PropertyTableRow Track2EncryptedDataLengthRow;
    private PropertyTableRow Track3DataRow;
    private PropertyTableRow Track3EncryptedDataRow;
    private PropertyTableRow Track3EncryptedDataLengthRow;
    private PropertyTableRow Track4DataRow;
    private PropertyTableRow Track4EncryptedDataRow;
    private PropertyTableRow Track4EncryptedDataLengthRow;
    private PropertyTableRow TracksToReadRow;
    private PropertyTableRow TracksToWriteRow;
    private PropertyTableRow TransmitSentinelsRow;
    private PropertyTableRow WriteCardTypeRow;
    private PropertyTableRow AccountNumberRow;
    private PropertyTableRow ExpirationDateRow;
    private PropertyTableRow TitleRow;
    private PropertyTableRow FirstNameRow;
    private PropertyTableRow SurnameRow;
    private PropertyTableRow MiddleInitialRow;
    private PropertyTableRow SuffixRow;
    private PropertyTableRow ServiceCodeRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Ascii;
        super.initialize(url, resourceBundle);
        TheMsr = (MSR) Control;
        TheMsr.addDirectIOListener(this);
        TheMsr.addStatusUpdateListener(this);
        TheMsr.addDataListener(this);
        TheMsr.addErrorListener(this);
        Properties.getItems().add(ClaimedRow = new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceAuthenticated", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("AdditionalSecurityInformation", ""));
        Properties.getItems().add(AccountNumberRow = new PropertyTableRow("AccountNumber", ""));
        Properties.getItems().add(ExpirationDateRow = new PropertyTableRow("ExpirationDate", ""));
        Properties.getItems().add(TitleRow = new PropertyTableRow("Title", ""));
        Properties.getItems().add(FirstNameRow = new PropertyTableRow("FirstName", ""));
        Properties.getItems().add(SurnameRow = new PropertyTableRow("Surname", ""));
        Properties.getItems().add(MiddleInitialRow = new PropertyTableRow("MiddleInitial", ""));
        Properties.getItems().add(SuffixRow = new PropertyTableRow("Suffix", ""));
        Properties.getItems().add(ServiceCodeRow = new PropertyTableRow("ServiceCode", ""));
        Properties.getItems().add(new PropertyTableRow("CapCardAuthentication", ""));
        Properties.getItems().add(new PropertyTableRow("CapDataEncryption", ""));
        Properties.getItems().add(new PropertyTableRow("CapDeviceAuthentication", "", new CapDeviceAuthenticationValues()));
        Properties.getItems().add(new PropertyTableRow("CapISO", ""));
        Properties.getItems().add(new PropertyTableRow("CapJISOne", ""));
        Properties.getItems().add(new PropertyTableRow("CapJISTwo", ""));
        Properties.getItems().add(new PropertyTableRow("CapTrackDataMasking", ""));
        Properties.getItems().add(new PropertyTableRow("CapTransmitSentinels", ""));
        Properties.getItems().add(CapWritableTracksRow = new PropertyTableRow("CapWritableTracks", "", new WritableTracksValues()));
        Properties.getItems().add(new PropertyTableRow("CardAuthenticationData", ""));
        Properties.getItems().add(new PropertyTableRow("CardAuthenticationDataLength", ""));
        Properties.getItems().add(CardPropertyListRow = new PropertyTableRow("CardPropertyList", ""));
        Properties.getItems().add(new PropertyTableRow("CardType", ""));
        Properties.getItems().add(new PropertyTableRow("CardTypeList", ""));
        Properties.getItems().add(DataEncryptionAlgorithmRow = new PropertyTableRow("DataEncryptionAlgorithm", "", new DataEncryptionAlgorithmValues()));
        Properties.getItems().add(DecodeDataRow = new PropertyTableRow("DecodeData", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceAuthenticationProtocol", "", new DeviceAuthenticationProtocolValues()));
        Properties.getItems().add(new PropertyTableRow("EncodingMaxLength", ""));
        Properties.getItems().add(ErrorReportingTypeRow = new PropertyTableRow("ErrorReportingType", "", new ErrorReportingTypeValues()));
        Properties.getItems().add(ParseDecodeDataRow = new PropertyTableRow("ParseDecodeData", ""));
        Properties.getItems().add(Track1DataRow = new PropertyTableRow("Track1Data", ""));
        Properties.getItems().add(Track1DiscretionaryDataRow = new PropertyTableRow("Track1DiscretionaryData", ""));
        Properties.getItems().add(Track1EncryptedDataRow = new PropertyTableRow("Track1EncryptedData", ""));
        Properties.getItems().add(Track1EncryptedDataLengthRow = new PropertyTableRow("Track1EncryptedDataLength", ""));
        Properties.getItems().add(Track2DataRow = new PropertyTableRow("Track2Data", ""));
        Properties.getItems().add(Track2DiscretionaryDataRow = new PropertyTableRow("Track2DiscretionaryData", ""));
        Properties.getItems().add(Track2EncryptedDataRow = new PropertyTableRow("Track2EncryptedData", ""));
        Properties.getItems().add(Track2EncryptedDataLengthRow = new PropertyTableRow("Track2EncryptedDataLength", ""));
        Properties.getItems().add(Track3DataRow = new PropertyTableRow("Track3Data", ""));
        Properties.getItems().add(Track3EncryptedDataRow = new PropertyTableRow("Track3EncryptedData", ""));
        Properties.getItems().add(Track3EncryptedDataLengthRow = new PropertyTableRow("Track3EncryptedDataLength", ""));
        Properties.getItems().add(Track4DataRow = new PropertyTableRow("Track4Data", ""));
        Properties.getItems().add(Track4EncryptedDataRow = new PropertyTableRow("Track4EncryptedData", ""));
        Properties.getItems().add(Track4EncryptedDataLengthRow = new PropertyTableRow("Track4EncryptedDataLength", ""));
        Properties.getItems().add(TracksToReadRow = new PropertyTableRow("TracksToRead", "", new TracksToReadValues()));
        Properties.getItems().add(TracksToWriteRow = new PropertyTableRow("TracksToWrite", "", new WritableTracksValues()));
        Properties.getItems().add(TransmitSentinelsRow = new PropertyTableRow("TransmitSentinels", ""));
        Properties.getItems().add(WriteCardTypeRow = new PropertyTableRow("WriteCardType", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        Values val = new DataEncryptionAlgorithmValues();
        DataEncryptionAlgorithm.getItems().add(val.getSymbol(MSRConst.MSR_DE_NONE));
        DataEncryptionAlgorithm.getItems().add(val.getSymbol(MSRConst.MSR_DE_3DEA_DUKPT));
        val = new ErrorReportingTypeValues();
        ErrorReportingType.getItems().add(val.getSymbol(MSRConst.MSR_ERT_CARD));
        ErrorReportingType.getItems().add(val.getSymbol(MSRConst.MSR_ERT_TRACK));
        val= new TracksToReadValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            TrackToRead.getItems().add((String) val.ValueList[i]);
        setPropertyOnFocusLost(WriteCardType, "WriteCardType");
        WT_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            Values val = new WritableTracksValues();
            Integer writable = val.getInteger(CapWritableTracksRow.getValue());
            if (writable != null && writable != MSRConst.MSR_TR_NONE) {
                TrackToWrite.getItems().clear();
                for (int i = 0; i < val.ValueList.length; i += 2) {
                    if (((Integer) val.ValueList[i] & ~writable) == 0) {
                        TrackToWrite.getItems().add((String) val.ValueList[i + 1]);
                        if (val.ValueList[i + 1].equals(TracksToWriteRow.getValue()))
                            TrackToWrite.setValue(TracksToWriteRow.getValue());
                    }
                }
            }
            if (CardPropertyListRow.getValue() != null && CardPropertyListRow.getValue().length() > 0) {
                String current = RCP_name.getValue();
                for (String name : CardPropertyListRow.getValue().split(",")) {
                    if (!"".equals(name)) {
                        RCP_name.getItems().add(name);
                        if (name.equals(current))
                            RCP_name.setValue(name);
                    }
                }
            }
            DecodeData.setSelected("true".equals(DecodeDataRow.getValue()));
            ParseDecodeData.setSelected("true".equals(ParseDecodeDataRow.getValue()));
            TransmitSentinels.setSelected("true".equals(TransmitSentinelsRow.getValue()));
            val = new IntValues();
            TrackToRead.setValue(val.getInteger(TracksToReadRow.getValue()) != null ? null : TracksToReadRow.getValue());
            TrackToWrite.setValue(val.getInteger(TracksToWriteRow.getValue()) != null ? null : TracksToWriteRow.getValue());
            DataEncryptionAlgorithm.setValue(val.getInteger(DataEncryptionAlgorithmRow.getValue()) != null ? null : DataEncryptionAlgorithmRow.getValue());
            ErrorReportingType.setValue(val.getInteger(ErrorReportingTypeRow.getValue()) != null ? null : ErrorReportingTypeRow.getValue());
            WriteCardType.setText(WriteCardTypeRow.getValue());
            setTrack1(null);
            setTrack2(null);
            setTrack3(null);
            setTrack4(null);
            Title.setText(TitleRow.getValue());
            Title.setDisable(!ParseDecodeData.isSelected());
            FirstName.setText(FirstNameRow.getValue());
            FirstName.setDisable(!ParseDecodeData.isSelected());
            SurName.setText(SurnameRow.getValue());
            SurName.setDisable(!ParseDecodeData.isSelected());
            AccountNumber.setText(AccountNumberRow.getValue());
            AccountNumber.setDisable(!ParseDecodeData.isSelected());
            ExpirationDate.setText(ExpirationDateRow.getValue());
            ExpirationDate.setDisable(!ParseDecodeData.isSelected());
            ServiceCode.setText(ServiceCodeRow.getValue());
            ServiceCode.setDisable(!ParseDecodeData.isSelected());
            MiddleInitial.setText(MiddleInitialRow.getValue());
            MiddleInitial.setDisable(!ParseDecodeData.isSelected());
            Suffix.setText(SuffixRow.getValue());
            Suffix.setDisable(!ParseDecodeData.isSelected());
            InUpdateGui = false;
        }
    }

    public void setTrack1(ActionEvent actionEvent) {
        if (Track1Encrypted.isSelected())
            Track1.setText(Track1EncryptedDataRow.getValue());
        else if (Track1Discretionary.isSelected())
            Track1.setText(Track1DiscretionaryDataRow.getValue());
        else
            Track1.setText(Track1DataRow.getValue());
        if (!InUpdateGui) {
            updateGui();
        }
    }

    public void setTrack2(ActionEvent actionEvent) {
        if (Track2Encrypted.isSelected())
            Track2.setText(Track2EncryptedDataRow.getValue());
        else if (Track2Discretionary.isSelected())
            Track2.setText(Track2DiscretionaryDataRow.getValue());
        else
            Track2.setText(Track2DataRow.getValue());
        if (!InUpdateGui) {
            updateGui();
        }
    }

    public void setTrack3(ActionEvent actionEvent) {
        if (Track3Encrypted.isSelected())
            Track3.setText(Track3EncryptedDataRow.getValue());
        else
            Track3.setText(Track3DataRow.getValue());
        if (!InUpdateGui) {
            updateGui();
        }
    }

    public void setTrack4(ActionEvent actionEvent) {
        if (Track4Encrypted.isSelected())
            Track4.setText(Track4EncryptedDataRow.getValue());
        else
            Track4.setText(Track4DataRow.getValue());
        if (!InUpdateGui) {
            updateGui();
        }
    }

    public void setDecodeData(ActionEvent actionEvent) {
        try {
            TheMsr.setDecodeData(DecodeData.isSelected());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setParseDecodeData(ActionEvent actionEvent) {
        try {
            TheMsr.setParseDecodeData(ParseDecodeData.isSelected());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setTransmitSentinels(ActionEvent actionEvent) {
        try {
            TheMsr.setTransmitSentinels(TransmitSentinels.isSelected());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setTrackToRead(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheMsr.setTracksToRead(new TracksToReadValues().getInteger(TrackToRead.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setTrackToWrite(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheMsr.setTracksToWrite(new WritableTracksValues().getInteger(TrackToWrite.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setDataEncryptionAlgorithm(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheMsr.setDataEncryptionAlgorithm(new DataEncryptionAlgorithmValues().getInteger(DataEncryptionAlgorithm.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setErrorReportingType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheMsr.setErrorReportingType(new ErrorReportingTypeValues().getInteger(ErrorReportingType.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setWriteCardType(ActionEvent ev) {
        try {
            TheMsr.setWriteCardType(WriteCardType.getText());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    class AuthenticateDevice extends MethodProcessor {
        private final byte[] Response;

        AuthenticateDevice(byte[] response) {
            super("AuthenticateDevice");
            Response = response;
        }

        @Override
        void runIt() throws JposException {
            TheMsr.authenticateDevice(Response);
        }
    }

    public void authenticateDevice(ActionEvent actionEvent) {
        byte[] challengeResponse = getResponse();
        if (challengeResponse != null) {
            new AuthenticateDevice(challengeResponse).start();
        }
    }

    class DeauthenticateDevice extends MethodProcessor {
        private final byte[] Response;

        DeauthenticateDevice(byte[] response) {
            super("DeauthenticateDevice");
            Response = response;
        }

        @Override
        void runIt() throws JposException {
            TheMsr.deauthenticateDevice(Response);
        }
    }

    public void deauthenticateDevice(ActionEvent actionEvent) {
        byte[] challengeResponse = getResponse();
        if (challengeResponse != null) {
            new DeauthenticateDevice(challengeResponse).start();
        }
    }

    private byte[] getResponse() {
        return textFieldToByteArray(ChallengeResponse, "response");
    }

    private byte[] textFieldToByteArray(TextField field, String parameter) {
        if (isMethodRunning())
            return null;
        byte[] challengeResponse = new byte[0];
        String data = field.getText().toUpperCase();
        if (data != null && data.length() > 0) {
            boolean valid = data.length() % 2 == 0;
            String hexchars = "0123456789ABCDEF";
            challengeResponse = new byte[data.length() / 2];
            for (int i = 0; valid && i < challengeResponse.length; i++) {
                int high = hexchars.indexOf(data.charAt(i + i)), low = hexchars.indexOf(data.charAt(i + i + 1));
                valid = high >= 0 && low >= 0;
                challengeResponse[i] = (byte) ((high << 4) + low);
            }
            if (invalid(valid ? "" : null, parameter))
                challengeResponse = null;
        }
        return challengeResponse;
    }

    class RetrieveDeviceAuthenticationData extends MethodProcessor {
        private final byte[][] Challenge;

        RetrieveDeviceAuthenticationData(byte[] challenge) {
            super("RetrieveDeviceAuthenticationData");
            Challenge = new byte[][]{challenge};
        }

        @Override
        void runIt() throws JposException {
            TheMsr.retrieveDeviceAuthenticationData(Challenge);
        }

        @Override
        void finish() {
            char[] data = new char[Challenge[0].length * 2];
            for (int i = 0; i < Challenge[0].length; i++) {
                data[i + i] = "0123456789ABCDEF".charAt((Challenge[0][i] >> 4) & 0xf);
                data[i + i + 1] = "0123456789ABCDEF".charAt(Challenge[0][i] & 0xf);
            }
            ChallengeResponse.setText(new String(data));
        }
    }

    public void retrieveDeviceAuthenticationData(ActionEvent actionEvent) {
        byte[] challengeResponse = getResponse();
        if (challengeResponse != null) {
            new RetrieveDeviceAuthenticationData(challengeResponse).start();
        }
    }

    public void retrieveCardProperty(ActionEvent actionEvent) {
        try {
            String[] value = new String[]{""};
            TheMsr.retrieveCardProperty(RCP_name.getValue(), value);
            RCP_value.setText(value[0]);
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
            RCP_value.setText(null);
        }
    }

    public void updateKey(ActionEvent actionEvent) {
        try {
            TheMsr.updateKey(UK_key.getText(), UK_keyName.getText());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    class WriteTracks extends MethodProcessor {
        private final byte[][] Tracks;
        private final int Timeout;

        WriteTracks(byte[][] tracks, int timeout) {
            super("WriteTracks");
            Tracks = tracks;
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            TheMsr.writeTracks(Tracks, Timeout);
        }
    }

    public void writeTracks(ActionEvent actionEvent) {
        if (Track1Encrypted.isSelected() || Track1Discretionary.isSelected() || Track2Encrypted.isSelected() || Track2Discretionary.isSelected() || Track3Encrypted.isSelected() || Track4Encrypted.isSelected()) {
            JOptionPane.showMessageDialog(null, "Neither encrypted nor discretionary track data allowed");
            return;
        }
        if (Conversion != ByteConversion.Hexadecimal) {
            JOptionPane.showMessageDialog(null, "Only hexadecimal conversion allowed for writing track data");
            return;
        }
        byte[][] tracks = new byte[][]{null, null, null, null};
        TextField[] trackdata = new TextField[]{Track1, Track2, Track3, Track4};
        for (int i = 0; i < trackdata.length && i < tracks.length; i++) {
            if ((tracks[i] = textFieldToByteArray(trackdata[i], "Track" + (i + 1))) == null)
                return;
        }
        Integer tio = new TimeoutValues().getInteger(WT_timeout.getValue());
        if (!invalid(tio, "timeout"))
            new WriteTracks(tracks, tio).start();
    }

    private class CapDeviceAuthenticationValues extends Values {
        CapDeviceAuthenticationValues() {
            ValueList = new Object[]{
                    MSRConst.MSR_DA_NOT_SUPPORTED, "DA_NOT_SUPPORTED",
                    MSRConst.MSR_DA_OPTIONAL, "DA_OPTIONAL",
                    MSRConst.MSR_DA_REQUIRED, "DA_REQUIRED"
            };
        }
    }

    private class WritableTracksValues extends TracksToReadValues {
        WritableTracksValues() {
            super();
            ValueList = Arrays.copyOf(ValueList, ValueList.length + 2);
            ValueList[ValueList.length - 2] = MSRConst.MSR_TR_NONE;
            ValueList[ValueList.length - 1] = "TR_NONE";
        }
    }

    private class DeviceAuthenticationProtocolValues extends Values {
        DeviceAuthenticationProtocolValues() {
            ValueList = new Object[]{
                    MSRConst.MSR_AP_NONE, "AP_NONE",
                    MSRConst.MSR_AP_CHALLENGERESPONSE, "MSR_AP_CHALLENGERESPONSE"
            };
        }
    }

    private class ErrorReportingTypeValues extends Values {
        ErrorReportingTypeValues() {
            ValueList = new Object[]{
                    MSRConst.MSR_ERT_CARD, "ERT_CARD",
                    MSRConst.MSR_ERT_TRACK, "ERT_TRACK"
            };
        }
    }

    private class TracksToReadValues extends Values {
        TracksToReadValues() {
            ValueList = new Object[]{
                    MSRConst.MSR_TR_1, "TR_1",
                    MSRConst.MSR_TR_2, "TR_2",
                    MSRConst.MSR_TR_1_2, "TR_1_2",
                    MSRConst.MSR_TR_3, "TR_3",
                    MSRConst.MSR_TR_1_3, "TR_1_3",
                    MSRConst.MSR_TR_2_3, "TR_2_3",
                    MSRConst.MSR_TR_1_2_3, "TR_1_2_3",
                    MSRConst.MSR_TR_4, "TR_4",
                    MSRConst.MSR_TR_1_4, "TR_1_4",
                    MSRConst.MSR_TR_2_4, "TR_2_4",
                    MSRConst.MSR_TR_1_2_4, "TR_1_2_4",
                    MSRConst.MSR_TR_3_4, "TR_3_4",
                    MSRConst.MSR_TR_1_3_4, "TR_1_3_4",
                    MSRConst.MSR_TR_2_3_4, "TR_2_3_4",
                    MSRConst.MSR_TR_1_2_3_4, "TR_1_2_3_4"
            };
        }
    }

    private class DataEncryptionAlgorithmValues extends Values {
        DataEncryptionAlgorithmValues() {
            ValueList = new Object[]{
                    MSRConst.MSR_DE_NONE, "DE_NONE",
                    MSRConst.MSR_DE_3DEA_DUKPT, "DE_3DEA_DUKPT"
            };
        }
    }
}
