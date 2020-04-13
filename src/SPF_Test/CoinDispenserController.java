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
 * GUI control for CoinDispenser properties, methods and events.
 */
public class CoinDispenserController extends CommonController {
    public Label DispenderStatus;
    public TextField DC_amount;
    public TextField ACC_cashCounts;
    public TextField RCC_cashCounts;
    public CheckBox RCC_discrepancy;

    private CoinDispenser TheDispenser;

    private PropertyTableRow DispenserStatusRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Hexadecimal;
        super.initialize(url, resourceBundle);
        TheDispenser = (CoinDispenser) Control;
        TheDispenser.addDirectIOListener(this);
        TheDispenser.addStatusUpdateListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("CapEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJamSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapNearEmptySensor", ""));
        Properties.getItems().add(DispenserStatusRow = new PropertyTableRow("DispenserStatus", "", new DispenserStatusValues()));
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
            DispenderStatus.setText(DispenserStatusRow.getValue());
            InUpdateGui = false;
        }
    }

    public void dispenseChange(ActionEvent actionEvent) {
        Integer amount = new IntValues().getInteger(DC_amount.getText());
        if (invalid(amount, "amount"))
            return;
        try {
            TheDispenser.dispenseChange(amount);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void adjustCashCounts(ActionEvent actionEvent) {
        try {
            TheDispenser.adjustCashCounts(ACC_cashCounts.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void readCashCounts(ActionEvent actionEvent) {
        String[] cashCounts = new String[]{RCC_cashCounts.getText()};
        boolean[] discrepancy = new boolean[]{RCC_discrepancy.isSelected()};
        try {
            TheDispenser.readCashCounts(cashCounts, discrepancy);
            RCC_discrepancy.setSelected(discrepancy[0]);
            RCC_cashCounts.setText(cashCounts[0]);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    private class DispenserStatusValues extends Values {
        DispenserStatusValues() {
            ValueList = new Object[]{
                    CoinDispenserConst.COIN_STATUS_OK, "STATUS_OK",
                    CoinDispenserConst.COIN_STATUS_EMPTY, "STATUS_EMPTY",
                    CoinDispenserConst.COIN_STATUS_NEAREMPTY, "STATUS_NEAREMPTY",
                    CoinDispenserConst.COIN_STATUS_JAM, "STATUS_JAM"
            };
        }
    }
}
