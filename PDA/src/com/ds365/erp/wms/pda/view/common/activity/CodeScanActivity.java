package com.ds365.erp.wms.pda.view.common.activity;

import java.io.IOException;
import java.util.Vector;

import com.ds365.commons.utils.T;
import com.ds365.erp.pda.R;
import com.ds365.erp.wms.pda.common.base.PdaConstants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.yxz.zxing.camera.CameraManager;
import com.yxz.zxing.decoding.CaptureActivityHandler;
import com.yxz.zxing.decoding.CaptureActivityHandler.HandleDecode;
import com.yxz.zxing.decoding.InactivityTimer;
import com.yxz.zxing.view.ViewfinderView;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 
 * 说明 ：手机扫描 
 */
public class CodeScanActivity extends BasePdaActivity implements Callback{

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private ImageView imageBack,resultImage;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	private RelativeLayout resultParent;
	private TextView resultText;
	private SurfaceView surfaceView;
	
	public static final int RESULT_CODE=PdaConstants.nextResultCode();

	@Override
	protected int getContentViewId() {
		return R.layout.common_code_scan;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		surfaceView = (SurfaceView) findViewById(R.id.codescan_preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}
	
	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
			if (handler == null) {
				handler = new CaptureActivityHandler(CodeScanActivity.this,decodeFormats,characterSet,viewfinderView);
				handler.setHandleDecode(new HandleDecode() {
					
					@Override
					public void onHandleDecode(Result result, Bitmap barcode) {
						inactivityTimer.onActivity();
						playBeepSoundAndVibrate();
						String resultString = result.getText();
						if (resultString.equals("")) {
							T.showShort(CodeScanActivity.this, "Scan failed!");
						}else {
//							surfaceView.setVisibility(View.INVISIBLE);
//							resultParent.setVisibility(View.VISIBLE);
//							resultText.setText(resultString);
//							resultImage.setImageBitmap(barcode);
							Intent data = new Intent();
							Bundle bundle = new Bundle();
							bundle.putString(PdaConstants.scanResult, resultString);
							data.putExtras(bundle);
							setResult(RESULT_CODE, data);
							finish();
						}
					}
				});
			}
		} catch (Exception e) {
			T.showLong(context,"请在设置页面打开相机权限！");
			finish();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(arg0);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		hasSurface = false;
	}
	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

	@Override
	protected void initActivityView() {
		imageBack = (ImageView) findViewById(R.id.codescan_head_left);
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.codescan_viewfinder);
		resultImage = (ImageView) findViewById(R.id.codescan_bitmap);
		resultParent = (RelativeLayout) findViewById(R.id.codescan_result_parent);
		resultText = (TextView) findViewById(R.id.codescan_result);

		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}

	@Override
	protected void initNavigation() {
		
	}

	@Override
	protected void setListener() {
		imageBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
}
