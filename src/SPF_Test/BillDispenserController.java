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
import jpos.BillDispenser;
import jpos.BillDispenserConst;
import jpos.JposConst;
import jpos.JposException;
import jpos.events.DataEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for BillDispenser properties, methods and events.
 */
public class BillDispenserController extends CommonController {
    public CheckBox RealTimeDataEnabled;
    public ComboBox<String> CurrencyCode;
    public ComboBox<String> CurrentExit;
    public TextField ACC_cashCounts;
    public TextField RCC_cashCounts;
    public CheckBox RCC_discrepancy;
    public TextField DC_cashCounts;
    public Label AsyncResultCode;
    public Label AsyncResultCodeExtended;
    public Label DeviceStatus;
    public CheckBox LockDataEventEnabled;
    private BillDispenser TheBillDispenser;
    private PropertyTableRow CurrencyCodeRow;
    private PropertyTableRow CurrencyCodeListRow;
    private PropertyTableRow CurrentExitRow;
    private PropertyTableRow DeviceExitsRow;
    private PropertyTableRow DeviceStatusRow;
    private PropertyTableRow AsyncResultCodeRow;
    private PropertyTableRow AsyncResultCodeExtendedRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheBillDispenser = (BillDispenser) Control;
        TheBillDispenser.addStatusUpdateListener(this);
        TheBillDispenser.addDirectIOListener(this);
        StatusUpdateEventStatusValueConverter = new CCStatusUpdateValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(DeviceStatusRow = new PropertyTableRow("DeviceStatus", "", new DeviceStatusValues()));
        Properties.getItems().add(CurrentExitRow = new PropertyTableRow("CurrentExit", ""));
        Properties.getItems().add(AsyncResultCodeRow = new PropertyTableRow("AsyncResultCode", "", new AsyncResultCodeValues()));
        Properties.getItems().add(AsyncResultCodeExtendedRow = new PropertyTableRow("AsyncResultCodeExtended", "", new AsyncResultCodeExtendedValues()));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapDiscrepancy", ""));
        Properties.getItems().add(new PropertyTableRow("CapEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJamSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapNearEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CurrencyCashList", ""));
        Properties.getItems().add(CurrencyCodeRow = new PropertyTableRow("CurrencyCode", ""));
        Properties.getItems().add(CurrencyCodeListRow = new PropertyTableRow("CurrencyCodeList", ""));
        Properties.getItems().add(DeviceExitsRow = new PropertyTableRow("DeviceExits", ""));
        Properties.getItems().add(new PropertyTableRow("ExitCashList", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
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
            InUpdateGui = false;
        }
    }

    public void setCurrencyCode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBillDispenser.setCurrencyCode(CurrencyCode.getValue());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setCurrentExit(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBillDispenser.setCurrentExit(new IntValues().getInteger(CurrentExit.getValue()));
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
            TheBillDispenser.adjustCashCounts(CashCounts);
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
            TheBillDispenser.readCashCounts(CashCounts, Discrepancy);
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

    private class DispenseCash extends MethodProcessor {
        String CashCounts;
        public DispenseCash(String cashCounts) {
            super("DispenseCash");
            CashCounts = cashCounts;
        }

        @Override
        void runIt() throws JposException {
            TheBillDispenser.dispenseCash(CashCounts);
        }
    }

    public void dispenseCash(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            String cashCounts = DC_cashCounts.getText();
            if (!invalid(cashCounts, "cashCounts"))
                new DispenseCash(cashCounts).start();
        }
    }

    private class CCStatusUpdateValues extends StatusUpdateValues {
        CCStatusUpdateValues() {
            super();
            Object[] cdvalues = new Object[]{
                    BillDispenserConst.BDSP_STATUS_EMPTY, "STATUS_EMPTY",
                    BillDispenserConst.BDSP_STATUS_NEAREMPTY, "STATUS_NEAREMPTY",
                    BillDispenserConst.BDSP_STATUS_EMPTYOK, "STATUS_EMPTYOK",
                    BillDispenserConst.BDSP_STATUS_JAM, "STATUS_JAM",
                    BillDispenserConst.BDSP_STATUS_JAMOK, "STATUS_JAMOK",
                    BillDispenserConst.BDSP_STATUS_ASYNC, "STATUS_ASYNC"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + cdvalues.length);
            System.arraycopy(cdvalues, 0, ValueList, ValueList.length - cdvalues.length, cdvalues.length);
        }
    }

    private class DeviceStatusValues extends Values {
        DeviceStatusValues() {
            ValueList = new Object[]{
                    BillDispenserConst.BDSP_STATUS_OK, "STATUS_OK",
                    BillDispenserConst.BDSP_STATUS_EMPTY, "STATUS_EMPTY",
                    BillDispenserConst.BDSP_STATUS_NEAREMPTY, "STATUS_NEAREMPTY",
                    BillDispenserConst.BDSP_STATUS_JAM, "STATUS_JAM"
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
                    BillDispenserConst.JPOS_EBDSP_OVERDISPENSE, "EBDSP_OVERDISPENSE"
            };
        }
    }
}
