/*
 * Copyright 2021 Martin Conrad
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
import jpos.CoinAcceptor;
import jpos.CoinAcceptorConst;
import jpos.JposException;
import jpos.events.DataEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for CoinAcceptor properties, methods and events.
 */
public class CoinAcceptorController extends CommonController {
    public CheckBox RealTimeDataEnabled;
    public ComboBox<String> CurrencyCode;
    public TextField ACC_cashCounts;
    public TextField RCC_cashCounts;
    public CheckBox RCC_discrepancy;
    public ComboBox<String> PD_control;
    public ComboBox<String> ED_success;
    public Label FullStatus;
    public Label DepositStatus;
    public Label DepositAmountText;
    public Label DepositAmount;
    private CoinAcceptor TheCoinAcceptor;
    private PropertyTableRow RealTimeDataEnabledRow;
    private PropertyTableRow CurrencyCodeRow;
    private PropertyTableRow DepositCodeListRow;
    private PropertyTableRow DepositStatusRow;
    private PropertyTableRow FullStatusRow;
    private PropertyTableRow DepositAmountRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheCoinAcceptor = (CoinAcceptor) Control;
        TheCoinAcceptor.addDataListener(this);
        TheCoinAcceptor.addStatusUpdateListener(this);
        TheCoinAcceptor.addDirectIOListener(this);
        StatusUpdateEventStatusValueConverter = new CCStatusUpdateValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(DepositStatusRow = new PropertyTableRow("DepositStatus", "", new DepositStatusValues()));
        Properties.getItems().add(FullStatusRow = new PropertyTableRow("FullStatus", "", new FullStatusValues()));
        Properties.getItems().add(DepositAmountRow = new PropertyTableRow("DepositAmount", ""));
        Properties.getItems().add(new PropertyTableRow("DepositCounts", ""));
        Properties.getItems().add(RealTimeDataEnabledRow = new PropertyTableRow("RealTimeDataEnabled", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapDiscrepancy", ""));
        Properties.getItems().add(new PropertyTableRow("CapFullSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJamSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapNearFullSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapPauseDeposit", ""));
        Properties.getItems().add(new PropertyTableRow("CapRealTimeData", ""));
        Properties.getItems().add(CurrencyCodeRow = new PropertyTableRow("CurrencyCode", ""));
        Properties.getItems().add(new PropertyTableRow("DepositCashList", ""));
        Properties.getItems().add(DepositCodeListRow = new PropertyTableRow("DepositCodeList", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        Values actvals = new PD_controlValues();
        for (int i = 1; i < actvals.ValueList.length; i += 2)
            PD_control.getItems().add(actvals.ValueList[i].toString());
        actvals = new ED_successValues();
        for (int i = 1; i < actvals.ValueList.length; i += 2)
            ED_success.getItems().add(actvals.ValueList[i].toString());
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            RealTimeDataEnabled.setSelected(RealTimeDataEnabledRow.getValue().equals("true"));
            String[] codes = new String[0];
            if (!DepositCodeListRow.getValue().equals(""))
                codes = DepositCodeListRow.getValue().split(",");
            if (codes.length != CurrencyCode.getItems().size()) {
                CurrencyCode.getItems().clear();
                for (String code : codes) {
                    CurrencyCode.getItems().add(code);
                    if (code.equals(CurrencyCodeRow.getValue()))
                        CurrencyCode.setValue(code);
                }
            }
            FullStatus.setText(FullStatusRow.getValue());
            String prev = DepositStatus.getText();
            DepositStatus.setText(DepositStatusRow.getValue());
            DepositAmount.setText(DepositAmountRow.getValue());
            if (!DepositStatus.getText().equals(prev)) {
                if (DepositStatus.getText().equals(new DepositStatusValues().getSymbol(CoinAcceptorConst.CACC_STATUS_DEPOSIT_START))) {
                    DepositAmountText.setVisible(true);
                    DepositAmount.setVisible(true);
                }
                else if (DepositStatus.getText().equals(new DepositStatusValues().getSymbol(CoinAcceptorConst.CACC_STATUS_DEPOSIT_END))) {
                    DepositAmountText.setVisible(false);
                    DepositAmount.setVisible(false);
                }
            }
            InUpdateGui = false;
        }
    }

    public void setRealTimeDataEnabled(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCoinAcceptor.setRealTimeDataEnabled(RealTimeDataEnabled.isSelected());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setCurrencyCode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCoinAcceptor.setCurrencyCode(CurrencyCode.getValue());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    private class AdjustCashCounts extends MethodProcessor {
        String CashCounts;
        public AdjustCashCounts(String cashCounts) {
            super("AdjustCashCounts");
            CashCounts = cashCounts;
        }

        @Override
        void runIt() throws JposException {
            TheCoinAcceptor.adjustCashCounts(CashCounts);
        }
    }

    public void adjustCashCounts(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            String cashCounts = ACC_cashCounts.getText();
            if (!invalid(cashCounts, "cashCounts"))
                new AdjustCashCounts(cashCounts).start();
        }
    }

    private class ReadCashCounts extends MethodProcessor {
        String[] CashCounts;
        boolean[] Discrepancy;
        public ReadCashCounts(String cashCounts, boolean discrepancy) {
            super("ReadCashCounts");
            CashCounts = new String[]{cashCounts};
            Discrepancy = new boolean[]{discrepancy};
        }

        @Override
        void runIt() throws JposException {
            TheCoinAcceptor.readCashCounts(CashCounts, Discrepancy);
        }

        @Override
        void finish() {
            super.finish();
            RCC_cashCounts.setText(CashCounts[0]);
            RCC_discrepancy.setSelected(Discrepancy[0]);
        }
    }

    public void readCashCounts(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            String cashCounts = RCC_cashCounts.getText();
            boolean discrepancy = RCC_discrepancy.isSelected();
            if (!invalid(cashCounts, "cashCounts"))
                new ReadCashCounts(cashCounts, discrepancy).start();
        }
    }

    private class BeginDeposit extends MethodProcessor {
        public BeginDeposit() {
            super("BeginDeposit");
        }

        @Override
        void runIt() throws JposException {
            TheCoinAcceptor.beginDeposit();
        }
    }

    public void beginDeposit(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            new BeginDeposit().start();
        }
    }

    private class PauseDeposit extends MethodProcessor {
        int Control;
        public PauseDeposit(int control) {
            super("PauseDeposit");
            Control = control;
        }

        @Override
        void runIt() throws JposException {
            TheCoinAcceptor.pauseDeposit(Control);
        }
    }

    public void pauseDeposit(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer control = new PD_controlValues().getInteger(PD_control.getValue());
            if (!invalid(control, "control"))
                new PauseDeposit(control).start();
        }
    }

    private class FixDeposit extends MethodProcessor {
        public FixDeposit() {
            super("FixDeposit");
        }

        @Override
        void runIt() throws JposException {
            TheCoinAcceptor.fixDeposit();
        }
    }

    public void fixDeposit(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            new FixDeposit().start();
        }
    }

    private class EndDeposit extends MethodProcessor {
        int Success;
        public EndDeposit(int success) {
            super("EndDeposit");
            Success = success;
        }

        @Override
        void runIt() throws JposException {
            TheCoinAcceptor.endDeposit(Success);
        }
    }

    public void endDeposit(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer success = new ED_successValues().getInteger(ED_success.getValue());
            if (!invalid(success, "success"))
                new EndDeposit(success).start();
        }
    }

    private class CCStatusUpdateValues extends StatusUpdateValues {
        CCStatusUpdateValues() {
            super();
            Object[] cdvalues = new Object[]{
                    CoinAcceptorConst.CACC_STATUS_JAM, "STATUS_JAM",
                    CoinAcceptorConst.CACC_STATUS_JAMOK, "STATUS_JAMOK",
                    CoinAcceptorConst.CACC_STATUS_FULL, "STATUS_FULL",
                    CoinAcceptorConst.CACC_STATUS_NEARFULL, "STATUS_NEARFULL",
                    CoinAcceptorConst.CACC_STATUS_FULLOK, "STATUS_FULLOK",
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + cdvalues.length);
            System.arraycopy(cdvalues, 0, ValueList, ValueList.length - cdvalues.length, cdvalues.length);
        }
    }

    private class DepositStatusValues extends Values {
        DepositStatusValues() {
            ValueList = new Object[]{
                    CoinAcceptorConst.CACC_STATUS_DEPOSIT_START, "STATUS_DEPOSIT_START",
                    CoinAcceptorConst.CACC_STATUS_DEPOSIT_END, "STATUS_DEPOSIT_END",
                    CoinAcceptorConst.CACC_STATUS_DEPOSIT_COUNT, "STATUS_DEPOSIT_COUNT",
                    CoinAcceptorConst.CACC_STATUS_DEPOSIT_JAM, "STATUS_DEPOSIT_JAM"
            };
        }
    }

    private class FullStatusValues extends Values {
        FullStatusValues() {
            ValueList = new Object[]{
                    CoinAcceptorConst.CACC_STATUS_OK, "STATUS_OK",
                    CoinAcceptorConst.CACC_STATUS_FULL, "STATUS_FULL",
                    CoinAcceptorConst.CACC_STATUS_NEARFULL, "STATUS_NEARFULL"
            };
        }
    }

    private class PD_controlValues extends Values {
        PD_controlValues() {
            ValueList = new Object[]{
                    CoinAcceptorConst.CACC_DEPOSIT_PAUSE, "DEPOSIT_PAUSE",
                    CoinAcceptorConst.CACC_DEPOSIT_RESTART, "DEPOSIT_RESTART"
            };
        }
    }

    private class ED_successValues extends Values {
        ED_successValues() {
            ValueList = new Object[]{
                    CoinAcceptorConst.CACC_DEPOSIT_COMPLETE, "DEPOSIT_COMPLETE"
            };
        }
    }
}
