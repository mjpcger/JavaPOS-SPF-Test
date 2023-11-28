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

import de.gmxhome.conrad.jpos.jpos_base.JposErrorEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import jpos.*;
import jpos.events.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for BumpBar properties, methods and events.
 */
public class BumpBarController extends CommonController {
    public CheckBox UnitsOnline01;
    public CheckBox UnitsOnline02;
    public CheckBox UnitsOnline03;
    public CheckBox UnitsOnline04;
    public CheckBox UnitsOnline05;
    public CheckBox UnitsOnline06;
    public CheckBox UnitsOnline07;
    public CheckBox UnitsOnline08;
    public CheckBox UnitsOnline09;
    public CheckBox UnitsOnline10;
    public CheckBox UnitsOnline11;
    public CheckBox UnitsOnline12;
    public CheckBox UnitsOnline13;
    public CheckBox UnitsOnline14;
    public CheckBox UnitsOnline15;
    public CheckBox UnitsOnline16;
    public CheckBox UnitsOnline17;
    public CheckBox UnitsOnline18;
    public CheckBox UnitsOnline19;
    public CheckBox UnitsOnline20;
    public CheckBox UnitsOnline21;
    public CheckBox UnitsOnline22;
    public CheckBox UnitsOnline23;
    public CheckBox UnitsOnline24;
    public CheckBox UnitsOnline25;
    public CheckBox UnitsOnline26;
    public CheckBox UnitsOnline27;
    public CheckBox UnitsOnline28;
    public CheckBox UnitsOnline29;
    public CheckBox UnitsOnline30;
    public CheckBox UnitsOnline31;
    public CheckBox UnitsOnline32;
    public CheckBox ErrorUnits01;
    public CheckBox ErrorUnits02;
    public CheckBox ErrorUnits03;
    public CheckBox ErrorUnits04;
    public CheckBox ErrorUnits05;
    public CheckBox ErrorUnits06;
    public CheckBox ErrorUnits07;
    public CheckBox ErrorUnits08;
    public CheckBox ErrorUnits09;
    public CheckBox ErrorUnits10;
    public CheckBox ErrorUnits11;
    public CheckBox ErrorUnits12;
    public CheckBox ErrorUnits13;
    public CheckBox ErrorUnits14;
    public CheckBox ErrorUnits15;
    public CheckBox ErrorUnits16;
    public CheckBox ErrorUnits17;
    public CheckBox ErrorUnits18;
    public CheckBox ErrorUnits19;
    public CheckBox ErrorUnits20;
    public CheckBox ErrorUnits21;
    public CheckBox ErrorUnits22;
    public CheckBox ErrorUnits23;
    public CheckBox ErrorUnits24;
    public CheckBox ErrorUnits25;
    public CheckBox ErrorUnits26;
    public CheckBox ErrorUnits27;
    public CheckBox ErrorUnits28;
    public CheckBox ErrorUnits29;
    public CheckBox ErrorUnits30;
    public CheckBox ErrorUnits31;
    public CheckBox ErrorUnits32;
    public CheckBox EventUnits01;
    public CheckBox EventUnits02;
    public CheckBox EventUnits03;
    public CheckBox EventUnits04;
    public CheckBox EventUnits05;
    public CheckBox EventUnits06;
    public CheckBox EventUnits07;
    public CheckBox EventUnits08;
    public CheckBox EventUnits09;
    public CheckBox EventUnits10;
    public CheckBox EventUnits11;
    public CheckBox EventUnits12;
    public CheckBox EventUnits13;
    public CheckBox EventUnits14;
    public CheckBox EventUnits15;
    public CheckBox EventUnits16;
    public CheckBox EventUnits17;
    public CheckBox EventUnits18;
    public CheckBox EventUnits19;
    public CheckBox EventUnits20;
    public CheckBox EventUnits21;
    public CheckBox EventUnits22;
    public CheckBox EventUnits23;
    public CheckBox EventUnits24;
    public CheckBox EventUnits25;
    public CheckBox EventUnits26;
    public CheckBox EventUnits27;
    public CheckBox EventUnits28;
    public CheckBox EventUnits29;
    public CheckBox EventUnits30;
    public CheckBox EventUnits31;
    public CheckBox EventUnits32;
    public CheckBox CurrentUnitID01;
    public CheckBox CurrentUnitID02;
    public CheckBox CurrentUnitID03;
    public CheckBox CurrentUnitID04;
    public CheckBox CurrentUnitID05;
    public CheckBox CurrentUnitID06;
    public CheckBox CurrentUnitID07;
    public CheckBox CurrentUnitID08;
    public CheckBox CurrentUnitID09;
    public CheckBox CurrentUnitID10;
    public CheckBox CurrentUnitID11;
    public CheckBox CurrentUnitID12;
    public CheckBox CurrentUnitID13;
    public CheckBox CurrentUnitID14;
    public CheckBox CurrentUnitID15;
    public CheckBox CurrentUnitID16;
    public CheckBox CurrentUnitID17;
    public CheckBox CurrentUnitID18;
    public CheckBox CurrentUnitID19;
    public CheckBox CurrentUnitID20;
    public CheckBox CurrentUnitID21;
    public CheckBox CurrentUnitID22;
    public CheckBox CurrentUnitID23;
    public CheckBox CurrentUnitID24;
    public CheckBox CurrentUnitID25;
    public CheckBox CurrentUnitID26;
    public CheckBox CurrentUnitID27;
    public CheckBox CurrentUnitID28;
    public CheckBox CurrentUnitID29;
    public CheckBox CurrentUnitID30;
    public CheckBox CurrentUnitID31;
    public CheckBox CurrentUnitID32;
    public ComboBox<String> CurrentUnitID;
    public TextField AutoToneDuration;
    public TextField AutoToneFrequency;
    public ComboBox<String> Timeout;
    public TextField BBS_frequency;
    public ComboBox<String> BBS_duration;
    public TextField BBS_numberOfCycles;
    public ComboBox<String> BBS_interSoundWait;
    public TextField SKT_scanCode;
    public TextField SKT_logicalKey;

    CheckBox[] UnitsOnline;

    private void initUnitsOnline() {
        UnitsOnline = new CheckBox[]{
                UnitsOnline01,
                UnitsOnline02,
                UnitsOnline03,
                UnitsOnline04,
                UnitsOnline05,
                UnitsOnline06,
                UnitsOnline07,
                UnitsOnline08,
                UnitsOnline09,
                UnitsOnline10,
                UnitsOnline11,
                UnitsOnline12,
                UnitsOnline13,
                UnitsOnline14,
                UnitsOnline15,
                UnitsOnline16,
                UnitsOnline17,
                UnitsOnline18,
                UnitsOnline19,
                UnitsOnline20,
                UnitsOnline21,
                UnitsOnline22,
                UnitsOnline23,
                UnitsOnline24,
                UnitsOnline25,
                UnitsOnline26,
                UnitsOnline27,
                UnitsOnline28,
                UnitsOnline29,
                UnitsOnline30,
                UnitsOnline31,
                UnitsOnline32
        };
    }

    CheckBox[] ErrorUnits;

    private void initErrorUnits() {
        ErrorUnits = new CheckBox[]{
                ErrorUnits01,
                ErrorUnits02,
                ErrorUnits03,
                ErrorUnits04,
                ErrorUnits05,
                ErrorUnits06,
                ErrorUnits07,
                ErrorUnits08,
                ErrorUnits09,
                ErrorUnits10,
                ErrorUnits11,
                ErrorUnits12,
                ErrorUnits13,
                ErrorUnits14,
                ErrorUnits15,
                ErrorUnits16,
                ErrorUnits17,
                ErrorUnits18,
                ErrorUnits19,
                ErrorUnits20,
                ErrorUnits21,
                ErrorUnits22,
                ErrorUnits23,
                ErrorUnits24,
                ErrorUnits25,
                ErrorUnits26,
                ErrorUnits27,
                ErrorUnits28,
                ErrorUnits29,
                ErrorUnits30,
                ErrorUnits31,
                ErrorUnits32
        };
    }

    CheckBox[] EventUnits;

    private void initEventUnits() {
        EventUnits = new CheckBox[]{
                EventUnits01,
                EventUnits02,
                EventUnits03,
                EventUnits04,
                EventUnits05,
                EventUnits06,
                EventUnits07,
                EventUnits08,
                EventUnits09,
                EventUnits10,
                EventUnits11,
                EventUnits12,
                EventUnits13,
                EventUnits14,
                EventUnits15,
                EventUnits16,
                EventUnits17,
                EventUnits18,
                EventUnits19,
                EventUnits20,
                EventUnits21,
                EventUnits22,
                EventUnits23,
                EventUnits24,
                EventUnits25,
                EventUnits26,
                EventUnits27,
                EventUnits28,
                EventUnits29,
                EventUnits30,
                EventUnits31,
                EventUnits32
        };
    }

    CheckBox[] CurrentUnits;

    private void initCurrentUnits() {
        CurrentUnits = new CheckBox[]{
                CurrentUnitID01,
                CurrentUnitID02,
                CurrentUnitID03,
                CurrentUnitID04,
                CurrentUnitID05,
                CurrentUnitID06,
                CurrentUnitID07,
                CurrentUnitID08,
                CurrentUnitID09,
                CurrentUnitID10,
                CurrentUnitID11,
                CurrentUnitID12,
                CurrentUnitID13,
                CurrentUnitID14,
                CurrentUnitID15,
                CurrentUnitID16,
                CurrentUnitID17,
                CurrentUnitID18,
                CurrentUnitID19,
                CurrentUnitID20,
                CurrentUnitID21,
                CurrentUnitID22,
                CurrentUnitID23,
                CurrentUnitID24,
                CurrentUnitID25,
                CurrentUnitID26,
                CurrentUnitID27,
                CurrentUnitID28,
                CurrentUnitID29,
                CurrentUnitID30,
                CurrentUnitID31,
                CurrentUnitID32
        };
    }
    private BumpBar TheBase;
    private PropertyTableRow CurrentUnitIDRow;
    private PropertyTableRow EventUnitIDRow;
    private PropertyTableRow UnitsOnlineRow;
    private PropertyTableRow AutoToneDurationRow;
    private PropertyTableRow AutoToneFrequencyRow;
    private PropertyTableRow TimeoutRow;
    private PropertyTableRow ErrorUnitsRow;
    private PropertyTableRow EventUnitsRow;
    private PropertyTableRow ErrorStringRow;
    private PropertyTableRow EventStringRow;

    int getUnits(CheckBox[]units) {
        int val = 0;
        for (int i = 0; i < units.length; ++i) {
            if (units[i].isSelected())
                val |= 1 << i;
        }
        return val;
    }

    void setUnits(CheckBox[]units, int val, boolean writable) {
        for (int i = 0; i < units.length; ++i) {
            units[i].setDisable(false);
            units[i].setSelected((val & (1 << i)) != 0);
            units[i].setDisable(!writable);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initUnitsOnline();
        initCurrentUnits();
        initErrorUnits();
        initEventUnits();
        super.initialize(url, resourceBundle);
        TheBase = (BumpBar) Control;
        TheBase.addDirectIOListener(this);
        TheBase.addStatusUpdateListener(this);
        TheBase.addOutputCompleteListener(this);
        TheBase.addDataListener(this);
        TheBase.addErrorListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(UnitsOnlineRow = new PropertyTableRow("UnitsOnline", ""));
        Properties.getItems().add(CurrentUnitIDRow = new PropertyTableRow("CurrentUnitID", "", new UnitIDValues()));
        Properties.getItems().add(ErrorStringRow = new PropertyTableRow("ErrorString", ""));
        Properties.getItems().add(ErrorUnitsRow = new PropertyTableRow("ErrorUnits", ""));
        Properties.getItems().add(EventStringRow = new PropertyTableRow("EventString", ""));
        Properties.getItems().add(EventUnitIDRow = new PropertyTableRow("EventUnitID", "", new UnitIDValues()));
        Properties.getItems().add(EventUnitsRow = new PropertyTableRow("EventUnits", ""));
        Properties.getItems().add(AutoToneDurationRow = new PropertyTableRow("AutoToneDuration", ""));
        Properties.getItems().add(AutoToneFrequencyRow = new PropertyTableRow("AutoToneFrequency", ""));
        Properties.getItems().add(new PropertyTableRow("CapTone", ""));
        Properties.getItems().add(new PropertyTableRow("DataCount", ""));
        Properties.getItems().add(new PropertyTableRow("BumpBarDataCount", ""));
        Properties.getItems().add(new PropertyTableRow("Keys", ""));
        Properties.getItems().add(TimeoutRow = new PropertyTableRow("Timeout", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        Values val = new UnitIDValues();
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_1));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_2));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_3));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_4));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_5));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_6));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_7));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_8));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_9));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_10));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_11));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_12));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_13));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_14));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_15));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_16));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_17));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_18));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_19));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_20));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_21));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_22));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_23));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_24));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_25));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_26));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_27));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_28));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_29));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_30));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_31));
        CurrentUnitID.getItems().add(val.getSymbol(BumpBarConst.BB_UID_32));
        Timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        setPropertyOnFocusLost(AutoToneDuration, "AutoToneDuration");
        setPropertyOnFocusLost(AutoToneFrequency, "AutoToneFrequency");
        val = new TimeoutValues();
        BBS_duration.getItems().add(val.getSymbol(JposConst.JPOS_FOREVER));
        BBS_interSoundWait.getItems().add(val.getSymbol(JposConst.JPOS_FOREVER));
        updateGui();
    }

    @Override
    String getLogString(DataEvent event) {
        int date = event.getStatus();
        return EventUnitIDRow.getValue() + ", " + ((date >> 16) & 0xff)
                + ", " + new EventTypeValues().getSymbol(date & 0xffff);
    }

    @Override
    String getLogString(ErrorEvent event) {
        String message = "Units ";
        if (event.getErrorLocus() == JposConst.JPOS_EL_OUTPUT) {
            message += ErrorUnitsRow.getValue() + ", " + super.getLogString(event);
            if (!(event instanceof JposErrorEvent) && ErrorStringRow.getValue().length() != 0) {
                message += "\n   " + ErrorStringRow.getValue();
            }
        } else {
            message += EventUnitsRow.getValue() + ", " + super.getLogString(event);
            if (!(event instanceof JposErrorEvent) && EventStringRow.getValue().length() != 0) {
                message += "\n   " + EventStringRow.getValue();
            }
        }
        return message;
    }

    @Override
    String getLogString(StatusUpdateEvent event) {
        return "Units " + EventUnitsRow.getValue() + ", " + super.getLogString(event);
    }

    @Override
    String getLogString(OutputCompleteEvent event) {
        return "Units " + EventUnitsRow.getValue() + ", " + super.getLogString(event);
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            Integer val = new IntValues().getInteger(UnitsOnlineRow.getValue());
            setUnits(UnitsOnline, val == null ? 0 : val, false);
            val = new IntValues().getInteger(EventUnitsRow.getValue());
            setUnits(EventUnits, val == null ? 0 : val, false);
            val = new IntValues().getInteger(ErrorUnitsRow.getValue());
            setUnits(ErrorUnits, val == null ? 0 : val, false);
            val = (Integer) new UnitIDValues().getValue(CurrentUnitIDRow.getValue());
            CurrentUnitID.setValue(val == null ? null : CurrentUnitIDRow.getValue());
            AutoToneDuration.setText(AutoToneDurationRow.getValue());
            AutoToneFrequency.setText(AutoToneFrequencyRow.getValue());
            Timeout.setValue(TimeoutRow.getValue());
            InUpdateGui = false;
        }
    }

    public void setCurrentUnitID(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBase.setCurrentUnitID(new UnitIDValues().getInteger(CurrentUnitID.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setAutoToneDuration(ActionEvent event) {
        Integer val = new IntValues().getInteger(AutoToneDuration.getText());
        if (invalid(val, "AutoToneDuration"))
            return;
        try {
            TheBase.setAutoToneDuration(val);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setAutoToneFrequency(ActionEvent event) {
        Integer val = new IntValues().getInteger(AutoToneFrequency.getText());
        if (invalid(val, "AutoToneFrequency"))
            return;
        try {
            TheBase.setAutoToneFrequency(val);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setTimeout(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBase.setTimeout(new TimeoutValues().getInteger(Timeout.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    class SetKeyTranslation extends MethodProcessor {
        Integer[] Parameters;
        SetKeyTranslation(Integer[] parameters) {
            super("SetKeyTranslation");
            Parameters = parameters;
        }

        @Override
        void runIt() throws JposException {
            TheBase.setKeyTranslation(Parameters[0], Parameters[1], Parameters[2]);
        }
    }

    public void setKeyTranslation(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[3];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = val.getInteger(SKT_scanCode.getText());
        params[2] = val.getInteger(SKT_logicalKey.getText());
        String[] parameterNames = new String[]{"", "scanCode", "logicalKey"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new SetKeyTranslation(params).start();
    }

    class BumpBarSound extends MethodProcessor {
        Integer[] Parameters;
        BumpBarSound(Integer[] parameters) {
            super("BumpBarSound");
            Parameters = parameters;
        }

        @Override
        void runIt() throws JposException {
            TheBase.bumpBarSound(Parameters[0], Parameters[1], Parameters[2], Parameters[3], Parameters[4]);
        }
    }

    public void bumpBarSound(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[5];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = val.getInteger(BBS_frequency.getText());
        params[3] = val.getInteger(BBS_numberOfCycles.getText());
        val = new TimeoutValues();
        params[2] = val.getInteger(BBS_duration.getValue());
        params[4] = val.getInteger(BBS_interSoundWait.getValue());
        String[] parameterNames = new String[]{"", "frequency", "duration", "numberOfCycles", "interSoundWait"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new BumpBarSound(params).start();
    }

    private class EventTypeValues extends Values {
        EventTypeValues() {
            ValueList = new Object[]{
                    0, "-",
                    BumpBarConst.BB_DE_KEY, "KEY"
            };
        }
    }

    private class UnitIDValues extends Values {
        UnitIDValues() {
            ValueList = new Object[]{
                    BumpBarConst.BB_UID_1, "UID_1",
                    BumpBarConst.BB_UID_2, "UID_2",
                    BumpBarConst.BB_UID_3, "UID_3",
                    BumpBarConst.BB_UID_4, "UID_4",
                    BumpBarConst.BB_UID_5, "UID_5",
                    BumpBarConst.BB_UID_6, "UID_6",
                    BumpBarConst.BB_UID_7, "UID_7",
                    BumpBarConst.BB_UID_8, "UID_8",
                    BumpBarConst.BB_UID_9, "UID_9",
                    BumpBarConst.BB_UID_10, "UID_10",
                    BumpBarConst.BB_UID_11, "UID_11",
                    BumpBarConst.BB_UID_12, "UID_12",
                    BumpBarConst.BB_UID_13, "UID_13",
                    BumpBarConst.BB_UID_14, "UID_14",
                    BumpBarConst.BB_UID_15, "UID_15",
                    BumpBarConst.BB_UID_16, "UID_16",
                    BumpBarConst.BB_UID_17, "UID_17",
                    BumpBarConst.BB_UID_18, "UID_18",
                    BumpBarConst.BB_UID_19, "UID_19",
                    BumpBarConst.BB_UID_20, "UID_20",
                    BumpBarConst.BB_UID_21, "UID_21",
                    BumpBarConst.BB_UID_22, "UID_22",
                    BumpBarConst.BB_UID_23, "UID_23",
                    BumpBarConst.BB_UID_24, "UID_24",
                    BumpBarConst.BB_UID_25, "UID_25",
                    BumpBarConst.BB_UID_26, "UID_26",
                    BumpBarConst.BB_UID_27, "UID_27",
                    BumpBarConst.BB_UID_28, "UID_28",
                    BumpBarConst.BB_UID_29, "UID_29",
                    BumpBarConst.BB_UID_30, "UID_30",
                    BumpBarConst.BB_UID_31, "UID_31",
                    BumpBarConst.BB_UID_32, "UID_32"
            };
        }
    }
}
