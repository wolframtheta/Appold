package com.example.abhinav.sahaya.Search;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhinav.sahaya.R;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {
    private List<ApplicationInfo> appsList = null;
    private Context context;
    private PackageManager packageManager;
    private String SearchText;

    public ApplicationAdapter(Context context, int textViewResourceId,
                              List<ApplicationInfo> appsList, String search) {
        super(context, textViewResourceId, appsList);
        this.context = context;
        this.appsList = appsList;
        packageManager = context.getPackageManager();
        SearchText = search;
        sortList();
        this.appsList = this.appsList.subList(0, 10);
    }

    @Override
    public int getCount() {
        return ((null != appsList) ? appsList.size() : 0);
    }

    @Override
    public ApplicationInfo getItem(int position) {
        return ((null != appsList) ? appsList.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public double compareStrings(String name, String aux) {
        return StringUtils.getLevenshteinDistance(name, aux);
    }

    public void quickSort(List<Double> arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public int partition(List<Double> arr, int low, int high) {
        ApplicationInfo aux = appsList.get(high);
        double auxNum = arr.get(high);
        int i = low;
        for (int j = low; j < high - 1; ++j) {
            if (arr.get(j) <= auxNum) {
                double temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                ApplicationInfo temp2 = appsList.get(i);
                appsList.set(i, appsList.get(j));
                appsList.set(j, temp2);
                ++i;
            }
        }
        double temp = arr.get(i);
        arr.set(i, arr.get(high));
        arr.set(high, temp);
        ApplicationInfo temp2 = appsList.get(i);
        appsList.set(i, appsList.get(high));
        appsList.set(high, temp2);
        return i;
    }

    public void sortList() {
        List<Double> appsListSort = new ArrayList<Double>();
        for (int i = 0; i < getCount(); ++i) {
            if (getCount() == 0) break;
            ApplicationInfo applicationInfo = appsList.get(i);
            if (null != applicationInfo) {
                String name = (String) applicationInfo.loadLabel(packageManager);
                name = name.toLowerCase();
                String aux = SearchText;
                aux = aux.toLowerCase();
                aux = aux.replaceAll("\\s+$", "");
                if(name.equals("sahaya")){
                    appsList.remove(i);
                    --i;
                }
                else {
                    double num = compareStrings(name, aux);
                    appsListSort.add(num);
                    int a = appsListSort.size();
                }
            }

        }
        quickSort(appsListSort, 0, appsListSort.size() - 1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.app_list_row, null);
        }

        ApplicationInfo applicationInfo = appsList.get(position);
        if (null != applicationInfo) {
            TextView appName = (TextView) view.findViewById(R.id.app_name);
            ImageView iconview = (ImageView) view.findViewById(R.id.app_icon);

            appName.setText(applicationInfo.loadLabel(packageManager));
            iconview.setImageDrawable(applicationInfo.loadIcon(packageManager));
        }
        return view;
    }
}