package com.onegoal.hammingCode;

import com.onegoal.crc.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText input;
	TextView msg, h_bits;
	Button submit, reset;
	String inputS="", msgS, h_bitsS;
	int c0, c1, c2, c3;
	int _c0[] = {0,0,3,0,5,0,7,0,9,0,11,0,13,0};
	int _c1[] = {0,0,3,0,0,6,7,0,0,10,11,0,0,14};
	int _c2[] = {0,0,0,0,5,6,7,0,0,0,0,12,13,14};
	int _c3[] = {0,0,0,0,0,0,0,0,9,10,11,12,13,14};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //defining the variables in onCreate method
        input = (EditText)	findViewById(R.id.input_textbox);
        msg = (TextView)	findViewById(R.id.msg_lbl);
        h_bits = (TextView) findViewById(R.id.h_bits_lbl);
        submit = (Button) findViewById(R.id.submit_btn);
        reset = (Button) findViewById(R.id.reset_btn);
        
        //setting listener to button
        submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				calculate();
			}
		});
        
        reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				msg.setText(null);
				h_bits.setText(null);
				input.setText(null);
			}
		});
        
    }
    
    public boolean checkInput(String inputS) {
    	for (int i = 0; i < inputS.length(); i++) {
    		if(inputS.charAt(i) == '0' || inputS.charAt(i) == '1') {}
    		else return false;
    	}
    	return true;
    }
    
    public void showErrorMessage(String errorMsg) {
    	Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    public void calculate() {
    	if (!input.getText().toString().equals("")) {
    		inputS = input.getText().toString();
    		if(checkInput(inputS)) {
    			inputS = "00" + inputS.charAt(0) + "0" + inputS.substring(1,4) + "0" + inputS.substring(4);
    			//showErrorMessage(inputS);
    			int size = inputS.length();
    			c0 = c1 = c2 = c3 = 0;
    			for (int i = 0; i < size; i++) {
    				if(inputS.charAt(i) == '1') {
    					if(i+1 == _c0[i])  { c0++; }
    					if(i+1 == _c1[i]) { c1++; }
    					if(i+1 == _c2[i]) { c2++; }
    					if(i+1 == _c3[i]) { c3++; }
    				}
    			}
    			
    			//if carry is even then set it to 0 else 1
    			if(c0 %2 == 0) c0 = 0; else c0 = 1;
    			if(c1 %2 == 0) c1 = 0; else c1 = 1;
    			if(c2 %2 == 0) c2 = 0; else c2 = 1;
    			if(c3 %2 == 0) c3 = 0; else c3 = 1;
    			
    			inputS = c0 + "" + c1 + "" + inputS.charAt(2) + c2 + inputS.substring(4,7) + c3 + inputS.substring(8);
    			
    			msg.setText(null);
    			msg.setText(inputS);
    			h_bits.setText("c0 = " + c0 + ",    c1 = " + c1 + ",     c2 = " + c2 + ",     c3 = " + c3);
    		} else {
    			showErrorMessage("Please enter proper message");
    		}
    	} else {
    		showErrorMessage("Please enter proper message");
    	}
    }
    
}
