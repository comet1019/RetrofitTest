package com.example.retrofittest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {	//서비스를 생성해주는 구현체 부분
    private const val BASE_URL = "http://10.0.2.2:8000/"

    //Retrofit 객체 생성
    private val retrofit: Retrofit = Retrofit

        //레트로핏 빌더 생성 (생성자 호출)
        .Builder()

        //빌더 객체의 baseUrl 호출. 서버의 메인 URL 전달
        .baseUrl(BASE_URL)

        //gson 컨버터 연동
        .addConverterFactory(GsonConverterFactory.create())

        //Retrofit 객체 반환
        .build()

    //인터페이스 객체를 create에 넘겨 실제 구현체 생성
    val jwtApi : JwtApi = retrofit.create(JwtApi::class.java)

}