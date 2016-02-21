package com.example.abhinav.sahaya.Pills;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xavier Marquès on 20/02/16.
 */
public class Pills {
    private List<Pill> pills;
    public Pills() {
        this.pills = new ArrayList<Pill>();
    }

    public void addPill(Pill pill) {
        this.pills.add(pill);
    }

    public int getSize() {
        return pills.size();
    }

    public Pill getPill(int position) {

        int i = 0;
        for (Pill pill : pills) {
            if (i == position) return pill;
            ++i;
        }
        return null;

    }



}
