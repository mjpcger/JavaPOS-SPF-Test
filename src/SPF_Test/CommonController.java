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

import de.gmxhome.conrad.jpos.jpos_base.JposErrorEvent;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;
import jpos.events.*;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for common properties, methods and events.
 */
public class CommonController implements Initializable, Runnable, DataListener, DirectIOListener, ErrorListener, OutputCompleteListener, StatusUpdateListener {
    public CheckBox AutoDisable;
    public CheckBox DeviceEnabled;
    public CheckBox FreezeEvents;
    public CheckBox DataEventEnabled;
    public CheckBox AsyncMode;

    /**
     * Converter class for values of PowerNotify property.
     */
    public class PowerNotifyValues extends Values {
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
    public class TimeoutValues extends Values {
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
    public class CH_levelValues extends Values {
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
    public class CFV_resultValues extends Values {
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
    public Label MethodActive;
    public ComboBox<ByteConversion> ByteArrayConversion;
    public ComboBox<Integer> CurrencyDigits;
    public BaseJposControl Control;
    public String LogicalDeviceName;
    public String Usage;

    /**
     * Converter class for CapPowerReporting values.
     */
    public class CapPowerReportingValues extends Values {
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
    public class PowerStateValues extends Values {
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
    public class StateValues extends Values {
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
    class PropertyTableRow {
        private StringProperty Name;
        private StringProperty Value;
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

    void string2Decimal(PropertyTableRow row) {
        try {
            row.setValue(new BigDecimal(Long.parseLong(row.getValue()))
                    .scaleByPowerOfTen(-4).setScale(CurrencyDigits.getValue(), RoundingMode.HALF_UP).toString());
        } catch (Exception e) {
            row.setValue("");
        }
    }

    Long getDecimal(PropertyTableRow row) {
        try {
            return new BigDecimal(row.getValue()).multiply(new BigDecimal(10000)).longValueExact();
        } catch (Exception e) {
            return null;
        }
    }

    void setDecimal(PropertyTableRow row, long value) {
        try {
            row.setValue(new BigDecimal(value).scaleByPowerOfTen(-4).setScale(CurrencyDigits.getValue(), RoundingMode.HALF_UP).toString());
        } catch (Exception e) {
            row.setValue("");
        }
    }

    void formatDecimalTextField(TextField field, int decimals) {
        try {
            field.setText(new BigDecimal(field.getText()).setScale(decimals, RoundingMode.HALF_UP).toString());
        } catch (Exception e) {
            field.setText(null);
        }
    }

    void setDecimalTextField(TextField field, long value) {
        try {
            field.setText(new BigDecimal(value).scaleByPowerOfTen(-4).setScale(CurrencyDigits.getValue(), RoundingMode.HALF_UP).toString());
        } catch (Exception e) {
            field.setText(null);
        }
    }

    void formatDecimalOnFocusLost(TextField field) {
        field.focusedProperty().addListener((ov, oldv, newv) -> {
            if (!newv) {
                formatDecimalTextField(field, CurrencyDigits.getValue());
            }
        });
    }

    void setPropertyOnFocusLost(TextField Tone2Volume, String propertyname) {
        Tone2Volume.focusedProperty().addListener((ov, oldv, newv) -> {
            if (!newv) {
                try {
                    Method setProperty = Class.forName(this.getClass().getName()).getMethod("set" + propertyname, ActionEvent.class);
                    ActionEvent ev = null;
                    setProperty.invoke(this, ev);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    public void setCurrencyDigits(ActionEvent actionEvent) {
        updateGui();
    }
    @FXML
    public void setAutoDisable(ActionEvent actionEvent) {
        try {
            Method setAutoDisable = Class.forName(Control.getClass().getName()).getMethod("setAutoDisable", Boolean.TYPE);
            setAutoDisable.invoke(Control, AutoDisable.isSelected());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void setDeviceEnabled(ActionEvent actionEvent) {
        try {
            Control.setDeviceEnabled(DeviceEnabled.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void setAsyncMode(ActionEvent actionEvent) {
        try {
            Method setAsyncMode = Class.forName(Control.getClass().getName()).getMethod("setAsyncMode", Boolean.TYPE);
            setAsyncMode.invoke(Control, AsyncMode.isSelected());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void setFreezeEvents(ActionEvent actionEvent) {
        try {
            Control.setFreezeEvents(FreezeEvents.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void setPowerNotify(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                Method setPowerNotify = Class.forName(Control.getClass().getName()).getMethod("setPowerNotify", Integer.TYPE);
                setPowerNotify.invoke(Control, new PowerNotifyValues().getInteger(PowerNotify.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    @FXML
    public void setDataEventEnabled(ActionEvent actionEvent) {
        try {
            Method setDataEventEnabled = Class.forName(Control.getClass().getName()).getMethod("setDataEventEnabled", Boolean.TYPE);
            setDataEventEnabled.invoke(Control, DataEventEnabled.isSelected());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void open(ActionEvent actionEvent) {
        try {
            Control.open(LogicalDeviceName);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void close(ActionEvent actionEvent) {
        try {
            Control.close();
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
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
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
            JOptionPane.showMessageDialog(null, "Method " + running.getClass().getSimpleName() + " is currently running");
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

        private int Timeout;

        @Override
        public void runIt() throws JposException {
            Control.claim(Timeout);
        }
    }

    @FXML
    public void claim(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer timeout = new TimeoutValues().getInteger(claim_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new ClaimHandler(timeout).start();
    }

    @FXML
    public void release(ActionEvent actionEvent) {
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

        private int Level;

        @Override
        public void runIt() throws JposException {
            Control.checkHealth(Level);
        }
    }

    @FXML
    public void checkHealth(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer level = new CH_levelValues().getInteger(CH_level.getValue());
        if (!invalid(level, "level"))
            new CheckHealthHandler(level).start();
    }

    @FXML
    public void clearInput(ActionEvent actionEvent) {
        try {
            Method clearInput = Class.forName(Control.getClass().getName()).getMethod("clearInput");
            clearInput.invoke(Control);
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void clearInputProperties(ActionEvent actionEvent) {
        try {
            Method clearInputProperties = Class.forName(Control.getClass().getName()).getMethod("clearInputProperties");
            clearInputProperties.invoke(Control);
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void clearOutput(ActionEvent actionEvent) {
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

        private int Command;
        private int[] Data;
        private Object[] Obj;

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
    public void directIO(ActionEvent actionEvent) {
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
    public void compareFirmwareVersion(ActionEvent actionEvent) {
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
    public void browseCFVName(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Firmware File");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showOpenDialog(null);
        if (selected != null)
            CFV_firmwareFileName.setText(selected.toString());
    }

    @FXML
    public void updateFirmware(ActionEvent actionEvent) {
        try {
            Method updateFirmware = Class.forName(Control.getClass().getName()).getMethod("updateFirmware", String.class);
            updateFirmware.invoke(Control, UF_firmwareFileName.getText());
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    @FXML
    public void browseUFName(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Firmware File");
        chooser.setInitialDirectory(new File("."));
        File selected = chooser.showOpenDialog(null);
        if (selected != null)
            UF_firmwareFileName.setText(selected.toString());
    }

    /**
     * Processor for method DirectIO
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

        private String StatisticsBuffer;

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
    public void updateStatistics(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new UpdateStatisticsHandler(_statisticsBuffer.getText()).start();
    }


    /**
     * Processor for method DirectIO
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
                /*
                ATTENTION: This is a workaround due to awful type mismatch in Java reflection when working with arrays.
                Solution: Use a copy of the array, created by Array class via reflection and set the necessary entries
                as expected:
                 */
                Object array = Array.newInstance(String.class, 1);      // Create the array
                Array.set(array, 0, StatisticsBuffer);                  // Set the statisticsBuffer input element
                Method retrieveStatistics = Class.forName(Control.getClass().getName()).getMethod("retrieveStatistics", String[].class);
                retrieveStatistics.invoke(Control, array);
                StatisticsBuffer = (String)Array.get(array, 0);         // Retrieve statisticsBuffer output element
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
    public void retrieveStatistics(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new RetrieveStatisticsHandler(_statisticsBuffer.getText()).start();
    }

    /**
     * Processor for method DirectIO
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

        private String StatisticsBuffer;

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
    public void resetStatistics(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new ResetStatisticsHandler(_statisticsBuffer.getText()).start();
    }

    /**
     * Enumerator for possible conversions for binary properties.
     */
    public static enum ByteConversion {
        Hexadecimal,
        Ascii,
        Decoded,
        Length
    }


    class ByteConversionValues extends Values {
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

    public void setByteConversion(ActionEvent actionEvent) {
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
                ByteConversionValues bcv = new ByteConversionValues();
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
                                for (byte b : data) {
                                    result += new String(new char[]{
                                            "0123456789ABCDEF".charAt((b & 0xf0) >> 4),
                                            "0123456789ABCDEF".charAt(b & 0xf)
                                    });
                                    if (result.length() >= MaxConversionLength - 1)
                                        break;
                                }
                                row.setValue(result);
                                break;
                            }
                            case Ascii: {
                                for (byte b : data) {
                                    if ((b & 0xff) >= 0x20 && (b & 0xff) < 0x7f && b != (byte) '\\' && b != (byte) ']') {
                                        result += new String(new char[]{(char) (b & 0xff)});
                                    } else if (b == (byte) '\\' || b == (byte) ']') {
                                        result += "\\" + new String(new byte[]{b});
                                    } else {
                                        result += "\\x" + new String(new char[]{
                                                "0123456789ABCDEF".charAt((b & 0xf0) >> 4),
                                                "0123456789ABCDEF".charAt(b & 0xf)
                                        });
                                    }
                                    if (result.length() >= MaxConversionLength - 1)
                                        break;
                                }
                                row.setValue(result);
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
                } catch (Exception e) {
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
                    PowerNotify.getSelectionModel().select(values.getSymbol((int) Class.forName(Control.getClass().getName()).getMethod("getPowerNotify").invoke(Control)));
                } catch (Exception e) {
                    PowerNotify.setValue(null);
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

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DeviceControl resource = (DeviceControl)(((DeviceControl.DeviceResources)resourceBundle).getContents()[0][0]);
        Control = resource.getControl();
        LogicalDeviceName = resource.getName();
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

    /**
     * GUI updater, used to perform GUI updates from threads that are not controlled by JavaFX.
     */
    public class GuiUpdater implements Runnable {
        @Override
        public void run() {
            updateGui();
        }
    }

    @Override
    public void dataOccurred(DataEvent dataEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gotData(dataEvent);
            }
        });
    }

    public void gotData(DataEvent event) {
        updateGui();
    }

    @Override
    public void directIOOccurred(DirectIOEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gotDirectIO(event);
            }
        });
    }

    public void gotDirectIO(DirectIOEvent event) {
        updateGui();
    }

    @Override
    public void errorOccurred(ErrorEvent errorEvent) {
        Platform.runLater(new GuiUpdater());
        String message = retrieveErrorPromptText(errorEvent);
        int doit = JOptionPane.showOptionDialog(null, "Error occurred:\n" + message + "\nClear error?", "Processing Error",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
        if (doit == JOptionPane.YES_OPTION)
            errorEvent.setErrorResponse(JposConst.JPOS_ER_CLEAR);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gotError(errorEvent);
            }
        });
    }

    Values ErrorCodeExtendedValueConverter = new IntValues();

    String retrieveErrorPromptText(ErrorEvent errorEvent) {
        String errorcodes = new ErrorLocusValues().getSymbol(errorEvent.getErrorLocus());
        errorcodes += " - " + new ErrorCodeValues().getSymbol(errorEvent.getErrorCode());
        if (errorEvent.getErrorCodeExtended() != 0) {
            if (errorEvent.getErrorCode() == JposConst.JPOS_E_EXTENDED)
                errorcodes += " - " + ErrorCodeExtendedValueConverter.getSymbol(errorEvent.getErrorCodeExtended());
            else
                errorcodes += " - " + errorEvent.getErrorCodeExtended();
        }
        if (errorEvent instanceof JposErrorEvent && !((JposErrorEvent)errorEvent).Message.equals(""))
            errorcodes += "\n" + ((JposErrorEvent)errorEvent).Message;
        return errorcodes;
    }

    public void gotError(ErrorEvent event) {
        updateGui();
    }

    @Override
    public void outputCompleteOccurred(OutputCompleteEvent outputCompleteEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gotOutputComplete(outputCompleteEvent);
            }
        });
    }

    public void gotOutputComplete(OutputCompleteEvent outputCompleteEvent) {
        updateGui();
    }

    @Override
    public void statusUpdateOccurred(StatusUpdateEvent statusUpdateEvent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gotStatusUpdate(statusUpdateEvent);
            }
        });
    }

    public void gotStatusUpdate(StatusUpdateEvent statusUpdateEvent) {
        updateGui();
    }

    private boolean InExceptionHandling = false;

    public String getFullErrorMessageAndPrintTrace(Throwable e) {
        if (!InExceptionHandling) {
            InExceptionHandling = true;
            String message = getFullErrorMessageAndPrintTrace(e);
            InExceptionHandling = false;
            JOptionPane.showMessageDialog(null, message);
            return message;
        } else {
            if (e instanceof InvocationTargetException)
                e = ((InvocationTargetException) e).getTargetException();
            String ret = e.getMessage();
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
            JOptionPane.showMessageDialog(null, "No valid " + name + " specified.");
            return true;
        }
        return false;
    }

    class ErrorCodeValues extends Values {
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

    class ErrorLocusValues extends Values {
        ErrorLocusValues() {
            ValueList = new Object[]{
                    JposConst.JPOS_EL_INPUT, "EL_INPUT",
                    JposConst.JPOS_EL_INPUT_DATA, "EL_INPUT_DATA",
                    JposConst.JPOS_EL_OUTPUT, "EL_OUTPUT"
            };
        }
    }

    class IntValues extends Values {
        IntValues() {
            ValueList = new Object[]{
                    0, "0"
            };
        }
    }

    class HexValues extends IntValues {
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
                    } catch (Exception e) {
                    }
                }
            }
            return (Integer) obj;
        }
    }
}
