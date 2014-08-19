package com.example.com.inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
Button warehouseBtn;
Button clientBtn;
Button Product;
Intent warehouseActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		warehouseBtn = (Button) findViewById(R.id.warehouseBtn);
		clientBtn 	 = (Button) findViewById(R.id.clientBtn);
		Product 	 = (Button) findViewById(R.id.productBtn);
		
		//on click open warehouse activity 
		warehouseBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//go to warehouse activity
				warehouseActivity = new Intent(MainActivity.this,WarehouseActivity.class);
				startActivity(warehouseActivity);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
