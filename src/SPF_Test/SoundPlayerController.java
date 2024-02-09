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
import jpos.JposException;
import jpos.SoundPlayer;
import jpos.SoundPlayerConst;

import java.net.URL;
import java.util.ResourceBundle;

public class SoundPlayerController extends CommonController {
    public ComboBox<String> Storage;
    public ComboBox<String> Volume;
    public ComboBox<String> PA_fileName;
    public CheckBox PA_loop;
    public ComboBox<String> SS_outputID;

    private SoundPlayer TheSoundPlayer;
    private PropertyTableRow CapStorageRow;
    private PropertyTableRow CapVolumeRow;
    private PropertyTableRow DeviceSoundListRow;
    private PropertyTableRow OutputIDListRow;
    private PropertyTableRow StorageRow;
    private PropertyTableRow VolumeRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheSoundPlayer = (SoundPlayer) Control;
        TheSoundPlayer.addDirectIOListener(this);
        TheSoundPlayer.addStatusUpdateListener(this);
        TheSoundPlayer.addErrorListener(this);
        TheSoundPlayer.addOutputCompleteListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(new PropertyTableRow("CapAssociatedHardTotalsDevice", ""));
        Properties.getItems().add(new PropertyTableRow("CapMultiPlay", ""));
        Properties.getItems().add(new PropertyTableRow("CapSoundTypeList", ""));
        Properties.getItems().add(CapStorageRow = new PropertyTableRow("CapStorage", "", new CapStorageValues()));
        Properties.getItems().add(CapVolumeRow = new PropertyTableRow("CapVolume", ""));
        Properties.getItems().add(DeviceSoundListRow = new PropertyTableRow("DeviceSoundList", ""));
        Properties.getItems().add(OutputIDListRow = new PropertyTableRow("OutputIDList", ""));
        Properties.getItems().add(StorageRow = new PropertyTableRow("Storage", "", new StorageValues()));
        Properties.getItems().add(VolumeRow = new PropertyTableRow("Volume", ""));
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
        updateGui();
    }

    @Override
    void updateGui() {
        String ids = OutputIDListRow.getValue();
        String dsl = DeviceSoundListRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            if (DeviceEnabled.isSelected()) {
                if (CapVolumeRow.getValue().length() == 0)
                    Volume.getItems().clear();
                else if (CapVolumeRow.getValue().toLowerCase().equals("true")) {
                    if (Volume.getItems().size() != 100) {
                        Volume.getItems().clear();
                        for (int i = 0; i <= 100; i++) {
                            Volume.getItems().add(Integer.toString(i));
                            if (VolumeRow.getValue().equals(Integer.toString(i)))
                                Volume.setValue(VolumeRow.getValue());
                        }
                    }
                } else if (Volume.getItems().size() != 1) {
                    Volume.getItems().clear();
                    Volume.getItems().add(VolumeRow.getValue());
                    Volume.setValue(VolumeRow.getValue());
                }
                Integer cst = CapStorageRow.getValueConverter().getInteger(CapStorageRow.getValue());
                Storage.getItems().clear();
                if (cst != null) {
                    String value;
                    if (cst == SoundPlayerConst.SPLY_CST_HOST_ONLY) {
                        Storage.getItems().add(value = StorageRow.getValueConverter().getSymbol(SoundPlayerConst.SPLY_ST_HOST));
                        if (value.equals(StorageRow.getValue()))
                            Storage.setValue(value);
                    } else if (cst == SoundPlayerConst.SPLY_CST_HARDTOTALS_ONLY) {
                        Storage.getItems().add(value = StorageRow.getValueConverter().getSymbol(SoundPlayerConst.SPLY_ST_HARDTOTALS));
                        if (value.equals(StorageRow.getValue()))
                            Storage.setValue(value);
                    } else if (cst == SoundPlayerConst.SPLY_CST_ALL) {
                        Object[] o = StorageRow.getValueConverter().ValueList;
                        for (int i = 1; i < o.length; i += 2) {
                            Storage.getItems().add(o[i].toString());
                            if (StorageRow.getValue().equals(o[i].toString()))
                                Storage.setValue(StorageRow.getValue());
                        }
                    }
                }
            } else {
                Storage.getItems().clear();
                Volume.getItems().clear();
            }
            if (!ids.equals(OutputIDListRow.getValue())) {
                String val = SS_outputID.getValue();
                String[] opt = OutputIDListRow.getValue().length() == 0 ? new String[0] : OutputIDListRow.getValue().split(",");
                SS_outputID.getItems().clear();
                for (String s : opt) {
                    SS_outputID.getItems().add(s);
                    if (s.equals(val))
                        SS_outputID.setValue(s);
                }
            }
            if (!dsl.equals(DeviceSoundListRow.getValue())) {
                String val = PA_fileName.getValue();
                String[] opt = DeviceSoundListRow.getValue().length() == 0 ? new String[0] : DeviceSoundListRow.getValue().split(",");
                PA_fileName.getItems().clear();
                for (String s : opt) {
                    PA_fileName.getItems().add(s);
                    if (s.equals(val))
                        PA_fileName.setValue(s);
                }
            }
            InUpdateGui = false;
        }
    }

    public void setStorage(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheSoundPlayer.setStorage(StorageRow.getValueConverter().getInteger(Storage.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setVolume(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheSoundPlayer.setVolume(new IntValues().getInteger(Volume.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    class PlaySound extends MethodProcessor {
        private final String FileName;
        private final boolean Loop;

        PlaySound(String fileName, boolean loop) {
            super("StartRecording");
            FileName = fileName;
            Loop = loop;
        }

        @Override
        void runIt() throws JposException {
            TheSoundPlayer.playSound(FileName, Loop);
        }
    }

    public void playSound(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String filename = PA_fileName.getValue();
        boolean loop = PA_loop.isSelected();
        if (!invalid(filename, "fileName") && !invalid(loop, "loop"))
            new PlaySound(filename, loop).start();
    }

    class StopSound extends MethodProcessor {
        final int OutputID;
        StopSound(int outputID) {
            super("StopSound");
            OutputID = outputID;
        }

        @Override
        void runIt() throws JposException {
            TheSoundPlayer.stopSound(OutputID);
        }
    }

    public void stopSound(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer outputID = new IntValues().getInteger(SS_outputID.getValue());
        if (!invalid(outputID, "outputID"))
            new StopSound(outputID).start();
    }

    private class CapStorageValues extends IntValues {
        CapStorageValues() {
            ValueList = new Object[]{
                    SoundPlayerConst.SPLY_CST_HOST_ONLY, "CST_HOST_ONLY",
                    SoundPlayerConst.SPLY_CST_HARDTOTALS_ONLY, "CST_HARDTOTALS_ONLY",
                    SoundPlayerConst.SPLY_CST_ALL, "CST_ALL"
            };
        }
    }

    private class StorageValues extends IntValues {
        StorageValues() {
            ValueList = new Object[]{
                    SoundPlayerConst.SPLY_ST_HOST, "ST_HOST",
                    SoundPlayerConst.SPLY_ST_HARDTOTALS, "ST_HARDTOTALS",
                    SoundPlayerConst.SPLY_ST_HOST_HARDTOTALS, "ST_HOST_HARDTOTALS"
            };
        }
    }
}
