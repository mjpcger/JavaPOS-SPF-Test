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
import jpos.*;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for ToneIndicator properties, methods and events.
 */
public class ToneIndicatorController extends CommonController {
    public ComboBox<String> S_numberOfCycles;
    public TextField S_interSoundWait;
    public TextField InterToneWait;
    public ComboBox<String> MelodyType;
    public TextField MelodyVolume;
    public TextField Tone1Duration;
    public TextField Tone1Pitch;
    public TextField Tone1Volume;
    public TextField Tone2Duration;
    public TextField Tone2Pitch;
    public TextField Tone2Volume;
    private ToneIndicator TheTone;
    private PropertyTableRow InterToneWaitRow;
    private PropertyTableRow MelodyTypeRow;
    private PropertyTableRow MelodyVolumeRow;
    private PropertyTableRow Tone1DurationRow;
    private PropertyTableRow Tone1PitchRow;
    private PropertyTableRow Tone1VolumeRow;
    private PropertyTableRow Tone2DurationRow;
    private PropertyTableRow Tone2PitchRow;
    private PropertyTableRow Tone2VolumeRow;
    private PropertyTableRow CapMelodyRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Ascii;
        super.initialize(url, resourceBundle);
        TheTone = (ToneIndicator) Control;
        TheTone.addDirectIOListener(this);
        TheTone.addStatusUpdateListener(this);
        TheTone.addOutputCompleteListener(this);
        TheTone.addErrorListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(CapMelodyRow = new PropertyTableRow("CapMelody", ""));
        Properties.getItems().add(new PropertyTableRow("CapPitch", "false"));
        Properties.getItems().add(new PropertyTableRow("CapVolume", "false"));
        Properties.getItems().add(InterToneWaitRow = new PropertyTableRow("InterToneWait", "0"));
        Properties.getItems().add(MelodyTypeRow = new PropertyTableRow("MelodyType", "", new MelodyTypeValues()));
        Properties.getItems().add(MelodyVolumeRow = new PropertyTableRow("MelodyVolume", "100"));
        Properties.getItems().add(new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(Tone1DurationRow = new PropertyTableRow("Tone1Duration", "0"));
        Properties.getItems().add(Tone1PitchRow = new PropertyTableRow("Tone1Pitch", "0"));
        Properties.getItems().add(Tone1VolumeRow = new PropertyTableRow("Tone1Volume", "100"));
        Properties.getItems().add(Tone2DurationRow = new PropertyTableRow("Tone2Duration", "0"));
        Properties.getItems().add(Tone2PitchRow = new PropertyTableRow("Tone2Pitch", "0"));
        Properties.getItems().add(Tone2VolumeRow = new PropertyTableRow("Tone2Volume", "100"));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        S_numberOfCycles.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        setPropertyOnFocusLost(InterToneWait, "InterToneWait");
        setPropertyOnFocusLost(MelodyVolume, "MelodyVolume");
        setPropertyOnFocusLost(Tone1Duration, "Tone1Duration");
        setPropertyOnFocusLost(Tone1Pitch, "Tone1Pitch");
        setPropertyOnFocusLost(Tone1Volume, "Tone1Volume");
        setPropertyOnFocusLost(Tone2Duration, "Tone2Duration");
        setPropertyOnFocusLost(Tone2Pitch, "Tone2Pitch");
        setPropertyOnFocusLost(Tone2Volume, "Tone2Volume");
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            Values ivals = new IntValues();
            Integer melodyCount;
            InterToneWait.setText(InterToneWaitRow.getValue());
            if (MelodyType.getItems().size() == 0 && (melodyCount = ivals.getInteger(CapMelodyRow.getValue())) != null && melodyCount >= 0) {
                MelodyTypeValues mvals = new MelodyTypeValues();
                for (int i = 0; i <= melodyCount; i++)
                    MelodyType.getItems().add(mvals.getSymbol(i));
            }
            MelodyType.setValue(MelodyTypeRow.getValue());
            MelodyVolume.setText(MelodyVolumeRow.getValue());
            Tone1Duration.setText(Tone1DurationRow.getValue());
            Tone1Pitch.setText(Tone1PitchRow.getValue());
            Tone1Volume.setText(Tone1VolumeRow.getValue());
            Tone2Duration.setText(Tone2DurationRow.getValue());
            Tone2Pitch.setText(Tone2PitchRow.getValue());
            Tone2Volume.setText(Tone2VolumeRow.getValue());
            InUpdateGui = false;
        }
    }

    public void soundImmediate(ActionEvent actionEvent) {
        try {
            TheTone.soundImmediate();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class SoundHandler extends MethodProcessor {
        private final int NumberOfCycles;
        private final int InterSoundWait;

        public SoundHandler(int count, int wait) {
            super("Sound");
            NumberOfCycles = count;
            InterSoundWait = wait;
        }

        @Override
        void runIt() throws JposException {
            TheTone.sound(NumberOfCycles, InterSoundWait);
        }
    }

    public void sound(ActionEvent actionEvent) {
        Integer count = new TimeoutValues().getInteger(S_numberOfCycles.getValue());
        if (count == null) {
            JOptionPane.showMessageDialog(null, "No valid entry for argument numberOfCycles.");
            return;
        }
        Integer wait = new IntValues().getInteger(S_interSoundWait.getText());
        if (wait == null) {
            JOptionPane.showMessageDialog(null, "No valid entry for argument interSoundWait.");
            return;
        }
        new SoundHandler(count, wait).start();
    }

    public void setInterToneWait(ActionEvent actionEvent) {
        try {
            TheTone.setInterToneWait(new IntValues().getInteger(InterToneWait.getText()));
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        } catch (NullPointerException e) {}
        updateGui();
    }

    public void setMelodyType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheTone.setInterToneWait(new MelodyTypeValues().getInteger(MelodyType.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    public void setMelodyVolume(ActionEvent actionEvent) {
        try {
            TheTone.setMelodyVolume(new IntValues().getInteger(MelodyVolume.getText()));
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        } catch (NullPointerException e) {
        }
        updateGui();
    }

    public void setTone1Duration(ActionEvent actionEvent) {
        try {
            TheTone.setTone1Duration(new IntValues().getInteger(Tone1Duration.getText()));
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        } catch (NullPointerException e) {}
        updateGui();
    }

    public void setTone1Pitch(ActionEvent actionEvent) {
        try {
            TheTone.setTone1Pitch(new IntValues().getInteger(Tone1Pitch.getText()));
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        } catch (NullPointerException e) {}
        updateGui();
    }

    public void setTone1Volume(ActionEvent actionEvent) {
        try {
            TheTone.setTone1Volume(new IntValues().getInteger(Tone1Volume.getText()));
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        } catch (NullPointerException e) {}
        updateGui();
    }

    public void setTone2Duration(ActionEvent actionEvent) {
        try {
            TheTone.setTone2Duration(new IntValues().getInteger(Tone2Duration.getText()));
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        } catch (NullPointerException e) {}
        updateGui();
    }

    public void setTone2Pitch(ActionEvent actionEvent) {
        try {
            TheTone.setTone2Pitch(new IntValues().getInteger(Tone2Pitch.getText()));
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        } catch (NullPointerException e) {}
        updateGui();
    }

    public void setTone2Volume(ActionEvent actionEvent) {
        try {
            TheTone.setTone2Volume(new IntValues().getInteger(Tone2Volume.getText()));
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        } catch (NullPointerException e) {}
        updateGui();
    }

    private class MelodyTypeValues extends Values {
        MelodyTypeValues() {
            ValueList = new Object[]{
                    ToneIndicatorConst.TONE_MT_NONE, "MT_NONE",
                    ToneIndicatorConst.TONE_MT_TYPE1, "MT_TYPE1",
                    ToneIndicatorConst.TONE_MT_TYPE2, "MT_TYPE2",
                    ToneIndicatorConst.TONE_MT_TYPE3, "MT_TYPE3",
                    ToneIndicatorConst.TONE_MT_TYPE4, "MT_TYPE4",
                    ToneIndicatorConst.TONE_MT_TYPE5, "MT_TYPE5"
            };
        }
    }
}
