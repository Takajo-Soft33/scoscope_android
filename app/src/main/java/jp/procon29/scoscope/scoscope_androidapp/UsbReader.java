package jp.procon29.scoscope.scoscope_androidapp;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import com.felhr.usbserial.CDCSerialDevice;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;

import java.util.*;

import static com.felhr.usbserial.UsbSerialInterface.*;

public class UsbReader {
    // change BAUD_RATE to appropriate value
    public static final int BAUD_RATE = 8600;
    public static final String CHARSET = "UTF-8";
    public static final long INTERVAL = 100;
    
    /**
     * new UsbReader()
     */
    public UsbReader(Context context) {
        this.context = context;
        serialPortConnected = false;
        setFilter();
        usbManager = (UsbManager) context.getSystemDevice(Context.USB_SERVICE);
        
        Map<String, UsbDevice> devices = usbManager.getDeviceList();
        if(devices.isEmpty())
            throw new RuntimeException("No USB device found");
        
        device = devices.entrySet().iterator().next().getValue();
        
    }
    
    public float getHAngle() {
        return hAngle;
    }
    
    public float getVAngle() {
        return vAngle;
    }
    
    final BroadcastReceiver usbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch(intent.getAction()) {
            case "com.android.example.USB_PERMISSION":
                boolean granted = intent.getExtras().getBoolean(EXTRA_PERMISSION_GRANTED);
                if(granted) {
                    connection = usbManager.openDevice(device);
                    Runnable thread = () -> {
                        port = UsbSerialDevice.createUsbSerial(device, connection);
                        if(port == null) throw new RuntimeException("failed to create serial port");
                        
                        if(port.open()) {
                            serialPortConnected = true;
                            port.setBaudRate(BAUD_RATE);
                            port.setDataBits(DATA_BITS_8);
                            port.setStopBits(STOP_BITS_1);
                            port.setParity(PARITY_NONE);
                            port.setFlowControl(FLOW_CONTROL_OFF);
                            try {
                                for(;;) {
                                    port.read(callback);
                                    Thread.sleep(INTERVAL);
                                }
                            } catch(Exception e) {}
                        }
                    };
                }
                break;
            case "android.hardware.usb.action.USB_ATTACHED":
                if(!serialPortConnected)
                    findSerialPortDevice();
                break;
            case "android.hardware.usb.action.USB_DETACHED":
                if(serialPortConnected)
                    serialPort.close();
                serialPortConnected = false;
                break;
            default:
            }
        }
    };
    
    final Context context;
    final UsbManager usbManager;
    final UsbDevice device;
    UsbDeviceConnection connection;
    UsbSerialDevice serialPort;
    
    private boolean serialPortConnected;
    
    final UsbReadCallback callback = new UsbReadCallback() {
        @Override
        public void onReceivedData(byte[] rawData) {
            try {
                String data = new String(rawData, CHARSET);
                Scanner scanner = new Scanner(data);
                hAngle = scanner.nextFloat();
                vAngle = scanner.nextFloat();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    };
    
    private float hAngle = 0, vAngle = 0;
}
