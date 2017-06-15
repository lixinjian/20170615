package com.ds365.appupdate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.ds365.commons.utils.L;
import com.ds365.commons.utils.T;

public class DownloadService extends Service {
	
	private static final int NOTIFY_ID = 0;
	private int progress;
	private NotificationManager mNotificationManager;
	private boolean canceled;
	public static int lastRate = 0;
	private DownloadBinder binder;
	private boolean serviceIsDestroy = false;
	private Context mContext = this;

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				AppUpdateGlobal.setDownload(false);
				// 下载完毕
				// 取消通知
				mNotificationManager.cancel(NOTIFY_ID);
				installApk();
				break;
			case 2:
				AppUpdateGlobal.setDownload(false);
				// 这里是用户界面手动取消，所以会经过activity的onDestroy();方法
				// 取消通知
				mNotificationManager.cancel(NOTIFY_ID);
				break;
			}
		}
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// 假如被销毁了，无论如何都默认取消了。
		AppUpdateGlobal.setDownload(false);
		binder.cancel();
		binder.cancelNotification();
		binder = null;
		lastRate = 0;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		lastRate = 0;
		if (binder != null || mNotificationManager != null) {
			binder = null;
			binder = new DownloadBinder();
			mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		} else {
			binder = new DownloadBinder();
			mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
			binder.start();
		}

		return START_STICKY;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void unbindService(ServiceConnection conn) {
		super.unbindService(conn);
	}

	public class DownloadBinder extends Binder {
		public void start() {
			if (downLoadThread == null || !downLoadThread.isAlive()) {// downLoadThread
				progress = 0;
				setUpNotification();
				new Thread() {
					public void run() {
						// 下载
						startDownload();
					}
				}.start();
			}
		}

		public void cancel() {
			canceled = true;
		}

		public int getProgress() {
			return progress;
		}

		public boolean isCanceled() {
			return canceled;
		}

		public boolean serviceIsDestroy() {
			return serviceIsDestroy;
		}

		public void cancelNotification() {
			mHandler.sendEmptyMessage(2);
		}

	}

	private void startDownload() {
		canceled = false;
		downloadApk();
	}
	/**
	 * 创建通知
	 */
	private void setUpNotification() {
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
		
		Notification.Builder mBuilder = new Notification.Builder(this);
		mBuilder.setContentTitle("")//设置通知栏标题
	    .setContentText("新apk 正在下载...")
	    .setContentIntent(contentIntent)//设置通知栏点击意图
	    .setTicker("开始下载") //通知首次出现在通知栏，带上升动画效果的
	    .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
	    .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
	    .setSmallIcon(R.drawable.ic_launcher);//设置通知小ICON
		
		Notification mNotification = mBuilder.build();
		// 指定内容意图
		mNotificationManager.notify(NOTIFY_ID, mNotification);
	}

	/**
	 * 下载apk
	 * 
	 * @param url
	 */
	private Thread downLoadThread;

	private void downloadApk() {
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}

	/**
	 * 安装apk
	 */
	private void installApk() {
		File apkfile = new File(AppUpdateConstants.ROOT_PATH + AppUpdateGlobal.saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);

	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				if ("" != AppUpdateGlobal.UPDATE_URL && null != AppUpdateGlobal.UPDATE_URL) {
					URL url = new URL(AppUpdateGlobal.UPDATE_URL);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					int length = conn.getContentLength();
					InputStream is = conn.getInputStream();

					File file = new File(AppUpdateConstants.ROOT_PATH);
					if (!file.exists()) {
						file.mkdirs();
					}
					String apkFile = AppUpdateConstants.ROOT_PATH + AppUpdateGlobal.saveFileName;
					File ApkFile = new File(apkFile);
					FileOutputStream fos = new FileOutputStream(ApkFile);
					int count = 0;
					byte buf[] = new byte[1024];

					do {
						int numread = is.read(buf);
						count += numread;
						progress = (int) (((float) count / length) * 100);
						if (progress >= lastRate + 1) {
							lastRate = progress;
						}
						if (numread <= 0) {
							lastRate = 0;
							// 下载完成通知安装
							mHandler.sendEmptyMessage(0);
							// 下载完了，cancelled也要设置
							canceled = true;
							break;
						}
						fos.write(buf, 0, numread);
					} while (!canceled);// 点击取消就停止下载.
					fos.close();
					is.close();
				}

			}catch (IOException e) {
				L.e(e.toString());
				T.showShort(DownloadService.this,e.toString());
			}

		}
	};

}
