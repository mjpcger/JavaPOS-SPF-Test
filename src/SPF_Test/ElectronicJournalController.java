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

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for ElectronicJournal properties, methods and events.
 */
public class ElectronicJournalController extends CommonController {
    public CheckBox FlagWhenIdle;
    public CheckBox StorageEnabled;
    public CheckBox WaterMark;
    public ComboBox<String> Station;
    public Label MediumFreeSpace;
    public TextField PCF_fileName;
    public TextField PC_fromMarker;
    public TextField PC_toMarker;
    public TextField QC_fileName;
    public TextField QC_fromMarker;
    public TextField QC_toMarker;
    public TextField IM_mediumID;
    public TextField AM_marker;
    public TextField M_marker;
    public TextField M_dateTime;
    public ComboBox<String> RCM_markerType;
    public ComboBox<String> RM_markerType;
    public TextField RM_sessionNumber;
    public TextField RM_documentNumber;
    public ComboBox<String> RMBDT_markerType;
    public TextField RMBDT_markerNumber;
    private ElectronicJournal TheJournal;
    private PropertyTableRow FlagWhenIdleRow;
    private PropertyTableRow StationRow;
    private PropertyTableRow StorageEnabledRow;
    private PropertyTableRow WaterMarkRow;
    private PropertyTableRow MediumFreeSpaceRow;
    private PropertyTableRow CapStationRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Hexadecimal;
        super.initialize(url, resourceBundle);
        TheJournal = (ElectronicJournal) Control;
        TheJournal.addDirectIOListener(this);
        TheJournal.addStatusUpdateListener(this);
        TheJournal.addDataListener(this);
        TheJournal.addErrorListener(this);
        TheJournal.addOutputCompleteListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("CapAddMarker", ""));
        Properties.getItems().add(new PropertyTableRow("CapErasableMedium", ""));
        Properties.getItems().add(new PropertyTableRow("CapInitializeMedium", ""));
        Properties.getItems().add(new PropertyTableRow("CapMediumIsAvailable", ""));
        Properties.getItems().add(new PropertyTableRow("CapPrintContent", ""));
        Properties.getItems().add(new PropertyTableRow("CapPrintContentFile", ""));
        Properties.getItems().add(new PropertyTableRow("CapRetrieveCurrentMarker", ""));
        Properties.getItems().add(new PropertyTableRow("CapRetrieveMarker", ""));
        Properties.getItems().add(new PropertyTableRow("CapRetrieveMarkerByDateTime", ""));
        Properties.getItems().add(new PropertyTableRow("CapRetrieveMarkersDateTime", ""));
        Properties.getItems().add(CapStationRow = new PropertyTableRow("CapStation", "", new StationValues()));
        Properties.getItems().add(new PropertyTableRow("CapStorageEnabled", ""));
        Properties.getItems().add(new PropertyTableRow("CapSuspendPrintContent", ""));
        Properties.getItems().add(new PropertyTableRow("CapSuspendQueryContent", ""));
        Properties.getItems().add(new PropertyTableRow("CapWaterMark", ""));
        Properties.getItems().add(FlagWhenIdleRow = new PropertyTableRow("FlagWhenIdle", ""));
        Properties.getItems().add(MediumFreeSpaceRow = new PropertyTableRow("MediumFreeSpace", ""));
        Properties.getItems().add(new PropertyTableRow("MediumID", ""));
        Properties.getItems().add(new PropertyTableRow("MediumIsAvailable", ""));
        Properties.getItems().add(new PropertyTableRow("MediumSize", ""));
        Properties.getItems().add(new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(StationRow = new PropertyTableRow("Station", "", new StationValues()));
        Properties.getItems().add(StorageEnabledRow = new PropertyTableRow("StorageEnabled", ""));
        Properties.getItems().add(new PropertyTableRow("Suspended", ""));
        Properties.getItems().add(WaterMarkRow = new PropertyTableRow("WaterMark", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        MarkerTypeValues mval = new MarkerTypeValues();
        RCM_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_SESSION_BEG));
        RCM_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_SESSION_END));
        RCM_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_DOCUMENT));
        RCM_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_HEAD));
        RCM_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_TAIL));
        RM_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_SESSION_BEG));
        RM_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_SESSION_END));
        RM_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_DOCUMENT));
        RMBDT_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_SESSION_BEG));
        RMBDT_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_SESSION_END));
        RMBDT_markerType.getItems().add(mval.getSymbol(ElectronicJournalConst.EJ_MT_DOCUMENT));
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            FlagWhenIdle.setSelected("true".equals(FlagWhenIdleRow.getValue()));
            StorageEnabled.setSelected("true".equals(StorageEnabledRow.getValue()));
            WaterMark.setSelected("true".equals(WaterMarkRow.getValue()));
            if (!"".equals(CapStationRow.getValue())) {
                if (Station.getItems().size() == 0) {
                    StationValues sval = new StationValues();
                    Integer mask = sval.getInteger(CapStationRow.getValue());
                    if (mask == null)
                        mask = ElectronicJournalConst.EJ_S_RECEIPT | ElectronicJournalConst.EJ_S_SLIP | ElectronicJournalConst.EJ_S_JOURNAL;
                    for (int i = 0; i <= mask; i++) {
                        if ((i & ~mask) == 0)
                            Station.getItems().add(sval.getSymbol(i));
                    }
                }
                Station.setValue(StationRow.getValue());
            }
            MediumFreeSpace.setText(MediumFreeSpaceRow.getValue());
            InUpdateGui = false;
        }
    }

    public void setFlagWhenIdle(ActionEvent actionEvent) {
        try {
            TheJournal.setFlagWhenIdle(FlagWhenIdle.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setStorageEnabled(ActionEvent actionEvent) {
        try {
            TheJournal.setStorageEnabled(StorageEnabled.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setWaterMark(ActionEvent actionEvent) {
        try {
            TheJournal.setWaterMark(WaterMark.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setStation(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            Integer station = new StationValues().getInteger(Station.getValue());
            if (invalid(station, "station"))
                return;
            try {
                TheJournal.setStation(station);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    class PrintContentFileHandler extends MethodProcessor {
        private final String FileName;

        PrintContentFileHandler(String fileName) {
            super("PrintContentFile");
            FileName = fileName;
        }

        @Override
        void runIt() throws JposException {
            TheJournal.printContentFile(FileName);
        }
    }

    public void printContentFile(ActionEvent actionEvent) {
        new PrintContentFileHandler(PCF_fileName.getText()).start();
    }

    public void browsePCF_fileName(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Journal File");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showOpenDialog(null);
        if (selected != null)
            PCF_fileName.setText(selected.toString());
    }

    class PrintContentHandler extends MethodProcessor {
        private final String FromMarker;
        private final String ToMarker;

        PrintContentHandler(String from, String to) {
            super("PrintContent");
            FromMarker = from;
            ToMarker = to;
        }
        @Override
        void runIt() throws JposException {
            TheJournal.printContent(FromMarker, ToMarker);
        }
    }

    public void printContent(ActionEvent actionEvent) {
        new PrintContentHandler(PC_fromMarker.getText(), PC_toMarker.getText()).start();
    }

    class QueryContentHandler extends MethodProcessor {
        private final String FileName;
        private final String FromMarker;
        private final String ToMarker;

        QueryContentHandler(String fileName, String fromMarker, String toMarker) {
            super("QueryContent");
            FileName = fileName;
            FromMarker = fromMarker;
            ToMarker = toMarker;
        }
        @Override
        void runIt() throws JposException {
            TheJournal.queryContent(FileName, FromMarker, ToMarker);
        }
    }
    public void queryContent(ActionEvent actionEvent) {
        new QueryContentHandler(QC_fileName.getText(), QC_fromMarker.getText(), QC_toMarker.getText()).start();
    }

    public void browseQC_fileName(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Target File");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showSaveDialog(null);
        if (selected != null)
            QC_fileName.setText(selected.toString());
    }

    public void cancelPrintContent(ActionEvent actionEvent) {
        try {
            TheJournal.cancelPrintContent();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    class ResumePrintContentHandler extends MethodProcessor {
        ResumePrintContentHandler() {
            super("ResumePrintContent");
        }
        @Override
        void runIt() throws JposException {
            TheJournal.resumePrintContent();
        }
    }

    public void resumePrintContent(ActionEvent actionEvent) {
        new ResumePrintContentHandler().start();
    }

    public void suspendPrintContent(ActionEvent actionEvent) {
        try {
            TheJournal.suspendPrintContent();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void suspendQueryContent(ActionEvent actionEvent) {
        try {
            TheJournal.suspendQueryContent();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    class ResumeQueryContentHandler extends MethodProcessor {
        ResumeQueryContentHandler() {
            super("ResumeQueryContent");
        }
        @Override
        void runIt() throws JposException {
            TheJournal.resumeQueryContent();
        }
    }

    public void resumeQueryContent(ActionEvent actionEvent) {
        new ResumeQueryContentHandler().start();
    }

    public void cancelQueryContent(ActionEvent actionEvent) {
        try {
            TheJournal.cancelQueryContent();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    class InitializeMediumHandler extends MethodProcessor {
        private final String MediumID;

        InitializeMediumHandler(String mediumID) {
            super("InitializeMedium");
            MediumID = mediumID;
        }
        @Override
        void runIt() throws JposException {
            TheJournal.initializeMedium(MediumID);
        }
    }

    public void initializeMedium(ActionEvent actionEvent) {
        new InitializeMediumHandler(IM_mediumID.getText()).start();
    }

    class EraseMediumHandler extends MethodProcessor {
        EraseMediumHandler() {
            super("EraseMediumContent");
        }
        @Override
        void runIt() throws JposException {
            TheJournal.eraseMedium();
        }
    }

    public void eraseMedium(ActionEvent actionEvent) {
        new EraseMediumHandler().start();
    }

    public void addMarker(ActionEvent actionEvent) {
        try {
            TheJournal.addMarker(AM_marker.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void retrieveCurrentMarker(ActionEvent actionEvent) {
        Integer markerType = new MarkerTypeValues().getInteger(RCM_markerType.getValue());
        if (invalid(markerType, "markerType"))
            return;
        String[] marker = new String[1];
        try {
            TheJournal.retrieveCurrentMarker(markerType, marker);
            M_marker.setText(marker[0]);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void retrieveMarker(ActionEvent actionEvent) {
        Integer markerType = new MarkerTypeValues().getInteger(RM_markerType.getValue());
        Integer sessionNumber = new IntValues().getInteger(RM_sessionNumber.getText());
        Integer documentNumber = new IntValues().getInteger(RM_documentNumber.getText());
        if (invalid(markerType, "markerType") || invalid(sessionNumber, "sessionNumber") || invalid(documentNumber, "documentNumber"))
            return;
        String[] marker = new String[1];
        try {
            TheJournal.retrieveMarker(markerType, sessionNumber, documentNumber, marker);
            M_marker.setText(marker[0]);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void retrieveMarkerByDateTime(ActionEvent actionEvent) {
        Integer markerType = new MarkerTypeValues().getInteger(RMBDT_markerType.getValue());
        if (invalid(markerType, "markerType"))
            return;
        String[] marker = new String[1];
        try {
            TheJournal.retrieveMarkerByDateTime(markerType, M_dateTime.getText(), RMBDT_markerNumber.getText(), marker);
            M_marker.setText(marker[0]);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void retrieveMarkersDateTime(ActionEvent actionEvent) {
        String[] dateTime = new String[1];
        try {
            TheJournal.retrieveMarkersDateTime(M_marker.getText(), dateTime);
            M_dateTime.setText(dateTime[0]);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class StationValues extends Values {
        StationValues() {
            ValueList = new Object[]{
                    0, "-",
                    ElectronicJournalConst.EJ_S_RECEIPT, "S_RECEIPT",
                    ElectronicJournalConst.EJ_S_SLIP, "S_SLIP",
                    ElectronicJournalConst.EJ_S_RECEIPT|ElectronicJournalConst.EJ_S_SLIP, "S_RECEIPT|SLIP",
                    ElectronicJournalConst.EJ_S_JOURNAL, "S_JOURNAL",
                    ElectronicJournalConst.EJ_S_JOURNAL|ElectronicJournalConst.EJ_S_RECEIPT, "S_RECEIPT|JOURNAL",
                    ElectronicJournalConst.EJ_S_JOURNAL|ElectronicJournalConst.EJ_S_SLIP, "S_SLIP|JOURNAL",
                    ElectronicJournalConst.EJ_S_JOURNAL|ElectronicJournalConst.EJ_S_RECEIPT|ElectronicJournalConst.EJ_S_SLIP, "S_RECEIPT|SLIP|JOURNAL"
            };
        }
    }

    private class MarkerTypeValues extends Values {
        MarkerTypeValues() {
            ValueList = new Object[]{
                    ElectronicJournalConst.EJ_MT_SESSION_BEG, "MT_SESSION_BEG",
                    ElectronicJournalConst.EJ_MT_SESSION_END, "MT_SESSION_END",
                    ElectronicJournalConst.EJ_MT_DOCUMENT, "MT_DOCUMENT",
                    ElectronicJournalConst.EJ_MT_HEAD, "MT_HEAD",
                    ElectronicJournalConst.EJ_MT_TAIL, "MT_TAIL"
            };
        }
    }
}
