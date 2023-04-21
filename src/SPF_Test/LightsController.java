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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jpos.JposException;
import jpos.Lights;
import jpos.LightsConst;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for Lights properties, methods and events.
 */
public class LightsController extends CommonController {
    public ComboBox<String> SO_lightNumber;
    public TextField SO_blinkOnCycle;
    public TextField SO_blinkOffCycle;
    public ComboBox<String> SO_color;
    public ComboBox<String> SO_alarm;
    public ComboBox<String> SOP_pattern;
    public ComboBox<String> SOP_alarm;
    private Lights TheLights;
    private PropertyTableRow MaxLightsRow;
    private PropertyTableRow CapAlarmRow;
    private PropertyTableRow CapColorRow;
    private PropertyTableRow CapPatternRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheLights = (Lights) Control;
        TheLights.addDirectIOListener(this);
        TheLights.addStatusUpdateListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(MaxLightsRow = new PropertyTableRow("MaxLights", ""));
        Properties.getItems().add(CapAlarmRow = new PropertyTableRow("CapAlarm", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapBlink", ""));
        Properties.getItems().add(CapColorRow = new PropertyTableRow("CapColor", "", new HexValues()));
        Properties.getItems().add(CapPatternRow = new PropertyTableRow("CapPattern", "", new HexValues()));
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

    int Lights = 0;
    int Colors = 0;
    int Alarms = 0;
    int Pattern = 0;

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            Integer lights = new IntValues().getInteger(MaxLightsRow.getValue());
            Integer colors = new HexValues().getInteger(CapColorRow.getValue());
            Integer alarms = new HexValues().getInteger(CapAlarmRow.getValue());
            Integer pattern = new HexValues().getInteger(CapPatternRow.getValue());
            lights = lights == null ? 0 : lights;
            colors = colors == null ? 0 : colors;
            alarms = alarms == null ? 0 : alarms;
            pattern = pattern == null ? 0 : pattern;
            if (Lights != lights) {
                if ((Lights = lights) == 0)
                    SO_lightNumber.getItems().clear();
                else {
                    for (int i = 1; i <= Lights; i++)
                        SO_lightNumber.getItems().add("" + i);
                }
            }
            if (Colors != colors) {
                Values val = new SO_colorValues();
                SO_color.getItems().clear();
                Colors = colors;
                Object[] vals = val.ValueList;
                for (int i = 1; colors != 0 && i < vals.length; i += 2) {
                    if (((Integer)vals[i - 1] & colors) != 0) {
                        SO_color.getItems().add(vals[i].toString());
                        colors &= ~(Integer)vals[i - 1];
                    }
                }
            }
            if (Alarms != alarms) {
                Values val = new SO_alarmValues();
                SO_alarm.getItems().clear();
                SOP_alarm.getItems().clear();
                Alarms = alarms;
                Object[] vals = val.ValueList;
                for (int i = 1; alarms != 0 && i < vals.length; i += 2) {
                    if (((Integer)vals[i - 1] & alarms) != 0) {
                        SO_alarm.getItems().add(vals[i].toString());
                        SOP_alarm.getItems().add(vals[i].toString());
                        alarms &= ~(Integer)vals[i - 1];
                    }
                }
            }
            if (Pattern != pattern) {
                Values val = new SOP_patternValues();
                SOP_pattern.getItems().clear();
                Pattern = pattern;
                Object[] vals = val.ValueList;
                for (int i = 1; pattern != 0 && i < vals.length; i += 2) {
                    if (((Integer)vals[i - 1] & pattern) != 0) {
                        SOP_pattern.getItems().add(vals[i].toString());
                        pattern &= ~(Integer)vals[i - 1];
                    }
                }
            }
            InUpdateGui = false;
        }
    }

    public void switchOn(ActionEvent actionEvent) {
        Integer light = new IntValues().getInteger(SO_lightNumber.getValue());
        Integer blinkOnCycle = new IntValues().getInteger(SO_blinkOnCycle.getText());
        Integer blinkOffCycle = new IntValues().getInteger(SO_blinkOffCycle.getText());
        Integer color = new SO_colorValues().getInteger(SO_color.getValue());
        Integer alarm = new SO_alarmValues().getInteger(SO_alarm.getValue());
        if (validate(new Object[]{
                light, "lightNumber",
                blinkOnCycle, "blinkOnCycle",
                blinkOffCycle, "blinkOffCycle",
                color, "color",
                alarm, "alarm"
        })){
            try {
                TheLights.switchOn(light, blinkOnCycle, blinkOffCycle, color, alarm);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void switchOff(ActionEvent actionEvent) {
        Integer light = new IntValues().getInteger(SO_lightNumber.getValue());
        if (!invalid(light, "lightNumber")){
            try {
                TheLights.switchOff(light);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void SwitchOnMultiple(ActionEvent actionEvent) {
        String lights = SO_lightNumber.getValue();
        Integer blinkOnCycle = new IntValues().getInteger(SO_blinkOnCycle.getText());
        Integer blinkOffCycle = new IntValues().getInteger(SO_blinkOffCycle.getText());
        Integer color = new SO_colorValues().getInteger(SO_color.getValue());
        Integer alarm = new SO_alarmValues().getInteger(SO_alarm.getValue());
        if (validate(new Object[]{
                lights, "lightNumbers",
                blinkOnCycle, "blinkOnCycle",
                blinkOffCycle, "blinkOffCycle",
                color, "color",
                alarm, "alarm"
        })){
            try {
                TheLights.switchOnMultiple(lights, blinkOnCycle, blinkOffCycle, color, alarm);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void switchOnPattern(ActionEvent actionEvent) {
        Integer pattern = new SOP_patternValues().getInteger(SOP_pattern.getValue());
        Integer alarm = new SO_alarmValues().getInteger(SOP_alarm.getValue());
        if (validate(new Object[]{
                pattern, "pattern",
                alarm, "alarm"
        })){
            try {
                TheLights.switchOnPattern(pattern, alarm);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    public void switchOffPattern(ActionEvent actionEvent) {
        try {
            TheLights.switchOffPattern();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class SO_colorValues extends Values {
        SO_colorValues() {
            ValueList = new Object[]{
                    LightsConst.LGT_COLOR_PRIMARY, "COLOR_PRIMARY",
                    LightsConst.LGT_COLOR_CUSTOM1, "COLOR_CUSTOM1",
                    LightsConst.LGT_COLOR_CUSTOM2, "COLOR_CUSTOM2",
                    LightsConst.LGT_COLOR_CUSTOM3, "COLOR_CUSTOM3",
                    LightsConst.LGT_COLOR_CUSTOM4, "COLOR_CUSTOM4",
                    LightsConst.LGT_COLOR_CUSTOM5, "COLOR_CUSTOM5"
            };
        }
    }

    private class SO_alarmValues extends Values {
        SO_alarmValues() {
            ValueList = new Object[]{
                    LightsConst.LGT_ALARM_NOALARM, "ALARM_NOALARM",
                    LightsConst.LGT_ALARM_SLOW, "ALARM_SLOW",
                    LightsConst.LGT_ALARM_MEDIUM, "ALARM_MEDIUM",
                    LightsConst.LGT_ALARM_FAST, "ALARM_FAST",
                    LightsConst.LGT_ALARM_CUSTOM1, "ALARM_CUSTOM1",
                    LightsConst.LGT_ALARM_CUSTOM2, "ALARM_CUSTOM2"
            };
        }
    }

    private class SOP_patternValues extends Values {
        SOP_patternValues() {
            ValueList = new Object[]{
                    LightsConst.LGT_PATTERN_NOPATTERN, "PATTERN_NOPATTERN",
                    LightsConst.LGT_PATTERN_CUSTOM1, "PATTERN_CUSTOM1",
                    LightsConst.LGT_PATTERN_CUSTOM2, "PATTERN_CUSTOM2",
                    LightsConst.LGT_PATTERN_CUSTOM3, "PATTERN_CUSTOM3",
                    LightsConst.LGT_PATTERN_CUSTOM4, "PATTERN_CUSTOM4",
                    LightsConst.LGT_PATTERN_CUSTOM5, "PATTERN_CUSTOM5",
                    LightsConst.LGT_PATTERN_CUSTOM6, "PATTERN_CUSTOM6",
                    LightsConst.LGT_PATTERN_CUSTOM7, "PATTERN_CUSTOM7",
                    LightsConst.LGT_PATTERN_CUSTOM8, "PATTERN_CUSTOM8",
                    LightsConst.LGT_PATTERN_CUSTOM9, "PATTERN_CUSTOM9",
                    LightsConst.LGT_PATTERN_CUSTOM10, "PATTERN_CUSTOM10",
                    LightsConst.LGT_PATTERN_CUSTOM11, "PATTERN_CUSTOM11",
                    LightsConst.LGT_PATTERN_CUSTOM12, "PATTERN_CUSTOM12",
                    LightsConst.LGT_PATTERN_CUSTOM13, "PATTERN_CUSTOM13",
                    LightsConst.LGT_PATTERN_CUSTOM14, "PATTERN_CUSTOM14",
                    LightsConst.LGT_PATTERN_CUSTOM15, "PATTERN_CUSTOM15",
                    LightsConst.LGT_PATTERN_CUSTOM16, "PATTERN_CUSTOM16",
                    LightsConst.LGT_PATTERN_CUSTOM17, "PATTERN_CUSTOM17",
                    LightsConst.LGT_PATTERN_CUSTOM18, "PATTERN_CUSTOM18",
                    LightsConst.LGT_PATTERN_CUSTOM19, "PATTERN_CUSTOM19",
                    LightsConst.LGT_PATTERN_CUSTOM20, "PATTERN_CUSTOM20",
                    LightsConst.LGT_PATTERN_CUSTOM21, "PATTERN_CUSTOM21",
                    LightsConst.LGT_PATTERN_CUSTOM22, "PATTERN_CUSTOM22",
                    LightsConst.LGT_PATTERN_CUSTOM23, "PATTERN_CUSTOM23",
                    LightsConst.LGT_PATTERN_CUSTOM24, "PATTERN_CUSTOM24",
                    LightsConst.LGT_PATTERN_CUSTOM25, "PATTERN_CUSTOM25",
                    LightsConst.LGT_PATTERN_CUSTOM26, "PATTERN_CUSTOM26",
                    LightsConst.LGT_PATTERN_CUSTOM27, "PATTERN_CUSTOM27",
                    LightsConst.LGT_PATTERN_CUSTOM28, "PATTERN_CUSTOM28",
                    LightsConst.LGT_PATTERN_CUSTOM29, "PATTERN_CUSTOM29",
                    LightsConst.LGT_PATTERN_CUSTOM30, "PATTERN_CUSTOM30",
                    LightsConst.LGT_PATTERN_CUSTOM31, "PATTERN_CUSTOM31",
                    LightsConst.LGT_PATTERN_CUSTOM32, "PATTERN_CUSTOM32"
            };
        }
    }
}
