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

import de.gmxhome.conrad.jpos.jpos_base.*;
import javafx.application.*;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.*;
import jpos.*;
import jpos.events.*;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.*;
import java.math.*;
import java.net.*;
import java.util.*;

import static de.gmxhome.conrad.jpos.jpos_base.JposBaseDevice.synchronizedMessageBox;

/**
 * GUI control for common properties, methods and events.
 */
public class CommonController implements Initializable, Runnable, DataListener, DirectIOListener, ErrorListener, OutputCompleteListener, StatusUpdateListener {
    public CheckBox AutoDisable;
    public CheckBox DeviceEnabled;
    public CheckBox FreezeEvents;
    public CheckBox DataEventEnabled;
    public CheckBox AsyncMode;
    public Label    DataCount;
    public CheckBox LockDataEventEnabled;

    /**
     * Converter class for values of PowerNotify property.
     */
    public static class PowerNotifyValues extends Values {
        PowerNotifyValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_PN_DISABLED, "PN_DISABLED",
                    JposConst.JPOS_PN_ENABLED, "PN_ENABLED"
            };
        }
    }
    public ComboBox<String> PowerNotify;

    /**
     * Converter class for timeout values.
     */
    public static class TimeoutValues extends Values {
        TimeoutValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_FOREVER, "FOREVER"
            };
        }
    }
    public ComboBox<String> claim_timeout;

    /**
     * Converter class for checkHealth parameter <i>level</i>.
     */
    public static class CH_levelValues extends Values {
        CH_levelValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_CH_INTERNAL, "CH_INTERNAL",
                    JposConst.JPOS_CH_EXTERNAL, "CH_EXTERNAL",
                    JposConst.JPOS_CH_INTERACTIVE, "CH_INTERACTIVE"
            };
        }
    }
    public ComboBox<String> CH_level;
    public TextField DIO_command;
    public TextField DIO_data;
    public TextField DIO_obj;
    public TextField CFV_firmwareFileName;

    /**
     * Converter class for compareFirmwareVersion parameter <i>result</i>.
     */
    public static class CFV_resultValues extends Values {
        CFV_resultValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_CFV_FIRMWARE_OLDER, "CFV_FIRMWARE_OLDER",
                    JposConst.JPOS_CFV_FIRMWARE_SAME, "CFV_FIRMWARE_SAME",
                    JposConst.JPOS_CFV_FIRMWARE_NEWER, "CFV_FIRMWARE_NEWER",
                    JposConst.JPOS_CFV_FIRMWARE_DIFFERENT, "CFV_FIRMWARE_DIFFERENT",
                    JposConst.JPOS_CFV_FIRMWARE_UNKNOWN, "CFV_FIRMWARE_UNKNOWN"
            };
        }
    }
    public TextField CFV_result;
    public TextField UF_firmwareFileName;
    public TextArea _statisticsBuffer;
    public TextArea EventOutput;
    public Label MethodActive;
    public ComboBox<ByteConversion> ByteArrayConversion;
    public ComboBox<Integer> CurrencyDigits;
    public BaseJposControl Control;
    public String LogicalDeviceName;
    public String Usage;

    /**
     * Converter class for CapPowerReporting values.
     */
    public static class CapPowerReportingValues extends Values {
        CapPowerReportingValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_PR_NONE, "PR_NONE",
                    JposConst.JPOS_PR_STANDARD, "PR_STANDARD",
                    JposConst.JPOS_PR_ADVANCED, "PR_ADVANCED"
            };
        }
    }
    Text CapPowerReporting;

    /**
     * Converter class for PowerState values.
     */
    public static class PowerStateValues extends Values {
        PowerStateValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_PS_UNKNOWN, "PS_UNKNOWN",
                    JposConst.JPOS_PS_ONLINE, "PS_ONLINE",
                    JposConst.JPOS_PS_OFF, "PS_OFF",
                    JposConst.JPOS_PS_OFFLINE, "PS_OFFLINE",
                    JposConst.JPOS_PS_OFF_OFFLINE, "PS_OFF_OFFLINE"
            };
        }
    }
    Text PowerState;

    /**
     * Converter class for State values.
     */
    public static class StateValues extends Values {
        StateValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_S_CLOSED, "S_CLOSED",
                    JposConst.JPOS_S_IDLE, "S_IDLE",
                    JposConst.JPOS_S_BUSY, "S_BUSY",
                    JposConst.JPOS_S_ERROR, "S_ERROR"
            };
        }
    }
    Text State;

    /**
     * Helper class for property Name - Value TableView.
     */
    static class PropertyTableRow {
        private final StringProperty Name;
        private final StringProperty Value;
        private Values ValueConverter;

        /**
         * Constructor. Name and default value of a device property.
         * @param name  Property name.
         * @param value Default value.
         */
        PropertyTableRow(String name, String value) {
            Name = new SimpleStringProperty(this, "Name", name);
            Value = new SimpleStringProperty(this, "Value", value);
            ValueConverter = null;
        }

        /**
         * Constructor. Name, default value and value converter of an integer device property.
         * @param name  Property name.
         * @param value Default value.
         * @param converter Value converter.
         */
        PropertyTableRow(String name, String value, Values converter) {
            Name = new SimpleStringProperty(this, "Name", name);
            Value = new SimpleStringProperty(this, "Value", value == null ? "" : value);
            ValueConverter = converter;
        }

        /**
         * Getter for property name.
         * @return Property name.
         */
        public String getName() {
            return Name.get();
        }

        /**
         * Getter for name property used by TableView.
         * @return Name property.
         */
        public StringProperty getNameProperty() {
            return Name;
        }

        /**
         * Getter for property value.
         * @return Property value.
         */
        public String getValue() {
            return Value.get();
        }

        /**
         * Setter for property value.
         * @param value New property value.
         */
        public void setValue(String value) {
            Value.set(value);
        }

        /**
         * Getter for Value property used by TableView.
         * @return Value property.
         */
        public StringProperty getValueProperty() {
            return Value;
        }

        /**
         * Getter for value converter to be used for integer properties.
         * @return Value converter or <b>null</b> if no converter is present.
         */
        public Values getValueConverter() {
            return ValueConverter;
        }

        /**
         *
         */
        public void setValueConverter(Values vals) {
            ValueConverter = vals;
        }
    }

    @FXML
    public TableView<PropertyTableRow> Properties;

    /**
     * Column width of TableView <b>Properties</b> column <i>Name</i>.
     */
    public int PropertyNameColumnWidth = 0;

    /**
     * Column width of TableView <b>Properties</b> column <i>Value</i>.
     */
    public int PropertyValueColumnWidth = 0;

    String currencyStringToDecimalString(String source, int digits) {
        try {
            return new BigDecimal(source).scaleByPowerOfTen(-4).setScale(digits, RoundingMode.HALF_EVEN).toString();
        } catch (Exception e) {
            return "";
        }
    }

    String  decimalStringToCurrencyString(String source) {
        try {
            return Long.toString(new BigDecimal(source).scaleByPowerOfTen(4).longValueExact());
        } catch (Exception e) {
            return "";
        }
    }

    void rowValue2Decimal(PropertyTableRow row) {
        row.setValue(currencyStringToDecimalString(row.getValue(), CurrencyDigits.getValue()));
    }

    Long getDecimalRowValue(PropertyTableRow row) {
        String result = decimalStringToCurrencyString(row.getValue());
        return result.length() == 0 ? null : Long.parseLong(result);
    }

    void setDecimalRowValue(PropertyTableRow row, long value) {
        row.setValue(currencyStringToDecimalString(Long.toString(value), CurrencyDigits.getValue()));
    }

    void formatDecimalTextField(TextField field, int decimals) {
        try {
            field.setText(new BigDecimal(field.getText()).setScale(decimals, RoundingMode.HALF_UP).toString());
        } catch (Exception e) {
            field.setText(null);
        }
    }

    void setDecimalTextField(TextField field, long value) {
        String result = currencyStringToDecimalString(Long.toString(value), CurrencyDigits.getValue());
        field.setText(result.length() == 0 ? null : result);
    }

    void formatDecimalOnFocusLost(TextField field) {
        field.focusedProperty().addListener((ov, oldv, newv) -> {
            if (!newv) {
                formatDecimalTextField(field, CurrencyDigits.getValue());
            }
        });
    }

    void setPropertyOnFocusLost(TextInputControl field, String propertyname) {
        field.focusedProperty().addListener((ov, oldv, newv) -> {
            if (!newv) {
                try {
                    Method setProperty = Class.forName(this.getClass().getName()).getMethod("set" + propertyname, ActionEvent.class);
                    ActionEvent ev = new ActionEvent(null, field );
                    setProperty.invoke(this, ev);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    public void setCurrencyDigits(ActionEvent ignore) {
        updateGui();
    }
    @FXML
    public void setAutoDisable(ActionEvent ignore) {
        try {
            Method setAutoDisable = Class.forName(Control.getClass().getName()).getMethod("setAutoDisable", Boolean.TYPE);
            setAutoDisable.invoke(Control, AutoDisable.isSelected());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    private class DataCountUpdater extends Thread {
        SyncObject CheckWaiter = new SyncObject();

        DataCountUpdater() {
            setName("DataCountUpdater");
            start();
        }

        @Override
        public void run() {
            long timeout = SyncObject.INFINITE;
            Integer oldcount = null;
            while (Control.getState() != JposConst.JPOS_S_CLOSED) {
                CheckWaiter.suspend(timeout);
                try {
                    timeout = Control.getDeviceEnabled() ? 100 : SyncObject.INFINITE;
                } catch (JposException e) {
                    timeout = SyncObject.INFINITE;
                }
                Integer count;
                try {
                        Method getDataCount = Control.getClass().getMethod("getDataCount");
                        count =  (Integer) getDataCount.invoke(Control);
                } catch (Exception e) {
                    count = null;
                }
                final Integer datacount = count;
                boolean equal = Objects.equals(count, oldcount);
                if (!equal) {
                    Platform.runLater(() -> {
                        DataCount.setText(datacount == null ? "" : "DataCount: " + datacount);
                        updateGui();
                    });
                    oldcount = count;
                }
            }
        }
    }

    private DataCountUpdater TheDataCountUpdater;

    @FXML
    public void setDeviceEnabled(ActionEvent ignore) {
        try {
            Control.setDeviceEnabled(DeviceEnabled.isSelected());
            if (DataCount != null)
                TheDataCountUpdater.CheckWaiter.signal();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void setAsyncMode(ActionEvent ignore) {
        try {
            Method setAsyncMode = Class.forName(Control.getClass().getName()).getMethod("setAsyncMode", Boolean.TYPE);
            setAsyncMode.invoke(Control, AsyncMode.isSelected());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void setFreezeEvents(ActionEvent ignore) {
        try {
            Control.setFreezeEvents(FreezeEvents.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void setPowerNotify(ActionEvent ignore) {
        if (!InUpdateGui) {
            try {
                Method setPowerNotify = Class.forName(Control.getClass().getName()).getMethod("setPowerNotify", Integer.TYPE);
                setPowerNotify.invoke(Control, new PowerNotifyValues().getInteger(PowerNotify.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    @FXML
    public void setDataEventEnabled(ActionEvent ignore) {
        try {
            Method setDataEventEnabled = Class.forName(Control.getClass().getName()).getMethod("setDataEventEnabled", Boolean.TYPE);
            setDataEventEnabled.invoke(Control, DataEventEnabled.isSelected());
            if (TheDataCountUpdater != null)
                TheDataCountUpdater.CheckWaiter.signal();
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void open(ActionEvent actionEvent) {
        try {
            Control.open(LogicalDeviceName);
            if (DataCount != null)
                TheDataCountUpdater = new DataCountUpdater();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void close(ActionEvent actionEvent) {
        try {
            Control.close();
            if (DataCount != null)
                TheDataCountUpdater.CheckWaiter.signal();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    /**
     * Thread object of currently running method. Methods that potentially do not finish within a short time will
     * be executed in a different thread context to allow fast GUI reactions.
     */
    MethodProcessor CurrentMethod = null;

    /**
     * Method execution class.
     */
    abstract class MethodProcessor extends Thread {
        /**
         * Constructor.
         * @param method Method name.
         */
        MethodProcessor(String method) {
            if (method == null) {
                method = getClass().getSimpleName();
                int tailindex;
                if ((tailindex = method.indexOf("Handler")) > 0)
                    method = method.substring(0, tailindex);
            }
            setName(LogicalDeviceName + " " + method + "Handler");
            synchronized(CommonController.this) {
                CurrentMethod = this;
            }
            MethodActive.setText(method + " running...");
        }

        @Override
        public void run() {
            try {
                runIt();
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            Platform.runLater(this::finish);
            CurrentMethod = null;
        }

        abstract void runIt() throws JposException;
        void finish() {
            updateGui();
            MethodActive.setText("");
        }
    }

    /**
     * Checks if a method is currently running. If so, a message box containing the method name will be shown.
     * @return true if a method is currently running. No other method must be invoked while the method is running.
     */
    boolean isMethodRunning() {
        MethodProcessor running = null;
        synchronized (this) {
            running = CurrentMethod;
        }
        if (running != null)
            myMessageDialog("Method " + running.getClass().getSimpleName() + " is currently running");
        synchronized (this) {
            return CurrentMethod != null;
        }
    }

    /**
     * Processor for method Claim.
     */
    class ClaimHandler extends MethodProcessor {
        /**
         * Constructor. Stores method name <b>Claim</b> and parameter <i>timeout</i>
         * @param timeout Parameter <i>timeout</i>
         */
        ClaimHandler(int timeout) {
            super("Claim");
            Timeout = timeout;
        }

        private final int Timeout;

        @Override
        public void runIt() throws JposException {
            Control.claim(Timeout);
        }
    }

    @FXML
    public void claim(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        Integer timeout = new TimeoutValues().getInteger(claim_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new ClaimHandler(timeout).start();
    }

    @FXML
    public void release(ActionEvent ignore) {
        try {
            Control.release();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    /**
     * Processor for method CheckHealth
     */
    class CheckHealthHandler extends MethodProcessor {
        /**
         * Constructor. Stores method name <b>CheckHealth</b> and parameter <i>level</i>
         * @param level Parameter <i>level</i>
         */
        CheckHealthHandler(int level) {
            super("CheckHealth");
            Level = level;
        }

        private final int Level;

        @Override
        public void runIt() throws JposException {
            Control.checkHealth(Level);
        }
    }

    @FXML
    public void checkHealth(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        Integer level = new CH_levelValues().getInteger(CH_level.getValue());
        if (!invalid(level, "level"))
            new CheckHealthHandler(level).start();
    }

    @FXML
    public void clearInput(ActionEvent ignore) {
        try {
            Method clearInput = Class.forName(Control.getClass().getName()).getMethod("clearInput");
            clearInput.invoke(Control);
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void clearInputProperties(ActionEvent ignore) {
        try {
            Method clearInputProperties = Class.forName(Control.getClass().getName()).getMethod("clearInputProperties");
            clearInputProperties.invoke(Control);
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void clearOutput(ActionEvent ignore) {
        try {
            Method clearOutput = Class.forName(Control.getClass().getName()).getMethod("clearOutput");
            clearOutput.invoke(Control);
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    /**
     * Processor for method DirectIO
     */
    class DirectIOHandler extends MethodProcessor {
        /**
         * Constructor. Stores method name <b>DirectIO</b> and parameters <i>command, data, object</i>.
         * @param command Parameter <i>command</i>
         * @param data Parameter <i>data</i>
         * @param object Parameter <i>object</i>
         */
        DirectIOHandler(int command, int[] data, Object[] object) {
            super("DirectIO");
            Command = command;
            Data = data;
            Obj = object;
        }

        private final int Command;
        private final int[] Data;
        private final Object[] Obj;

        @Override
        public void runIt() throws JposException {
            Control.directIO(Command, Data, Obj);
        }

        @Override
        void finish() {
            super.finish();
            if (Data != null)
                DIO_data.setText(Integer.toString(Data[0]));
            if (Obj != null && Obj[0] != null)
                DIO_obj.setText(Obj[0].toString());
        }
    }

    @FXML
    public void directIO(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        Integer cmd = new IntValues().getInteger(DIO_command.getText());
        Integer dataval = new IntValues().getInteger(DIO_data.getText());
        Object[] obj = new Object[]{DIO_obj.getText()};
        int[] data = null;
        if (invalid(cmd, "command"))
            return;
        if (dataval != null) {
            data = new int[]{dataval};
        }
        new DirectIOHandler(cmd, data, obj).start();
    }

    @FXML
    public void compareFirmwareVersion(ActionEvent ignore) {
        try {
            Method compareFirmwareVersion = Class.forName(Control.getClass().getName()).getMethod("compareFirmwareVersion", String.class, int[].class);
            int[] result = new int[1];
            compareFirmwareVersion.invoke(Control, CFV_firmwareFileName.getText(), result);
            CFV_result.setText(new CFV_resultValues().getSymbol(result[0]));
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void browseCFVName(ActionEvent ignore) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Firmware File");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showOpenDialog(null);
        if (selected != null)
            CFV_firmwareFileName.setText(selected.toString());
    }

    @FXML
    public void updateFirmware(ActionEvent ignore) {
        try {
            Method updateFirmware = Class.forName(Control.getClass().getName()).getMethod("updateFirmware", String.class);
            updateFirmware.invoke(Control, UF_firmwareFileName.getText());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void browseUFName(ActionEvent ignore) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Firmware File");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showOpenDialog(null);
        if (selected != null)
            UF_firmwareFileName.setText(selected.toString());
    }

    /**
     * Processor for method UpdateStatistics
     */
    class UpdateStatisticsHandler extends MethodProcessor {
        /**
         * Constructor. Stores method name <b>UpdateStatistics</b> and parameter <i>statisticsBuffer</i>.
         * @param statisticsBuffer Parameter <i>statisticsBuffer</i>
         */
        UpdateStatisticsHandler(String statisticsBuffer) {
            super("UpdateStatistics");
            StatisticsBuffer = statisticsBuffer;
        }

        private final String StatisticsBuffer;

        @Override
        public void runIt() throws JposException {
            try {
                Method updateStatistics = Class.forName(Control.getClass().getName()).getMethod("updateStatistics", String.class);
                updateStatistics.invoke(Control, StatisticsBuffer);
            } catch (Exception e) {
                if (e instanceof InvocationTargetException) {
                    Exception ee = (Exception)((InvocationTargetException) e).getTargetException();
                    if (ee instanceof JposException)
                        throw (JposException) ee;
                    throw new JposException(JposConst.JPOS_E_FAILURE, ee.getMessage(), ee);
                }
                throw new JposException(JposConst.JPOS_E_FAILURE, e.getMessage(), e);
            }
        }
    }

    @FXML
    public void updateStatistics(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        new UpdateStatisticsHandler(_statisticsBuffer.getText()).start();
    }


    /**
     * Processor for method RetrieveStatistics
     */
    class RetrieveStatisticsHandler extends MethodProcessor {
        /**
         * Constructor. Stores method name <b>RetrieveStatistics</b> and parameter <i>statisticsBuffer</i>.
         * @param statisticsBuffer Parameter <i>statisticsBuffer</i>
         */
        RetrieveStatisticsHandler(String statisticsBuffer) {
            super("RetrieveStatistics");
            StatisticsBuffer = statisticsBuffer;
        }

        private String StatisticsBuffer;

        @Override
        public void runIt() throws JposException {
            try {
                String[] array = {StatisticsBuffer};
                Method retrieveStatistics = Class.forName(Control.getClass().getName()).getMethod("retrieveStatistics", String[].class);
                retrieveStatistics.invoke(Control, (Object) array);
                StatisticsBuffer = array[0];
            } catch (Exception e) {
                if (e instanceof InvocationTargetException) {
                    Exception ee = (Exception)((InvocationTargetException) e).getTargetException();
                    if (ee instanceof JposException)
                        throw (JposException) ee;
                    throw new JposException(JposConst.JPOS_E_FAILURE, ee.getMessage(), ee);
                }
                throw new JposException(JposConst.JPOS_E_FAILURE, e.getMessage(), e);
            }
        }

        @Override
        void finish() {
            super.finish();
            _statisticsBuffer.setText(StatisticsBuffer);
        }
    }

    @FXML
    public void retrieveStatistics(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        new RetrieveStatisticsHandler(_statisticsBuffer.getText()).start();
    }

    /**
     * Processor for method ResetStatistics
     */
    class ResetStatisticsHandler extends MethodProcessor {
        /**
         * Constructor. Stores method name <b>ResetStatistics</b> and parameter <i>statisticsBuffer</i>.
         * @param statisticsBuffer Parameter <i>statisticsBuffer</i>
         */
        ResetStatisticsHandler(String statisticsBuffer) {
            super("ResetStatistics");
            StatisticsBuffer = statisticsBuffer;
        }

        private final String StatisticsBuffer;

        @Override
        public void runIt() throws JposException {
            try {
                Method resetStatistics = Class.forName(Control.getClass().getName()).getMethod("resetStatistics", String.class);
                resetStatistics.invoke(Control, StatisticsBuffer);
            } catch (Exception e) {
                if (e instanceof InvocationTargetException) {
                    Exception ee = (Exception)((InvocationTargetException) e).getTargetException();
                    if (ee instanceof JposException)
                        throw (JposException) ee;
                    throw new JposException(JposConst.JPOS_E_FAILURE, ee.getMessage(), ee);
                }
                throw new JposException(JposConst.JPOS_E_FAILURE, e.getMessage(), e);
            }
        }
    }

    @FXML
    public void resetStatistics(ActionEvent ignore) {
        if (isMethodRunning())
            return;
        new ResetStatisticsHandler(_statisticsBuffer.getText()).start();
    }

    /**
     * Enumerator for possible conversions for binary properties.
     */
    public enum ByteConversion {
        Hexadecimal,
        Ascii,
        Decoded,
        Length
    }

    static class ByteConversionValues extends Values {
        ByteConversionValues() {
            ValueList = new Object[]{
                    ByteConversion.Hexadecimal, "Hexadecimal",
                    ByteConversion.Ascii, "Ascii",
                    ByteConversion.Decoded, "Decoded",
                    ByteConversion.Length, "Length"
            };
        }
        ByteConversion getByteConversion(String symbol) {
            return (ByteConversion) getValue(symbol);
        }
    }

    public ByteConversion Conversion = ByteConversion.Decoded;
    public int MaxConversionLength = 160;
    public String BinaryEncoding = null;
    public boolean InUpdateGui = false;

    public void setByteConversion(ActionEvent ignore) {
        Conversion = ByteArrayConversion.getValue();
        updateGui();
    }

    /**
     * Update GUI elements that correspond to property values.
     */
    void updateGui() {
        if (!InUpdateGui) {
            InUpdateGui = true;
            if (ByteArrayConversion != null) {
                if (ByteArrayConversion.getItems().size() == 0) {
                    ByteArrayConversion.getItems().add(ByteConversion.Hexadecimal);
                    ByteArrayConversion.getItems().add(ByteConversion.Ascii);
                    ByteArrayConversion.getItems().add(ByteConversion.Decoded);
                    ByteArrayConversion.getItems().add(ByteConversion.Length);
                }
                ByteArrayConversion.setValue(Conversion);
            }
            for (PropertyTableRow row : Properties.getItems()) {
                try {
                    Method getter = Class.forName(Control.getClass().getName()).getMethod("get" + row.getName());
                    Object o = getter.invoke(Control);
                    if (o instanceof byte[]) {
                        byte[] data = (byte[]) o;
                        String result = "";
                        switch (Conversion) {
                            case Hexadecimal: {
                                if (data.length > MaxConversionLength / 2)
                                    data = Arrays.copyOf(data, MaxConversionLength / 2);
                                row.setValue(byteArrayToHexString(data, data.length, false, 0));
                                break;
                            }
                            case Ascii: {
                                if (data.length > MaxConversionLength)
                                    data = Arrays.copyOf(data, MaxConversionLength);
                                result = byteArrayToAsciiString(data, MaxConversionLength * 4);
                                row.setValue(result.length() > MaxConversionLength ? result.substring(0, MaxConversionLength) : result);
                                break;
                            }
                            case Decoded: {
                                String value = new String(data);
                                try {
                                    if (BinaryEncoding != null)
                                        value = new String(data, BinaryEncoding);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                row.setValue(value.length() <= MaxConversionLength ? value : value.substring(0, MaxConversionLength));
                                break;
                            }
                            case Length: {
                                row.setValue("[" + data.length + " Byte]");
                                break;
                            }
                        }
                    } else
                        row.setValue(row.getValueConverter() == null ? o.toString() : row.getValueConverter().getSymbol((int) o));
                } catch (Throwable e) {
                    if (e instanceof InvocationTargetException && !(((InvocationTargetException) e).getTargetException() instanceof JposException))
                        e.printStackTrace();
                    row.setValue("");
                }
            }
            try {
                DeviceEnabled.setSelected((boolean) Class.forName(Control.getClass().getName()).getMethod("getDeviceEnabled").invoke(Control));
            } catch (Exception e) {
                DeviceEnabled.setSelected(false);
            }
            try {
                FreezeEvents.setSelected((boolean) Class.forName(Control.getClass().getName()).getMethod("getFreezeEvents").invoke(Control));
            } catch (Exception e) {
                FreezeEvents.setSelected(false);
            }
            if (AutoDisable != null) {
                try {
                    AutoDisable.setSelected((boolean) Class.forName(Control.getClass().getName()).getMethod("getAutoDisable").invoke(Control));
                } catch (Exception e) {
                    AutoDisable.setSelected(false);
                }
            }
            if (DataEventEnabled != null) {
                try {
                    DataEventEnabled.setSelected((boolean) Class.forName(Control.getClass().getName()).getMethod("getDataEventEnabled").invoke(Control));
                } catch (Exception e) {
                    DataEventEnabled.setSelected(false);
                }
            }
            if (PowerNotify != null) {
                PowerNotifyValues values = new PowerNotifyValues();
                if (PowerNotify.getItems().size() == 0) {
                    PowerNotify.getItems().add(values.getSymbol(JposConst.JPOS_PN_DISABLED));
                    PowerNotify.getItems().add(values.getSymbol(JposConst.JPOS_PN_ENABLED));
                }
                try {
                    PowerNotify.setValue(values.getSymbol((int) Class.forName(Control.getClass().getName()).getMethod("getPowerNotify").invoke(Control)));
                } catch (Exception e) {
                    PowerNotify.setValue("");
                }
            }
            if (AsyncMode != null) {
                try {
                    AsyncMode.setSelected((boolean) Class.forName(Control.getClass().getName()).getMethod("getAsyncMode").invoke(Control));
                } catch (Exception e) {
                    AsyncMode.setSelected(false);
                }
            }
            if (claim_timeout != null && claim_timeout.getItems().size() == 0)
                claim_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
            if (CH_level != null && CH_level.getItems().size() == 0) {
                CH_levelValues values = new CH_levelValues();
                CH_level.getItems().add(values.getSymbol(JposConst.JPOS_CH_INTERNAL));
                CH_level.getItems().add(values.getSymbol(JposConst.JPOS_CH_EXTERNAL));
                CH_level.getItems().add(values.getSymbol(JposConst.JPOS_CH_INTERACTIVE));
            }
            InUpdateGui = false;
        }
    }

    /**
     * Convert byte array to hexadecimal string. limit specifies the maximum number of bytes that will be converted.
     * If separator is true, whitespace characters (' ' or '\n') will be inserted between bytes, if false, each byte
     * will be represented by two hexadecimal digits. If separators will be used, bytesPerLine specifies the maximum
     * number of space separators between a newline separator will be inserted.
     * <br>If data.length &gt; limit, "..." will be appended to the string.
     *
     * @param data  byte array to be converted.
     * @param limit Maximum number of bytes to be converted.
     * @param separator If true, byte separators will be used.
     * @param bytesPerLine Number of bytes per line. If &lt; 1, no newline will be used as separator.
     * @return Hexadecimal string
     */
    public String byteArrayToHexString(byte[] data, int limit, boolean separator, int bytesPerLine) {
        StringBuilder result = new StringBuilder();
        int i;
        for (i = 0; i < limit && i < data.length; i++) {
            if (!separator) {
                result.append(String.format("%02X", 0xff & (int) data[i]));
            } else {
                if (result.length() > 0)
                    result.append((bytesPerLine > 0 && i % bytesPerLine == 0) ? "\n" : " ");
                result.append(String.format("%02X", 0xff & (int) data[i]));
            }
        }
        if (i < data.length)
            result.append("...");
        return result.toString();
    }

    /**
     * Converts hexadecimal string to byte array. Whitespace are allowed as byte separators and will be skipped.
     * <br>Parameter strong specifies the behavior when data contains invalid characters:
     * <ul>
     *     <li>If strong is true, this method returns null if data contains an invalid character.</li>
     *     <li>If strong is false, this method returns a byte array that represents the contents of the string
     *     up to the invalid character.</li>
     * </ul>
     * @param data  Hexadecimal string.
     * @param strong Specifies behavior in case of invalid string.
     * @return The corresponding byte array.
     */
    public byte[] hexStringToByteArray(String data, boolean strong) {
        data = data.toUpperCase();
        byte[] result = new byte[data.length()];
        int i, j = 0;
        for (i = 0; i < result.length; i++) {
            try {
                while (j < data.length() && (data.charAt(j) & 0xff) <= 0x20)
                    j++;
                if (j >= data.length())
                    break;
                byte b = 0;
                for (int k = 0; k < 2 && j < data.length(); k++, j++) {
                    int c = data.charAt(j);
                    byte v = (byte) "0123456789ABCDEF".indexOf(c);
                    if (v < 0) {
                        if (c <= 0x20)            // whitespace: byte finished
                            break;
                        if (strong)               // No hex digit, no whitespace: Break all loops
                            return null;
                        throw new Exception("");
                    }
                    b = (byte) (b * 0x10 + v);
                }
                result[i] = b;
            } catch (Exception e) {
                break;
            }
        }
        return Arrays.copyOf(result, i);
    }

    /**
     * Converts hexadecimal string to byte array. Whitespace are allowed as byte separators and will be skipped.
     * @param data  Hexadecimal string.
     * @return The corresponding byte array.
     */
    public byte[] hexStringToByteArray(String data) {
        return hexStringToByteArray(data, false);
    }

    /**
     * Converts byte array to ASCII string with backslash escape sequences: A single backslash will be replaced by
     * two backslashes and byte values &lt; 0x20 and 0x7f will be replaced by one backslash, followed by the byte value
     * in octal representation. If maxLineLength &gt; 5, backslash followed by newline will be inserted whenever a
     * line would become longer than maxLineLength characters. Escape sequences will not be separated by
     * backslash-newline.
     * @param data          Byte array to be converted.
     * @param maxLineLength Maximum length of a line within the converted string
     * @return Resulting ASCII string with escape sequences.
     */
    public String byteArrayToAsciiString(byte[]data, int maxLineLength) {
        StringBuilder result = new StringBuilder();
        int len = 0;
        for (int i = 0; i < data.length; i++) {
            String c;
            if (data[i] < 0x20 || data[i] > 0x7e)
                c = String.format("\\%03o", 0xff & (int) data[i]);
            else if (data[i] == '\\')
                c = "\\\\";
            else
                c = new String(data, i, 1);
            if (len + c.length() + 1 > maxLineLength && maxLineLength > 5) {
                result.append("\\\n");
                len = c.length();
            } else
                len += c.length();
            result.append(c);
        }
        return result.toString();
    }

    /**
     * Converts ASCII string to byte array. The following backslash escape sequences are allowed:
     * <ul>
     *     <li>backslash backslash: Will be replaced by a single backslash.</li>
     *     <li>backslash newline: Only for better visibility, will be discarded.</li>
     *     <li>backslash ol1 ol2 ol3: Will be replaced by (byte)((ol1 * 8 + ol2) * 8 + ol3. ol2 and ol3 must be
     *     octal letters and ol1 must be between 0 and 3.</li>
     * </ul>
     * Whitespace characters will be discarded.
     * @param data  Ascii string.
     * @return The corresponding byte array.
     */
    public byte[] asciiStringToByteArray(String data) {
        byte[] result = new byte[data.length()];
        int i = 0, j = 0;
        while (i < result.length && j < data.length()) {
            int c = data.charAt(j++);
            if (c > 0x7e)
                break;      // Not an ASCII string
            if (c >= 0x20) {
                if (c != '\\')
                    result[i++] = (byte) c;
                else if (j < data.length()){
                    c = data.charAt(j++);
                    if (c == '\\') {
                        result[i++] = '\\';
                    } else if (c != '\n') {
                        if ((c = "0123".indexOf(c)) < 0 || j + 2 > data.length())
                            break;  // Invalid character: Stop conversion
                        else {
                            int k = -1;
                            while (++k < 2) {
                                int oc = "01234567".indexOf(data.charAt(j++));
                                if (oc < 0)
                                    break;
                                c = c * 8 + oc;
                            }
                            if (k < 2)
                                break;
                            result[i++] = (byte)c;
                        }
                    }
                }
            }
        }
        return Arrays.copyOf(result, i);
    }

    public void updateGuiLater() {
        Platform.runLater(this::updateGui);
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DeviceControl resource = (DeviceControl)(((DeviceControl.DeviceResources)resourceBundle).getContents()[0][0]);
        Control = resource.getControl();
        LogicalDeviceName = resource.getName();
        resource.Controller = this;
        Properties.getItems().add(new PropertyTableRow("State", new StateValues().getSymbol(Control.getState()), new StateValues()));
        Properties.getItems().add(new PropertyTableRow("PowerState", "", new PowerStateValues()));
        TableColumn<PropertyTableRow, String> column = new TableColumn<>("Name");
        if (PropertyNameColumnWidth > 0)
            column.setPrefWidth(PropertyNameColumnWidth);
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PropertyTableRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PropertyTableRow, String> propertyTableRowStringCellDataFeatures) {
                return propertyTableRowStringCellDataFeatures.getValue().getNameProperty();
            }
        });
        Properties.getColumns().add(column);
        column = new TableColumn<>("Value");
        if (PropertyValueColumnWidth > 0)
            column.setPrefWidth(PropertyValueColumnWidth);
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PropertyTableRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PropertyTableRow, String> propertyTableRowStringCellDataFeatures) {
                return propertyTableRowStringCellDataFeatures.getValue().getValueProperty();
            }
        });
        Properties.getColumns().add(column);
    }

    int RowCount = 0;

    void output(String logline) {
        if (EventOutput != null) {
            EventOutput.setDisable(false);
            if (++RowCount > 1000) {
                --RowCount;
                EventOutput.deleteText(0, EventOutput.getText().indexOf("\n") + 1);
            }
            EventOutput.appendText(RowCount == 1 ? logline : "\n" + logline);
        }
    }

    @Override
    public void dataOccurred(DataEvent dataEvent) {
        Platform.runLater(() -> {
            gotData(dataEvent);
            if (LockDataEventEnabled != null && LockDataEventEnabled.isSelected()) {
                try {
                    Method setDataEventEnabled = Class.forName(Control.getClass().getName()).getMethod("setDataEventEnabled", Boolean.TYPE);
                    setDataEventEnabled.invoke(Control, true);
                    DataEventEnabled.setSelected(true);
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
            }
            updateGui();
        });
    }

    Values DataEventStatusValueConverter = new DataEventStatusValues();

    public void gotData(DataEvent event) {
        updateGui();
        output("DE: " + getLogString(event));
    }

    String getLogString(DataEvent event) {
        if (event instanceof JposDataEvent)
            return ((JposDataEvent) event).toLogString();
        return DataEventStatusValueConverter.getSymbol(event.getStatus());
    }

    @Override
    public void directIOOccurred(DirectIOEvent event) {
        SyncObject diowaiter = new SyncObject();
        Platform.runLater(() -> {
            gotDirectIO(event);
            diowaiter.signal();

        });
        diowaiter.suspend(SyncObject.INFINITE);
    }

    public void gotDirectIO(DirectIOEvent event) {
        updateGui();
        output("DIOE: " + getLogString(event));
    }

    String getLogString(DirectIOEvent event) {
        if (event instanceof JposDirectIOEvent)
            return ((JposDirectIOEvent) event).toLogString();
        StringBuilder add = new StringBuilder(event.getEventNumber() + " - " + event.getData() + ":");
        String[] object = event.getObject().toString().split("\n");
        for (String line : object) {
            add.append("\n  ").append(line);
        }
        return add.toString();
    }

    @Override
    public void errorOccurred(ErrorEvent errorEvent) {
        Platform.runLater(() -> {
            preGotError(errorEvent);
        });
        String message = getLogString(errorEvent);
        int doit = myOptionDialog("Error occurred:\n" + message + "\nClear error?");
        if (doit == JOptionPane.YES_OPTION)
            errorEvent.setErrorResponse(JposConst.JPOS_ER_CLEAR);
        else if (doit == JOptionPane.NO_OPTION && errorEvent.getErrorLocus() != JposConst.JPOS_EL_INPUT_DATA)
            errorEvent.setErrorResponse(JposConst.JPOS_ER_RETRY);
        Platform.runLater(() -> {
            gotError(errorEvent);
        });
    }

    static void myMessageDialog(String message) {
        synchronizedMessageBox(message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    static int myOptionDialog(String message) {
        SynchronizedMessageBox box = new SynchronizedMessageBox();
        box.synchronizedConfirmationBox(message, "Processing Error", null,
                "" + JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, JposConst.JPOS_FOREVER);
        return box.Result;
    }

    Values ErrorCodeExtendedValueConverter = new IntValues();

    String getLogString(ErrorEvent event) {
        String errorcodes = new ErrorLocusValues().getSymbol(event.getErrorLocus());
        errorcodes += " - " + new ErrorCodeValues().getSymbol(event.getErrorCode());
        if (event.getErrorCodeExtended() != 0) {
            if (event.getErrorCode() == JposConst.JPOS_E_EXTENDED)
                errorcodes += " - " + ErrorCodeExtendedValueConverter.getSymbol(event.getErrorCodeExtended());
            else
                errorcodes += " - " + event.getErrorCodeExtended();
        }
        if (event instanceof JposErrorEvent && !((JposErrorEvent)event).Message.equals(""))
            errorcodes += "\n   " + ((JposErrorEvent)event).Message;
        return errorcodes;
    }

    public void gotError(ErrorEvent ignore) {
        updateGui();
    }

    public void preGotError(ErrorEvent event) {
        updateGui();
        output("EE: " + getLogString(event));
    }

    @Override
    public void outputCompleteOccurred(OutputCompleteEvent event) {
        Platform.runLater(() -> {
            gotOutputComplete(event);
        });
    }

    public void gotOutputComplete(OutputCompleteEvent event) {
        updateGui();
        output("OC: " + getLogString(event));
    }

    String getLogString(OutputCompleteEvent event) {
        if (event instanceof JposOutputCompleteEvent)
            return ((JposOutputCompleteEvent) event).toLogString();
        return "ID = " + event.getOutputID();
    }

    @Override
    public void statusUpdateOccurred(StatusUpdateEvent event) {
        Platform.runLater(() -> {
            gotStatusUpdate(event);
        });
    }

    Values StatusUpdateEventStatusValueConverter = new StatusUpdateValues();

    public void gotStatusUpdate(StatusUpdateEvent event) {
        updateGui();
        output("SUE: " + getLogString(event));
    }

    String getLogString(StatusUpdateEvent event) {
        if (event instanceof JposStatusUpdateEvent)
            return ((JposStatusUpdateEvent) event).toLogString();
        String symbol = StatusUpdateEventStatusValueConverter.getSymbol(event.getStatus());
        return symbol.substring(symbol.indexOf("SUE_") == 0 ? 4 : 0);
    }

    private boolean InExceptionHandling = false;

    public String getFullErrorMessageAndPrintTrace(Throwable e) {
        if (!InExceptionHandling) {
            InExceptionHandling = true;
            String message = getFullErrorMessageAndPrintTrace(e);
            InExceptionHandling = false;
             myMessageDialog(message);
            return message;
        } else {
            if (e instanceof InvocationTargetException)
                e = ((InvocationTargetException) e).getTargetException();
            String ret = e.getClass().getSimpleName() + ": " + e.getMessage();
            if (e.getCause() != null)
                ret += "\n" + getFullErrorMessageAndPrintTrace(e.getCause());
            e.printStackTrace();
            return ret;
        }
    }

    public String getFullErrorMessageAndPrintTrace(boolean withDialog, Throwable e) {
        if (withDialog)
            return getFullErrorMessageAndPrintTrace(e);
        InExceptionHandling = true;
        String ret = getFullErrorMessageAndPrintTrace(e);
        InExceptionHandling = false;
        return ret;
    }

    public boolean invalid(Object obj, String name) {
        if (obj == null) {
            myMessageDialog("No valid " + name + " specified.");
            return true;
        }
        return false;
    }

    public boolean validate(Object[] values) {
        if (values.length % 2 != 0) {
            myMessageDialog("validate: Odd number of parameters, cannot validate (contact software developer)");
            return true;
        }
        for (int i = 0; i < values.length; i += 2) {
            if (invalid(values[i], values[i + 1].toString()))
                return false;
        }
        return true;
    }

    static class ErrorCodeValues extends Values {
        ErrorCodeValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_E_CLOSED, "E_CLOSED",
                    JposConst.JPOS_E_CLAIMED, "E_CLAIMED",
                    JposConst.JPOS_E_NOTCLAIMED, "E_NOTCLAIMED",
                    JposConst.JPOS_E_NOSERVICE, "E_NOSERVICE",
                    JposConst.JPOS_E_DISABLED, "E_DISABLED",
                    JposConst.JPOS_E_ILLEGAL, "E_ILLEGAL",
                    JposConst.JPOS_E_NOHARDWARE, "E_NOHARDWARE",
                    JposConst.JPOS_E_OFFLINE, "E_OFFLINE",
                    JposConst.JPOS_E_NOEXIST, "E_NOEXIST",
                    JposConst.JPOS_E_EXISTS, "E_EXISTS",
                    JposConst.JPOS_E_FAILURE, "E_FAILURE",
                    JposConst.JPOS_E_TIMEOUT, "E_TIMEOUT",
                    JposConst.JPOS_E_BUSY, "E_BUSY",
                    JposConst.JPOS_E_EXTENDED, "E_EXTENDED",
                    JposConst.JPOS_E_DEPRECATED, "E_DEPRECATED"
            };
        }
    }

    static class ErrorLocusValues extends Values {
        ErrorLocusValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_EL_INPUT, "EL_INPUT",
                    JposConst.JPOS_EL_INPUT_DATA, "EL_INPUT_DATA",
                    JposConst.JPOS_EL_OUTPUT, "EL_OUTPUT"
            };
        }
    }

    static class StatusUpdateValues extends Values {
        StatusUpdateValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_SUE_POWER_ONLINE, "SUE_POWER_ONLINE",
                    JposConst.JPOS_SUE_POWER_OFF, "SUE_POWER_OFF",
                    JposConst.JPOS_SUE_POWER_OFFLINE, "SUE_POWER_OFFLINE",
                    JposConst.JPOS_SUE_POWER_OFF_OFFLINE, "SUE_POWER_OFF_OFFLINE",
                    JposConst.JPOS_SUE_UF_PROGRESS, "SUE_UF_PROGRESS 0%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 1, "SUE_UF_PROGRESS 1%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 2, "SUE_UF_PROGRESS 2%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 3, "SUE_UF_PROGRESS 3%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 4, "SUE_UF_PROGRESS 4%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 5, "SUE_UF_PROGRESS 5%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 6, "SUE_UF_PROGRESS 6%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 7, "SUE_UF_PROGRESS 7%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 8, "SUE_UF_PROGRESS 8%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 9, "SUE_UF_PROGRESS 9%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 10, "SUE_UF_PROGRESS 10%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 11, "SUE_UF_PROGRESS 11%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 12, "SUE_UF_PROGRESS 12%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 13, "SUE_UF_PROGRESS 13%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 14, "SUE_UF_PROGRESS 14%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 15, "SUE_UF_PROGRESS 15%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 16, "SUE_UF_PROGRESS 16%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 17, "SUE_UF_PROGRESS 17%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 18, "SUE_UF_PROGRESS 18%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 19, "SUE_UF_PROGRESS 19%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 20, "SUE_UF_PROGRESS 20%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 21, "SUE_UF_PROGRESS 21%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 22, "SUE_UF_PROGRESS 22%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 23, "SUE_UF_PROGRESS 23%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 24, "SUE_UF_PROGRESS 24%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 25, "SUE_UF_PROGRESS 25%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 26, "SUE_UF_PROGRESS 26%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 27, "SUE_UF_PROGRESS 27%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 28, "SUE_UF_PROGRESS 28%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 29, "SUE_UF_PROGRESS 29%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 30, "SUE_UF_PROGRESS 30%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 31, "SUE_UF_PROGRESS 31%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 32, "SUE_UF_PROGRESS 32%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 33, "SUE_UF_PROGRESS 33%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 34, "SUE_UF_PROGRESS 34%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 35, "SUE_UF_PROGRESS 35%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 36, "SUE_UF_PROGRESS 36%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 37, "SUE_UF_PROGRESS 37%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 38, "SUE_UF_PROGRESS 38%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 39, "SUE_UF_PROGRESS 39%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 40, "SUE_UF_PROGRESS 40%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 41, "SUE_UF_PROGRESS 41%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 42, "SUE_UF_PROGRESS 42%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 43, "SUE_UF_PROGRESS 43%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 44, "SUE_UF_PROGRESS 44%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 45, "SUE_UF_PROGRESS 45%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 46, "SUE_UF_PROGRESS 46%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 47, "SUE_UF_PROGRESS 47%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 48, "SUE_UF_PROGRESS 48%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 49, "SUE_UF_PROGRESS 49%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 50, "SUE_UF_PROGRESS 50%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 51, "SUE_UF_PROGRESS 51%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 52, "SUE_UF_PROGRESS 52%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 53, "SUE_UF_PROGRESS 53%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 54, "SUE_UF_PROGRESS 54%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 55, "SUE_UF_PROGRESS 55%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 56, "SUE_UF_PROGRESS 56%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 57, "SUE_UF_PROGRESS 57%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 58, "SUE_UF_PROGRESS 58%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 59, "SUE_UF_PROGRESS 59%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 60, "SUE_UF_PROGRESS 60%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 61, "SUE_UF_PROGRESS 61%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 62, "SUE_UF_PROGRESS 62%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 63, "SUE_UF_PROGRESS 63%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 64, "SUE_UF_PROGRESS 64%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 65, "SUE_UF_PROGRESS 65%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 66, "SUE_UF_PROGRESS 66%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 67, "SUE_UF_PROGRESS 67%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 68, "SUE_UF_PROGRESS 68%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 69, "SUE_UF_PROGRESS 69%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 70, "SUE_UF_PROGRESS 70%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 71, "SUE_UF_PROGRESS 71%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 72, "SUE_UF_PROGRESS 72%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 73, "SUE_UF_PROGRESS 73%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 74, "SUE_UF_PROGRESS 74%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 75, "SUE_UF_PROGRESS 75%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 76, "SUE_UF_PROGRESS 76%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 77, "SUE_UF_PROGRESS 77%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 78, "SUE_UF_PROGRESS 78%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 79, "SUE_UF_PROGRESS 79%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 80, "SUE_UF_PROGRESS 80%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 81, "SUE_UF_PROGRESS 81%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 82, "SUE_UF_PROGRESS 82%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 83, "SUE_UF_PROGRESS 83%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 84, "SUE_UF_PROGRESS 84%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 85, "SUE_UF_PROGRESS 85%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 86, "SUE_UF_PROGRESS 86%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 87, "SUE_UF_PROGRESS 87%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 88, "SUE_UF_PROGRESS 88%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 89, "SUE_UF_PROGRESS 89%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 90, "SUE_UF_PROGRESS 90%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 91, "SUE_UF_PROGRESS 91%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 92, "SUE_UF_PROGRESS 92%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 93, "SUE_UF_PROGRESS 93%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 94, "SUE_UF_PROGRESS 94%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 95, "SUE_UF_PROGRESS 95%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 96, "SUE_UF_PROGRESS 96%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 97, "SUE_UF_PROGRESS 97%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 98, "SUE_UF_PROGRESS 98%",
                    JposConst.JPOS_SUE_UF_PROGRESS + 99, "SUE_UF_PROGRESS 99%",
                    JposConst.JPOS_SUE_UF_COMPLETE, "SUE_UF_COMPLETE",
                    JposConst.JPOS_SUE_UF_FAILED_DEV_OK, "SUE_UF_FAILED_DEV_OK",
                    JposConst.JPOS_SUE_UF_FAILED_DEV_UNRECOVERABLE, "SUE_UF_FAILED_DEV_UNRECOVERABLE",
                    JposConst.JPOS_SUE_UF_FAILED_DEV_NEEDS_FIRMWARE, "SUE_UF_FAILED_DEV_NEEDS_FIRMWARE",
                    JposConst.JPOS_SUE_UF_FAILED_DEV_UNKNOWN, "SUE_UF_FAILED_DEV_UNKNOWN",
                    JposConst.JPOS_SUE_UF_COMPLETE_DEV_NOT_RESTORED, "SUE_UF_COMPLETE_DEV_NOT_RESTORED"
            };
        }
    }

    static class IntValues extends Values {
        IntValues() {
            ValueList = new Object[]{
                    0, "0"
            };
        }
    }

    static class HexValues extends IntValues {
        @Override
        public String getSymbol(Object value) {
            for (int i = 0; i < ValueList.length - 1; i += 2) {
                if (value.equals(ValueList[i]))
                    return (String)ValueList[i + 1];
            }
            return Integer.toString((Integer) value, 16);
        }

        @Override
        public Integer getInteger(String symbol) {
            Object obj = getValue(symbol);

            if (obj == null && ValueList.length > 0) {
                if (ValueList[0].getClass() == Integer.class) {
                    try {
                        return Integer.parseInt(symbol, 16);
                    } catch (Exception ignored) {}
                }
            }
            return (Integer) obj;
        }
    }

    private static class DataEventStatusValues extends Values {
        DataEventStatusValues() {
            ValueList = new Object[]{
                    0, ""
            };
        }
    }
}
