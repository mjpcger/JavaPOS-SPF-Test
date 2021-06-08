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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jpos.ImageScanner;
import jpos.ImageScannerConst;
import jpos.JposConst;
import jpos.JposException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for ImageScanner properties, methods and events.
 */
public class ImageScannerController extends CommonController {
    public CheckBox AimMode;
    public CheckBox IlluminateMode;
    public ComboBox<String> ImageMode;
    public ComboBox<String> ImageQuality;
    public TextField VideoCount;
    public TextField VideoRate;
    public Label BitsPerPixel;
    public Label FrameType;
    public Label ImageType;
    public Label ImageHeight;
    public Label ImageWidth;
    public Label ImageLength;
    private ImageScanner TheImageScanner;
    private PropertyTableRow CapHostTriggeredRow;
    private PropertyTableRow CapDecodeDataRow;
    private PropertyTableRow CapVideoDataRow;
    private PropertyTableRow CapImageDataRow;
    private PropertyTableRow CapImageQualityRow;
    private PropertyTableRow CapAimRow;
    private PropertyTableRow CapIlluminateRow;
    private PropertyTableRow ImageModeRow;
    private PropertyTableRow ImageQualityRow;
    private PropertyTableRow VideoCountRow;
    private PropertyTableRow VideoRateRow;
    private PropertyTableRow AimModeRow;
    private PropertyTableRow IlluminateModeRow;
    private PropertyTableRow FrameTypeRow;
    private PropertyTableRow ImageTypeRow;
    private PropertyTableRow ImageHeightRow;
    private PropertyTableRow ImageWidthRow;
    private PropertyTableRow BitsPerPixelRow;
    private PropertyTableRow ImageLengthRow;
    private PropertyTableRow FrameDataRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheImageScanner = (ImageScanner) Control;
        TheImageScanner.addDataListener(this);
        TheImageScanner.addDirectIOListener(this);
        TheImageScanner.addErrorListener(this);
        TheImageScanner.addStatusUpdateListener(this);
        Conversion = ByteConversion.Length;
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(CapHostTriggeredRow = new PropertyTableRow("CapHostTriggered", ""));
        Properties.getItems().add(CapDecodeDataRow = new PropertyTableRow("CapDecodeData", ""));
        Properties.getItems().add(CapVideoDataRow = new PropertyTableRow("CapVideoData", ""));
        Properties.getItems().add(CapImageDataRow = new PropertyTableRow("CapImageData", ""));
        Properties.getItems().add(CapImageQualityRow = new PropertyTableRow("CapImageQuality", ""));
        Properties.getItems().add(CapAimRow = new PropertyTableRow("CapAim", ""));
        Properties.getItems().add(CapIlluminateRow = new PropertyTableRow("CapIlluminate", ""));
        Properties.getItems().add(ImageModeRow = new PropertyTableRow("ImageMode", "", new ImageModeValues()));
        Properties.getItems().add(ImageQualityRow = new PropertyTableRow("ImageQuality", "", new ImageQualityValues()));
        Properties.getItems().add(VideoCountRow = new PropertyTableRow("VideoCount", ""));
        Properties.getItems().add(VideoRateRow = new PropertyTableRow("VideoRate", ""));
        Properties.getItems().add(AimModeRow = new PropertyTableRow("AimMode", ""));
        Properties.getItems().add(IlluminateModeRow = new PropertyTableRow("IlluminateMode", ""));
        Properties.getItems().add(FrameTypeRow = new PropertyTableRow("FrameType", "", new FrameTypeValues()));
        Properties.getItems().add(ImageTypeRow = new PropertyTableRow("ImageType", "", new ImageTypeValues()));
        Properties.getItems().add(ImageHeightRow = new PropertyTableRow("ImageHeight", ""));
        Properties.getItems().add(ImageWidthRow = new PropertyTableRow("ImageWidth", ""));
        Properties.getItems().add(BitsPerPixelRow = new PropertyTableRow("BitsPerPixel", ""));
        Properties.getItems().add(ImageLengthRow = new PropertyTableRow("ImageLength", ""));
        Properties.getItems().add(FrameDataRow = new PropertyTableRow("FrameData", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        setPropertyOnFocusLost(VideoCount, "VideoCount");
        setPropertyOnFocusLost(VideoRate, "VideoRate");
    }

    @Override
    void updateGui() {
        boolean videocount = VideoCountRow.getValue().equals(VideoCount.getText());
        boolean videorate = VideoRateRow.getValue().equals(VideoRate.getText());
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            if (TheImageScanner.getState() == JposConst.JPOS_S_CLOSED) {
                ImageMode.getItems().clear();
                ImageQuality.getItems().clear();
                VideoCount.setText("");
                VideoRate.setText("");
            } else {
                Object[] vals = new ImageModeValues().ValueList;
                if (ImageMode.getItems().size() == 0) {
                    String current = ImageModeRow.getValue();
                    for (int i = 1; i < vals.length; i += 2) {
                        ImageMode.getItems().add(vals[i].toString());
                        if (current.equals(vals[i].toString()))
                            ImageMode.setValue(current);
                    }
                }
                if (ImageQuality.getItems().size() == 0) {
                    String current = ImageQualityRow.getValue();
                    vals = new ImageQualityValues().ValueList;
                    for (int i = 1; i < vals.length; i += 2) {
                        ImageQuality.getItems().add(vals[i].toString());
                        if (current.equals(vals[i].toString()))
                            ImageQuality.setValue(current);
                    }
                }
                if (videocount)
                    VideoCount.setText(VideoCountRow.getValue());
                if (videorate)
                    VideoRate.setText(VideoRateRow.getValue());
            }
            AimMode.setSelected(AimModeRow.getValue().equals("true"));
            IlluminateMode.setSelected((IlluminateModeRow.getValue().equals("true")));
            BitsPerPixel.setText(BitsPerPixelRow.getValue());
            FrameType.setText(FrameTypeRow.getValue());
            ImageType.setText(ImageTypeRow.getValue());
            ImageHeight.setText(ImageHeightRow.getValue());
            ImageWidth.setText(ImageWidthRow.getValue());
            ImageLength.setText(ImageLengthRow.getValue());
            InUpdateGui = false;
        }
    }

    public void setAimMode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheImageScanner.setAimMode(AimMode.isSelected());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setIlluminateMode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheImageScanner.setIlluminateMode(IlluminateMode.isSelected());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    private class StartSession extends MethodProcessor {
        StartSession() {
            super("StartSession");
        }
        @Override
        void runIt() throws JposException {
            TheImageScanner.startSession();
        }
    }

    public void startSession(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            new StartSession().start();
        }
    }

    private class StopSession extends MethodProcessor {
        StopSession() {
            super("StopSession");
        }
        @Override
        void runIt() throws JposException {
            TheImageScanner.stopSession();
        }
    }

    public void stopSession(ActionEvent actionEvent) {
        if (!isMethodRunning()) {
            new StopSession().start();
        }
    }

    public void setImageMode(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheImageScanner.setImageMode(ImageModeRow.getValueConverter().getInteger(ImageMode.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setImageQuality(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheImageScanner.setImageQuality(ImageQualityRow.getValueConverter().getInteger(ImageQuality.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setVideoCount(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheImageScanner.setVideoCount(new IntValues().getInteger(VideoCount.getText()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setVideoRate(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheImageScanner.setVideoRate(new IntValues().getInteger(VideoRate.getText()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    private class FrameTypeValues extends Values {
        FrameTypeValues() {
            super();
            ValueList = new Object[]{
                    ImageScannerConst.IMG_FRAME_STILL, "FRAME_STILL",
                    ImageScannerConst.IMG_FRAME_VIDEO, "FRAME_VIDEO"
            };
        }
    }

    private class ImageModeValues extends Values {
        ImageModeValues() {
            super();
            ValueList = new Object[]{
                    ImageScannerConst.IMG_DECODE_ONLY, "DECODE_ONLY",
                    ImageScannerConst.IMG_STILL_ONLY, "STILL_ONLY",
                    ImageScannerConst.IMG_STILL_DECODE, "STILL_DECODE",
                    ImageScannerConst.IMG_VIDEO_DECODE, "VIDEO_DECODE",
                    ImageScannerConst.IMG_VIDEO_STILL, "VIDEO_STILL",
                    ImageScannerConst.IMG_ALL, "ALL"
            };
        }
    }

    private class ImageQualityValues extends Values {
        ImageQualityValues() {
            super();
            ValueList = new Object[]{
                    ImageScannerConst.IMG_QUAL_LOW, "QUAL_LOW",
                    ImageScannerConst.IMG_QUAL_MED, "QUAL_MED",
                    ImageScannerConst.IMG_QUAL_HIGH, "QUAL_HIGH"
            };
        }
    }

    private class ImageTypeValues extends Values {
        ImageTypeValues () {
            super();
            ValueList = new Object[]{
                    ImageScannerConst.IMG_TYP_BMP, "TYP_BMP",
                    ImageScannerConst.IMG_TYP_JPEG, "TYP_JPEG",
                    ImageScannerConst.IMG_TYP_GIF, "TYP_GIF",
                    ImageScannerConst.IMG_TYP_PNG, "TYP_PNG",
                    ImageScannerConst.IMG_TYP_TIFF, "TYP_TIFF"
            };
        }
    }
}
