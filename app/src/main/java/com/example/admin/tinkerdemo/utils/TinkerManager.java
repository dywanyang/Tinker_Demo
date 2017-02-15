package com.example.admin.tinkerdemo.utils;

import com.example.admin.tinkerdemo.SampleResultService;
import com.example.admin.tinkerdemo.reporter.SampleLoaderReporter;
import com.example.admin.tinkerdemo.reporter.SamplePatchListener;
import com.example.admin.tinkerdemo.reporter.SamplePatchReporter;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * Created by Daiyan on 2017/2/14.
 */
public class TinkerManager {
    private static final String TAG = "Tinker.TinkerManager";
    private static ApplicationLike applicationLike;
    private static SampleLoaderReporter loadReporter;
    private static SamplePatchReporter patchReporter;
    private static SamplePatchListener patchListener;

    public ApplicationLike getApplicationLike() {
        return applicationLike;
    }

    public static void setApplicationLike(ApplicationLike appLike){
        applicationLike = appLike;
    }

    public static void installTinker(ApplicationLike applicationLike){
        loadReporter = new SampleLoaderReporter(applicationLike.getApplication());
        patchReporter = new SamplePatchReporter(applicationLike.getApplication());
        patchListener = new SamplePatchListener(applicationLike.getApplication());
        AbstractPatch upgradePatchProcessor = new UpgradePatch();
        TinkerInstaller.install(applicationLike,loadReporter, patchReporter, patchListener,
                SampleResultService.class, upgradePatchProcessor);
    }
}
