/*
 * Copyright (C) 2016 Go Karumi S.L.
 */

package com.flowup.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.flowup.BuildConfig;

import static com.flowup.utils.MetricNameUtils.replaceDots;

public class App {

  private final Context context;

  public App(Context context) {
    this.context = context;
  }

  public String getAppPackageName() {
    return replaceDots(context.getPackageName());
  }

  public String getVersionName() {
    try {
      String packageName = context.getPackageName();
      PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
      if (packageInfo.versionName == null) {
        throw new PackageManager.NameNotFoundException("Name found but is null");
      }
      return replaceDots(packageInfo.versionName);
    } catch (PackageManager.NameNotFoundException e) {
      String buildConfigVersionName = replaceDots(BuildConfig.VERSION_NAME);
      if (buildConfigVersionName.isEmpty()) {
        return "unknown";
      } else {
        return buildConfigVersionName;
      }
    }
  }

  public int getPid() {
    return android.os.Process.myPid();
  }
}
