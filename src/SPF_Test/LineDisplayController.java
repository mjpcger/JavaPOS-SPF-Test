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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import jpos.JposConst;
import jpos.JposException;
import jpos.LineDisplay;
import jpos.LineDisplayConst;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for LineDisplay properties and methods.
 */
public class LineDisplayController extends CommonController {
    public CheckBox CursorUpdate;
    public CheckBox MapCharacterSet;
    public TextField BlinkRate;
    public ComboBox<String> ScreenMode;
    public ComboBox<String> CharacterSet;
    public ComboBox<String> CurrentWindow;
    public TextField CursorColumn;
    public TextField CursorRow;
    public ComboBox<String> CursorType;
    public TextField DeviceBrightness;
    public TextField InterCharacterWait;
    public ComboBox<String> MarqueeFormat;
    public TextField MarqueeRepeatWait;
    public ComboBox<String> MarqueeType;
    public TextField MarqueeUnitWait;
    public TextField CW_viewportRow;
    public TextField CW_viewportColumn;
    public TextField CW_viewportHeight;
    public TextField CW_viewportWidth;
    public TextField CW_windowHeight;
    public TextField CW_windowWidth;
    public TextField RW_window;
    public TextField SD_descriptor;
    public ComboBox<String> SD_attribute;
    public TextField DG_glyphCode;
    public TextField DG_glyph;
    public TextField SB_bitmapNumber;
    public TextField SB_fileName;
    public ComboBox<String> SB_width;
    public ComboBox<String> SB_alignmentX;
    public ComboBox<String> SB_alignmentY;
    public TextField DB_fileName;
    public ComboBox<String> DB_width;
    public ComboBox<String> DB_alignmentX;
    public ComboBox<String> DB_alignmentY;
    public ComboBox<String> DTA_row;
    public ComboBox<String> DTA_column;
    public TextArea DT_data;
    public ComboBox<String> DT_attribute;
    public ComboBox<String> ST_direction;
    public TextField ST_units;
    public TextField RCAC_cursorData;

    private LineDisplay TheDisplay;
    private PropertyTableRow ClaimedRow;
    private PropertyTableRow BlinkRateRow;
    private PropertyTableRow CharacterSetRow;
    private PropertyTableRow CharacterSetListRow;
    private PropertyTableRow CurrentWindowRow;
    private PropertyTableRow CursorColumnRow;
    private PropertyTableRow CursorRowRow;
    private PropertyTableRow CursorTypeRow;
    private PropertyTableRow CursorUpdateRow;
    private PropertyTableRow DeviceBrightnessRow;
    private PropertyTableRow InterCharacterWaitRow;
    private PropertyTableRow MapCharacterSetRow;
    private PropertyTableRow MarqueeFormatRow;
    private PropertyTableRow MarqueeRepeatWaitRow;
    private PropertyTableRow MarqueeTypeRow;
    private PropertyTableRow MarqueeUnitWaitRow;
    private PropertyTableRow ScreenModeRow;
    private PropertyTableRow ScreenModeListRow;
    private PropertyTableRow CustomGlyphListRow;
    private PropertyTableRow DeviceWindowsRow;
    private PropertyTableRow ColumnsRow;
    private PropertyTableRow RowsRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Hexadecimal;
        super.initialize(url, resourceBundle);
        TheDisplay = (LineDisplay) Control;
        TheDisplay.addDirectIOListener(this);
        TheDisplay.addStatusUpdateListener(this);
        Properties.getItems().add(ClaimedRow = new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(BlinkRateRow = new PropertyTableRow("BlinkRate", ""));
        Properties.getItems().add(CharacterSetRow = new PropertyTableRow("CharacterSet", "", new CharacterSetValues()));
        Properties.getItems().add(CharacterSetListRow = new PropertyTableRow("CharacterSetList", ""));
        Properties.getItems().add(CurrentWindowRow = new PropertyTableRow("CurrentWindow", ""));
        Properties.getItems().add(CursorColumnRow = new PropertyTableRow("CursorColumn", ""));
        Properties.getItems().add(CursorRowRow = new PropertyTableRow("CursorRow", ""));
        Properties.getItems().add(CursorTypeRow = new PropertyTableRow("CursorType", "", new CursorTypeValues()));
        Properties.getItems().add(CursorUpdateRow = new PropertyTableRow("CursorUpdate", ""));
        Properties.getItems().add(DeviceBrightnessRow = new PropertyTableRow("DeviceBrightness", ""));
        Properties.getItems().add(InterCharacterWaitRow = new PropertyTableRow("InterCharacterWait", ""));
        Properties.getItems().add(MapCharacterSetRow = new PropertyTableRow("MapCharacterSet", ""));
        Properties.getItems().add(MarqueeFormatRow = new PropertyTableRow("MarqueeFormat", "", new MarqueeFormatValues()));
        Properties.getItems().add(MarqueeRepeatWaitRow = new PropertyTableRow("MarqueeRepeatWait", ""));
        Properties.getItems().add(MarqueeTypeRow = new PropertyTableRow("MarqueeType", "", new MarqueeTypeValues()));
        Properties.getItems().add(MarqueeUnitWaitRow = new PropertyTableRow("MarqueeUnitWait", ""));
        Properties.getItems().add(ScreenModeRow = new PropertyTableRow("ScreenMode", "", new IntValues()));
        Properties.getItems().add(ScreenModeListRow = new PropertyTableRow("ScreenModeList", ""));
        Properties.getItems().add(new PropertyTableRow("CapBitmap", ""));
        Properties.getItems().add(new PropertyTableRow("CapBlink", "", new CapBlinkValues()));
        Properties.getItems().add(new PropertyTableRow("CapBlinkRate", ""));
        Properties.getItems().add(new PropertyTableRow("CapBrightness", ""));
        Properties.getItems().add(new PropertyTableRow("CapCharacterSet", "", new CapCharacterSetValues()));
        Properties.getItems().add(new PropertyTableRow("CapCursorType", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapCustomGlyph", ""));
        Properties.getItems().add(new PropertyTableRow("CapDescriptors", ""));
        Properties.getItems().add(new PropertyTableRow("CapHMarquee", ""));
        Properties.getItems().add(new PropertyTableRow("CapVMarquee", ""));
        Properties.getItems().add(new PropertyTableRow("CapICharWait", ""));
        Properties.getItems().add(new PropertyTableRow("CapMapCharacterSet", ""));
        Properties.getItems().add(new PropertyTableRow("CapReadBack", "", new CapReadBackValues()));
        Properties.getItems().add(new PropertyTableRow("CapReverse", "", new CapReverseValues()));
        Properties.getItems().add(new PropertyTableRow("CapScreenMode", ""));
        Properties.getItems().add(ColumnsRow = new PropertyTableRow("Columns", ""));
        Properties.getItems().add(RowsRow = new PropertyTableRow("Rows", ""));
        Properties.getItems().add(CustomGlyphListRow = new PropertyTableRow("CustomGlyphList", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceColumns", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceRows", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceDescriptors", ""));
        Properties.getItems().add(DeviceWindowsRow = new PropertyTableRow("DeviceWindows", ""));
        Properties.getItems().add(new PropertyTableRow("GlyphHeight", ""));
        Properties.getItems().add(new PropertyTableRow("GlyphWidth", ""));
        Properties.getItems().add(new PropertyTableRow("MaximumX", ""));
        Properties.getItems().add(new PropertyTableRow("MaximumY", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        setPropertyOnFocusLost(BlinkRate, "BlinkRate");
        setPropertyOnFocusLost(CursorColumn, "CursorColumn");
        setPropertyOnFocusLost(CursorRow, "CursorRow");
        setPropertyOnFocusLost(DeviceBrightness, "DeviceBrightness");
        setPropertyOnFocusLost(InterCharacterWait, "InterCharacterWait");
        setPropertyOnFocusLost(MarqueeRepeatWait, "MarqueeRepeatWait");
        setPropertyOnFocusLost(MarqueeUnitWait, "MarqueeUnitWait");
        setPropertyOnFocusLost(DG_glyph, "DG_glyph");
        Values val = new CursorTypeValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            CursorType.getItems().add(val.ValueList[i].toString());
        val = new MarqueeTypeValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            MarqueeType.getItems().add(val.ValueList[i].toString());
        val = new MarqueeFormatValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            MarqueeFormat.getItems().add(val.ValueList[i].toString());
        val = new ST_directionValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            ST_direction.getItems().add(val.ValueList[i].toString());
        val = new DT_attributeValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            DT_attribute.getItems().add(val.ValueList[i].toString());
        val = new SD_attributeValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            SD_attribute.getItems().add(val.ValueList[i].toString());
        val = new B_widthValues();
        for (int i = 1; i < val.ValueList.length; i += 2) {
            SB_width.getItems().add(val.ValueList[i].toString());
            DB_width.getItems().add(val.ValueList[i].toString());
        }
        val = new B_alignmentXValues();
        for (int i = 1; i < val.ValueList.length; i += 2) {
            SB_alignmentX.getItems().add(val.ValueList[i].toString());
            DB_alignmentX.getItems().add(val.ValueList[i].toString());
        }
        val = new B_alignmentYValues();
        for (int i = 1; i < val.ValueList.length; i += 2) {
            SB_alignmentY.getItems().add(val.ValueList[i].toString());
            DB_alignmentY.getItems().add(val.ValueList[i].toString());
        }
        updateGui();
    }

    @Override
    void updateGui() {
        String modes = "";
        try {
            modes = TheDisplay.getScreenModeList();
            if (modes.length() > 0 && ScreenModeRow.getValueConverter() != null) {
                String[] modi = modes.split(",");
                Object[] converterarray = new Object[modi.length * 2 + 2];
                converterarray[0] = 0;
                converterarray[1] = "Default";
                for (int i = 1; i <= modi.length; i++) {
                    converterarray[i + i] = i;
                    converterarray[i + i + 1] = modi[i - 1];
                }
                ScreenModeRow.getValueConverter().ValueList = converterarray;
            }
        } catch (JposException e) {
            if (TheDisplay.getState() != JposConst.JPOS_S_CLOSED)
                e.printStackTrace();
        }
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            Values val = new IntValues();
            Integer ival = val.getInteger(DeviceWindowsRow.getValue());
            if (ival != null) {
                if (CurrentWindow.getItems().size() != ival + 1) {
                    CurrentWindow.getItems().clear();
                    for (int i = 0; i <= ival; i++) {
                        CurrentWindow.getItems().add(String.valueOf(i));
                    }
                }
                if (CurrentWindowRow.getValue().length() > 0)
                    CurrentWindow.setValue(CurrentWindowRow.getValue());
            }
            ival = val.getInteger(RowsRow.getValue());
            if (ival != null && DTA_row.getItems().size() != ival + 1) {
                String current = DTA_row.getValue();
                DTA_row.getItems().clear();
                for (int i = 0; i < ival; i++) {
                    DTA_row.getItems().add(String.valueOf(i));
                    if (String.valueOf(i).equals(current))
                        DTA_row.setValue(current);
                }
            }
            ival = val.getInteger(ColumnsRow.getValue());
            if (ival != null && DTA_column.getItems().size() != ival + 1) {
                String current = DTA_column.getValue();
                DTA_column.getItems().clear();
                for (int i = 0; i <= ival; i++) {
                    DTA_column.getItems().add(String.valueOf(i));
                    if (String.valueOf(i).equals(current))
                        DTA_column.setValue(current);
                }
            }
            if (CharacterSetListRow.getValue() != null && CharacterSetListRow.getValue().length() > 0) {
                String[] charsets = CharacterSetListRow.getValue().split(",");
                if (charsets.length != CharacterSet.getItems().size()) {
                    CharacterSet.getItems().clear();
                    for (String set : charsets) {
                        if ((ival = val.getInteger(set)) != null)
                            set = new CharacterSetValues().getSymbol(ival);
                        CharacterSet.getItems().add(set);
                    }
                }
                CharacterSet.setValue(CharacterSetRow.getValue());
            }
            if (ScreenModeRow.getValueConverter() != null && ScreenModeRow.getValueConverter().ValueList.length > 0) {
                if (ScreenModeRow.getValueConverter().ValueList.length != ScreenMode.getItems().size() * 2) {
                    ScreenMode.getItems().clear();
                    for (int i = 0; i < ScreenModeRow.getValueConverter().ValueList.length; i += 2) {
                        ScreenMode.getItems().add(ScreenModeRow.getValueConverter().ValueList[i + 1].toString());
                    }
                }
                ScreenMode.setValue(ScreenModeRow.getValue());
            }
            BlinkRate.setText(BlinkRateRow.getValue());
            DeviceBrightness.setText(DeviceBrightnessRow.getValue());
            InterCharacterWait.setText(InterCharacterWaitRow.getValue());
            CursorRow.setText(CursorRowRow.getValue());
            CursorColumn.setText(CursorColumnRow.getValue());
            MarqueeUnitWait.setText(MarqueeUnitWaitRow.getValue());
            MarqueeRepeatWait.setText(MarqueeRepeatWaitRow.getValue());
            CursorType.setValue(CursorTypeRow.getValue());
            MarqueeType.setValue(MarqueeTypeRow.getValue());
            MarqueeFormat.setValue(MarqueeFormatRow.getValue());
            CursorUpdate.setSelected("true".equals(CursorUpdateRow.getValue()));
            MapCharacterSet.setSelected("true".equals(MapCharacterSetRow.getValue()));
            InUpdateGui = false;
        }
    }

    public void setCursorUpdate(ActionEvent actionEvent) {
        try {
            TheDisplay.setCursorUpdate(CursorUpdate.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setMapCharacterSet(ActionEvent actionEvent) {
        try {
            TheDisplay.setMapCharacterSet(MapCharacterSet.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setScreenMode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheDisplay.setScreenMode(ScreenModeRow.getValueConverter().getInteger(ScreenMode.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setBlinkRate(ActionEvent event) {
        try {
            TheDisplay.setBlinkRate(new IntValues().getInteger(BlinkRate.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setDeviceBrightness(ActionEvent event) {
        try {
            TheDisplay.setDeviceBrightness(new IntValues().getInteger(DeviceBrightness.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setInterCharacterWait(ActionEvent event) {
        try {
            TheDisplay.setInterCharacterWait(new IntValues().getInteger(InterCharacterWait.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setCharacterSet(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheDisplay.setCharacterSet(new CharacterSetValues().getInteger(CharacterSet.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setCurrentWindow(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheDisplay.setCurrentWindow(new IntValues().getInteger(CurrentWindow.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setCursorColumn(ActionEvent actionEvent) {
        try {
            TheDisplay.setCursorColumn(new IntValues().getInteger(CursorColumn.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setCursorRow(ActionEvent actionEvent) {
        try {
            TheDisplay.setCursorRow(new IntValues().getInteger(CursorRow.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setCursorType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheDisplay.setCursorType(new CursorTypeValues().getInteger(CursorType.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setMarqueeFormat(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheDisplay.setMarqueeFormat(new MarqueeFormatValues().getInteger(MarqueeFormat.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setMarqueeType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheDisplay.setMarqueeType(new MarqueeTypeValues().getInteger(MarqueeType.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setMarqueeUnitWait(ActionEvent actionEvent) {
        try {
            TheDisplay.setMarqueeUnitWait(new IntValues().getInteger(MarqueeUnitWait.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setMarqueeRepeatWait(ActionEvent actionEvent) {
        try {
            TheDisplay.setMarqueeRepeatWait(new IntValues().getInteger(MarqueeRepeatWait.getText()));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private boolean ValidGlyph = false;

    public void setDG_glyph(ActionEvent actionEvent) {
        ValidGlyph = false;
        if (DG_glyph.getText().length() % 2 == 0) {
            for (int i = 0; i < DG_glyph.getText().length(); i++) {
                char c = DG_glyph.getText().toUpperCase().charAt(i);
                if ("0123456789ABCDEF".indexOf(c) < 0) {
                    int doit = myOptionDialog("Character " + (i + 1) + " not hexadecimal.\nClear?");
                    if (doit == JOptionPane.YES_OPTION) {
                        DG_glyph.setText("");
                    }
                    break;
                }
            }
            ValidGlyph = true;
        } else {
            int doit = myOptionDialog("Odd number of characters.\nClear?");
            if (doit == JOptionPane.YES_OPTION) {
                DG_glyph.setText("");
            }
        }
    }

    public void clearText(ActionEvent actionEvent) {
        try {
            TheDisplay.clearText();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    class DisplayText extends MethodProcessor {
        final String Data;
        final int Attribute;

        DisplayText(String data, int attribute) {
            super(null);
            Data = data;
            Attribute = attribute;
        }

        @Override
        void runIt() throws JposException {
            TheDisplay.displayText(Data, Attribute);
        }
    }

    public void displayText(ActionEvent actionEvent) {
        String data = getData();
        Integer attribute = new DT_attributeValues().getInteger(DT_attribute.getValue());
        if (data != null && !invalid(attribute, "attribute"))
            new DisplayText(data, attribute).start();
    }

    class DisplayTextAt extends DisplayText {
        private final int Row;
        private final int Column;

        DisplayTextAt(int row, int column, String data, int attribute) {
            super(data, attribute);
            Row = row;
            Column = column;
        }

        @Override
        void runIt() throws JposException {
            TheDisplay.displayTextAt(Row, Column, Data, Attribute);
        }
    }

    public void displayTextAt(ActionEvent actionEvent) {
        Integer row = new IntValues().getInteger(DTA_row.getValue());
        Integer column = new IntValues().getInteger(DTA_column.getValue());
        String data = getData();
        Integer attribute = new DT_attributeValues().getInteger(DT_attribute.getValue());
        if (!invalid(row, "row") && !invalid(column, "column") && data != null && !invalid(attribute, "attribute"))
            new DisplayTextAt(row, column, data, attribute).start();
    }

    private String getData() {
        char[] data = DT_data.getText().toCharArray();
        int i = 0, j = 0;
        while (i < data.length) {
            if (data[i] != '\\' || i + 1 == data.length)
                data[j++] = data[i++];
            else if ("nre\\".indexOf(data[++i]) >= 0)
                data[j++] = "\n\r\027\\".toCharArray()["nre\\".indexOf(data[i++])];
            else {
                myMessageDialog("Invalid control character specification in data: \\" + data[i]);
                return null;
            }
        }
        String text = new String(Arrays.copyOf(data, j));
        return MapCharacterSet.isSelected() ? text : mapData(text);
    }

    private String mapData(String text) {
        Integer encoding = new CharacterSetValues().getInteger(CharacterSet.getValue());
        String charset;
        if (encoding < 400) {
            myMessageDialog("Device specific character set " + encoding + " not supported.");
            return null;
        } else if (encoding > 990 && encoding < 1000) {
            if (encoding == LineDisplayConst.DISP_CS_ANSI)
                charset = "UTF-8";
            else if (encoding == LineDisplayConst.DISP_CS_ASCII)
                charset = "ASCII";
            else if (encoding == LineDisplayConst.DISP_CS_UNICODE)
                return text;
            else {
                myMessageDialog("Character set " + encoding + " not supported.");
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
            myMessageDialog("Character set " + encoding + " not supported:\n" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String getCp(int encoding) {
        return "cp" + encoding;
    }

    public void scrollText(ActionEvent actionEvent) {
        Integer direction = new ST_directionValues().getInteger(ST_direction.getValue());
        Integer units = new IntValues().getInteger(ST_units.getText());
        if (invalid(direction, "direction") || invalid(units, "units"))
            return;
        try {
            TheDisplay.scrollText(direction, units);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void readCharacterAtCursor(ActionEvent actionEvent) {
        Integer cursorDataValue = new IntValues().getInteger(RCAC_cursorData.getText());
        if (RCAC_cursorData.getText().length() > 0 && invalid(cursorDataValue, "cursorData"))
            return;
        int[] cursorData = cursorDataValue == null ? new int[1] : new int[]{cursorDataValue};
        try {
            TheDisplay.readCharacterAtCursor(cursorData);
            RCAC_cursorData.setText(String.valueOf(cursorData[0]));
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void createWindow(ActionEvent actionEvent) {
        Integer viewportRow = new IntValues().getInteger(CW_viewportRow.getText());
        Integer viewportColumn = new IntValues().getInteger(CW_viewportColumn.getText());
        Integer viewportHeight = new IntValues().getInteger(CW_viewportHeight.getText());
        Integer viewportWidth = new IntValues().getInteger(CW_viewportWidth.getText());
        Integer windowHeight = new IntValues().getInteger(CW_windowHeight.getText());
        Integer windowWidth = new IntValues().getInteger(CW_windowWidth.getText());
        if (invalid(viewportRow, "viewportRow") || invalid(viewportColumn, "viewportColumn") ||
                invalid(viewportHeight, "viewportHeight") || invalid(viewportWidth, "viewportWidth") ||
                invalid(windowHeight, "windowHeight") || invalid(windowWidth, "windowWidth"))
            return;
        try {
            TheDisplay.createWindow(viewportRow, viewportColumn, viewportHeight, viewportWidth, windowHeight, windowWidth);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void destroyWindow(ActionEvent actionEvent) {
        try {
            TheDisplay.destroyWindow();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void refreshWindow(ActionEvent actionEvent) {
        Integer window = new IntValues().getInteger(RW_window.getText());
        if (invalid(window, "window"))
            return;
        try {
            TheDisplay.refreshWindow(window);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void clearDescriptors(ActionEvent actionEvent) {
        try {
            TheDisplay.clearDescriptors();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setDescriptor(ActionEvent actionEvent) {
        Integer descriptor = new IntValues().getInteger(SD_descriptor.getText());
        Integer attribute = new SD_attributeValues().getInteger(SD_attribute.getValue());
        if (invalid(descriptor, "descriptor") || invalid(attribute, "attribute"))
            return;
        try {
            TheDisplay.setDescriptor(descriptor, attribute);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void defineGlyph(ActionEvent actionEvent) {
        Integer glyphCode = new IntValues().getInteger(DG_glyphCode.getText());
        byte[] glyph = getGlyph();
        if (invalid(glyphCode, "glyphCode") || invalid(glyph, "glyph"))
            return;
        try {
            TheDisplay.defineGlyph(glyphCode, glyph);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private byte[] getGlyph() {
        if (ValidGlyph) {
            String hexglyph = DG_glyph.getText().toUpperCase();
            byte[] glyph = new byte[hexglyph.length() / 2];
            for (int i = 0; i < glyph.length; i++) {
                glyph[i] = (byte)(("0123456789ABCDEF".indexOf(hexglyph.charAt(i + i))) << 4);
                glyph[i] |= (byte)("0123456789ABCDEF".indexOf(hexglyph.charAt(i + i + 1)));
            }
            return glyph;
        }
        return null;
    }

    public void setBitmap(ActionEvent actionEvent) {
        Integer bitmapNumber = new IntValues().getInteger(SB_bitmapNumber.getText());
        String fileName = SB_fileName.getText();
        Integer width = new B_widthValues().getInteger(SB_width.getValue());
        Integer alignmentX = new B_alignmentXValues().getInteger(SB_alignmentX.getValue());
        Integer alignmentY = new B_alignmentYValues().getInteger(SB_alignmentY.getValue());
        if (invalid(bitmapNumber, "bitmapNumber") || invalid(fileName, "fileName") ||invalid(width, "width") ||
                invalid(alignmentX, "alignmentX") || invalid(alignmentY, "alignmentY"))
            return;
        try {
            TheDisplay.setBitmap(bitmapNumber, fileName, width, alignmentX, alignmentY);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void displayBitmap(ActionEvent actionEvent) {
        String fileName = DB_fileName.getText();
        Integer width = new B_widthValues().getInteger(DB_width.getValue());
        Integer alignmentX = new B_alignmentXValues().getInteger(DB_alignmentX.getValue());
        Integer alignmentY = new B_alignmentYValues().getInteger(DB_alignmentY.getValue());
        if (invalid(fileName, "fileName") || invalid(width, "width") || invalid(alignmentX, "alignmentX") || invalid(alignmentY, "alignmentY"))
            return;
        try {
            TheDisplay.displayBitmap(fileName, width, alignmentX, alignmentY);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void browseSBName(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Bitmap File");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showOpenDialog(null);
        if (selected != null)
            SB_fileName.setText(selected.toString());
    }

    public void browseDBName(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Bitmap File");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showOpenDialog(null);
        if (selected != null)
            DB_fileName.setText(selected.toString());
    }

    class CharacterSetValues extends Values {
        CharacterSetValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_CS_UNICODE, "CS_UNICODE",
                    LineDisplayConst.DISP_CS_ASCII, "CS_ASCII",
                    LineDisplayConst.DISP_CS_ANSI, "CS_ANSI"
            };
        }
    }

    private class MarqueeFormatValues extends Values {
        MarqueeFormatValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_MF_WALK, "MF_WALK",
                    LineDisplayConst.DISP_MF_PLACE, "MF_PLACE"
            };
        }
    }

    private class MarqueeTypeValues extends Values {
        MarqueeTypeValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_MT_NONE, "MT_NONE",
                    LineDisplayConst.DISP_MT_UP, "MT_UP",
                    LineDisplayConst.DISP_MT_DOWN, "MT_DOWN",
                    LineDisplayConst.DISP_MT_LEFT, "MT_LEFT",
                    LineDisplayConst.DISP_MT_RIGHT, "MT_RIGHT",
                    LineDisplayConst.DISP_MT_INIT, "MT_INIT"
            };
        }
    }

    private class CapBlinkValues extends Values {
        CapBlinkValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_CB_NOBLINK, "CB_NOBLINK",
                    LineDisplayConst.DISP_CB_BLINKALL, "CB_BLINKALL",
                    LineDisplayConst.DISP_CB_BLINKEACH, "CB_BLINKEACH"
            };
        }
    }

    private class CapCharacterSetValues extends Values {
        CapCharacterSetValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_CCS_NUMERIC, "CCS_NUMERIC",
                    LineDisplayConst.DISP_CCS_ALPHA, "CCS_ALPHA",
                    LineDisplayConst.DISP_CCS_KANA, "CCS_KANA",
                    LineDisplayConst.DISP_CCS_KANJI, "CCS_KANJI",
                    LineDisplayConst.DISP_CCS_UNICODE, "CCS_UNICODE",
                    LineDisplayConst.DISP_CCS_ASCII, "CCS_ASCII"
            };
        }
    }

    private class CursorTypeValues extends Values {
        CursorTypeValues() {
            final int blink = LineDisplayConst.DISP_CT_BLINK;
            ValueList = new Object[]{
                    LineDisplayConst.DISP_CT_NONE, "CT_NONE",
                    LineDisplayConst.DISP_CT_FIXED, "CT_FIXED",
                    LineDisplayConst.DISP_CT_BLOCK, "CT_BLOCK",
                    LineDisplayConst.DISP_CT_HALFBLOCK, "CT_HALFBLOCK",
                    LineDisplayConst.DISP_CT_UNDERLINE, "CT_UNDERLINE",
                    LineDisplayConst.DISP_CT_REVERSE, "CT_REVERSE",
                    LineDisplayConst.DISP_CT_OTHER, "CT_OTHER",
                    blink|LineDisplayConst.DISP_CT_NONE, "CT_BLINK|NONE",
                    blink|LineDisplayConst.DISP_CT_FIXED, "CT_BLINK|FIXED",
                    blink|LineDisplayConst.DISP_CT_BLOCK, "CT_BLINK|BLOCK",
                    blink|LineDisplayConst.DISP_CT_HALFBLOCK, "CT_BLINK|HALFBLOCK",
                    blink|LineDisplayConst.DISP_CT_UNDERLINE, "CT_BLINK|UNDERLINE",
                    blink|LineDisplayConst.DISP_CT_REVERSE, "CT_BLINK|REVERSE",
                    blink|LineDisplayConst.DISP_CT_OTHER, "CT_BLINK|OTHER"
            };
        }
    }

    private class CapReadBackValues extends Values {
        CapReadBackValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_CRB_NONE, "CRB_NONE",
                    LineDisplayConst.DISP_CRB_SINGLE, "CRB_SINGLE"
            };
        }
    }

    private class CapReverseValues extends Values {
        CapReverseValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_CR_NONE, "CR_NONE",
                    LineDisplayConst.DISP_CR_REVERSEALL, "CR_REVERSEALL",
                    LineDisplayConst.DISP_CR_REVERSEEACH, "CR_REVERSEEACH"
            };
        }
    }

    private class ST_directionValues extends Values {
        ST_directionValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_ST_UP, "ST_UP",
                    LineDisplayConst.DISP_ST_DOWN, "ST_DOWN",
                    LineDisplayConst.DISP_ST_LEFT, "ST_LEFT",
                    LineDisplayConst.DISP_ST_RIGHT, "ST_RIGHT"
            };
        }
    }

    private class DT_attributeValues extends Values {
        DT_attributeValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_DT_NORMAL, "DT_NORMAL",
                    LineDisplayConst.DISP_DT_BLINK, "DT_BLINK",
                    LineDisplayConst.DISP_DT_REVERSE, "DT_REVERSE",
                    LineDisplayConst.DISP_DT_BLINK_REVERSE, "DT_BLINK_REVERSE"
            };
        }
    }

    private class SD_attributeValues extends Values {
        SD_attributeValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_SD_OFF, "SD_OFF",
                    LineDisplayConst.DISP_SD_ON, "SD_ON",
                    LineDisplayConst.DISP_SD_BLINK, "SD_BLINK"
            };
        }
    }

    private class B_widthValues extends Values {
        B_widthValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_BM_ASIS, "BM_ASIS"
            };
        }
    }

    private class B_alignmentXValues extends Values {
        B_alignmentXValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_BM_LEFT, "BM_LEFT",
                    LineDisplayConst.DISP_BM_CENTER, "BM_CENTER",
                    LineDisplayConst.DISP_BM_RIGHT, "BM_RIGHT"
            };
        }
    }

    private class B_alignmentYValues extends Values {
        B_alignmentYValues() {
            ValueList = new Object[]{
                    LineDisplayConst.DISP_BM_TOP, "BM_TOP",
                    LineDisplayConst.DISP_BM_CENTER, "BM_CENTER",
                    LineDisplayConst.DISP_BM_BOTTOM, "BM_BOTTOM"
            };
        }
    }
}
