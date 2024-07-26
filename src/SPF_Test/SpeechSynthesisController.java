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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jpos.JposException;
import jpos.SpeechSynthesis;

import java.net.URL;
import java.util.ResourceBundle;

import static jpos.JposConst.JPOS_S_CLOSED;

public class SpeechSynthesisController extends CommonController {
    public ComboBox<String> Language;
    public ComboBox<String> Voice;
    public TextField Pitch;
    public TextField Speed;
    public TextField Volume;
    public TextArea SP_text;
    public ComboBox<String> SP_id;
    private SpeechSynthesis TheSpeechSynthesis;
    private PropertyTableRow LanguageRow;
    private PropertyTableRow LanguageListRow;
    private PropertyTableRow OutputIDListRow;
    private PropertyTableRow PitchRow;
    private PropertyTableRow SpeedRow;
    private PropertyTableRow VoiceRow;
    private PropertyTableRow VoiceListRow;
    private PropertyTableRow VolumeRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheSpeechSynthesis = (SpeechSynthesis) Control;
        TheSpeechSynthesis.addDirectIOListener(this);
        TheSpeechSynthesis.addStatusUpdateListener(this);
        TheSpeechSynthesis.addErrorListener(this);
        TheSpeechSynthesis.addOutputCompleteListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(new PropertyTableRow("CapLanguage", ""));
        Properties.getItems().add(new PropertyTableRow("CapPitch", ""));
        Properties.getItems().add(new PropertyTableRow("CapSpeed", ""));
        Properties.getItems().add(new PropertyTableRow("CapVoice", ""));
        Properties.getItems().add(new PropertyTableRow("CapVolume", ""));
        Properties.getItems().add(LanguageRow = new PropertyTableRow("Language", ""));
        Properties.getItems().add(LanguageListRow = new PropertyTableRow("LanguageList", ""));
        Properties.getItems().add(OutputIDListRow = new PropertyTableRow("OutputIDList", ""));
        Properties.getItems().add(PitchRow = new PropertyTableRow("Pitch", ""));
        Properties.getItems().add(SpeedRow = new PropertyTableRow("Speed", ""));
        Properties.getItems().add(VoiceRow = new PropertyTableRow("Voice", ""));
        Properties.getItems().add(VoiceListRow = new PropertyTableRow("VoiceList", ""));
        Properties.getItems().add(VolumeRow = new PropertyTableRow("Volume", ""));
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
        setPropertyOnFocusLost(Pitch, "Pitch");
        setPropertyOnFocusLost(Speed, "Speed");
        setPropertyOnFocusLost(Volume, "Volume");
        updateGui();
    }

    @Override
    public void updateGui() {
        String prevLang = LanguageListRow.getValue();
        String prevVoice = VoiceListRow.getValue();
        String prevID = OutputIDListRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            if (!prevLang.equals(LanguageListRow.getValue())) {
                Language.getItems().clear();
                if (LanguageListRow.getValue().length() > 0) {
                    String[] langs = LanguageListRow.getValue().split(",");
                    for (String lang : langs) {
                        Language.getItems().add(lang);
                        if (LanguageRow.getValue().equals(lang))
                            Language.setValue(lang);
                    }
                }
            }
            if (!prevVoice.equals(VoiceListRow.getValue())) {
                Voice.getItems().clear();
                if (VoiceListRow.getValue().length() > 0) {
                    String[] voices = VoiceListRow.getValue().split(",");
                    for (String voice : voices) {
                        Voice.getItems().add(voice);
                        if (VoiceRow.getValue().equals(voice))
                            Voice.setValue(voice);
                    }
                }
            }
            if (!prevID.equals(OutputIDListRow.getValue())) {
                SP_id.getItems().clear();
                if (OutputIDListRow.getValue().length() > 0) {
                    String[] ids = OutputIDListRow.getValue().split(",");
                    for (String id : ids)
                        SP_id.getItems().add(id);
                    SP_id.setValue(ids[0]);
                }
            }
            if (!VoiceRow.getValue().equals(Voice.getValue()))
                Voice.setValue(VoiceRow.getValue());
            if (!LanguageRow.getValue().equals(Language.getValue()))
                Language.setValue(LanguageRow.getValue());
            if (!PitchRow.getValue().equals(Pitch.getText()))
                Pitch.setText(PitchRow.getValue());
            if (!SpeedRow.getValue().equals(Speed.getText()))
                Speed.setText(SpeedRow.getValue());
            if (!VolumeRow.getValue().equals(Volume.getText()))
                Volume.setText(VolumeRow.getValue());
            InUpdateGui = false;
        }

    }

    public void setLanguage(ActionEvent actionEvent) {
        if (!InUpdateGui && TheSpeechSynthesis.getState() != JPOS_S_CLOSED) {
            try {
                TheSpeechSynthesis.setLanguage(Language.getValue());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setVoice(ActionEvent actionEvent) {
        if (!InUpdateGui && TheSpeechSynthesis.getState() != JPOS_S_CLOSED) {
            try {
                TheSpeechSynthesis.setVoice(Voice.getValue());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setPitch(ActionEvent actionEvent) {
        if (!InUpdateGui && TheSpeechSynthesis.getState() != JPOS_S_CLOSED) {
            try {
                TheSpeechSynthesis.setPitch(new IntValues().getInteger(Pitch.getText()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setSpeed(ActionEvent actionEvent) {
        if (!InUpdateGui && TheSpeechSynthesis.getState() != JPOS_S_CLOSED) {
            try {
                TheSpeechSynthesis.setSpeed(new IntValues().getInteger(Speed.getText()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setVolume(ActionEvent actionEvent) {
        if (!InUpdateGui && TheSpeechSynthesis.getState() != JPOS_S_CLOSED) {
            try {
                TheSpeechSynthesis.setVolume(new IntValues().getInteger(Volume.getText()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    class SpeakHandler extends MethodProcessor {
        final String Text;
        SpeakHandler(String text) {
            super("Speak");
            Text = text;
        }

        @Override
        void runIt() throws JposException {
            TheSpeechSynthesis.speak(Text);
        }
    }

    public void speak(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String text = SP_text.getText();
        if (!invalid(text, "text"))
            new SpeakHandler(text).start();
    }

    class SpeakImmediateHandler extends MethodProcessor {
        final String Text;
        SpeakImmediateHandler(String text) {
            super("SpeakImmediate");
            Text = text;
        }

        @Override
        void runIt() throws JposException {
            TheSpeechSynthesis.speakImmediate(Text);
        }
    }

    public void speakImmediate(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String text = SP_text.getText();
        if (!invalid(text, "text"))
            new SpeakImmediateHandler(text).start();
    }

    class StopCurrentSpeakingHandler extends MethodProcessor {
        StopCurrentSpeakingHandler() {
            super("StopCurrentSpeaking");
        }

        @Override
        void runIt() throws JposException {
            TheSpeechSynthesis.stopCurrentSpeaking();
        }
    }

    public void stopCurrentSpeaking(ActionEvent actionEvent) {
        if (!isMethodRunning())
            new StopCurrentSpeakingHandler().start();
    }

    class StopSpeakingHandler extends MethodProcessor {
        final int OutputID;
        StopSpeakingHandler(int id) {
            super("StopSpeaking");
            OutputID = id;
        }

        @Override
        void runIt() throws JposException {
            TheSpeechSynthesis.stopSpeaking(OutputID);
        }
    }

    public void stopSpeaking(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer outputID = new IntValues().getInteger(SP_id.getValue());
        if (!invalid(outputID, "outputID"))
            new StopSpeakingHandler(outputID).start();
    }
}
