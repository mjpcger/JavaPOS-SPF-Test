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
import jpos.events.DataEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for POSKeyboard properties, methods and events.
 */
public class POSKeyboardController extends CommonController {
    public CheckBox EventTypes;
    public Label POSKeyData;
    public Label POSKeyEventType;
    private POSKeyboard TheLock;
    private PropertyTableRow EventTypesRow;
    private PropertyTableRow POSKeyEventTypeRow;
    private PropertyTableRow POSKeyDataRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Ascii;
        super.initialize(url, resourceBundle);
        TheLock = (POSKeyboard) Control;
        TheLock.addDirectIOListener(this);
        TheLock.addStatusUpdateListener(this);
        TheLock.addDataListener(this);
        TheLock.addErrorListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("CapKeyUp", ""));
        Properties.getItems().add(EventTypesRow = new PropertyTableRow("EventTypes", "", new EventTypesValues()));
        Properties.getItems().add(POSKeyDataRow = new PropertyTableRow("POSKeyData", ""));
        Properties.getItems().add(POSKeyEventTypeRow = new PropertyTableRow("POSKeyEventType", "", new POSKeyEventTypeValues()));
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
            EventTypes.setSelected(new EventTypesValues().getSymbol(POSKeyboardConst.KBD_ET_DOWN_UP).equals(EventTypesRow.getValue()));
            if (!"0".equals(POSKeyEventTypeRow.getValue())) {
                POSKeyData.setText(POSKeyDataRow.getValue());
                POSKeyEventType.setText(POSKeyEventTypeRow.getValue());
            } else {
                POSKeyData.setText("");
                POSKeyEventType.setText("");
            }
            InUpdateGui = false;
        }
    }

    @Override
    String getLogString(DataEvent event) {
        return "Key " + POSKeyDataRow.getValue() + " - " + POSKeyEventTypeRow.getValue();
    }

    public void setEventTypes(ActionEvent actionEvent) {
        try {
            TheLock.setEventTypes(EventTypes.isSelected() ? POSKeyboardConst.KBD_ET_DOWN_UP : POSKeyboardConst.KBD_ET_DOWN);
        } catch (JposException e) {
            if (TheLock.getState() != JposConst.JPOS_S_CLOSED) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    private class EventTypesValues extends Values {
        EventTypesValues() {
            ValueList = new Object[] {
                    POSKeyboardConst.KBD_ET_DOWN, "ET_DOWN",
                    POSKeyboardConst.KBD_ET_DOWN_UP, "ET_DOWN_UP"
            };
        }
    }

    private class POSKeyEventTypeValues extends Values {
        POSKeyEventTypeValues() {
            ValueList = new Object[]{
                    POSKeyboardConst.KBD_KET_KEYDOWN, "KET_KEYDOWN",
                    POSKeyboardConst.KBD_KET_KEYUP, "KET_KEYUP"
            };
        }
    }
}
