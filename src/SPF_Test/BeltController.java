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

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jpos.*;

/**
 * GUI control for Belt properties, methods and events.
 */
public class BeltController extends CommonController {
    public Label LightBarrierForwardInterrupted;
    public Label SecurityFlapForwardOpened;
    public Label LightBarrierBackwardInterrupted;
    public Label SecurityFlapBackwardOpened;
    public Label AutoStopForwardItemCount;
    public Label AutoStopBackwardItemCount;
    public Label MotionStatus;
    public CheckBox AutoStopForward;
    public ComboBox<String> AutoStopForwardDelayTime;
    public CheckBox AutoStopBackward;
    public ComboBox<String> AutoStopBackwardDelayTime;
    public ComboBox<String> AIC_direction;
    public TextField AIC_count;
    public ComboBox<String> MF_speed;
    public ComboBox<String> MB_speed;
    public ComboBox<String> RIC_direction;
    private Belt TheBelt;
    private PropertyTableRow MotionStatusRow;
    private PropertyTableRow LightBarrierForwardInterruptedRow;
    private PropertyTableRow SecurityFlapForwardOpenedRow;
    private PropertyTableRow LightBarrierBackwardInterruptedRow;
    private PropertyTableRow SecurityFlapBackwardOpenedRow;
    private PropertyTableRow AutoStopBackwardRow;
    private PropertyTableRow AutoStopBackwardDelayTimeRow;
    private PropertyTableRow AutoStopBackwardItemCountRow;
    private PropertyTableRow AutoStopForwardRow;
    private PropertyTableRow AutoStopForwardDelayTimeRow;
    private PropertyTableRow AutoStopForwardItemCountRow;
    private PropertyTableRow CapAutoStopBackwardRow;
    private PropertyTableRow CapAutoStopForwardRow;
    private PropertyTableRow CapAutoStopBackwardItemCountRow;
    private PropertyTableRow CapAutoStopForwardItemCountRow;
    private PropertyTableRow CapMoveBackwardRow;
    private PropertyTableRow CapSpeedStepsBackwardRow;
    private PropertyTableRow CapSpeedStepsForwardRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        StatusUpdateEventStatusValueConverter = new StatusUpdateEventValues();
        super.initialize(url, resourceBundle);
        TheBelt = (Belt) Control;
        TheBelt.addDirectIOListener(this);
        TheBelt.addStatusUpdateListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(MotionStatusRow = new PropertyTableRow("MotionStatus", "", new MotionStatusValues()));
        Properties.getItems().add(LightBarrierForwardInterruptedRow = new PropertyTableRow("LightBarrierForwardInterrupted", ""));
        Properties.getItems().add(SecurityFlapForwardOpenedRow = new PropertyTableRow("SecurityFlapForwardOpened", ""));
        Properties.getItems().add(LightBarrierBackwardInterruptedRow = new PropertyTableRow("LightBarrierBackwardInterrupted", ""));
        Properties.getItems().add(SecurityFlapBackwardOpenedRow = new PropertyTableRow("SecurityFlapBackwardOpened", ""));
        Properties.getItems().add(AutoStopBackwardRow = new PropertyTableRow("AutoStopBackward", ""));
        Properties.getItems().add(AutoStopBackwardDelayTimeRow = new PropertyTableRow("AutoStopBackwardDelayTime", "", new TimeoutValues()));
        Properties.getItems().add(AutoStopBackwardItemCountRow = new PropertyTableRow("AutoStopBackwardItemCount", ""));
        Properties.getItems().add(AutoStopForwardRow = new PropertyTableRow("AutoStopForward", ""));
        Properties.getItems().add(AutoStopForwardDelayTimeRow = new PropertyTableRow("AutoStopForwardDelayTime", "", new TimeoutValues()));
        Properties.getItems().add(AutoStopForwardItemCountRow = new PropertyTableRow("AutoStopForwardItemCount", ""));
        Properties.getItems().add(CapAutoStopBackwardRow = new PropertyTableRow("CapAutoStopBackward", ""));
        Properties.getItems().add(CapAutoStopBackwardItemCountRow = new PropertyTableRow("CapAutoStopBackwardItemCount", ""));
        Properties.getItems().add(CapAutoStopForwardRow = new PropertyTableRow("CapAutoStopForward", ""));
        Properties.getItems().add(CapAutoStopForwardItemCountRow = new PropertyTableRow("CapAutoStopForwardItemCount", ""));
        Properties.getItems().add(new PropertyTableRow("CapLightBarrierBackward", ""));
        Properties.getItems().add(new PropertyTableRow("CapLightBarrierForward", ""));
        Properties.getItems().add(CapMoveBackwardRow = new PropertyTableRow("CapMoveBackward", ""));
        Properties.getItems().add(new PropertyTableRow("CapSecurityFlapBackward", ""));
        Properties.getItems().add(new PropertyTableRow("CapSecurityFlapForward", ""));
        Properties.getItems().add(CapSpeedStepsBackwardRow = new PropertyTableRow("CapSpeedStepsBackward", ""));
        Properties.getItems().add(CapSpeedStepsForwardRow = new PropertyTableRow("CapSpeedStepsForward", ""));
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
        updateGui();
    }

    @Override
    public void close(ActionEvent ev) {
        super.close(ev);
        MB_speed.getItems().clear();
        MF_speed.getItems().clear();
        AIC_direction.getItems().clear();
        RIC_direction.getItems().clear();
        AutoStopBackwardDelayTime.getItems().clear();
        AutoStopForwardDelayTime.getItems().clear();
        updateGui();
    }

    @Override
    public void open(ActionEvent actionEvent) {
        super.open(actionEvent);
        MB_speed.getItems().clear();
        if (!CapSpeedStepsBackwardRow.getValue().equals("") && "true".equals(CapAutoStopBackwardRow.getValue())) {
            int limit = new IntValues().getInteger(CapSpeedStepsBackwardRow.getValue());
            for (int i = 1; i <= limit; i++)
                MB_speed.getItems().add("" + i);
        }
        MF_speed.getItems().clear();
        if (!CapSpeedStepsForwardRow.getValue().equals("")) {
            int limit = new IntValues().getInteger(CapSpeedStepsForwardRow.getValue());
            for (int i = 1; i <= limit; i++)
                MF_speed.getItems().add("" + i);
        }
        Values vals = new AIC_directionValues();
        AIC_direction.getItems().clear();
        if ("true".equals(CapAutoStopForwardItemCountRow.getValue()))
            AIC_direction.getItems().add(vals.getSymbol(BeltConst.BELT_AIC_FORWARD));
        if ("true".equals(CapAutoStopBackwardItemCountRow.getValue()) && "true".equals(CapAutoStopBackwardRow.getValue()))
            AIC_direction.getItems().add(vals.getSymbol(BeltConst.BELT_AIC_BACKWARD));
        vals = new RIC_directionValues();
        RIC_direction.getItems().clear();
        if ("true".equals(CapAutoStopForwardItemCountRow.getValue()))
            RIC_direction.getItems().add(vals.getSymbol(BeltConst.BELT_RIC_FORWARD));
        if ("true".equals(CapAutoStopBackwardItemCountRow.getValue()) && "true".equals(CapAutoStopBackwardRow.getValue()))
            RIC_direction.getItems().add(vals.getSymbol(BeltConst.BELT_RIC_BACKWARD));
        AutoStopBackwardDelayTime.getItems().clear();
        if ("true".equals(CapAutoStopBackwardRow.getValue()) && "true".equals(CapMoveBackwardRow.getValue()))
            AutoStopBackwardDelayTime.getItems().add("FOREVER");
        AutoStopForwardDelayTime.getItems().clear();
        if ("true".equals(CapAutoStopForwardRow.getValue()))
            AutoStopForwardDelayTime.getItems().add("FOREVER");
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            LightBarrierForwardInterrupted.setText(setLabel(LightBarrierForwardInterruptedRow, "Interrupted", "Free"));
            SecurityFlapForwardOpened.setText(setLabel(SecurityFlapForwardOpenedRow, "Opened", "Closed"));
            LightBarrierBackwardInterrupted.setText(setLabel(LightBarrierBackwardInterruptedRow, "Interrupted", "Free"));
            SecurityFlapBackwardOpened.setText(setLabel(SecurityFlapBackwardOpenedRow, "Opened", "Closed"));
            AutoStopForwardItemCount.setText(AutoStopForwardItemCountRow.getValue());
            AutoStopBackwardItemCount.setText(AutoStopBackwardItemCountRow.getValue());
            MotionStatus.setText(MotionStatusRow.getValue());
            AutoStopForward.setSelected("true".equals(AutoStopForwardRow.getValue()));
            AutoStopForwardDelayTime.setValue(AutoStopForwardDelayTimeRow.getValue());
            AutoStopBackward.setSelected("true".equals(AutoStopBackwardRow.getValue()));
            AutoStopBackwardDelayTime.setValue(AutoStopBackwardDelayTimeRow.getValue());
            InUpdateGui = false;
        }
    }

    private String setLabel(PropertyTableRow row, String yes, String no) {
        if (row.getValue().equals("true"))
            return yes;
        else if (row.getValue().equals("false"))
            return no;
        return "";
    }

    public void autoStopForward(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBelt.setAutoStopForward(AutoStopForward.isSelected());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void autoStopForwardDelayTime(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBelt.setAutoStopForwardDelayTime(AutoStopForwardDelayTimeRow.getValueConverter().getInteger(AutoStopForwardDelayTime.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void autoStopBackward(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBelt.setAutoStopBackward(AutoStopBackward.isSelected());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void autoStopBackwardDelayTime(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBelt.setAutoStopBackwardDelayTime(AutoStopBackwardDelayTimeRow.getValueConverter().getInteger(AutoStopBackwardDelayTime.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void adjustItemCount(ActionEvent actionEvent) {
        Integer direction = new AIC_directionValues().getInteger(AIC_direction.getValue());
        Integer count = new IntValues().getInteger(AIC_count.getText());
        if (validate(new Object[]{direction, "direction", count, "count"})) {
            try {
                TheBelt.adjustItemCount(direction, count);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void moveForward(ActionEvent actionEvent) {
        Integer speed = new IntValues().getInteger(MF_speed.getValue());
        if (validate(new Object[]{speed, "speed"})) {
            try {
                TheBelt.moveForward(speed);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void moveBackward(ActionEvent actionEvent) {
        Integer speed = new IntValues().getInteger(MB_speed.getValue());
        if (validate(new Object[]{speed, "speed"})) {
            try {
                TheBelt.moveBackward(speed);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void resetBelt(ActionEvent actionEvent) {
        try {
            TheBelt.resetBelt();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void resetItemCount(ActionEvent actionEvent) {
        Integer direction = new IntValues().getInteger(RIC_direction.getValue());
        if (validate(new Object[]{direction, "direction"})) {
            try {
                TheBelt.resetItemCount(direction);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void stopBelt(ActionEvent actionEvent) {
        try {
            TheBelt.stopBelt();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class MotionStatusValues extends Values {
        MotionStatusValues() {
            ValueList = new Object[]{
                    BeltConst.BELT_MT_FORWARD, "MT_FORWARD",
                    BeltConst.BELT_MT_BACKWARD, "MT_BACKWARD",
                    BeltConst.BELT_MT_STOPPED, "MT_STOPPED",
                    BeltConst.BELT_MT_EMERGENCY, "MT_EMERGENCY",
                    BeltConst.BELT_MT_MOTOR_FAULT, "MT_MOTOR_FAULT"
            };
        }
    }

    private class AIC_directionValues extends Values {
        AIC_directionValues() {
            ValueList = new Object[]{
                    BeltConst.BELT_AIC_BACKWARD, "AIC_BACKWARD",
                    BeltConst.BELT_AIC_FORWARD, "AIC_FORWARD"
            };
        }
    }

    private class RIC_directionValues extends Values {
        RIC_directionValues() {
            ValueList = new Object[]{
                    BeltConst.BELT_RIC_BACKWARD, "RIC_BACKWARD",
                    BeltConst.BELT_RIC_FORWARD, "RIC_FORWARD"
            };
        }
    }

    private class StatusUpdateEventValues extends StatusUpdateValues {
        StatusUpdateEventValues() {
            super();
            Object[] cdvalues = new Object[]{
                    BeltConst.BELT_SUE_AUTO_STOP, "SUE_AUTO_STOP",
                    BeltConst.BELT_SUE_EMERGENCY_STOP, "SUE_EMERGENCY_STOP",
                    BeltConst.BELT_SUE_SAFETY_STOP, "SUE_SAFETY_STOP",
                    BeltConst.BELT_SUE_TIMEOUT_STOP, "SUE_TIMEOUT_STOP",
                    BeltConst.BELT_SUE_MOTOR_OVERHEATING, "SUE_MOTOR_OVERHEATING",
                    BeltConst.BELT_SUE_MOTOR_FUSE_DEFECT, "SUE_MOTOR_FUSE_DEFECT",
                    BeltConst.BELT_SUE_LIGHT_BARRIER_BACKWARD_INTERRUPTED, "SUE_LIGHT_BARRIER_BACKWARD_INTERRUPTED",
                    BeltConst.BELT_SUE_LIGHT_BARRIER_BACKWARD_OK, "SUE_LIGHT_BARRIER_BACKWARD_OK",
                    BeltConst.BELT_SUE_LIGHT_BARRIER_FORWARD_INTERRUPTED, "SUE_LIGHT_BARRIER_FORWARD_INTERRUPTED",
                    BeltConst.BELT_SUE_LIGHT_BARRIER_FORWARD_OK, "SUE_LIGHT_BARRIER_FORWARD_OK",
                    BeltConst.BELT_SUE_SECURITY_FLAP_BACKWARD_OPENED, "SUE_SECURITY_FLAP_BACKWARD_OPENED",
                    BeltConst.BELT_SUE_SECURITY_FLAP_BACKWARD_CLOSED, "SUE_SECURITY_FLAP_BACKWARD_CLOSED",
                    BeltConst.BELT_SUE_SECURITY_FLAP_FORWARD_OPENED, "SUE_SECURITY_FLAP_FORWARD_OPENED",
                    BeltConst.BELT_SUE_SECURITY_FLAP_FORWARD_CLOSED, "SUE_SECURITY_FLAP_FORWARD_CLOSED"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + cdvalues.length);
            System.arraycopy(cdvalues, 0, ValueList, ValueList.length - cdvalues.length, cdvalues.length);
        }
    }
}
