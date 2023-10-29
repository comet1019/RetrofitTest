package com.example.retrofittest.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface JwtApi {	//서버로부터 받아오는 Service를 정의하는 부분


    @GET("/user/signup/jwt")
    fun getLogin(
        @Header("token") token : String
        //@Query()
    ) : Call<GetResData>


    @Headers("Content-Type: application/json")  //@Headers 어노테이션 이용해 헤더값 넣어주기
    @POST("user/signin")                         //HTTP 메소드를 설정해주고 API와 URL 작성
    fun postLogin(
        //@Body 어노테이션을 통해 RequestBody 데이터를 넣어준다.
        @Body postReqLoginData: PostReqLoginData

    ) : Call<PostResLoginData>

}