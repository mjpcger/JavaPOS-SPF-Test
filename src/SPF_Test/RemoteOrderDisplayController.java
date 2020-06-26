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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jpos.*;
import jpos.events.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for RemoteOrderDisplay properties, methods and events.
 */
public class RemoteOrderDisplayController extends CommonController {
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
    public CheckBox LockDataEventEnabled;
    public TextField AutoToneDuration;
    public TextField AutoToneFrequency;
    public ComboBox<String> CharacterSet;
    public ComboBox<String> EventType;
    public CheckBox MapCharacterSet;
    public ComboBox<String> Timeout;
    public ComboBox<String> VideoMode;
    public Label DataEvent;
    public CheckBox AE_blinking;
    public ComboBox<String> AE_bgcolor;
    public CheckBox AE_intensity;
    public ComboBox<String> AE_fgcolor;
    public Label AE_value;
    public ComboBox<String> CCl_function;
    public ComboBox<String> CCl_clockId;
    public TextField CCl_hour;
    public TextField CCl_min;
    public TextField CCl_sec;
    public TextField CCl_row;
    public TextField CCl_column;
    public ComboBox<String> CCl_attribute;
    public ComboBox<String> CCl_mode;
    public ComboBox<String> CCu_function;
    public TextField FVR_bufferId;
    public TextField SC_row;
    public TextField SC_column;
    public ComboBox<String> CV_attribute;
    public TextField CVR_row;
    public TextField CVR_column;
    public TextField CVR_height;
    public TextField CVR_width;
    public ComboBox<String> CVR_attribute;
    public TextField CpVR_row;
    public TextField CpVR_column;
    public TextField CpVR_height;
    public TextField CpVR_width;
    public TextField CpVR_targetRow;
    public TextField CpVR_targetColumn;
    public TextField SVR_row;
    public TextField SVR_column;
    public TextField SVR_height;
    public TextField SVR_width;
    public TextField SVR_bufferId;
    public TextField RVR_targetRow;
    public TextField RVR_targetColumn;
    public TextField RVR_bufferId;
    public TextField DD_row;
    public TextField DD_column;
    public ComboBox<String> DD_attribute;
    public TextField DD_data;
    public TextField DB_row;
    public TextField DB_column;
    public TextField DB_height;
    public TextField DB_width;
    public ComboBox<String> DB_attribute;
    public ComboBox<String> DB_bordertype;
    public ComboBox<String> TD_function;
    public ComboBox<String> UVRA_function;
    public TextField UVRA_row;
    public TextField UVRA_column;
    public TextField UVRA_height;
    public TextField UVRA_width;
    public ComboBox<String> UVRA_attribute;
    public TextField VS_frequency;
    public ComboBox<String> VS_duration;
    public TextField VS_numberOfCycles;
    public ComboBox<String> VS_interSoundWait;

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
    private RemoteOrderDisplay TheBase;
    private PropertyTableRow CurrentUnitIDRow;
    private PropertyTableRow UnitsOnlineRow;
    private PropertyTableRow AutoToneDurationRow;
    private PropertyTableRow AutoToneFrequencyRow;
    private PropertyTableRow EventTypeRow;
    private PropertyTableRow MapCharacterSetRow;
    private PropertyTableRow TimeoutRow;
    private PropertyTableRow VideoModeRow;
    private PropertyTableRow VideoModesListRow;
    private PropertyTableRow ErrorUnitsRow;
    private PropertyTableRow EventUnitsRow;
    private PropertyTableRow CharacterSetListRow;
    private PropertyTableRow CharacterSetRow;
    private PropertyTableRow ErrorStringRow;
    private PropertyTableRow EventStringRow;
    private PropertyTableRow EventUnitIDRow;
    private PropertyTableRow ClocksRow;

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
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheBase = (RemoteOrderDisplay) Control;
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
        Properties.getItems().add(CharacterSetRow = new PropertyTableRow("CharacterSet", "", new CharacterSetValues()));
        Properties.getItems().add(CharacterSetListRow = new PropertyTableRow("CharacterSetList", ""));
        Properties.getItems().add(new PropertyTableRow("CapMapCharacterSet", ""));
        Properties.getItems().add(new PropertyTableRow("CapSelectCharacterSet", ""));
        Properties.getItems().add(new PropertyTableRow("CapTone", ""));
        Properties.getItems().add(new PropertyTableRow("CapTouch", "", new EventTypeValues()));
        Properties.getItems().add(new PropertyTableRow("CapTransaction", ""));
        Properties.getItems().add(ClocksRow = new PropertyTableRow("Clocks", ""));
        Properties.getItems().add(new PropertyTableRow("CapTransaction", ""));
        Properties.getItems().add(new PropertyTableRow("DataCount", ""));
        Properties.getItems().add(new PropertyTableRow("VideoDataCount", ""));
        Properties.getItems().add(EventTypeRow = new PropertyTableRow("EventType", "", new EventTypeValues()));
        Properties.getItems().add(MapCharacterSetRow = new PropertyTableRow("MapCharacterSet", ""));
        Properties.getItems().add(new PropertyTableRow("SystemClocks", ""));
        Properties.getItems().add(new PropertyTableRow("SystemVideoSaveBuffers", ""));
        Properties.getItems().add(TimeoutRow = new PropertyTableRow("Timeout", ""));
        Properties.getItems().add(VideoModeRow = new PropertyTableRow("VideoMode", "", new Values()));
        Properties.getItems().add(VideoModesListRow = new PropertyTableRow("VideoModesList", ""));
        Properties.getItems().add(new PropertyTableRow("VideoSaveBuffers", ""));
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
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_1));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_2));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_3));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_4));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_5));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_6));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_7));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_8));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_9));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_10));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_11));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_12));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_13));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_14));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_15));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_16));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_17));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_18));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_19));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_20));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_21));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_22));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_23));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_24));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_25));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_26));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_27));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_28));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_29));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_30));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_31));
        CurrentUnitID.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UID_32));
        val = new BGColorValues();
        AE_bgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_BG_BLACK));
        AE_bgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_BG_BLUE));
        AE_bgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_BG_GREEN));
        AE_bgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_BG_CYAN));
        AE_bgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_BG_RED));
        AE_bgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_BG_MAGENTA));
        AE_bgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_BG_BROWN));
        AE_bgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_BG_GRAY));
        AE_bgcolor.setValue(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_BG_BLACK));
        val = new FGColorValues();
        AE_fgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_FG_BLACK));
        AE_fgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_FG_BLUE));
        AE_fgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_FG_GREEN));
        AE_fgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_FG_CYAN));
        AE_fgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_FG_RED));
        AE_fgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_FG_MAGENTA));
        AE_fgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_FG_BROWN));
        AE_fgcolor.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_FG_GRAY));
        AE_fgcolor.setValue(val.getSymbol(RemoteOrderDisplayConst.ROD_ATTR_FG_BLACK));
        Timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        setPropertyOnFocusLost(AutoToneDuration, "AutoToneDuration");
        setPropertyOnFocusLost(AutoToneFrequency, "AutoToneFrequency");
        val = new EventTypeValues();
        for (int i = 0; i < 8; i++)
            EventType.getItems().add(val.getSymbol(i));
        val = new CCl_functionValues();
        CCl_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CLK_START));
        CCl_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CLK_PAUSE));
        CCl_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CLK_RESUME));
        CCl_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CLK_STOP));
        CCl_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CLK_MOVE));
        val = new CCl_modeValues();
        CCl_mode.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CLK_SHORT));
        CCl_mode.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CLK_NORMAL));
        CCl_mode.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CLK_12_LONG));
        CCl_mode.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CLK_24_LONG));
        val = new CCu_functionValues();
        CCu_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CRS_LINE));
        CCu_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CRS_LINE_BLINK));
        CCu_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CRS_BLOCK));
        CCu_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CRS_BLOCK_BLINK));
        CCu_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_CRS_OFF));
        val = new DB_bordertypeValues();
        DB_bordertype.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_BDR_SINGLE));
        DB_bordertype.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_BDR_DOUBLE));
        DB_bordertype.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_BDR_SOLID));
        val = new TD_functionValues();
        TD_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_TD_TRANSACTION));
        TD_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_TD_NORMAL));
        val = new UVRA_functionValues();
        UVRA_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UA_SET));
        UVRA_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UA_INTENSITY_ON));
        UVRA_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UA_INTENSITY_OFF));
        UVRA_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UA_REVERSE_ON));
        UVRA_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UA_REVERSE_OFF));
        UVRA_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UA_BLINK_ON));
        UVRA_function.getItems().add(val.getSymbol(RemoteOrderDisplayConst.ROD_UA_BLINK_OFF));
        val = new TimeoutValues();
        VS_duration.getItems().add(val.getSymbol(JposConst.JPOS_FOREVER));
        VS_interSoundWait.getItems().add(val.getSymbol(JposConst.JPOS_FOREVER));
        updateAttributes();
        updateGui();
    }

    @Override
    String getLogString(DataEvent event) {
        int date = event.getStatus();
        return EventUnitIDRow.getValue() + ", " + ((date >> 24) & 0xff)
                + " / " + ((date >> 16) & 0xff)
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
        try {
            String[] videomodi = TheBase.getVideoModesList().split(",");
            Object[] values = new Object[videomodi.length * 2];
            int i = 0;
            for (String entry : videomodi) {
                String[] mode = entry.split(":");
                if (mode.length == 2) {
                    try {
                        values[i++] = Integer.parseInt(mode[0]);
                        values[i++] = mode[1];
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            VideoModeRow.getValueConverter().ValueList = Arrays.copyOf(values, i);
        } catch (JposException e) {
            VideoModeRow.getValueConverter().ValueList = new Object[0];
        }
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
            VideoMode.getItems().clear();
            for (int i = 1; i < VideoModeRow.getValueConverter().ValueList.length; i += 2) {
                VideoMode.getItems().add((String)VideoModeRow.getValueConverter().ValueList[i]);
            }
            VideoMode.setValue(VideoModeRow.getValue());
            CharacterSet.getItems().clear();
            if (CharacterSetListRow.getValue().length() > 0) {
                CharacterSetValues  cval = new CharacterSetValues();
                for (String set : CharacterSetListRow.getValue().split(",")){
                    CharacterSet.getItems().add(cval.getSymbol(cval.getInteger(set)));
                }
                CharacterSet.setValue(CharacterSetRow.getValue());
            }
            EventType.setValue(EventTypeRow.getValue());
            MapCharacterSet.setSelected(Boolean.TRUE.toString().equals(MapCharacterSetRow.getValue()));
            Integer clocks = new IntValues().getInteger(ClocksRow.getValue());
            if (clocks != null && clocks > 0) {
                String oldval = CCl_clockId.getValue();
                CCl_clockId.getItems().clear();
                for (int i = 1; i <= clocks; i++) {
                    String newval = Integer.toString(i);
                    CCl_clockId.getItems().add(newval);
                    if (newval.equals(oldval))
                        CCl_clockId.setValue(newval);
                }
            }
            InUpdateGui = false;
        }
    }

    @Override
    public void gotData(DataEvent event) {
        super.gotData(event);
        String type = new EventTypeValues().getSymbol(event.getStatus() & 0xffff);
        String unit = EventUnitIDRow.getValue();
        int row = (event.getStatus() >> 24) & 0xff;
        int column = (event.getStatus() >> 16) & 0xff;
        DataEvent.setText(String.format("%7s: %03d/%03d %s", unit, row, column, type));
        if (LockDataEventEnabled.isSelected()) {
            try {
                TheBase.setDataEventEnabled(true);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
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

    public void setEventType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBase.setEventType(new EventTypeValues().getInteger(EventType.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setMapCharacterSet(ActionEvent actionEvent) {
        try {
            TheBase.setMapCharacterSet(MapCharacterSet.isSelected());
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

    public void setVideoMode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBase.setVideoMode(VideoModeRow.getValueConverter().getInteger(VideoMode.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void selectCharacterSet(ActionEvent actionEvent) {
        Integer val = new CharacterSetValues().getInteger(CharacterSet.getValue());
        if (invalid(val, "CharacterSet"))
            return;
        try {
            TheBase.selectChararacterSet(getUnits(CurrentUnits), val);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setAE_value(ActionEvent actionEvent) {
        int value = AE_blinking.isSelected() ? 0x80 : 0;
        value |= new BGColorValues().getInteger(AE_bgcolor.getValue());
        value |= new FGColorValues().getInteger(AE_fgcolor.getValue());
        value |= AE_intensity.isSelected() ? 0x8 : 0;
        AE_value.setText(Integer.toString(value));
        updateAttributes();
    }

    private void updateAttributes() {
        String editor = String.format("Editor (%s)", AE_value.getText());
        for (ComboBox<String> cb : new ComboBox[]{CCl_attribute, CV_attribute, CVR_attribute, DD_attribute, DB_attribute, UVRA_attribute}) {
            String old = cb.getValue();
            cb.getItems().clear();
            cb.getItems().add(editor);
            if (old != null && !old.equals(cb.getValue()))
                cb.setValue(editor);
        }
    }

    private Integer getAttribute(ComboBox<String> attr) {
        Values val = new IntValues();
        Integer res = val.getInteger(attr.getValue());
        if (res == null && attr.getValue() != null) {
            String[] parts = attr.getValue().split("[()]");
            res = parts.length == 2 ? val.getInteger(parts[1]) : null;
        }
        return res;
    }

    // Synchronous methods

    class ControlClock extends MethodProcessor {
        Integer[] Parameters;
        ControlClock(Integer[] parameters) {
            super("ControlClock");
            Parameters = parameters;
        }

        @Override
        void runIt() throws JposException {
            TheBase.controlClock(Parameters[0], Parameters[1], Parameters[2], Parameters[3], Parameters[4], Parameters[5],
                    Parameters[6], Parameters[7], Parameters[8], Parameters[9]);
        }
    }

    public void controlClock(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[10];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = new CCl_functionValues().getInteger(CCl_function.getValue());
        params[2] = val.getInteger(CCl_clockId.getValue());
        params[3] = val.getInteger(CCl_hour.getText());
        params[4] = val.getInteger(CCl_min.getText());
        params[5] = val.getInteger(CCl_sec.getText());
        params[6] = val.getInteger(CCl_row.getText());
        params[7] = val.getInteger(CCl_column.getText());
        params[8] = getAttribute(CCl_attribute);
        params[9] = new CCl_modeValues().getInteger(CCl_mode.getValue());
        String[] parameterNames = new String[]{"units", "function", "clockId", "hour", "min", "sec", "row", "column", "attribute", "mode"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new ControlClock(params).start();
    }

    class ControlCursor extends MethodProcessor {
        private final int Units;
        private final int Functions;

        ControlCursor(int units, int function){
            super("ControlCursor");
            Units = units;
            Functions = function;
        }

        @Override
        void runIt() throws JposException {
            TheBase.controlCursor(Units, Functions);
        }
    }

    public void controlCursor(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer functions = new CCu_functionValues().getInteger(CCu_function.getValue());
        if (!invalid(functions, "function"))
            new ControlCursor(getUnits(CurrentUnits), functions).start();
    }

    class FreeVideoRegion extends MethodProcessor {
        private final int Units;
        private final int BufferId;

        FreeVideoRegion(int units, int bufferId){
            super("FreeVideoRegion");
            Units = units;
            BufferId = bufferId;
        }

        @Override
        void runIt() throws JposException {
            TheBase.freeVideoRegion(Units, BufferId);
        }
    }

    public void freeVideoRegion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer bufferId = new IntValues().getInteger(FVR_bufferId.getText());
        if (!invalid(bufferId, "bufferId"))
            new FreeVideoRegion(getUnits(CurrentUnits), bufferId).start();
    }

    class ResetVideo extends MethodProcessor {
        private final int Units;

        ResetVideo(int units){
            super("ResetVideo");
            Units = units;
        }

        @Override
        void runIt() throws JposException {
            TheBase.resetVideo(Units);
        }
    }

    public void resetVideo(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new ResetVideo(getUnits(CurrentUnits)).start();
    }

    class SetCursor extends MethodProcessor {
        private final int Units;
        private final int Row;
        private final int Column;

        SetCursor(int units, int row, int column){
            super("SetCursor");
            Units = units;
            Row = row;
            Column = column;
        }

        @Override
        void runIt() throws JposException {
            TheBase.setCursor(Units, Row, Column);
        }
    }

    public void setCursor(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Values ival = new IntValues();
        Integer row = ival.getInteger(SC_row.getText());
        Integer column = ival.getInteger(SC_column.getText());
        if (!invalid(row, "row") && !invalid(column, "column"))
            new SetCursor(getUnits(CurrentUnits), row, column).start();
    }

    // Asynchronous region manipulation methods

    class ClearVideo extends MethodProcessor {
        private final int Units;
        private final int Attribute;

        ClearVideo(int units, int attribute){
            super("ClearVideo");
            Units = units;
            Attribute = attribute;
        }

        @Override
        void runIt() throws JposException {
            TheBase.clearVideo(Units, Attribute);
        }
    }

    public void clearVideo(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer attribute = getAttribute(CV_attribute);
        if (!invalid(attribute, "attribute"))
            new ClearVideo(getUnits(CurrentUnits), attribute).start();
    }

    class ClearVideoRegion extends MethodProcessor {
        Integer[] Parameters;
        ClearVideoRegion(Integer[] parameters) {
            super("ClearVideoRegion");
            Parameters = parameters;
        }

        @Override
        void runIt() throws JposException {
            TheBase.clearVideoRegion(Parameters[0], Parameters[1], Parameters[2], Parameters[3], Parameters[4], Parameters[5]);
        }
    }

    public void clearVideoRegion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[6];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = val.getInteger(CVR_row.getText());
        params[2] = val.getInteger(CVR_column.getText());
        params[3] = val.getInteger(CVR_height.getText());
        params[4] = val.getInteger(CVR_width.getText());
        params[5] = getAttribute(CVR_attribute);
        String[] parameterNames = new String[]{"", "row", "column", "height", "width", "attribute"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new ClearVideoRegion(params).start();
    }

    class CopyVideoRegion extends MethodProcessor {
        Integer[] Parameters;
        CopyVideoRegion(Integer[] parameters) {
            super("CopyVideoRegion");
            Parameters = parameters;
        }

        @Override
        void runIt() throws JposException {
            TheBase.copyVideoRegion(Parameters[0], Parameters[1], Parameters[2], Parameters[3], Parameters[4],
                    Parameters[5], Parameters[6]);
        }
    }

    public void copyVideoRegion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[7];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = val.getInteger(CpVR_row.getText());
        params[2] = val.getInteger(CpVR_column.getText());
        params[3] = val.getInteger(CpVR_height.getText());
        params[4] = val.getInteger(CpVR_width.getText());
        params[5] = val.getInteger(CpVR_targetRow.getText());
        params[6] = val.getInteger(CpVR_targetColumn.getText());
        String[] parameterNames = new String[]{"", "row", "column", "height", "width", "targetRow", "targetColumn"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new CopyVideoRegion(params).start();
    }

    class SaveVideoRegion extends MethodProcessor {
        Integer[] Parameters;
        SaveVideoRegion(Integer[] parameters) {
            super("SaveVideoRegion");
            Parameters = parameters;
        }

        @Override
        void runIt() throws JposException {
            TheBase.saveVideoRegion(Parameters[0], Parameters[1], Parameters[2], Parameters[3], Parameters[4], Parameters[5]);
        }
    }

    public void saveVideoRegion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[6];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = val.getInteger(SVR_row.getText());
        params[2] = val.getInteger(SVR_column.getText());
        params[3] = val.getInteger(SVR_height.getText());
        params[4] = val.getInteger(SVR_width.getText());
        params[5] = val.getInteger(SVR_bufferId.getText());
        String[] parameterNames = new String[]{"", "row", "column", "height", "width", "bufferId"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new SaveVideoRegion(params).start();
    }

    class RestoreVideoRegion extends MethodProcessor {
        Integer[] Parameters;
        RestoreVideoRegion(Integer[] parameters) {
            super("RestoreVideoRegion");
            Parameters = parameters;
        }

        @Override
        void runIt() throws JposException {
            TheBase.restoreVideoRegion(Parameters[0], Parameters[1], Parameters[2], Parameters[3]);
        }
    }

    public void restoreVideoRegion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[4];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = val.getInteger(RVR_targetRow.getText());
        params[2] = val.getInteger(RVR_targetColumn.getText());
        params[3] = val.getInteger(RVR_bufferId.getText());
        String[] parameterNames = new String[]{"", "targetRow", "targetColumn", "bufferId"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new RestoreVideoRegion(params).start();
    }

    // Other asynchronous methods

    class DisplayData extends MethodProcessor {
        private final String Data;
        Integer[] Parameters;
        DisplayData(Integer[] parameters, String data) {
            super("DisplayData");
            Parameters = parameters;
            Data = data;
        }

        @Override
        void runIt() throws JposException {
            TheBase.displayData(Parameters[0], Parameters[1], Parameters[2], Parameters[3], Data);
        }
    }

    public void displayData(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[4];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = val.getInteger(DD_row.getText());
        params[2] = val.getInteger(DD_column.getText());
        params[3] = getAttribute(DD_attribute);
        String[] parameterNames = new String[]{"", "row", "column", "attribute"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new DisplayData(params, DD_data.getText()).start();
    }

    class DrawBox extends MethodProcessor {
        Integer[] Parameters;
        DrawBox(Integer[] parameters) {
            super("DrawBox");
            Parameters = parameters;
        }

        @Override
        void runIt() throws JposException {
            TheBase.drawBox(Parameters[0], Parameters[1], Parameters[2], Parameters[3], Parameters[4], Parameters[5], Parameters[6]);
        }
    }

    public void drawBox(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[7];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = val.getInteger(DB_row.getText());
        params[2] = val.getInteger(DB_column.getText());
        params[3] = val.getInteger(DB_height.getText());
        params[4] = val.getInteger(DB_width.getText());
        params[5] = getAttribute(DB_attribute);
        val = new DB_bordertypeValues();
        params[6] = val.getInteger(DB_bordertype.getValue());
        String[] parameterNames = new String[]{"", "row", "column", "height", "width", "attribute", "bordertype"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new DrawBox(params).start();
    }

    class TransactionDisplay extends MethodProcessor {
        private final int Units;
        private final int Functions;

        TransactionDisplay(int units, int function) {
            super("TransactionDisplay");
            Units = units;
            Functions = function;
        }

        @Override
        void runIt() throws JposException {
            TheBase.transactionDisplay(Units, Functions);
        }
    }

    public void transactionDisplay(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer function;
        function = new TD_functionValues().getInteger(TD_function.getValue());
        if (!invalid(function, "function"))
            new TransactionDisplay(getUnits(CurrentUnits), function).start();
    }

    class UpdateVideoRegionAttribute extends MethodProcessor {
        Integer[] Parameters;
        UpdateVideoRegionAttribute(Integer[] parameters) {
            super("UpdateVideoRegionAttribute");
            Parameters = parameters;
        }

        @Override
        void runIt() throws JposException {
            TheBase.updateVideoRegionAttribute(Parameters[0], Parameters[1], Parameters[2], Parameters[3], Parameters[4], Parameters[5], Parameters[6]);
        }
    }

    public void updateVideoRegionAttribute(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[7];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = new UVRA_functionValues().getInteger(UVRA_function.getValue());
        params[2] = val.getInteger(UVRA_row.getText());
        params[3] = val.getInteger(UVRA_column.getText());
        params[4] = val.getInteger(UVRA_height.getText());
        params[5] = val.getInteger(UVRA_width.getText());
        params[6] = getAttribute(UVRA_attribute);
        String[] parameterNames = new String[]{"", "function", "row", "column", "height", "width", "attribute"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new UpdateVideoRegionAttribute(params).start();
    }

    class VideoSound extends MethodProcessor {
        Integer[] Parameters;
        VideoSound(Integer[] parameters) {
            super("VideoSound");
            Parameters = parameters;
        }

        @Override
        void runIt() throws JposException {
            TheBase.videoSound(Parameters[0], Parameters[1], Parameters[2], Parameters[3], Parameters[4]);
        }
    }

    public void videoSound(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer[] params = new Integer[5];
        Values val = new IntValues();
        params[0] = getUnits(CurrentUnits);
        params[1] = val.getInteger(VS_frequency.getText());
        params[3] = val.getInteger(VS_numberOfCycles.getText());
        val = new TimeoutValues();
        params[2] = val.getInteger(VS_duration.getValue());
        params[4] = val.getInteger(VS_interSoundWait.getValue());
        String[] parameterNames = new String[]{"", "frequency", "duration", "numberOfCycles", "interSoundWait"};
        for (int i = 1; i < params.length && i < parameterNames.length; i++) {
            if (invalid(params[i], parameterNames[i]))
                return;
        }
        new VideoSound(params).start();
    }

    private class UnitIDValues extends Values {
        UnitIDValues() {
            ValueList = new Object[]{
                    RemoteOrderDisplayConst.ROD_UID_1, "UID_1",
                    RemoteOrderDisplayConst.ROD_UID_2, "UID_2",
                    RemoteOrderDisplayConst.ROD_UID_3, "UID_3",
                    RemoteOrderDisplayConst.ROD_UID_4, "UID_4",
                    RemoteOrderDisplayConst.ROD_UID_5, "UID_5",
                    RemoteOrderDisplayConst.ROD_UID_6, "UID_6",
                    RemoteOrderDisplayConst.ROD_UID_7, "UID_7",
                    RemoteOrderDisplayConst.ROD_UID_8, "UID_8",
                    RemoteOrderDisplayConst.ROD_UID_9, "UID_9",
                    RemoteOrderDisplayConst.ROD_UID_10, "UID_10",
                    RemoteOrderDisplayConst.ROD_UID_11, "UID_11",
                    RemoteOrderDisplayConst.ROD_UID_12, "UID_12",
                    RemoteOrderDisplayConst.ROD_UID_13, "UID_13",
                    RemoteOrderDisplayConst.ROD_UID_14, "UID_14",
                    RemoteOrderDisplayConst.ROD_UID_15, "UID_15",
                    RemoteOrderDisplayConst.ROD_UID_16, "UID_16",
                    RemoteOrderDisplayConst.ROD_UID_17, "UID_17",
                    RemoteOrderDisplayConst.ROD_UID_18, "UID_18",
                    RemoteOrderDisplayConst.ROD_UID_19, "UID_19",
                    RemoteOrderDisplayConst.ROD_UID_20, "UID_20",
                    RemoteOrderDisplayConst.ROD_UID_21, "UID_21",
                    RemoteOrderDisplayConst.ROD_UID_22, "UID_22",
                    RemoteOrderDisplayConst.ROD_UID_23, "UID_23",
                    RemoteOrderDisplayConst.ROD_UID_24, "UID_24",
                    RemoteOrderDisplayConst.ROD_UID_25, "UID_25",
                    RemoteOrderDisplayConst.ROD_UID_26, "UID_26",
                    RemoteOrderDisplayConst.ROD_UID_27, "UID_27",
                    RemoteOrderDisplayConst.ROD_UID_28, "UID_28",
                    RemoteOrderDisplayConst.ROD_UID_29, "UID_29",
                    RemoteOrderDisplayConst.ROD_UID_30, "UID_30",
                    RemoteOrderDisplayConst.ROD_UID_31, "UID_31",
                    RemoteOrderDisplayConst.ROD_UID_32, "UID_32"
            };
        }
    }

    private class EventTypeValues extends Values {
        EventTypeValues() {
            ValueList = new Object[]{
                    0, "-",
                    RemoteOrderDisplayConst.ROD_DE_TOUCH_UP, "TOUCH_UP",
                    RemoteOrderDisplayConst.ROD_DE_TOUCH_DOWN, "TOUCH_DOWN",
                    RemoteOrderDisplayConst.ROD_DE_TOUCH_UP|RemoteOrderDisplayConst.ROD_DE_TOUCH_DOWN, "TOUCH_UP|DOWN",
                    RemoteOrderDisplayConst.ROD_DE_TOUCH_MOVE, "TOUCH_MOVE",
                    RemoteOrderDisplayConst.ROD_DE_TOUCH_UP|RemoteOrderDisplayConst.ROD_DE_TOUCH_MOVE, "TOUCH_UP|MOVE",
                    RemoteOrderDisplayConst.ROD_DE_TOUCH_DOWN|RemoteOrderDisplayConst.ROD_DE_TOUCH_MOVE, "TOUCH_DOWN|MOVE",
                    RemoteOrderDisplayConst.ROD_DE_TOUCH_UP|RemoteOrderDisplayConst.ROD_DE_TOUCH_DOWN|RemoteOrderDisplayConst.ROD_DE_TOUCH_MOVE, "TOUCH_UP|DOWN|MOVE",
            };
        }
    }

    private class CharacterSetValues extends Values {
        CharacterSetValues() {
            ValueList = new Object[]{
                    RemoteOrderDisplayConst.ROD_CS_UNICODE, "CS_UNICODE",
                    RemoteOrderDisplayConst.ROD_CS_ASCII, "CS_ASCII",
                    RemoteOrderDisplayConst.ROD_CS_ANSI, "CS_ANSI"
            };
        }
    }

    private class BGColorValues extends Values {
        BGColorValues(){
            ValueList = new Object[]{
                    RemoteOrderDisplayConst.ROD_ATTR_BG_BLACK, "BG_BLACK",
                    RemoteOrderDisplayConst.ROD_ATTR_BG_BLUE, "BG_BLUE",
                    RemoteOrderDisplayConst.ROD_ATTR_BG_GREEN, "BG_GREEN",
                    RemoteOrderDisplayConst.ROD_ATTR_BG_CYAN, "BG_CYAN",
                    RemoteOrderDisplayConst.ROD_ATTR_BG_RED, "BG_RED",
                    RemoteOrderDisplayConst.ROD_ATTR_BG_MAGENTA, "BG_MAGENTA",
                    RemoteOrderDisplayConst.ROD_ATTR_BG_BROWN, "BG_BROWN",
                    RemoteOrderDisplayConst.ROD_ATTR_BG_GRAY, "BG_GRAY"
            };
        }
    }

    private class FGColorValues extends Values {
        FGColorValues(){
            ValueList = new Object[]{
                    RemoteOrderDisplayConst.ROD_ATTR_FG_BLACK, "FG_BLACK",
                    RemoteOrderDisplayConst.ROD_ATTR_FG_BLUE, "FG_BLUE",
                    RemoteOrderDisplayConst.ROD_ATTR_FG_GREEN, "FG_GREEN",
                    RemoteOrderDisplayConst.ROD_ATTR_FG_CYAN, "FG_CYAN",
                    RemoteOrderDisplayConst.ROD_ATTR_FG_RED, "FG_RED",
                    RemoteOrderDisplayConst.ROD_ATTR_FG_MAGENTA, "FG_MAGENTA",
                    RemoteOrderDisplayConst.ROD_ATTR_FG_BROWN, "FG_BROWN",
                    RemoteOrderDisplayConst.ROD_ATTR_FG_GRAY, "FG_GRAY"
            };
        }
    }

    private class CCl_functionValues extends Values {
        CCl_functionValues() {
            ValueList = new Object[]{
                    RemoteOrderDisplayConst.ROD_CLK_START, "CLK_START",
                    RemoteOrderDisplayConst.ROD_CLK_PAUSE, "CLK_PAUSE",
                    RemoteOrderDisplayConst.ROD_CLK_RESUME, "CLK_RESUME",
                    RemoteOrderDisplayConst.ROD_CLK_STOP, "CLK_STOP",
                    RemoteOrderDisplayConst.ROD_CLK_MOVE, "CLK_MOVE"
            };
        }
    }

    private class CCl_modeValues extends Values {
        CCl_modeValues() {
            ValueList = new Object[]{
                    RemoteOrderDisplayConst.ROD_CLK_SHORT, "CLK_SHORT",
                    RemoteOrderDisplayConst.ROD_CLK_NORMAL, "CLK_NORMAL",
                    RemoteOrderDisplayConst.ROD_CLK_12_LONG, "CLK_12_LONG",
                    RemoteOrderDisplayConst.ROD_CLK_24_LONG, "CLK_24_LONG"
            };
        }
    }

    private class CCu_functionValues extends Values {
        CCu_functionValues() {
            ValueList = new Object[]{
                    RemoteOrderDisplayConst.ROD_CRS_LINE, "CRS_LINE",
                    RemoteOrderDisplayConst.ROD_CRS_LINE_BLINK, "CRS_LINE_BLINK",
                    RemoteOrderDisplayConst.ROD_CRS_BLOCK, "CRS_BLOCK",
                    RemoteOrderDisplayConst.ROD_CRS_BLOCK_BLINK, "CRS_BLOCK_BLINK",
                    RemoteOrderDisplayConst.ROD_CRS_OFF, "CRS_OFF"
            };
        }
    }

    private class TD_functionValues extends Values {
        TD_functionValues() {
            ValueList = new Object[]{
                    RemoteOrderDisplayConst.ROD_TD_TRANSACTION, "TD_TRANSACTION",
                    RemoteOrderDisplayConst.ROD_TD_NORMAL, "TD_NORMAL"
            };
        }
    }

    private class DB_bordertypeValues extends Values {
        DB_bordertypeValues() {
            ValueList = new Object[]{
                    RemoteOrderDisplayConst.ROD_BDR_SINGLE, "BDR_SINGLE",
                    RemoteOrderDisplayConst.ROD_BDR_DOUBLE, "BDR_DOUBLE",
                    RemoteOrderDisplayConst.ROD_BDR_SOLID, "BDR_SOLID"
            };
        }
    }

    private class UVRA_functionValues extends Values {
        UVRA_functionValues() {
            ValueList = new Object[]{
                    RemoteOrderDisplayConst.ROD_UA_SET, "UA_SET",
                    RemoteOrderDisplayConst.ROD_UA_INTENSITY_ON, "UA_INTENSITY_ON",
                    RemoteOrderDisplayConst.ROD_UA_INTENSITY_OFF, "UA_INTENSITY_OFF",
                    RemoteOrderDisplayConst.ROD_UA_REVERSE_ON, "UA_REVERSE_ON",
                    RemoteOrderDisplayConst.ROD_UA_REVERSE_OFF, "UA_REVERSE_OFF",
                    RemoteOrderDisplayConst.ROD_UA_BLINK_ON, "UA_BLINK_ON",
                    RemoteOrderDisplayConst.ROD_UA_BLINK_OFF, "UA_BLINK_OFF"
            };
        }
    }
}
