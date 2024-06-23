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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import jpos.JposException;
import jpos.VoiceRecognition;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

import static jpos.VoiceRecognitionConst.*;

public class VoiceRecognitionControl extends CommonController {
    private VoiceRecognition TheVoiceRecognition;
    private PropertyTableRow CapLanguageRow;
    private PropertyTableRow LanguageListRow;
    private PropertyTableRow HearingResultRow;
    private PropertyTableRow HearingStatusRow;
    private PropertyTableRow HearingDataWordRow;
    private PropertyTableRow HearingDataWordListRow;
    private PropertyTableRow HearingDataPatternRow;

    public ComboBox<String> MethodName;
    public ComboBox<String> Language;
    public AnchorPane WordListLine;
    public AnchorPane PatternListLine;
    public TextField WordList;
    public TextField PatternList;
    public Label HearingStatus;
    public Label HearingResult;
    public Label HearingDataWord;
    public Label HearingDataWordList;
    public Label HearingDataPattern;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheVoiceRecognition = (VoiceRecognition) Control;
        TheVoiceRecognition.addDataListener(this);
        TheVoiceRecognition.addDirectIOListener(this);
        TheVoiceRecognition.addErrorListener(this);
        TheVoiceRecognition.addStatusUpdateListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(CapLanguageRow = new PropertyTableRow("CapLanguage", ""));
        Properties.getItems().add(LanguageListRow = new PropertyTableRow("LanguageList", ""));
        Properties.getItems().add(HearingResultRow = new PropertyTableRow("HearingResult", "", new HearingResultValues()));
        Properties.getItems().add(HearingStatusRow = new PropertyTableRow("HearingStatus", "", new HearingStatusValues()));
        Properties.getItems().add(HearingDataWordRow = new PropertyTableRow("HearingDataWord", ""));
        Properties.getItems().add(HearingDataWordListRow = new PropertyTableRow("HearingDataWordList", ""));
        Properties.getItems().add(HearingDataPatternRow = new PropertyTableRow("HearingDataPattern", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        for (String method : Methods) {
            MethodName.getItems().add(method);
        }
        MethodName.setValue(Methods[0]);
    }

    @Override
    void updateGui() {
        String languages = LanguageListRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            if (!languages.equals(LanguageListRow.getValue())) {
                String[] languageList = LanguageListRow.getValue().split(",");
                String current = Language.getValue();
                Language.getItems().clear();
                for (String language : languageList) {
                    if (language.length() > 0) {
                        Language.getItems().add(language);
                        if (language.equals(current))
                            Language.setValue(current);
                    }
                }
            }
            HearingResult.setText(HearingResultRow.getValue());
            HearingStatus.setText(HearingStatusRow.getValue());
            HearingDataWord.setText(HearingDataWordRow.getValue());
            HearingDataWordList.setText(HearingDataWordListRow.getValue());
            HearingDataPattern.setText(HearingDataPatternRow.getValue());
            InUpdateGui = false;
        }
    }


    public void methodSelection(ActionEvent ignored) {
        for (int i = 0; i < Methods.length; i++) {
            if (Methods[i].equals(MethodName.getValue())) {
                if ((i + 1) % 4 < 2) {
                    WordListLine.setVisible(false);
                    PatternListLine.setVisible(false);
                } else if (i == 2) {
                    WordListLine.setVisible(true);
                    PatternListLine.setVisible(false);
                } else {
                    WordListLine.setVisible(true);
                    PatternListLine.setVisible(true);
                }
                break;
            }
        }
    }

    class StopHearingHandler extends MethodProcessor {
        /**
         * Constructor.
         *
         * @param method Method name.
         */
        StopHearingHandler() {
            super("StopHearing");
        }

        @Override
        void runIt() throws JposException {
            TheVoiceRecognition.stopHearing();
        }
    }
    public void stopHearing(ActionEvent ignored) {
        if (isMethodRunning())
            return;
        new StopHearingHandler().start();
    }

    private String[] Methods = {
            "StartHearingFree",
            "StartHearingSentence",
            "StartHearingWord",
            "StartHearingYesNo"
    };

    public void callIt(ActionEvent ignored) {
        if (!InUpdateGui) {
            for (int what = 0; what < Methods.length; what++) {
                if (Methods[what].equals(MethodName.getValue())) {
                    try {
                        switch (what) {
                            case 0:
                                TheVoiceRecognition.startHearingFree(Language.getValue());
                                break;
                            case 1:
                                TheVoiceRecognition.startHearingSentence(Language.getValue(), WordList.getText(), PatternList.getText());
                                break;
                            case 2:
                                TheVoiceRecognition.startHearingWord(Language.getValue(), WordList.getText());
                                break;
                            case 3:
                                TheVoiceRecognition.startHearingYesNo(Language.getValue());
                        }
                    }
                    catch (JposException error) {
                        getFullErrorMessageAndPrintTrace(error);
                    }
                    break;
                }
            }
        }
    }

    private class HearingResultValues extends IntValues {
        HearingResultValues() {
            ValueList = new Object[]{
                    VRCG_HRESULT_YESNO_YES, "HRESULT_YESNO_YES",
                    VRCG_HRESULT_YESNO_NO, "HRESULT_YESNO_NO",
                    VRCG_HRESULT_YESNO_CANCEL, "HRESULT_YESNO_CANCEL",
                    VRCG_HRESULT_WORD, "HRESULT_WORD",
                    VRCG_HRESULT_SENTENCE, "HRESULT_SENTENCE",
                    VRCG_HRESULT_FREE, "HRESULT_FREE"
            };
        }
    }

    private class HearingStatusValues extends IntValues {
        HearingStatusValues() {
            ValueList = new Object[]{
                    VRCG_HSTATUS_NONE, "HSTATUS_NONE",
                    VRCG_HSTATUS_FREE, "HSTATUS_FREE",
                    VRCG_HSTATUS_SENTENCE, "HSTATUS_SENTENCE",
                    VRCG_HSTATUS_WORD, "HSTATUS_WORD",
                    VRCG_HSTATUS_YESNO, "HSTATUS_YESNO"
            };
        }
    }
}
