package com.abhat.network.interceptors

import okhttp3.*

/**
 * Created by Anirudh Uppunda on 25,November,2020
 */
class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith("nasa/pictures") -> getNasaPictures
            else -> ""
        }
        return if (responseString.isNotEmpty()) {
            Response.Builder()
                .code(200)
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            chain.proceed(chain.request())
        }
    }

    companion object {
        const val getNasaPictures = """
            
            {
                
            }
            
            """
    }
}