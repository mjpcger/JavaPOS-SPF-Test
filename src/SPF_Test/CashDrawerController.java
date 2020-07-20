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
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for CashDrawer properties, methods and events.
 */
public class CashDrawerController extends CommonController {
    public ComboBox<String> WFDC_beepTimeout;
    public TextField WFDC_beepFrequency;
    public ComboBox<String> WFDC_beepDuration;
    public TextField WFDC_beepDelay;

    private CashDrawer TheDrawer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheDrawer = (CashDrawer) Control;
        TheDrawer.addDirectIOListener(this);
        TheDrawer.addStatusUpdateListener(this);
        StatusUpdateEventStatusValueConverter = new CDStatusUpdateValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("DrawerOpened", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapStatus", ""));
        Properties.getItems().add(new PropertyTableRow("CapStatusMultiDrawerDetect", ""));
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
            if (WFDC_beepTimeout.getItems().size() == 0)
                WFDC_beepTimeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
            if (WFDC_beepDuration.getItems().size() == 0)
                WFDC_beepDuration.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
            InUpdateGui = false;
        }
    }

    public void openDrawer(ActionEvent actionEvent) {
        try {
            TheDrawer.openDrawer();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    /**
     * Processor for method WaitForDrawer
     */
    class WaitForDrawerCloseHandler extends MethodProcessor {
        /**
         * Constructor. Stores method name <b>WaitForDrawerClose</b> and parameters <i>beepTimeout, beepFrequency,
         * beepDuration, beepDelay</i>.
         * @param beepTimeout Parameter <i>beepTimeout</i>
         * @param beepFrequency Parameter <i>data</i>
         * @param beepDuration Parameter <i>beepDuration</i>
         */
        WaitForDrawerCloseHandler(int beepTimeout, int beepFrequency, int beepDuration, int beepDelay) {
            super("WaitForDrawerClose");
            Frequency = beepFrequency;
            Timeout = beepTimeout;
            Duration = beepDuration;
            Delay = beepDelay;
        }

        private int Frequency;
        private int Timeout;
        private int Duration;
        private int Delay;

        @Override
        public void runIt() throws JposException {
            TheDrawer.waitForDrawerClose(Timeout, Frequency, Duration, Delay);
        }
    }

    public void waitForDrawerClose(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Values tiov = new TimeoutValues(), intv = new IntValues();
        Integer beeptio = tiov.getInteger(WFDC_beepTimeout.getValue());
        Integer beepfreq = intv.getInteger(WFDC_beepFrequency.getText());
        Integer beepduration = tiov.getInteger(WFDC_beepDuration.getValue());
        Integer beepdelay = intv.getInteger(WFDC_beepDelay.getText());
        if (!invalid(beeptio, "beepTimeout") && !invalid(beepfreq, "beepFrequency") && !invalid(beepduration, "beepDuration") && !invalid(beepdelay, "beepDelay"))
            new WaitForDrawerCloseHandler(beeptio, beepfreq, beepduration, beepdelay).start();
    }

    private class CDStatusUpdateValues extends StatusUpdateValues {
        CDStatusUpdateValues() {
            super();
            Object[] cdvalues = new Object[]{
                    CashDrawerConst.CASH_SUE_DRAWERCLOSED, "SUE_DRAWERCLOSED",
                    CashDrawerConst.CASH_SUE_DRAWEROPEN, "SUE_DRAWEROPEN"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + cdvalues.length);
            System.arraycopy(cdvalues, 0, ValueList, ValueList.length - cdvalues.length, cdvalues.length);
        }
    }
}
