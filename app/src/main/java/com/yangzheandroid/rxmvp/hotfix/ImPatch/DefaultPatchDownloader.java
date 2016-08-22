package com.yangzheandroid.rxmvp.hotfix.ImPatch;


import com.yangzheandroid.rxmvp.hotfix.IPatch.IPatchDownloader;

/**
 * patch下载默认实现
 * Created by shoyu666@163.com on 16/7/5.
 */
public class DefaultPatchDownloader implements IPatchDownloader {

    public static final String TAG = "DefaultPatchDownloader";

    @Override
    public void downloadPatch() {
        downloadRemotePach();
    }

    @Override
    public String getUrl() {
        return "";
    }

    /**
     * 下载pach
     *
     * @param
     */
    private void downloadRemotePach() {

//        File pachDir = PatchManger.getGlobalPatchManger().patchFileDir.getPatchJarDir();
//
//        if (pachDir != null && pachDir.exists()) {
//            File temp = new File(pachDir, "temp.jar");
//            if (temp.exists()) {
//                temp.delete();
//            }

//            AQuery aq = new AQuery(MAppManager.getApplication());
//
//            aq.download(getUrl(), temp, new AjaxCallback<File>() {
//                @Override
//                public void callback(String url, File object, AjaxStatus status) {
//                    super.callback(url, object, status);
//                    if (status.getCode() == 200) {
//                        File current = PatchManger.getGlobalPatchManger().patchFileDir.getCurrentPatchJar();
//                        if (current.exists() && current.delete()) {
//                            object.renameTo(current);
//                        }
//                        Log.d(TAG, "down load patch success ");
//                    } else {
//                        Log.e(TAG, "down load patch  error" + status.getMessage());
//                    }
//                }
//            });
            //
//        }
    }
}
