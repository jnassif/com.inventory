package com.example.com.inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class WarehouseActivity extends Activity {

	Button add_save_btn, add_view_all, update_btn, update_view_all;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.warehouse_activity);
		
		// set screen
		Set_Add_Update_Screen();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void Set_Add_Update_Screen() {
    	add_save_btn = (Button) findViewById(R.id.add);
    	update_btn = (Button) findViewById(R.id.update_btn);
    	add_view_all = (Button) findViewById(R.id.add_view_all);
    	update_view_all = (Button) findViewById(R.id.update_view_all);
    }
	
}
