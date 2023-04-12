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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jpos.JposConst;
import jpos.JposException;
import jpos.PINPad;
import jpos.PINPadConst;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PINPadController extends CommonController {
    public CheckBox PINEntryEnabled;
    public ComboBox<String> PromptLanguage;
    public ComboBox<String> Prompt;
    public Label PromptLabel;
    public TextField MaximumPINLength;
    public TextField MinimumPINLength;
    public TextField EncryptedPIN;
    public TextField AdditionalSecurityInformation;
    public TextField AccountNumber;
    public TextField Amount;
    public TextField MerchantID;
    public TextField TerminalID;
    public ComboBox<String> TransactionType;
    public TextField Track1Data;
    public TextField Track2Data;
    public TextField Track3Data;
    public TextField Track4Data;
    public ComboBox<String> BET_system;
    public TextField BET_host;
    public TextField CM_inMsg;
    public TextField CM_outMsg;
    public TextField VM_message;
    public TextField UK_keyNum;
    public TextField UK_key;
    public ComboBox<String> EET_completionCode;
    private PINPad ThePINPad;
    private PropertyTableRow DataCountRow;
    private PropertyTableRow PINEntryEnabledRow;
    private PropertyTableRow EncryptedPINRow;
    private PropertyTableRow AdditionalSecurityInformationRow;
    private PropertyTableRow AvailableLanguagesListRow;
    private PropertyTableRow AvailablePromptsListRow;
    private PropertyTableRow CapDisplayRow;
    private PropertyTableRow CapLanguageRow;
    private PropertyTableRow TransactionTypeRow;
    private PropertyTableRow AccountNumberRow;
    private PropertyTableRow AmountRow;
    private PropertyTableRow Track1DataRow;
    private PropertyTableRow Track2DataRow;
    private PropertyTableRow Track3DataRow;
    private PropertyTableRow Track4DataRow;
    private PropertyTableRow MerchantIDRow;
    private PropertyTableRow TerminalIDRow;
    private PropertyTableRow MaximumPINLengthRow;
    private PropertyTableRow MinimumPINLengthRow;
    private PropertyTableRow PromptRow;
    private PropertyTableRow PromptLanguageRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Hexadecimal;
        MaxConversionLength = Integer.MAX_VALUE;
        super.initialize(url, resourceBundle);
        ThePINPad = (PINPad) Control;
        ThePINPad.addDirectIOListener(this);
        ThePINPad.addStatusUpdateListener(this);
        ThePINPad.addDataListener(this);
        ThePINPad.addErrorListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(DataCountRow = new PropertyTableRow("DataCount", ""));
        Properties.getItems().add(new PropertyTableRow("DataEventEnabled", ""));
        Properties.getItems().add(PINEntryEnabledRow = new PropertyTableRow("PINEntryEnabled", ""));
        Properties.getItems().add(EncryptedPINRow = new PropertyTableRow("EncryptedPIN", ""));
        Properties.getItems().add(AdditionalSecurityInformationRow = new PropertyTableRow("AdditionalSecurityInformation", ""));
        Properties.getItems().add(AvailableLanguagesListRow = new PropertyTableRow("AvailableLanguagesList", ""));
        Properties.getItems().add(AvailablePromptsListRow = new PropertyTableRow("AvailablePromptsList", ""));
        Properties.getItems().add(CapDisplayRow = new PropertyTableRow("CapDisplay", "", new CapDisplayValues()));
        Properties.getItems().add(new PropertyTableRow("CapKeyboard", ""));
        Properties.getItems().add(CapLanguageRow = new PropertyTableRow("CapLanguage", "", new CapLanguageValues()));
        Properties.getItems().add(new PropertyTableRow("CapMACCalculation", ""));
        Properties.getItems().add(new PropertyTableRow("CapTone", ""));
        Properties.getItems().add(TransactionTypeRow = new PropertyTableRow("TransactionType", "", new TransactionTypeValues()));
        Properties.getItems().add(AccountNumberRow = new PropertyTableRow("AccountNumber", ""));
        Properties.getItems().add(AmountRow = new PropertyTableRow("Amount", ""));
        Properties.getItems().add(Track1DataRow = new PropertyTableRow("Track1Data", ""));
        Properties.getItems().add(Track2DataRow = new PropertyTableRow("Track2Data", ""));
        Properties.getItems().add(Track3DataRow = new PropertyTableRow("Track3Data", ""));
        Properties.getItems().add(Track4DataRow = new PropertyTableRow("Track4Data", ""));
        Properties.getItems().add(MerchantIDRow = new PropertyTableRow("MerchantID", ""));
        Properties.getItems().add(TerminalIDRow = new PropertyTableRow("TerminalID", ""));
        Properties.getItems().add(MaximumPINLengthRow = new PropertyTableRow("MaximumPINLength", ""));
        Properties.getItems().add(MinimumPINLengthRow = new PropertyTableRow("MinimumPINLength", ""));
        Properties.getItems().add(PromptRow = new PropertyTableRow("Prompt", "", new PromptValues()));
        Properties.getItems().add(PromptLanguageRow = new PropertyTableRow("PromptLanguage", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        CurrencyDigits.getItems().add(0);
        CurrencyDigits.getItems().add(1);
        CurrencyDigits.getItems().add(2);
        CurrencyDigits.getItems().add(3);
        CurrencyDigits.getItems().add(4);
        CurrencyDigits.setValue(2);
        for (int i = 1; i < TransactionTypeRow.getValueConverter().ValueList.length; i+= 2)
            TransactionType.getItems().add(TransactionTypeRow.getValueConverter().ValueList[i].toString());
        String[] systems = {"M/S", "DUKPT", "APACS40", "AS2805", "HGEPOS", "JDEBIT2"};
        for (String system : systems)
            BET_system.getItems().add(system);
        Object[] val = new CompletionCodeValues().ValueList;
        for (int i = 1; i < val.length; i += 2)
            EET_completionCode.getItems().add(val[i].toString());
        setPropertyOnFocusLost(MaximumPINLength, "MaximumPINLength");
        setPropertyOnFocusLost(MinimumPINLength, "MinimumPINLength");
        setPropertyOnFocusLost(EncryptedPIN, "EncryptedPIN");
        setPropertyOnFocusLost(AdditionalSecurityInformation, "AdditionalSecurityInformation");
        setPropertyOnFocusLost(AccountNumber, "AccountNumber");
        setPropertyOnFocusLost(Amount, "Amount");
        setPropertyOnFocusLost(MerchantID, "MerchantID");
        setPropertyOnFocusLost(TerminalID, "TerminalID");
        setPropertyOnFocusLost(Track1Data, "Track1Data");
        setPropertyOnFocusLost(Track2Data, "Track2Data");
        setPropertyOnFocusLost(Track3Data, "Track3Data");
        setPropertyOnFocusLost(Track4Data, "Track4Data");
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            ByteArrayConversion.getItems().remove(ByteConversion.Length);
            PINEntryEnabled.setSelected("true".equals(PINEntryEnabledRow.getValue().toLowerCase()));
            if (!CapDisplayRow.getValueConverter().getSymbol(PINPadConst.PPAD_DISP_NONE).equals(CapDisplayRow.getValue()) &&
                !CapDisplayRow.getValueConverter().getSymbol(PINPadConst.PPAD_DISP_UNRESTRICTED).equals(CapDisplayRow.getValue())) {
                if (Prompt.getItems().size() == 0 && AvailablePromptsListRow.getValue().length() > 0) {
                    String[] promptsstr = AvailablePromptsListRow.getValue().split(",");
                    Values vals = PromptRow.getValueConverter();
                    for (String promptstr : promptsstr) {
                        try {
                            String symbol = vals.getSymbol(Integer.parseInt(promptstr));
                            String label = symbol.length() > 4 && symbol.substring(0, 4).equals("MSG_") ? symbol.substring(4) : symbol;
                            Prompt.getItems().add(label);
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
                String label = PromptRow.getValue();
                if (label.length() > 4 && label.substring(0, 4).equals("MSG_"))
                    label = label.substring(4);
                Prompt.setValue(label);
                Prompt.setVisible(true);
                if (PromptLanguage.getItems().size() == 0 && AvailableLanguagesListRow.getValue().length() > 0) {
                    String[] langsstr = AvailableLanguagesListRow.getValue().split(";");
                    Map<String,String> langmap = new HashMap<>();
                    for (String langstr : langsstr) {
                        String[] langspec = langstr.split(",");
                        if (!langmap.containsKey(langspec[0])) {
                            langmap.put(langspec[0], langspec[0]);
                            PromptLanguage.getItems().add(langspec[0]);
                            if (PromptLanguageRow.getValue().equals(langspec[0]))
                                PromptLanguage.setValue(langspec[0]);

                        }
                        PromptLanguage.getItems().add(langstr);
                        if (PromptLanguageRow.getValue().equals(langstr))
                            PromptLanguage.setValue(langstr);
                    }
                }
                PromptLanguage.setValue(PromptLanguageRow.getValue());
                PromptLanguage.setVisible(true);
                PromptLabel.setVisible(true);
            } else {
                Prompt.setVisible(false);
                PromptLanguage.setVisible(false);
                PromptLabel.setVisible(false);
                Prompt.getItems().clear();
                PromptLanguage.getItems().clear();
            }
            MaximumPINLength.setText(MaximumPINLengthRow.getValue());
            MinimumPINLength.setText(MinimumPINLengthRow.getValue());
            EncryptedPIN.setText(EncryptedPINRow.getValue());
            EncryptedPIN.setDisable(EncryptedPINRow.getValue().length() == 0);
            AdditionalSecurityInformation.setText(AdditionalSecurityInformationRow.getValue());
            AdditionalSecurityInformation.setDisable(EncryptedPINRow.getValue().length() == 0);
            AccountNumber.setText(AccountNumberRow.getValue());
            PropertyTableRow dummy = new PropertyTableRow("", AmountRow.getValue());
            rowValue2Decimal(dummy);
            Amount.setText(dummy.getValue());
            MerchantID.setText(MerchantIDRow.getValue());
            TerminalID.setText(TerminalIDRow.getValue());
            TransactionType.setValue(TransactionTypeRow.getValue());
            Track1Data.setText(Track1DataRow.getValue());
            Track2Data.setText(Track2DataRow.getValue());
            Track3Data.setText(Track3DataRow.getValue());
            Track4Data.setText(Track4DataRow.getValue());
            InUpdateGui = false;
        }
    }

    public void setPromptLanguage(ActionEvent actionEvent) {
        if (!InUpdateGui && !PromptLanguageRow.getValue().equals(PromptLanguage.getValue())) {
            try {
                if (ThePINPad.getState() != JposConst.JPOS_S_CLOSED)
                    ThePINPad.setPromptLanguage(PromptLanguage.getValue());
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setPrompt(ActionEvent actionEvent) {
        Values vals = PromptRow.getValueConverter();
        Integer prompt = vals.getInteger("MSG_" + Prompt.getValue());
        if (prompt == null)
            prompt = vals.getInteger(Prompt.getValue());
        if (!InUpdateGui && prompt != null && !prompt.equals(vals.getInteger(PromptRow.getValue()))) {
            try {
                if (ThePINPad.getState() != JposConst.JPOS_S_CLOSED)
                    ThePINPad.setPrompt(prompt);
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setMaximumPINLength(ActionEvent actionEvent) {
        if (!InUpdateGui && !MaximumPINLengthRow.getValue().equals(MaximumPINLength.getText())) {
            try {
                if (ThePINPad.getState() != JposConst.JPOS_S_CLOSED)
                    ThePINPad.setMaximumPINLength(Integer.parseInt(MaximumPINLength.getText()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setMinimumPINLength(ActionEvent actionEvent) {
        if (!InUpdateGui && !MinimumPINLengthRow.getValue().equals(MinimumPINLength.getText())) {
            try {
                if (ThePINPad.getState() != JposConst.JPOS_S_CLOSED)
                    ThePINPad.setMinimumPINLength(Integer.parseInt(MinimumPINLength.getText()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setEncryptedPIN(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            EncryptedPIN.setText(EncryptedPINRow.getValue());
        }
    }

    public void setAdditionalSecurityInformation(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            AdditionalSecurityInformation.setText(AdditionalSecurityInformationRow.getValue());
        }
    }

    public void setAccountNumber(ActionEvent actionEvent) {
        if (!InUpdateGui && ThePINPad.getState() != JposConst.JPOS_S_CLOSED && !AccountNumberRow.getValue().equals(AccountNumber.getText())) {
            try {
                ThePINPad.setAccountNumber(AccountNumber.getText());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setAmount(ActionEvent actionEvent) {
        if (!InUpdateGui && ThePINPad.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                long amount = getDecimalRowValue(new PropertyTableRow("", Amount.getText()));
                ThePINPad.setAmount(amount);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setMerchantID(ActionEvent actionEvent) {
        if (!InUpdateGui && ThePINPad.getState() != JposConst.JPOS_S_CLOSED && !MerchantIDRow.getValue().equals(MerchantID.getText())) {
            try {
                ThePINPad.setMerchantID(MerchantID.getText());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setTerminalID(ActionEvent actionEvent) {
        if (!InUpdateGui && ThePINPad.getState() != JposConst.JPOS_S_CLOSED && !TerminalIDRow.getValue().equals(TerminalID.getText())) {
            try {
                ThePINPad.setTerminalID(TerminalID.getText());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setTransactionType(ActionEvent actionEvent) {
        Integer newValue = TransactionTypeRow.getValueConverter().getInteger(TransactionType.getValue());
        Integer currentValue = TransactionTypeRow.getValueConverter().getInteger(TransactionTypeRow.getValue());
        if (!InUpdateGui && ThePINPad.getState() != JposConst.JPOS_S_CLOSED && newValue != null && !newValue.equals(currentValue)) {
            try {
                ThePINPad.setTransactionType(newValue);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setTrack1Data(ActionEvent actionEvent) {
        if (!InUpdateGui && ThePINPad.getState() != JposConst.JPOS_S_CLOSED && !Track1DataRow.getValue().equals(Track1Data.getText())) {
            try {
                if (Conversion == ByteConversion.Hexadecimal)
                    ThePINPad.setTrack1Data(hexStringToByteArray(Track1Data.getText()));
                else if (Conversion == ByteConversion.Ascii)
                    ThePINPad.setTrack1Data(asciiStringToByteArray(Track1Data.getText()));
                else
                    ThePINPad.setTrack1Data(Track1Data.getText().getBytes());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setTrack2Data(ActionEvent actionEvent) {
        if (!InUpdateGui && ThePINPad.getState() != JposConst.JPOS_S_CLOSED && !Track2DataRow.getValue().equals(Track2Data.getText())) {
            try {
                if (Conversion == ByteConversion.Hexadecimal)
                    ThePINPad.setTrack2Data(hexStringToByteArray(Track2Data.getText()));
                else if (Conversion == ByteConversion.Ascii)
                    ThePINPad.setTrack2Data(asciiStringToByteArray(Track2Data.getText()));
                else
                    ThePINPad.setTrack2Data(Track2Data.getText().getBytes());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setTrack3Data(ActionEvent actionEvent) {
        if (!InUpdateGui && ThePINPad.getState() != JposConst.JPOS_S_CLOSED && !Track3DataRow.getValue().equals(Track3Data.getText())) {
            try {
                if (Conversion == ByteConversion.Hexadecimal)
                    ThePINPad.setTrack3Data(hexStringToByteArray(Track3Data.getText()));
                else if (Conversion == ByteConversion.Ascii)
                    ThePINPad.setTrack3Data(asciiStringToByteArray(Track3Data.getText()));
                else
                    ThePINPad.setTrack3Data(Track3Data.getText().getBytes());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setTrack4Data(ActionEvent actionEvent) {
        if (!InUpdateGui && ThePINPad.getState() != JposConst.JPOS_S_CLOSED && !Track4DataRow.getValue().equals(Track4Data.getText())) {
            try {
                if (Conversion == ByteConversion.Hexadecimal)
                    ThePINPad.setTrack4Data(hexStringToByteArray(Track4Data.getText()));
                else if (Conversion == ByteConversion.Ascii)
                    ThePINPad.setTrack4Data(asciiStringToByteArray(Track4Data.getText()));
                else
                    ThePINPad.setTrack4Data(Track4Data.getText().getBytes());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void beginEFTTransaction(ActionEvent actionEvent) {
        Integer transactionHost = new IntValues().getInteger(BET_host.getText());
        String  pinPadSystem = BET_system.getValue();
        if (!invalid(pinPadSystem, "PINPadSystem") && !invalid(transactionHost, "transactionHost")) {
            try {
                ThePINPad.beginEFTTransaction(pinPadSystem, transactionHost);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void enablePINEntry(ActionEvent actionEvent) {
        try {
            ThePINPad.enablePINEntry();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void computeMAC(ActionEvent actionEvent) {
        String inMsg = CM_inMsg.getText();
        String[] outMsg = {CM_outMsg.getText()};
        if (!invalid(inMsg, "inMsg")) {
            try {
                ThePINPad.computeMAC(inMsg, outMsg);
                CM_outMsg.setText(outMsg[0]);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void verifyMAC(ActionEvent actionEvent) {
        String  message = VM_message.getText();
        if (!invalid(message, "message")) {
            try {
                ThePINPad.verifyMAC(message);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void updateKey(ActionEvent actionEvent) {
        Integer keyNum = new IntValues().getInteger(UK_keyNum.getText());
        String  key = UK_key.getText();
        if (!invalid(keyNum, "keyNum") && !invalid(key, "key")) {
            try {
                ThePINPad.updateKey(keyNum, key);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void endEFTTransaction(ActionEvent actionEvent) {
        Integer completionCode = new CompletionCodeValues().getInteger(EET_completionCode.getValue());
        if (!invalid(completionCode, "completionCode")) {
            try {
                ThePINPad.endEFTTransaction(completionCode);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    private class CapDisplayValues extends Values {
        CapDisplayValues() {
            ValueList = new Object[] {
                    PINPadConst.PPAD_DISP_UNRESTRICTED, "DISP_UNRESTRICTED",
                    PINPadConst.PPAD_DISP_PINRESTRICTED, "DISP_PINRESTRICTED",
                    PINPadConst.PPAD_DISP_RESTRICTED_LIST, "DISP_RESTRICTED_LIST",
                    PINPadConst.PPAD_DISP_RESTRICTED_ORDER, "DISP_RESTRICTED_ORDER",
                    PINPadConst.PPAD_DISP_NONE, "DISP_NONE"
            };
        }
    }

    private class CapLanguageValues extends Values {
        CapLanguageValues() {
            ValueList = new Object[] {
                    PINPadConst.PPAD_LANG_NONE, "LANG_NONE",
                    PINPadConst.PPAD_LANG_ONE, "LANG_ONE",
                    PINPadConst.PPAD_LANG_PINRESTRICTED, "LANG_PINRESTRICTED",
                    PINPadConst.PPAD_LANG_UNRESTRICTED, "LANG_UNRESTRICTED"
            };
        }
    }

    private class PromptValues extends Values {
        PromptValues() {
            ValueList = new Object[] {
                    PINPadConst.PPAD_MSG_ENTERPIN, "MSG_ENTERPIN",
                    PINPadConst.PPAD_MSG_PLEASEWAIT, "MSG_PLEASEWAIT",
                    PINPadConst.PPAD_MSG_ENTERVALIDPIN, "MSG_ENTERVALIDPIN",
                    PINPadConst.PPAD_MSG_RETRIESEXCEEDED, "MSG_RETRIESEXCEEDED",
                    PINPadConst.PPAD_MSG_APPROVED, "MSG_APPROVED",
                    PINPadConst.PPAD_MSG_DECLINED, "MSG_DECLINED",
                    PINPadConst.PPAD_MSG_CANCELED, "MSG_CANCELED",
                    PINPadConst.PPAD_MSG_AMOUNTOK, "MSG_AMOUNTOK",
                    PINPadConst.PPAD_MSG_NOTREADY, "MSG_NOTREADY",
                    PINPadConst.PPAD_MSG_IDLE, "MSG_IDLE",
                    PINPadConst.PPAD_MSG_SLIDE_CARD, "MSG_SLIDE_CARD",
                    PINPadConst.PPAD_MSG_INSERTCARD, "MSG_INSERTCARD",
                    PINPadConst.PPAD_MSG_SELECTCARDTYPE, "MSG_SELECTCARDTYPE"
            };
        }
    }

    private class TransactionTypeValues extends Values {
        TransactionTypeValues() {
            ValueList = new Object[] {
                    PINPadConst.PPAD_TRANS_DEBIT, "TRANS_DEBIT",
                    PINPadConst.PPAD_TRANS_CREDIT, "TRANS_CREDIT",
                    PINPadConst.PPAD_TRANS_INQ, "TRANS_INQ",
                    PINPadConst.PPAD_TRANS_RECONCILE, "TRANS_RECONCILE",
                    PINPadConst.PPAD_TRANS_ADMIN, "TRANS_ADMIN"
            };
        }
    }

    private class CompletionCodeValues extends Values {
        CompletionCodeValues() {
            ValueList = new Object[] {
                    PINPadConst.PPAD_EFT_NORMAL, "EFT_NORMAL",
                    PINPadConst.PPAD_EFT_ABNORMAL, "EFT_ABNORMAL"
            };
        }
    }
}
