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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import jpos.IndividualRecognition;
import jpos.JposException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import static java.nio.charset.StandardCharsets.UTF_8;

public class IndividualRecognitionController extends CommonController {
    private IndividualRecognition TheIndividualRecognition;
    private PropertyTableRow IndividualIDsRow;
    private PropertyTableRow IndividualRecognitionFilterRow;
    private PropertyTableRow IndividualRecognitionInformationRow;
    private PropertyTableRow CapIndividualListRow;
    public TextArea CapIndividualList;
    public TextField IndividualIDs;
    public TextArea IndividualRecognitionFilter;
    public TextArea IndividualRecognitionInformation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheIndividualRecognition = (IndividualRecognition) Control;
        TheIndividualRecognition.addDataListener(this);
        TheIndividualRecognition.addDirectIOListener(this);
        TheIndividualRecognition.addErrorListener(this);
        TheIndividualRecognition.addStatusUpdateListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(CapIndividualListRow = new PropertyTableRow("CapIndividualList", ""));
        Properties.getItems().add(IndividualIDsRow = new PropertyTableRow("IndividualIDs", ""));
        Properties.getItems().add(IndividualRecognitionFilterRow = new PropertyTableRow("IndividualRecognitionFilter", ""));
        Properties.getItems().add(IndividualRecognitionInformationRow = new PropertyTableRow("IndividualRecognitionInformation", ""));
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
    }

    @Override
    void updateGui() {
        String capIL = CapIndividualListRow.getValue();
        String iIDs = IndividualIDsRow.getValue();
        String iRF = IndividualRecognitionFilterRow.getValue();
        String iRI = IndividualRecognitionInformationRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            if (!capIL.equals(CapIndividualListRow.getValue())) {
                String[] list = CapIndividualListRow.getValue().split(",");
                StringBuilder tx = new StringBuilder();
                for (String entry : list)
                    tx.append('\n').append(entry);
                CapIndividualList.setText(tx.substring(1));
            }
            IndividualIDs.setDisable(false);
            IndividualIDs.setText(IndividualIDsRow.getValue());
            IndividualIDs.setDisable(true);
            IndividualRecognitionInformation.setText(IndividualRecognitionInformationRow.getValue());
            IndividualRecognitionFilter.setText(IndividualRecognitionFilterRow.getValue());
            InUpdateGui = false;
        }
    }

    public void saveIndividualRecognitionInformation(ActionEvent actionEvent) {
        if (!InUpdateGui && IndividualRecognitionInformationRow.getValue().length() > 0) {
        FileChooser fc = new FileChooser();
            fc.setTitle("Select File Name for Individual Recognition Information");
            File target = fc.showSaveDialog(IndividualRecognitionInformation.getScene().getWindow());
            if (target != null) {
                try (FileOutputStream fs = new FileOutputStream(target, false)) {
                    fs.write(IndividualRecognitionInformationRow.getValue().getBytes(UTF_8));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
            }
        }
    }

    public void saveIndividualRecognitionFilter(ActionEvent actionEvent) {
        if (!InUpdateGui && IndividualRecognitionFilterRow.getValue().length() > 0) {
            FileChooser fc = new FileChooser();
            fc.setTitle("Select File Name for Individual Recognition Filter");
            File target = fc.showSaveDialog(IndividualRecognitionFilter.getScene().getWindow());
            if (target != null) {
                try (FileOutputStream fs = new FileOutputStream(target, false)) {
                    fs.write(IndividualRecognitionFilterRow.getValue().getBytes(UTF_8));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
            }
        }
    }

    public void loadIndividualRecognitionFilter(ActionEvent actionEvent) {
        if (!InUpdateGui && DeviceEnabled.isSelected()) {
            FileChooser fc = new FileChooser();
            fc.setTitle("Select File Name for Individual Recognition Information");
            File target = fc.showOpenDialog(IndividualRecognitionFilter.getScene().getWindow());
            if (target != null) {
                try (FileInputStream fs = new FileInputStream(target)) {
                    byte[] data = new byte[(int)target.length()];
                    int start = 0;
                    int len;
                    while (start < data.length && (len = fs.read(data, start, data.length - start)) > 0)
                        start += len;
                    if (start < data.length)
                        throw new IOException("Could Read Only " + start + " of " + data.length + " bytes");
                    TheIndividualRecognition.setIndividualRecognitionFilter(new String(data, UTF_8));
                    updateGui();
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
            }
        }
    }
}
