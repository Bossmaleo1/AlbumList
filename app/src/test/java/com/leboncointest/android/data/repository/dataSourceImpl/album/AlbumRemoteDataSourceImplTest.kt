package com.leboncointest.android.data.repository.dataSourceImpl.album

import com.leboncointest.android.data.apiService.AlbumAPIService
import com.leboncointest.android.data.model.dataRemote.response.Album
import com.leboncointest.android.errorResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class AlbumRemoteDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: AlbumAPIService

    private val client = OkHttpClient.Builder().build()

    @Before
    fun createServer() {
        mockWebServer = MockWebServer()
        apiClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumAPIService::class.java)
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
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
        mockWebServer.enqueue(mockResponse)
    }


    @Test
    fun `correct response is parsed into success result`() = runTest {

        //we load our json response
        enqueueMockResponse("albums.json")

        val albumRemoteDataSource = AlbumRemoteDataSourceImpl(apiClient)

        // Act
        val result = albumRemoteDataSource.getAlbums()

        // Assert
        assert(result is Response<List<Album>>)
    }

}