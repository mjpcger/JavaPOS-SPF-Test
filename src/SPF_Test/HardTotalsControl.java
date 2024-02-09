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

import javafx.event.*;
import javafx.scene.control.*;
import jpos.*;

import java.net.*;
import java.util.*;

public class HardTotalsControl extends CommonController {
    public Label TotalsSize;
    public Label FreeData;
    public Label NumberOfFiles;
    public Label TransactionInProgress;
    public TextField Handle;
    public ComboBox<String> CF_timeout;
    public TextField FileName;
    public TextField C_size;
    public ComboBox<String> ErrorDetection;
    public TextField F_size;
    public TextField F_index;
    public TextField SA_value;
    public TextArea RW_data;
    public TextField RW_offset;
    public TextField RW_count;
    public ComboBox<String> RW_conversion;
    // private HardTotals TheHardTotals;
    private HardTotals TheHardTotals;
    private PropertyTableRow NumberOfFilesRow;
    private PropertyTableRow FreeDataRow;
    private PropertyTableRow TotalsSizeRow;
    private PropertyTableRow TransactionInProgressRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheHardTotals = (HardTotals) Control;
        TheHardTotals.addDirectIOListener(this);
        TheHardTotals.addStatusUpdateListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(NumberOfFilesRow = new PropertyTableRow("NumberOfFiles", ""));
        Properties.getItems().add(FreeDataRow = new PropertyTableRow("FreeData", ""));
        Properties.getItems().add(TotalsSizeRow = new PropertyTableRow("TotalsSize", ""));
        Properties.getItems().add(new PropertyTableRow("CapTransactions", ""));
        Properties.getItems().add(TransactionInProgressRow = new PropertyTableRow("TransactionInProgress", ""));
        Properties.getItems().add(new PropertyTableRow("CapSingleFile", ""));
        Properties.getItems().add(new PropertyTableRow("CapErrorDetection", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        CF_timeout.getItems().add(new TimeoutValues().ValueList[1].toString());
        ErrorDetection.getItems().add("true");
        ErrorDetection.getItems().add("false");
        RW_conversion.getItems().add("UTF-8");
        RW_conversion.getItems().add("Hexadecimal ASCII");
        RW_conversion.getItems().add("ASCII with \\-Escape");
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            TransactionInProgress.setText(TransactionInProgressRow.getValue());
            NumberOfFiles.setText(NumberOfFilesRow.getValue());
            TotalsSize.setText(TotalsSizeRow.getValue());
            FreeData.setText(FreeDataRow.getValue());
        }
    }

    class ClaimFileHandler extends MethodProcessor {

        private final int Timeout;
        private final int FileHandle;

        /**
         * Constructor.
         *
         * @param handle    File handle.
         * @param timeout   timeout.
         */
        ClaimFileHandler(int handle, int timeout) {
            super("ClaimFileHandler");
            Timeout = timeout;
            FileHandle = handle;
        }

        @Override
        void runIt() throws JposException {
            TheHardTotals.claimFile(FileHandle, Timeout);
        }
    }

    public void claimFile(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer handle = new IntValues().getInteger(Handle.getText());
        Integer timeout = new TimeoutValues().getInteger(CF_timeout.getValue());
        if (!invalid(timeout, "timeout") && !invalid(handle, "hTotalsFile"))
            new ClaimFileHandler(handle, timeout).start();
    }

    public void releaseFile(ActionEvent actionEvent) {
        try {
            Integer handle = new IntValues().getInteger(Handle.getText());
            if (!invalid(handle, "hTotalsFile"))
                TheHardTotals.releaseFile(handle);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void beginTrans(ActionEvent actionEvent) {
        try {
            TheHardTotals.beginTrans();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void commitTrans(ActionEvent actionEvent) {
        try {
            TheHardTotals.commitTrans();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void rollback(ActionEvent actionEvent) {
        try {
            TheHardTotals.rollback();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void create(ActionEvent actionEvent) {
        try {
            String name = FileName.getText();
            int[] handle = new int[1];
            int size = new IntValues().getInteger(C_size.getText());
            Boolean err = null;
            if ("true".equals(ErrorDetection.getValue()))
                err = true;
            else if ("false".equals(ErrorDetection.getValue()))
                err = false;
            if (!invalid(name, "fileName") && !invalid(handle, "hTotalsFile")
                    && !invalid(size, "size") && !invalid(err, "errorDetection")) {
                TheHardTotals.create(name, handle, size, err);
                Handle.setText("" + handle[0]);
            }
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void delete(ActionEvent actionEvent) {
        try {
            if (!invalid(FileName.getText(), "fileName"))
                TheHardTotals.delete(FileName.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void find(ActionEvent actionEvent) {
        try {
            int[] handle = new int[1], size = new int[1];
            if (!invalid(FileName.getText(), "fileName")) {
                TheHardTotals.find(FileName.getText(), handle, size);
                Handle.setText("" + handle[0]);
                F_size.setText("" + size[0]);
            }
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void findByIndex(ActionEvent actionEvent) {
        try {
            Integer index = new IntValues().getInteger(F_index.getText());
            String[] name = new String[1];
            if (!invalid(index, "index")) {
                TheHardTotals.findByIndex(index, name);
                FileName.setText(name[0]);
            }
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setAll(ActionEvent actionEvent) {
        try {
            Integer handle = new IntValues().getInteger(Handle.getText());
            Integer value = new IntValues().getInteger(SA_value.getText());
            if (!invalid(handle, "hTotalsFile") && !invalid(value, "value")
                    && !invalid(value < -128 || value > 255 ? null : value, "value"))
                TheHardTotals.setAll(handle, (byte)(int)value);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void rename(ActionEvent actionEvent) {
        try {
            Integer handle = new IntValues().getInteger(Handle.getText());
            if (!invalid(handle, "hTotalsFile") && !invalid(FileName.getText(), "fileName"))
                TheHardTotals.rename(handle, FileName.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void read(ActionEvent actionEvent) {
        try {
            IntValues cvt = new IntValues();
            Integer handle = cvt.getInteger(Handle.getText());
            Integer offset = cvt.getInteger(RW_offset.getText());
            Integer count = cvt.getInteger(RW_count.getText());
            byte[] data = new byte[0];
            String encoding = RW_conversion.getValue();
            if ("UTF-8".equals(encoding) && RW_data.getText() != null)
                data = RW_data.getText().getBytes();
            else if ("Hexadecimal ASCII".equals(encoding) && RW_data.getText() != null)
                data = hexStringToByteArray(RW_data.getText());
            else if ("ASCII with \\-Escape".equals(encoding) && RW_data.getText() != null)
                data = asciiStringToByteArray(RW_data.getText());
            if (!invalid(handle, "hTotalsFile") && !invalid(offset, "offset") && !invalid(count, "count"))
            {
                if (data.length == 0)
                    data = new byte[count];
                TheHardTotals.read(handle, data, offset, count);
                if ("UTF-8".equals(encoding) && RW_data.getText() != null)
                    RW_data.setText(new String(data));
                else if ("Hexadecimal ASCII".equals(encoding) && RW_data.getText() != null)
                    RW_data.setText(byteArrayToHexString(data, data.length, true, 8));
                else if ("ASCII with \\-Escape".equals(encoding))
                    RW_data.setText(byteArrayToAsciiString(data, 28));
            }
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void write(ActionEvent actionEvent) {
        try {
            IntValues cvt = new IntValues();
            Integer handle = cvt.getInteger(Handle.getText());
            Integer offset = cvt.getInteger(RW_offset.getText());
            Integer count = cvt.getInteger(RW_count.getText());
            byte[] data = null;
            String encoding = RW_conversion.getValue();
            if ("UTF-8".equals(encoding) && RW_data.getText() != null)
                data = RW_data.getText().getBytes();
            else if ("Hexadecimal ASCII".equals(encoding) && RW_data.getText() != null)
                data = hexStringToByteArray(RW_data.getText());
            else if ("ASCII with \\-Escape".equals(encoding) && RW_data.getText() != null)
                data = asciiStringToByteArray(RW_data.getText());
            if (!invalid(handle, "hTotalsFile") && !invalid(data, "data")
                    && !invalid(offset, "offset") && !invalid(count, "count"))
            {
                TheHardTotals.write(handle, data, offset, count);
            }
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void recalculateValidationData(ActionEvent actionEvent) {
        try {
            Integer handle = new IntValues().getInteger(Handle.getText());
            if (!invalid(handle, "hTotalsFile"))
                TheHardTotals.recalculateValidationData(handle);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void validateData(ActionEvent actionEvent) {
        try {
            Integer handle = new IntValues().getInteger(Handle.getText());
            if (!invalid(handle, "hTotalsFile"))
                TheHardTotals.validateData(handle);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }
}
