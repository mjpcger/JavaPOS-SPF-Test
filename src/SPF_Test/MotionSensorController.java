package SPF_Test;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jpos.JposException;
import jpos.MotionSensor;
import jpos.MotionSensorConst;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for Lights properties, methods and events.
 */
public class MotionSensorController extends CommonController {
    public TextField Timeout;
    public ComboBox<String> WFM_timeout;
    private MotionSensor TheMotionSensor;
    private PropertyTableRow TimeoutRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheMotionSensor = (MotionSensor) Control;
        TheMotionSensor.addDirectIOListener(this);
        TheMotionSensor.addStatusUpdateListener(this);
        StatusUpdateEventStatusValueConverter = new MSStatusUpdateValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("Motion", ""));
        Properties.getItems().add(TimeoutRow = new PropertyTableRow("Timeout", ""));
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
        setPropertyOnFocusLost(Timeout, "Timeout");
        updateGui();
        WFM_timeout.getItems().add("FOREVER");
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            Timeout.setText(TimeoutRow.getValue());
            InUpdateGui = false;
        }
    }

    private class WaitForMotionHandler extends MethodProcessor {
        private final Integer Timeout;

        public WaitForMotionHandler(Integer timeout) {
            super("WaitForGateClosed");
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            TheMotionSensor.waitForMotion(Timeout);
        }
    }

    public void setTimeout(ActionEvent actionEvent) {
        Integer timeout = new TimeoutValues().getInteger(Timeout.getText());
        if (!invalid(timeout, "timeout")) {
            try {
                TheMotionSensor.setTimeout(timeout);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void waitForMotion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer timeout = new TimeoutValues().getInteger(WFM_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new WaitForMotionHandler(timeout).start();
    }

    private class MSStatusUpdateValues extends StatusUpdateValues {
        MSStatusUpdateValues() {
            super();
            Object[] cdvalues = new Object[]{
                    MotionSensorConst.MOTION_M_PRESENT, "M_PRESENT",
                    MotionSensorConst.MOTION_M_ABSENT, "M_ABSENT"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + cdvalues.length);
            System.arraycopy(cdvalues, 0, ValueList, ValueList.length - cdvalues.length, cdvalues.length);
        }
    }
}
