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
import jpos.events.DataEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for MICR properties, methods and events.
 */
public class MICRController extends CommonController {
    public ComboBox<String> BI_timeout;
    public ComboBox<String> BR_timeout;
    public Label RawData;
    public Label CheckType;
    public Label CountryCode;
    public Label EPC;
    public Label BankNumber;
    public Label TransitNumber;
    public Label AccountNumber;
    public Label SerialNumber;
    public Label Amount;
    MICR TheMicr;
    private PropertyTableRow AccountNumberRow;
    private PropertyTableRow AmountRow;
    private PropertyTableRow BankNumberRow;
    private PropertyTableRow CheckTypeRow;
    private PropertyTableRow CountryCodeRow;
    private PropertyTableRow EPCRow;
    private PropertyTableRow RawDataRow;
    private PropertyTableRow SerialNumberRow;
    private PropertyTableRow TransitNumberRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Ascii;
        super.initialize(url, resourceBundle);
        TheMicr = (MICR) Control;
        TheMicr.addDirectIOListener(this);
        TheMicr.addStatusUpdateListener(this);
        TheMicr.addDataListener(this);
        TheMicr.addErrorListener(this);
        ErrorCodeExtendedValueConverter = new ErrorCodeExtendedValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(AccountNumberRow = new PropertyTableRow("AccountNumber", ""));
        Properties.getItems().add(AmountRow = new PropertyTableRow("Amount", ""));
        Properties.getItems().add(BankNumberRow = new PropertyTableRow("BankNumber", ""));
        Properties.getItems().add(new PropertyTableRow("CapValidationDevice", ""));
        Properties.getItems().add(CheckTypeRow = new PropertyTableRow("CheckType", "", new CheckTypeValues()));
        Properties.getItems().add(CountryCodeRow = new PropertyTableRow("CountryCode", "", new CountryCodeValues()));
        Properties.getItems().add(EPCRow = new PropertyTableRow("EPC", ""));
        Properties.getItems().add(RawDataRow = new PropertyTableRow("RawData", ""));
        Properties.getItems().add(SerialNumberRow = new PropertyTableRow("SerialNumber", ""));
        Properties.getItems().add(TransitNumberRow = new PropertyTableRow("TransitNumber", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        String foreverSymbol = new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER);
        BI_timeout.getItems().add(foreverSymbol);
        BR_timeout.getItems().add(foreverSymbol);
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            Amount.setText(AmountRow.getValue());
            AccountNumber.setText(AccountNumberRow.getValue());
            BankNumber.setText(BankNumberRow.getValue());
            CheckType.setText(CheckTypeRow.getValue());
            CountryCode.setText(CountryCodeRow.getValue());
            EPC.setText(EPCRow.getValue());
            RawData.setText(RawDataRow.getValue());
            SerialNumber.setText(SerialNumberRow.getValue());
            TransitNumber.setText(TransitNumberRow.getValue());
            InUpdateGui = false;
        }
    }

    @Override
    String getLogString(DataEvent event) {
        return RawDataRow.getValue();
    }

    private class BeginInsertionHandler extends MethodProcessor {
        private final int Timeout;

        BeginInsertionHandler(int timeout) {
            super("BeginInsertion");
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            TheMicr.beginInsertion(Timeout);
        }
    }

    public void beginInsertion(ActionEvent actionEvent) {
        Integer tio = new TimeoutValues().getInteger(BI_timeout.getValue());
        if (!invalid(tio, "timeout"))
            new BeginInsertionHandler(tio).start();
    }

    public void endInsertion(ActionEvent actionEvent) {
        try {
            TheMicr.endInsertion();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class BeginRemovalHandler extends MethodProcessor {
        private final int Timeout;

        BeginRemovalHandler(int timeout) {
            super("BeginRemoval");
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            TheMicr.beginRemoval(Timeout);
        }
    }

    public void beginRemoval(ActionEvent actionEvent) {
        Integer tio = new TimeoutValues().getInteger(BI_timeout.getValue());
        if (!invalid(tio, "timeout"))
            new BeginRemovalHandler(tio).start();
    }

    public void endRemoval(ActionEvent actionEvent) {
        try {
            TheMicr.endRemoval();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }


    private class CheckTypeValues extends Values {
        CheckTypeValues() {
            ValueList = new Object[]{
                    MICRConst.MICR_CT_PERSONAL, "CT_PERSONAL",
                    MICRConst.MICR_CT_BUSINESS, "CT_BUSINESS",
                    MICRConst.MICR_CT_UNKNOWN, "CT_UNKNOWN"
            };
        }
    }

    private class CountryCodeValues extends Values {
        CountryCodeValues(){
            ValueList = new Object[]{
                    MICRConst.MICR_CC_USA, "CC_USA",
                    MICRConst.MICR_CC_CANADA, "CC_CANADA",
                    MICRConst.MICR_CC_MEXICO, "CC_MEXICO",
                    MICRConst.MICR_CC_CMC7, "CC_CMC7",
                    MICRConst.MICR_CC_OTHER, "CC_OTHER",
                    MICRConst.MICR_CC_UNKNOWN, "CC_UNKNOWN"
            };
        }
    }

    private class ErrorCodeExtendedValues extends Values {
        ErrorCodeExtendedValues(){
            ValueList = new Object[]{
                    MICRConst.JPOS_EMICR_NOCHECK, "CC_USA",
                    MICRConst.JPOS_EMICR_CHECK, "CC_USA",
                    MICRConst.JPOS_EMICR_BADDATA, "CC_USA",
                    MICRConst.JPOS_EMICR_NODATA, "CC_USA",
                    MICRConst.JPOS_EMICR_BADSIZE, "CC_USA",
                    MICRConst.JPOS_EMICR_JAM, "CC_USA",
                    MICRConst.JPOS_EMICR_CHECKDIGIT, "CC_USA",
                    MICRConst.JPOS_EMICR_COVEROPEN, "CC_USA"
            };
        }
    }
}
