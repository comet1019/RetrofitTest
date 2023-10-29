package com.example.retrofittest

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.retrofit.GetResData
import com.example.retrofittest.retrofit.JwtApi
import com.example.retrofittest.retrofit.PostReqLoginData
import com.example.retrofittest.retrofit.PostResLoginData
import com.example.retrofittest.retrofit.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    //val email : String = "comet1010@naver.com"
    //val pw : String = "1234"
    //val requestData = PostReqLoginData(email,pw)

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnEvent = binding.subBnt
        val checkEvent = binding.checkBtn
        val jwtApi : JwtApi = ServiceCreator.jwtApi

        btnEvent.setOnClickListener{
            val email : String = binding.ETemail.text.toString()
            val pw : String = binding.ETpw.text.toString()
            val requestData = PostReqLoginData(email,pw)

            val callPostJwtApi = jwtApi.postLogin(requestData)
            JwtPost(callPostJwtApi);

            App.token_prefs.accessToken?.let { it1 -> Log.d(TAG, it1) }
        }

        checkEvent.setOnClickListener{
            val callGetJwtCheck = App.token_prefs.accessToken?.let { it1 -> jwtApi.getLogin(it1) }

            callGetJwtCheck?.let { it1 -> jwtCheck(it1) }
        }

    }


    // jwt사용 함수
    fun JwtPost(callPostJwtApi : Call<PostResLoginData>) {
        callPostJwtApi.enqueue(object : Callback<PostResLoginData> {

            override fun onResponse(
                call: Call<PostResLoginData>,
                response: Response<PostResLoginData>
            ) {
                Log.d(TAG, "성공 : ${response.body()}")

                App.token_prefs.accessToken = response.body()?.token
                // Log.d(TAG, "token : ${App.token_prefs.accessToken}")// sharedPreference에 데이터 저장 후 호출
            }

            override fun onFailure(call: Call<PostResLoginData>, t: Throwable) {
                Log.d(TAG, "POST 실패 : $t")
            }


        })
    }

    fun jwtCheck(callGetJwtCheck : Call<GetResData>){
        callGetJwtCheck.enqueue(object : Callback<GetResData> {

            override fun onResponse(
                call: Call<GetResData>,
                response: Response<GetResData>
            ) {
                Log.d(TAG, "GET성공 : ${response.body()}")
                Log.d(TAG, "token : ${App.token_prefs.accessToken}")// sharedPreference에 데이터 저장 후 호출
            }

            override fun onFailure(call: Call<GetResData>, t: Throwable) {
                Log.d(TAG, "GET 실패 : $t")
            }


        })
    }


}




