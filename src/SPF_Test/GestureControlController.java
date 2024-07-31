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

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jpos.GestureControl;
import jpos.JposException;
import jpos.events.OutputCompleteEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static jpos.GestureControlConst.*;
import static jpos.JposConst.*;

public class GestureControlController extends CommonController {
    private GestureControl TheGestureControl;
    private PropertyTableRow AutoModeRow;
    private PropertyTableRow AutoModeListRow;
    private PropertyTableRow CapStorageRow;
    private PropertyTableRow MotionListRow;
    private PropertyTableRow OutputIDRow;
    private PropertyTableRow PoseCreationModeRow;
    private PropertyTableRow PoseListRow;
    private PropertyTableRow StorageRow;

    public CheckBox PoseCreationMode;
    public ComboBox<String> Storage;
    public ComboBox<String> AutoMode;
    public ComboBox<String> CP_time;
    public TextField PM_fileName;
    public ComboBox<String> SP_time;
    public CheckBox SP_absolute;
    public ComboBox<String> SS_time;
    public ComboBox<String> SC_outputID;
    public TextField GP_jointID;
    public TextField GP_position;
    public TextArea PS_speedList;
    public TextArea PS_positionList;
    public TextArea CM_poseList;

    public Boolean SetOutpuID = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheGestureControl = (GestureControl) Control;
        TheGestureControl.addDirectIOListener(this);
            TheGestureControl.addStatusUpdateListener(this);
        TheGestureControl.addErrorListener(this);
        TheGestureControl.addOutputCompleteListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(OutputIDRow = new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(AutoModeRow = new PropertyTableRow("AutoMode", ""));
        Properties.getItems().add(AutoModeListRow = new PropertyTableRow("AutoModeList", ""));
        Properties.getItems().add(new PropertyTableRow("CapAssociatedHardTotalsDevice", ""));
        Properties.getItems().add(new PropertyTableRow("CapMotion", ""));
        Properties.getItems().add(new PropertyTableRow("CapMotionCreation", ""));
        Properties.getItems().add(new PropertyTableRow("CapPose", ""));
        Properties.getItems().add(new PropertyTableRow("CapPoseCreation", ""));
        Properties.getItems().add(CapStorageRow = new PropertyTableRow("CapStorage", "", new CapStorageValues()));
        Properties.getItems().add(new PropertyTableRow("JointList", ""));
        Properties.getItems().add(MotionListRow = new PropertyTableRow("MotionList", ""));
        Properties.getItems().add(PoseCreationModeRow = new PropertyTableRow("PoseCreationMode", ""));
        Properties.getItems().add(PoseListRow = new PropertyTableRow("PoseList", ""));
        Properties.getItems().add(StorageRow = new PropertyTableRow("Storage", "", new StorageValues()));
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
        PS_positionList.setPromptText("positionList, One line per position information");
        PS_speedList.setPromptText("speedList, One line per speed information");
        CM_poseList.setPromptText("poseList, One line per pose information");
        String forever = new TimeoutValues().getSymbol(JPOS_FOREVER);
        CP_time.getItems().add(forever);
        SP_time.getItems().add(forever);
        SS_time.getItems().add(forever);
        updateGui();
    }

    @Override
    public void updateGui() {
        String prevPoseCreationMode = PoseCreationModeRow.getValue();
        String prevAutoModeList = AutoModeListRow.getValue();
        String prevCapStorage = CapStorageRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            if (!prevPoseCreationMode.equals(PoseCreationModeRow.getValue())) {
                PoseCreationMode.setSelected(PoseCreationModeRow.getValue().equalsIgnoreCase("true"));
                if (CapStorageRow.getValue().equals(CapStorageRow.getValueConverter().getSymbol(GCTL_CST_ALL))) {
                    if (PoseCreationModeRow.getValue().equalsIgnoreCase("true")) {
                        Storage.getItems().add(StorageRow.getValueConverter().getSymbol(GCTL_ST_HOST_HARDTOTALS));
                    } else {
                        for (int i = 0; i < Storage.getItems().size(); i++) {
                            if (StorageRow.getValueConverter().getSymbol(GCTL_ST_HOST_HARDTOTALS).equals(Storage.getItems().get(i))) {
                                Storage.getItems().remove(i);
                                break;
                            }
                        }
                    }
                }
            }
            if (TheGestureControl.getState() == JPOS_S_IDLE && SetOutpuID != null) {
                SC_outputID.getItems().clear();
                SetOutpuID = null;
            }
            if (SetOutpuID != null) {
                if (SetOutpuID) {
                    SetOutpuID = false;
                    SC_outputID.getItems().add(OutputIDRow.getValue());
                }
            }
            if (!prevCapStorage.equals(CapStorageRow.getValue())) {
                Storage.getItems().clear();
                if (CapStorageRow.getValue().equals(CapStorageRow.getValueConverter().getSymbol(GCTL_CST_HARDTOTALS_ONLY)))
                    Storage.getItems().add(StorageRow.getValueConverter().getSymbol(GCTL_ST_HARDTOTALS));
                else if (CapStorageRow.getValue().equals(CapStorageRow.getValueConverter().getSymbol(GCTL_CST_HOST_ONLY)))
                    Storage.getItems().add(StorageRow.getValueConverter().getSymbol(GCTL_ST_HOST));
                else if (CapStorageRow.getValue().equals(CapStorageRow.getValueConverter().getSymbol(GCTL_CST_ALL))) {
                    Storage.getItems().add(StorageRow.getValueConverter().getSymbol(GCTL_ST_HARDTOTALS));
                    Storage.getItems().add(StorageRow.getValueConverter().getSymbol(GCTL_ST_HOST));
                    if (PoseCreationMode.isSelected())
                        Storage.getItems().add(StorageRow.getValueConverter().getSymbol(GCTL_ST_HOST_HARDTOTALS));
                }
            }
            if (!prevAutoModeList.equals(AutoModeListRow.getValue())) {
                AutoMode.getItems().clear();
                if (CapStorageRow.getValue().length() > 0) {
                    String current = AutoModeRow.getValue();
                    String[] items = ("," + AutoModeListRow.getValue()).split(",");
                    if (items.length == 2 && items[1].length() == 0)
                        items = Arrays.copyOf(items, 1);
                    for (String mode : items) {
                        AutoMode.getItems().add(mode);
                        if (mode.equals(current))
                            AutoMode.setValue(mode);
                    }
                }
            }
            Storage.setValue(StorageRow.getValue());
            AutoMode.setValue(AutoMode.getValue());
            InUpdateGui = false;
        }
    }

    @Override
    public void gotOutputComplete(OutputCompleteEvent event) {
        String outputID = new IntValues().getSymbol(event.getOutputID());
        while (SC_outputID.getItems().size() > 0) {
            // Must be the first, except after disable / enable of device
            if (outputID.equals(SC_outputID.getItems().get(0))) {
                SC_outputID.getItems().remove(0);
                break;
            } else {
                SC_outputID.getItems().remove(0);
            }
        }
        super.gotOutputComplete(event);
    }

    public void setStorage(ActionEvent actionEvent) {
        if (!InUpdateGui && TheGestureControl.getState() != JPOS_S_CLOSED) {
            try {
                TheGestureControl.setStorage(StorageRow.getValueConverter().getInteger(Storage.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setAutoMode(ActionEvent actionEvent) {
        if (!InUpdateGui && TheGestureControl.getState() != JPOS_S_CLOSED) {
            try {
                TheGestureControl.setAutoMode(AutoMode.getValue());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setPoseCreationMode(ActionEvent actionEvent) {
        if (!InUpdateGui && TheGestureControl.getState() != JPOS_S_CLOSED) {
            try {
                TheGestureControl.setPoseCreationMode(PoseCreationMode.isSelected());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    class CreateMotion extends MethodProcessor {
        private final String FileName, PoseList;
        CreateMotion(String fileName, String poseList) {
            super("CreateMotion");
            FileName = fileName;
            PoseList = poseList;
        }

        @Override
        void runIt() throws JposException {
            TheGestureControl.createMotion(FileName, PoseList);
        }
    }

    public void createMotion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String fileName = PM_fileName.getText();
        String poseList = CM_poseList.getText();
        if (!invalid(fileName, "fileName") && !invalid(poseList, "poseList")) {
            poseList = String.join(",", poseList.split("\n"));
            new CreateMotion(fileName, poseList).start();
        }
    }

    class CreatePose extends MethodProcessor {
        private final String FileName;
        private final int Time;

        CreatePose(String fileName, int time) {
            super("CreatePose");
            FileName = fileName;
            Time = time;
        }

        @Override
        void runIt() throws JposException {
            TheGestureControl.createPose(FileName, Time);
        }
    }

    public void createPose(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String fileName = PM_fileName.getText();
        Integer time = new TimeoutValues().getInteger(CP_time.getValue());
        if (!invalid(fileName, "fileName") && !invalid(time, "time")) {
            new CreatePose(fileName, time).start();
        }
    }

    class StartMotion extends MethodProcessor {
        private final String FileName;

        StartMotion(String fileName) {
            super("StartMotion");
            FileName = fileName;
        }

        @Override
        void runIt() throws JposException {
            TheGestureControl.startMotion(FileName);
            SetOutpuID = true;
            updateGuiLater();
        }
    }

    public void startMotion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String fileName = PM_fileName.getText();
        if (!invalid(fileName, "fileName")) {
            new StartMotion(fileName).start();
        }
    }

    class StartPose extends MethodProcessor {
        private final String FileName;

        StartPose(String fileName) {
            super("StartPose");
            FileName = fileName;
        }

        @Override
        void runIt() throws JposException {
            TheGestureControl.startPose(FileName);
            SetOutpuID = true;
            updateGuiLater();
        }
    }

    public void startPose(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String fileName = PM_fileName.getText();
        if (!invalid(fileName, "fileName")) {
            new StartPose(fileName).start();
        }
    }

    class SetPosition extends MethodProcessor {
        private final String PositionList;
        private final int Time;
        private final boolean Absolute;

        SetPosition(String positionList, int time, boolean absolute) {
            super("SetPosition");
            PositionList = positionList;
            Time = time;
            Absolute = absolute;
        }

        @Override
        void runIt() throws JposException {
            TheGestureControl.setPosition(PositionList, Time, Absolute);
            SetOutpuID = true;
            updateGuiLater();
        }
    }

    public void setPosition(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String positionList = PS_positionList.getText();
        Integer time = new TimeoutValues().getInteger(SP_time.getValue());
        boolean absolute = SP_absolute.isSelected();
        if (!invalid(positionList, "positionList") && !invalid(time, "time")) {
            positionList = String.join(",", positionList.split("\n"));
            new SetPosition(positionList, time, absolute).start();
        }
    }

    class SetSpeed extends MethodProcessor {
        private final String FileName;
        private final int Time;

        SetSpeed(String fileName, int time) {
            super("SetSpeed");
            FileName = fileName;
            Time = time;
        }

        @Override
        void runIt() throws JposException {
            TheGestureControl.setSpeed(FileName, Time);
            SetOutpuID = true;
            updateGuiLater();
        }
    }

    public void setSpeed(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String speedList = PS_speedList.getText();
        Integer time = new TimeoutValues().getInteger(SS_time.getValue());
        if (!invalid(speedList, "speedList") && !invalid(time, "time")) {
            speedList = String.join(",", speedList.split("\n"));
            new SetSpeed(speedList, time).start();
        }
    }

    class StopControl extends MethodProcessor {
        private final int OutputID;
        private boolean Success = false;

        StopControl(int outputID) {
            super("StopControl");
            OutputID = outputID;
        }

        @Override
        void runIt() throws JposException {
            TheGestureControl.stopControl(OutputID);
            Success = true;
        }

        @Override
        void finish() {
            if (Success) {
                String outputID = new IntValues().getSymbol(OutputID);
                for (Object id : SC_outputID.getItems()) {
                    if (id.equals(outputID)) {
                        SC_outputID.getItems().remove(id);
                        break;
                    }
                }
            }
            super.finish();
        }
    }

    public void stopControl(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer outputID = new IntValues().getInteger(SC_outputID.getValue());
        if (!invalid(outputID, "outputID")) {
            new StopControl(outputID).start();
        }
    }

    class GetPosition extends MethodProcessor {
        private final String JointID;
        private int Position = 0;

        GetPosition(String jointID) {
            super("GetPosition");
            JointID = jointID;
        }

        @Override
        void runIt() throws JposException {
            int[]position = {0};
            TheGestureControl.getPosition(JointID, position);
            Position = position[0];
        }

        @Override
        void finish() {
            GP_position.setEditable(true);
            GP_position.setText(String.valueOf(Position));
            GP_position.setEditable(false);
            super.finish();
        }
    }

    public void getPosition(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String jointID = GP_jointID.getText();
        if (!invalid(jointID, "jointID")) {
            new GetPosition(jointID).start();
        }
    }

    private class StorageValues extends IntValues {
        StorageValues() {
            ValueList = new Object[] {
                    GCTL_ST_HARDTOTALS, "ST_HARDTOTALS",
                    GCTL_ST_HOST, "ST_HOST",
                    GCTL_ST_HOST_HARDTOTALS, "ST_HOST_HARDTOTALS"
            };
        }
    }

    private class CapStorageValues extends IntValues {
        CapStorageValues() {
            ValueList = new Object[] {
                    GCTL_CST_HARDTOTALS_ONLY, "CST_HARDTOTALS_ONLY",
                    GCTL_CST_HOST_ONLY, "CST_HOST_ONLY",
                    GCTL_CST_ALL, "CST_ALL"
            };
        }
    }
}
