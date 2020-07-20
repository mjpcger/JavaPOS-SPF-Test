package SPF_Test;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import jpos.JposException;
import jpos.SignatureCapture;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for Lights properties, methods and events.
 */
public class SignatureCaptureController extends CommonController {
    public Canvas PointArray;
    public CheckBox RealTimeDataEnabled;
    public TextField BC_formName;
    private SignatureCapture TheSignatureCapture;
    private PropertyTableRow MaximumXRow;
    private PropertyTableRow MaximumYRow;
    private PropertyTableRow RawDataRow;
    private PropertyTableRow RealTimeDataEnabledRow;
    private Integer MaxX = null;
    private Integer MaxY = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheSignatureCapture = (SignatureCapture) Control;
        TheSignatureCapture.addDirectIOListener(this);
        TheSignatureCapture.addStatusUpdateListener(this);
        TheSignatureCapture.addDataListener(this);
        TheSignatureCapture.addErrorListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CapDisplay", ""));
        Properties.getItems().add(new PropertyTableRow("CapRealTimeData", ""));
        Properties.getItems().add(new PropertyTableRow("CapUserTerminated", ""));
        Properties.getItems().add(MaximumXRow = new PropertyTableRow("MaximumX", ""));
        Properties.getItems().add(MaximumYRow = new PropertyTableRow("MaximumY", ""));
        Properties.getItems().add(RawDataRow = new PropertyTableRow("RawData", ""));
        Properties.getItems().add(RealTimeDataEnabledRow = new PropertyTableRow("RealTimeDataEnabled", ""));
        Properties.getItems().add(new PropertyTableRow("DataCount", ""));
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
        clearAllPixels();
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            RealTimeDataEnabled.setSelected("true".equals(RealTimeDataEnabledRow.getValue()));
            if (!DeviceEnabled.isSelected() && PreviousSignature != null)
                setPixels(Color.WHITE);
            MaxX = new IntValues().getInteger(MaximumXRow.getValue());
            MaxY = new IntValues().getInteger(MaximumYRow.getValue());
            drawPixels();
            InUpdateGui = false;
        }
    }

    private void clearAllPixels() {
        PixelWriter writer = PointArray.getGraphicsContext2D().getPixelWriter();
        int maxy = (int) PointArray.getHeight() - 1;
        for (int x = (int) PointArray.getWidth() - 1; x >= 0; x--)
            for (int y = maxy; y >= 0; y--)
                writer.setColor(x, y, Color.WHITE);
    }

    java.awt.Point[] PreviousSignature = null;
    Integer PreviousMaxX = null;
    Integer PreviousMaxY = null;

    private void setPixels(Color color) {
        if (PreviousSignature != null) {
            PixelWriter writer = PointArray.getGraphicsContext2D().getPixelWriter();
            for (java.awt.Point point : PreviousSignature) {
                if (point.x < 0xffff && point.y < 0xffff) {
                    int[] dot = getCoordinate(point, PreviousMaxX, PreviousMaxY);
                    if (dot[0] >= 0 && dot[0] < (int) PointArray.getWidth() && dot[1] >= 0 && dot[1] < (int) PointArray.getHeight())
                        writer.setColor(dot[0], dot[1], color);
                }
            }
            if (color == Color.WHITE)
                PreviousSignature = null;
        }
    }

    private int[] getCoordinate(java.awt.Point point, Integer maxx, Integer maxy) {
        int[] ret = {point.x, point.y};
        if (maxx != null && maxy != null) {
            ret[0] = (ret[0] * (int)PointArray.getWidth()) / maxx;
            ret[1] = (ret[1] * (int)PointArray.getHeight()) / maxy;
        }
        return ret;
    }

    private void drawPixels() {
        java.awt.Point[] signature = null;
        if (DeviceEnabled.isSelected()) {
            try {
                signature = TheSignatureCapture.getPointArray();
            } catch (JposException e) {}
        }
        if (signature != PreviousSignature) {
            setPixels(Color.WHITE);
            PreviousSignature = signature;
            PreviousMaxX = MaxX;
            PreviousMaxY = MaxY;
            setPixels(Color.RED);
        }
    }

    public void setRealTimeDataEnabled(ActionEvent actionEvent) {
        try {
            TheSignatureCapture.setRealTimeDataEnabled(RealTimeDataEnabled.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class BeginCapture extends MethodProcessor {
        private final String FormName;

        public BeginCapture(String formName) {
            super("BeginCapture");
            FormName = formName;
        }

        @Override
        void runIt() throws JposException {
            TheSignatureCapture.beginCapture(FormName);
        }
    }

    public void beginCapture(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new BeginCapture(BC_formName.getText()).start();
    }

    private class EndCapture extends MethodProcessor {
        public EndCapture() {
            super("EndCapture");
        }

        @Override
        void runIt() throws JposException {
            TheSignatureCapture.endCapture();
        }
    }

    public void endCapture(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new EndCapture().start();
    }
}
