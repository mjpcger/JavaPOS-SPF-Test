/*
 * Copyright 2023 Martin Conrad
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

import de.gmxhome.conrad.jpos.jpos_base.JposDevice;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jpos.*;
import jpos.events.*;

import java.lang.reflect.Method;
import java.net.*;
import java.util.*;

import static de.gmxhome.conrad.jpos.jpos_base.JposBaseDevice.stringArrayToLongArray;

public class PointCardRWController extends CommonController {
    private PointCardRW ThePointCardRW;
    private PropertyTableRow CharacterSetRow;
    private PropertyTableRow CharacterSetListRow;
    private PropertyTableRow LineCharsRow;
    private PropertyTableRow LineCharsListRow;
    private PropertyTableRow LineHeightRow;
    private PropertyTableRow LineSpacingRow;
    private PropertyTableRow MapCharacterSetRow;
    private PropertyTableRow MapModeRow;
    private PropertyTableRow TracksToReadRow;
    private PropertyTableRow TracksToWriteRow;
    private PropertyTableRow Track1DataRow;
    private PropertyTableRow Track2DataRow;
    private PropertyTableRow Track3DataRow;
    private PropertyTableRow Track4DataRow;
    private PropertyTableRow Track5DataRow;
    private PropertyTableRow Track6DataRow;
    private PropertyTableRow Write1DataRow;
    private PropertyTableRow Write2DataRow;
    private PropertyTableRow Write3DataRow;
    private PropertyTableRow Write4DataRow;
    private PropertyTableRow Write5DataRow;
    private PropertyTableRow Write6DataRow;
    private PropertyTableRow CapRight90Row;
    private PropertyTableRow CapRotate180Row;
    private PropertyTableRow CapLeft90Row;
    private PropertyTableRow CapPrintRow;
    private PropertyTableRow CapClearPrintRow;
    private PropertyTableRow CapPrintModeRow;

    public ComboBox<String> BI_timeout;
    public ComboBox<String> BR_timeout;
    public ComboBox<String> RP_rotation;
    public ComboBox<String> PW_kind;
    public ComboBox<String> CPW_kind;
    public ComboBox<String> DataHelp;
    public TextField Write1Data;
    public TextField Write2Data;
    public TextField Write3Data;
    public TextField Write4Data;
    public TextField Write5Data;
    public TextField Write6Data;
    public TextField XX_data;
    public TextField PW_hposition;
    public TextField PW_vposition;
    public TextField CPW_hposition;
    public TextField CPW_vposition;
    public TextField CPW_width;
    public TextField CPW_height;
    public CheckBox TTR1;
    public CheckBox TTR2;
    public CheckBox TTR3;
    public CheckBox TTR4;
    public CheckBox TTR5;
    public CheckBox TTR6;
    public CheckBox TTW1;
    public CheckBox TTW2;
    public CheckBox TTW3;
    public CheckBox TTW4;
    public CheckBox TTW5;
    public CheckBox TTW6;
    public ComboBox<String> CharacterSet;
    public CheckBox MapCharacterSet;
    public ComboBox<String> MapMode;
    public ComboBox<String> LineChars;
    public TextField LineHeight;
    public TextField LineSpacing;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Hexadecimal;
        MaxConversionLength = Integer.MAX_VALUE;
        super.initialize(url, resourceBundle);
        ThePointCardRW = (PointCardRW) Control;
        ThePointCardRW.addDirectIOListener(this);
        ThePointCardRW.addStatusUpdateListener(this);
        ThePointCardRW.addDataListener(this);
        ThePointCardRW.addErrorListener(this);
        Values hex = new HexValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("CardState", "", new CardStateValues()));
        Properties.getItems().add(new PropertyTableRow("DataCount", ""));
        Properties.getItems().add(new PropertyTableRow("DataEventEnabled", ""));
        Properties.getItems().add(new PropertyTableRow("CapBold", ""));
        Properties.getItems().add(new PropertyTableRow("CapCardEntranceSensor", "", new BoolAsIntValues()));
        Properties.getItems().add(new PropertyTableRow("CapCharacterSet", "", new CapCharacterSetValues()));
        Properties.getItems().add(new PropertyTableRow("CapCleanCard", ""));
        Properties.getItems().add(CapClearPrintRow = new PropertyTableRow("CapClearPrint", ""));
        Properties.getItems().add(new PropertyTableRow("CapDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapDwide", ""));
        Properties.getItems().add(new PropertyTableRow("CapDwideDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapItalic", ""));
        Properties.getItems().add(CapLeft90Row = new PropertyTableRow("CapLeft90", ""));
        Properties.getItems().add(new PropertyTableRow("CapMapCharacterSet", ""));
        Properties.getItems().add(CapPrintRow = new PropertyTableRow("CapPrint", ""));
        Properties.getItems().add(CapPrintModeRow = new PropertyTableRow("CapPrintMode", ""));
        Properties.getItems().add(CapRight90Row = new PropertyTableRow("CapRight90", ""));
        Properties.getItems().add(CapRotate180Row = new PropertyTableRow("CapRotate180", ""));
        Properties.getItems().add(new PropertyTableRow("CapTracksToRead", "", hex));
        Properties.getItems().add(new PropertyTableRow("CapTracksToWrite", "", hex));
        Properties.getItems().add(CharacterSetRow = new PropertyTableRow("CharacterSet", "", new CharacterSetValues()));
        Properties.getItems().add(CharacterSetListRow = new PropertyTableRow("CharacterSetList", ""));
        Properties.getItems().add(new PropertyTableRow("FontTypefaceList", ""));
        Properties.getItems().add(LineCharsRow = new PropertyTableRow("LineChars", ""));
        Properties.getItems().add(LineCharsListRow = new PropertyTableRow("LineCharsList", ""));
        Properties.getItems().add(LineHeightRow = new PropertyTableRow("LineHeight", ""));
        Properties.getItems().add(LineSpacingRow = new PropertyTableRow("LineSpacing", ""));
        Properties.getItems().add(new PropertyTableRow("LineWidth", ""));
        Properties.getItems().add(MapCharacterSetRow = new PropertyTableRow("MapCharacterSet", ""));
        Properties.getItems().add(MapModeRow = new PropertyTableRow("MapMode", "", new MapModeValues()));
        Properties.getItems().add(new PropertyTableRow("MaxLine", ""));
        Properties.getItems().add(new PropertyTableRow("PrintHeight", ""));
        Properties.getItems().add(new PropertyTableRow("ReadState1", "", hex));
        Properties.getItems().add(new PropertyTableRow("ReadState2", "", hex));
        Properties.getItems().add(new PropertyTableRow("RecvLength1", "", hex));
        Properties.getItems().add(new PropertyTableRow("RecvLength2", "", hex));
        Properties.getItems().add(new PropertyTableRow("SidewaysMaxChars", ""));
        Properties.getItems().add(new PropertyTableRow("SidewaysMaxLines", ""));
        Properties.getItems().add(TracksToReadRow = new PropertyTableRow("TracksToRead", "", hex));
        Properties.getItems().add(TracksToWriteRow = new PropertyTableRow("TracksToWrite", "", hex));
        Properties.getItems().add(Track1DataRow = new PropertyTableRow("Track1Data", ""));
        Properties.getItems().add(Track2DataRow = new PropertyTableRow("Track2Data", ""));
        Properties.getItems().add(Track3DataRow = new PropertyTableRow("Track3Data", ""));
        Properties.getItems().add(Track4DataRow = new PropertyTableRow("Track4Data", ""));
        Properties.getItems().add(Track5DataRow = new PropertyTableRow("Track5Data", ""));
        Properties.getItems().add(Track6DataRow = new PropertyTableRow("Track6Data", ""));
        Properties.getItems().add(new PropertyTableRow("WriteState1", "", hex));
        Properties.getItems().add(new PropertyTableRow("WriteState2", "", hex));
        Properties.getItems().add(Write1DataRow = new PropertyTableRow("Write1Data", ""));
        Properties.getItems().add(Write2DataRow = new PropertyTableRow("Write2Data", ""));
        Properties.getItems().add(Write3DataRow = new PropertyTableRow("Write3Data", ""));
        Properties.getItems().add(Write4DataRow = new PropertyTableRow("Write4Data", ""));
        Properties.getItems().add(Write5DataRow = new PropertyTableRow("Write5Data", ""));
        Properties.getItems().add(Write6DataRow = new PropertyTableRow("Write6Data", ""));

        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        Values val = new TimeoutValues();
        for (int i = 1; i < val.ValueList.length; i+=2) {
            BI_timeout.getItems().add(val.ValueList[i].toString());
            BR_timeout.getItems().add(val.ValueList[i].toString());
        }
        RP_rotation.getItems().add(new RP_rotationValues().getSymbol(PointCardRWConst.PCRW_RP_NORMAL));
        DataHelp.getItems().add("Help");
        DataHelp.getItems().add("");
        DataHelp.getItems().add("Valid text may contain ASCII characters and");
        DataHelp.getItems().add("byte values specified as 3-digit octal numbers");
        DataHelp.getItems().add("preceeded by '\\'. A backslash ('\\') can be");
        DataHelp.getItems().add("passed as '\\\\'.");
        DataHelp.setValue("Help");
        setPropertyOnFocusLost(LineHeight, "LineHeight");
        setPropertyOnFocusLost(LineSpacing, "LineSpacing");
        setPropertyOnFocusLost(Write1Data, "Write1Data");
        setPropertyOnFocusLost(Write2Data, "Write2Data");
        setPropertyOnFocusLost(Write3Data, "Write3Data");
        setPropertyOnFocusLost(Write4Data, "Write4Data");
        setPropertyOnFocusLost(Write5Data, "Write5Data");
        setPropertyOnFocusLost(Write6Data, "Write6Data");
        updateGui();
    }

    @Override
    public void updateGui() {
        boolean o90 = CapRight90Row.getValue().equals("true");
        boolean o180 = CapRotate180Row.getValue().equals("true");
        boolean o270 = CapLeft90Row.getValue().equals("true");
        boolean op = CapPrintRow.getValue().equals("true");
        boolean oc = CapClearPrintRow.getValue().equals("true");
        int ow = TracksToWriteRow.getValue().compareTo("0");
        String ocsl = CharacterSetListRow.getValue();
        String osc = CharacterSetRow.getValue();
        String olc = LineCharsListRow.getValue();
        boolean om = CapPrintModeRow.getValue().equals("true");
        super.updateGui();
        boolean n90 = CapRight90Row.getValue().equals("true");
        boolean n180 = CapRotate180Row.getValue().equals("true");
        boolean n270 = CapLeft90Row.getValue().equals("true");
        boolean np = CapPrintRow.getValue().equals("true");
        boolean nc = CapClearPrintRow.getValue().equals("true");
        int nw = TracksToWriteRow.getValue().compareTo("0");
        String ncsl = CharacterSetListRow.getValue();
        String ncs = CharacterSetRow.getValue();
        if (!InUpdateGui) {
            InUpdateGui = true;
            Values val;
            if (!ocsl.equals(ncsl) || !osc.equals(ncs)) {
                val = CharacterSetRow.getValueConverter();
                CharacterSet.getItems().clear();
                if (CharacterSetListRow.getValue().length() > 0) {
                    long[] valid = stringArrayToLongArray(CharacterSetListRow.getValue().split(","));
                    for (long cset : valid) {
                        String s = val.getSymbol((int) cset);
                        CharacterSet.getItems().add(s);
                    }
                }
            }
            if (!CharacterSetRow.getValue().equals(CharacterSet.getValue()))
                CharacterSet.setValue(CharacterSetRow.getValue());
            if (om != CapPrintModeRow.getValue().equals("true")) {
                if (om)
                    MapMode.getItems().clear();
                else {
                    val = MapModeRow.getValueConverter();
                    for (int i = 1; i < val.ValueList.length; i += 2) {
                        String s = val.ValueList[i].toString();
                        MapMode.getItems().add(s);
                    }
                }
            }
            if (!MapModeRow.getValue().equals(MapMode.getValue()))
                MapMode.setValue(MapModeRow.getValue());
            if (!olc.equals(LineCharsListRow.getValue())) {
                LineChars.getItems().clear();
                for (String lines : LineCharsListRow.getValue().split(",")) {
                    LineChars.getItems().add(lines);
                    if (LineCharsRow.getValue().equals(lines))
                        LineChars.setValue(lines);
                }
            }
            if (!LineCharsRow.getValue().equals(LineChars.getValue()))
                LineChars.setValue(LineCharsRow.getValue());
            val = new PW_kindValues();
            if (np != op || (nw > 0) != (ow > 0)) {
                PW_kind.getItems().clear();
                if (np)
                    PW_kind.getItems().add(val.getSymbol(1));
                if (nw > 0)
                    PW_kind.getItems().add(val.getSymbol(2));
                if (nw > 0 && np)
                    PW_kind.getItems().add(val.getSymbol(3));
            }
            if (nc != oc || (nw > 0) != (ow > 0)) {
                CPW_kind.getItems().clear();
                if (nc)
                    CPW_kind.getItems().add(val.getSymbol(1));
                if (nw >= 0)
                    CPW_kind.getItems().add(val.getSymbol(2));
                if (nw >= 0 && nc)
                    CPW_kind.getItems().add(val.getSymbol(3));
            }
            if (n90 != o90 || n180 != o180 || o270 != n270) {
                RP_rotation.getItems().clear();
                RP_rotation.getItems().add((val = new RP_rotationValues()).getSymbol(PointCardRWConst.PCRW_RP_NORMAL));
                if (n90)
                    RP_rotation.getItems().add(val.getSymbol(PointCardRWConst.PCRW_RP_RIGHT90));
                if (n180)
                    RP_rotation.getItems().add(val.getSymbol(PointCardRWConst.PCRW_RP_ROTATE180));
                if (n270)
                    RP_rotation.getItems().add(val.getSymbol(PointCardRWConst.PCRW_RP_LEFT90));
            }
            if (!LineHeight.isFocused())
                LineHeight.setText(LineHeightRow.getValue());
            if (!LineSpacing.isFocused())
                LineSpacing.setText(LineSpacingRow.getValue());
            MapCharacterSet.setSelected(MapCharacterSetRow.getValue().equals("true"));
            val = TracksToWriteRow.getValueConverter();
            Integer ttw = val.getInteger(TracksToWriteRow.getValue());
            TTW1.setSelected(ttw != null && (ttw & PointCardRWConst.PCRW_TRACK1) != 0);
            TTW2.setSelected(ttw != null && (ttw & PointCardRWConst.PCRW_TRACK2) != 0);
            TTW3.setSelected(ttw != null && (ttw & PointCardRWConst.PCRW_TRACK3) != 0);
            TTW4.setSelected(ttw != null && (ttw & PointCardRWConst.PCRW_TRACK4) != 0);
            TTW5.setSelected(ttw != null && (ttw & PointCardRWConst.PCRW_TRACK5) != 0);
            TTW6.setSelected(ttw != null && (ttw & PointCardRWConst.PCRW_TRACK6) != 0);
            Integer ttr = val.getInteger(TracksToReadRow.getValue());
            TTR1.setSelected(ttr != null && (ttr & PointCardRWConst.PCRW_TRACK1) != 0);
            TTR2.setSelected(ttr != null && (ttr & PointCardRWConst.PCRW_TRACK2) != 0);
            TTR3.setSelected(ttr != null && (ttr & PointCardRWConst.PCRW_TRACK3) != 0);
            TTR4.setSelected(ttr != null && (ttr & PointCardRWConst.PCRW_TRACK4) != 0);
            TTR5.setSelected(ttr != null && (ttr & PointCardRWConst.PCRW_TRACK5) != 0);
            TTR6.setSelected(ttr != null && (ttr & PointCardRWConst.PCRW_TRACK6) != 0);
            InUpdateGui = false;
        }
    }

    @Override
    public void preGotError(ErrorEvent ev) {
        super.preGotError(ev);
        if (ev.getErrorLocus() == JposConst.JPOS_EL_INPUT) {
            Write1Data.setText(Track1DataRow.getValue());
            Write2Data.setText(Track2DataRow.getValue());
            Write3Data.setText(Track3DataRow.getValue());
            Write4Data.setText(Track4DataRow.getValue());
            Write5Data.setText(Track5DataRow.getValue());
            Write6Data.setText(Track6DataRow.getValue());
        }
    }

    @Override
    public void gotData(DataEvent ev) {
        super.gotData(ev);
        Write1Data.setText(Track1DataRow.getValue());
        Write2Data.setText(Track2DataRow.getValue());
        Write3Data.setText(Track3DataRow.getValue());
        Write4Data.setText(Track4DataRow.getValue());
        Write5Data.setText(Track5DataRow.getValue());
        Write6Data.setText(Track6DataRow.getValue());
    }

    public void dataHelp(ActionEvent ignore) {
        DataHelp.setValue("Help");
        updateGuiLater();
    }

    public void trackToWrite(ActionEvent ignore) {
        if (!InUpdateGui) {
            int ttw = 0;
            if (TTW1.isSelected())
                ttw |= PointCardRWConst.PCRW_TRACK1;
            if (TTW2.isSelected())
                ttw |= PointCardRWConst.PCRW_TRACK2;
            if (TTW3.isSelected())
                ttw |= PointCardRWConst.PCRW_TRACK3;
            if (TTW4.isSelected())
                ttw |= PointCardRWConst.PCRW_TRACK4;
            if (TTW5.isSelected())
                ttw |= PointCardRWConst.PCRW_TRACK5;
            if (TTW6.isSelected())
                ttw |= PointCardRWConst.PCRW_TRACK6;
            String s = TracksToWriteRow.getValueConverter().getSymbol(ttw);
            if (!TracksToWriteRow.getValue().equals(s)) {
                try {
                    ThePointCardRW.setTracksToWrite(ttw);
                    updateTrack(TTW1, Write1Data, Write1DataRow);
                    updateTrack(TTW2, Write2Data, Write2DataRow);
                    updateTrack(TTW3, Write3Data, Write3DataRow);
                    updateTrack(TTW4, Write4Data, Write4DataRow);
                    updateTrack(TTW5, Write5Data, Write5DataRow);
                    updateTrack(TTW6, Write6Data, Write6DataRow);
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    private void updateTrack(CheckBox ttw1, TextField write1Data, PropertyTableRow write1DataRow) throws Exception {
        if (ttw1.isSelected() && !write1DataRow.getValue().equals(write1Data.getText())) {
            Method setWriteXData = ThePointCardRW.getClass().getMethod("set" + write1DataRow.getName(), String.class);
            setWriteXData.invoke(ThePointCardRW, write1Data.getText());
        }
    }

    public void trackToRead(ActionEvent ignore) {
        if (!InUpdateGui) {
            int ttr = 0;
            if (TTR1.isSelected())
                ttr |= PointCardRWConst.PCRW_TRACK1;
            if (TTR2.isSelected())
                ttr |= PointCardRWConst.PCRW_TRACK2;
            if (TTR3.isSelected())
                ttr |= PointCardRWConst.PCRW_TRACK3;
            if (TTR4.isSelected())
                ttr |= PointCardRWConst.PCRW_TRACK4;
            if (TTR5.isSelected())
                ttr |= PointCardRWConst.PCRW_TRACK5;
            if (TTR6.isSelected())
                ttr |= PointCardRWConst.PCRW_TRACK6;
            String s = TracksToReadRow.getValueConverter().getSymbol(ttr);
            if (!TracksToReadRow.getValue().equals(s)) {
                try {
                    ThePointCardRW.setTracksToRead(ttr);
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setCharacterSet(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!CharacterSetRow.getValue().equals(CharacterSet.getValue())) {
                try {
                    ThePointCardRW.setCharacterSet(new IntValues().getInteger(CharacterSet.getValue()));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setMapCharacterSet(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (MapCharacterSetRow.getValue().equals("true") != MapCharacterSet.isSelected()) {
                try {
                    ThePointCardRW.setMapCharacterSet(MapCharacterSet.isSelected());
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setMapMode(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!MapModeRow.getValue().equals(MapMode.getValue())) {
                try {
                    ThePointCardRW.setMapMode(new IntValues().getInteger(MapMode.getValue()));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setLineChars(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!LineCharsRow.getValue().equals(LineChars.getValue())) {
                try {
                    ThePointCardRW.setLineChars(new IntValues().getInteger(LineChars.getValue()));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setLineHeight(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!LineHeightRow.getValue().equals(LineHeight.getText())) {
                try {
                    ThePointCardRW.setLineHeight(new IntValues().getInteger(LineHeight.getText()));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setLineSpacing(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!LineSpacingRow.getValue().equals(LineSpacing.getText())) {
                try {
                    ThePointCardRW.setLineSpacing(new IntValues().getInteger(LineSpacing.getText()));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setWrite1Data(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!Write1DataRow.getValue().equals(Write1Data.getText())) {
                try {
                    ThePointCardRW.setWrite1Data(Write1Data.getText());
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setWrite2Data(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!Write2DataRow.getValue().equals(Write2Data.getText())) {
                try {
                    ThePointCardRW.setWrite2Data(Write2Data.getText());
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setWrite3Data(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!Write3DataRow.getValue().equals(Write3Data.getText())) {
                try {
                    ThePointCardRW.setWrite3Data(Write3Data.getText());
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setWrite4Data(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!Write4DataRow.getValue().equals(Write4Data.getText())) {
                try {
                    ThePointCardRW.setWrite4Data(Write4Data.getText());
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setWrite5Data(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!Write5DataRow.getValue().equals(Write5Data.getText())) {
                try {
                    ThePointCardRW.setWrite5Data(Write5Data.getText());
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setWrite6Data(ActionEvent ignore) {
        if (!InUpdateGui) {
            if (!Write6DataRow.getValue().equals(Write6Data.getText())) {
                try {
                    ThePointCardRW.setWrite6Data(Write6Data.getText());
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    private class CleanCardHandler extends MethodProcessor {
        public CleanCardHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            ThePointCardRW.cleanCard();
        }
    }

    public void cleanCard(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        new CleanCardHandler().start();
    }

    private class BeginInsertionHandler extends MethodProcessor {
        private final int Timeout;
        public BeginInsertionHandler(Integer timeout) {
            super(null);
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            ThePointCardRW.beginInsertion(Timeout);
        }
    }

    public void beginInsertion(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        Integer timeout = new TimeoutValues().getInteger(BI_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new BeginInsertionHandler(timeout).start();
    }

    private class EndInsertionHandler extends MethodProcessor {
        public EndInsertionHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            ThePointCardRW.endInsertion();
        }
    }

    public void endInsertion(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        new EndInsertionHandler().start();
    }

    private class BeginRemovalHandler extends MethodProcessor {
        private final int Timeout;
        public BeginRemovalHandler(Integer timeout) {
            super(null);
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            ThePointCardRW.beginRemoval(Timeout);
        }
    }

    public void beginRemoval(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        Integer timeout = new TimeoutValues().getInteger(BR_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new BeginRemovalHandler(timeout).start();
    }

    private class EndRemovalHandler extends MethodProcessor {
        public EndRemovalHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            ThePointCardRW.endRemoval();
        }
    }

    public void endRemoval(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        new EndRemovalHandler().start();
    }

    private class RotatePrintHandler extends MethodProcessor {
        private final int Rotation;
        public RotatePrintHandler(Integer rotation) {
            super(null);
            Rotation = rotation;
        }

        @Override
        void runIt() throws JposException {
            ThePointCardRW.rotatePrint(Rotation);
        }
    }

    public void rotatePrint(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        Integer rotation = new RP_rotationValues().getInteger(RP_rotation.getValue());
        if (!invalid(rotation, "rotation"))
            new RotatePrintHandler(rotation).start();
    }

    private String dataToString() {
        String s = XX_data.getText();
        char[] t = new char[s.length()];
        int i = 0;
        for (int l = 0; l < s.length(); l++) {
            if (s.charAt(l) == '\\' && ++l < s.length()) {
                if (s.charAt(l) == '\\')
                    t[i++] = '\\';
                else if (l + 3 < s.length()){
                    int oc0 = "0123".indexOf(s.charAt(l++));
                    int oc1 = "01234567".indexOf(s.charAt(l++));
                    int oc2 = "01234567".indexOf(s.charAt(l++));
                    if (oc0 < 0 || oc1 < 0 || oc2 < 0)
                        return null;
                    t[i++] = (char)(((oc0 * 0x40) + oc1) * 0x8 + oc2);
                } else
                    return null;
            } else {
                t[i++] = s.charAt(l);
            }
        }
        return new String(Arrays.copyOf(t, i));
    }

    private class ValidateDataHandler extends MethodProcessor {
        private final String Data;
        public ValidateDataHandler(String data) {
            super(null);
            Data = data;
        }

        @Override
        void runIt() throws JposException {
            ThePointCardRW.validateData(Data);
        }
    }

    public void validateData(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        String data = dataToString();
        if (!invalid(data, "data"))
            new ValidateDataHandler(data).start();
    }

    private class PrintWriteHandler extends MethodProcessor {
        private final String Data;
        private final int Kind;
        private final int HPos;
        private final int VPos;
        public PrintWriteHandler(int kind, int hpos, int vpos, String data) {
            super(null);
            Kind = kind;
            HPos = hpos;
            VPos = vpos;
            Data = data;
        }

        @Override
        void runIt() throws JposException {
            ThePointCardRW.printWrite(Kind, HPos, VPos, Data);
        }
    }

    public void printWrite(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        Integer kind = new PW_kindValues().getInteger(PW_kind.getValue());
        Integer hposition = new IntValues().getInteger(PW_hposition.getText());
        Integer vposition = new IntValues().getInteger(PW_vposition.getText());
        String data = dataToString();
        if (!invalid(kind, "kind") && !invalid(hposition, "hposition") && !invalid(vposition, "vposition") && !invalid(data, "data"))
            new PrintWriteHandler(kind, hposition, vposition, data).start();
    }

    private class ClearPrintWriteHandler extends MethodProcessor {
        private final int Kind, HPos, VPos, Width, Height;
        public ClearPrintWriteHandler(int kind, int hpos, int vpos, int width, int height) {
            super(null);
            Kind = kind;
            HPos = hpos;
            VPos = vpos;
            Width = width;
            Height = height;
        }

        @Override
        void runIt() throws JposException {
            ThePointCardRW.clearPrintWrite(Kind, HPos, VPos, Width, Height);
        }
    }

    public void clearPrintWrite(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        Integer kind = new PW_kindValues().getInteger(CPW_kind.getValue());
        Integer hposition = new IntValues().getInteger(CPW_hposition.getText());
        Integer vposition = new IntValues().getInteger(CPW_vposition.getText());
        Integer width = new IntValues().getInteger(CPW_width.getText());
        Integer height = new IntValues().getInteger(CPW_height.getText());
        if (!invalid(kind, "kind") && !invalid(hposition, "hposition") && !invalid(vposition, "vposition") && !invalid(width, "width") && !invalid(height, "height"))
            new ClearPrintWriteHandler(kind, hposition, vposition, width, height).start();
    }

    private static class CardStateValues extends Values {
        CardStateValues() {
            ValueList = new Object[]{
                    PointCardRWConst.PCRW_STATE_NOCARD, "NOCARD",
                    PointCardRWConst.PCRW_STATE_REMAINING, "REMAINING",
                    PointCardRWConst.PCRW_STATE_INRW, "INRW"
            };
        }
    }

    private static class BoolAsIntValues extends Values {
        BoolAsIntValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_TRUE, "TRUE",
                    JposConst.JPOS_FALSE, "FALSE"
            };
        }
    }

    private static class CapCharacterSetValues extends Values {
        CapCharacterSetValues() {
            ValueList = new Object[]{
                    PointCardRWConst.PCRW_CCS_ALPHA, "ALPHA",
                    PointCardRWConst.PCRW_CCS_KANA, "KANA",
                    PointCardRWConst.PCRW_CCS_KANJI, "KANJI",
                    PointCardRWConst.PCRW_CCS_UNICODE, "UNICODE",
                    PointCardRWConst.PCRW_CCS_ASCII, "US_ASCII"
            };
        }
    }

    private static class CharacterSetValues extends Values {
        CharacterSetValues() {
            ValueList = new Object[]{
                    PointCardRWConst.PCRW_CS_UNICODE, "UNICODE",
                    PointCardRWConst.PCRW_CS_ASCII, "ASCII",
                    PointCardRWConst.PCRW_CS_ANSI, "ANSI"
            };
        }
    }

    private static class MapModeValues extends Values {
        MapModeValues() {
            ValueList = new Object[]{
                    PointCardRWConst.PCRW_MM_DOTS, "DOTS",
                    PointCardRWConst.PCRW_MM_TWIPS, "TWIPS",
                    PointCardRWConst.PCRW_MM_ENGLISH, "ENGLISH",
                    PointCardRWConst.PCRW_MM_METRIC, "METRIC"
            };
        }
    }

    private static class PW_kindValues extends Values {
        PW_kindValues() {
            ValueList = new Object[]{
                    1, "Print",
                    2, "Write",
                    3, "Both"
            };
        }
    }

    private static class RP_rotationValues extends Values {
        RP_rotationValues() {
            ValueList = new Object[]{
                    PointCardRWConst.PCRW_RP_NORMAL, "NORMAL",
                    PointCardRWConst.PCRW_RP_RIGHT90, "RIGHT90°",
                    PointCardRWConst.PCRW_RP_LEFT90, "LEFT90°",
                    PointCardRWConst.PCRW_RP_ROTATE180, "ROTATE180°"
            };
        }
    }
}
