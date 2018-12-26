package com.workingwithbluetooth.ca

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.workingwithbluetooth.ca.adapter.DeviceAdapter
import com.workingwithbluetooth.ca.dto.DeviceDTO
import com.workingwithbluetooth.ca.util.Util
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val BLUETOOTH_ENABLE_REQUEST_CODE = 1
    private var isEnabled = false
    private var hasBluetooth = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list: MutableList<DeviceDTO> = ArrayList()
        listView.layoutManager = LinearLayoutManager(this)

        btVerify.setOnClickListener{
            hasBluetooth = Util.verifyIfHasBluetooth(baseContext)

            if(hasBluetooth){
                isEnabled = Util.verifyIfBluetoothIsEnabled(baseContext)

                if(!isEnabled){
                    var enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)

                    startActivityForResult(enableIntent, BLUETOOTH_ENABLE_REQUEST_CODE);
                    Toast.makeText(baseContext, "Enabling Bluetooth!", Toast.LENGTH_LONG).show();
                }else{
                    list = Util.verifyingPairedDevices()

                    listView.adapter = DeviceAdapter(list,this)
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BLUETOOTH_ENABLE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Util.showToast("Bluetooth has been enabled", baseContext)
            }

            if(resultCode == RESULT_CANCELED){
                Util.showToast("An error occurred while attempting to enable Bluetooth", baseContext)
            }
        }
    }
}