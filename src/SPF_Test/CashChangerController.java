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
import jpos.CashChanger;
import jpos.CashChangerConst;
import jpos.JposConst;
import jpos.JposException;
import jpos.events.DataEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for CashChanger properties, methods and events.
 */
public class CashChangerController extends CommonController {
    public CheckBox RealTimeDataEnabled;
    public ComboBox<String> CurrencyCode;
    public ComboBox<String> CurrentExit;
    public ComboBox<String> CurrentService;
    public TextField ACC_cashCounts;
    public TextField RCC_cashCounts;
    public CheckBox RCC_discrepancy;
    public ComboBox<String> PD_control;
    public ComboBox<String> ED_success;
    public TextField DC_cashCounts;
    public TextField DC_amount;
    public Label AsyncResultCode;
    public Label AsyncResultCodeExtended;
    public Label DeviceStatus;
    public Label FullStatus;
    public Label DepositStatus;
    public Label DepositAmountText;
    public Label DepositAmount;
    private CashChanger TheCashChanger;
    private PropertyTableRow RealTimeDataEnabledRow;
    private PropertyTableRow CurrencyCodeRow;
    private PropertyTableRow CurrencyCodeListRow;
    private PropertyTableRow CurrentExitRow;
    private PropertyTableRow DeviceExitsRow;
    private PropertyTableRow CurrentServiceRow;
    private PropertyTableRow ServiceCountRow;
    private PropertyTableRow ServiceIndexRow;
    private PropertyTableRow DepositStatusRow;
    private PropertyTableRow DeviceStatusRow;
    private PropertyTableRow FullStatusRow;
    private PropertyTableRow DepositAmountRow;
    private PropertyTableRow DepositCountsRow;
    private PropertyTableRow AsyncResultCodeRow;
    private PropertyTableRow AsyncResultCodeExtendedRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheCashChanger = (CashChanger) Control;
        TheCashChanger.addDataListener(this);
        TheCashChanger.addStatusUpdateListener(this);
        TheCashChanger.addDirectIOListener(this);
        StatusUpdateEventStatusValueConverter = new CCStatusUpdateValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(DepositStatusRow = new PropertyTableRow("DepositStatus", "", new DepositStatusValues()));
        Properties.getItems().add(DeviceStatusRow = new PropertyTableRow("DeviceStatus", "", new DeviceStatusValues()));
        Properties.getItems().add(FullStatusRow = new PropertyTableRow("FullStatus", "", new FullStatusValues()));
        Properties.getItems().add(DepositAmountRow = new PropertyTableRow("DepositAmount", ""));
        Properties.getItems().add(DepositCountsRow = new PropertyTableRow("DepositCounts", ""));
        Properties.getItems().add(CurrentExitRow = new PropertyTableRow("CurrentExit", ""));
        Properties.getItems().add(AsyncResultCodeRow = new PropertyTableRow("AsyncResultCode", "", new AsyncResultCodeValues()));
        Properties.getItems().add(AsyncResultCodeExtendedRow = new PropertyTableRow("AsyncResultCodeExtended", "", new AsyncResultCodeExtendedValues()));
        Properties.getItems().add(CurrentServiceRow = new PropertyTableRow("CurrentService", ""));
        Properties.getItems().add(RealTimeDataEnabledRow = new PropertyTableRow("RealTimeDataEnabled", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapDeposit", ""));
        Properties.getItems().add(new PropertyTableRow("CapDepositDataEvent", ""));
        Properties.getItems().add(new PropertyTableRow("CapDiscrepancy", ""));
        Properties.getItems().add(new PropertyTableRow("CapEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapFullSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJamSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapNearEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapNearFullSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapPauseDeposit", ""));
        Properties.getItems().add(new PropertyTableRow("CapRealTimeData", ""));
        Properties.getItems().add(new PropertyTableRow("CapRepayDeposit", ""));
        Properties.getItems().add(new PropertyTableRow("CurrencyCashList", ""));
        Properties.getItems().add(CurrencyCodeRow = new PropertyTableRow("CurrencyCode", ""));
        Properties.getItems().add(CurrencyCodeListRow = new PropertyTableRow("CurrencyCodeList", ""));
        Properties.getItems().add(new PropertyTableRow("DepositCashList", ""));
        Properties.getItems().add(new PropertyTableRow("DepositCodeList", ""));
        Properties.getItems().add(DeviceExitsRow = new PropertyTableRow("DeviceExits", ""));
        Properties.getItems().add(new PropertyTableRow("ExitCashList", ""));
        Properties.getItems().add(ServiceCountRow = new PropertyTableRow("ServiceCount", ""));
        Properties.getItems().add(ServiceIndexRow = new PropertyTableRow("ServiceIndex", "", new HexValues()));
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
            try {
                int limit = Integer.parseInt(DeviceExitsRow.getValue());
                if (CurrentExit.getItems().size() != limit) {
                    CurrentExit.getItems().clear();
                    for (int i = 1; i <= limit; i++) {
                        CurrentExit.getItems().add(Integer.toString(i));
                        if (Integer.parseInt(CurrentExitRow.getValue()) == i)
                            CurrentExit.setValue(CurrentExitRow.getValue());
                    }
                }
            }
            catch (Exception e) {
                CurrentExit.getItems().clear();
            }
            try {
                int limit = Integer.parseInt(ServiceCountRow.getValue());
                if (CurrentService.getItems().size() != limit + 1) {
                    CurrentService.getItems().clear();
                    for (int i = 0; i <= limit; i++) {
                        CurrentService.getItems().add(Integer.toString(i));
                        if (Integer.parseInt(CurrentServiceRow.getValue()) == i)
                            CurrentService.setValue(CurrentServiceRow.getValue());
                    }
                }
            }
            catch (Exception e) {
                CurrentService.getItems().clear();
            }
            String[] codes = new String[0];
            if (!CurrencyCodeListRow.getValue().equals(""))
                codes = CurrencyCodeListRow.getValue().split(",");
            if (codes.length != CurrencyCode.getItems().size()) {
                CurrencyCode.getItems().clear();
                for (String code : codes) {
                    CurrencyCode.getItems().add(code);
                    if (code.equals(CurrencyCodeRow.getValue()))
                        CurrencyCode.setValue(code);
                }
            }
            AsyncResultCode.setText(AsyncResultCodeRow.getValue());
            AsyncResultCodeExtended.setText(AsyncResultCodeExtendedRow.getValue());
            DeviceStatus.setText(DeviceStatusRow.getValue());
            FullStatus.setText(FullStatusRow.getValue());
            String prev = DepositStatus.getText();
            DepositStatus.setText(DepositStatusRow.getValue());
            DepositAmount.setText(DepositAmountRow.getValue());
            if (!DepositStatus.getText().equals(prev)) {
                if (DepositStatus.getText().equals(new DepositStatusValues().getSymbol(CashChangerConst.CHAN_STATUS_DEPOSIT_START))) {
                    DepositAmountText.setVisible(true);
                    DepositAmount.setVisible(true);
                }
                else if (DepositStatus.getText().equals(new DepositStatusValues().getSymbol(CashChangerConst.CHAN_STATUS_DEPOSIT_END))) {
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
                TheCashChanger.setRealTimeDataEnabled(RealTimeDataEnabled.isSelected());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setCurrencyCode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCashChanger.setCurrencyCode(CurrencyCode.getValue());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setCurrentExit(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCashChanger.setCurrentExit(new IntValues().getInteger(CurrentExit.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setCurrentService(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCashChanger.setCurrentService(new IntValues().getInteger(CurrentService.getValue()));
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
            TheCashChanger.adjustCashCounts(CashCounts);
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
            TheCashChanger.readCashCounts(CashCounts, Discrepancy);
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
            TheCashChanger.beginDeposit();
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
            TheCashChanger.pauseDeposit(Control);
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
            TheCashChanger.fixDeposit();
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
            TheCashChanger.endDeposit(Success);
        }
    }

    public void endDeposit(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer success = new ED_successValues().getInteger(ED_success.getValue());
            if (!invalid(success, "success"))
                new EndDeposit(success).start();
        }
    }

    private class DispenseCash extends MethodProcessor {
        String CashCounts;
        public DispenseCash(String cashCounts) {
            super("DispenseCash");
            CashCounts = cashCounts;
        }

        @Override
        void runIt() throws JposException {
            TheCashChanger.dispenseCash(CashCounts);
        }
    }

    public void dispenseCash(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            String cashCounts = DC_cashCounts.getText();
            if (!invalid(cashCounts, "cashCounts"))
                new DispenseCash(cashCounts).start();
        }
    }

    private class DispenseChange extends MethodProcessor {
        int Amount;
        public DispenseChange(int amount) {
            super("DispenseChange");
            Amount = amount;
        }

        @Override
        void runIt() throws JposException {
            TheCashChanger.dispenseChange(Amount);
        }
    }

    public void dispenseChange(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer amount = new IntValues().getInteger(DC_amount.getText());
            if (!invalid(amount, "success"))
                new DispenseChange(amount).start();
        }
    }

    private class CCStatusUpdateValues extends StatusUpdateValues {
        CCStatusUpdateValues() {
            super();
            Object[] cdvalues = new Object[]{
                    CashChangerConst.CHAN_STATUS_EMPTY, "STATUS_EMPTY",
                    CashChangerConst.CHAN_STATUS_NEAREMPTY, "STATUS_NEAREMPTY",
                    CashChangerConst.CHAN_STATUS_EMPTYOK, "STATUS_EMPTYOK",
                    CashChangerConst.CHAN_STATUS_JAM, "STATUS_JAM",
                    CashChangerConst.CHAN_STATUS_JAMOK, "STATUS_JAMOK",
                    CashChangerConst.CHAN_STATUS_FULL, "STATUS_FULL",
                    CashChangerConst.CHAN_STATUS_NEARFULL, "STATUS_NEARFULL",
                    CashChangerConst.CHAN_STATUS_FULLOK, "STATUS_FULLOK",
                    CashChangerConst.CHAN_STATUS_ASYNC, "STATUS_ASYNC"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + cdvalues.length);
            System.arraycopy(cdvalues, 0, ValueList, ValueList.length - cdvalues.length, cdvalues.length);
        }
    }

    private class DepositStatusValues extends Values {
        DepositStatusValues() {
            ValueList = new Object[]{
                    CashChangerConst.CHAN_STATUS_DEPOSIT_START, "STATUS_DEPOSIT_START",
                    CashChangerConst.CHAN_STATUS_DEPOSIT_END, "STATUS_DEPOSIT_END",
                    CashChangerConst.CHAN_STATUS_DEPOSIT_NONE, "STATUS_DEPOSIT_NONE",
                    CashChangerConst.CHAN_STATUS_DEPOSIT_COUNT, "STATUS_DEPOSIT_COUNT",
                    CashChangerConst.CHAN_STATUS_DEPOSIT_JAM, "STATUS_DEPOSIT_JAM"
            };
        }
    }

    private class DeviceStatusValues extends Values {
        DeviceStatusValues() {
            ValueList = new Object[]{
                    CashChangerConst.CHAN_STATUS_OK, "STATUS_OK",
                    CashChangerConst.CHAN_STATUS_EMPTY, "STATUS_EMPTY",
                    CashChangerConst.CHAN_STATUS_NEAREMPTY, "STATUS_NEAREMPTY",
                    CashChangerConst.CHAN_STATUS_JAM, "STATUS_JAM"
            };
        }
    }

    private class FullStatusValues extends Values {
        FullStatusValues() {
            ValueList = new Object[]{
                    CashChangerConst.CHAN_STATUS_OK, "STATUS_OK",
                    CashChangerConst.CHAN_STATUS_FULL, "STATUS_FULL",
                    CashChangerConst.CHAN_STATUS_NEARFULL, "STATUS_NEARFULL"
            };
        }
    }

    private class AsyncResultCodeValues extends ErrorCodeValues {
        AsyncResultCodeValues() {
            super();
            Object[] arcvalues = new Object[]{
                    JposConst.JPOS_SUCCESS, "SUCCESS"
            };
            arcvalues = Arrays.copyOf(arcvalues, ValueList.length + arcvalues.length);
            System.arraycopy(ValueList, 0, arcvalues, arcvalues.length - ValueList.length, ValueList.length);
            ValueList = arcvalues;
        }
    }

    private class AsyncResultCodeExtendedValues extends Values {
        AsyncResultCodeExtendedValues() {
            ValueList = new Object[]{
                    CashChangerConst.JPOS_ECHAN_OVERDISPENSE, "ECHAN_OVERDISPENSE"
            };
        }
    }

    private class PD_controlValues extends Values {
        PD_controlValues() {
            ValueList = new Object[]{
                    CashChangerConst.CHAN_DEPOSIT_PAUSE, "DEPOSIT_PAUSE",
                    CashChangerConst.CHAN_DEPOSIT_RESTART, "DEPOSIT_RESTART"
            };
        }
    }

    private class ED_successValues extends Values {
        ED_successValues() {
            ValueList = new Object[]{
                    CashChangerConst.CHAN_DEPOSIT_CHANGE, "DEPOSIT_CHANGE",
                    CashChangerConst.CHAN_DEPOSIT_NOCHANGE, "DEPOSIT_NOCHANGE",
                    CashChangerConst.CHAN_DEPOSIT_REPAY, "DEPOSIT_REPAY"
            };
        }
    }
}
