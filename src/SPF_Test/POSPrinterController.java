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
import javafx.scene.control.CheckBox;
import jpos.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for POSPrinter properties, methods and events.
 */
public class POSPrinterController extends CommonController {
    public CheckBox FlagWhenIdle;
    private POSPrinter ThePrinter;
    private PropertyTableRow CartridgeNotifyRow;
    private PropertyTableRow CharacterSetRow;
    private PropertyTableRow CharacterSetListRow;
    private PropertyTableRow CoverOpenRow;
    private PropertyTableRow FlagWhenIdleRow;
    private PropertyTableRow JrnCartridgeStateRow;
    private PropertyTableRow JrnCurrentCartridgeRow;
    private PropertyTableRow JrnEmptyRow;
    private PropertyTableRow JrnLetterQualityRow;
    private PropertyTableRow JrnLineCharsRow;
    private PropertyTableRow JrnLineCharsListRow;
    private PropertyTableRow JrnLineHeightRow;
    private PropertyTableRow JrnLineSpacingRow;
    private PropertyTableRow JrnNearEndRow;
    private PropertyTableRow MapCharacterSetRow;
    private PropertyTableRow MapModeRow;
    private PropertyTableRow PageModeDescriptorRow;
    private PropertyTableRow PageModeHorizontalPositionRow;
    private PropertyTableRow PageModePrintAreaRow;
    private PropertyTableRow PageModePrintDirectionRow;
    private PropertyTableRow PageModeStationRow;
    private PropertyTableRow PageModeVerticalPositionRow;
    private PropertyTableRow RecBarCodeRotationListRow;
    private PropertyTableRow RecBitmapRotationListRow;
    private PropertyTableRow RecCartridgeStateRow;
    private PropertyTableRow RecCurrentCartridgeRow;
    private PropertyTableRow RecEmptyRow;
    private PropertyTableRow RecLetterQualityRow;
    private PropertyTableRow RecLineCharsRow;
    private PropertyTableRow RecLineCharsListRow;
    private PropertyTableRow RecLineHeightRow;
    private PropertyTableRow RecLineSpacingRow;
    private PropertyTableRow RecNearEndRow;
    private PropertyTableRow RotateSpecialRow;
    private PropertyTableRow SlpBarCodeRotationListRow;
    private PropertyTableRow SlpBitmapRotationListRow;
    private PropertyTableRow SlpCartridgeStateRow;
    private PropertyTableRow SlpCurrentCartridgeRow;
    private PropertyTableRow SlpEmptyRow;
    private PropertyTableRow SlpLetterQualityRow;
    private PropertyTableRow SlpLineCharsRow;
    private PropertyTableRow SlpLineCharsListRow;
    private PropertyTableRow SlpLineHeightRow;
    private PropertyTableRow SlpLineSpacingRow;
    private PropertyTableRow SlpNearEndRow;
    private PropertyTableRow SlpPrintSideRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Hexadecimal;
        super.initialize(url, resourceBundle);
        ThePrinter = (POSPrinter) Control;
        ThePrinter.addDirectIOListener(this);
        ThePrinter.addStatusUpdateListener(this);
        ThePrinter.addErrorListener(this);
        ThePrinter.addOutputCompleteListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("CapCharacterSet", "", new CapCharacterSetValues()));
        Properties.getItems().add(new PropertyTableRow("CapConcurrentJrnRec", ""));
        Properties.getItems().add(new PropertyTableRow("CapConcurrentJrnSlp", ""));
        Properties.getItems().add(new PropertyTableRow("CapConcurrentPageMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapConcurrentRecSlp", ""));
        Properties.getItems().add(new PropertyTableRow("CapCoverSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrn2Color", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnBold", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnCartridgeSensor", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapJrnColor", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapJrnDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnDwide", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnDwideDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnItalic", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnNearEndSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnPresent", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnUnderline", ""));
        Properties.getItems().add(new PropertyTableRow("CapMapCharacterSet", ""));
        Properties.getItems().add(new PropertyTableRow("CapRec2Color", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecBarCode", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecBitmap", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecBold", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecCartridgeSensor", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapRecColor", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapRecDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecDwide", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecDwideDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecItalic", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecLeft90", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecMarkFeed", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapRecNearEndSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecPageMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecPapercut", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecPresent", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecRight90", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecRotate180", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecRuledLine", "", new CapRuledLineValues()));
        Properties.getItems().add(new PropertyTableRow("CapRecStamp", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecUnderline", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlp2Color", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpBarCode", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpBitmap", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpBold", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpBothSidesPrint", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpCartridgeSensor", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapSlpColor", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapSlpDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpDwide", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpDwideDhigh", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpFullslip", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpItalic", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpLeft90", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpNearEndSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpPageMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpPresent", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpRight90", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpRotate180", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpRuledLine", "", new CapRuledLineValues()));
        Properties.getItems().add(new PropertyTableRow("CapSlpUnderline", ""));
        Properties.getItems().add(new PropertyTableRow("CapTransaction", ""));
        Properties.getItems().add(CartridgeNotifyRow = new PropertyTableRow("CartridgeNotify", "", new CartridgeNotifyValues()));
        Properties.getItems().add(CharacterSetRow = new PropertyTableRow("CharacterSet", "", new CharacterSetValues()));
        Properties.getItems().add(CharacterSetListRow = new PropertyTableRow("CharacterSetList", ""));
        Properties.getItems().add(CoverOpenRow = new PropertyTableRow("CoverOpen", ""));
        Properties.getItems().add(new PropertyTableRow("ErrorLevel", "", new ErrorLevelValues()));
        Properties.getItems().add(new PropertyTableRow("ErrorStation", "", new ErrorStationValues()));
        Properties.getItems().add(new PropertyTableRow("ErrorString", ""));
        Properties.getItems().add(FlagWhenIdleRow = new PropertyTableRow("FlagWhenIdle", ""));
        Properties.getItems().add(new PropertyTableRow("FontTypefaceList", ""));
        Properties.getItems().add(JrnCartridgeStateRow = new PropertyTableRow("JrnCartridgeState", "", new CartridgeStateValues()));
        Properties.getItems().add(JrnCurrentCartridgeRow= new PropertyTableRow("JrnCurrentCartridge", "", new CurrentCartridgeValues()));
        Properties.getItems().add(JrnEmptyRow = new PropertyTableRow("JrnEmpty", ""));
        Properties.getItems().add(JrnLetterQualityRow = new PropertyTableRow("JrnLetterQuality", ""));
        Properties.getItems().add(JrnLineCharsRow = new PropertyTableRow("JrnLineChars", ""));
        Properties.getItems().add(JrnLineCharsListRow = new PropertyTableRow("JrnLineCharsList", ""));
        Properties.getItems().add(JrnLineHeightRow = new PropertyTableRow("JrnLineHeight", ""));
        Properties.getItems().add(JrnLineSpacingRow = new PropertyTableRow("JrnLineSpacing", ""));
        Properties.getItems().add(new PropertyTableRow("JrnLineWidth", ""));
        Properties.getItems().add(JrnNearEndRow = new PropertyTableRow("JrnNearEnd", ""));
        Properties.getItems().add(MapCharacterSetRow = new PropertyTableRow("MapCharacterSet", ""));
        Properties.getItems().add(MapModeRow = new PropertyTableRow("MapMode", "", new MapModeValues()));
        Properties.getItems().add(new PropertyTableRow("PageModeArea", ""));
        Properties.getItems().add(PageModeDescriptorRow = new PropertyTableRow("PageModeDescriptor", "",new HexValues()));
        Properties.getItems().add(PageModeHorizontalPositionRow = new PropertyTableRow("PageModeHorizontalPosition", ""));
        Properties.getItems().add(PageModePrintAreaRow = new PropertyTableRow("PageModePrintArea", ""));
        Properties.getItems().add(PageModePrintDirectionRow = new PropertyTableRow("PageModePrintDirection", "", new PageModePrintDirectionValues()));
        Properties.getItems().add(PageModeStationRow = new PropertyTableRow("PageModeStation", "", new PageModeStationValues()));
        Properties.getItems().add(PageModeVerticalPositionRow = new PropertyTableRow("PageModeVerticalPosition", ""));
        Properties.getItems().add(RecBarCodeRotationListRow = new PropertyTableRow("RecBarCodeRotationList", ""));
        Properties.getItems().add(RecBitmapRotationListRow = new PropertyTableRow("RecBitmapRotationList", ""));
        Properties.getItems().add(RecCartridgeStateRow = new PropertyTableRow("RecCartridgeState", "", new CartridgeStateValues()));
        Properties.getItems().add(RecCurrentCartridgeRow = new PropertyTableRow("RecCurrentCartridge", "", new CurrentCartridgeValues()));
        Properties.getItems().add(RecEmptyRow = new PropertyTableRow("RecEmpty", ""));
        Properties.getItems().add(RecLetterQualityRow = new PropertyTableRow("RecLetterQuality", ""));
        Properties.getItems().add(RecLineCharsRow = new PropertyTableRow("RecLineChars", ""));
        Properties.getItems().add(RecLineCharsListRow = new PropertyTableRow("RecLineCharsList", ""));
        Properties.getItems().add(RecLineHeightRow = new PropertyTableRow("RecLineHeight", ""));
        Properties.getItems().add(RecLineSpacingRow = new PropertyTableRow("RecLineSpacing", ""));
        Properties.getItems().add(new PropertyTableRow("RecLinesToPaperCut", ""));
        Properties.getItems().add(new PropertyTableRow("RecLineWidth", ""));
        Properties.getItems().add(RecNearEndRow = new PropertyTableRow("RecNearEnd", ""));
        Properties.getItems().add(new PropertyTableRow("RecSidewaysMaxChars", ""));
        Properties.getItems().add(new PropertyTableRow("RecSidewaysMaxLines", ""));
        Properties.getItems().add(RotateSpecialRow = new PropertyTableRow("RotateSpecial", "", new RotateSpecialValues()));
        Properties.getItems().add(SlpBarCodeRotationListRow = new PropertyTableRow("SlpBarCodeRotationList", ""));
        Properties.getItems().add(SlpBitmapRotationListRow = new PropertyTableRow("SlpBitmapRotationList", ""));
        Properties.getItems().add(SlpCartridgeStateRow = new PropertyTableRow("SlpCartridgeState", "", new CartridgeStateValues()));
        Properties.getItems().add(SlpCurrentCartridgeRow = new PropertyTableRow("SlpCurrentCartridge", "", new CurrentCartridgeValues()));
        Properties.getItems().add(SlpEmptyRow = new PropertyTableRow("SlpEmpty", ""));
        Properties.getItems().add(SlpLetterQualityRow = new PropertyTableRow("SlpLetterQuality", ""));
        Properties.getItems().add(SlpLineCharsRow = new PropertyTableRow("SlpLineChars", ""));
        Properties.getItems().add(SlpLineCharsListRow = new PropertyTableRow("SlpLineCharsList", ""));
        Properties.getItems().add(SlpLineHeightRow = new PropertyTableRow("SlpLineHeight", ""));
        Properties.getItems().add(new PropertyTableRow("SlpLinesNearEndToEnd", ""));
        Properties.getItems().add(SlpLineSpacingRow = new PropertyTableRow("SlpLineSpacing", ""));
        Properties.getItems().add(new PropertyTableRow("SlpLineWidth", ""));
        Properties.getItems().add(new PropertyTableRow("SlpMaxLines", ""));
        Properties.getItems().add(SlpNearEndRow = new PropertyTableRow("SlpNearEnd", ""));
        Properties.getItems().add(SlpPrintSideRow = new PropertyTableRow("SlpPrintSide", "", new SlpPrintSideValues()));
        Properties.getItems().add(new PropertyTableRow("SlpSidewaysMaxChars", ""));
        Properties.getItems().add(new PropertyTableRow("SlpSidewaysMaxLines", ""));
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
            FlagWhenIdle.setSelected("true".equals(FlagWhenIdleRow.getValue()));
            InUpdateGui = false;
        }
    }

    public void setFlagWhenIdle(ActionEvent actionEvent) {
        try {
            ThePrinter.setFlagWhenIdle(FlagWhenIdle.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class CapCharacterSetValues extends Values {
    }

    private class CapRuledLineValues extends Values {
    }

    private class CartridgeNotifyValues extends Values {
    }

    private class CharacterSetValues extends Values {
    }

    private class ErrorLevelValues extends Values {
    }

    private class ErrorStationValues extends Values {
    }

    private class CartridgeStateValues extends Values {
    }

    private class CurrentCartridgeValues extends Values {
    }

    private class MapModeValues extends Values {
    }

    private class PageModePrintDirectionValues extends Values {
    }

    private class PageModeStationValues extends Values {
    }

    private class RotateSpecialValues extends Values {
    }

    private class SlpPrintSideValues extends Values {
    }
}
