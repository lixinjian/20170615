/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yxz.zxing.decoding;

import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.yxz.zxing.camera.CameraManager;
import com.yxz.zxing.view.ViewfinderResultPointCallback;
import com.yxz.zxing.view.ViewfinderView;

/**
 * This class handles all the messaging which comprises the state machine for capture.
 */
public final class CaptureActivityHandler extends Handler {

	private final Activity activity;
	private final DecodeThread decodeThread;
	private State state;
	private ViewfinderView viewFinderView;
	private HandleDecode handleDecode;

	private enum State {
		PREVIEW,
		SUCCESS,
		DONE
	}

	public CaptureActivityHandler(Activity activity,Vector<BarcodeFormat> decodeFormats,
			String characterSet,ViewfinderView viewFinderView) {
		this.activity = activity;
		decodeThread = new DecodeThread(this, decodeFormats, characterSet,
				new ViewfinderResultPointCallback(viewFinderView));
		this.viewFinderView = viewFinderView;
		decodeThread.start();
		state = State.SUCCESS;
		// Start ourselves capturing previews and decoding.
		CameraManager.get().startPreview();
		restartPreviewAndDecode();
	}

	@Override
	public void handleMessage(Message message) {
		if(message.what == HandlerIdUtil.AUTO_FOCUS){
			if (state == State.PREVIEW) {
				CameraManager.get().requestAutoFocus(this, HandlerIdUtil.AUTO_FOCUS);
			}
		}else if(message.what == HandlerIdUtil.RESTART_PREVIEW){
			restartPreviewAndDecode();
		}else if(message.what == HandlerIdUtil.DECODE_SUCCEEDED){
			state = State.SUCCESS;
			Bundle bundle = message.getData();
			Bitmap barcode = bundle == null ? null :
			(Bitmap) bundle.getParcelable(DecodeThread.BARCODE_BITMAP);//���ñ����߳�
			handleDecode.onHandleDecode((Result) message.obj, barcode);
		}else if(message.what == HandlerIdUtil.DECODE_FAILED){
			state = State.PREVIEW;
			CameraManager.get().requestPreviewFrame(decodeThread.getHandler(),HandlerIdUtil.DECODE);
		}else if(message.what == HandlerIdUtil.RETURN_SCAN_RESULT){
			activity.setResult(Activity.RESULT_OK, (Intent) message.obj);
			activity.finish();
		}else if(message.what == HandlerIdUtil.LAUNCH_PRODUCT_QUERY){
			String url = (String) message.obj;
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			activity.startActivity(intent);
		}
	}

	public void setHandleDecode(HandleDecode handleDecode){
		this.handleDecode = handleDecode;
	}
	
	public interface HandleDecode{
		public void onHandleDecode(Result result, Bitmap barcode);
	}
	
	public void quitSynchronously() {
		state = State.DONE;
		CameraManager.get().stopPreview();
		Message quit = Message.obtain(decodeThread.getHandler(),HandlerIdUtil.QUIT);
		quit.sendToTarget();
		try {
			decodeThread.join();
		} catch (InterruptedException e) {
			// continue
		}
		removeMessages(HandlerIdUtil.DECODE_SUCCEEDED);
		removeMessages(HandlerIdUtil.DECODE_FAILED);
	}

	private void restartPreviewAndDecode() {
		if (state == State.SUCCESS) {
			state = State.PREVIEW;
			CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), HandlerIdUtil.DECODE);
			CameraManager.get().requestAutoFocus(this,HandlerIdUtil.AUTO_FOCUS);
			viewFinderView.drawViewfinder();
		}
	}

}
