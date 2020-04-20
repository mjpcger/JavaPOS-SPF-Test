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
import javafx.stage.FileChooser;
import jpos.*;
import jpos.events.ErrorEvent;
import jpos.events.OutputCompleteEvent;
import jpos.events.StatusUpdateEvent;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for POSPrinter properties, methods and events.
 */
public class POSPrinterController extends CommonController {
    public CheckBox FlagWhenIdle;
    public Button ValidateData;
    public CheckBox MapCharacterSet;
    public CheckBox JrnLetterQuality;
    public CheckBox RecLetterQuality;
    public CheckBox SlpLetterQuality;
    public ComboBox<String> CartridgeNotify;
    public ComboBox<String> CharacterSet;
    public ComboBox<String> MapMode;
    public ComboBox<String> RotateSpecial;
    public TextField PageModePrintArea;
    public TextField PageModeHorizontalPosition;
    public TextField PageModeVerticalPosition;
    public ComboBox<String> PageModePrintDirection;
    public ComboBox<String> PageModeStation;
    public TextArea EventOutput;
    public ComboBox<String> JrnCurrentCartridge;
    public ComboBox<String> JrnLineChars;
    public TextField JrnLineHeight;
    public TextField JrnLineSpacing;
    public ComboBox<String> RecCurrentCartridge;
    public ComboBox<String> RecLineChars;
    public TextField RecLineHeight;
    public TextField RecLineSpacing;
    public ComboBox<String> SlpCurrentCartridge;
    public ComboBox<String> SlpLineChars;
    public TextField SlpLineHeight;
    public TextField SlpLineSpacing;
    public ComboBox<String> P_station;
    public ComboBox<String> SL_location;
    public ComboBox<String> PTN_station;
    public TextArea P_data;
    public TextArea PTN_data2;
    public TextField CP_percentage;
    public ComboBox<String> MF_type;
    public ComboBox<String> DRL_station;
    public TextField DRL_positionList;
    public ComboBox<String> DRL_lineDirection;
    public TextField DRL_lineWidth;
    public ComboBox<String> DRL_lineStyle;
    public TextField DRL_lineColor;
    public ComboBox<String> PBC_station;
    public TextField PBC_data;
    public ComboBox<String> PBC_symbology;
    public TextField PBC_height;
    public TextField PBC_width;
    public ComboBox<String> PBC_alignment;
    public ComboBox<String> PBC_textPosition;
    public ComboBox<String> PB_station;
    public TextField PB_fileName;
    public ComboBox<String> PB_width;
    public ComboBox<String> PB_alignment;
    public ComboBox<String> PMB_station;
    public TextField PMB_data;
    public ComboBox<String> PMB_type;
    public ComboBox<String> PMB_width;
    public ComboBox<String> PMB_alignment;
    public ComboBox<String> SB_bitmapNumber;
    public ComboBox<String> SB_station;
    public TextField SB_fileName;
    public ComboBox<String> SB_width;
    public ComboBox<String> SB_alignment;
    public ComboBox<String> BI_timeout;
    public ComboBox<String> BR_timeout;
    public ComboBox<String> CPS_side;
    public ComboBox<String> PMP_control;
    public ComboBox<String> RP_station;
    public ComboBox<String> RP_rotation;
    public ComboBox<String> TP_station;
    public ComboBox<String> TP_control;

    private POSPrinter ThePrinter;

    private PropertyTableRow CartridgeNotifyRow;
    private PropertyTableRow CharacterSetRow;
    private PropertyTableRow CharacterSetListRow;
    private PropertyTableRow FlagWhenIdleRow;
    private PropertyTableRow JrnCurrentCartridgeRow;
    private PropertyTableRow JrnLetterQualityRow;
    private PropertyTableRow JrnLineCharsRow;
    private PropertyTableRow JrnLineCharsListRow;
    private PropertyTableRow JrnLineHeightRow;
    private PropertyTableRow JrnLineSpacingRow;
    private PropertyTableRow MapCharacterSetRow;
    private PropertyTableRow MapModeRow;
    private PropertyTableRow PageModeHorizontalPositionRow;
    private PropertyTableRow PageModePrintAreaRow;
    private PropertyTableRow PageModePrintDirectionRow;
    private PropertyTableRow PageModeStationRow;
    private PropertyTableRow PageModeVerticalPositionRow;
    private PropertyTableRow RecCurrentCartridgeRow;
    private PropertyTableRow RecLetterQualityRow;
    private PropertyTableRow RecLineCharsRow;
    private PropertyTableRow RecLineCharsListRow;
    private PropertyTableRow RecLineHeightRow;
    private PropertyTableRow RecLineSpacingRow;
    private PropertyTableRow RotateSpecialRow;
    private PropertyTableRow SlpCurrentCartridgeRow;
    private PropertyTableRow SlpLetterQualityRow;
    private PropertyTableRow SlpLineCharsRow;
    private PropertyTableRow SlpLineCharsListRow;
    private PropertyTableRow SlpLineHeightRow;
    private PropertyTableRow SlpLineSpacingRow;
    private PropertyTableRow CapJrnColorRow;
    private PropertyTableRow CapRecColorRow;
    private PropertyTableRow CapSlpColorRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Hexadecimal;
        super.initialize(url, resourceBundle);
        ThePrinter = (POSPrinter) Control;
        ThePrinter.addDirectIOListener(this);
        ThePrinter.addStatusUpdateListener(this);
        ThePrinter.addErrorListener(this);
        ThePrinter.addOutputCompleteListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("CapCharacterSet", "", new CapCharacterSetValues()));
        Properties.getItems().add(new PropertyTableRow("CapConcurrentJrnRec", ""));
        Properties.getItems().add(new PropertyTableRow("CapConcurrentJrnSlp", ""));
        Properties.getItems().add(new PropertyTableRow("CapConcurrentPageMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapConcurrentRecSlp", ""));
        Properties.getItems().add(new PropertyTableRow("CapCoverSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrn2Color", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnBold", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnCartridgeSensor", "", new HexValues()));
        Properties.getItems().add(CapJrnColorRow = new PropertyTableRow("CapJrnColor", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapJrnDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnDwide", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnDwideDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnItalic", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnNearEndSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnPresent", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnUnderline", ""));
        Properties.getItems().add(new PropertyTableRow("CapMapCharacterSet", ""));
        Properties.getItems().add(new PropertyTableRow("CapRec2Color", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecBarCode", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecBitmap", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecBold", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecCartridgeSensor", "", new HexValues()));
        Properties.getItems().add(CapRecColorRow = new PropertyTableRow("CapRecColor", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapRecDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecDwide", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecDwideDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecItalic", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecLeft90", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecMarkFeed", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapRecNearEndSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecPageMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecPapercut", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecPresent", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecRight90", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecRotate180", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecRuledLine", "", new CapRuledLineValues()));
        Properties.getItems().add(new PropertyTableRow("CapRecStamp", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecUnderline", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlp2Color", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpBarCode", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpBitmap", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpBold", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpBothSidesPrint", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpCartridgeSensor", "", new HexValues()));
        Properties.getItems().add(CapSlpColorRow = new PropertyTableRow("CapSlpColor", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapSlpDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpDwide", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpDwideDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpFullslip", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpItalic", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpLeft90", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpNearEndSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpPageMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpPresent", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpRight90", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpRotate180", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpRuledLine", "", new CapRuledLineValues()));
        Properties.getItems().add(new PropertyTableRow("CapSlpUnderline", ""));
        Properties.getItems().add(new PropertyTableRow("CapTransaction", ""));
        Properties.getItems().add(CartridgeNotifyRow = new PropertyTableRow("CartridgeNotify", "", new CartridgeNotifyValues()));
        Properties.getItems().add(CharacterSetRow = new PropertyTableRow("CharacterSet", "", new CharacterSetValues()));
        Properties.getItems().add(CharacterSetListRow = new PropertyTableRow("CharacterSetList", ""));
        Properties.getItems().add(new PropertyTableRow("CoverOpen", ""));
        Properties.getItems().add(new PropertyTableRow("ErrorLevel", "", new ErrorLevelValues()));
        Properties.getItems().add(new PropertyTableRow("ErrorStation", "", new ErrorStationValues()));
        Properties.getItems().add(new PropertyTableRow("ErrorString", ""));
        Properties.getItems().add(FlagWhenIdleRow = new PropertyTableRow("FlagWhenIdle", ""));
        Properties.getItems().add(new PropertyTableRow("FontTypefaceList", ""));
        Properties.getItems().add(new PropertyTableRow("JrnCartridgeState", "", new CartridgeStateValues()));
        Properties.getItems().add(JrnCurrentCartridgeRow= new PropertyTableRow("JrnCurrentCartridge", "", new CurrentCartridgeValues()));
        Properties.getItems().add(new PropertyTableRow("JrnEmpty", ""));
        Properties.getItems().add(JrnLetterQualityRow = new PropertyTableRow("JrnLetterQuality", ""));
        Properties.getItems().add(JrnLineCharsRow = new PropertyTableRow("JrnLineChars", ""));
        Properties.getItems().add(JrnLineCharsListRow = new PropertyTableRow("JrnLineCharsList", ""));
        Properties.getItems().add(JrnLineHeightRow = new PropertyTableRow("JrnLineHeight", ""));
        Properties.getItems().add(JrnLineSpacingRow = new PropertyTableRow("JrnLineSpacing", ""));
        Properties.getItems().add(new PropertyTableRow("JrnLineWidth", ""));
        Properties.getItems().add(new PropertyTableRow("JrnNearEnd", ""));
        Properties.getItems().add(MapCharacterSetRow = new PropertyTableRow("MapCharacterSet", ""));
        Properties.getItems().add(MapModeRow = new PropertyTableRow("MapMode", "", new MapModeValues()));
        Properties.getItems().add(new PropertyTableRow("PageModeArea", ""));
        Properties.getItems().add(new PropertyTableRow("PageModeDescriptor", "",new HexValues()));
        Properties.getItems().add(PageModeHorizontalPositionRow = new PropertyTableRow("PageModeHorizontalPosition", ""));
        Properties.getItems().add(PageModePrintAreaRow = new PropertyTableRow("PageModePrintArea", ""));
        Properties.getItems().add(PageModePrintDirectionRow = new PropertyTableRow("PageModePrintDirection", "", new PageModePrintDirectionValues()));
        Properties.getItems().add(PageModeStationRow = new PropertyTableRow("PageModeStation", "", new PageModeStationValues()));
        Properties.getItems().add(PageModeVerticalPositionRow = new PropertyTableRow("PageModeVerticalPosition", ""));
        Properties.getItems().add(new PropertyTableRow("RecBarCodeRotationList", ""));
        Properties.getItems().add(new PropertyTableRow("RecBitmapRotationList", ""));
        Properties.getItems().add(new PropertyTableRow("RecCartridgeState", "", new CartridgeStateValues()));
        Properties.getItems().add(RecCurrentCartridgeRow = new PropertyTableRow("RecCurrentCartridge", "", new CurrentCartridgeValues()));
        Properties.getItems().add(new PropertyTableRow("RecEmpty", ""));
        Properties.getItems().add(RecLetterQualityRow = new PropertyTableRow("RecLetterQuality", ""));
        Properties.getItems().add(RecLineCharsRow = new PropertyTableRow("RecLineChars", ""));
        Properties.getItems().add(RecLineCharsListRow = new PropertyTableRow("RecLineCharsList", ""));
        Properties.getItems().add(RecLineHeightRow = new PropertyTableRow("RecLineHeight", ""));
        Properties.getItems().add(RecLineSpacingRow = new PropertyTableRow("RecLineSpacing", ""));
        Properties.getItems().add(new PropertyTableRow("RecLinesToPaperCut", ""));
        Properties.getItems().add(new PropertyTableRow("RecLineWidth", ""));
        Properties.getItems().add(new PropertyTableRow("RecNearEnd", ""));
        Properties.getItems().add(new PropertyTableRow("RecSidewaysMaxChars", ""));
        Properties.getItems().add(new PropertyTableRow("RecSidewaysMaxLines", ""));
        Properties.getItems().add(RotateSpecialRow = new PropertyTableRow("RotateSpecial", "", new RotateSpecialValues()));
        Properties.getItems().add(new PropertyTableRow("SlpBarCodeRotationList", ""));
        Properties.getItems().add(new PropertyTableRow("SlpBitmapRotationList", ""));
        Properties.getItems().add(new PropertyTableRow("SlpCartridgeState", "", new CartridgeStateValues()));
        Properties.getItems().add(SlpCurrentCartridgeRow = new PropertyTableRow("SlpCurrentCartridge", "", new CurrentCartridgeValues()));
        Properties.getItems().add(new PropertyTableRow("SlpEmpty", ""));
        Properties.getItems().add(SlpLetterQualityRow = new PropertyTableRow("SlpLetterQuality", ""));
        Properties.getItems().add(SlpLineCharsRow = new PropertyTableRow("SlpLineChars", ""));
        Properties.getItems().add(SlpLineCharsListRow = new PropertyTableRow("SlpLineCharsList", ""));
        Properties.getItems().add(SlpLineHeightRow = new PropertyTableRow("SlpLineHeight", ""));
        Properties.getItems().add(new PropertyTableRow("SlpLinesNearEndToEnd", ""));
        Properties.getItems().add(SlpLineSpacingRow = new PropertyTableRow("SlpLineSpacing", ""));
        Properties.getItems().add(new PropertyTableRow("SlpLineWidth", ""));
        Properties.getItems().add(new PropertyTableRow("SlpMaxLines", ""));
        Properties.getItems().add(new PropertyTableRow("SlpNearEnd", ""));
        Properties.getItems().add(new PropertyTableRow("SlpPrintSide", "", new SlpPrintSideValues()));
        Properties.getItems().add(new PropertyTableRow("SlpSidewaysMaxChars", ""));
        Properties.getItems().add(new PropertyTableRow("SlpSidewaysMaxLines", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        CartridgeNotify.getItems().add(CartridgeNotifyRow.getValueConverter().getSymbol(POSPrinterConst.PTR_CN_DISABLED));
        CartridgeNotify.getItems().add(CartridgeNotifyRow.getValueConverter().getSymbol(POSPrinterConst.PTR_CN_ENABLED));
        for (int i = 1; i < MapModeRow.getValueConverter().ValueList.length; i += 2)
            MapMode.getItems().add(MapModeRow.getValueConverter().ValueList[i].toString());
        ValidateData.setText("Validate\n\nuses station\nparameter from\nprint methods");
        for (int i = 1; i < RotateSpecialRow.getValueConverter().ValueList.length; i += 2)
            RotateSpecial.getItems().add(RotateSpecialRow.getValueConverter().ValueList[i].toString());
        for (int i = 1; i < PageModePrintDirectionRow.getValueConverter().ValueList.length; i += 2)
            PageModePrintDirection.getItems().add(PageModePrintDirectionRow.getValueConverter().ValueList[i].toString());
        for (int i = 1; i < PageModeStationRow.getValueConverter().ValueList.length; i += 2) {
            String symbol = PageModeStationRow.getValueConverter().ValueList[i].toString();
            PageModeStation.getItems().add(symbol);
            DRL_station.getItems().add(symbol);
            PBC_station.getItems().add(symbol);
            PB_station.getItems().add(symbol);
            PMB_station.getItems().add(symbol);
            SB_station.getItems().add(symbol);
            RP_station.getItems().add(symbol);
        }
        Object[] values = new P_stationValues().ValueList;
        for (int i = 1; i < values.length; i += 2) {
            P_station.getItems().add(values[i].toString());
            TP_station.getItems().add(values[i].toString());
        }
        values = new SL_locationValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            SL_location.getItems().add(values[i].toString());
        values = new PTN_stationValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            PTN_station.getItems().add(values[i].toString());
        values = new MF_typeValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            MF_type.getItems().add(values[i].toString());
        values = new DRL_lineDirectionValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            DRL_lineDirection.getItems().add(values[i].toString());
        values = new DRL_lineStyleValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            DRL_lineStyle.getItems().add(values[i].toString());
        values = new PBC_symbologyValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            PBC_symbology.getItems().add(values[i].toString());
        values = new PBC_alignmentValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            PBC_alignment.getItems().add(values[i].toString());
        values = new PBC_textPositionValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            PBC_textPosition.getItems().add(values[i].toString());
        values = new B_widthValues().ValueList;
        for (int i = 1; i < values.length; i += 2) {
            PB_width.getItems().add(values[i].toString());
            PMB_width.getItems().add(values[i].toString());
            SB_width.getItems().add(values[i].toString());
        }
        values = new B_alignmentValues().ValueList;
        for (int i = 1; i < values.length; i += 2) {
            PB_alignment.getItems().add(values[i].toString());
            PMB_alignment.getItems().add(values[i].toString());
            SB_alignment.getItems().add(values[i].toString());
        }
        values = new PMB_typeValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            PMB_type.getItems().add(values[i].toString());
        for (int i = 1; i <= 20; i++)
            SB_bitmapNumber.getItems().add(String.valueOf(i));
        values = new TimeoutValues().ValueList;
        for (int i = 1; i < values.length; i += 2) {
            BI_timeout.getItems().add(values[i].toString());
            BR_timeout.getItems().add(values[i].toString());
        }
        values = new CPS_sideValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            CPS_side.getItems().add(values[i].toString());
        values = new PMP_controlValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            PMP_control.getItems().add(values[i].toString());
        values = new RP_rotationValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            RP_rotation.getItems().add(values[i].toString());
        values = new TP_controlValues().ValueList;
        for (int i = 1; i < values.length; i += 2)
            TP_control.getItems().add(values[i].toString());
        setPropertyOnFocusLost(PageModePrintArea, "PageModePrintArea");
        setPropertyOnFocusLost(PageModeHorizontalPosition, "PageModeHorizontalPosition");
        setPropertyOnFocusLost(PageModeVerticalPosition, "PageModeVerticalPosition");
        setPropertyOnFocusLost(JrnLineHeight, "JrnLineHeight");
        setPropertyOnFocusLost(JrnLineSpacing, "JrnLineSpacing");
        setPropertyOnFocusLost(RecLineHeight, "RecLineHeight");
        setPropertyOnFocusLost(RecLineSpacing, "RecLineSpacing");
        setPropertyOnFocusLost(SlpLineHeight, "SlpLineHeight");
        setPropertyOnFocusLost(SlpLineSpacing, "SlpLineSpacing");
        EventOutput.setDisable(true);
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            FlagWhenIdle.setSelected("true".equals(FlagWhenIdleRow.getValue()));
            MapCharacterSet.setSelected("true".equals(MapCharacterSetRow.getValue()));
            JrnLetterQuality.setSelected("true".equals(JrnLetterQualityRow.getValue()));
            RecLetterQuality.setSelected("true".equals(RecLetterQualityRow.getValue()));
            SlpLetterQuality.setSelected("true".equals(SlpLetterQualityRow.getValue()));
            CartridgeNotify.setValue(CartridgeNotifyRow.getValue());
            if (CharacterSet.getItems().size() == 0 && !CharacterSetListRow.getValue().equals("")) {
                String[] supported = CharacterSetListRow.getValue().split(",");
                try {
                    for (int i = 0; i < supported.length; i++)
                        CharacterSet.getItems().add(CharacterSetRow.getValueConverter().getSymbol(Integer.parseInt(supported[i])));
                } catch (Exception e) {}
            }
            CharacterSet.setValue(CharacterSetRow.getValue());
            MapMode.setValue(MapModeRow.getValue());
            RotateSpecial.setValue(RotateSpecialRow.getValue());
            PageModePrintArea.setText(PageModePrintAreaRow.getValue());
            PageModeHorizontalPosition.setText(PageModeHorizontalPositionRow.getValue());
            PageModeVerticalPosition.setText(PageModeVerticalPositionRow.getValue());
            PageModePrintDirection.setValue(PageModePrintDirectionRow.getValue());
            PageModeStation.setValue(PageModeStationRow.getValue());
            Integer val = CapJrnColorRow.getValueConverter().getInteger(CapJrnColorRow.getValue());
            if (val != null && val != 0) {
                if (JrnCurrentCartridge.getItems().size() == 0) {
                    Object[] values = JrnCurrentCartridgeRow.getValueConverter().ValueList;
                    for (int i = 0; i < values.length; i += 2) {
                        if ((val & (int) values[i]) != 0)
                            JrnCurrentCartridge.getItems().add(values[i + 1].toString());
                    }
                }
                JrnCurrentCartridge.setValue(JrnCurrentCartridgeRow.getValue());
            }
            if (JrnLineChars.getItems().size() == 0 && !JrnLineCharsListRow.getValue().equals("")) {
                String[] values = JrnLineCharsListRow.getValue().split(",");
                for (String symbol : values)
                    JrnLineChars.getItems().add(symbol);
            }
            if (JrnLineChars.getItems().size() != 0)
                JrnLineChars.setValue(JrnLineCharsRow.getValue());
            JrnLineHeight.setText(JrnLineHeightRow.getValue());
            JrnLineSpacing.setText(JrnLineSpacingRow.getValue());
            if ((val = CapRecColorRow.getValueConverter().getInteger(CapRecColorRow.getValue())) != null && val != 0) {
                if (RecCurrentCartridge.getItems().size() == 0) {
                    Object[] values = RecCurrentCartridgeRow.getValueConverter().ValueList;
                    for (int i = 0; i < values.length; i += 2) {
                        if ((val & (int) values[i]) != 0)
                            RecCurrentCartridge.getItems().add(values[i + 1].toString());
                    }
                }
                RecCurrentCartridge.setValue(RecCurrentCartridgeRow.getValue());
            }
            if (RecLineChars.getItems().size() == 0 && !RecLineCharsListRow.getValue().equals("")) {
                String[] values = RecLineCharsListRow.getValue().split(",");
                for (String symbol : values)
                    RecLineChars.getItems().add(symbol);
            }
            if (RecLineChars.getItems().size() != 0)
                RecLineChars.setValue(RecLineCharsRow.getValue());
            RecLineHeight.setText(RecLineHeightRow.getValue());
            RecLineSpacing.setText(RecLineSpacingRow.getValue());
            if ((val = CapSlpColorRow.getValueConverter().getInteger(CapSlpColorRow.getValue())) != null && val != 0) {
                if (SlpCurrentCartridge.getItems().size() == 0) {
                    Object[] values = SlpCurrentCartridgeRow.getValueConverter().ValueList;
                    for (int i = 0; i < values.length; i += 2) {
                        if ((val & (int) values[i]) != 0)
                            SlpCurrentCartridge.getItems().add(values[i + 1].toString());
                    }
                }
                SlpCurrentCartridge.setValue(SlpCurrentCartridgeRow.getValue());
            }
            if (SlpLineChars.getItems().size() == 0 && !SlpLineCharsListRow.getValue().equals("")) {
                String[] values = SlpLineCharsListRow.getValue().split(",");
                for (String symbol : values)
                    SlpLineChars.getItems().add(symbol);
            }
            if (SlpLineChars.getItems().size() > 0)
                SlpLineChars.setValue(SlpLineCharsRow.getValue());
            SlpLineHeight.setText(SlpLineHeightRow.getValue());
            SlpLineSpacing.setText(SlpLineSpacingRow.getValue());
            InUpdateGui = false;
        }
    }

    private int RowCount = 0;

    private void output(String logline) {
        EventOutput.setDisable(false);
        if (++RowCount > 1000) {
            --RowCount;
            EventOutput.deleteText(0, EventOutput.getText().indexOf("\n") + 1);
        }
        EventOutput.appendText("\n" + logline);
    }

    @Override
    public void gotOutputComplete(OutputCompleteEvent event) {
        super.gotOutputComplete(event);
        output("OC: ID = " + event.getOutputID());
    }

    @Override
    public void gotStatusUpdate(StatusUpdateEvent event) {
        super.gotStatusUpdate(event);
        String symbol = new PrtStatusUpdateValues().getSymbol(event.getStatus());
        output("SUE: " + symbol.substring(symbol.indexOf("SUE_") == 0 ? 4 : 0));
    }

    @Override
    public void preGotError(ErrorEvent event) {
        super.preGotError(event);
        String error = new ErrorCodeValues().getSymbol(event.getErrorCode());
        if (event.getErrorCode() == JposConst.JPOS_E_EXTENDED)
            error += " / " + new ExtendedErrorCodeValues().getSymbol(event.getErrorCodeExtended());
        else if (event.getErrorCodeExtended() != 0)
            error += event.getErrorCode();
        output("EE: " + error);
    }

    public void setFlagWhenIdle(ActionEvent actionEvent) {
        try {
            ThePrinter.setFlagWhenIdle(FlagWhenIdle.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setPageModePrintArea(ActionEvent event) {
        try {
            ThePrinter.setPageModePrintArea(PageModePrintArea.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setPageModeHorizontalPosition(ActionEvent event) {
        try {
            ThePrinter.setPageModeHorizontalPosition(Integer.parseInt(PageModeHorizontalPosition.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setPageModeVerticalPosition(ActionEvent event) {
        try {
            ThePrinter.setPageModeVerticalPosition(Integer.parseInt(PageModeVerticalPosition.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setJrnLineHeight(ActionEvent event) {
        try {
            ThePrinter.setJrnLineHeight(Integer.parseInt(JrnLineHeight.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setJrnLineSpacing(ActionEvent event) {
        try {
            ThePrinter.setJrnLineSpacing(Integer.parseInt(JrnLineSpacing.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setRecLineHeight(ActionEvent event) {
        try {
            ThePrinter.setRecLineHeight(Integer.parseInt(RecLineHeight.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setRecLineSpacing(ActionEvent event) {
        try {
            ThePrinter.setRecLineSpacing(Integer.parseInt(RecLineSpacing.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setSlpLineHeight(ActionEvent event) {
        try {
            ThePrinter.setSlpLineHeight(Integer.parseInt(SlpLineHeight.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setSlpLineSpacing(ActionEvent event) {
        try {
            ThePrinter.setSlpLineSpacing(Integer.parseInt(SlpLineSpacing.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setMapCharacterSet(ActionEvent actionEvent) {
        try {
            ThePrinter.setMapCharacterSet(MapCharacterSet.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setJrnLetterQuality(ActionEvent actionEvent) {
        try {
            ThePrinter.setJrnLetterQuality(JrnLetterQuality.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setRecLetterQuality(ActionEvent actionEvent) {
        try {
            ThePrinter.setRecLetterQuality(RecLetterQuality.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setSlpLetterQuality(ActionEvent actionEvent) {
        try {
            ThePrinter.setSlpLetterQuality(SlpLetterQuality.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setCartridgeNotify(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setCartridgeNotify(CartridgeNotifyRow.getValueConverter().getInteger(CartridgeNotify.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setCharacterSet(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setCharacterSet(CharacterSetRow.getValueConverter().getInteger(CharacterSet.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setMapMode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setMapMode(MapModeRow.getValueConverter().getInteger(MapMode.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setRotateSpecial(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setRotateSpecial(RotateSpecialRow.getValueConverter().getInteger(RotateSpecial.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setPageModePrintDirection(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setPageModePrintDirection(PageModePrintDirectionRow.getValueConverter().getInteger(PageModePrintDirection.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setPageModeStation(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setPageModeStation(PageModeStationRow.getValueConverter().getInteger(PageModeStation.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setJrnCurrentCartridge(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setJrnCurrentCartridge(JrnCurrentCartridgeRow.getValueConverter().getInteger(JrnCurrentCartridge.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setJrnLineChars(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setJrnLineChars(Integer.parseInt(JrnLineChars.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setRecCurrentCartridge(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setRecCurrentCartridge(RecCurrentCartridgeRow.getValueConverter().getInteger(RecCurrentCartridge.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setRecLineChars(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setRecLineChars(Integer.parseInt(RecLineChars.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setSlpCurrentCartridge(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setSlpCurrentCartridge(SlpCurrentCartridgeRow.getValueConverter().getInteger(SlpCurrentCartridge.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setSlpLineChars(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setSlpLineChars(Integer.parseInt(SlpLineChars.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    class BeginInsertion extends MethodProcessor {
        private final int Timeout;

        BeginInsertion(int timeout) {
            super("BeginInsertion");
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.beginInsertion(Timeout);
        }
    }

    public void beginInsertion(ActionEvent actionEvent) {
        Integer timeout = new TimeoutValues().getInteger(BI_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new BeginInsertion(timeout).start();
    }

    class EndInsertion extends MethodProcessor {
        EndInsertion() {
            super("EndInsertion");
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.endInsertion();
        }
    }

    public void endInsertion(ActionEvent actionEvent) {
        new EndInsertion().start();
    }

    class BeginRemoval extends MethodProcessor {
        private final int Timeout;

        BeginRemoval(int timeout) {
            super("BeginRemoval");
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.beginRemoval(Timeout);
        }
    }

    public void beginRemoval(ActionEvent actionEvent) {
        Integer timeout = new TimeoutValues().getInteger(BR_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new BeginRemoval(timeout).start();
    }

    class EndRemoval extends MethodProcessor {
        EndRemoval() {
            super("EndRemoval");
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.endRemoval();
        }
    }

    public void endRemoval(ActionEvent actionEvent) {
        new EndRemoval().start();
    }

    class ChangePrintSide extends MethodProcessor {
        private final int Side;

        ChangePrintSide(int side) {
            super("ChangePrintSide");
            Side = side;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.changePrintSide(Side);
        }
    }

    public void changePrintSide(ActionEvent actionEvent) {
        Integer side = new CPS_sideValues().getInteger(CPS_side.getValue());
        if (!invalid(side, "side"))
            new ChangePrintSide(side).start();
    }

    class PageModePrint extends MethodProcessor {
        private final int Ctrl;

        PageModePrint(int control) {
            super("PageModePrint");
            Ctrl = control;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.pageModePrint(Ctrl);
        }
    }

    public void pageModePrint(ActionEvent actionEvent) {
        Integer control = new PMP_controlValues().getInteger(PMP_control.getValue());
        if (!invalid(control, "control"))
            new PageModePrint(control).start();
    }

    class ClearPrintArea extends MethodProcessor {
        ClearPrintArea() {
            super("ClearPrintArea");
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.clearPrintArea();
        }
    }

    public void clearPrintArea(ActionEvent actionEvent) {
        new ClearPrintArea().start();
    }

    class RotatePrint extends MethodProcessor {
        private final int Station;
        private final int Rotation;

        RotatePrint(int station, int rotation) {
            super("RotatePrint");
            Station = station;
            Rotation = rotation;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.rotatePrint(Station, Rotation);
        }
    }

    public void rotatePrint(ActionEvent actionEvent) {
        Integer station = new PageModeStationValues().getInteger(RP_station.getValue());
        Integer rotation = new RP_rotationValues().getInteger(RP_rotation.getValue());
        if (!invalid(station, "station") && !invalid(rotation, "rotation"))
            new RotatePrint(station, rotation).start();
    }

    class TransactionPrint extends MethodProcessor {
        private final int Station;
        private final int Ctrl;

        TransactionPrint(int station, int control) {
            super("TransactionPrint");
            Station = station;
            Ctrl = control;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.transactionPrint(Station, Ctrl);
        }
    }

    public void transactionPrint(ActionEvent actionEvent) {
        Integer station = new P_stationValues().getInteger(TP_station.getValue());
        Integer control = new TP_controlValues().getInteger(TP_control.getValue());
        if (!invalid(station, "station") && !invalid(control, "control"))
            new TransactionPrint(station, control).start();
    }

    class CutPaper extends MethodProcessor {
        private final int Percentage;

        CutPaper(int percentage) {
            super("CutPaper");
            Percentage = percentage;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.cutPaper(Percentage);
        }
    }

    public void cutPaper(ActionEvent actionEvent) {
        Integer percentage = new TP_controlValues().getInteger(CP_percentage.getText());
        if (!invalid(percentage, "percentage"))
            new CutPaper(percentage).start();
    }

    class MarkFeed extends MethodProcessor {
        private final int Type;

        MarkFeed(int type) {
            super("MarkFeed");
            Type = type;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.markFeed(Type);
        }
    }

    public void markFeed(ActionEvent actionEvent) {
        Integer type = new MF_typeValues().getInteger(MF_type.getValue());
        if (!invalid(type, "type"))
            new MarkFeed(type).start();
    }

    class DrawRuledLine extends MethodProcessor {
        private final int Station;
        private final String PositionList;
        private final int LineDirection;
        private final int LineWidth;
        private final int LineStyle;
        private final int LineColor;

        DrawRuledLine(int station, String positionList, int lineDirection, int lineWidth, int lineStyle, int lineColor) {
            super("DrawRuledLine");
            Station = station;
            PositionList = positionList;
            LineDirection = lineDirection;
            LineWidth = lineWidth;
            LineStyle = lineStyle;
            LineColor = lineColor;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.drawRuledLine(Station, PositionList, LineDirection, LineWidth, LineStyle, LineColor);
        }
    }

    public void drawRuledLine(ActionEvent actionEvent) {
        Integer station = new PageModeStationValues().getInteger(DRL_station.getValue());
        Integer lineDirection = new DRL_lineDirectionValues().getInteger(DRL_lineDirection.getValue());
        Integer lineWidth = new IntValues().getInteger(DRL_lineWidth.getText());
        Integer lineStyle = new DRL_lineStyleValues().getInteger(DRL_lineStyle.getValue());
        Integer lineColor = new HexValues().getInteger(DRL_lineColor.getText());
        if (!invalid(station, "station") && !invalid(lineDirection, "lineDirection") &&!invalid(lineWidth, "lineWidth") &&!invalid(lineStyle, "lineStyle") &&!invalid(lineColor, "lineColor"))
            new DrawRuledLine(station, DRL_positionList.getText(), lineDirection, lineWidth, lineStyle, lineColor).start();
    }

    class PrintBarCode extends MethodProcessor {
        private final int Station;
        private final String Data;
        private final int Symbology;
        private final int Height;
        private final int Width;
        private final int Alignment;
        private final int TextPosition;

        PrintBarCode(int station, String data, int symbology, int height, int width, int alignment, int textPosition) {
            super("PrintBarCode");
            Station = station;
            Data = data;
            Symbology = symbology;
            Height = height;
            Width = width;
            Alignment = alignment;
            TextPosition = textPosition;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.printBarCode(Station, Data, Symbology, Height, Width, Alignment, TextPosition);
        }
    }

    public void printBarCode(ActionEvent actionEvent) {
        Integer station = new PageModeStationValues().getInteger(PBC_station.getValue());
        Integer symbology = new PBC_symbologyValues().getInteger(PBC_symbology.getValue());
        Integer height = new IntValues().getInteger(PBC_height.getText());
        Integer width = new IntValues().getInteger(PBC_width.getText());
        Integer alignment = new PBC_alignmentValues().getInteger(PBC_alignment.getValue());
        Integer textPosition = new PBC_textPositionValues().getInteger(PBC_textPosition.getValue());
        if (!invalid(station, "station") && !invalid(symbology, "symbology") && !invalid(height, "height") && !invalid(width, "width") && !invalid(alignment, "alignment") && !invalid(textPosition, "textPosition"))
            new PrintBarCode(station, PBC_data.getText(), symbology, height, width, alignment, textPosition).start();
    }

    class PrintBitmap extends MethodProcessor {
        private final int Station;
        private final String FileName;
        private final int Width;
        private final int Alignment;

        PrintBitmap(int station, String fileName, int width, int alignment) {
            super("PrintBitmap");
            Station = station;
            FileName = fileName;
            Width = width;
            Alignment = alignment;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.printBitmap(Station, FileName, Width, Alignment);
        }
    }

    public void printBitmap(ActionEvent actionEvent) {
        Integer station = new PageModeStationValues().getInteger(PB_station.getValue());
        Integer width = new B_widthValues().getInteger(PB_width.getValue());
        Integer alignment = new B_alignmentValues().getInteger(PB_alignment.getValue());
        if (!invalid(station, "station") && !invalid(width, "width") && !invalid(alignment, "alignment"))
            new PrintBitmap(station, PB_fileName.getText(), width, alignment).start();
    }

    public void browsePBName(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Bitmap File");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showOpenDialog(null);
        if (selected != null)
            PB_fileName.setText(selected.toString());
    }

    class PrintMemoryBitmap extends MethodProcessor {
        private final int Station;
        private final byte[] Data;
        private final int Type;
        private final int Width;
        private final int Alignment;

        PrintMemoryBitmap(int station, byte[] data, int type, int width, int alignment) {
            super("PrintMemoryBitmap");
            Station = station;
            Data = data;
            Width = width;
            Alignment = alignment;
            Type = type;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.printMemoryBitmap(Station, Data, Type, Width, Alignment);
        }
    }

    public void printMemoryBitmap(ActionEvent actionEvent) {
        Integer station = new PageModeStationValues().getInteger(PMB_station.getValue());
        Integer type = new PMB_typeValues().getInteger(PMB_type.getValue());
        Integer width = new B_widthValues().getInteger(PMB_width.getValue());
        Integer alignment = new B_alignmentValues().getInteger(PMB_alignment.getValue());
        if (!invalid(station, "station") && !invalid(type, "type") && !invalid(width, "width") && !invalid(alignment, "alignment")) {
            byte[] data;
            try {
                data = new byte[(int) new File(PMB_data.getText()).length()];
                new FileInputStream(PMB_data.getText()).read(data);
            } catch (Exception e) {
                invalid(null, "data");
                return;
            }
            new PrintMemoryBitmap(station, data, type, width, alignment).start();
        }
    }

    public void browsePMBName(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select File Containing Bitmap Data");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showOpenDialog(null);
        if (selected != null)
            PMB_data.setText(selected.toString());
    }

    class SetBitmap extends MethodProcessor {
        private final int Station;
        private final String FileName;
        private final int Width;
        private final int Alignment;
        private final int BitmapNumber;

        SetBitmap(int bitmapNumber, int station, String fileName, int width, int alignment) {
            super("SetBitmap");
            Station = station;
            FileName = fileName;
            Width = width;
            Alignment = alignment;
            BitmapNumber = bitmapNumber;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.setBitmap(BitmapNumber, Station, FileName, Width, Alignment);
        }
    }

    public void setBitmap(ActionEvent actionEvent) {
        Integer bitmapNumber = new IntValues().getInteger(SB_bitmapNumber.getValue());
        Integer station = new PageModeStationValues().getInteger(SB_station.getValue());
        Integer width = new B_widthValues().getInteger(SB_width.getValue());
        Integer alignment = new B_alignmentValues().getInteger(SB_alignment.getValue());
        if (!invalid(bitmapNumber, "bitmapNumber") && !invalid(station, "station") && !invalid(width, "width") && !invalid(alignment, "alignment"))
            new SetBitmap(bitmapNumber, station, PB_fileName.getText(), width, alignment).start();
    }

    public void browseSBName(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Bitmap File");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showOpenDialog(null);
        if (selected != null)
            SB_fileName.setText(selected.toString());
    }

    class PrintImmediate extends MethodProcessor {
        final int Station;
        final String Data;

        PrintImmediate(int station, String data) {
            super("PrintImmediate");
            Station = station;
            Data = data;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.printImmediate(Station, Data);
        }
    }

    private String getData(TextArea text) {
        char[] data = (MapCharacterSet.isSelected() ? text.getText() : mapData(text.getText())).toCharArray();
        int i = 0, j = 0;
        boolean hexmode = false;
        while (i < data.length) {
            if (data[i] == '\\' && i + 1 < data.length) {
                if (hexmode && data[i + 1] == 'c') {    // \c stops hex data
                    hexmode = false;
                }
                else if (!hexmode && data[i + 1] == 'x') {
                    hexmode = true;
                }
                else {
                    int index = "nre\\".indexOf(data[i + 1]);
                    if (index < 0 || hexmode) {
                        if (hexmode)
                            JOptionPane.showMessageDialog(null, "Invalid control character specification in data: \\" + data[i + 1]);
                        else
                            JOptionPane.showMessageDialog(null, "Invalid control character specification in hex-data: \\" + data[i + 1]);
                        break;
                    }
                    data[j++] = new char[]{10, 13, 27, '\\'}[index];
                }
                i += 2;
            }
            else {
                if (hexmode) {
                    int h = "0123456789ABCDEF".indexOf(new String(new char[]{data[i]}).toUpperCase());
                    int l = i + 1 < data.length ? "0123456789ABCDEF".indexOf(new String(new char[]{data[i + 1]}).toUpperCase()) : -999;
                    if (h < 0 || l < 0) {
                        if (l == -999)
                            JOptionPane.showMessageDialog(null, "Unexpected hex-data end");
                        else
                            JOptionPane.showMessageDialog(null, "Invalid hex-data: " + data[i] + data[i + 1]);
                        break;
                    }
                    data[j++] = (char)((h << 4) + l);
                    i += 2;
                }
                else {
                    data[j++] = data[i++];
                }
            }
        }
        return new String(Arrays.copyOf(data, j));
    }

    private String mapData(String text) {
        Integer encoding = new CharacterSetValues().getInteger(CharacterSet.getValue());
        String charset;
        if (encoding == null) {
            JOptionPane.showMessageDialog(null, "No CharacterSet not specified.");
            return null;
        } else if (encoding < 400) {
            JOptionPane.showMessageDialog(null, "Device specific character set " + encoding + " not supported.");
            return null;
        } else if (encoding > 990 && encoding < 1000) {
            if (encoding == LineDisplayConst.DISP_CS_ANSI)
                charset = "UTF-8";
            else if (encoding == LineDisplayConst.DISP_CS_ASCII)
                charset = "ASCII";
            else if (encoding == LineDisplayConst.DISP_CS_UNICODE)
                return text;
            else {
                JOptionPane.showMessageDialog(null, "Character set " + encoding + " not supported.");
                return null;
            }
        } else
            charset = getCp(encoding);
        try {
            byte[] array = text.getBytes(charset);
            char[] target = new char[array.length];
            for (int k = 0; k < array.length; k++)
                target[k] = (char)(array[k] & 0xff);
            return new String(target);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Character set " + encoding + " not supported:\n" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String getCp(int encoding) {
        return "cp" + encoding;
    }

    public void printImmediate(ActionEvent actionEvent) {
        Integer station = new P_stationValues().getInteger(P_station.getValue());
        String data = getData(P_data);
        if (!invalid(station, "station") && data != null)
            new PrintImmediate(station, data).start();
    }

    class PrintNormal extends PrintImmediate {
        PrintNormal(int station, String data) {
            super(station, data);
            setName("PrintNormal");
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.printNormal(Station, Data);
        }
    }

    public void printNormal(ActionEvent actionEvent) {
        Integer station = new P_stationValues().getInteger(P_station.getValue());
        String data = getData(P_data);
        if (!invalid(station, "station") && data != null)
            new PrintNormal(station, data).start();
    }

    public void validateData(ActionEvent actionEvent) {
        Integer station1 = (Integer) new P_stationValues().getValue(P_station.getValue());
        Integer station2 = (Integer) new PTN_stationValues().getValue(PTN_station.getValue());
        if (station1 == null && station2 == null) {
            invalid(null, "station");
            return;
        }
        if (station1 != null && station2 != null) {
            if (station1 == POSPrinterConst.PTR_S_JOURNAL || (station1 == POSPrinterConst.PTR_S_RECEIPT && station2 != POSPrinterConst.PTR_TWO_RECEIPT_JOURNAL)) {
                JOptionPane.showMessageDialog(null, "Inconsistent station specifications, data / data1 validation not possible");
                return;
            }
            station2 = station2 == POSPrinterConst.PTR_TWO_SLIP_RECEIPT ? POSPrinterConst.PTR_S_RECEIPT : POSPrinterConst.PTR_S_JOURNAL;
        }
        else if (station1 == null) {
            station1 = station2 == POSPrinterConst.PTR_TWO_RECEIPT_JOURNAL ? POSPrinterConst.PTR_S_RECEIPT : POSPrinterConst.PTR_S_SLIP;
            station2 = station2 == POSPrinterConst.PTR_TWO_SLIP_RECEIPT ? POSPrinterConst.PTR_S_RECEIPT : POSPrinterConst.PTR_S_JOURNAL;
        }
        String data = getData(P_data);
        if (data == null)
            return;
        try {
            ThePrinter.validateData(station1, data);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        if (station2 != null) {
            data = getData(PTN_data2);
            if (data == null)
                return;
            try {
                ThePrinter.validateData(station2, data);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
    }

    class SetLogo extends MethodProcessor {
        final int Location;
        final String Data;

        SetLogo(int location, String data) {
            super("SetLogo");
            Location = location;
            Data = data;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.setLogo(Location, Data);
        }
    }

    public void setLogo(ActionEvent actionEvent) {
        Integer location = new SL_locationValues().getInteger(SL_location.getValue());
        String data = getData(P_data);
        if (!invalid(location, "station") && data != null)
            new SetLogo(location, data).start();
    }

    class PrintTwoNormal extends MethodProcessor {
        private final int Station;
        private final String Data1;
        private final String Data2;

        PrintTwoNormal(int station, String data1, String data2) {
            super("PrintTwoNormal");
            Station = station;
            Data1 = data1;
            Data2 = data2;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.printTwoNormal(Station, Data1, Data2);
        }
    }

    public void printTwoNormal(ActionEvent actionEvent) {
        Integer station = new PTN_stationValues().getInteger(PTN_station.getValue());
        String data1 = getData(P_data);
        String data2 = getData(PTN_data2);
        if (data1 != null && data2 != null && !invalid(station, "station"))
            new PrintTwoNormal(station, data1, data2).start();
    }

    private class CapCharacterSetValues extends Values {
        CapCharacterSetValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_CCS_ALPHA, "CCS_ALPHA",
                    POSPrinterConst.PTR_CCS_ASCII, "CCS_ASCII",
                    POSPrinterConst.PTR_CCS_KANA, "CCS_KANA",
                    POSPrinterConst.PTR_CCS_KANJI, "CCS_KANJI",
                    POSPrinterConst.PTR_CCS_UNICODE, "CCS_UNICODE"
            };
        }
    }

    private class CapRuledLineValues extends Values {
        CapRuledLineValues() {
            ValueList = new Object[] {
                    0, "-",
                    POSPrinterConst.PTR_RL_HORIZONTAL, "RL_HORIZONTAL",
                    POSPrinterConst.PTR_RL_VERTICAL, "RL_VERTICAL",
                    POSPrinterConst.PTR_RL_HORIZONTAL|POSPrinterConst.PTR_RL_VERTICAL, "RL_HORIZONTAL|VERTICAL"
            };
        }
    }

    private class CartridgeNotifyValues extends Values {
        CartridgeNotifyValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_CN_DISABLED, "CN_DISABLED",
                    POSPrinterConst.PTR_CN_ENABLED, "CN_ENABLED"
            };
        }
    }

    private class CharacterSetValues extends Values {
        CharacterSetValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_CS_UNICODE, "CS_UNICODE",
                    POSPrinterConst.PTR_CS_ASCII, "CS_ASCII",
                    POSPrinterConst.PTR_CS_ANSI, "CS_ANSI"
            };
        }
    }

    private class ErrorLevelValues extends Values {
        ErrorLevelValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_EL_NONE, "EL_NONE",
                    POSPrinterConst.PTR_EL_RECOVERABLE, "EL_RECOVERABLE",
                    POSPrinterConst.PTR_EL_FATAL, "EL_FATAL"
            };
        }
    }

    private class ErrorStationValues extends Values {
        ErrorStationValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_S_JOURNAL, "S_JOURNAL",
                    POSPrinterConst.PTR_S_RECEIPT, "S_RECEIPT",
                    POSPrinterConst.PTR_S_SLIP, "S_SLIP",
                    POSPrinterConst.PTR_S_JOURNAL_RECEIPT, "S_JOURNAL_RECEIPT",
                    POSPrinterConst.PTR_S_JOURNAL_SLIP, "S_JOURNAL_SLIP",
                    POSPrinterConst.PTR_S_RECEIPT_SLIP, "S_RECEIPT_SLIP",
                    POSPrinterConst.PTR_TWO_RECEIPT_JOURNAL, "TWO_RECEIPT_JOURNAL",
                    POSPrinterConst.PTR_TWO_SLIP_JOURNAL, "TWO_SLIP_JOURNAL",
                    POSPrinterConst.PTR_TWO_SLIP_RECEIPT, "TWO_SLIP_RECEIPT"
            };
        }
    }

    private class CartridgeStateValues extends Values {
        CartridgeStateValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_CART_UNKNOWN, "CART_UNKNOWN",
                    POSPrinterConst.PTR_CART_REMOVED, "CART_REMOVED",
                    POSPrinterConst.PTR_CART_EMPTY, "CART_EMPTY",
                    POSPrinterConst.PTR_CART_CLEANING, "CART_CLEANING",
                    POSPrinterConst.PTR_CART_NEAREND, "CART_NEAREND",
                    POSPrinterConst.PTR_CART_OK, "CART_OK"
            };
        }
    }

    private class CurrentCartridgeValues extends Values {
        CurrentCartridgeValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_COLOR_PRIMARY, "COLOR_PRIMARY",
                    POSPrinterConst.PTR_COLOR_CUSTOM1, "COLOR_CUSTOM1",
                    POSPrinterConst.PTR_COLOR_CUSTOM2, "COLOR_CUSTOM2",
                    POSPrinterConst.PTR_COLOR_CUSTOM3, "COLOR_CUSTOM3",
                    POSPrinterConst.PTR_COLOR_CUSTOM4, "COLOR_CUSTOM4",
                    POSPrinterConst.PTR_COLOR_CUSTOM5, "COLOR_CUSTOM5",
                    POSPrinterConst.PTR_COLOR_CUSTOM6, "COLOR_CUSTOM6",
                    POSPrinterConst.PTR_COLOR_CYAN, "COLOR_CYAN",
                    POSPrinterConst.PTR_COLOR_MAGENTA, "COLOR_MAGENTA",
                    POSPrinterConst.PTR_COLOR_YELLOW, "COLOR_YELLOW"
            };
        }
    }

    private class MapModeValues extends Values {
        MapModeValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_MM_DOTS, "MM_DOTS",
                    POSPrinterConst.PTR_MM_TWIPS, "MM_TWIPS",
                    POSPrinterConst.PTR_MM_ENGLISH, "MM_ENGLISH",
                    POSPrinterConst.PTR_MM_METRIC, "MM_METRIC"
            };
        }
    }

    private class PageModePrintDirectionValues extends Values {
        PageModePrintDirectionValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_PD_LEFT_TO_RIGHT, "PD_LEFT_TO_RIGHT",
                    POSPrinterConst.PTR_PD_BOTTOM_TO_TOP, "PD_BOTTOM_TO_TOP",
                    POSPrinterConst.PTR_PD_RIGHT_TO_LEFT, "PD_RIGHT_TO_LEFT",
                    POSPrinterConst.PTR_PD_TOP_TO_BOTTOM, "PD_TOP_TO_BOTTOM"
            };
        }
    }

    private class PageModeStationValues extends Values {
        PageModeStationValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_S_RECEIPT, "S_RECEIPT",
                    POSPrinterConst.PTR_S_SLIP, "S_SLIP"
            };
        }
    }

    private class P_stationValues extends Values {
        P_stationValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_S_RECEIPT, "S_RECEIPT",
                    POSPrinterConst.PTR_S_JOURNAL, "S_JOURNAL",
                    POSPrinterConst.PTR_S_SLIP, "S_SLIP"
            };
        }
    }

    private class RotateSpecialValues extends Values {
        RotateSpecialValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_RP_NORMAL, "RP_NORMAL",
                    POSPrinterConst.PTR_RP_RIGHT90, "RP_RIGHT90",
                    POSPrinterConst.PTR_RP_LEFT90, "RP_LEFT90",
                    POSPrinterConst.PTR_RP_ROTATE180, "RP_ROTATE180"
            };
        }
    }

    private class SlpPrintSideValues extends Values {
        SlpPrintSideValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_PS_UNKNOWN, "PS_UNKNOWN",
                    POSPrinterConst.PTR_PS_SIDE1, "PS_SIDE1",
                    POSPrinterConst.PTR_PS_SIDE2, "PS_SIDE2"
            };
        }
    }

    private class CPS_sideValues extends SlpPrintSideValues {
        CPS_sideValues() {
            super();
            ValueList[0] = POSPrinterConst.PTR_PS_OPPOSITE;
            ValueList[1] = "PS_OPPOSITE";
        }
    }

    private class SL_locationValues extends SlpPrintSideValues {
        SL_locationValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_L_TOP, "L_TOP",
                    POSPrinterConst.PTR_L_BOTTOM, "L_BOTTOM"
            };
        }
    }

    private class PTN_stationValues extends SlpPrintSideValues {
        PTN_stationValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_TWO_RECEIPT_JOURNAL, "TWO_RECEIPT_JOURNAL",
                    POSPrinterConst.PTR_TWO_SLIP_JOURNAL, "TWO_SLIP_JOURNAL",
                    POSPrinterConst.PTR_TWO_SLIP_RECEIPT, "TWO_SLIP_RECEIPT"
            };
        }
    }

    private class MF_typeValues extends SlpPrintSideValues {
        MF_typeValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_MF_TO_TAKEUP, "MF_TO_TAKEUP",
                    POSPrinterConst.PTR_MF_TO_CUTTER, "MF_TO_CUTTER",
                    POSPrinterConst.PTR_MF_TO_CURRENT_TOF, "MF_TO_CURRENT_TOF",
                    POSPrinterConst.PTR_MF_TO_NEXT_TOF, "MF_TO_NEXT_TOF"
            };
        }
    }

    private class DRL_lineDirectionValues extends Values {
        DRL_lineDirectionValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_RL_HORIZONTAL, "RL_HORIZONTAL",
                    POSPrinterConst.PTR_RL_VERTICAL, "RL_VERTICAL"
            };
        }
    }

    private class DRL_lineStyleValues extends Values {
        DRL_lineStyleValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_LS_SINGLE_SOLID_LINE, "LS_SINGLE_SOLID_LINE",
                    POSPrinterConst.PTR_LS_DOUBLE_SOLID_LINE, "LS_DOUBLE_SOLID_LINE",
                    POSPrinterConst.PTR_LS_BROKEN_LINE, "LS_BROKEN_LINE",
                    POSPrinterConst.PTR_LS_CHAIN_LINE, "LS_CHAIN_LINE"
            };
        }
    }

    private class PBC_symbologyValues extends Values {
        PBC_symbologyValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_BCS_AZTEC, "BCS_AZTEC",
                    POSPrinterConst.PTR_BCS_Codabar, "BCS_Codabar",
                    POSPrinterConst.PTR_BCS_Code39, "BCS_Code",
                    POSPrinterConst.PTR_BCS_Code93, "BCS_Code93",
                    POSPrinterConst.PTR_BCS_Code128, "BCS_Code128",
                    POSPrinterConst.PTR_BCS_Code128_Parsed, "BCS_Code128_Parsed",
                    POSPrinterConst.PTR_BCS_DATAMATRIX, "BCS_Code",
                    POSPrinterConst.PTR_BCS_EAN8, "BCS_EAN8",
                    POSPrinterConst.PTR_BCS_EAN8_S, "BCS_EAN8_S",
                    POSPrinterConst.PTR_BCS_EAN13, "BCS_EAN13",
                    POSPrinterConst.PTR_BCS_EAN13_S, "BCS_EAN13_S",
                    POSPrinterConst.PTR_BCS_EAN128, "BCS_EAN128",
                    POSPrinterConst.PTR_BCS_GS1DATABAR, "BCS_GS1DATABAR",
                    POSPrinterConst.PTR_BCS_GS1DATABAR_E, "BCS_GS1DATABAR_E",
                    POSPrinterConst.PTR_BCS_GS1DATABAR_E_S, "BCS_GS1DATABAR_E_S",
                    POSPrinterConst.PTR_BCS_GS1DATABAR_S, "BCS_GS1DATABAR_S",
                    POSPrinterConst.PTR_BCS_ITF, "BCS_ITF",
                    POSPrinterConst.PTR_BCS_JAN8, "BCS_JAN8",
                    POSPrinterConst.PTR_BCS_JAN13, "BCS_JAN13",
                    POSPrinterConst.PTR_BCS_MAXICODE, "BCS_MAXICODE",
                    POSPrinterConst.PTR_BCS_OCRA, "BCS_OCRA",
                    POSPrinterConst.PTR_BCS_OCRB, "BCS_OCRB",
                    POSPrinterConst.PTR_BCS_OTHER, "BCS_OTHER",
                    POSPrinterConst.PTR_BCS_PDF417, "BCS_PDF417",
                    POSPrinterConst.PTR_BCS_QRCODE, "BCS_QRCODE",
                    POSPrinterConst.PTR_BCS_RSS14, "BCS_RSS14",
                    POSPrinterConst.PTR_BCS_RSS_EXPANDED, "BCS_RSS_EXPANDED",
                    POSPrinterConst.PTR_BCS_TF, "BCS_TF",
                    POSPrinterConst.PTR_BCS_UPCA, "BCS_UPCA",
                    POSPrinterConst.PTR_BCS_UPCA_S, "BCS_UPCA_S",
                    POSPrinterConst.PTR_BCS_UPCD1, "BCS_UPCD1",
                    POSPrinterConst.PTR_BCS_UPCD2, "BCS_UPCD2",
                    POSPrinterConst.PTR_BCS_UPCD3, "BCS_UPCD3",
                    POSPrinterConst.PTR_BCS_UPCD4, "BCS_UPCD4",
                    POSPrinterConst.PTR_BCS_UPCD5, "BCS_UPCD5",
                    POSPrinterConst.PTR_BCS_UPCE, "BCS_UPCE",
                    POSPrinterConst.PTR_BCS_UPCE_S, "BCS_UPCE_S",
                    POSPrinterConst.PTR_BCS_UPDF417, "BCS_UPDF417",
                    POSPrinterConst.PTR_BCS_UQRCODE, "BCS_UQRCODE"
            };
        }
    }

    private class PBC_alignmentValues extends Values {
        PBC_alignmentValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_BC_LEFT, "BC_LEFT",
                    POSPrinterConst.PTR_BC_CENTER, "BC_CENTER",
                    POSPrinterConst.PTR_BC_RIGHT, "BC_RIGHT"
            };
        }
    }

    private class PBC_textPositionValues extends Values {
        PBC_textPositionValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_BC_TEXT_NONE, "BC_TEXT_NONE",
                    POSPrinterConst.PTR_BC_TEXT_ABOVE, "BC_TEXT_ABOVE",
                    POSPrinterConst.PTR_BC_TEXT_BELOW, "BC_TEXT_BELOW"
            };
        }
    }

    private class B_widthValues extends Values {
        B_widthValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_BM_ASIS, "BM_ASIS"
            };
        }
    }

    private class B_alignmentValues extends Values {
        B_alignmentValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_BM_LEFT, "BM_LEFT",
                    POSPrinterConst.PTR_BM_CENTER, "BM_CENTER",
                    POSPrinterConst.PTR_BM_RIGHT, "BM_RIGHT"
            };
        }
    }

    private class PMB_typeValues extends Values {
        PMB_typeValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_BMT_BMP, "BMT_BMP",
                    POSPrinterConst.PTR_BMT_JPEG, "BMT_JPEG",
                    POSPrinterConst.PTR_BMT_GIF, "BMT_GIF"
            };
        }
    }

    private class PMP_controlValues extends Values {
        PMP_controlValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_PM_PAGE_MODE, "PM_PAGE_MODE",
                    POSPrinterConst.PTR_PM_PRINT_SAVE, "PM_PRINT_SAVE",
                    POSPrinterConst.PTR_PM_NORMAL, "PM_NORMAL",
                    POSPrinterConst.PTR_PM_CANCEL, "PM_CANCEL"
            };
        }
    }

    private class RP_rotationValues extends Values {
        RP_rotationValues() {
            ValueList = new Object[] {
                    POSPrinterConst.PTR_RP_NORMAL, "RP_NORMAL",
                    POSPrinterConst.PTR_RP_RIGHT90, "RP_RIGHT90",
                    POSPrinterConst.PTR_RP_LEFT90, "RP_LEFT90",
                    POSPrinterConst.PTR_RP_ROTATE180, "RP_ROTATE180",
                    POSPrinterConst.PTR_RP_BARCODE, "RP_BARCODE",
                    POSPrinterConst.PTR_RP_BITMAP, "RP_BITMAP"
            };
        }
    }

    private class TP_controlValues extends Values {
        TP_controlValues() {
            ValueList = new Object[] {
                   POSPrinterConst.PTR_TP_TRANSACTION, "TP_TRANSACTION",
                    POSPrinterConst.PTR_TP_NORMAL, "TP_NORMAL"
            };
        }
    }

    private class PrtStatusUpdateValues extends StatusUpdateValues {
        PrtStatusUpdateValues() {
            super();
            Object[] prtvalues = new Object[]{
                    POSPrinterConst.PTR_SUE_IDLE, "SUE_IDLE",
                    POSPrinterConst.PTR_SUE_COVER_OPEN, "SUE_COVER_OPEN",
                    POSPrinterConst.PTR_SUE_COVER_OK, "SUE_COVER_OK",
                    POSPrinterConst.PTR_SUE_JRN_EMPTY, "SUE_JRN_EMPTY",
                    POSPrinterConst.PTR_SUE_JRN_NEAREMPTY, "SUE_JRN_NEAREMPTY",
                    POSPrinterConst.PTR_SUE_JRN_PAPEROK, "SUE_JRN_PAPEROK",
                    POSPrinterConst.PTR_SUE_JRN_CARTRIDGE_EMPTY, "SUE_JRN_CARTRIDGE_EMPTY",
                    POSPrinterConst.PTR_SUE_JRN_CARTRIDGE_NEAREMPTY, "SUE_JRN_CARTRIDGE_NEAREMPTY",
                    POSPrinterConst.PTR_SUE_JRN_CARTDRIGE_OK, "SUE_JRN_CARTDRIGE_OK",
                    POSPrinterConst.PTR_SUE_JRN_HEAD_CLEANING, "SUE_JRN_HEAD_CLEANING",
                    POSPrinterConst.PTR_SUE_JRN_COVER_OPEN, "SUE_JRN_COVER_OPEN",
                    POSPrinterConst.PTR_SUE_JRN_COVER_OK, "SUE_JRN_COVER_OK",
                    POSPrinterConst.PTR_SUE_REC_EMPTY, "SUE_REC_EMPTY",
                    POSPrinterConst.PTR_SUE_REC_NEAREMPTY, "SUE_REC_NEAREMPTY",
                    POSPrinterConst.PTR_SUE_REC_PAPEROK, "SUE_REC_PAPEROK",
                    POSPrinterConst.PTR_SUE_REC_CARTRIDGE_EMPTY, "SUE_REC_CARTRIDGE_EMPTY",
                    POSPrinterConst.PTR_SUE_REC_CARTRIDGE_NEAREMPTY, "SUE_REC_CARTRIDGE_NEAREMPTY",
                    POSPrinterConst.PTR_SUE_REC_CARTDRIGE_OK, "SUE_REC_CARTDRIGE_OK",
                    POSPrinterConst.PTR_SUE_REC_HEAD_CLEANING, "SUE_REC_HEAD_CLEANING",
                    POSPrinterConst.PTR_SUE_REC_COVER_OPEN, "SUE_REC_COVER_OPEN",
                    POSPrinterConst.PTR_SUE_REC_COVER_OK, "SUE_REC_COVER_OK",
                    POSPrinterConst.PTR_SUE_SLP_EMPTY, "SUE_SLP_EMPTY",
                    POSPrinterConst.PTR_SUE_SLP_NEAREMPTY, "SUE_SLP_NEAREMPTY",
                    POSPrinterConst.PTR_SUE_SLP_PAPEROK, "SUE_SLP_PAPEROK",
                    POSPrinterConst.PTR_SUE_SLP_CARTRIDGE_EMPTY, "SUE_SLP_CARTRIDGE_EMPTY",
                    POSPrinterConst.PTR_SUE_SLP_CARTRIDGE_NEAREMPTY, "SUE_SLP_CARTRIDGE_NEAREMPTY",
                    POSPrinterConst.PTR_SUE_SLP_CARTDRIGE_OK, "SUE_SLP_CARTDRIGE_OK",
                    POSPrinterConst.PTR_SUE_SLP_HEAD_CLEANING, "SUE_SLP_HEAD_CLEANING",
                    POSPrinterConst.PTR_SUE_SLP_COVER_OPEN, "SUE_SLP_COVER_OPEN",
                    POSPrinterConst.PTR_SUE_SLP_COVER_OK, "SUE_SLP_COVER_OK"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + prtvalues.length);
            System.arraycopy(prtvalues, 0, ValueList, ValueList.length - prtvalues.length, prtvalues.length);
        }
    }

    private class ExtendedErrorCodeValues extends Values {
        ExtendedErrorCodeValues() {
            ValueList = new Object[] {
                    POSPrinterConst.JPOS_EPTR_COVER_OPEN, "EPTR_COVER_OPEN",
                    POSPrinterConst.JPOS_EPTR_JRN_EMPTY, "EPTR_JRN_EMPTY",
                    POSPrinterConst.JPOS_EPTR_REC_EMPTY, "EPTR_REC_EMPTY",
                    POSPrinterConst.JPOS_EPTR_SLP_EMPTY, "EPTR_SLP_EMPTY",
                    POSPrinterConst.JPOS_EPTR_SLP_FORM, "EPTR_SLP_FORM",
                    POSPrinterConst.JPOS_EPTR_TOOBIG, "EPTR_TOOBIG",
                    POSPrinterConst.JPOS_EPTR_BADFORMAT, "EPTR_BADFORMAT",
                    POSPrinterConst.JPOS_EPTR_JRN_CARTRIDGE_REMOVED, "EPTR_JRN_CARTRIDGE_REMOVED",
                    POSPrinterConst.JPOS_EPTR_JRN_CARTRIDGE_EMPTY, "EPTR_JRN_CARTRIDGE_EMPTY",
                    POSPrinterConst.JPOS_EPTR_JRN_HEAD_CLEANING, "EPTR_JRN_HEAD_CLEANING",
                    POSPrinterConst.JPOS_EPTR_REC_CARTRIDGE_REMOVED, "EPTR_REC_CARTRIDGE_REMOVED",
                    POSPrinterConst.JPOS_EPTR_REC_CARTRIDGE_EMPTY, "EPTR_REC_CARTRIDGE_EMPTY",
                    POSPrinterConst.JPOS_EPTR_REC_HEAD_CLEANING, "EPTR_REC_HEAD_CLEANING",
                    POSPrinterConst.JPOS_EPTR_SLP_CARTRIDGE_REMOVED, "EPTR_SLP_CARTRIDGE_REMOVED",
                    POSPrinterConst.JPOS_EPTR_SLP_CARTRIDGE_EMPTY, "EPTR_SLP_CARTRIDGE_EMPTY",
                    POSPrinterConst.JPOS_EPTR_SLP_HEAD_CLEANING, "EPTR_SLP_HEAD_CLEANING"
            };
        }
    }
}
