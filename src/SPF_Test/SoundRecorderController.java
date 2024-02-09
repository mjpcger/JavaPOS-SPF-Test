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
import javafx.scene.control.TextField;
import jpos.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SoundRecorderController extends CommonController {
    public ComboBox<String> Storage;
    public ComboBox<String> Channel;
    public ComboBox<String> RecordingLevel;
    public ComboBox<String> SamplingRate;
    public ComboBox<String> SoundType;
    public TextField SR_fileName;
    public CheckBox SR_overWrite;
    public ComboBox<String> SR_recordingTime;
    private PropertyTableRow ClaimedRow;
    private SoundRecorder TheSoundRecorder;
    private PropertyTableRow CapStorageRow;
    private PropertyTableRow CapChannelRow;
    private PropertyTableRow CapSamplingRateRow;
    private PropertyTableRow CapSoundTypeRow;
    private PropertyTableRow CapRecordingLevelRow;
    private PropertyTableRow ChannelRow;
    private PropertyTableRow ChannelListRow;
    private PropertyTableRow RecordingLevelRow;
    private PropertyTableRow SamplingRateRow;
    private PropertyTableRow SamplingRateListRow;
    private PropertyTableRow SoundTypeRow;
    private PropertyTableRow SoundTypeListRow;
    private PropertyTableRow StorageRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheSoundRecorder = (SoundRecorder) Control;
        TheSoundRecorder.addDirectIOListener(this);
        TheSoundRecorder.addStatusUpdateListener(this);
        TheSoundRecorder.addErrorListener(this);
        TheSoundRecorder.addDataListener(this);
        Properties.getItems().add(ClaimedRow = new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(new PropertyTableRow("CapAssociatedHardTotalsDevice", ""));
        Properties.getItems().add(new PropertyTableRow("CapMultiPlay", ""));
        Properties.getItems().add(new PropertyTableRow("CapSoundTypeList", ""));
        Properties.getItems().add(CapStorageRow = new PropertyTableRow("CapStorage", "", new CapStorageValues()));
        Properties.getItems().add(CapChannelRow = new PropertyTableRow("CapChannel", ""));
        Properties.getItems().add(CapSamplingRateRow = new PropertyTableRow("CapSamplingRate", ""));
        Properties.getItems().add(CapSoundTypeRow = new PropertyTableRow("CapSoundType", ""));
        Properties.getItems().add(CapRecordingLevelRow = new PropertyTableRow("CapRecordingLevel", ""));
        Properties.getItems().add(ChannelRow = new PropertyTableRow("Channel", ""));
        Properties.getItems().add(ChannelListRow = new PropertyTableRow("ChannelList", ""));
        Properties.getItems().add(RecordingLevelRow = new PropertyTableRow("RecordingLevel", ""));
        Properties.getItems().add(new PropertyTableRow("RemainingRecordingTimeInSec", ""));
        Properties.getItems().add(SamplingRateRow = new PropertyTableRow("SamplingRate", ""));
        Properties.getItems().add(SamplingRateListRow = new PropertyTableRow("SamplingRateList", ""));
        Properties.getItems().add(SoundTypeRow = new PropertyTableRow("SoundType", ""));
        Properties.getItems().add(SoundTypeListRow = new PropertyTableRow("SoundTypeList", ""));
        Properties.getItems().add(StorageRow = new PropertyTableRow("Storage", "", new StorageValues()));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
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
        SR_recordingTime.getItems().clear();
        SR_recordingTime.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            if (ClaimedRow.getValue().toLowerCase().equals("true")) {
                if (ChannelListRow.getValue().length() > 0 && Channel.getItems().size() == 0) {
                    String[] channels = ChannelListRow.getValue().split(",");
                    for (String channel : channels) {
                        if (CapChannelRow.getValue().toLowerCase().equals("true") || channel.equals(ChannelRow.getValue()))
                            Channel.getItems().add(channel);
                    }
                }
                if (CapRecordingLevelRow.getValue().toLowerCase().equals("true") && Channel.getItems().size() == 0) {
                    for (int i = 0; i <= 100; i++)
                        RecordingLevel.getItems().add(Integer.toString(i));
                }
                if (SamplingRateListRow.getValue().length() > 0 && SamplingRate.getItems().size() == 0) {
                    String[] samplingRates = SamplingRateListRow.getValue().split(",");
                    for (String samplingRate : samplingRates) {
                        if (CapSamplingRateRow.getValue().toLowerCase().equals("true") || samplingRate.equals(SamplingRateRow.getValue()))
                            SamplingRate.getItems().add(samplingRate);
                    }
                }
                if (SoundTypeListRow.getValue().length() > 0 && SoundType.getItems().size() == 0) {
                    String[] soundTypes = SoundTypeListRow.getValue().split(",");
                    for (String soundType : soundTypes) {
                        if (CapSoundTypeRow.getValue().toLowerCase().equals("true") || soundType.equals(SoundTypeRow.getValue()))
                            SoundType.getItems().add(soundType);
                    }
                }
                Integer cst = CapStorageRow.getValueConverter().getInteger(CapStorageRow.getValue());
                Storage.getItems().clear();
                if (cst != null) {
                    String value;
                    if (cst == SoundRecorderConst.SREC_CST_HOST_ONLY) {
                        Storage.getItems().add(value = StorageRow.getValueConverter().getSymbol(SoundRecorderConst.SREC_ST_HOST));
                        if (value.equals(StorageRow.getValue()))
                            Storage.setValue(value);
                    } else if (cst == SoundRecorderConst.SREC_CST_HARDTOTALS_ONLY) {
                        Storage.getItems().add(value = StorageRow.getValueConverter().getSymbol(SoundRecorderConst.SREC_ST_HARDTOTALS));
                        if (value.equals(StorageRow.getValue()))
                            Storage.setValue(value);
                    } else if (cst == SoundRecorderConst.SREC_CST_ALL) {
                        Object[] o = StorageRow.getValueConverter().ValueList;
                        for (int i = 1; i < o.length; i += 2) {
                            Storage.getItems().add(o[i].toString());
                            if (StorageRow.getValue().equals(o[i].toString()))
                                Storage.setValue(StorageRow.getValue());
                        }
                    }
                }
            } else {
                Channel.getItems().clear();
                RecordingLevel.getItems().clear();
                SamplingRate.getItems().clear();
                SoundType.getItems().clear();
                Storage.getItems().clear();
            }
            if (!ChannelRow.getValue().equals(Channel.getValue()))
                Channel.setValue(ChannelRow.getValue());
            if (!RecordingLevelRow.getValue().equals(RecordingLevel.getValue()))
                RecordingLevel.setValue(RecordingLevelRow.getValue());
            if (!SamplingRateRow.getValue().equals(SamplingRate.getValue()))
                SamplingRate.setValue(SamplingRateRow.getValue());
            if (!SoundTypeRow.getValue().equals(SoundType.getValue()))
                SoundType.setValue(SoundTypeRow.getValue());
            if (!StorageRow.getValue().equals(Storage.getValue()))
                Storage.setValue(StorageRow.getValue());
            InUpdateGui = false;
        }
    }
   public void setStorage(ActionEvent actionEvent) {
         if (!InUpdateGui && !StorageRow.getValue().equals(Storage.getValue())) {
            try {
                TheSoundRecorder.setStorage(StorageRow.getValueConverter().getInteger(Storage.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
   }

    public void setChannel(ActionEvent actionEvent) {
        if (!InUpdateGui && !ChannelRow.getValue().equals(Channel.getValue())) {
            try {
                TheSoundRecorder.setChannel(Channel.getValue());
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setRecordingLevel(ActionEvent actionEvent) {
        if (!InUpdateGui && !RecordingLevelRow.getValue().equals(RecordingLevel.getValue())) {
            try {
                TheSoundRecorder.setRecordingLevel(Integer.parseInt(RecordingLevel.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setSamplingRate(ActionEvent actionEvent) {
        if (!InUpdateGui && !SamplingRateRow.getValue().equals(SamplingRate.getValue())) {
            try {
                TheSoundRecorder.setSamplingRate(SamplingRate.getValue());
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setSoundType(ActionEvent actionEvent) {
        if (!InUpdateGui && !SoundTypeRow.getValue().equals(SoundType.getValue())) {
            try {
                TheSoundRecorder.setSoundType(SoundType.getValue());
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    class StartRecording extends MethodProcessor {
        private final String FileName;
        private final boolean Overwrite;
        private final int RecordingTime;

        StartRecording(String fileName, boolean overwrite, int recordingTime) {
            super("StartRecording");
            FileName = fileName;
            Overwrite = overwrite;
            RecordingTime = recordingTime;
        }

        @Override
        void runIt() throws JposException {
            TheSoundRecorder.startRecording(FileName, Overwrite, RecordingTime);
        }
    }

    public void startRecording(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer recordingTime = new IntValues().getInteger(SR_recordingTime.getValue());
        String filename = SR_fileName.getText();
        boolean overwrite = SR_overWrite.isSelected();
        if (!invalid(filename, "fileName") && !invalid(recordingTime, "recordingTime"))
            new StartRecording(filename, overwrite, recordingTime).start();
    }

    class StopRecording extends MethodProcessor {
        StopRecording() {
            super("StopRecording");
        }

        @Override
        void runIt() throws JposException {
            TheSoundRecorder.stopRecording();
        }
    }

    public void stopRecording(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new StopRecording().start();
    }

    private class CapStorageValues extends Values {
         CapStorageValues() {
            ValueList = new Object[]{
                    SoundRecorderConst.SREC_CST_HOST_ONLY, "CST_HOST_ONLY",
                    SoundRecorderConst.SREC_CST_HARDTOTALS_ONLY, "CST_HARDTOTALS_ONLY",
                    SoundRecorderConst.SREC_CST_ALL, "CST_ALL"
            };
        }
   }

    private class StorageValues extends Values {
        StorageValues() {
            ValueList = new Object[]{
                    SoundRecorderConst.SREC_ST_HOST, "ST_HOST",
                    SoundRecorderConst.SREC_ST_HARDTOTALS, "ST_HARDTOTALS",
                    SoundRecorderConst.SREC_ST_HOST_HARDTOTALS, "ST_HOST_HARDTOTALS"
            };
        }
    }
}
