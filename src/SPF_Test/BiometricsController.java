/*
 * Copyright 2021 Martin Conrad
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import jpos.*;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * GUI control for Biometrics properties, methods and events.
 */
public class BiometricsController extends CommonController {
    public ComboBox<String> Algorithm;
    public CheckBox RealTimeDataEnabled;
    public ComboBox<String> SensorColor;
    public ComboBox<String> SensorOrientation;
    public ComboBox<String> SensorType;
    public TextField BECreferenceBIR;
    public TextField BECpayload;
    public ComboBox<String> PPDsampleBIR;
    public TextField PPDprematchDataBIR;
    public TextField PPDprocessedBIR;
    public TextField MaxFARRequested;
    public TextField MaxFRRRequested;
    public ComboBox<Boolean> FARPrecedence;
    public TextField IreferenceBIRPopulation;
    public ComboBox<Integer> IcandidateRanking;
    public ComboBox<String> Itimeout;
    public ComboBox<String> IMsampleBIR;
    public TextField IMreferenceBIRPopulation;
    public ComboBox<Integer> IMcandidateRanking;
    public TextField VreferenceBIR;
    public TextField VadaptedBIR;
    public TextField Vresult;
    public TextField VFARAchieved;
    public TextField VFRRAchieved;
    public TextField Vpayload;
    public ComboBox<String> Vtimeout;
    public ComboBox<String> VMsampleBIR;
    public TextField VMreferenceBIR;
    public TextField VMadaptedBIR;
    public TextField VMresult;
    public TextField VMFARAchieved;
    public TextField VMFRRAchieved;
    public TextField VMpayload;
    private Biometrics TheBiometrics;
    private PropertyTableRow AlgorithmRow;
    private PropertyTableRow AlgorithmListRow;
    private PropertyTableRow BIRRow;
    private PropertyTableRow RawSensorDataRow;
    private PropertyTableRow RealTimeDataEnabledRow;
    private PropertyTableRow SensorColorRow;
    private PropertyTableRow SensorOrientationRow;
    private PropertyTableRow SensorTypeRow;
    private PropertyTableRow CapSensorColorRow;
    private PropertyTableRow CapSensorOrientationRow;
    private PropertyTableRow CapSensorTypeRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheBiometrics = (Biometrics) Control;
        TheBiometrics.addDataListener(this);
        TheBiometrics.addDirectIOListener(this);
        TheBiometrics.addErrorListener(this);
        TheBiometrics.addStatusUpdateListener(this);
        StatusUpdateEventStatusValueConverter = new BioStatusUpdateValues();
        Conversion = ByteConversion.Length;
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(AlgorithmRow = new PropertyTableRow("Algorithm", "", new Values()));
        Properties.getItems().add(AlgorithmListRow = new PropertyTableRow("AlgorithmList", ""));
        Properties.getItems().add(BIRRow = new PropertyTableRow("BIR", ""));
        Properties.getItems().add(RawSensorDataRow = new PropertyTableRow("RawSensorData", ""));
        Properties.getItems().add(new PropertyTableRow("SensorBPP", ""));
        Properties.getItems().add(new PropertyTableRow("SensorHeight", ""));
        Properties.getItems().add(new PropertyTableRow("SensorWidth", ""));
        Properties.getItems().add(RealTimeDataEnabledRow = new PropertyTableRow("RealTimeDataEnabled", ""));
        Properties.getItems().add(SensorColorRow = new PropertyTableRow("SensorColor", "", new SensorColorValues()));
        Properties.getItems().add(SensorOrientationRow = new PropertyTableRow("SensorOrientation", "", new SensorOrientationValues()));
        Properties.getItems().add(SensorTypeRow = new PropertyTableRow("SensorType", "", new SensorTypeValues()));
        Properties.getItems().add(new PropertyTableRow("CapPrematchData", ""));
        Properties.getItems().add(new PropertyTableRow("CapRawSensorData", ""));
        Properties.getItems().add(new PropertyTableRow("CapRealTimeData", ""));
        Properties.getItems().add(CapSensorColorRow = new PropertyTableRow("CapSensorColor", "", new HexValues()));
        Properties.getItems().add(CapSensorOrientationRow = new PropertyTableRow("CapSensorOrientation", "", new HexValues()));
        Properties.getItems().add(CapSensorTypeRow = new PropertyTableRow("CapSensorType", "", new HexValues()));
        Properties.getItems().add(new PropertyTableRow("CapTemplateAdaptation", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        FARPrecedence.getItems().add(false);
        FARPrecedence.getItems().add(true);
        Itimeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        IMsampleBIR.getItems().add("BIR");
        PPDsampleBIR.getItems().add("BIR");
        Vtimeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        VMsampleBIR.getItems().add("BIR");
        updateGui();
    }

    @Override
    void updateGui() {
        String algolist = AlgorithmListRow.getValue();
        String colors = CapSensorColorRow.getValue();
        String orientations = CapSensorOrientationRow.getValue();
        String types = CapSensorTypeRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            updateAlgorithm(algolist);
            RealTimeDataEnabled.setSelected(RealTimeDataEnabledRow.getValue().equals("true"));
            updateSensorColor(colors);
            updateSensorOrientation(orientations);
            updateSensorType(types);
            InUpdateGui = false;
        }
    }

    private void updateSensorType(String colors) {
        if (!CapSensorTypeRow.getValue().equals(colors)) {
            Integer mask = CapSensorTypeRow.getValueConverter().getInteger(CapSensorTypeRow.getValue());
            int[] maskBits = {
                    BiometricsConst.BIO_CST_FACIAL_FEATURES,
                    BiometricsConst.BIO_CST_VOICE,
                    BiometricsConst.BIO_CST_FINGERPRINT,
                    BiometricsConst.BIO_CST_IRIS,
                    BiometricsConst.BIO_CST_RETINA,
                    BiometricsConst.BIO_CST_HAND_GEOMETRY,
                    BiometricsConst.BIO_CST_SIGNATURE_DYNAMICS,
                    BiometricsConst.BIO_CST_KEYSTROKE_DYNAMICS,
                    BiometricsConst.BIO_CST_LIP_MOVEMENT,
                    BiometricsConst.BIO_CST_THERMAL_FACE_IMAGE,
                    BiometricsConst.BIO_CST_THERMAL_HAND_IMAGE,
                    BiometricsConst.BIO_CST_GAIT,
                    BiometricsConst.BIO_CST_PASSWORD
            };
            SensorType.getItems().clear();
            if (mask != null) {
                for (int i = 0; i < maskBits.length; i++) {
                    if ((mask & maskBits[i]) != 0)
                        SensorType.getItems().add(SensorTypeRow.getValueConverter().ValueList[i + i + 1].toString());
                }
            }
        }
        for (String val : SensorType.getItems()) {
            if (SensorTypeRow.getValue().equals(val))
                SensorType.setValue(val);
        }
    }

    private void updateSensorOrientation(String colors) {
        if (!CapSensorOrientationRow.getValue().equals(colors)) {
            Integer mask = CapSensorOrientationRow.getValueConverter().getInteger(CapSensorOrientationRow.getValue());
            int[] maskBits = {
                    BiometricsConst.BIO_CSO_NORMAL,
                    BiometricsConst.BIO_CSO_RIGHT,
                    BiometricsConst.BIO_CSO_INVERTED,
                    BiometricsConst.BIO_CSO_LEFT
            };
            SensorOrientation.getItems().clear();
            if (mask != null) {
                for (int i = 0; i < maskBits.length; i++) {
                    if ((mask & maskBits[i]) != 0)
                        SensorOrientation.getItems().add(SensorOrientationRow.getValueConverter().ValueList[i + i + 1].toString());
                }
            }
        }
        for (String val : SensorOrientation.getItems()) {
            if (SensorOrientationRow.getValue().equals(val))
                SensorOrientation.setValue(val);
        }
    }

    private void updateSensorColor(String colors) {
        if (!CapSensorColorRow.getValue().equals(colors)) {
            Integer mask = CapSensorColorRow.getValueConverter().getInteger(CapSensorColorRow.getValue());
            int[] maskBits = {
                    BiometricsConst.BIO_CSC_MONO,
                    BiometricsConst.BIO_CSC_GRAYSCALE,
                    BiometricsConst.BIO_CSC_16,
                    BiometricsConst.BIO_CSC_256,
                    BiometricsConst.BIO_CSC_FULL
            };
            SensorColor.getItems().clear();
            if (mask != null) {
                for (int i = 0; i < maskBits.length; i++) {
                    if ((mask & maskBits[i]) != 0)
                        SensorColor.getItems().add(SensorColorRow.getValueConverter().ValueList[i + i + 1].toString());
                }
            }
        }
        for (String val : SensorColor.getItems()) {
            if (SensorColorRow.getValue().equals(val))
                SensorColor.setValue(val);
        }
    }

    private void updateAlgorithm(String algolist) {
        if (!AlgorithmListRow.getValue().equals(algolist)) {
            String[] algos = ("Default," + AlgorithmListRow.getValue()).split(",");
            Object[] values = new Object[algos.length * 2];
            Algorithm.getItems().clear();
            int j = 0;
            for (int i = 0; i < algos.length; i++) {
                if (!algos[i].equals("")) {
                    values[j++] = i;
                    Algorithm.getItems().add((values[j++] = algos[i]).toString());
                }
            }
            AlgorithmRow.getValueConverter().ValueList = Arrays.copyOf(values, j);
        }
        for (String val : Algorithm.getItems()) {
            if (val.equals(AlgorithmRow.getValue())) {
                Algorithm.setValue(val);
            }
        }
    }

    public void setAlgorithm(ActionEvent actionEvent) {
        Integer algo = AlgorithmRow.getValueConverter().getInteger(Algorithm.getValue());
        if (!InUpdateGui && algo != null) {
            try {
                TheBiometrics.setAlgorithm(algo);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setRealTimeDataEnabled(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheBiometrics.setRealTimeDataEnabled(RealTimeDataEnabled.isSelected());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setSensorColor(ActionEvent actionEvent) {
        Integer color = SensorColorRow.getValueConverter().getInteger(SensorColor.getValue());
        if (!InUpdateGui && color != null) {
            try {
                TheBiometrics.setSensorColor(color);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setSensorOrientation(ActionEvent actionEvent) {
        Integer orientation = SensorOrientationRow.getValueConverter().getInteger(SensorOrientation.getValue());
        if (!InUpdateGui && orientation != null) {
            try {
                TheBiometrics.setSensorOrientation(orientation);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    public void setSensorType(ActionEvent actionEvent) {
        Integer type = SensorTypeRow.getValueConverter().getInteger(SensorType.getValue());
        if (!InUpdateGui && type != null) {
            try {
                TheBiometrics.setSensorType(type);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGui();
        }
    }

    byte[] getFromFile(String filename, Boolean invalidFileAsByteArray) throws JposException {
        if (filename == null || filename.equals(""))
            return new byte[0];
        else {
            if (new File(filename).isFile()) {
                try {
                    RandomAccessFile source = new RandomAccessFile(filename, "r");
                    byte[] buffer = new byte[(int) source.length()];
                    try {
                        source.read(buffer);
                    } catch (Exception e) {
                        throw e;
                    } finally {
                        source.close();
                    }
                    return buffer;
                } catch (Exception e) {
                    throw new JposException(JposConst.JPOS_E_FAILURE, e.getMessage(), e);
                }
            } else if (invalidFileAsByteArray != null && invalidFileAsByteArray)
                return filename.getBytes();
            else if (invalidFileAsByteArray == null && filename.equals("BIR"))
                return TheBiometrics.getBIR();
            return null;
        }
    }

    void saveToFile(String filename, byte[] data) throws JposException {
        if (data == null)
            throw new JposException(JposConst.JPOS_E_DISABLED, "No contents available");
        if (filename != null && !filename.equals("")) {
            try {
                RandomAccessFile source = new RandomAccessFile(filename, "rw");
                try {
                    source.write(data);
                } finally {
                    source.close();
                }
            } catch (Exception e) {
                throw new JposException(JposConst.JPOS_E_FAILURE, e.getMessage(), e);
            }
        } else
            throw new JposException(JposConst.JPOS_E_FAILURE, "Target file not specified");
    }

    byte[][] getFromFiles(String files) throws JposException {
        byte[][] ret = null;
        if (files != null && !files.equals("")) {
            String[] filenames = files.split(",");
            ret = new byte[filenames.length][];
            for (int i = 0; i < filenames.length; i++) {
                if ((ret[i] = getFromFile(filenames[i], false)) == null || ret[i].length == 0)
                    throw new JposException(JposConst.JPOS_E_ILLEGAL, "Filename invalid: " + filenames[i]);
            }
        }
        return ret;
    }

    private class BeginEnrollCapture extends MethodProcessor {
        byte[] ReferenceBIR, Payload;

        BeginEnrollCapture(byte[] referenceBIR, byte[] payload) {
            super("BeginEnrollCapture");
            ReferenceBIR = referenceBIR;
            Payload = payload;
        }

        @Override
        void runIt() throws JposException {
            TheBiometrics.beginEnrollCapture(ReferenceBIR, Payload);
        }
    }

    public void beginEnrollCapture(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            try {
                byte[] referenceBIR = getFromFile(BECreferenceBIR.getText(), false);
                byte[] payload = getFromFile(BECpayload.getText(), true);
                if (!invalid(referenceBIR, "referenceBIR") && !invalid(payload, "payload")) {
                    new BeginEnrollCapture(referenceBIR, payload).start();
                }
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
    }

    FileChooser TheFileChooser = new FileChooser();

    public void setBECreferenceBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open File With referenceBIR contents");
        File name = TheFileChooser.showOpenDialog(null);
        if (name != null) {
            BECreferenceBIR.setText(name.getPath());
            TheFileChooser.setInitialDirectory(name.getParentFile());
        }
    }

    public void setBECpayload(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open File With payload contents");
        File name = TheFileChooser.showOpenDialog(null);
        if (name != null) {
            BECpayload.setText(name.getPath());
            TheFileChooser.setInitialDirectory(name.getParentFile());
        } else {
            try {
                BECpayload.setText(new String(ReturnedPayload));
            } catch (Exception e) {}
        }
    }

    private class BeginVerifyCapture extends MethodProcessor {
        BeginVerifyCapture() {
            super("BeginVerifyCapture");
        }

        @Override
        void runIt() throws JposException {
            TheBiometrics.beginVerifyCapture();
        }
    }

    public void beginVerifyCapture(ActionEvent actionEvent) {
        if (!isMethodRunning())
            new BeginVerifyCapture().start();
    }

    class EndCapture extends MethodProcessor {
        EndCapture() {
            super("EndCapture");
        }

        @Override
        void runIt() throws JposException {
            TheBiometrics.endCapture();
        }
    }

    public void endCapture(ActionEvent actionEvent) {
        if (!isMethodRunning())
            new EndCapture().start();
    }

    public void saveBIR(ActionEvent actionEvent) {
        try {
            byte[] bir = TheBiometrics.getBIR();
            TheFileChooser.setTitle("Save BIR As");
            File file = TheFileChooser.showSaveDialog(null);
            if (file != null) {
                saveToFile(file.getPath(), bir);
                TheFileChooser.setInitialDirectory(file.getParentFile());
            }
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    private byte[]ReturnedBIR;

    private class ProcessPrematchData extends MethodProcessor {
        byte[]SampleBIR;
        byte[]PrematchDataBIR;
        ProcessPrematchData(byte[]sampleBIR, byte[]prematchDataBIR) {
            super("ProcessPrematchData");
        }

        @Override
        void runIt() throws JposException {
            byte[][] rb = new byte[1][];
            TheBiometrics.processPrematchData(SampleBIR, PrematchDataBIR, rb);
            ReturnedBIR = rb[0];
        }
    }

    public void processPrematchData(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            try {
                byte[] sampleBIR = getFromFile(PPDsampleBIR.getValue(), null);
                byte[] prematchDataBIR = getFromFile(PPDprematchDataBIR.getText(), false);
                new ProcessPrematchData(sampleBIR, prematchDataBIR).start();
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
    }

    public void setPPDsampleBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open File With sampleBIR contents");
        File file = TheFileChooser.showOpenDialog(null);
        if (file != null) {
            PPDsampleBIR.setValue(file.getPath());
            TheFileChooser.setInitialDirectory(file.getParentFile());
        }
        else
            PPDsampleBIR.setValue("BIR");
    }

    public void setPPDprematchDataBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open File With prematchDataBIR contents");
        File file = TheFileChooser.showOpenDialog(null);
        if (file != null) {
            PPDprematchDataBIR.setText(file.getPath());
            TheFileChooser.setInitialDirectory(file.getParentFile());
        }
    }

    public void storePPDprocessedBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Save Contents of processedBIR to File");
        File file = TheFileChooser.showSaveDialog(null);
        if (file != null) {
            BECreferenceBIR.setText(file.getPath());
            try {
                saveToFile(file.getPath(), ReturnedBIR);
                TheFileChooser.setInitialDirectory(file.getParentFile());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
    }

    private class Identify extends MethodProcessor {
        int MaxFARRequested;
        int MaxFRRRequested;
        boolean FARPrecedence;
        byte[][] ReferenceBIRPopulation;
        int[][] CandidateRanking = new int[1][];
        int Timeout;
        Identify(int maxFARRequested, int maxFRRRequested, boolean farPrecedence, byte[][]referenceBIRPopulation, int timeout) {
            super("Identify");
            MaxFARRequested = maxFARRequested;
            MaxFRRRequested = maxFRRRequested;
            FARPrecedence = farPrecedence;
            ReferenceBIRPopulation = referenceBIRPopulation;
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            TheBiometrics.identify(MaxFARRequested, MaxFRRRequested, FARPrecedence, ReferenceBIRPopulation, CandidateRanking, Timeout);
        }

        @Override
        void finish() {
            super.finish();
            IcandidateRanking.getItems().clear();
            for (int i : CandidateRanking[0])
                IcandidateRanking.getItems().add(i);
        }
    }

    public void identify(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Values ival = new IntValues();
            Integer maxFARRequested = ival.getInteger(MaxFARRequested.getText());
            Integer maxFRRRequested = ival.getInteger(MaxFRRRequested.getText());
            Boolean farPrecedence = FARPrecedence.getValue();
            Integer timeout = new TimeoutValues().getInteger(Itimeout.getValue());
            if (!invalid(maxFARRequested, "maxFARRequested") && !invalid(maxFRRRequested, "maxFRRRequested") &&
                    !invalid(farPrecedence, "FARPrecedence") && !invalid(timeout, "timeout")) {
                try {
                    byte[][] referenceBIRPopulation = getFromFiles(IreferenceBIRPopulation.getText());
                    new Identify(maxFARRequested, maxFRRRequested,farPrecedence, referenceBIRPopulation, timeout).start();
                } catch (JposException e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
            }
        }
    }

    private class IdentifyMatch extends MethodProcessor {
        int MaxFARRequested;
        int MaxFRRRequested;
        boolean FARPrecedence;
        byte[][] ReferenceBIRPopulation;
        int[][] CandidateRanking = new int[1][];
        byte[] SampleBIR;
        IdentifyMatch(int maxFARRequested, int maxFRRRequested, boolean farPrecedence, byte[] sampleBIR, byte[][]referenceBIRPopulation) {
            super("IdentifyMatch");
            MaxFARRequested = maxFARRequested;
            MaxFRRRequested = maxFRRRequested;
            FARPrecedence = farPrecedence;
            ReferenceBIRPopulation = referenceBIRPopulation;
            SampleBIR = sampleBIR;
        }

        @Override
        void runIt() throws JposException {
            TheBiometrics.identifyMatch(MaxFARRequested, MaxFRRRequested, FARPrecedence, SampleBIR, ReferenceBIRPopulation, CandidateRanking);
        }

        @Override
        void finish() {
            super.finish();
            IcandidateRanking.getItems().clear();
            for (int i : CandidateRanking[0])
                IcandidateRanking.getItems().add(i);
        }
    }

    public void identifyMatch(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Values ival = new IntValues();
            Integer maxFARRequested = ival.getInteger(MaxFARRequested.getText());
            Integer maxFRRRequested = ival.getInteger(MaxFRRRequested.getText());
            Boolean farPrecedence = FARPrecedence.getValue();
            if (!invalid(maxFARRequested, "maxFARRequested") && !invalid(maxFRRRequested, "maxFRRRequested") &&
                    !invalid(farPrecedence, "FARPrecedence")) {
                try {
                    byte[] sampleBIR = getFromFile(IMsampleBIR.getValue(), null);
                    byte[][] referenceBIRPopulation = getFromFiles(IMreferenceBIRPopulation.getText());
                    new IdentifyMatch(maxFARRequested, maxFRRRequested,farPrecedence, sampleBIR, referenceBIRPopulation).start();
                } catch (JposException e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
            }
        }
    }

    public void setIreferenceBIRPopulation(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open Files With referenceBIRPopulation contents");
        List<File> files = TheFileChooser.showOpenMultipleDialog(null);
        if (files != null && files.size() > 0) {
            String names = "";
            for (File file : files)
                names += "," + file.getPath();
            IreferenceBIRPopulation.setText(names.substring(1));
            TheFileChooser.setInitialDirectory(files.get(0).getParentFile());
        }
    }

    public void setIMsampleBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open File With sampleBIR contents");
        File file = TheFileChooser.showOpenDialog(null);
        if (file != null) {
            IMsampleBIR.setValue(file.getPath());
            TheFileChooser.setInitialDirectory(file.getParentFile());
        }
        else
            IMsampleBIR.setValue("BIR");
    }

    public void setIMreferenceBIRPopulation(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open Files With referenceBIRPopulation contents");
        List<File> files = TheFileChooser.showOpenMultipleDialog(null);
        if (files != null && files.size() > 0) {
            String names = "";
            for (File file : files)
                names += "," + file.getPath();
            IMreferenceBIRPopulation.setText(names.substring(1));
            TheFileChooser.setInitialDirectory(files.get(0).getParentFile());
        }
    }

    private byte[]ReturnedPayload;

    private class Verify extends MethodProcessor {
        int MaxFARRequested;
        int MaxFRRRequested;
        boolean FARPrecedence;
        byte[] ReferenceBIR;
        byte[][] AdaptedBIR = new byte[1][];
        boolean[] Result = {false};
        int[] FARAchieved = {0};
        int[] FRRAchieved = {0};
        byte[][] Payload = new byte[1][];
        int Timeout;
        Verify(int maxFARRequested, int maxFRRRequested, boolean farPrecedence, byte[]referenceBIR, byte[]adaptedBIR, int timeout) {
            super("Verify");
            MaxFARRequested = maxFARRequested;
            MaxFRRRequested = maxFRRRequested;
            FARPrecedence = farPrecedence;
            ReferenceBIR = referenceBIR;
            AdaptedBIR[0] = adaptedBIR;
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            TheBiometrics.verify(MaxFARRequested, MaxFRRRequested, FARPrecedence, ReferenceBIR, AdaptedBIR, Result, FARAchieved, FRRAchieved, Payload, Timeout);
        }

        @Override
        void finish() {
            super.finish();
            ReturnedPayload = Payload[0];
            ReturnedBIR = AdaptedBIR[0];
            Vresult.setText(Result[0] ? "true" : "false");
            VFARAchieved.setText(Integer.toString(FARAchieved[0]));
            VFRRAchieved.setText(Integer.toString(FRRAchieved[0]));
        }
    }

    public void verify(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Values ival = new IntValues();
            Integer maxFARRequested = ival.getInteger(MaxFARRequested.getText());
            Integer maxFRRRequested = ival.getInteger(MaxFRRRequested.getText());
            Boolean farPrecedence = FARPrecedence.getValue();
            Integer timeout = new TimeoutValues().getInteger(Vtimeout.getValue());
            if (!invalid(maxFARRequested, "maxFARRequested") && !invalid(maxFRRRequested, "maxFRRRequested") &&
                    !invalid(farPrecedence, "FARPrecedence") && !invalid(timeout, "timeout")) {
                try {
                    byte[] referenceBIR = getFromFile(VreferenceBIR.getText(), false);
                    byte[] adaptedBIR = getFromFile(VadaptedBIR.getText(), false);
                    new Verify(maxFARRequested, maxFRRRequested,farPrecedence, referenceBIR, adaptedBIR, timeout).start();
                } catch (JposException e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
            }
        }
    }

    private class VerifyMatch extends MethodProcessor {
        int MaxFARRequested;
        int MaxFRRRequested;
        boolean FARPrecedence;
        byte[] ReferenceBIR;
        byte[][] AdaptedBIR = new byte[1][];
        boolean[] Result = {false};
        int[] FARAchieved = {0};
        int[] FRRAchieved = {0};
        byte[][] Payload = new byte[1][];
        byte[] SampleBIR;
        VerifyMatch(int maxFARRequested, int maxFRRRequested, boolean farPrecedence, byte[] sampleBIR, byte[]referenceBIR, byte[]adaptedBIR) {
            super("VerifyMatch");
            MaxFARRequested = maxFARRequested;
            MaxFRRRequested = maxFRRRequested;
            FARPrecedence = farPrecedence;
            ReferenceBIR = referenceBIR;
            AdaptedBIR[0] = adaptedBIR;
            SampleBIR = sampleBIR;
        }

        @Override
        void runIt() throws JposException {
            TheBiometrics.verifyMatch(MaxFARRequested, MaxFRRRequested, FARPrecedence, SampleBIR, ReferenceBIR, AdaptedBIR, Result, FARAchieved, FRRAchieved, Payload);
        }

        @Override
        void finish() {
            super.finish();
            ReturnedPayload = Payload[0];
            ReturnedBIR = AdaptedBIR[0];
            VMresult.setText(Result[0] ? "true" : "false");
            VMFARAchieved.setText(Integer.toString(FARAchieved[0]));
            VMFRRAchieved.setText(Integer.toString(FRRAchieved[0]));
        }
    }

    public void verifyMatch(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Values ival = new IntValues();
            Integer maxFARRequested = ival.getInteger(MaxFARRequested.getText());
            Integer maxFRRRequested = ival.getInteger(MaxFRRRequested.getText());
            Boolean farPrecedence = FARPrecedence.getValue();
            if (!invalid(maxFARRequested, "maxFARRequested") && !invalid(maxFRRRequested, "maxFRRRequested") &&
                    !invalid(farPrecedence, "FARPrecedence")) {
                try {
                    byte[] sampleBIR = getFromFile(VMsampleBIR.getValue(), null);
                    byte[] referenceBIR = getFromFile(VMreferenceBIR.getText(), false);
                    byte[] adaptedBIR = getFromFile(VMadaptedBIR.getText(), false);
                    new VerifyMatch(maxFARRequested, maxFRRRequested,farPrecedence, sampleBIR, referenceBIR, adaptedBIR).start();
                } catch (JposException e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
            }
        }
    }

    public void setVreferenceBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open File With referenceBIR contents");
        File file = TheFileChooser.showOpenDialog(null);
        if (file != null) {
            VreferenceBIR.setText(file.getPath());
            TheFileChooser.setInitialDirectory(file.getParentFile());
        }
    }

    public void setVadaptedBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open File With adaptedBIR contents");
        File file = TheFileChooser.showOpenDialog(null);
        if (file != null) {
            VadaptedBIR.setText(file.getPath());
            TheFileChooser.setInitialDirectory(file.getParentFile());
        }
    }

    public void saveVadaptedBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Save Contents of adaptedBIR to File");
        File file = TheFileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                saveToFile(file.getPath(), ReturnedBIR);
                TheFileChooser.setInitialDirectory(file.getParentFile());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
    }

    public void saveVpayload(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Save Contents of payload to File");
        File file = TheFileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                saveToFile(file.getPath(), ReturnedPayload);
                TheFileChooser.setInitialDirectory(file.getParentFile());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        } else {
            try {
                Vpayload.setText(new String(ReturnedPayload));
            } catch (Exception e) {}
        }
    }

    public void setVMsampleBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open File With sampleBIR contents");
        File file = TheFileChooser.showOpenDialog(null);
        if (file != null) {
            VMsampleBIR.setValue(file.getPath());
            TheFileChooser.setInitialDirectory(file.getParentFile());
        }
        else
            VMsampleBIR.setValue("BIR");
    }

    public void setVMreferenceBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open File With referenceBIR contents");
        File file = TheFileChooser.showOpenDialog(null);
        if (file != null) {
            VMreferenceBIR.setText(file.getPath());
            TheFileChooser.setInitialDirectory(file.getParentFile());
        }
    }

    public void setVMadaptedBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Open File With adaptedBIR contents");
        File file = TheFileChooser.showOpenDialog(null);
        if (file != null) {
            VMadaptedBIR.setText(file.getPath());
            TheFileChooser.setInitialDirectory(file.getParentFile());
        }
    }

    public void saveVMadaptedBIR(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Save Contents of adaptedBIR to File");
        File file = TheFileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                saveToFile(file.getPath(), ReturnedBIR);
                TheFileChooser.setInitialDirectory(file.getParentFile());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
    }

    public void saveVMpayload(ActionEvent actionEvent) {
        TheFileChooser.setTitle("Save Contents of payload to File");
        File file = TheFileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                saveToFile(file.getPath(), ReturnedPayload);
                TheFileChooser.setInitialDirectory(file.getParentFile());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        } else {
            try {
                VMpayload.setText(new String(ReturnedPayload));
            } catch (Exception e) {}
        }
    }

    private class BioStatusUpdateValues extends StatusUpdateValues {
        BioStatusUpdateValues() {
            super();
            Object[] biovalues = new Object[]{
                    BiometricsConst.BIO_SUE_RAW_DATA, "SUE_RAW_DATA",
                    BiometricsConst.BIO_SUE_MOVE_LEFT, "SUE_MOVE_LEFT",
                    BiometricsConst.BIO_SUE_MOVE_RIGHT, "SUE_MOVE_RIGHT",
                    BiometricsConst.BIO_SUE_MOVE_DOWN, "SUE_MOVE_DOWN",
                    BiometricsConst.BIO_SUE_MOVE_UP, "SUE_MOVE_UP",
                    BiometricsConst.BIO_SUE_MOVE_CLOSER, "SUE_MOVE_CLOSER",
                    BiometricsConst.BIO_SUE_MOVE_AWAY, "SUE_MOVE_AWAY",
                    BiometricsConst.BIO_SUE_MOVE_BACKWARD, "SUE_MOVE_BACKWARD",
                    BiometricsConst.BIO_SUE_MOVE_FORWARD, "SUE_MOVE_FORWARD",
                    BiometricsConst.BIO_SUE_MOVE_SLOWER, "SUE_MOVE_SLOWER",
                    BiometricsConst.BIO_SUE_MOVE_FASTER, "SUE_MOVE_FASTER",
                    BiometricsConst.BIO_SUE_SENSOR_DIRTY, "SUE_SENSOR_DIRTY",
                    BiometricsConst.BIO_SUE_FAILED_READ, "SUE_FAILED_READ",
                    BiometricsConst.BIO_SUE_SENSOR_READY, "SUE_SENSOR_READY",
                    BiometricsConst.BIO_SUE_SENSOR_COMPLETE, "SUE_SENSOR_COMPLETE"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + biovalues.length);
            System.arraycopy(biovalues, 0, ValueList, ValueList.length - biovalues.length, biovalues.length);
        }
    }

    private class SensorColorValues extends Values {
        SensorColorValues() {
            super();
            ValueList = new Object[]{
                    BiometricsConst.BIO_SC_MONO, "SC_MONO",
                    BiometricsConst.BIO_SC_GRAYSCALE, "SC_GRAYSCALE",
                    BiometricsConst.BIO_SC_16, "SC_16",
                    BiometricsConst.BIO_SC_256, "SC_256",
                    BiometricsConst.BIO_SC_FULL, "SC_FULL"
            };
        }
    }

    private class SensorOrientationValues extends Values {
        SensorOrientationValues() {
            super();
            ValueList = new Object[]{
                    BiometricsConst.BIO_SO_NORMAL, "SO_NORMAL",
                    BiometricsConst.BIO_SO_RIGHT, "SO_RIGHT",
                    BiometricsConst.BIO_SO_INVERTED, "SO_INVERTED",
                    BiometricsConst.BIO_SO_LEFT, "SO_LEFT"
            };
        }
    }

    private class SensorTypeValues extends Values {
        SensorTypeValues() {
            super();
            ValueList = new Object[]{
                    BiometricsConst.BIO_ST_FACIAL_FEATURES, "ST_FACIAL_FEATURES",
                    BiometricsConst.BIO_ST_VOICE, "ST_VOICE",
                    BiometricsConst.BIO_ST_FINGERPRINT, "ST_FINGERPRINT",
                    BiometricsConst.BIO_ST_IRIS, "ST_IRIS",
                    BiometricsConst.BIO_ST_RETINA, "ST_RETINA",
                    BiometricsConst.BIO_ST_HAND_GEOMETRY, "ST_HAND_GEOMETRY",
                    BiometricsConst.BIO_ST_SIGNATURE_DYNAMICS, "ST_SIGNATURE_DYNAMICS",
                    BiometricsConst.BIO_ST_KEYSTROKE_DYNAMICS, "ST_KEYSTROKE_DYNAMICS",
                    BiometricsConst.BIO_ST_LIP_MOVEMENT, "ST_LIP_MOVEMENT",
                    BiometricsConst.BIO_ST_THERMAL_FACE_IMAGE, "ST_THERMAL_FACE_IMAGE",
                    BiometricsConst.BIO_ST_THERMAL_HAND_IMAGE, "ST_THERMAL_HAND_IMAGE",
                    BiometricsConst.BIO_ST_GAIT, "ST_GAIT",
                    BiometricsConst.BIO_ST_PASSWORD, "ST_PASSWORD"
            };
        }
    }
}
