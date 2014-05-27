package se.mah.kd330a.project.find.data;

import android.content.Context;
import android.graphics.Bitmap;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
	Context mContext;
	String mLoadingFile;
	OnImageLoaderListener mListener;
	
	public interface OnImageLoaderListener {
		public void onImageReceived(String fileName);
	}
	
	public ImageLoader(Context context, Fragment frag) {
		mContext = context;
		try {
			mListener = (OnImageLoaderListener) frag;
		} catch (ClassCastException e) {
			throw new ClassCastException(frag.toString()
					+ " must implement OnImageLoaderListener");
		}
	}
	
	//This starts the download of the image. There is no check in here if the file exists, so we can actually run this to force a new image being downloaded.
	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap output = null;
		try {
		mLoadingFile = params[0];
		Log.i("project", "DownloadFilesTask " + mLoadingFile);
		GetImage.getImageFromNet(mLoadingFile, true, mContext);		
		output =  GetImage.getImageFromLocalStorage(mLoadingFile, mContext);
		}
		catch(Exception e)
		{
		Log.e("find", "Could not download the picture, so we are writing this instead of crashing");	
		
		}
		return output;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if (result != null)
			mListener.onImageReceived(mLoadingFile);
   	 
		super.onPostExecute(result);
	}

}


