package ke.co.mediashare.mediashare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;
import java.io.IOException;

public class BookReaderActivity extends AppCompatActivity {
	PDFView pdfView;
	ProgressDialog progressDialog;
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_reader);

		pdfView = (PDFView)findViewById(R.id.pdfView);

		toolbar = (Toolbar)findViewById(R.id.book_reader_toolbar);
		toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
		setSupportActionBar(toolbar);

		AsyncTask<Void, Void, Void> pdfLoadingTask = new AsyncTask<Void, Void, Void>() {
			@Override
			protected void onPreExecute(){
				progressDialog = new ProgressDialog(BookReaderActivity.this);
				progressDialog.setTitle("MediaShare Book Reader");
				progressDialog.setMessage("Opening book.....");
				progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progressDialog.setCancelable(false);
				progressDialog.setIndeterminate(true);
				progressDialog.show();
			}
			@Override
			protected Void doInBackground(Void... params) {
				loadPdf();
				return null;
			}
			@Override
		    protected void onPostExecute(Void results){
				progressDialog.dismiss();
			}
		};
		pdfLoadingTask.execute((Void[])null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_book_reader, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void loadPdf(){
		String assetPath = "file:///android_asset/books/open_km.pdf";
		try{
			FileUtils.fileFromAsset(this, assetPath);
			pdfView.loadPages();
			pdfView.fromAsset(assetPath)
					.defaultPage(2)
					.showMinimap(false)
					.enableSwipe(true)
					.onPageChange(new OnPageChangeListener() {
						@Override
						public void onPageChanged(int i, int i1) {

						}
					})
					.load();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

}
