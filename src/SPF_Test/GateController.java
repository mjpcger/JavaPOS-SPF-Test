package SPF_Test;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import jpos.Gate;
import jpos.GateConst;
import jpos.JposException;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for Gate properties, methods and events.
 */
public class GateController extends CommonController {
    public ComboBox<String> WFGC_timeout;
    private Gate TheGate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheGate = (Gate) Control;
        TheGate.addDirectIOListener(this);
        TheGate.addStatusUpdateListener(this);
        StatusUpdateEventStatusValueConverter = new GStatusUpdateValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("GetStatus", "", new GateStatusValues()));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapGateStatus", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        updateGui();
        WFGC_timeout.getItems().add("FOREVER");
    }

    public void openGate(ActionEvent actionEvent) {
        try {
            TheGate.openGate();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class WaitForGateClosedHandler extends MethodProcessor {
        private final Integer Timeout;

        public WaitForGateClosedHandler(Integer timeout) {
            super("WaitForGateClosed");
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            TheGate.waitForGateClose(Timeout);
        }
    }

    public void waitForGateClosed(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer timeout = new TimeoutValues().getInteger(WFGC_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new WaitForGateClosedHandler(timeout).start();
    }

    private class GStatusUpdateValues extends StatusUpdateValues {
        GStatusUpdateValues() {
            super();
            Object[] cdvalues = new Object[]{
                    GateConst.GATE_SUE_CLOSED, "SUE_CLOSED",
                    GateConst.GATE_SUE_OPEN, "SUE_OPEN",
                    GateConst.GATE_SUE_BLOCKED, "SUE_BLOCKED",
                    GateConst.GATE_SUE_MALFUNCTION, "SUE_MALFUNCTION"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + cdvalues.length);
            System.arraycopy(cdvalues, 0, ValueList, ValueList.length - cdvalues.length, cdvalues.length);
        }
    }

    private class GateStatusValues extends Values {
        GateStatusValues() {
            ValueList = new Object[]{
                    GateConst.GATE_GS_CLOSED, "GS_CLOSED",
                    GateConst.GATE_GS_OPEN, "GS_OPEN",
                    GateConst.GATE_GS_BLOCKED, "GS_BLOCKED",
                    GateConst.GATE_GS_MALFUNCTION, "GS_MALFUNCTION"
            };
        }
    }
}
