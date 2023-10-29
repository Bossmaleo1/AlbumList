package com.leboncointest.android.data.apiService

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumAPIServiceTest {

    private lateinit var service: AlbumAPIService
    //we initialize our mock server
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        //we call our mock web server constructor
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumAPIService::class.java)
    }


    /**
     * We create our enqueue Mock Response
     * This function help us to learn our
     * json resources files
     */
     fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }


    @Test
    fun `Retrieve our album list`() {
        runBlocking {
            //we load our json response
            enqueueMockResponse("albums.json")
            //we simulate our api call
            val responseBody = service.getAlbums().body()
            val request = server.takeRequest()
            //we test if our response is Not null
            assertThat(responseBody).isNotNull()
            Truth.assertThat(request.path).isEqualTo("/img/shared/technical-test.json")
            //we test our product list response length
            assertThat(responseBody!!.size).isEqualTo(5000)
            assertThat(responseBody[0].title).isEqualTo("accusamus beatae ad facilis cum similique qui sunt")
            assertThat(responseBody[0].id).isEqualTo(1)
            assertThat(responseBody[0].albumId).isEqualTo(1)
            assertThat(responseBody[0].url).isEqualTo("https://via.placeholder.com/600/92c952")
            assertThat(responseBody[0].thumbnailUrl).isEqualTo("https://via.placeholder.com/150/92c952")
        }
    }

    @After
    fun shutdownServer() {
        server.shutdown()
    }
}