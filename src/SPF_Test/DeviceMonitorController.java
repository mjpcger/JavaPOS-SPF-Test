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
import javafx.scene.control.*;
import jpos.*;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class DeviceMonitorController extends CommonController {
    private DeviceMonitor TheDeviceMonitor;
    private PropertyTableRow DataCountRow;
    private PropertyTableRow DeviceDataRow;
    private PropertyTableRow DeviceListRow;
    private PropertyTableRow MonitoringDeviceListRow;

    public Label DeviceID;
    public Label Type;
    public Label Unit;
    public Label Value;
    public ComboBox<String> GDV_deviceID;
    public TextField GDV_pValue;
    public Label GDV_deviceParameters;
    public ComboBox<String> AMD_deviceID;
    public ComboBox<String> AMD_monitoringMode;
    public TextField AMD_boundary;
    public TextField AMD_subBoundary;
    public TextField AMD_intervalTime;
    public ComboBox<String> DMD_deviceID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Hexadecimal;
        MaxConversionLength = Integer.MAX_VALUE;
        super.initialize(url, resourceBundle);
        TheDeviceMonitor = (DeviceMonitor) Control;
        TheDeviceMonitor.addDirectIOListener(this);
        TheDeviceMonitor.addStatusUpdateListener(this);
        TheDeviceMonitor.addDataListener(this);
        TheDeviceMonitor.addErrorListener(this);
        Values val = new AMD_monitoringModeValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(DataCountRow = new PropertyTableRow("DataCount", ""));
        Properties.getItems().add(DeviceDataRow = new PropertyTableRow("DeviceData", ""));
        Properties.getItems().add(DeviceListRow = new PropertyTableRow("DeviceList", ""));
        Properties.getItems().add(MonitoringDeviceListRow = new PropertyTableRow("MonitoringDeviceList", ""));
        Properties.getItems().add(new PropertyTableRow("DataEventEnabled", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        AMD_monitoringMode.getItems().clear();
        for (int i = 1; i < val.ValueList.length; i += 2)
            AMD_monitoringMode.getItems().add(val.ValueList[i].toString());
    }

    @Override
    public void updateGui() {
        String data = DeviceDataRow.getValue();
        String list = DeviceListRow.getValue();
        String monitored = MonitoringDeviceListRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            if (!DeviceDataRow.getValue().equals(data)) {
                if (DeviceDataRow.equals("")) {
                    DeviceID.setText("");
                    Type.setText("");
                    Unit.setText("");
                    Value.setText("");
                    GDV_deviceParameters.setText("");
                } else {
                    String[] parts = DeviceDataRow.getValue().split(":");
                    if (parts.length >= 2) {
                        String[] devs = DeviceListRow.getValue().split(",");
                        for (String description : devs) {
                            String[] params = description.split(":");
                            if (params.length >= 4 && params[0].equals(parts[0])) {
                                try {
                                    Value.setText(new BigDecimal(parts[1]).divide(new BigDecimal(params[3])).toString());
                                    DeviceID.setText(params[0]);
                                    Type.setText(params[1]);
                                    Unit.setText(params[2]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (!DeviceListRow.getValue().equals(list)) {
                GDV_deviceID.getItems().clear();
                GDV_deviceParameters.setText("");
                AMD_deviceID.getItems().clear();
                if (DeviceListRow.getValue().length() > 0) {
                    String[] lines = DeviceListRow.getValue().split(",");
                    for (String line : lines) {
                        String[] columns = line.split(":");
                        GDV_deviceID.getItems().add(columns[0]);
                        AMD_deviceID.getItems().add(columns[0]);
                    }
                }
            }
            if (!MonitoringDeviceListRow.getValue().equals(monitored)) {
                String oldval = DMD_deviceID.getValue();
                DMD_deviceID.getItems().clear();
                if (MonitoringDeviceListRow.getValue().length() > 0) {
                    String[] lines = MonitoringDeviceListRow.getValue().split(",");
                    for (String line : lines) {
                        String[] columns = line.split(":");
                        DMD_deviceID.getItems().add(columns[0]);
                        if (columns[0].equals(oldval))
                            DMD_deviceID.setValue(oldval);
                    }
                }
            }
            InUpdateGui = false;
        }
    }

    class GetDeviceValue extends MethodProcessor {
        final String DeviceID;
        int PValue = 0;
        GetDeviceValue(String deviceID) {
            super("GetDeviceValue");
            DeviceID = deviceID;
        }

        @Override
        void runIt() throws JposException {
            int[] pValue = {0};
            TheDeviceMonitor.getDeviceValue(DeviceID, pValue);
            PValue = pValue[0];
        }

        @Override
        void finish() {
            String[] params = getDeviceParams(GDV_deviceID.getValue(), DeviceListRow);
            GDV_pValue.setText(new BigDecimal(PValue).divide(new BigDecimal(params[3])).toString());
            super.finish();
        }
    }

    public void getDeviceValue(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String deviceID = GDV_deviceID.getValue();
        if (!invalid(deviceID, "deviceID"))
            new GetDeviceValue(deviceID).start();
    }

    class AddMonitoringDevice extends MethodProcessor {
        String ID;
        final int Mode, Upper, Lower, Time;
        AddMonitoringDevice(String id, int mode, int upper, int lower, int time) {
            super("AddMonitoringDevice");
            ID = id;
            Mode = mode;
            Upper = upper;
            Lower = lower;
            Time = time;
        }

        @Override
        void runIt() throws JposException {
            TheDeviceMonitor.addMonitoringDevice(ID, Mode, Upper, Lower, Time);
        }
    }

    public void addMonitoringDevice(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        IntValues val = new IntValues();
        String id = AMD_deviceID.getValue();
        String[] params = getDeviceParams(id, DeviceListRow);
        Integer mode = new AMD_monitoringModeValues().getInteger(AMD_monitoringMode.getValue());
        Integer upper = null;
        Integer lower = null;
        if (params == null) {
            upper = val.getInteger(AMD_boundary.getText());
            lower = val.getInteger(AMD_subBoundary.getText());
        } else {
            try {
                upper = new BigDecimal(AMD_boundary.getText()).multiply(new BigDecimal(params[3])).intValue();
            } catch (Exception e) {
            }
            try {
                lower = new BigDecimal(AMD_subBoundary.getText()).multiply(new BigDecimal(params[3])).intValue();
            } catch (Exception e) {
            }
        }
        Integer time = val.getInteger(AMD_intervalTime.getText());
        if (validate(new Object[]{id, "deviceID", mode, "monitoringMode", upper, "boundary", lower, "subBoundary", time, "intervalTime"}))
            new AddMonitoringDevice(id, mode, upper, lower, time).start();
    }

    public void selectDeviceID(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            GDV_deviceParameters.setText("");
            String selected = GDV_deviceID.getValue();
            String[] params = getDeviceParams(selected, DeviceListRow);
            if (params != null) {
                GDV_deviceParameters.setText(params[1] + " - " + params[2]);
            }
            updateGui();
        }
    }

    class DeleteMonitoringDevice extends MethodProcessor {
        final String ID;
        DeleteMonitoringDevice(String id) {
            super("DeleteMonitoringDevice");
            ID = id;
        }

        @Override
        void runIt() throws JposException {
            TheDeviceMonitor.deleteMonitoringDevice(ID);
        }
    }

    public void deleteMonitoringDevice(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String id = DMD_deviceID.getValue();
        if (!invalid(id, "deviceID"))
            new DeleteMonitoringDevice(id).start();
    }

    class ClearMonitoringDevices extends MethodProcessor {
        ClearMonitoringDevices() {
            super("ClearMonitoringDevices");
        }

        @Override
        void runIt() throws JposException {
            TheDeviceMonitor.clearMonitoringDevices();
        }
    }

    public void clearMonitoringDevices(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new ClearMonitoringDevices().start();
    }

    private String[] getDeviceParams(String id, PropertyTableRow val) {
        String[] lines = val.getValue().split(",");
        for(String line : lines) {
            String[] columns = line.split(":");
            if (columns.length > 3 && columns[0].equals(id)) {
                return columns;
            }
        }
        return null;
    }

    private class AMD_monitoringModeValues extends IntValues {
        AMD_monitoringModeValues() {
            ValueList = new Object[]{
                    DeviceMonitorConst.DMON_MMODE_UPDATE, "MMODE_UPDATE",
                    DeviceMonitorConst.DMON_MMODE_STRADDLED, "MMODE_STRADDLED",
                    DeviceMonitorConst.DMON_MMODE_HIGH, "MMODE_HIGH",
                    DeviceMonitorConst.DMON_MMODE_LOW, "MMODE_LOW",
                    DeviceMonitorConst.DMON_MMODE_WITHIN, "MMODE_WITHIN",
                    DeviceMonitorConst.DMON_MMODE_OUTSIDE, "MMODE_OUTSIDE",
                    DeviceMonitorConst.DMON_MMODE_POLLING, "MMODE_POLLING"
            };
        }
    }
}
