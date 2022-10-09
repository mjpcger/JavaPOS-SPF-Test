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
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import jpos.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for CheckScanner properties, methods and events.
 */
public class CheckScannerController extends CommonController {
    public CheckBox ConcurrentMICR;
    public ComboBox<String> Color;
    public ComboBox<String> Contrast;
    public TextField DocumentHeight;
    public TextField DocumentWidth;
    public TextField FileID;
    public TextField FileIndex;
    public ComboBox<String> ImageFormat;
    public ComboBox<String> MapMode;
    public ComboBox<String> Quality;
    public TextArea ImageTagData;
    public ComboBox<String> BI_timeout;
    public ComboBox<String> BR_timeout;
    public ComboBox<String> CI_by;
    public ComboBox<String> RI_cropAreaID;
    public ComboBox<String> RM_by;
    public ComboBox<String> SI_cropAreaID;
    public ComboBox<String> DCA_cropAreaID;
    public TextField DCA_x;
    public TextField DCA_y;
    public ComboBox<String> DCA_cx;
    public ComboBox<String> DCA_cy;
    private CheckScanner TheCheckScanner;
    private PropertyTableRow ImageMemoryStatusRow;
    private PropertyTableRow RemainingImagesEstimateRow;
    private PropertyTableRow ImageDataRow;
    private PropertyTableRow CapAutoContrastRow;
    private PropertyTableRow CapAutoGenerateFileIDRow;
    private PropertyTableRow CapAutoGenerateImageTagDataRow;
    private PropertyTableRow CapAutoSizeRow;
    private PropertyTableRow CapColorRow;
    private PropertyTableRow CapConcurrentMICRRow;
    private PropertyTableRow CapContrastRow;
    private PropertyTableRow CapDefineCropAreaRow;
    private PropertyTableRow CapImageFormatRow;
    private PropertyTableRow CapImageTagDataRow;
    private PropertyTableRow CapMICRDeviceRow;
    private PropertyTableRow CapStoreImageFilesRow;
    private PropertyTableRow CapValidationDeviceRow;
    private PropertyTableRow CropAreaCountRow;
    private PropertyTableRow MaxCropAreasRow;
    private PropertyTableRow QualityListRow;
    private PropertyTableRow ColorRow;
    private PropertyTableRow ConcurrentMICRRow;
    private PropertyTableRow ContrastRow;
    private PropertyTableRow DocumentHeightRow;
    private PropertyTableRow DocumentWidthRow;
    private PropertyTableRow FileIDRow;
    private PropertyTableRow FileIndexRow;
    private PropertyTableRow ImageFormatRow;
    private PropertyTableRow ImageTagDataRow;
    private PropertyTableRow MapModeRow;
    private PropertyTableRow QualityRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheCheckScanner = (CheckScanner) Control;
        TheCheckScanner.addDataListener(this);
        TheCheckScanner.addDirectIOListener(this);
        TheCheckScanner.addErrorListener(this);
        TheCheckScanner.addStatusUpdateListener(this);
        StatusUpdateEventStatusValueConverter = new CheckScannerStatusUpdateValues();
        Conversion = ByteConversion.Length;
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(ImageMemoryStatusRow = new PropertyTableRow("ImageMemoryStatus", "", new ImageMemoryStatusValues()));
        Properties.getItems().add(RemainingImagesEstimateRow = new PropertyTableRow("RemainingImagesEstimate", ""));
        Properties.getItems().add(ImageDataRow = new PropertyTableRow("ImageData", ""));
        Properties.getItems().add(CapAutoContrastRow = new PropertyTableRow("CapAutoContrast", ""));
        Properties.getItems().add(CapAutoGenerateFileIDRow = new PropertyTableRow("CapAutoGenerateFileID", ""));
        Properties.getItems().add(CapAutoGenerateImageTagDataRow = new PropertyTableRow("CapAutoGenerateImageTagData", ""));
        Properties.getItems().add(CapAutoSizeRow = new PropertyTableRow("CapAutoSize", ""));
        Properties.getItems().add(CapColorRow = new PropertyTableRow("CapColor", "", new HexValues()));
        Properties.getItems().add(CapConcurrentMICRRow = new PropertyTableRow("CapConcurrentMICR", ""));
        Properties.getItems().add(CapContrastRow = new PropertyTableRow("CapContrast", ""));
        Properties.getItems().add(CapDefineCropAreaRow = new PropertyTableRow("CapDefineCropArea", ""));
        Properties.getItems().add(CapImageFormatRow = new PropertyTableRow("CapImageFormat", "", new HexValues()));
        Properties.getItems().add(CapImageTagDataRow = new PropertyTableRow("CapImageTagData", ""));
        Properties.getItems().add(CapMICRDeviceRow = new PropertyTableRow("CapMICRDevice", ""));
        Properties.getItems().add(CapStoreImageFilesRow = new PropertyTableRow("CapStoreImageFiles", ""));
        Properties.getItems().add(CapValidationDeviceRow = new PropertyTableRow("CapValidationDevice", ""));
        Properties.getItems().add(CropAreaCountRow = new PropertyTableRow("CropAreaCount", ""));
        Properties.getItems().add(MaxCropAreasRow = new PropertyTableRow("MaxCropAreas", ""));
        Properties.getItems().add(QualityListRow = new PropertyTableRow("QualityList", ""));
        Properties.getItems().add(ColorRow = new PropertyTableRow("Color", "", new ColorValues()));
        Properties.getItems().add(ConcurrentMICRRow = new PropertyTableRow("ConcurrentMICR", ""));
        Properties.getItems().add(ContrastRow = new PropertyTableRow("Contrast", "", new ContrastValues()));
        Properties.getItems().add(DocumentHeightRow = new PropertyTableRow("DocumentHeight", ""));
        Properties.getItems().add(DocumentWidthRow = new PropertyTableRow("DocumentWidth", ""));
        Properties.getItems().add(FileIDRow = new PropertyTableRow("FileID", ""));
        Properties.getItems().add(FileIndexRow = new PropertyTableRow("FileIndex", ""));
        Properties.getItems().add(ImageFormatRow = new PropertyTableRow("ImageFormat", "", new ImageFormatValues()));
        Properties.getItems().add(ImageTagDataRow = new PropertyTableRow("ImageTagData", ""));
        Properties.getItems().add(MapModeRow = new PropertyTableRow("MapMode", "", new MapModeValues()));
        Properties.getItems().add(QualityRow = new PropertyTableRow("Quality", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        setPropertyOnFocusLost(DocumentHeight, "DocumentHeight");
        setPropertyOnFocusLost(DocumentWidth, "DocumentWidth");
        setPropertyOnFocusLost(FileID, "FileID");
        setPropertyOnFocusLost(FileIndex, "FileIndex");
        setPropertyOnFocusLost(ImageTagData, "ImageTagData");
        Object[] Values = new MapModeValues().ValueList;
        for (int i = 1; i < Values.length; i += 2)
            MapMode.getItems().add(Values[i].toString());
        BI_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        BR_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        RI_cropAreaID.getItems().add(new CropAreaValues().getSymbol(CheckScannerConst.CHK_CROP_AREA_ENTIRE_IMAGE));
    }

    @Override
    void updateGui() {
        String prevcolors = CapColorRow.getValue();
        String prevcontrast = CapContrastRow.getValue();
        String prevautocontrast = CapAutoContrastRow.getValue();
        String previmageformats = CapImageFormatRow.getValue();
        String prevqualitylist = QualityListRow.getValue();
        String prevstoreimages = CapStoreImageFilesRow.getValue();
        String prevcropareas = CapDefineCropAreaRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            ConcurrentMICR.setSelected(ConcurrentMICRRow.getValue().equals("true"));
            if (!prevcolors.equals(CapColorRow.getValue())) {
                int[][] pairs = {
                        {CheckScannerConst.CHK_CCL_MONO, CheckScannerConst.CHK_CL_MONO},
                        {CheckScannerConst.CHK_CCL_GRAYSCALE, CheckScannerConst.CHK_CL_GRAYSCALE},
                        {CheckScannerConst.CHK_CCL_16, CheckScannerConst.CHK_CL_16},
                        {CheckScannerConst.CHK_CCL_256, CheckScannerConst.CHK_CL_256},
                        {CheckScannerConst.CHK_CCL_FULL, CheckScannerConst.CHK_CL_FULL}
                };
                setupBox(CapColorRow, ColorRow, pairs, Color);
            }
            Color.setValue(ColorRow.getValue());
            if (!prevcontrast.equals(CapContrastRow.getValue())) {
                Contrast.getItems().clear();
                if (!prevautocontrast.equals(CapAutoContrastRow.getValue())) {
                    if (CapAutoContrastRow.getValue().equals("true"))
                        Contrast.getItems().add(ContrastRow.getValueConverter().getSymbol(CheckScannerConst.CHK_AUTOMATIC_CONTRAST));
                }
            }
            Contrast.setValue(ContrastRow.getValue());
            if (!previmageformats.equals(CapImageFormatRow.getValue())) {
                int[][] pairs = {
                        {CheckScannerConst.CHK_CIF_NATIVE, CheckScannerConst.CHK_IF_NATIVE},
                        {CheckScannerConst.CHK_CIF_TIFF, CheckScannerConst.CHK_IF_TIFF},
                        {CheckScannerConst.CHK_CIF_BMP, CheckScannerConst.CHK_IF_BMP},
                        {CheckScannerConst.CHK_CIF_JPEG, CheckScannerConst.CHK_IF_JPEG},
                        {CheckScannerConst.CHK_CIF_GIF, CheckScannerConst.CHK_IF_GIF}
                };
                setupBox(CapImageFormatRow, ImageFormatRow, pairs, ImageFormat);
            }
            ImageFormat.setValue(ImageFormatRow.getValue());
            if (!prevqualitylist.equals(QualityListRow)) {
                Quality.getItems().clear();
                if (!QualityListRow.getValue().equals("")) {
                    for (String val : QualityListRow.getValue().split(",")) {
                        Quality.getItems().add(val);
                    }
                }
            }
            Quality.setValue(QualityRow.getValue());
            DocumentHeight.setText(DocumentHeightRow.getValue());
            DocumentWidth.setText(DocumentWidthRow.getValue());
            FileID.setText(FileIDRow.getValue());
            FileIndex.setText(FileIndexRow.getValue());
            MapMode.setValue(MapModeRow.getValue());
            if (!prevstoreimages.equals(CapStoreImageFilesRow.getValue())) {
                CI_by.getItems().clear();
                RM_by.getItems().clear();
                SI_cropAreaID.getItems().clear();
                if (CapStoreImageFilesRow.getValue().equals("true")) {
                    Values vals = CapImageTagDataRow.getValue().equals("true") ?
                            new ClearImageWithTagValues() : new ClearImageValues();
                    for (int i = 1; i < vals.ValueList.length; i += 2)
                        CI_by.getItems().add(vals.ValueList[i].toString());
                    vals = CapImageTagDataRow.getValue().equals("true") ?
                            new RetrieveMemoryWithTagValues() : new RetrieveMemoryValues();
                    for (int i = 1; i < vals.ValueList.length; i += 2)
                        RM_by.getItems().add(vals.ValueList[i].toString());
                    vals = new CropAreaValues();
                    for (int i = 1; i < vals.ValueList.length; i += 2)
                        SI_cropAreaID.getItems().add(vals.ValueList[i].toString());
                }
            }
            if (!prevcropareas.equals(CapDefineCropAreaRow.getValue())) {
                DCA_cropAreaID.getItems().clear();
                DCA_cx.getItems().clear();
                DCA_cy.getItems().clear();
                if (CapDefineCropAreaRow.getValue().equals("true")) {
                    Values vals = new DefineCropAreaValues();
                    for (int i = 1; i < vals.ValueList.length; i += 2)
                        DCA_cropAreaID.getItems().add(vals.ValueList[i].toString());
                    vals = new CropAreaRightValues();
                    for (int i = 1; i < vals.ValueList.length; i += 2)
                        DCA_cx.getItems().add(vals.ValueList[i].toString());
                    vals = new CropAreaBottomValues();
                    for (int i = 1; i < vals.ValueList.length; i += 2)
                        DCA_cy.getItems().add(vals.ValueList[i].toString());
                }
            }
            InUpdateGui = false;
        }
    }

    private void setupBox(PropertyTableRow optionRow, PropertyTableRow valueRow, int[][] pairs, ComboBox<String> box) {
        Integer options = optionRow.getValueConverter().getInteger(optionRow.getValue());
        box.getItems().clear();
        String oldval = box.getValue();
        if (options != null) {
            for (int[] pair : pairs) {
                if ((options & pair[0]) != 0) {
                    box.getItems().add(valueRow.getValueConverter().getSymbol(pair[1]));
                }
            }
        }
    }

    public void setConcurrentMICR(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setConcurrentMICR(ConcurrentMICR.isSelected());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setColor(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setColor(new ColorValues().getInteger(Color.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    public void setContrast(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setContrast(new ContrastValues().getInteger(Contrast.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    public void setDocumentHeight(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setDocumentHeight(new IntValues().getInteger(DocumentHeight.getText()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    public void setDocumentWidth(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setDocumentWidth(new IntValues().getInteger(DocumentWidth.getText()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    public void setFileID(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setFileID(FileIDRow.getValue());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    public void setFileIndex(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setFileIndex(new IntValues().getInteger(FileIndex.getText()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    public void setImageFormat(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setImageFormat(new ImageFormatValues().getInteger(ImageFormat.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    public void setMapMode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setMapMode(new MapModeValues().getInteger(MapMode.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    public void setQuality(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setQuality(new IntValues().getInteger(Quality.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    public void setImageTagData(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCheckScanner.setImageTagData(ImageTagData.getText());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            } catch (NullPointerException e) {
            }
            updateGuiLater();
        }
    }

    private FileChooser TheFileChooser = new FileChooser();

    public void imageToFile(ActionEvent actionEvent) {
        try {
            byte[] imagedata = TheCheckScanner.getImageData();
            if (imagedata == null || imagedata.length == 0)
                throw new JposException(JposConst.JPOS_E_ILLEGAL, "ImageData empty");
            TheFileChooser.setTitle("Save File With ImageData Contents");
            File file = TheFileChooser.showSaveDialog(null);
            if (file != null) {
                TheFileChooser.setInitialDirectory(file.getParentFile());
                try {
                    RandomAccessFile target = new RandomAccessFile(file, "rw");
                    try {
                        target.write(imagedata);
                    } finally {
                        target.close();
                    }
                } catch (Exception e) {
                    throw new JposException(JposConst.JPOS_E_FAILURE, e.getMessage(), e);
                }
            }
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    private class BeginInsertion extends MethodProcessor {
        private int Timeout;

        BeginInsertion(int timeout) {
            super("BeginInsertion");
            Timeout = timeout;
        }
        @Override
        void runIt() throws JposException {
            TheCheckScanner.beginInsertion(Timeout);
        }
    }

    public void beginInsertion(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer timeout = new TimeoutValues().getInteger(BI_timeout.getValue());
            if (!invalid(timeout, "timeout"))
                new BeginInsertion(timeout).start();
        }
    }

    private class EndInsertion extends MethodProcessor {
        EndInsertion() {
            super("EndInsertion");
        }
        @Override
        void runIt() throws JposException {
            TheCheckScanner.endInsertion();
        }
    }

    public void endInsertion(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            new EndInsertion().start();
        }
    }

    private class BeginRemoval extends MethodProcessor {
        private int Timeout;

        BeginRemoval(int timeout) {
            super("BeginRemoval");
            Timeout = timeout;
        }
        @Override
        void runIt() throws JposException {
            TheCheckScanner.beginRemoval(Timeout);
        }
    }

    public void beginRemoval(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer timeout = new TimeoutValues().getInteger(BR_timeout.getValue());
            if (!invalid(timeout, "timeout"))
                new BeginRemoval(timeout).start();
        }
    }

    private class EndRemoval extends MethodProcessor {
        EndRemoval() {
            super("EndRemoval");
        }
        @Override
        void runIt() throws JposException {
            TheCheckScanner.endRemoval();
        }
    }

    public void endRemoval(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            new EndRemoval().start();
        }
    }

    private class ClearImage extends MethodProcessor {
        private int By;

        ClearImage(int by) {
            super("ClearImage");
            By = by;
        }
        @Override
        void runIt() throws JposException {
            TheCheckScanner.clearImage(By);
        }
    }

    public void clearImage(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer by = new ClearImageWithTagValues().getInteger(CI_by.getValue());
            if (!invalid(by, "by"))
                new ClearImage(by).start();
        }
    }

    private class RetrieveImage extends MethodProcessor {
        private int CropAreaID;

        RetrieveImage(int cropAreaID) {
            super("RetrieveImage");
            CropAreaID = cropAreaID;
        }
        @Override
        void runIt() throws JposException {
            TheCheckScanner.retrieveImage(CropAreaID);
        }
    }

    public void retrieveImage(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer cropAreaID = new CropAreaValues().getInteger(RI_cropAreaID.getValue());
            if (!invalid(cropAreaID, "cropAreaID"))
                new RetrieveImage(cropAreaID).start();
        }
    }

    private class RetrieveMemory extends MethodProcessor {
        private int By;

        RetrieveMemory(int by) {
            super("RetrieveMemory");
            By = by;
        }
        @Override
        void runIt() throws JposException {
            TheCheckScanner.retrieveMemory(By);
        }
    }

    public void retrieveMemory(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer by = new RetrieveMemoryWithTagValues().getInteger(RM_by.getValue());
            if (!invalid(by, "by"))
                new RetrieveMemory(by).start();
        }
    }

    private class StoreImage extends MethodProcessor {
        private int CropAreaID;

        StoreImage(int cropAreaID) {
            super("StoreImage");
            CropAreaID = cropAreaID;
        }
        @Override
        void runIt() throws JposException {
            TheCheckScanner.storeImage(CropAreaID);
        }
    }

    public void storeImage(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer cropAreaID = new CropAreaValues().getInteger(SI_cropAreaID.getValue());
            if (!invalid(cropAreaID, "cropAreaID"))
                new StoreImage(cropAreaID).start();
        }
    }

    private class DefineCropArea extends MethodProcessor {
        private int CropAreaID;
        private int X;
        private int Y;
        private int Cx;
        private int Cy;

        DefineCropArea(int cropAreaID, int x, int y, int cx, int cy) {
            super("DefineCropArea");
            CropAreaID = cropAreaID;
            X = x;
            Y = y;
            Cx = cx;
            Cy = cy;
        }
        @Override
        void runIt() throws JposException {
            TheCheckScanner.defineCropArea(CropAreaID, X, Y, Cx, Cy);
        }
    }

    public void defineCropArea(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            Integer cropAreaID = new DefineCropAreaValues().getInteger(DCA_cropAreaID.getValue());
            Integer x = new IntValues().getInteger(DCA_x.getText());
            Integer y = new IntValues().getInteger(DCA_y.getText());
            Integer cx = new CropAreaRightValues().getInteger(DCA_cx.getValue());
            Integer cy = new CropAreaBottomValues().getInteger(DCA_cy.getValue());
            if (!invalid(cropAreaID, "cropAreaID") && !invalid(x, "x") && !invalid(y, "y") && !invalid(cx, "cx") && !invalid(cy, "cy"))
                new DefineCropArea(cropAreaID, x, y, cx, cy).start();
        }
    }

    private class CheckScannerStatusUpdateValues extends StatusUpdateValues {
        CheckScannerStatusUpdateValues() {
            super();
            Object[] checkscanvalues = new Object[]{
                    CheckScannerConst.CHK_SUE_SCANCOMPLETE, "SUE_SCANCOMPLETE"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + checkscanvalues.length);
            System.arraycopy(checkscanvalues, 0, ValueList, ValueList.length - checkscanvalues.length, checkscanvalues.length);
        }
    }

    private class ImageMemoryStatusValues extends Values {
        ImageMemoryStatusValues() {
            super();
            ValueList = new Object[]{
                    CheckScannerConst.CHK_IMS_EMPTY, "IMS_EMPTY",
                    CheckScannerConst.CHK_IMS_OK, "IMS_OK",
                    CheckScannerConst.CHK_IMS_FULL, "IMS_FULL"
            };
        }
    }

    private class ColorValues extends Values {
        ColorValues() {
            super();
            ValueList = new Object[]{
                    CheckScannerConst.CHK_CL_MONO, "CL_MONO",
                    CheckScannerConst.CHK_CL_GRAYSCALE, "CL_GRAYSCALE",
                    CheckScannerConst.CHK_CL_16, "CL_16",
                    CheckScannerConst.CHK_CL_256, "CL_256",
                    CheckScannerConst.CHK_CL_FULL, "CL_FULL"
            };
        }
    }

    private class ContrastValues extends Values {
        ContrastValues() {
            super();
            ValueList = new Object[]{
                    CheckScannerConst.CHK_AUTOMATIC_CONTRAST, "AUTOMATIC_CONTRAST"
            };
        }
    }

    private class ImageFormatValues extends Values {
        ImageFormatValues() {
            super();
            ValueList = new Object[]{
                    CheckScannerConst.CHK_IF_NATIVE, "IF_NATIVE",
                    CheckScannerConst.CHK_IF_TIFF, "IF_TIFF",
                    CheckScannerConst.CHK_IF_BMP, "IF_BMP",
                    CheckScannerConst.CHK_IF_JPEG, "IF_JPEG",
                    CheckScannerConst.CHK_IF_GIF, "IF_GIF"
            };
        }
    }

    private class MapModeValues extends Values {
        MapModeValues() {
            super();
            ValueList = new Object[]{
                    CheckScannerConst.CHK_MM_DOTS, "MM_DOTS",
                    CheckScannerConst.CHK_MM_TWIPS, "MM_TWIPS",
                    CheckScannerConst.CHK_MM_ENGLISH, "MM_ENGLISH",
                    CheckScannerConst.CHK_MM_METRIC, "MM_METRIC"
            };
        }
    }

    private class CropAreaValues extends Values {
        CropAreaValues() {
            super();
            ValueList = new Object[]{
                    CheckScannerConst.CHK_CROP_AREA_ENTIRE_IMAGE, "ENTIRE_IMAGE"
            };
        }
    }

    private class DefineCropAreaValues extends CropAreaValues {
        DefineCropAreaValues() {
            super();
            ValueList = Arrays.copyOf(ValueList, ValueList.length + 2);
            ValueList[ValueList.length - 2] = CheckScannerConst.CHK_CROP_AREA_RESET_ALL;
            ValueList[ValueList.length - 1] = "RESET_ALL";
        }
    }

    private class CropAreaRightValues extends Values {
        CropAreaRightValues() {
            super();
            ValueList = new Object[]{
                    CheckScannerConst.CHK_CROP_AREA_RIGHT, "AREA_RIGHT"
            };
        }
    }

    private class CropAreaBottomValues extends Values {
        CropAreaBottomValues() {
            super();
            ValueList = new Object[]{
                    CheckScannerConst.CHK_CROP_AREA_BOTTOM, "AREA_BOTTOM"
            };
        }
    }

    private class ClearImageValues extends Values {
        ClearImageValues() {
            super();
            ValueList = new Object[]{
                    CheckScannerConst.CHK_CLR_ALL, "ALL",
                    CheckScannerConst.CHK_CLR_BY_FILEID, "BY_FILEID",
                    CheckScannerConst.CHK_CLR_BY_FILEINDEX, "BY_FILEINDEX"
            };
        }
    }

    private class ClearImageWithTagValues extends ClearImageValues {
        ClearImageWithTagValues() {
            super();
            ValueList = Arrays.copyOf(ValueList, ValueList.length + 2);
            ValueList[ValueList.length - 2] = CheckScannerConst.CHK_CLR_BY_IMAGETAGDATA;
            ValueList[ValueList.length - 1] = "BY_IMAGETAGDATA";
        }
    }

    private class RetrieveMemoryValues extends Values {
        RetrieveMemoryValues() {
            super();
            ValueList = new Object[]{
                    CheckScannerConst.CHK_LOCATE_BY_FILEID, "BY_FILEID",
                    CheckScannerConst.CHK_LOCATE_BY_FILEINDEX, "BY_FILEINDEX"
            };
        }
    }

    private class RetrieveMemoryWithTagValues extends RetrieveMemoryValues {
        RetrieveMemoryWithTagValues() {
            super();
            ValueList = Arrays.copyOf(ValueList, ValueList.length + 2);
            ValueList[ValueList.length - 2] = CheckScannerConst.CHK_LOCATE_BY_IMAGETAGDATA;
            ValueList[ValueList.length - 1] = "BY_IMAGETAGDATA";
        }
    }
}
