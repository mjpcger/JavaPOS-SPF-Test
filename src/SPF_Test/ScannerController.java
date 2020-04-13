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
 * GUI control for Scanner properties, methods and events.
 */
public class ScannerController extends CommonController {
    public CheckBox DecodeData;
    public Label ScanData;
    public Label ScanDataLabel;
    public Label ScanDataType;
    Scanner TheScanner;
    private PropertyTableRow ScanDataTypeRow;
    private PropertyTableRow ScanDataLabelRow;
    private PropertyTableRow ScanDataRow;
    private PropertyTableRow DecodeDataRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Ascii;
        super.initialize(url, resourceBundle);
        TheScanner = (Scanner) Control;
        TheScanner.addDirectIOListener(this);
        TheScanner.addStatusUpdateListener(this);
        TheScanner.addDataListener(this);
        TheScanner.addErrorListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(DecodeDataRow = new PropertyTableRow("DecodeData", "false"));
        Properties.getItems().add(ScanDataRow = new PropertyTableRow("ScanData", ""));
        Properties.getItems().add(ScanDataLabelRow = new PropertyTableRow("ScanDataLabel", ""));
        Properties.getItems().add(ScanDataTypeRow = new PropertyTableRow("ScanDataType", "", new ScanDataTypeValues()));
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
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            DecodeData.setSelected("true".equals(DecodeDataRow.getValue()));
            ScanData.setText(ScanDataRow.getValue());
            ScanDataLabel.setText(ScanDataLabelRow.getValue());
            ScanDataType.setText(ScanDataLabel.getText() == null || ScanDataLabel.getText().length() == 0
                    ? "" : ScanDataTypeRow.getValue());
            InUpdateGui = false;
        }
    }

    public void setDecodeData(ActionEvent actionEvent) {
        try {
            TheScanner.setDecodeData(DecodeData.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class ScanDataTypeValues extends Values {
        ScanDataTypeValues(){
            ValueList = new Object[]{
                    ScannerConst.SCAN_SDT_AMES, "SDT_AMES",
                    ScannerConst.SCAN_SDT_AusPost, "SDT_AusPost",
                    ScannerConst.SCAN_SDT_AZTEC, "SDT_AZTEC",
                    ScannerConst.SCAN_SDT_CanPost, "SDT_CanPost",
                    ScannerConst.SCAN_SDT_CCA, "SDT_CCA",
                    ScannerConst.SCAN_SDT_CCB, "SDT_CCB",
                    ScannerConst.SCAN_SDT_CCC, "SDT_CCC",
                    ScannerConst.SCAN_SDT_ChinaPost, "SDT_ChinaPost",
                    ScannerConst.SCAN_SDT_Codabar, "SDT_Codabar",
                    ScannerConst.SCAN_SDT_Codablock256, "SDT_Codablock256",
                    ScannerConst.SCAN_SDT_CodablockA, "SDT_CodablockA",
                    ScannerConst.SCAN_SDT_CodablockF, "SDT_CodablockF",
                    ScannerConst.SCAN_SDT_Code11, "SDT_Code11",
                    ScannerConst.SCAN_SDT_Code16k, "SDT_Code16k",
                    ScannerConst.SCAN_SDT_Code32, "SDT_Code32",
                    ScannerConst.SCAN_SDT_Code39, "SDT_Code39",
                    ScannerConst.SCAN_SDT_Code39_CK, "SDT_Code39_CK",
                    ScannerConst.SCAN_SDT_Code49, "SDT_Code49",
                    ScannerConst.SCAN_SDT_Code93, "SDT_Code93",
                    ScannerConst.SCAN_SDT_Code128, "SDT_Code128",
                    ScannerConst.SCAN_SDT_CodeCIP, "SDT_CodeCIP",
                    ScannerConst.SCAN_SDT_DATAMATRIX, "SDT_DATAMATRIX",
                    ScannerConst.SCAN_SDT_DutchKix, "SDT_DutchKix",
                    ScannerConst.SCAN_SDT_EAN8, "SDT_EAN8",
                    ScannerConst.SCAN_SDT_EAN8_S, "SDT_EAN8_S",
                    ScannerConst.SCAN_SDT_EAN13, "SDT_EAN13",
                    ScannerConst.SCAN_SDT_EAN13_S, "SDT_EAN13_S",
                    ScannerConst.SCAN_SDT_EAN128, "SDT_EAN128",
                    ScannerConst.SCAN_SDT_GS1DATABAR, "SDT_GS1DATABAR",
                    ScannerConst.SCAN_SDT_GS1DATABAR_E, "SDT_GS1DATABAR_E",
                    ScannerConst.SCAN_SDT_GS1DATABAR_TYPE2, "SDT_GS1DATABAR_TYPE2",
                    ScannerConst.SCAN_SDT_GS1DATAMATRIX, "SDT_GS1DATAMATRIX",
                    ScannerConst.SCAN_SDT_GS1QRCODE, "SDT_GS1QRCODE",
                    ScannerConst.SCAN_SDT_HANXIN, "SDT_HANXIN",
                    ScannerConst.SCAN_SDT_InfoMail, "SDT_InfoMail",
                    ScannerConst.SCAN_SDT_ISBT128, "SDT_ISBT128",
                    ScannerConst.SCAN_SDT_ITF, "SDT_ITF",
                    ScannerConst.SCAN_SDT_ITF_CK, "SDT_ITF_CK",
                    ScannerConst.SCAN_SDT_JAN8, "SDT_JAN8",
                    ScannerConst.SCAN_SDT_JAN13, "SDT_JAN13",
                    ScannerConst.SCAN_SDT_JapanPost, "SDT_JapanPost",
                    ScannerConst.SCAN_SDT_KoreanPost, "SDT_KoreanPost",
                    ScannerConst.SCAN_SDT_MAXICODE, "SDT_MAXICODE",
                    ScannerConst.SCAN_SDT_MSI, "SDT_MSI",
                    ScannerConst.SCAN_SDT_OCRA, "SDT_OCRA",
                    ScannerConst.SCAN_SDT_OCRB, "SDT_OCRB",
                    ScannerConst.SCAN_SDT_OTHER, "SDT_OTHER",
                    ScannerConst.SCAN_SDT_PDF417, "SDT_PDF417",
                    ScannerConst.SCAN_SDT_PLESSEY, "SDT_PLESSEY",
                    ScannerConst.SCAN_SDT_PostNet, "SDT_PostNet",
                    ScannerConst.SCAN_SDT_QRCODE, "SDT_QRCODE",
                    ScannerConst.SCAN_SDT_RSS14, "SDT_RSS14",
                    ScannerConst.SCAN_SDT_RSS_EXPANDED, "SDT_RSS_EXPANDED",
                    ScannerConst.SCAN_SDT_SwedenPost, "SDT_SwedenPost",
                    ScannerConst.SCAN_SDT_TELEPEN, "SDT_TELEPEN",
                    ScannerConst.SCAN_SDT_TF, "SDT_TF",
                    ScannerConst.SCAN_SDT_TFMAT, "SDT_TFMAT",
                    ScannerConst.SCAN_SDT_TLC39, "SDT_TLC39",
                    ScannerConst.SCAN_SDT_TRIOPTIC39, "SDT_TRIOPTIC39",
                    ScannerConst.SCAN_SDT_UkPost, "SDT_UkPost",
                    ScannerConst.SCAN_SDT_UNKNOWN, "SDT_UNKNOWN",
                    ScannerConst.SCAN_SDT_UPCA, "SDT_UPCA",
                    ScannerConst.SCAN_SDT_UPCA_S, "SDT_UPCA_S",
                    ScannerConst.SCAN_SDT_UPCD1, "SDT_UPCD1",
                    ScannerConst.SCAN_SDT_UPCD2, "SDT_UPCD2",
                    ScannerConst.SCAN_SDT_UPCD3, "SDT_UPCD3",
                    ScannerConst.SCAN_SDT_UPCD4, "SDT_UPCD4",
                    ScannerConst.SCAN_SDT_UPCD5, "SDT_UPCD5",
                    ScannerConst.SCAN_SDT_UPCE, "SDT_UPCE",
                    ScannerConst.SCAN_SDT_UPCE_S, "SDT_UPCE_S",
                    ScannerConst.SCAN_SDT_UPDF417, "SDT_UPDF417",
                    ScannerConst.SCAN_SDT_UQRCODE, "SDT_UQRCODE",
                    ScannerConst.SCAN_SDT_UsIntelligent, "SDT_UsIntelligent",
                    ScannerConst.SCAN_SDT_UsPlanet, "SDT_UsPlanet"
            };
        }
    }
}
