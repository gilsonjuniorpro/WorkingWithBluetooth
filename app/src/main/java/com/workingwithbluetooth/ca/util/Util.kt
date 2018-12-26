package com.workingwithbluetooth.ca.util

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.widget.Toast
import android.util.Log
import android.widget.ListView
import com.workingwithbluetooth.ca.dto.DeviceDTO


object Util {

    fun verifyIfHasBluetooth(context: Context) : Boolean {
        var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if (bluetoothAdapter == null) {
            showToast("This device doesn’t support Bluetooth", context)
            return false
        } else {
            showToast("Great, this device supports Bluetooth", context)
            return true
        }
    }


    fun verifyIfBluetoothIsEnabled(context: Context) : Boolean {
        var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if (!bluetoothAdapter.isEnabled) {
            showToast("Bluetooth isn't enabled", context)
            return false
        } else {
            showToast("Bluetooth is enabled", context)
            return true
        }
    }


    fun verifyingPairedDevices() : MutableList<DeviceDTO>{
        var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        val pairedDevices = bluetoothAdapter.bondedDevices

        var list: MutableList<DeviceDTO> = ArrayList()

        if (pairedDevices.size > 0) {

            for (device in pairedDevices) {
                //Retrieve each device’s public identifier and MAC address. Add each device’s name and address to an ArrayAdapter, ready to incorporate into a
                //ListView
                //mArrayAdapter.add(device.name + "\n" + device.address)
                list.add(DeviceDTO(device.name, device.address))
                Log.i("TAGBLUE", "${device.name} - ${device.address}")
            }
        }

        return list
    }

    fun showToast(message: String, context: Context){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

}