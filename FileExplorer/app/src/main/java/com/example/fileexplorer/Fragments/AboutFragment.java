package com.example.fileexplorer.Fragments;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fileexplorer.R;

import java.io.File;

public class AboutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        TextView txtInfo = view.findViewById(R.id.txtInfo);

        StringBuilder sb = new StringBuilder();

        // ======= Device Info =======
        sb.append("Manufacturer: ").append(Build.MANUFACTURER).append("\n");
        sb.append("Brand: ").append(Build.BRAND).append("\n");
        sb.append("Model: ").append(Build.MODEL).append("\n");
        sb.append("Device: ").append(Build.DEVICE).append("\n");
        sb.append("Board: ").append(Build.BOARD).append("\n");
        sb.append("Hardware: ").append(Build.HARDWARE).append("\n");
        sb.append("Android ID: ").append(Settings.Secure.getString(
                getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID
        )).append("\n\n");

        // ======= Android Version =======
        sb.append("Android Version: ").append(Build.VERSION.RELEASE).append("\n");
        sb.append("SDK Level: ").append(Build.VERSION.SDK_INT).append("\n");
        sb.append("Build Number: ").append(Build.DISPLAY).append("\n\n");

        // ======= CPU Info =======
        sb.append("CPU ABI: ").append(Build.SUPPORTED_ABIS[0]).append("\n");
        sb.append("CPU Cores: ").append(Runtime.getRuntime().availableProcessors()).append("\n");
        sb.append("CPU Max Frequency: ").append(getCpuMaxFreqMHz()).append(" MHz\n\n");

        // ======= RAM Info =======
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memInfo);

        long totalRam = memInfo.totalMem / (1024 * 1024);
        long freeRam = memInfo.availMem / (1024 * 1024);

        sb.append("Total RAM: ").append(totalRam).append(" MB\n");
        sb.append("Available RAM: ").append(freeRam).append(" MB\n\n");

        // ======= Internal Storage =======
        File path = Environment.getDataDirectory();
        long total = path.getTotalSpace() / (1024 * 1024 * 1024);
        long free = path.getFreeSpace() / (1024 * 1024 * 1024);
        sb.append("Internal Storage: ").append(total).append(" GB total\n");
        sb.append("Internal Free: ").append(free).append(" GB\n\n");

        // ======= External Storage (SD Card) =======
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File ext = Environment.getExternalStorageDirectory();
            long t = ext.getTotalSpace() / (1024 * 1024 * 1024);
            long f = ext.getFreeSpace() / (1024 * 1024 * 1024);
            sb.append("SD Card: ").append(t).append(" GB total\n");
            sb.append("SD Free: ").append(f).append(" GB\n\n");
        } else {
            sb.append("SD Card: Not mounted\n\n");
        }

        // ======= Screen Info =======
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);

        sb.append("Resolution: ").append(dm.widthPixels).append(" x ").append(dm.heightPixels).append("\n");
        sb.append("Density DPI: ").append(dm.densityDpi).append("\n");
        sb.append("Screen Size: ").append(String.format("%.1f", screenInches)).append(" inches\n\n");

        txtInfo.setText(sb.toString());

        return view;
    }

    private int getCpuMaxFreqMHz() {
        try {
            File file = new File("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            if (file.exists()) {
                java.util.Scanner sc = new java.util.Scanner(file);
                return sc.nextInt() / 1000;
            }
        } catch (Exception ignored) {}

        return -1;
    }
}
