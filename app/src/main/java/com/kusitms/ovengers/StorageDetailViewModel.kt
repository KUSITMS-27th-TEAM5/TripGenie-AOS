package com.kusitms.ovengers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kusitms.ovengers.data.ResponseGetTicket
import com.kusitms.ovengers.data.ResponseStorageCarrier
import com.kusitms.ovengers.data.StorageData
import com.kusitms.ovengers.data.TicketData
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StorageDetailViewModel : ViewModel() {// 커밋용
    private lateinit var retAPIS: APIS
    private val _ticketList = MutableLiveData<List<TicketData>>()
    val ticketList : LiveData<List<TicketData>> = _ticketList

    private val TAG = StorageDetailViewModel::class.java.simpleName

    // SharedPreferences 조희
    val accessToken = MyApplication.prefs.getString("accessToken", "token")
    val username = MyApplication.prefs.getString("username", "username")
    val carrierId = MyApplication.prefs.getString("carrierId", "1")


    init {
        fetchTicketList()
    }

    // 티켓 리스트 조회 API
    private fun fetchTicketList() {
        // Retrofit
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // Bearer 추가
        val bearerToken = "Bearer $accessToken"
        viewModelScope.launch {
            try {
                retAPIS.getTicket(bearerToken, carrierId).enqueue(object : Callback<ResponseGetTicket> {
                    override fun onResponse(call: Call<ResponseGetTicket>, response: Response<ResponseGetTicket>) {
                        if (response.isSuccessful) {
                            _ticketList.value = response.body()?.data
                            Log.d(TAG, "getTicket Response: ${response.body()?.data}")
                        } else {
                            Log.d(TAG, "getTicket Error 1 : ${response.errorBody()}")
                        }
                    } override fun onFailure(call: Call<ResponseGetTicket>, t: Throwable) {
                        Log.d(TAG, "getTicket Error 2")
                    }
                })
            } catch (e: Exception) {
                Log.d(TAG, "getTicket Error 3 : ${e.message}")
            }
        }
    }
}