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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jpos.GraphicDisplay;
import jpos.GraphicDisplayConst;
import jpos.JposException;

import java.net.URL;
import java.util.ResourceBundle;

public class GraphicDisplayController extends CommonController {
    public ComboBox<String> Brightness;
    public ComboBox<String> DisplayMode;
    public ComboBox<String> ImageType;
    public ComboBox<String> Storage;
    public ComboBox<String> VideoType;
    public ComboBox<String> Volume;
    public Label URL;
    public Label LoadStatus;
    public TextField LI_fileName;
    public TextField PV_fileName;
    public TextField LURL_URL;
    public CheckBox PV_loop;
    private GraphicDisplay TheGraphicDisplay;
    private PropertyTableRow BrightnessRow;
    private PropertyTableRow DisplayModeRow;
    private PropertyTableRow ImageTypeRow;
    private PropertyTableRow StorageRow;
    private PropertyTableRow VideoTypeRow;
    private PropertyTableRow VolumeRow;
    private PropertyTableRow ImageTypeListRow;
    private PropertyTableRow VideoTypeListRow;
    private PropertyTableRow CapBrightnessRow;
    private PropertyTableRow CapImageTypeRow;
    private PropertyTableRow CapStorageRow;
    private PropertyTableRow CapVideoTypeRow;
    private PropertyTableRow CapVolumeRow;
    private PropertyTableRow LoadStatusRow;
    private PropertyTableRow UrlRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheGraphicDisplay = (GraphicDisplay) Control;
        TheGraphicDisplay.addDirectIOListener(this);
        TheGraphicDisplay.addErrorListener(this);
        TheGraphicDisplay.addStatusUpdateListener(this);
        TheGraphicDisplay.addOutputCompleteListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("CapAssociatedHardTotalsDevice", ""));
        Properties.getItems().add(CapBrightnessRow = new PropertyTableRow("CapBrightness", ""));
        Properties.getItems().add(CapImageTypeRow = new PropertyTableRow("CapImageType", ""));
        Properties.getItems().add(CapStorageRow = new PropertyTableRow("CapStorage", "", new CapStorageValues()));
        Properties.getItems().add(new PropertyTableRow("CapURLBack", ""));
        Properties.getItems().add(new PropertyTableRow("CapURLForward", ""));
        Properties.getItems().add(CapVideoTypeRow = new PropertyTableRow("CapVideoType", ""));
        Properties.getItems().add(CapVolumeRow = new PropertyTableRow("CapVolume", ""));
        Properties.getItems().add(BrightnessRow = new PropertyTableRow("Brightness", ""));
        Properties.getItems().add(DisplayModeRow = new PropertyTableRow("DisplayMode", "", new DisplayModeValues()));
        Properties.getItems().add(ImageTypeRow = new PropertyTableRow("ImageType", ""));
        Properties.getItems().add(StorageRow = new PropertyTableRow("Storage", "", new StorageValues()));
        Properties.getItems().add(VideoTypeRow = new PropertyTableRow("VideoType", ""));
        Properties.getItems().add(VolumeRow = new PropertyTableRow("Volume", ""));
        Properties.getItems().add(ImageTypeListRow = new PropertyTableRow("ImageTypeList", ""));
        Properties.getItems().add(LoadStatusRow = new PropertyTableRow("LoadStatus", "", new LoadStatusValues()));
        Properties.getItems().add(UrlRow = new PropertyTableRow("URL", ""));
        Properties.getItems().add(VideoTypeListRow = new PropertyTableRow("VideoTypeList", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        updateGui();
    }

    @Override
    public void updateGui() {
        String capbrightness = CapBrightnessRow.getValue();
        String capimagetype = CapImageTypeRow.getValue();
        String capstorage = CapStorageRow.getValue();
        String capvideotype = CapVideoTypeRow.getValue();
        String capvolume = CapVolumeRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            if(!CapBrightnessRow.getValue().equals(capbrightness)) {
                Brightness.getItems().clear();
                if (CapBrightnessRow.getValue().equals("true")) {
                    for (int i = 0; i <= 100; i++) {
                        String val = Integer.toString(i);
                        Brightness.getItems().add(val);
                        if (val.equals(BrightnessRow.getValue()))
                            Brightness.setValue(val);
                    }
                }
            }
            else if (!BrightnessRow.getValue().equals(Brightness.getValue()))
                Brightness.setValue(BrightnessRow.getValue());
            if(!CapVolumeRow.getValue().equals(capvolume)) {
                Volume.getItems().clear();
                if (CapVolumeRow.getValue().equals("true")) {
                    for (int i = 0; i <= 100; i++) {
                        String val = Integer.toString(i);
                        Volume.getItems().add(val);
                        if (val.equals(CapVolumeRow.getValue()))
                            Volume.setValue(val);
                    }
                }
            }
            else if (!VolumeRow.getValue().equals(Volume.getValue()))
                Volume.setValue(VolumeRow.getValue());
            if (!CapStorageRow.getValue().equals(capstorage)) {
                Storage.getItems().clear();
                Integer val = CapStorageRow.getValueConverter().getInteger(CapStorageRow.getValue());
                if (val != null) {
                    switch (val) {
                        case GraphicDisplayConst.GDSP_CST_HOST_ONLY: {
                            String symbol = StorageRow.getValueConverter().getSymbol(GraphicDisplayConst.GDSP_ST_HOST);
                            Storage.getItems().add(symbol);
                            if (symbol.equals(StorageRow.getValue()))
                                Storage.setValue(symbol);
                            break;
                        }
                        case GraphicDisplayConst.GDSP_CST_HARDTOTALS_ONLY:{
                            String symbol = StorageRow.getValueConverter().getSymbol(GraphicDisplayConst.GDSP_ST_HARDTOTALS);
                            Storage.getItems().add(symbol);
                            if (symbol.equals(StorageRow.getValue()))
                                Storage.setValue(symbol);
                            break;
                        }
                        case GraphicDisplayConst.GDSP_CST_ALL: {
                            Object[] vals = StorageRow.getValueConverter().ValueList;
                            for (int i = 1; i < vals.length; i += 2) {
                                Storage.getItems().add(vals[i].toString());
                                if (vals[i].equals(StorageRow.getValue()))
                                    Storage.setValue(StorageRow.getValue());
                            }
                            break;
                        }
                    }
                }
            }
            else if (!StorageRow.getValue().equals(Storage.getValue()))
                Storage.setValue(StorageRow.getValue());

            boolean displaymodeready = false;

            if (!CapImageTypeRow.getValue().equals(capimagetype)) {
                ImageType.getItems().clear();
                if (CapImageTypeRow.getValue().equals("true")) {
                    String[] vals = ImageTypeListRow.getValue().split(",");
                    for (String val : vals) {
                        if (val.length() > 0) {
                            ImageType.getItems().add(val);
                            if (val.equals(ImageTypeRow.getValue()))
                                ImageType.setValue(val);
                        }
                    }
                }
                setDisplayMode();
                displaymodeready = true;
            }
            else if (!ImageTypeRow.getValue().equals(ImageType.getValue()))
                ImageType.setValue(ImageTypeRow.getValue());
            if (!CapVideoTypeRow.getValue().equals(capvideotype)) {
                VideoType.getItems().clear();
                if (CapVideoTypeRow.getValue().equals("true")) {
                    String[] vals = VideoTypeListRow.getValue().split(",");
                    for (String val : vals) {
                        if (val.length() > 0) {
                            VideoType.getItems().add(val);
                            if (val.equals(VideoTypeRow.getValue()))
                                VideoType.setValue(val);
                        }
                    }
                }
                if (!displaymodeready) {
                    setDisplayMode();
                    displaymodeready = true;
                }
            }
            else if (!VideoTypeRow.getValue().equals(VideoType.getValue()))
                VideoType.setValue(VideoTypeRow.getValue());
            if (!displaymodeready && !DisplayModeRow.getValue().equals(DisplayMode.getValue()))
                DisplayMode.setValue(DisplayModeRow.getValue());
            String val = UrlRow.getValue();
            URL.setText(val.length() > 0 ? "URL: " + val : val);
            val = LoadStatusRow.getValue();
            LoadStatus.setText(val.length() > 0 ? "LoadStatus: " + val : val);
            InUpdateGui = false;
        }
    }

    private void setDisplayMode() {
        DisplayMode.getItems().clear();
        if (!"".equals(CapImageTypeRow.getValue()) && !"".equals(CapVideoTypeRow.getValue())) {
            int[] modi = { GraphicDisplayConst.GDSP_DMODE_HIDDEN, GraphicDisplayConst.GDSP_DMODE_WEB };
            setDisplayModeComponents(modi);
            if (CapImageTypeRow.getValue().equals("true")) {
                int[] imagemodi = { GraphicDisplayConst.GDSP_DMODE_IMAGE_CENTER,
                        GraphicDisplayConst.GDSP_DMODE_IMAGE_FILL,
                        GraphicDisplayConst.GDSP_DMODE_IMAGE_FIT
                };
                setDisplayModeComponents(imagemodi);
            }
            if (CapVideoTypeRow.getValue().equals("true")) {
                int[] videomodi = { GraphicDisplayConst.GDSP_DMODE_VIDEO_NORMAL, GraphicDisplayConst.GDSP_DMODE_VIDEO_FULL };
                setDisplayModeComponents(videomodi);
            }
        }
        else if (!DisplayModeRow.getValue().equals(DisplayMode.getValue())) {
            DisplayMode.setValue(DisplayModeRow.getValue());
        }
    }

    private void setDisplayModeComponents(int[] modi) {
        String val;
        for (int mode : modi) {
            DisplayMode.getItems().add(val = DisplayModeRow.getValueConverter().getSymbol(mode));
            if (DisplayModeRow.getValue().equals(val))
                DisplayMode.setValue(val);
        }
    }

    public void setBrightness(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            if (!BrightnessRow.getValue().equals(Brightness.getValue())) {
                try {
                    TheGraphicDisplay.setBrightness(new IntValues().getInteger(Brightness.getValue()));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setDisplayMode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            if (!DisplayModeRow.getValue().equals(DisplayMode.getValue())) {
                try {
                    TheGraphicDisplay.setDisplayMode(DisplayModeRow.getValueConverter().getInteger(DisplayMode.getValue()));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setImageType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            if (!ImageTypeRow.getValue().equals(ImageType.getValue())) {
                try {
                    TheGraphicDisplay.setImageType(ImageType.getValue());
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setStorage(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            if (!StorageRow.getValue().equals(Storage.getValue())) {
                try {
                    TheGraphicDisplay.setStorage(StorageRow.getValueConverter().getInteger(Storage.getValue()));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setVideoType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            if (!VideoTypeRow.getValue().equals(VideoType.getValue())) {
                try {
                    TheGraphicDisplay.setVideoType(VideoType.getValue());
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    public void setVolume(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            if (!VolumeRow.getValue().equals(Volume.getValue())) {
                try {
                    TheGraphicDisplay.setVolume(new IntValues().getInteger(Volume.getValue()));
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
                updateGuiLater();
            }
        }
    }

    class LoadImage extends MethodProcessor {
        final String FileName;
        LoadImage(String fileName) {
            super("StopSound");
            FileName = fileName;
        }

        @Override
        void runIt() throws JposException {
            TheGraphicDisplay.loadImage(FileName);
        }
    }

    public void loadImage(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String fileName = LI_fileName.getText();
        if (!invalid(fileName, "FileName"))
            new LoadImage(fileName).start();
    }

    class PlayVideo extends MethodProcessor {
        final String FileName;
        final boolean Loop;
        PlayVideo(String fileName, boolean loop) {
            super("PlayVideo");
            FileName = fileName;
            Loop = loop;
        }

        @Override
        void runIt() throws JposException {
            TheGraphicDisplay.playVideo(FileName, Loop);
        }
    }

    public void playVideo(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String fileName = PV_fileName.getText();
        boolean loop = PV_loop.isSelected();
        if (!invalid(fileName, "FileName"))
            new PlayVideo(fileName, loop).start();
    }

    class StopVideo extends MethodProcessor {
        StopVideo() {
            super("StopVideo");
        }

        @Override
        void runIt() throws JposException {
            TheGraphicDisplay.stopVideo();
        }
    }

        public void stopVideo(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new StopVideo().start();
    }

    class LoadURL extends MethodProcessor {
        final String URL;
        LoadURL(String url) {
            super("LoadURL");
            URL = url;
        }

        @Override
        void runIt() throws JposException {
            TheGraphicDisplay.loadURL(URL);
        }
    }

    public void loadURL(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        String url = LURL_URL.getText();
        if (!invalid(url, "url"))
            new LoadURL(url).start();
    }

    class GoURLBack extends MethodProcessor {
        GoURLBack() {
            super("GoURLBack");
        }

        @Override
        void runIt() throws JposException {
            TheGraphicDisplay.goURLBack();
        }
    }

    public void goURLBack(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new GoURLBack().start();
    }

    class GoURLForward extends MethodProcessor {
        GoURLForward() {
            super("GoURLForward");
        }

        @Override
        void runIt() throws JposException {
            TheGraphicDisplay.goURLForward();
        }
    }

    public void goURLForward(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new GoURLForward().start();
    }

    class UpdateURLPage extends MethodProcessor {
        UpdateURLPage() {
            super("UpdateURLPage");
        }

        @Override
        void runIt() throws JposException {
            TheGraphicDisplay.updateURLPage();
        }
    }

    public void updateURLPage(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new UpdateURLPage().start();
    }


    class CancelURLLoading extends MethodProcessor {
        CancelURLLoading() {
            super("CancelURLLoading");
        }

        @Override
        void runIt() throws JposException {
            TheGraphicDisplay.cancelURLLoading();
        }
    }

    public void cancelURLLoading(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new CancelURLLoading().start();
    }

    private class CapStorageValues extends IntValues {
        CapStorageValues() {
            ValueList = new Object[]{
                    GraphicDisplayConst.GDSP_CST_HOST_ONLY, "HOST_ONLY",
                    GraphicDisplayConst.GDSP_CST_HARDTOTALS_ONLY, "HARDTOTALS_ONLY",
                    GraphicDisplayConst.GDSP_CST_ALL, "ALL"
            };
        }
    }

    private class DisplayModeValues extends IntValues {
        DisplayModeValues() {
            ValueList = new Object[]{
                    GraphicDisplayConst.GDSP_DMODE_HIDDEN, "HIDDEN",
                    GraphicDisplayConst.GDSP_DMODE_IMAGE_FIT, "IMAGE_FIT",
                    GraphicDisplayConst.GDSP_DMODE_IMAGE_FILL, "IMAGE_FILL",
                    GraphicDisplayConst.GDSP_DMODE_IMAGE_CENTER, "IMAGE_CENTER",
                    GraphicDisplayConst.GDSP_DMODE_VIDEO_NORMAL, "VIDEO_NORMAL",
                    GraphicDisplayConst.GDSP_DMODE_VIDEO_FULL, "VIDEO_FULL",
                    GraphicDisplayConst.GDSP_DMODE_WEB, "WEB"
            };
        }
    }

    @SuppressWarnings("deprecation")
    private class StorageValues extends IntValues {
        StorageValues() {
            ValueList = new Object[]{
                    GraphicDisplayConst.GDSP_ST_HOST, "HOST",
                    GraphicDisplayConst.GDSP_ST_HARDTOTALS, "HARDTOTALS",
                    GraphicDisplayConst.GDSP_ST_HOST_HARDTOTALS, "HOST_HARDTOTALS"
            };
        }
    }

    private class LoadStatusValues extends IntValues {
        LoadStatusValues() {
            ValueList = new Object[]{
                    GraphicDisplayConst.GDSP_LSTATUS_START, "START",
                    GraphicDisplayConst.GDSP_LSTATUS_FINISH, "FINISH",
                    GraphicDisplayConst.GDSP_LSTATUS_CANCEL, "CANCEL"
            };
        }
    }
}
