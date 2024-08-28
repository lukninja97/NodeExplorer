package com.lukninja.nodeexplorer

import com.lukninja.nodeexplorer.service.model.Country
import com.lukninja.nodeexplorer.service.model.Node
import com.lukninja.nodeexplorer.service.repository.NodeRepository
import com.lukninja.nodeexplorer.service.repository.remote.NodeService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class NodeRepositoryTest {
    private val mockNodeService = mock(NodeService::class.java)

    private val repository = NodeRepository().apply {
        val field = NodeRepository::class.java.getDeclaredField("mRemote")
        field.isAccessible = true
        field.set(this, mockNodeService)
    }

    @Test
    fun `test getLargerConnectedNodes returns data when successful`() = runBlocking {

        val fakeNodes = listOf(
            Node(
                alias = "ACINQ",
                capacity = 60729927595,
                channels = 2491,
                city = null,
                country = Country(
                    de = "Vereinigte Staaten",
                    en = "United States",
                    es = "Estados Unidos",
                    fr = "États Unis",
                    ja = "アメリカ",
                    ptBR = "EUA",
                    ru = "США",
                    zhCN = "美国"
                ),
                firstSeen = 1522941222,
                publicKey = "03864ef025fde8fb587d989186ce6a4a186895ee44a926bfc370e2c366597a3f8f",
                updatedAt = 1724796883
            )
        )

        `when`(mockNodeService.getLargerConnectedNodes()).thenReturn(Response.success(fakeNodes))

        val result = repository.getLargerConnectedNodes()

        assertEquals(fakeNodes, result)
    }

    @Test
    fun `test getLargerConnectedNodes returns empty list when successful`() = runBlocking {
        val fakeNodes = listOf<Node>()

        `when`(mockNodeService.getLargerConnectedNodes()).thenReturn(Response.success(fakeNodes))

        val result = repository.getLargerConnectedNodes()

        assertEquals(fakeNodes, result)
    }
}