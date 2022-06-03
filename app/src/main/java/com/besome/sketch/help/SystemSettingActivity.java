package com.besome.sketch.help;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.besome.sketch.editor.property.PropertySwitchItem;
import com.besome.sketch.lib.base.BaseAppCompatActivity;
import com.sketchware.remod.R;

import a.a.a.mB;
import a.a.a.xB;

public class SystemSettingActivity extends BaseAppCompatActivity {

    private LinearLayout contentLayout;
    private SharedPreferences.Editor preferenceEditor;

    private void addPreference(int key, int resName, int resDescription, boolean value) {
        PropertySwitchItem switchItem = new PropertySwitchItem(this);
        switchItem.setKey(key);
        switchItem.setName(xB.b().a(getApplicationContext(), resName));
        switchItem.setDesc(xB.b().a(getApplicationContext(), resDescription));
        switchItem.setValue(value);
        contentLayout.addView(switchItem);
    }

    @Override
    public void onBackPressed() {
        if (isSettingsSaved()) {
            setResult(-1, new Intent());
            finish();
        }
    }

    @Override
    public void onCreate(Bundle var1) {
        super.onCreate(var1);
        setContentView(R.layout.system_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        a(toolbar);
        d().d(true);
        d().e(true);
        findViewById(R.id.layout_main_logo).setVisibility(View.GONE);
        d().a(xB.b().a(this, R.string.main_drawer_title_system_settings));
        toolbar.setNavigationOnClickListener(view -> {
            if (!mB.a()) onBackPressed();
        });
        contentLayout = findViewById(R.id.content);
        SharedPreferences preferences = getSharedPreferences("P12", Context.MODE_PRIVATE);
        preferenceEditor = preferences.edit();
        addPreference(0, R.string.system_settings_title_setting_vibration,
                R.string.system_settings_description_setting_vibration,
                preferences.getBoolean("P12I0", true));

        addPreference(1, R.string.system_settings_title_automatically_save,
                R.string.system_settings_description_automatically_save,
                preferences.getBoolean("P12I2", false));
    }

    private boolean isSettingsSaved() {
        for (int i = 0; i < contentLayout.getChildCount(); i++) {
            View childAtView = contentLayout.getChildAt(i);
            if (childAtView instanceof PropertySwitchItem) {
                PropertySwitchItem propertySwitchItem = (PropertySwitchItem) childAtView;
                if (0 == propertySwitchItem.getKey()) {
                    preferenceEditor.putBoolean("P12I0", propertySwitchItem.getValue());
                } else if (1 == propertySwitchItem.getKey()) {
                    preferenceEditor.putBoolean("P12I2", propertySwitchItem.getValue());
                }
            }
        }
        return preferenceEditor.commit();
    }
}
