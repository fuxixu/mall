package com.example.mall.adapter;

import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class MyTabAdapter implements TabAdapter {

    List<String> titles;

    public MyTabAdapter() {
        titles = new ArrayList<>();
        Collections.addAll(titles, "Android", "IOS", "Web", "JAVA", "C++",
                ".NET", "JavaScript", "Swift", "PHP", "Python", "C#", "Groovy", "SQL", "Ruby");
    }
    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public ITabView.TabBadge getBadge(int position) {
        return null;
    }

    @Override
    public ITabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public ITabView.TabTitle getTitle(int position) {



        return new TabView.TabTitle.Builder()
                .setContent(titles.get(position))
                .setTextColor(Color.WHITE, 0xBBFFFFFF)

                .build();
    }

    @Override
    public int getBackground(int position) {
        return -1;
    }
}
