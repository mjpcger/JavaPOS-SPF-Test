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
 *
 */

package SPF_Test;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jpos.*;
import jpos.events.StatusUpdateEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for POSPower properties, methods and events.
 */
public class POSPowerController extends CommonController {

    public TextField BatteryCriticallyLowThreshold;
    public TextField BatteryLowThreshold;
    public TextField EnforcedShutdownDelayTime;
    public ComboBox<String> SP_reason;
    private POSPower ThePOSPower;
    private PropertyTableRow CapVariableBatteryCriticallyLowThresholdRow;
    private PropertyTableRow CapVariableBatteryLowThresholdRow;
    private PropertyTableRow BatteryCriticallyLowThresholdRow;
    private PropertyTableRow BatteryLowThresholdRow;
    private PropertyTableRow EnforcedShutdownDelayTimeRow;
    private PropertyTableRow BatteryCapacityRemainingRow;
    private PropertyTableRow PowerSourceRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        ThePOSPower = (POSPower) Control;
        ThePOSPower.addDirectIOListener(this);
        ThePOSPower.addStatusUpdateListener(this);
        StatusUpdateEventStatusValueConverter = new POSPStatusUpdateValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(BatteryCapacityRemainingRow = new PropertyTableRow("BatteryCapacityRemaining", ""));
        Properties.getItems().add(PowerSourceRow = new PropertyTableRow("PowerSource", "", new PowerSourceValues()));
        Properties.getItems().add(new PropertyTableRow("UPSChargeState", "", new UPSChargeStateValues()));
        Properties.getItems().add(new PropertyTableRow("PowerFailDelayTime", ""));
        Properties.getItems().add(new PropertyTableRow("QuickChargeMode", ""));
        Properties.getItems().add(new PropertyTableRow("QuickChargeTime", ""));
        Properties.getItems().add(new PropertyTableRow("CapBatteryCapacityRemaining", ""));
        Properties.getItems().add(new PropertyTableRow("CapFanAlarm", ""));
        Properties.getItems().add(new PropertyTableRow("CapHeatAlarm", ""));
        Properties.getItems().add(new PropertyTableRow("CapQuickCharge", ""));
        Properties.getItems().add(new PropertyTableRow("CapRestartPOS", ""));
        Properties.getItems().add(new PropertyTableRow("CapShutdownPOS", ""));
        Properties.getItems().add(new PropertyTableRow("CapStandbyPOS", ""));
        Properties.getItems().add(new PropertyTableRow("CapSuspendPOS", ""));
        Properties.getItems().add(new PropertyTableRow("CapUPSChargeState", "", new HexValues()));
        Properties.getItems().add(CapVariableBatteryCriticallyLowThresholdRow = new PropertyTableRow("CapVariableBatteryCriticallyLowThreshold", ""));
        Properties.getItems().add(CapVariableBatteryLowThresholdRow = new PropertyTableRow("CapVariableBatteryLowThreshold", ""));
        Properties.getItems().add(BatteryCriticallyLowThresholdRow = new PropertyTableRow("BatteryCriticallyLowThreshold", ""));
        Properties.getItems().add(BatteryLowThresholdRow = new PropertyTableRow("BatteryLowThreshold", ""));
        Properties.getItems().add(EnforcedShutdownDelayTimeRow = new PropertyTableRow("EnforcedShutdownDelayTime", ""));
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
        setPropertyOnFocusLost(BatteryCriticallyLowThreshold, "BatteryCriticallyLowThreshold");
        setPropertyOnFocusLost(BatteryLowThreshold, "BatteryLowThreshold");
        setPropertyOnFocusLost(EnforcedShutdownDelayTime, "EnforcedShutdownDelayTime");
        Values val = new SP_reasonValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            SP_reason.getItems().add(val.ValueList[i].toString());
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            BatteryLowThreshold.setText(BatteryLowThresholdRow.getValue());
            BatteryCriticallyLowThreshold.setText(BatteryCriticallyLowThresholdRow.getValue());
            EnforcedShutdownDelayTime.setText(EnforcedShutdownDelayTimeRow.getValue());
            InUpdateGui = false;
        }
    }

    @Override
    String getLogString(StatusUpdateEvent event) {
        String retstr = super.getLogString(event);
        if (event.getStatus() == POSPowerConst.PWR_SUE_PWR_SOURCE) {
            retstr += ": " + PowerSourceRow.getValue();
        }
        else if (event.getStatus() == POSPowerConst.PWR_SUE_BAT_CAPACITY_REMAINING) {
            retstr += ": " + BatteryCapacityRemainingRow.getValue() + "%";
        }
        return retstr;
    }

    public void setBatteryCriticallyLowThreshold(ActionEvent dummy) {
        Integer batteryCriticallyLowThreshold = new TimeoutValues().getInteger(BatteryCriticallyLowThreshold.getText());
        if (!invalid(batteryCriticallyLowThreshold, "batteryCriticallyLowThreshold")) {
            try {
                ThePOSPower.setBatteryCriticallyLowThreshold(batteryCriticallyLowThreshold);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setBatteryLowThreshold(ActionEvent dummy) {
        Integer batteryLowThreshold = new TimeoutValues().getInteger(BatteryLowThreshold.getText());
        if (!invalid(batteryLowThreshold, "batteryLowThreshold")) {
            try {
                ThePOSPower.setBatteryLowThreshold(batteryLowThreshold);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }


    public void setEnforcedShutdownDelayTime(ActionEvent dummy) {
        Integer enforcedShutdownDelayTime = new TimeoutValues().getInteger(EnforcedShutdownDelayTime.getText());
        if (!invalid(enforcedShutdownDelayTime, "enforcedShutdownDelayTime")) {
            try {
                ThePOSPower.setEnforcedShutdownDelayTime(enforcedShutdownDelayTime);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void restartPOS(ActionEvent actionEvent) {
        try {
            ThePOSPower.restartPOS();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void shutdownPOS(ActionEvent actionEvent) {
        try {
            ThePOSPower.shutdownPOS();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void standbyPOS(ActionEvent actionEvent) {
        Integer reason = new SP_reasonValues().getInteger(SP_reason.getValue());
        if (!invalid(reason, "reason")){
            try {
                ThePOSPower.standbyPOS(reason);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void suspendPOS(ActionEvent actionEvent) {
        Integer reason = new SP_reasonValues().getInteger(SP_reason.getValue());
        if (!invalid(reason, "reason")){
            try {
                ThePOSPower.suspendPOS(reason);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    private class POSPStatusUpdateValues extends StatusUpdateValues {
        POSPStatusUpdateValues() {
            super();
            Object[] pospvalues = new Object[]{
                    POSPowerConst.PWR_SUE_BAT_LOW, "SUE_BAT_LOW",
                    POSPowerConst.PWR_SUE_BAT_CRITICAL, "SUE_BAT_CRITICAL",
                    POSPowerConst.PWR_SUE_BAT_CAPACITY_REMAINING, "SUE_BAT_CAPACITY_REMAINING",
                    POSPowerConst.PWR_SUE_FAN_RUNNING, "SUE_FAN_RUNNING",
                    POSPowerConst.PWR_SUE_FAN_STOPPED, "SUE_FAN_STOPPED",
                    POSPowerConst.PWR_SUE_PWR_SOURCE, "SUE_PWR_SOURCE",
                    POSPowerConst.PWR_SUE_RESTART, "SUE_RESTART",
                    POSPowerConst.PWR_SUE_SHUTDOWN, "SUE_SHUTDOWN",
                    POSPowerConst.PWR_SUE_STANDBY, "SUE_STANDBY",
                    POSPowerConst.PWR_SUE_SUSPEND, "SUE_SUSPEND",
                    POSPowerConst.PWR_SUE_TEMPERATURE_HIGH, "SUE_TEMPERATURE_HIGH",
                    POSPowerConst.PWR_SUE_TEMPERATURE_OK, "SUE_TEMPERATURE_OK",
                    POSPowerConst.PWR_SUE_UPS_CRITICAL, "SUE_UPS_CRITICAL",
                    POSPowerConst.PWR_SUE_UPS_FULL, "SUE_UPS_FULL",
                    POSPowerConst.PWR_SUE_UPS_LOW, "SUE_UPS_LOW",
                    POSPowerConst.PWR_SUE_UPS_WARNING, "SUE_UPS_WARNING",
                    POSPowerConst.PWR_SUE_USER_STANDBY, "SUE_USER_STANDBY",
                    POSPowerConst.PWR_SUE_USER_SUSPEND, "SUE_USER_SUSPEND"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + pospvalues.length);
            System.arraycopy(pospvalues, 0, ValueList, ValueList.length - pospvalues.length, pospvalues.length);
        }
    }

    private class UPSChargeStateValues extends Values {
        UPSChargeStateValues() {
            ValueList = new Object[]{
                    POSPowerConst.PWR_UPS_FULL, "UPS_FULL",
                    POSPowerConst.PWR_UPS_WARNING, "UPS_WARNING",
                    POSPowerConst.PWR_UPS_LOW, "UPS_LOW",
                    POSPowerConst.PWR_UPS_CRITICAL, "UPS_CRITICAL"
            };
        }
    }

    private class PowerSourceValues extends Values {
        PowerSourceValues() {
            ValueList = new Object[]{
                    POSPowerConst.PWR_SOURCE_NA, "SOURCE_NA",
                    POSPowerConst.PWR_SOURCE_AC, "SOURCE_AC",
                    POSPowerConst.PWR_SOURCE_BATTERY, "SOURCE_BATTERY",
                    POSPowerConst.PWR_SOURCE_BACKUP, "SOURCE_BACKUP"
            };
        }
    }

    private class SP_reasonValues extends Values {
        SP_reasonValues() {
            ValueList = new Object[]{
                    POSPowerConst.PWR_REASON_REQUEST, "REASON_REQUEST",
                    POSPowerConst.PWR_REASON_ALLOW, "REASON_ALLOW",
                    POSPowerConst.PWR_REASON_DENY, "REASON_DENY"
            };
        }
    }
}
